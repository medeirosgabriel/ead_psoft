package com.ufcg.lab2.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ufcg.lab2.entities.Discipline;
import com.ufcg.lab2.services.DisciplineService;

@RestController
public class DisciplineController {
	
	@Autowired
	private DisciplineService ds;
	
	public DisciplineController(DisciplineService ds) {
		super();
		this.ds = ds;
	}

	@GetMapping("/api/disciplines")
	public ResponseEntity<List<Discipline>> saveDiscipline() {
		return new ResponseEntity<List<Discipline>>(ds.getDisciplines(), HttpStatus.OK);
	}
	
	@GetMapping("/api/disciplines/{id}")
	public ResponseEntity<Discipline> getDisciplineById(@PathVariable long id) {
		Optional<Discipline> d = ds.getDisciplineById(id);
		if (d.isPresent()) { 
			return new ResponseEntity<Discipline>(d.get(), HttpStatus.OK);
		}else {
			return new ResponseEntity<Discipline>(HttpStatus.NOT_FOUND);
		}
	}
	
	@PutMapping("/api/disciplines/likes/{id}")
	public ResponseEntity<Discipline> incrementLike(@PathVariable long id) {
		Optional<Discipline> d = ds.incrementLike(id);
		if (d.isPresent()) { 
			return new ResponseEntity<Discipline>(d.get(), HttpStatus.OK);
		}else {
			return new ResponseEntity<Discipline>(HttpStatus.NOT_FOUND);
		}
	}
	
	// JSON parse error: Cannot deserialize instance of `double` out of START_OBJECT token
	
	/*@PutMapping("/api/disciplines/grade/{id}")
	public ResponseEntity<Discipline> changeGrade(@PathVariable long id, @RequestBody double grade) {
		Optional<Discipline> d = ds.changeGrade(id, grade);
		if (d.isPresent()) {
			return new ResponseEntity<Discipline>(d.get(), HttpStatus.OK);
		}else {
			return new ResponseEntity<Discipline>(HttpStatus.NOT_FOUND);
		}
	}*/
	
	@PutMapping("/api/disciplines/coments/{id}")
	public ResponseEntity<Discipline> addComent(@PathVariable long id, @RequestBody String coment) throws JsonMappingException, JsonProcessingException {
		ObjectMapper mapper = new ObjectMapper();
		JsonNode json = mapper.readTree(coment);
		Optional<Discipline> d = ds.addComent(id, json.get("coment").asText());
		if (d.isPresent()) {
			return new ResponseEntity<Discipline>(d.get(), HttpStatus.OK);
		}else {
			return new ResponseEntity<Discipline>(HttpStatus.NOT_FOUND);
		}
	}
	
	@GetMapping("api/disciplines/ranking/likes")
	public ResponseEntity<List<Discipline>> getOrderedLikes() {
		return new ResponseEntity<List<Discipline>>(ds.getOrderedLikes(), HttpStatus.OK);
	}
	
	@GetMapping("/api/disciplines/ranking/grades")
	public ResponseEntity<List<Discipline>> getOrderedGrades() {
		return new ResponseEntity<List<Discipline>>(ds.getOrderedGrades(), HttpStatus.OK);
	}
	
	
}
