package br.cefet.servletadmin;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.cefet.dao.EncomendaDAO;
import br.cefet.dao.LinhaMovelDAO;
import br.cefet.dao.MovelDAO;
import br.cefet.model.LinhaMovel;
import br.cefet.model.Movel;
import br.cefet.utilities.Message;

@WebServlet("/ServletMovel")
public class ServletMovel extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public ServletMovel() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String acao = request.getParameter("acao") != null ? request.getParameter("acao") : "";

		if(acao.equals("inserir"))
			this.inserir(request, response);
		else if(acao.equals("buscar")) 
			this.buscar(request, response);
		else if(acao.equals("editar"))
			this.editar(request, response);
		else if(acao.equals("deletar"))
			this.deletar(request, response);
		else 
			this.listar(request, response);
	}
	
	/*
	 *	CREATE/UPDATE 
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String acao = request.getParameter("acao") != null ? request.getParameter("acao") : "";
		Message msg = new Message();
		
		// insere um móvel
		if(acao.equals("inserir")) {
			this.inserir(request, response);
			this.listar(request, response);
		}
		// atualiza um móvel
		else {
			int id = Integer.parseInt(request.getParameter("movelId"));
			String nome = request.getParameter("movelName");
			String modelo = request.getParameter("movelModelo");
			String descricao = request.getParameter("movelDescricao");
			String preco = request.getParameter("movelPreco");
			int idLinhaMovel = Integer.parseInt(request.getParameter("movelLinha"));
			
			try {
				MovelDAO mDAO = new MovelDAO();
				
				if(nome.length() < 3)
					throw new Exception("Erro: nome inválido!");
				if(modelo.length() < 3)
					throw new Exception("Erro: modelo inválido!");
				if(preco.length() < 1)
					throw new Exception("Erro: preço inválido!");
				if(idLinhaMovel < 0)
					throw new Exception("Erro: Linha móvel vazia");
				
				Movel movel = new Movel(id, nome, modelo, descricao, Double.parseDouble(preco));
				LinhaMovel linhaMovel = new LinhaMovel();
				linhaMovel.setId(idLinhaMovel);
				movel.setLinhaMovel(linhaMovel);
				
				if(mDAO.editar(movel))
					msg.defineMessage("success", "Movel atualizado com sucesso!");
				else
					throw new Exception("Erro: não foi possível remover o móvel!");
			} catch (Exception e) {
				msg.defineMessage("error", e.getMessage());
			}
			
			request.setAttribute("msg", msg);
			this.listar(request, response);
		}
	}
	
	/*
	 *	CREATE - insere um móvel
	 */
	protected void inserir(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String nome = request.getParameter("movelName");
		String modelo = request.getParameter("movelModelo");
		String descricao = request.getParameter("movelDescricao");
		String preco = request.getParameter("movelPreco");
		String idMovelLinha = request.getParameter("movelLinha");
		Message msg = new Message();
		MovelDAO mDAO = new MovelDAO();
		Movel movel = null;

		try {
			if(nome.length() < 3)
				throw new Exception("Erro: nome inválido!");
			if(modelo.length() < 3)
				throw new Exception("Erro: modelo inválido!");
			if(preco.length() < 1)
				throw new Exception("Erro: preço inválido!");
			if(idMovelLinha == null)
				throw new Exception("Erro: Linha móvel vazia");
			
			movel = new Movel(nome, modelo, descricao, Double.parseDouble(preco));
			LinhaMovel lm = new LinhaMovel();
			lm.setId(Integer.parseInt(idMovelLinha));
			movel.setLinhaMovel(lm);
			
			if(mDAO.registrar(movel))
				msg.defineMessage("success", "Movel inserido com sucesso!");
			else
				throw new Exception("Erro: não foi possível inserir o móvel");
		} catch (Exception e) {
			msg.defineMessage("error", e.getMessage());
		}
		
		request.setAttribute("msg", msg);
	}
	
	/*
	 *	SELECT - lista todos os móveis
	 */
	protected void listar(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List<Movel> moveis = new ArrayList<Movel>();
		
		MovelDAO mDAO = new MovelDAO();
		Message msg = new Message();
		
		try {
			moveis = mDAO.listarTodos();
		} catch (SQLException e) {
			msg.defineMessage("error", e.getMessage());
		}
		
		request.setAttribute("moveis", moveis);
		RequestDispatcher rd = request.getRequestDispatcher("/view/admin/pages/listarMovel.jsp");
		rd.forward(request, response);
	}
	
	
	/*
	 *	SEARCH - busca um móvel pelo nome
	 */
	protected void buscar(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String buscar = request.getParameter("buscar") != null ? request.getParameter("buscar") : "";
		MovelDAO mDAO = new MovelDAO();
		Message msg = new Message();
		List<Movel> moveis = null;
		
		try {
			moveis = mDAO.pesquisa(buscar);
		} catch (SQLException e) {
			msg.defineMessage("error", e.getMessage());
		}

		request.setAttribute("moveis", moveis);
		RequestDispatcher rd = request.getRequestDispatcher("view/admin/pages/listarMovel.jsp");
		rd.forward(request, response);
	}
	
	/*
	 *	UPDATE - retorna o item a ser editado
	 */
	protected void editar(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// verifica se está recebendo o parâmetro id, se não, coloca um número inválido
		int id = request.getParameter("id") != null ? Integer.parseInt(request.getParameter("id")) : -1;
		Message msg = new Message();
		List<LinhaMovel> linhasMoveis = null;
		Movel movel = null;
		String nextPage = "view/admin/pages/ServletMovel";
		
		try {
			MovelDAO mDAO = new MovelDAO();
			LinhaMovelDAO lMovelDAO = new LinhaMovelDAO();
			
			// se o id for válido, lista todas as linhas móveis e os dados do elemento a ser editado
			if(id < 0)
				throw new Exception("Erro: id inválido");
			
			movel = new Movel();
			linhasMoveis = lMovelDAO.listarTodos();
			movel = mDAO.listarUm(id);
			nextPage = "view/admin/pages/formAtualizarMovel.jsp";
			
		} catch (Exception e) {
			msg.defineMessage("error", e.getMessage());
			response.sendRedirect("localhost:9090/madeira/view/admin/pages/ServletMovel");
		}
		
		request.setAttribute("linhasMoveis", linhasMoveis);
		request.setAttribute("movel", movel);
		RequestDispatcher rd = request.getRequestDispatcher(nextPage);
		rd.forward(request, response);
	}
	
	/*
	 *	DELETE 
	 */
	protected void deletar(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int id = Integer.parseInt(request.getParameter("id"));
		
		MovelDAO mDAO = new MovelDAO();

		Message msg = new Message();
		
		try {
			if(mDAO.deletar(id))
				msg.defineMessage("success", "Movel removido com sucesso!");
			else
				throw new SQLException("Erro: este móvel pode estar associada a alguma encomenda"
						+ " ou não existe elemento associado ao id. Remova a encomenda e tente novamente.");
		} catch (SQLException e) {
			msg.defineMessage("error", e.getMessage());
		}
		
		 request.setAttribute("msg", msg);
		 this.listar(request, response);
	}
}
