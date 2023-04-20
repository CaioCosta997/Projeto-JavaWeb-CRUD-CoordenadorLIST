package dao;

import java.util.ArrayList;
import model.Cursos;

public class CursoDAO {
	
	private static ArrayList<Cursos> listaCursos = new ArrayList<>();
	
	public void salvar(Cursos curso)
	{
		listaCursos.add(curso);
	}
	
	public ArrayList<Cursos> listar()
	{
		return listaCursos;
	}
	
	public void deletarPeloId(int id)
	{
		Cursos curso = listaCursos.stream().filter(e -> e.getId() == id).findFirst().get();
		listaCursos.remove(curso);
	}
	
	public Cursos buscarPeloId(int id)
	{
		return listaCursos.stream().filter(e -> e.getId() == id).findFirst().get();
	}

}