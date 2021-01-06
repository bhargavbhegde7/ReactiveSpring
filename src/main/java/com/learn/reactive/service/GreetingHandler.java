package com.learn.reactive.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

@Component
public class GreetingHandler {

    @Autowired
    UserRepository userRepository;

    //@Autowired
    //UserRepositoryReactive userRepositoryReactive;

    public Mono<ServerResponse> hello(ServerRequest request) {
        return ServerResponse.ok().contentType(MediaType.TEXT_PLAIN)
                .body(BodyInserters.fromValue("Hello, Spring!"));
    }

    public Mono<ServerResponse> hello2(ServerRequest request) {

        List<User> users = userRepository.findAll();

        return ServerResponse.ok().contentType(MediaType.TEXT_PLAIN)
                .body(BodyInserters.fromValue("Hello, Spring 2!"));
    }

    public Mono<ServerResponse> insertUser(ServerRequest request) {
        Mono<User> userMono = request.bodyToMono(User.class);

        final User user = new User();

        //userRepositoryReactive.save(userMono.block()).block();

        try {
            userMono.subscribe(u->{
                user.setUserId(u.getUserId());
                user.setUserSettings(u.getUserSettings());
                user.setName(u.getName());

                userRepository.save(user);
            }).wait();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        return ServerResponse.ok().contentType(MediaType.TEXT_PLAIN).body(BodyInserters.fromValue("Hello, Spring 2!"));

    }
}
