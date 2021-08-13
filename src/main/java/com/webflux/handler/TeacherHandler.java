package com.webflux.handler;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;

import com.webflux.dao.TeacherDao;
import com.webflux.dto.Teacher;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class TeacherHandler {

    @Autowired
    private TeacherDao teacherdao;

    // load teacher is a method name 
    // server Request
    public Mono<ServerResponse> loadTeacher(ServerRequest request){
        Flux<Teacher> teacherList = teacherdao.getTeacherList();
        return ServerResponse.ok().body(teacherList,Teacher.class);
    }

    public Mono<ServerResponse> findTeacher(ServerRequest request){
        int teacherId= Integer.valueOf( request.pathVariable("input"));
     // dao.getTeacherList().filter(c->c.getId()==teacherId).take(1).single();
        Mono<Teacher> teacherMono = teacherdao.getTeacherList().filter(c -> c.getId() == teacherId).next();
        return ServerResponse.ok().body(teacherMono,Teacher.class);
    }
    
    public Mono<ServerResponse> saveTeacher(ServerRequest request){
    	Mono<Teacher> teacherMono=request.bodyToMono(Teacher.class);
    	Mono<String> saveResponse = teacherMono.map(dto -> dto.getId()+":" + dto.getId());
    	return ServerResponse.ok().body(teacherMono,Teacher.class);
    
   }
}

