package dao;

import java.util.ArrayList;

import model.Periodos;

public class PeriodoDAO {

	private static ArrayList<Periodos> listaPeriodos = new ArrayList<>();
	
	
	public void salvar(Periodos periodo)
	{
		listaPeriodos.add(periodo);
	}
	
	public ArrayList<Periodos> listar()
	{
		return listaPeriodos;
	}
	
	public void deletarPeloId(int id)
	{
		Periodos periodo = listaPeriodos.stream().filter(e -> e.getId() == id).findFirst().get();
		listaPeriodos.remove(periodo);
	}
	
	public Periodos buscarPeloId(int id)
	{
		return listaPeriodos.stream().filter(e -> e.getId() == id).findFirst().get();
	}
}