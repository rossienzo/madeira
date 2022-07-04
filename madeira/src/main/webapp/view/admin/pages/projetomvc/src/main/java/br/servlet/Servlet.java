package br.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.model.Funcionario;

/**
 * Servlet implementation class Servlet
 */
@WebServlet("/Servlet")
public class Servlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Servlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		String nome = String.valueOf(request.getParameter("nome"));
		float salario = Float.valueOf(request.getParameter("salario"));
		
		Funcionario fun = new Funcionario();
		fun.setNome(nome);
		fun.setSalario(salario);
		
		PrintWriter saida = response.getWriter();
		saida.println("<html><head><title> Registro de Funcionário </title> </head>");;
		saida.println("<body> Nome: " + fun.getNome());
		saida.println("<body> Salário: " + fun.getSalario());
		saida.println("</body> </html>");

		
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
