package br.cefet.utilities;

import java.sql.SQLException;

import br.cefet.dao.ClienteDAO;
import br.cefet.dao.ItemEncomendaDAO;

public class ItemCard {

	private int qtdEncomenda;
	private int qtdCliente;
	
	public ItemCard() throws SQLException {
		ItemEncomendaDAO iEncomendaDAO = new ItemEncomendaDAO();
		ClienteDAO cDAO = new ClienteDAO();
		
		this.qtdEncomenda = iEncomendaDAO.numItemEncomenda();
		this.qtdCliente = cDAO.numClientes();
	}

	public int getQtdEncomenda() {
		return this.qtdEncomenda;
	}

	public int getQtdCliente() {
		return this.qtdCliente;
	}
	
}
