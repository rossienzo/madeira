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
import br.cefet.dao.MovelDAO;
import br.cefet.model.Cliente;
import br.cefet.model.Movel;
import br.cefet.utilities.Message;

@WebServlet("/ServletListarCliente")
public class ServletListarCliente extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public ServletListarCliente() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		Message msg = new Message();
		ClienteDAO cDAO = new ClienteDAO();
		List<Cliente> clientes = null;
		
		MovelDAO mDAO = new MovelDAO();
		List<Movel> moveis = null;
		
		try {
			clientes = cDAO.listarTodos();
			moveis = mDAO.listarTodos();
		} catch (SQLException e) {
			msg.defineMessage("error", e.getMessage());
		}

		request.setAttribute("clientes", clientes);
		request.setAttribute("moveis", moveis);
		RequestDispatcher rd = request.getRequestDispatcher("view/admin/pages/formCreateEncomenda.jsp");
		rd.forward(request, response);
	}

}
