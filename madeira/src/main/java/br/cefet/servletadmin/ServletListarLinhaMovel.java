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
import br.cefet.model.LinhaMovel;
import br.cefet.utilities.Message;


@WebServlet("/ServletListarLinhaMovel")
public class ServletListarLinhaMovel extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public ServletListarLinhaMovel() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List<LinhaMovel> linhasMoveis = null;
		LinhaMovelDAO lmDAO = new LinhaMovelDAO();
		Message msg = new Message();
		
		try {
			linhasMoveis = lmDAO.listarTodos();
		} catch (SQLException e) {
			msg.defineMessage("error", e.getMessage());
		}

		request.setAttribute("linhasMoveis", linhasMoveis);
		RequestDispatcher rd = request.getRequestDispatcher("view/admin/pages/formCreateMovel.jsp");
		rd.forward(request, response);
	}

}
