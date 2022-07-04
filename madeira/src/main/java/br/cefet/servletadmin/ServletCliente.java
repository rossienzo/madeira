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
import javax.servlet.http.HttpSession;

import br.cefet.dao.ClienteDAO;
import br.cefet.model.Cliente;
import br.cefet.utilities.Message;

@WebServlet("/ServletCliente")
public class ServletCliente extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public ServletCliente() {
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
		Message msg = new Message();
		
		// insere um móvel
		if(acao.equals("inserir")) {
			this.inserir(request, response);
		}
		// atualiza um móvel
		else {
			int id = Integer.parseInt(request.getParameter("id"));
			String telefone = request.getParameter("telefone");
			String nome = request.getParameter("nome");
			String senha = request.getParameter("senha");
			String resenha = request.getParameter("resenha");
			
			try {
				ClienteDAO cDAO = new ClienteDAO();
				
				if(telefone.length() < 8 || telefone.length() > 12)
					throw new Exception("Erro: O telefone é inválido!");
				
				if(nome.isBlank() || nome.isEmpty())
					throw new Exception("Erro: O nome não pode ser vazio!");
				
				if(senha.length() < 5 )
					throw new Exception("Erro: A senha precisa ser maior que 4 digitos!");
				
				if(senha == resenha)
					throw new Exception("Erro: As senhas precisam ser iguais!");		
			
				Cliente clienteSession = (Cliente) request.getSession().getAttribute("cliente");
				
				if(cDAO.verificarSeExiste(nome)) 
					if(nome != clienteSession.getNome())
						throw new Exception("Erro: O usuário já existe!");

				Cliente cliente = new Cliente(id, telefone, nome, senha);
				
				if(cDAO.editar(cliente))
					msg.defineMessage("success", "Cliente atualizado com sucesso!");
				else
					throw new Exception("Erro: não foi possivel atualizar o cliente!");
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
		String telefone = request.getParameter("telefone");
		String nome = request.getParameter("nome");
		String senha = request.getParameter("senha");
		String resenha = request.getParameter("resenha");
		
		Message msg = new Message();

		try {
			ClienteDAO cDAO = new ClienteDAO();
			
			if(telefone.length() < 8 || telefone.length() > 12)
				throw new Exception("Erro: O telefone é inválido!");
			
			if(nome.isBlank() || nome.isEmpty())
				throw new Exception("Erro: O nome não pode ser vazio!");
			
			if(senha.length() < 4 )
				throw new Exception("Erro: A senha precisa ser maior que 4 digitos!");
			
			if(!senha.equals(resenha))
				throw new Exception("Erro: As senhas precisam ser iguais!");
		
			if(cDAO.verificarSeExiste(nome))
				throw new Exception("Erro: O cliente já existe!");
			
			String codigo = "cod-" + String.valueOf(System.currentTimeMillis()); //pega o horário atual
			
			Cliente cliente = new Cliente(codigo, telefone, nome, senha);
			
			if(cDAO.registrar(cliente))
				msg.defineMessage("success", "Cliente cadastrado com sucesso!");
			else
				throw new Exception("Erro: erro ao cadastrar cliente!");
			
		} catch (Exception e) {
			msg.defineMessage("error", e.getMessage());
		}
		
		request.setAttribute("msg", msg);
		RequestDispatcher rd = request.getRequestDispatcher("view/admin/pages/formCreateCliente.jsp");
		rd.forward(request, response);
	}
	
	/*
	 *	SELECT - lista todos os móveis
	 */
	protected void listar(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List<Cliente> clientes = null;
		Message msg = new Message();
		ClienteDAO cDAO = new ClienteDAO();
		
		try {
			clientes = cDAO.listarTodos();
		} catch (SQLException e) {
			msg.defineMessage("error", e.getMessage());
		}

		request.setAttribute("clientes", clientes);
		RequestDispatcher rd = request.getRequestDispatcher("view/admin/pages/listarCliente.jsp");
		rd.forward(request, response);

	}
	
	/*
	 *	SEARCH - busca um móvel pelo nome
	 */
	protected void buscar(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String buscar = request.getParameter("buscar") != null ? request.getParameter("buscar") : "";
		ClienteDAO cDAO = new ClienteDAO();
		Message msg = new Message();
		List<Cliente> clientes = null;
		
		try {
			clientes = cDAO.pesquisa(buscar);
		} catch (SQLException e) {
			msg.defineMessage("error", e.getMessage());
		}

		request.setAttribute("clientes", clientes);
		RequestDispatcher rd = request.getRequestDispatcher("view/admin/pages/listarCliente.jsp");
		rd.forward(request, response);
	}
	
	/*
	 *	UPDATE - retorna o item a ser editado
	 */
	protected void editar(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// verifica se está recebendo o parâmetro id, se não, coloca um número inválido
		int id = request.getParameter("id") != null ? Integer.parseInt(request.getParameter("id")) : -1;
		Message msg = new Message();

		Cliente cliente = null;
		String nextPage = "view/admin/pages/ServletCliente";
		
		try {
			ClienteDAO cDAO = new ClienteDAO();
			
			// se o id for válido, lista todas as linhas móveis e os dados do elemento a ser editado
			if(id < 0)
				throw new Exception("Erro: id inválido");
			
			cliente = new Cliente();
			cliente = cDAO.listarUm(id);
			nextPage = "view/admin/pages/formAtualizarCliente.jsp";
			
		} catch (Exception e) {
			msg.defineMessage("error", e.getMessage());
			response.sendRedirect("localhost:9090/madeira/view/admin/pages/ServletCliente");
		}

		request.setAttribute("cliente", cliente);
		RequestDispatcher rd = request.getRequestDispatcher(nextPage);
		rd.forward(request, response);	
	}
	
	/*
	 *	DELETE 
	 */
	protected void deletar(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int id = Integer.parseInt(request.getParameter("id"));
		
		Cliente cliente = null;
		ClienteDAO cDAO = new ClienteDAO();
		Message msg = new Message();
		
		try {
			HttpSession session = request.getSession();
			Cliente clienteSession = (Cliente) session.getAttribute("user");
			cliente = cDAO.listarUm(id);

			if(clienteSession == null)
				response.sendRedirect("ServletLogin?acao=logoff");
			
			// impede de apagar o cliente da session
			if(cliente.getCodigo().equals(clienteSession.getCodigo()))
				throw new Exception("Erro: não é possível apagar um cliente logado!");

			if(cDAO.deletar(id))
				msg.defineMessage("success", "Cliente removido com sucesso!");
			else
				throw new Exception("Erro: o cliente possui encomendas associadas a ele, remova-as e tente novamente!");

		} catch (Exception e) {
			msg.defineMessage("error", e.getMessage());
		}
		
		 request.setAttribute("msg", msg);
		 this.listar(request, response);
	}
}


