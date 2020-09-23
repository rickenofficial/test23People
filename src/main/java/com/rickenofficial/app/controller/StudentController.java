package com.rickenofficial.app.controller;

import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rickenofficial.app.entity.Student;
import com.rickenofficial.app.service.StudentService;

@RestController
@RequestMapping("/student")
public class StudentController {
	@Autowired
	private StudentService studentService;
	
	//Create a New Student
	@PostMapping
	public ResponseEntity<?> create (@Valid @RequestBody  Student student){
		return ResponseEntity.status(HttpStatus.CREATED).body(studentService.save(student));
	}
	
	//Read a Student
	@GetMapping("/{id}")
	public ResponseEntity<?> read (@PathVariable (value ="id")Long studentId) {
		Optional<Student> oStudent = studentService.findById(studentId);
		
		if(!oStudent.isPresent()) {
			return ResponseEntity.notFound().build();
		}
			return ResponseEntity.ok(oStudent);
			
	}
	
	//Update a Student
	@PutMapping("/{id}")
	public ResponseEntity<?> update(@RequestBody Student studentDetails, @PathVariable (value="id") Long studentId ){
		Optional<Student> student = studentService.findById(studentId);
		
		if(!student.isPresent()) {
			return ResponseEntity.notFound().build();
					
		}
		student.get().setName(studentDetails.getName());
		student.get().setLastName(studentDetails.getLastName());
//		student.get().setCourses(studentDetails.getCourses());
		student.get().setAge(studentDetails.getAge());
		student.get().setRut(studentDetails.getRut());
		student.get().setEnabled(studentDetails.getEnabled());
		
		return ResponseEntity.status(HttpStatus.CREATED).body(studentService.save(student.get()));
		
		}
	//delete a Student
	@DeleteMapping("/{id}")
	public ResponseEntity<?> delete (@PathVariable(value ="id")Long studentId){
		if (!studentService.findById(studentId).isPresent()) {
			return ResponseEntity.notFound().build();
		}
		studentService.deleteById(studentId);
		return ResponseEntity.ok().build();
		
	}
}
