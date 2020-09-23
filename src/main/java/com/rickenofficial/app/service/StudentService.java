package com.rickenofficial.app.service;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.rickenofficial.app.entity.Student;



public interface StudentService {

	public Iterable<Student> findAll();

	public Page<Student> findAll(Pageable pageable);

	public Optional<Student> findById(long id);

	public Student save(Student student);

	public void deleteById(Long id);

}
