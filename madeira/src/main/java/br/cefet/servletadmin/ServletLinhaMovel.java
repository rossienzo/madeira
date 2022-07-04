package br.cefet.servletadmin;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.cefet.dao.LinhaMovelDAO;
import br.cefet.dao.MovelDAO;
import br.cefet.model.LinhaMovel;
import br.cefet.utilities.Message;

@WebServlet("/ServletLinhaMovel")
public class ServletLinhaMovel extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public ServletLinhaMovel() {
        super();

    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String acao = request.getParameter("acao") != null ? request.getParameter("acao") : "";

		if(acao.equals("buscar")) 
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
		
		// insere um móvel
		if(acao.equals("inserir")) {
			this.inserir(request, response);
		}
		// atualiza um móvel
		else {
			int id = Integer.parseInt(request.getParameter("linhaMovelId"));
			String nome = request.getParameter("linhaMovelNome");
			String descricao = request.getParameter("linhaMovelDescricao");
			Message msg = new Message();
			
			try {
				LinhaMovelDAO lMovelDAO = new LinhaMovelDAO();
				
				
				if(nome.length() < 3)
					throw new Exception("Erro: nome inválido!");
				if(descricao.length() < 3)
					throw new Exception("Erro: descricao inválida!");
				
				LinhaMovel lMovel = new LinhaMovel(id, nome, descricao);
				
				if(lMovelDAO.editar(lMovel))
					msg.defineMessage("success", "Movel atualizado com sucesso!");
				else
					throw new Exception("Erro: não foi possível editar a linha móvel!");
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
		String nome = request.getParameter("linhaMovelNome");
		String descricao = request.getParameter("linhaMovelDescricao");
		
		LinhaMovel lMovel = null;
		LinhaMovelDAO lMovelDAO = new LinhaMovelDAO();
		Message msg = new Message();
	
		try {
			lMovel = new LinhaMovel();
			lMovel.setNome(nome);
			lMovel.setDescricao(descricao);
			
			if(nome.length() < 3)
				throw new Exception("Erro: nome inválido!");
			if(descricao.length() < 3)
				throw new Exception("Erro: descricao inválida!");
			if(lMovelDAO.verificaSeExiste(nome))
				throw new Exception("Erro: linha móvel já cadastrada!");
			if(lMovelDAO.registrar(lMovel))
				msg.defineMessage("success", "Linha Móvel criada com sucesso!");
			else
				throw new Exception("Erro: Erro ao criar linha móvel!");
		} catch(Exception e) {
			msg.defineMessage("error", e.getMessage());
		}
		request.setAttribute("msg", msg);
		RequestDispatcher rd = request.getRequestDispatcher("view/admin/pages/formCreateLinhaMovel.jsp");
		rd.forward(request, response);
	}
	
	/*
	 *	SELECT - lista todos os móveis
	 */
	protected void listar(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List<LinhaMovel> lMoveis = null;
		Message msg = new Message();
		LinhaMovelDAO lmDAO = new LinhaMovelDAO();
		
		try {
			lMoveis = lmDAO.listarTodos();
		} catch (SQLException e) {
			msg.defineMessage("error", e.getMessage());
		}

		request.setAttribute("lMoveis", lMoveis);
		RequestDispatcher rd = request.getRequestDispatcher("view/admin/pages/listarLinhaMovel.jsp");
		rd.forward(request, response);
	}
	
	/*
	 *	SEARCH - busca um móvel pelo nome
	 */
	protected void buscar(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String buscar = request.getParameter("buscar") != null ? request.getParameter("buscar") : "";
		Message msg = new Message();
		LinhaMovelDAO lMovelDAO = new LinhaMovelDAO();
		List<LinhaMovel> lMoveis = null;
		
		try {
			lMoveis = lMovelDAO.pesquisa(buscar);
		} catch (SQLException e) {
			msg.defineMessage("error", e.getMessage());
		}

		request.setAttribute("lMoveis", lMoveis);
		RequestDispatcher rd = request.getRequestDispatcher("view/admin/pages/listarLinhaMovel.jsp");
		rd.forward(request, response);
	}
	
	/*
	 *	UPDATE - retorna o item a ser editado
	 */
	protected void editar(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// verifica se está recebendo o parâmetro id, se não, coloca um número inválido
		int id = request.getParameter("id") != null ? Integer.parseInt(request.getParameter("id")) : -1;
		Message msg = new Message();
		LinhaMovelDAO lMovelDAO = new LinhaMovelDAO();
		
		LinhaMovel lMovel = null;
		String nextPage = "/ServletLogin";
		
		try {
			if(id < 0)
				throw new Exception("Erro: id inválido");
			lMovel = new LinhaMovel();
			lMovel = lMovelDAO.listarUm(id);
			nextPage = "view/admin/pages/formAtualizarLinhaMovel.jsp";
			
		} catch (Exception e) {
			msg.defineMessage("error", e.getMessage());
			response.sendRedirect("localhost:9090/madeira/view/admin/pages/ServletLinhaMovel");
		}
		
		request.setAttribute("lMovel", lMovel);
		RequestDispatcher rd = request.getRequestDispatcher(nextPage);
		rd.forward(request, response);
	}
	
	/*
	 *	DELETE 
	 */
	protected void deletar(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int id = Integer.parseInt(request.getParameter("id"));
		
		LinhaMovelDAO lMovelDAO = new LinhaMovelDAO();
		Message msg = new Message();
		
		try {
			if(lMovelDAO.deletar(id))
				msg.defineMessage("success", "Linha Movel removida com sucesso!");
			else
				throw new SQLException("Erro: esta linha móvel pode estar associada a algum móvel"
						+ " ou não existe elemento associado ao id. Remova o móvel e tente novamente.");
		} catch (SQLException e) {
			msg.defineMessage("error", e.getMessage());
		}
		 request.setAttribute("msg", msg);
		 this.listar(request, response);
	}
}
