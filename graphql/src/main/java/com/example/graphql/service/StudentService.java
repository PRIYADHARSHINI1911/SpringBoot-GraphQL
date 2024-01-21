package com.example.graphql.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicInteger;

import org.springframework.stereotype.Service;

import com.example.graphql.model.Department;
import com.example.graphql.model.Student;

import jakarta.annotation.PostConstruct;

@Service
public class StudentService {
	
	private List<Student> students = new ArrayList<>();
	
	AtomicInteger id = new AtomicInteger(0);
	
	/**
	 * Method to get all students
	 * @return students
	 */
	public List<Student> findAll(){
		return students;
	}
	
	/**
	 * Method to find a student with id
	 * @param id
	 * @return student
	 */
	public Optional<Student> findOne(int id) {
		return students.stream().filter(student -> student.id() == id).findFirst();
	}
	

	/**
	 * Method to create a student
	 * @param name
	 * @param department
	 * @return student
	 */
	public Student create(String name, Department department) {
		Student student = new Student(id.incrementAndGet(),name, department);
		students.add(student);
		return student;
	}
	
	/**
	 * Method to delete a student
	 * @param id
	 * @return
	 */

	public Student delete(int id) {
		Student student = students
				.stream()
				.filter(stu -> stu.id() == id)
				.findFirst()
				.orElseThrow(() -> new IllegalArgumentException());
		students.remove(student);
		return student;
	}
	
	/**
	 * Method to update the student
	 * @param id
	 * @param name
	 * @param department
	 * @return
	 */
	
	public Student update(int id, String name, Department department) {
	
		Student updatedStudent = new Student(id,name,department);
		Optional<Student> optional = students
				.stream()
				.filter(optionalStudent -> optionalStudent.id() == id)
				.findFirst();
		if(optional.isPresent()) {
			Student student = optional.get();
			int index = students.indexOf(student);
			students.set(index, updatedStudent);
		}
		else {
			throw new IllegalArgumentException("Invalid student");
		}
		
		return updatedStudent;
	}
	
	@PostConstruct
	private void init() {
		students.add(new Student(id.incrementAndGet(),"Joey",Department.CIVIL));
		students.add(new Student(id.incrementAndGet(),"Chandler",Department.CSE));
		students.add(new Student(id.incrementAndGet(),"Ross",Department.MECH));
		students.add(new Student(id.incrementAndGet(),"Rachel",Department.EEE));
		students.add(new Student(id.incrementAndGet(),"Monica",Department.CIVIL));
		students.add(new Student(id.incrementAndGet(),"Phoebe",Department.CIVIL));
	}
}
