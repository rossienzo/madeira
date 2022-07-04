package br.cefet.servlet;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.cefet.dao.ClienteDAO;
import br.cefet.model.Cliente;
import br.cefet.utilities.Message;

@WebServlet("/ServletCreate")
public class ServletCreate extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public ServletCreate() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException { }

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
		String name = request.getParameter("name");
		String phone = request.getParameter("phone");
		String password = request.getParameter("password");
		
		Message msg = new Message();
		ClienteDAO cDAO = new ClienteDAO();
		
		Cliente cliente = new Cliente();
		cliente.setNome(name);
		cliente.setTelefone(phone);
		cliente.setSenha(password);
		
		try {
			if(name.length() < 3 || name.isBlank())
				throw new Exception("Erro: nome inválido!");
			else if(phone.length() < 11 || phone.isBlank())
				throw new Exception("Erro: telefône inválido!");
			else if(password.length() < 5 || password.isBlank())
				throw new Exception("Erro: senha inválida!");
			else if(cDAO.verificarSeExiste(name)) 
				throw new Exception("Erro: o usuário já existe");
			
			String time = String.valueOf(System.currentTimeMillis()); //pega o horário atual
			cliente.setCodigo("cod-" + time);
			
			if(cDAO.registrar(cliente))
				msg.defineMessage("success", "Usuário criado com sucesso!");
			else
				msg.defineMessage("error", "Erro: não foi possível criar o usuário");
	
			cliente.resetarDados();
		} catch (Exception e) {
			msg.defineMessage("error", e.getMessage());
		}

		// salva os dados já digitados
		request.setAttribute("cliente", cliente);
		
		// define a mensagem a ser enviada
		request.setAttribute("msg2", msg);
		RequestDispatcher rd = request.getRequestDispatcher("view/login.jsp"); // aponto para a página
		rd.forward(request, response); // envio para a pagina	
	}

}
