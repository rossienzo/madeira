package br.cefet.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.cefet.model.Cliente;
import br.cefet.model.Encomenda;
import br.cefet.model.ItemEncomenda;
import br.cefet.model.Movel;

public class ItemEncomendaDAO {

	public Boolean registrar(ItemEncomenda iEncomenda) throws SQLException {
		Boolean result = false;
		String sql = "INSERT INTO item_encomenda (id_encomenda, id_movel, quantidade) VALUES(?, ?, ?)";
		Connection conn = ConnectionFactory.getConnection();
		PreparedStatement stmt = conn.prepareStatement(sql);
		
		stmt.setInt(1, iEncomenda.getEncomenda().getId());
		stmt.setInt(2, iEncomenda.getMovel().getId());
		stmt.setInt(3, iEncomenda.getQuantidade());

		int count = stmt.executeUpdate();
		
		if(count > 0)
			result = true;
		
		conn.close();
		return result;
	}
	
	public int numItemEncomenda() throws SQLException {
		String sql = "SELECT COUNT(*) as quantidadeItemEncomendas FROM item_encomenda";
		Connection conn = ConnectionFactory.getConnection();
		PreparedStatement stmt = conn.prepareStatement(sql);
		ResultSet rs = stmt.executeQuery();
		int qtdEncomendas = 0;
		
		while(rs.next())
			qtdEncomendas = (rs.getInt("quantidadeItemEncomendas"));

		conn.close();
		return qtdEncomendas;
	}
	
	public ItemEncomenda listarUm(int id) throws SQLException {
		String sql = "SELECT ie.id, e.id, e.data, e.data_prevista, e.data_entrega, e.data_vencimento, e.data_pagamento, e.data_prontidao, e.id_cliente, c.nome, "
				+ " ie.quantidade, m.id, m.nome FROM item_encomenda ie "
				+ " INNER JOIN encomenda e ON(ie.id_encomenda = e.id) "
				+ " INNER JOIN movel m ON (ie.id_movel = m.id) "
				+ " INNER JOIN cliente c ON (e.id_cliente = c.id) "
				+ " WHERE ie.id = ?";
		
		Connection conn = ConnectionFactory.getConnection();
		PreparedStatement stmt = conn.prepareStatement(sql);
		stmt.setInt(1, id);
		ResultSet rs = stmt.executeQuery();

		Encomenda encomenda = null;
		Cliente cliente = null;
		Movel movel = null;
		ItemEncomenda iEncomenda = null;
		
		while(rs.next()) {
			encomenda = new Encomenda();
			cliente = new Cliente();
			movel = new Movel();
			iEncomenda = new ItemEncomenda();
			
			iEncomenda.setId(rs.getInt("ie.id"));
			encomenda.setId(rs.getInt("e.id"));
			encomenda.setData(rs.getString("e.data"));
			encomenda.setDataPrevista(rs.getString("e.data_prevista"));
			encomenda.setDataEntrega(rs.getString("e.data_entrega"));
			encomenda.setDataVencimento(rs.getString("e.data_vencimento"));
			encomenda.setDataPagamento(rs.getString("e.data_pagamento"));
			encomenda.setDataProntidao(rs.getString("e.data_prontidao"));
			
			cliente.setId(rs.getInt("e.id_cliente"));
			cliente.setNome(rs.getString("c.nome"));
			encomenda.setCliente(cliente);
			
			iEncomenda.setEncomenda(encomenda);
			
			movel.setId(rs.getInt("m.id"));
			movel.setNome(rs.getString("m.nome"));
			iEncomenda.setMovel(movel);
			iEncomenda.setQuantidade(rs.getInt("ie.quantidade"));
		}

		return iEncomenda;
	}
	
	public List<ItemEncomenda> listarTodos()  throws SQLException {
		String sql = "SELECT e.data, e.data_prevista, e.data_entrega, e.data_vencimento, e.data_pagamento, e.data_prontidao, c.nome, "
				+ " ie.id, ie.id_encomenda, ie.id_movel, ie.quantidade, m.nome FROM item_encomenda ie "
				+ " INNER JOIN encomenda e ON(ie.id_encomenda = e.id) "
				+ " INNER JOIN cliente c ON (e.id_cliente = c.id) "
				+ " INNER JOIN movel m ON(ie.id_movel = m.id)";
		
		Connection conn = ConnectionFactory.getConnection();
		PreparedStatement stmt = conn.prepareStatement(sql);
		ResultSet rs = stmt.executeQuery();
		
		List<ItemEncomenda> iEncomendas = new ArrayList<ItemEncomenda>();

		Encomenda encomenda = null;
		Cliente cliente = null;
		Movel movel = null;
		ItemEncomenda iEncomenda = null;
		
		while(rs.next()) {
			encomenda = new Encomenda();
			cliente = new Cliente();
			movel = new Movel();
			iEncomenda = new ItemEncomenda();
			
			encomenda.setId(rs.getInt("ie.id_encomenda"));
			encomenda.setData(rs.getString("e.data"));
			encomenda.setDataPrevista(rs.getString("e.data_prevista"));
			encomenda.setDataEntrega(rs.getString("e.data_entrega"));
			encomenda.setDataVencimento(rs.getString("e.data_vencimento"));
			encomenda.setDataPagamento(rs.getString("e.data_pagamento"));
			encomenda.setDataProntidao(rs.getString("e.data_prontidao"));
			cliente.setNome(rs.getString("c.nome"));
			encomenda.setCliente(cliente);
			
			iEncomenda.setId(rs.getInt("ie.id"));
			
			iEncomenda.setEncomenda(encomenda);
			
			movel.setId(rs.getInt("ie.id_movel"));
			movel.setNome(rs.getString("m.nome"));
			iEncomenda.setMovel(movel);
			iEncomenda.setQuantidade(rs.getInt("ie.quantidade"));
			iEncomendas.add(iEncomenda);
		}

		return iEncomendas;
	}
	
