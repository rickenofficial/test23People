package com.rickenofficial.app.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import com.rickenofficial.app.entity.Course;
import com.rickenofficial.app.repository.CourseRepository;

@Service
public class CourseServiceImpl implements CourseService{
	@Autowired
	private CourseRepository courseRepository;
	
	@Override
	
	public Iterable<Course> findAll(){
		return courseRepository.findAll();
	}
	@Override
	public Page<Course> findAll(Pageable pageable){
		return courseRepository.findAll(pageable);
	}
	@Override
	
	public Optional<Course> findById(long id){
		return courseRepository.findById(id);
	}
	
	@Override
	public Course save(Course course) {
			return courseRepository.save(course);
	}
	@Override
	public void deleteById(Long id) {
		courseRepository.deleteById(id);
	}
}
