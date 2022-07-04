package br.cefet.filtro;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import br.cefet.model.Cliente;

@WebFilter(filterName="Filtro", urlPatterns="/view/*")
public class Filtro extends HttpFilter implements Filter {

	private static final long serialVersionUID = 1L;

	public Filtro() {
        super();
    }

	public void destroy() { }

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		// faz um cast para a instância fique igual ao do protocolo ServletRequest e ServletResponse
		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse resp = (HttpServletResponse) response;
		
		// Pega o cliente da session
		HttpSession session = req.getSession();
		
		// salva os dados da sessão em uma instância de cliente
		Cliente cliente = (Cliente) session.getAttribute("user");
		
		// Url que o usuário está tentando acessar
		String url = req.getRequestURI();

		if (url.endsWith("login.jsp") || url.endsWith("ServletLogin"))		
			// saída do filtro
			chain.doFilter(request, response);
		else
			if (cliente != null)
				chain.doFilter(request, response);
			else
				resp.sendRedirect("/madeira/view/login.jsp");
	}

	public void init(FilterConfig fConfig) throws ServletException { }

}
