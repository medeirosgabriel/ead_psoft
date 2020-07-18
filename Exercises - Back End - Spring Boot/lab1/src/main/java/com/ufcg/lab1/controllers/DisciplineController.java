package com.ufcg.lab1.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ufcg.lab1.entities.Discipline;
import com.ufcg.lab1.services.DisciplineService;

@RestController
public class DisciplineController {
	
	@Autowired
	private DisciplineService ds;
	
	@PostMapping("/v1/api/disciplines")
	public ResponseEntity<Discipline> saveDiscipline(@RequestBody Discipline d) {
		return new ResponseEntity<Discipline>(ds.save(d.getName(), d.getGrade()), HttpStatus.OK);
	}
	
	@GetMapping("/v1/api/disciplines")
	public ResponseEntity<Discipline[]> getDisciplines() {
		return new ResponseEntity<Discipline[]>(ds.getDisciplines(), HttpStatus.OK);
	}
	
	@GetMapping("/v1/api/disciplines/{id}")
	public ResponseEntity<Discipline> getDiscipline(@PathVariable int id) {
		Discipline d = ds.getDiscipline(id);
		if (d != null) {
			return new ResponseEntity<Discipline>(ds.getDiscipline(id), HttpStatus.OK);
		}else {
			return ResponseEntity.notFound().build(); // 404
		}
	}
	
	@PutMapping("/v1/api/disciplines/{id}/name")
	public ResponseEntity<Discipline> updateName(@PathVariable int id, @RequestBody String name) throws JsonMappingException, JsonProcessingException {
		ObjectMapper mapper = new ObjectMapper();
		JsonNode json = mapper.readTree(name);
		Discipline d = ds.updateName(id, json.get("name").asText());
		if (d != null) {
			return new ResponseEntity<Discipline>(d, HttpStatus.OK);
		}else {
			return ResponseEntity.notFound().build(); // 404
		}
	}
	
	@PutMapping("/v1/api/disciplines/{id}/grade")
	public ResponseEntity<Discipline> updateGrade(@PathVariable int id, @RequestBody int grade) {
		Discipline d = ds.updateGrade(id, grade);
		if (d != null) {
			return new ResponseEntity<Discipline>(d, HttpStatus.OK);
		}else {
			return ResponseEntity.notFound().build(); // 404
		}
	}
	
	@GetMapping("/v1/api/disciplines/ranking")
	public ResponseEntity<Discipline[]> getSorted() {
		return new ResponseEntity<Discipline[]>(ds.getSortedList(), HttpStatus.OK);
	}
	
	@DeleteMapping("/v1/api/disciplines/{id}")
	public ResponseEntity<Discipline> deleteDiscipline(@PathVariable int id) {
		Discipline d = ds.deleteDiscipline(id);
		if (d != null) {
			return new ResponseEntity<Discipline>(d, HttpStatus.OK);
		}else {
			return ResponseEntity.notFound().build(); // 404
		}
	}
}
