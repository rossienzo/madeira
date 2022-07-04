package br.cefet.servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import br.cefet.dao.ClienteDAO;
import br.cefet.model.Cliente;
import br.cefet.utilities.ItemCard;
import br.cefet.utilities.Message;

@WebServlet("/ServletLogin")
public class ServletLogin extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public ServletLogin() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// pega o tipo de parâmetro
		String acao = request.getParameter("acao") != null ? request.getParameter("acao") : "";
      
		// realiza o logoff
		if (acao.equals("logoff")) {
			this.logoff(request, response);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String acao = request.getParameter("acao") != null ? request.getParameter("acao") : "";
		String name = request.getParameter("name");
		String password = request.getParameter("password");
		String nextPage = "view/login.jsp";
		
		if(acao.equals("logoff"))
			this.logoff(request, response);
		
		ClienteDAO cDAO = new ClienteDAO();
		Message msg = new Message();
		Cliente cliente = new Cliente();
		ItemCard iCard = null;
		
		cliente.setNome(name);
		cliente.setSenha(password);
		
		try {
			cliente = cDAO.login(cliente);
			
			if(cliente == null || name.length() < 3 || name.isBlank() || password.length() < 3 || password.isBlank() || cliente.getNome().isEmpty() || cliente.getSenha().isEmpty())
				throw new Exception("Erro: Usuário ou senha inválida!");
			else if(name.equals(cliente.getNome())  &&  password.equals(cliente.getSenha())) {
				
				HttpSession session; // Instancio uma session
				session = request.getSession(); // inicio a session
				
				// carrega os dados do index admin
				iCard = new ItemCard();

				session.setAttribute("user", cliente); // defino os dados do cliente na session
				session.setAttribute("item", iCard);

				nextPage = "view/admin/index.jsp";

			} else 
				msg.defineMessage("error", "Usuário ou senha inválida");
		} catch (Exception e) {
			msg.defineMessage("error", e.getMessage());
		}
		
		request.setAttribute("msg", msg);
		RequestDispatcher rd = request.getRequestDispatcher(nextPage); // aponto para a página
		rd.forward(request, response); // envio para a pagina
	}
	
	protected void logoff(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session;
		session = request.getSession(); // inicio a session
		session.getAttribute("cliente");
		session.invalidate(); // elimina a session
		response.sendRedirect("view/login.jsp");
	}
}
