package com.example.graphql.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import com.example.graphql.model.Department;
import com.example.graphql.model.Student;
import com.example.graphql.service.StudentService;

@Controller
public class StudentController {

	private StudentService studentService;
	
	public StudentController(StudentService studentService){
		this.studentService = studentService;
	}
	
	@QueryMapping
	public List<Student> findAll(){
		return studentService.findAll();
	}
	
	@QueryMapping
	public Optional<Student> findOne(@Argument int id){
		return studentService.findOne(id);
	}
	
	@MutationMapping
	public Student create(@Argument String name, @Argument Department department) {
		return studentService.create(name, department);
	}
	
	@MutationMapping
	public Student update(@Argument int id,@Argument String name,@Argument Department department) {
		return studentService.update(id, name, department);
	}
	
	@MutationMapping
	public Student delete(@Argument int id) {
		return studentService.delete(id);
	}
}
