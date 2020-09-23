package com.rickenofficial.app.controller;

import java.util.Collection;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.rickenofficial.app.entity.Course;
import com.rickenofficial.app.service.CourseService;

@Validated
@RestController
@RequestMapping("/courses")
public class CourseController {

	@Autowired
	private CourseService courseService;

	// Create a New Course
	@PostMapping
	public ResponseEntity<?> create(@Valid @RequestBody Course course) {
		return ResponseEntity.status(HttpStatus.CREATED).body(courseService.save(course));
	}

	// Read a Course
	@GetMapping("/{id}")
	public ResponseEntity<?> read(@PathVariable(value = "id") Long courseId) {
		Optional<Course> oCourse = courseService.findById(courseId);

		if (!oCourse.isPresent()) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(oCourse);

	}

	@GetMapping("/all")
	public ResponseEntity<?> readAll() {
		Iterable<Course> oCourse = courseService.findAll();
		if (oCourse instanceof Collection<?>) {
			int courseNumber = ((Collection<?>) oCourse).size();
			if (courseNumber > 0) {
				return ResponseEntity.ok(oCourse);
			}

		}
		return ResponseEntity.notFound().build();
	}

	@GetMapping("/pageCourse")
	public ResponseEntity<?> readAll(@RequestParam(defaultValue="1") int page, @RequestParam(defaultValue="Guest") int size) {
		Pageable paging = PageRequest.of(page-1, size, Sort.by("id"));
		return ResponseEntity.ok(courseService.findAll(paging).getContent());
	}

	// Update a Course
	@PutMapping("/{id}")
	public ResponseEntity<?> update(@RequestBody Course courseDetails, @PathVariable(value = "id") Long courseId) {
		Optional<Course> course = courseService.findById(courseId);

		if (!course.isPresent()) {
			return ResponseEntity.notFound().build();

		}
		course.get().setCourseName(courseDetails.getCourseName());
		course.get().setCode(courseDetails.getCode());

		return ResponseEntity.status(HttpStatus.CREATED).body(courseService.save(course.get()));

	}

	// delete a Course
	@DeleteMapping("/{id}")
	public ResponseEntity<?> delete(@PathVariable(value = "id") Long courseId) {
		if (!courseService.findById(courseId).isPresent()) {
			return ResponseEntity.notFound().build();
		}
		courseService.deleteById(courseId);
		return ResponseEntity.ok().build();

	}
}
