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

import br.cefet.dao.ClienteDAO;
import br.cefet.dao.EncomendaDAO;
import br.cefet.dao.ItemEncomendaDAO;
import br.cefet.dao.MovelDAO;
import br.cefet.model.Cliente;
import br.cefet.model.Encomenda;
import br.cefet.model.ItemEncomenda;
import br.cefet.model.Movel;
import br.cefet.utilities.Message;

@WebServlet("/ServletItemEncomenda")
public class ServletItemEncomenda extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public ServletItemEncomenda() {
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
			this.listar(request, response);
		}
		// atualiza um móvel
		else {
			int itemEncomendaId = Integer.parseInt(request.getParameter("itemEncomendaId"));
			int encomendaId = Integer.parseInt(request.getParameter("encomendaId"));
			String strData = request.getParameter("data");
			String strDataPrevista = request.getParameter("dataPrevista");
			String strDataEntrega = request.getParameter("dataEntrega").isBlank() ? null : request.getParameter("dataEntrega");
			String strDataVencimento = request.getParameter("dataVencimento").isBlank() ? null : request.getParameter("dataVencimento");
			String strDataPagamento = request.getParameter("dataPagamento").isBlank() ? null : request.getParameter("dataPagamento");
			String strDataProntidao = request.getParameter("dataProntidao").isBlank() ? null : request.getParameter("dataProntidao");
			String idCliente = request.getParameter("cliente");
			String strQtdEncomendada = request.getParameter("qtdEncomendada");
			int qtdEncomendada = !strQtdEncomendada.isBlank() ? Integer.parseInt(request.getParameter("qtdEncomendada")) : -1;
			String idMovel = request.getParameter("idMovel");
			
			Message msg = new Message();
			
			try {
				ItemEncomendaDAO iEncomendaDAO = new ItemEncomendaDAO(); 
				
				if(strData.isBlank() || strData.isEmpty())
					throw new Exception("Erro: a Data não pode estar vazia!");
				
				if(strDataPrevista.isBlank() || strDataPrevista.isEmpty())
					throw new Exception("Erro: a Data prevista não pode estar vazia!");
				
				if(qtdEncomendada < 1)
					throw new Exception("Erro: Quantidade inválida!");
				
				if(idCliente == null)
					throw new Exception("Erro: O cliente está vazio!");
				
				if(idMovel == null)
					throw new Exception("Erro: O móvel não pode estar vazio!");
				
				ItemEncomenda iEncomenda = new ItemEncomenda(); 
				
				Cliente cliente = new Cliente();
				cliente.setId(Integer.parseInt(idCliente));
				Movel movel = new Movel();
				movel.setId(Integer.parseInt(idMovel));
			
				// Registra os dados da encomenda adicionando o espaço na data
				Encomenda encomenda = new Encomenda (
						formatarData(strData, "bd"), 
						formatarData(strDataPrevista, "bd"),
						formatarData(strDataEntrega, "bd"),
						formatarData(strDataVencimento, "bd"),
						formatarData(strDataPagamento, "bd"),
						formatarData(strDataProntidao, "bd"),
						cliente);
				
				encomenda.setId(encomendaId);
				
				// Salva dados do item encomenda
				iEncomenda.setId(itemEncomendaId);
				iEncomenda.setEncomenda(encomenda);
				iEncomenda.setMovel(movel);
				iEncomenda.setQuantidade(qtdEncomendada);
					
				// registra no BD o item encomenda 
				if(iEncomendaDAO.editar(iEncomenda))
					msg.defineMessage("success", "Encomenda atualizada com sucesso!");							
				else
					throw new Exception("Erro: não foi possível atualizar a encomenda!");

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
		String strData = request.getParameter("data");
		String strDataPrevista = request.getParameter("dataPrevista");
		String strDataEntrega = request.getParameter("dataEntrega").isBlank() ? null : request.getParameter("dataEntrega");
		String strDataVencimento = request.getParameter("dataVencimento").isBlank() ? null : request.getParameter("dataVencimento");
		String strDataPagamento = request.getParameter("dataPagamento").isBlank() ? null : request.getParameter("dataPagamento");
		String strDataProntidao = request.getParameter("dataProntidao").isBlank() ? null : request.getParameter("dataProntidao");
		String idCliente = request.getParameter("cliente");
		String strQtdEncomendada = request.getParameter("qtdEncomendada");
		int qtdEncomendada = !strQtdEncomendada.isBlank() ? Integer.parseInt(request.getParameter("qtdEncomendada")) : -1;
		String idMovel = request.getParameter("idMovel");
		Message msg = new Message();
		
		ItemEncomenda iEncomenda = new ItemEncomenda();
	
		try {

			EncomendaDAO eDAO = new EncomendaDAO();
			ItemEncomendaDAO iEncomendaDAO = new ItemEncomendaDAO();
			
			if(strData.isBlank() || strData.isEmpty())
				throw new Exception("Erro: a Data não pode estar vazia!");
			
			if(strDataPrevista.isBlank() || strDataPrevista.isEmpty())
				throw new Exception("Erro: a Data prevista não pode estar vazia!");
			
			if(qtdEncomendada < 1)
				throw new Exception("Erro: Quantidade inválida!");

			if(idCliente == null)
				throw new Exception("Erro: O cliente está vazio!");
			
			if(idMovel == null)
				throw new Exception("Erro: O móvel não pode estar vazio!");
			
			Cliente cliente = new Cliente();
			cliente.setId(Integer.parseInt(idCliente));
			Movel movel = new Movel();
			movel.setId(Integer.parseInt(idMovel));
			
			Encomenda encomenda = new Encomenda(strData, strDataPrevista, strDataEntrega, strDataVencimento, 
					strDataPagamento, strDataProntidao, cliente);
			iEncomenda.setEncomenda(encomenda);
			
			iEncomenda.setMovel(movel);
			iEncomenda.setQuantidade(qtdEncomendada);
			
			// registra no BD a encomenda
			if(eDAO.registrar(encomenda)) {				
				
				// pega o id da ultima encomenda gerada 
				int id = eDAO.lastId();
				encomenda.setId(id);
				
				// registra no BD o item encomenda 
				if(iEncomendaDAO.registrar(iEncomenda))
					msg.defineMessage("success", "Encomenda inserida com sucesso!");				
				else
					throw new Exception("Erro: não foi possível inserir o Item encomenda");
			} else
				throw new Exception("Erro: não foi possível inserir a encomenda");			

		} catch (Exception e) {
			msg.defineMessage("error", e.getMessage());
		}
		
		request.setAttribute("msg", msg);
	}

	/*
	 *	SELECT - lista todos os móveis
	 */
	protected void listar(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		List<ItemEncomenda> iEncomendas = new ArrayList<ItemEncomenda>();
		ItemEncomendaDAO iEncomendaDAO = new ItemEncomendaDAO();
		Message msg = new Message();
		
		try {
			//encomendas = eDAO.listarTodos();
			iEncomendas = iEncomendaDAO.listarTodos();
			
		} catch (SQLException e) {
			msg.defineMessage("error", e.getMessage());
		}
		
		//request.setAttribute("encomendas", encomendas);
		request.setAttribute("iEncomendas", iEncomendas);
		RequestDispatcher rd = request.getRequestDispatcher("view/admin/pages/listarEncomenda.jsp");
		rd.forward(request, response);
	}
	
	/*
	 *	SEARCH - busca um móvel pelo nome
	 */
	protected void buscar(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String buscar = request.getParameter("buscar") != null ? request.getParameter("buscar") : "";
		ItemEncomendaDAO iEncomendaDAO = new ItemEncomendaDAO(); 
		Message msg = new Message();
		List<ItemEncomenda> iEncomendas = null;
		
		try {
			iEncomendas = iEncomendaDAO.pesquisa(buscar);
		} catch (SQLException e) {
			msg.defineMessage("error", e.getMessage());
		}

		request.setAttribute("iEncomendas", iEncomendas);
		RequestDispatcher rd = request.getRequestDispatcher("view/admin/pages/listarEncomenda.jsp");
		rd.forward(request, response);
	}

	/*
	 *	UPDATE - retorna o item a ser editado
	 */
	protected void editar(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// verifica se está recebendo o parâmetro id, se não, coloca um número inválido
		int id = request.getParameter("id") != null ? Integer.parseInt(request.getParameter("id")) : -1;
		Message msg = new Message();
		
		List<Cliente> clientes = null;
		List<Movel> moveis = null;
		
		Encomenda encomenda = null;
		ItemEncomenda iEncomenda = null;
		String nextPage = "view/admin/pages/ServletItemEncomenda";
		
		try {
			ClienteDAO cDAO = new ClienteDAO();
			MovelDAO mDAO = new MovelDAO();
			ItemEncomendaDAO iEncomendaDAO = new ItemEncomendaDAO();
			
			// se o id for válido, lista todas as linhas móveis e os dados do elemento a ser editado
			if(id < 0)
				throw new Exception("Erro: id inválido");

			iEncomenda = iEncomendaDAO.listarUm(id);
			clientes = cDAO.listarTodos();
			moveis = mDAO.listarTodos();
			
			encomenda = new Encomenda();
			encomenda.setId(iEncomenda.getEncomenda().getId());
			encomenda.setData(formatarData(iEncomenda.getEncomenda().getData(), "html"));
			encomenda.setDataEntrega(formatarData(iEncomenda.getEncomenda().getDataEntrega(), "html"));
			encomenda.setDataPagamento(formatarData(iEncomenda.getEncomenda().getDataPagamento(), "html"));
			encomenda.setDataPrevista(formatarData(iEncomenda.getEncomenda().getDataPrevista(), "html"));
			encomenda.setDataVencimento(formatarData(iEncomenda.getEncomenda().getDataVencimento(), "html"));
			encomenda.setDataProntidao(formatarData(iEncomenda.getEncomenda().getDataProntidao(), "html"));
			encomenda.setCliente(iEncomenda.getEncomenda().getCliente());
			iEncomenda.setEncomenda(encomenda);
			
			nextPage = "view/admin/pages/formAtualizarEncomenda.jsp";
			
		} catch (Exception e) {
			msg.defineMessage("error", e.getMessage());
			response.sendRedirect("localhost:9090/madeira/view/admin/pages/ServletItemEncomenda");
		}
		
		request.setAttribute("clientes", clientes);
		request.setAttribute("iEncomenda", iEncomenda);
		request.setAttribute("moveis", moveis);
		
		RequestDispatcher rd = request.getRequestDispatcher(nextPage);
		rd.forward(request, response);
	}
	
	/*
	 *	DELETE 
	 */
	protected void deletar(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int id = Integer.parseInt(request.getParameter("id"));

		ItemEncomendaDAO iEncomendaDAO = new ItemEncomendaDAO(); 
		Message msg = new Message();

		try {
			// remove o item encomenda
			if(iEncomendaDAO.deletar(id)) 
				msg.defineMessage("success", "Encomenda removida com sucesso!");
			else
				throw new SQLException("Erro: não foi possível remover a encomenda!");
		} catch (SQLException e) {
			msg.defineMessage("error", e.getMessage());
		}
		
		 request.setAttribute("msg", msg);
		 this.listar(request, response);
	}
	
	
	public String formatarData(String data, String acao){

		switch (acao) {
		case "html": {
			if (data == null)
				data = "0000-00-00 00:00:00";
			return data.replace(" ", "T");		
		}
		case "bd": {
			if (data != null)
				data.replace("T", " ");
			return data;		
		}
		default:
			return null;
		}
	}
}

