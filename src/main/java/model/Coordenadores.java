package model;

import java.util.ArrayList;

public class Coordenadores {
	
	private static Integer lastId = 0;
	
	private Integer id;

	private String nome;

	private ArrayList<Cursos> cursos;

	private ArrayList<Periodos> periodos;

	public Coordenadores() {
	
	}
	
	public Coordenadores(String nome, ArrayList<Cursos> cursos, ArrayList<Periodos> periodos) {
		this.id = lastId++;
		this.nome = nome;
		this.cursos = cursos;
		this.periodos = periodos;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public ArrayList<Cursos> getCursos() {
		return cursos;
	}

	public void setCursos(ArrayList<Cursos> cursos) {
		this.cursos = cursos;
	}

	public ArrayList<Periodos> getPeriodos() {
		return periodos;
	}

	public void setPeriodos(ArrayList<Periodos> periodos) {
		this.periodos = periodos;
	}

	

}