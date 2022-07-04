package br.cefet.servletadmin;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import br.cefet.utilities.ItemCard;
import br.cefet.utilities.Message;

/*
 * Responsável por carregar os elementos do admin/index
 */

@WebServlet("/ServletIndex")
public class ServletIndex extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public ServletIndex() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		Message msg = new Message();
		HttpSession session = request.getSession(); // inicio a session
		ItemCard iCard = null;
		
		try {
			iCard = new ItemCard();
		} catch (SQLException e) {
			msg.defineMessage("error", "Erro: " + e.getMessage());
		}
		session.setAttribute("item", iCard);
		
		request.setAttribute("msg", msg);
		RequestDispatcher rd = request.getRequestDispatcher("/view/admin/index.jsp"); // aponto para a página
		rd.forward(request, response); // envio para a pagina
	}

}
