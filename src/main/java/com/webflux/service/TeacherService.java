package com.webflux.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.webflux.dao.TeacherDao;
import com.webflux.dto.Teacher;

import reactor.core.publisher.Flux;

import java.util.List;

@Service
public class TeacherService {

    @Autowired
    private TeacherDao teacherdao;

	public List<Teacher> loadAllTeacher() {
		// printing execution time 
		 long start = System.currentTimeMillis();
		 List<Teacher> teachers = teacherdao.getTeacher();
		 long end = System.currentTimeMillis();
		 // time difference 
		 System.out.println("Total execution time : " + (end - start));
		return teachers;
	}



	public Flux<Teacher> loadAllTeacherStream() {
		long start = System.currentTimeMillis();
        Flux<Teacher> teachers = teacherdao.getTeachersStream();
        long end = System.currentTimeMillis();
        System.out.println("Total execution time : " + (end - start));
        return teachers;
	}
}