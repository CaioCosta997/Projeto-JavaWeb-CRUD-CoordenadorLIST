package dao;

import java.util.ArrayList;

import model.Coordenadores;

public class CoordenadorDAO {
	
	private static ArrayList<Coordenadores> listaCoord = new ArrayList<>();

	public void salvar(Coordenadores coord)
	{
		listaCoord.add(coord);
	}
	
	public ArrayList<Coordenadores> listar()
	{
		return listaCoord;
	}
	
	public void deletarPeloId(int id)
	{
		Coordenadores cord = listaCoord.stream().filter(e -> e.getId() == id).findFirst().get();
		listaCoord.remove(cord);
	}
	
	public Coordenadores buscarPeloId(int id)
	{
		return listaCoord.stream().filter(e -> e.getId() == id).findFirst().get();
	}
	
	public void editar(Coordenadores coordenador, int idCoordenador)
	{
		listaCoord.stream().filter(e -> e.getId() == idCoordenador).findFirst().map(coordToUpdate -> {

            int indice = listaCoord.indexOf(coordToUpdate);
            
            coordToUpdate.setNome(coordenador.getNome());
            coordToUpdate.setCursos(coordenador.getCursos());
            coordToUpdate.setPeriodos(coordenador.getPeriodos());

            listaCoord.set(indice, coordToUpdate);
            return null;
        }).orElse(coordenador);
	}
}