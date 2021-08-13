package com.webflux.router;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

import com.webflux.handler.TeacherHandler;
import com.webflux.handler.TeacherStreamHandler;

@Configuration
public class RouterConfig {

    @Autowired
    private TeacherHandler handler;

    @Autowired
    private TeacherStreamHandler streamHandler;

    /**
     * router Function is a return type 
     * @return
     */
    @Bean
    public RouterFunction<ServerResponse> routerFunction(){
        return RouterFunctions.route()
                .GET("/router/teacher",handler::loadTeacher)
                .GET("/router/teacher/stream",streamHandler::getTeachers)
                .GET("/router/teacher/{input}",handler::findTeacher)
                .POST("/router/teacher/save",handler::saveTeacher)
                .build();

    }
}
