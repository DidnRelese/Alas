package com.alasdoo.developercourseassignment.service.impl;

import com.alasdoo.developercourseassignment.dto.TeacherDTO;
import com.alasdoo.developercourseassignment.repository.TeacherRepository;
import com.alasdoo.developercourseassignment.service.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.alasdoo.developercourseassignment.entity.Teacher;
import com.alasdoo.developercourseassignment.mapper.TeacherMapper;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class TeacherServiceImpl implements TeacherService {
	@Autowired
	TeacherRepository teacherRepository;
	@Autowired
	TeacherMapper teacherMapper;

	// Method return data for the Teacher based on Id sent form frontend
	public TeacherDTO findOne(Integer id) {
		Optional<Teacher> teacher = teacherRepository.findById(id);
		if (!teacher.isPresent()) {
			throw new IllegalArgumentException(
					"Teacher with the following id = " + id + " is not found.");
		}
		return teacherMapper.transformToDTO(teacher.get());
	}

	public List<TeacherDTO> findAll() {
		return null;
	}
	// Add values of the new teacher to the table
	public TeacherDTO save(TeacherDTO teacherDTO) {
		Teacher teacher = teacherMapper.transformToEntity(teacherDTO);
		return teacherMapper.transformToDTO(teacherRepository.save(teacher));
	}
	// check if there is row in DB with that id
	// If there is not row in db throw exception
	// If there is row remove it
	public void remove(Integer id) throws IllegalArgumentException {
		Optional<Teacher> teacher = teacherRepository.findById(id);
		if (!teacher.isPresent()) {
			throw new IllegalArgumentException(
					"Teacher with the following id = " + id + " is not found.");
		}
		teacherRepository.deleteById(id);
	}

	// Recive the data from the frontend
	// Check if there is row in DB with that id
	// If there is not throw exception
	// If there change values in db with values recived form the frontend (user)
	public TeacherDTO update(Integer id, TeacherDTO teacherDTO) {
		Optional<Teacher> oldTeacher = teacherRepository.findById(id);
		if (!oldTeacher.isPresent()) {
			throw new IllegalArgumentException(
					"Techer with the following id = " + id + " is not found.");
		}

		oldTeacher.get().setId(teacherDTO.getId());
		oldTeacher.get().setTeacherEmail(teacherDTO.getTeacherEmail());
		oldTeacher.get().setTeacherName(teacherDTO.getTeacherName());
		oldTeacher.get().setTeacherSurname(teacherDTO.getTeacherSurname());
		teacherRepository.save(oldTeacher.get());
		return teacherMapper.transformToDTO(oldTeacher.get());
	}
	// Return data of teacher/teachers based on the name and surname sent as
	// parametar
	public TeacherDTO findByTeacherNameAndTeacherSurname(String name,
			String surname) {
		Optional<Teacher> teacher = teacherRepository
				.findByTeacherNameAndTeacherSurname(name, surname);
		if (!teacher.isPresent()) {
			throw new IllegalArgumentException(
					"Teacher with the following name = " + name
							+ " and surename" + surname + " is not found.");
		}
		return teacherMapper.transformToDTO(teacher.get());
	}

	// Return the data of theacher based on email sent as parametar
	public TeacherDTO findByTeacherEmail(String email) {
		Optional<Teacher> teacher = teacherRepository.findByTeacherEmail(email);
		if (!teacher.isPresent()) {
			throw new IllegalArgumentException(
					"Teacher with the following email adress " + email
							+ " is not found.");
		}
		return teacherMapper.transformToDTO(teacher.get());
	}
	//Method need to returns all teachers which are in the DataBase
	public List<TeacherDTO> findAllTeachers(){
		 return teacherRepository.findAll().stream().map(i -> teacherMapper.transformToDTO(i)).collect(Collectors.toList());
	}
}
