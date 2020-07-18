package com.ufcg.lab1.services;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.ufcg.lab1.entities.Discipline;


@Service
public class DisciplineService {
	
	private Map<Integer, Discipline> disciplines = new HashMap<Integer, Discipline>();
	private int id = 1;
	
	public Discipline save(String name, double grade) {
		if (!disciplines.containsKey(id)) {
			Discipline newDiscipline = new Discipline(id, name, grade);
			disciplines.put(id, newDiscipline);
			id++;
			return newDiscipline;
		}
		return null;
	}
	
	public Discipline[] getDisciplines() {
		Discipline[] discArray = new Discipline[disciplines.size()];
		int i = 0;
		for (Discipline d : disciplines.values()) {
			discArray[i] = d;
			i++;
		}
		return discArray;
	}
	
	public Discipline getDiscipline(int id) {
		if (disciplines.containsKey(id)) {
			return disciplines.get(id);
		} else {
			return null;
		}
	}
	
	public Discipline updateName(int id, String name) {
		if (disciplines.containsKey(id)) {
			disciplines.get(id).setName(name);
			return disciplines.get(id);
		} else {
			return null;
		}
	}
	
	public Discipline updateGrade(int id, double grade) {
		if (disciplines.containsKey(id)) {
			disciplines.get(id).setGrade(grade);
			return disciplines.get(id);
		} else {
			return null;
		}
	}
	
	public Discipline deleteDiscipline(int id) {
		if (disciplines.containsKey(id)) {
			Discipline rmDiscipline = disciplines.get(id);
			disciplines.remove(id);
			return rmDiscipline;
		} else {
			return null;
		}
	}
	
	public Discipline[] getSortedList() {
		Discipline[] discArray = new Discipline[disciplines.size()];
		int i = 0;
		for (Discipline d : disciplines.values()) {
			discArray[i] = d;
			i++;
		}
		Arrays.sort(discArray);
		return discArray;
	}
}