	public List<ItemEncomenda> pesquisa(String text)  throws SQLException {
		
		text = "%" + text + "%";
		
		String sql = "SELECT e.data, e.data_prevista, e.data_entrega, e.data_vencimento, e.data_pagamento, e.data_prontidao, c.nome, "
				+ " ie.id, ie.id_encomenda, ie.id_movel, ie.quantidade, m.nome FROM item_encomenda ie "
				+ " INNER JOIN encomenda e ON(ie.id_encomenda = e.id) "
				+ " INNER JOIN cliente c ON (e.id_cliente = c.id) "
				+ " INNER JOIN movel m ON(ie.id_movel = m.id) "
				+ " WHERE c.nome LIKE ? || m.nome LIKE ?";
		
		Connection conn = ConnectionFactory.getConnection();
		PreparedStatement stmt = conn.prepareStatement(sql);
		stmt.setString(1, text);
		stmt.setString(2, text);
		ResultSet rs = stmt.executeQuery();
		
		List<ItemEncomenda> iEncomendas = new ArrayList<ItemEncomenda>();

		Encomenda encomenda = null;
		Cliente cliente = null;
		Movel movel = null;
		ItemEncomenda iEncomenda = null;
		
		while(rs.next()) {
			encomenda = new Encomenda();
			cliente = new Cliente();
			movel = new Movel();
			iEncomenda = new ItemEncomenda();
			
			encomenda.setId(rs.getInt("ie.id_encomenda"));
			encomenda.setData(rs.getString("e.data"));
			encomenda.setDataPrevista(rs.getString("e.data_prevista"));
			encomenda.setDataEntrega(rs.getString("e.data_entrega"));
			encomenda.setDataVencimento(rs.getString("e.data_vencimento"));
			encomenda.setDataPagamento(rs.getString("e.data_pagamento"));
			encomenda.setDataProntidao(rs.getString("e.data_prontidao"));
			cliente.setNome(rs.getString("c.nome"));
			encomenda.setCliente(cliente);
			
			iEncomenda.setId(rs.getInt("ie.id"));
			
			iEncomenda.setEncomenda(encomenda);
			
			movel.setId(rs.getInt("ie.id_movel"));
			movel.setNome(rs.getString("m.nome"));
			iEncomenda.setMovel(movel);
			iEncomenda.setQuantidade(rs.getInt("ie.quantidade"));
			iEncomendas.add(iEncomenda);
		}

		return iEncomendas;
	}
	
	public Boolean editar(ItemEncomenda iEncomenda) throws SQLException {
		Boolean result = false;
		String sql = "UPDATE item_encomenda ie INNER JOIN encomenda e ON(ie.id_encomenda = e.id) "
				+ " SET ie.id_encomenda = ?, e.data = ?, e.data_prevista = ?, e.data_entrega = ?, "
				+ " e.data_vencimento = ?, e.data_pagamento = ?, e.data_prontidao = ?, e.id_cliente = ?, "
				+ " ie.id_movel = ?, quantidade = ? "
				+ " WHERE ie.id = ?";
		
		Connection conn = ConnectionFactory.getConnection();
		PreparedStatement stmt = conn.prepareStatement(sql);
		stmt.setInt(1, iEncomenda.getEncomenda().getId());
		stmt.setString(2, iEncomenda.getEncomenda().getData());
		stmt.setString(3, iEncomenda.getEncomenda().getDataPrevista());
		stmt.setString(4, iEncomenda.getEncomenda().getDataEntrega());
		stmt.setString(5, iEncomenda.getEncomenda().getDataVencimento());
		stmt.setString(6, iEncomenda.getEncomenda().getDataPagamento());
		stmt.setString(7, iEncomenda.getEncomenda().getDataProntidao());
		stmt.setInt(8, iEncomenda.getEncomenda().getCliente().getId());
		stmt.setInt(9, iEncomenda.getMovel().getId());
		stmt.setInt(10, iEncomenda.getQuantidade());
		stmt.setInt(11, iEncomenda.getId());
	
		int count = stmt.executeUpdate();
		if(count > 0)
			result = true;
		
		conn.close();
		return result;
	}
	
	public Boolean deletar(int id) throws SQLException {
		Boolean result = false;
		String sql = "DELETE item_encomenda, encomenda FROM item_encomenda "
				+ " INNER JOIN encomenda ON(item_encomenda.id_encomenda = encomenda.id) "
				+ " WHERE item_encomenda.id = ?";
		Connection conn = ConnectionFactory.getConnection();
		PreparedStatement stmt = conn.prepareStatement(sql);
		stmt.setInt(1, id);
		
		int count = stmt.executeUpdate();
		
		if(count > 0)
			result = true;
		
		conn.close();
		return result;
	}
}
