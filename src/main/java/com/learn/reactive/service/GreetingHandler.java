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




        /*return request.bodyToMono(User.class)
                .map(this::mapToUser)
                .log()
                .doOnNext(userRepository::save)
                .then(ServerResponse.ok().contentType(MediaType.TEXT_PLAIN)
                .body(BodyInserters.fromValue("Hello, Spring 2!")));*/

        /*return request.bodyToMono(User.class)
                .map(this::mapToUser)
                .log()
                .doOnNext(userRepository::save)
                .flatMap(u-> ServerResponse
                        .ok()
                        .contentType(MediaType.TEXT_PLAIN)
                        .body(BodyInserters.fromValue(u.getName())));*/

        /*return request.bodyToMono(User.class)
                .map(this::mapToUser)
                .log()
                .doOnNext(userRepository::save)
                .flatMap(u-> ServerResponse
                        .ok()
                        .contentType(MediaType.TEXT_PLAIN)
                        .body(BodyInserters.fromValue(u.getName())))
                .onErrorResume(e -> Mono.just("Error " + e.getMessage())
                        .flatMap(s -> ServerResponse.badRequest()
                                .contentType(MediaType.TEXT_PLAIN)
                                .body(BodyInserters.fromValue(s))));*/

        return request.bodyToMono(User.class)
                .map(this::mapToUser)
                .log()
                .doOnNext(userRepository::save)
                .flatMap(u-> ServerResponse
                        .ok()
                        .contentType(MediaType.TEXT_PLAIN)
                        .body(BodyInserters.fromValue(u.getName())))
                .onErrorResume(e -> ServerResponse.badRequest()
                                .contentType(MediaType.TEXT_PLAIN)
                                .body(BodyInserters.fromValue("Error 123 : "+e.getMessage())));



    }


    User mapToUser(User u){
        final User user = new User();
        user.setUserId(u.getUserId());
        user.setUserSettings(u.getUserSettings());
        user.setName(u.getName());
        throw new RuntimeException("Error custom ");
        //return user;
    }
}
