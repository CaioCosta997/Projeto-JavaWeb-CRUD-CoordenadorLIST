package controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.CoordenadorDAO;
import dao.CursoDAO;
import dao.PeriodoDAO;
import model.Coordenadores;
import model.Cursos;
import model.Periodos;

/**
 * Servlet implementation class Controller
 */
@WebServlet(urlPatterns = { "/Controller", "/main", "/telaAddCoordenador", "/create", "/editar", "/edite", "/deletar",
		"/addnovo", "/addNovoContatoPeriodo" })
public class Controller extends HttpServlet {

	static {
		CursoDAO cursoDAO = new CursoDAO();
		cursoDAO.salvar(new Cursos(1, "Medicina", "MED"));
		cursoDAO.salvar(new Cursos(2, "Direito", "DIR"));
		cursoDAO.salvar(new Cursos(3, "Engenharia de Software", "BES"));
		cursoDAO.salvar(new Cursos(4, "Analise e Dessenvolvimento de Sistemas", "ADS"));

		PeriodoDAO periodoDAO = new PeriodoDAO();
		periodoDAO.salvar(new Periodos(1, "Segunda", "7:00 as 12:30"));
		periodoDAO.salvar(new Periodos(2, "Terça", "7:00 as 12:30"));
		periodoDAO.salvar(new Periodos(3, "Quarta", "7:00 as 12:30"));
		periodoDAO.salvar(new Periodos(4, "Quinta", "7:00 as 12:30"));
		periodoDAO.salvar(new Periodos(5, "Sexta", "7:00 as 12:30"));

	}

	private static final long serialVersionUID = 1L;

	CoordenadorDAO coordenadorDAO = new CoordenadorDAO();
	CursoDAO cursoDAO = new CursoDAO();
	PeriodoDAO periodoDAO = new PeriodoDAO();

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String action = request.getServletPath();
		System.out.println(action);

		if (action.equals("/main")) {
			acessarListaCoordenador(request, response);
		} else if (action.equals("/telaAddCoordenador")) {
			addCoordenador(request, response);
		} else if (action.equals("/editar")) {
			telaEditarCoordenador(request, response);
		} else if (action.equals("/edite")) {
			editarCoordenador(request, response);
		} else if (action.equals("/deletar")) {
			removerCoordenador(request, response);
		}else {
			acessarListaCoordenador(request, response);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String action = request.getServletPath();
		System.out.println(action);

		if (action.equals("/create")) {

			String nomeCoordenador = request.getParameter("nome");
			String[] cursosSelecionados = request.getParameterValues("cursos");
			String[] periodosSelecionados = request.getParameterValues("periodos");

			ArrayList<Cursos> listaCursos = new ArrayList<>();
			ArrayList<Periodos> listaPeriodos = new ArrayList<>();

			for (String idCurso : cursosSelecionados) {
				listaCursos.add(cursoDAO.buscarPeloId(Integer.parseInt(idCurso)));
			}

			for (String idPeriodo : periodosSelecionados) {
				listaPeriodos.add(periodoDAO.buscarPeloId(Integer.parseInt(idPeriodo)));
			}

			coordenadorDAO.salvar(new Coordenadores(nomeCoordenador, listaCursos, listaPeriodos));

		}

		RequestDispatcher rd = request.getRequestDispatcher("index.jsp");
		rd.forward(request, response);
	}

	protected void acessarListaCoordenador(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		ArrayList<Coordenadores> listaCoordenadores = coordenadorDAO.listar();

		request.setAttribute("listaCoordenadores", listaCoordenadores);

		request.getRequestDispatcher("listacoord.jsp").forward(request, response);
		;
	}

	protected void addCoordenador(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		ArrayList<Cursos> listaCurso = cursoDAO.listar();
		ArrayList<Periodos> listaPeriodo = periodoDAO.listar();

		request.setAttribute("listaCursos", listaCurso);
		request.setAttribute("listaPeriodos", listaPeriodo);

		RequestDispatcher rd = request.getRequestDispatcher("cadastro.jsp");
		rd.forward(request, response);
	}

	protected void telaEditarCoordenador(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		ArrayList<Cursos> listaCursos = cursoDAO.listar();
		ArrayList<Periodos> listaPeriodos = periodoDAO.listar();

		Integer id = Integer.parseInt(request.getParameter("id"));
		Coordenadores coordenador = coordenadorDAO.buscarPeloId(id);
		
		request.setAttribute("coordenador", coordenador);
		request.setAttribute("listaPeriodos", listaPeriodos);
		request.setAttribute("listaCursos", listaCursos);

		
		request.getRequestDispatcher("updateCoor.jsp").forward(request, response);
	}

	protected void editarCoordenador(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		Integer idCoordenador = Integer.parseInt(request.getParameter("id"));
		String nomeCoordenador = request.getParameter("nome");
		String[] cursosSelecionados = request.getParameterValues("cursos");
		String[] periodosSelecionados = request.getParameterValues("periodos");

		ArrayList<Cursos> listaCursos = new ArrayList<>();
		ArrayList<Periodos> listaPeriodos = new ArrayList<>();

		for (String idCurso : cursosSelecionados) {
			listaCursos.add(cursoDAO.buscarPeloId(Integer.parseInt(idCurso)));
		}

		for (String idPeriodo : periodosSelecionados) {
			listaPeriodos.add(periodoDAO.buscarPeloId(Integer.parseInt(idPeriodo)));
		}

		coordenadorDAO.editar(new Coordenadores(nomeCoordenador, listaCursos, listaPeriodos), idCoordenador);

		response.sendRedirect("main");
	}

	protected void removerCoordenador(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		Integer id = Integer.parseInt(request.getParameter("id"));

		coordenadorDAO.deletarPeloId(id);

		response.sendRedirect("main");
	}

}