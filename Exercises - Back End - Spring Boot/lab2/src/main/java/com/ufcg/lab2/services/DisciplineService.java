package com.ufcg.lab2.services;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sun.el.stream.Stream;
import com.ufcg.lab2.daos.DisciplineRepository;
import com.ufcg.lab2.entities.Discipline;

@Service
public class DisciplineService {
	
	@Autowired
	private DisciplineRepository<Discipline, Long> dr;
	
	@PostConstruct
	public void initDisciplines() {
		ObjectMapper mapper = new ObjectMapper();
		TypeReference<List<Discipline>> typeReference = new TypeReference<List<Discipline>>() {};
		InputStream inputStream = ObjectMapper.class.getResourceAsStream("/json/disciplines.json");
		try {
			List<Discipline> disciplines = mapper.readValue(inputStream, typeReference);
			this.dr.saveAll(disciplines);
			System.out.println("The disciplines were saved");
		} catch (IOException e) {
			System.out.println("It was not possible save the disciplines. ERROR: " + e.getMessage());
		}
	}
	
	public List<Discipline> getDisciplines() {
		return dr.findAll();
	}
	
	public Optional<Discipline> getDisciplineById(@PathVariable long id) {
		return dr.findById(id);
	}

	public Optional<Discipline> incrementLike(long id) {
		Optional<Discipline> d = dr.findById(id);
		if (d.isPresent()) {
			int likes = d.get().getLikes();
			d.get().setLikes(++likes);
			dr.save(d.get());
		}
		return d;
	}
	
	public Optional<Discipline> changeGrade(long id, double grade) {
		Optional<Discipline> d = dr.findById(id);
		if (d.isPresent()) {
			double cgrade = d.get().getGrade();
			d.get().setGrade((cgrade == 0.0) ? grade : (cgrade + grade)/2);
			dr.save(d.get());
		}
		return d;
	}

	public Optional<Discipline> addComent(long id, String coment) {
		Optional<Discipline> d = dr.findById(id);
		if (d.isPresent()) {
			String c = d.get().getComents();
			d.get().setComentarios((c.equals("")) ? coment : c + " | " + coment);
			dr.save(d.get());
		}
		return d;
	}

	public List<Discipline> getOrderedLikes() {
		List<Discipline> list = dr.findAll();
		list = (List<Discipline>) list.stream().sorted((d1, d2) -> (d1.getLikes() < d2.getLikes()) ? 1 : -1).collect(Collectors.toList());
		return list;
	}

	public List<Discipline> getOrderedGrades() {
		List<Discipline> list = dr.findAll();
		list = (List<Discipline>) list.stream().sorted((d1, d2) -> (d1.getGrade() < d2.getGrade()) ? 1 : -1).collect(Collectors.toList());
		return list;
	}
}
