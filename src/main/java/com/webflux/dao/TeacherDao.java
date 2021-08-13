package com.webflux.dao;

import org.springframework.stereotype.Component;

import com.webflux.dto.Teacher;

import reactor.core.publisher.Flux;

import java.time.Duration;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Component
public class TeacherDao {
	
	//By using this a user a user wait for the process count
	//and not getting any value on the local host.
	
	private static void sleepExecution(int i){
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public List<Teacher> getTeacher()  {
       // wants to return 50 object then we
      // peek is used to print the process count the time 
      // for each count 
    	return  IntStream.rangeClosed(1, 10)
                .peek(TeacherDao::sleepExecution)
                .peek(i -> System.out.println("processing count : " + i))
                .mapToObj(i -> new Teacher(i,"teacher"+i))
                .collect(Collectors.toList());
    }


    public Flux<Teacher> getTeachersStream()  {
    	//
        return Flux.range(1,10)
        		// delay is used as sleep
                .delayElements(Duration.ofSeconds(1))
                .doOnNext(i -> System.out.println("processing count in stream flow : " + i))
                .map(i -> new Teacher());
    }


    public Flux<Teacher> getTeacherList()  {
        return Flux.range(1,50)
                .doOnNext(i -> System.out.println("processing count in stream flow : " + i))
                .map(i -> new Teacher());
    }



}
