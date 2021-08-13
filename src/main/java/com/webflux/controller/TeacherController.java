package com.webflux.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.webflux.dto.Teacher;
import com.webflux.service.TeacherService;

import reactor.core.publisher.Flux;

import java.util.List;

@RestController
@RequestMapping("/Teacher")
public class TeacherController {
	@Autowired
    private TeacherService service;


    @GetMapping
    public List<Teacher> getAllTeacher() {
        return service.loadAllTeacher();
    }

    @GetMapping(value = "/stream",produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    // In reactive programming we do not use list
    //we use Flux because it is a publisher
    public Flux<Teacher> getAllTeacherStream() {
        return service.loadAllTeacherStream();
    }

}
