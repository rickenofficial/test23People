package com.rickenofficial.app.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import com.rickenofficial.app.entity.Student;
import com.rickenofficial.app.repository.StudentRepository;

@Service
public class StudentServiceImpl implements StudentService {
	@Autowired
	private StudentRepository studentRepository;

	@Override
	public Iterable<Student> findAll() {

		return studentRepository.findAll();
	}

	@Override

	public Page<Student> findAll(Pageable pageable) {

		return studentRepository.findAll(pageable);
	}

	@Override

	public Optional<Student> findById(long id) {

		return studentRepository.findById(id);
	}

	@Override
	public Student save(Student student) {

		return studentRepository.save(student);
	}

	@Override
	public void deleteById(Long id) {
		studentRepository.deleteById(id);

	}

}
