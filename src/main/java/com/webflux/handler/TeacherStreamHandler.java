package com.webflux.handler;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;

import com.webflux.dao.TeacherDao;
import com.webflux.dto.Teacher;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class TeacherStreamHandler {

    @Autowired
    private TeacherDao dao;


    public Mono<ServerResponse> getTeachers(ServerRequest request) {
        Flux<Teacher> teachersStream = dao.getTeachersStream();
        return ServerResponse.ok().
        		// by using content type we infrom publisher to send stream 
        		// instead of object
                contentType(MediaType.TEXT_EVENT_STREAM)
                .body(teachersStream, Teacher.class);
    }
}
