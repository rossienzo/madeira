package br.cefet.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.cefet.model.Cliente;
import br.cefet.model.Encomenda;

public class EncomendaDAO {

	public int lastId() throws SQLException {
		int id = 0;
		String sql = "SELECT id FROM encomenda";
		Connection conn = ConnectionFactory.getConnection();
		PreparedStatement stmt = conn.prepareStatement(sql);
		ResultSet rs = stmt.executeQuery();
		
		while(rs.next() )
			id = rs.getInt("id");
		
		return id;
	}
	
	public Boolean registrar(Encomenda encomenda) throws SQLException {
		Boolean result = false;
		String sql = "INSERT INTO encomenda (data, data_prevista, data_entrega, data_vencimento, data_pagamento, data_prontidao, id_cliente) "
				+ " VALUES(?, ?, ?, ?, ?, ?, ?)";
		Connection conn = ConnectionFactory.getConnection();
		PreparedStatement stmt = conn.prepareStatement(sql);
		
		stmt.setString(1, encomenda.getData());
		stmt.setString(2, encomenda.getDataPrevista());
		stmt.setString(3, encomenda.getDataEntrega());
		stmt.setString(4, encomenda.getDataVencimento());
		stmt.setString(5, encomenda.getDataPagamento());
		stmt.setString(6, encomenda.getDataProntidao());
		stmt.setInt(7, encomenda.getCliente().getId());

		int count = stmt.executeUpdate();
		
		if(count > 0)
			result = true;
		
		conn.close();
		return result;
	}
	
	public List<Encomenda> listarTodos() throws SQLException {
		String sql = "SELECT e.id, e.data, e.data_prevista, e.data_entrega, e.data_vencimento, e.data_pagamento, e.data_prontidao, c.nome "
				+ " FROM encomenda e INNER JOIN cliente c ON(e.id_cliente = c.id)";
		
		Connection conn = ConnectionFactory.getConnection();
		PreparedStatement stmt = conn.prepareStatement(sql);
		ResultSet rs = stmt.executeQuery();
		
		List<Encomenda> encomendas = new ArrayList<Encomenda>();

		while(rs.next()) {
			Encomenda encomenda = new Encomenda();
			Cliente cliente = new Cliente();
			
			encomenda.setId(rs.getInt("id"));
			encomenda.setData(rs.getString("data"));
			encomenda.setDataPrevista(rs.getString("data_prevista"));
			encomenda.setDataEntrega(rs.getString("data_entrega"));
			encomenda.setDataVencimento(rs.getString("data_vencimento"));
			encomenda.setDataPagamento(rs.getString("data_pagamento"));
			encomenda.setDataProntidao(rs.getString("data_prontidao"));
			cliente.setNome(rs.getString("nome"));
			encomenda.setCliente(cliente);
			encomendas.add(encomenda);
		}
		
		conn.close();
		return encomendas;
	}
	
	public int numEncomendas() throws SQLException {
		String sql = "SELECT COUNT(*) as quantidadeEncomendas FROM encomenda";
		Connection conn = ConnectionFactory.getConnection();
		PreparedStatement stmt = conn.prepareStatement(sql);
		ResultSet rs = stmt.executeQuery();
		int qtdEncomendas = 0;
		
		while(rs.next())
			qtdEncomendas = (rs.getInt("quantidadeEncomendas"));

		conn.close();
		return qtdEncomendas;
	}
	
	public List<Encomenda> pesquisa(String text) throws SQLException {
		
		text = "%" + text + "%";
		
		String sql = "SELECT e.id, e.data, e.data_prevista, e.data_entrega, e.data_vencimento, e.data_pagamento, e.data_prontidao, c.nome "
				+ " FROM encomenda e INNER JOIN cliente c ON(e.id_cliente = c.id) "
				+ " WHERE c.nome LIKE ?";
		
		Connection conn = ConnectionFactory.getConnection();
		PreparedStatement stmt = conn.prepareStatement(sql);
		stmt.setString(1, text);
		ResultSet rs = stmt.executeQuery();
		
		List<Encomenda> encomendas = new ArrayList<Encomenda>();

		while(rs.next()) {
			Encomenda encomenda = new Encomenda();
			Cliente cliente = new Cliente();
			
			encomenda.setId(rs.getInt("id"));
			encomenda.setData(rs.getString("data"));
			encomenda.setDataPrevista(rs.getString("data_prevista"));
			encomenda.setDataEntrega(rs.getString("data_entrega"));
			encomenda.setDataVencimento(rs.getString("data_vencimento"));
			encomenda.setDataPagamento(rs.getString("data_pagamento"));
			encomenda.setDataProntidao(rs.getString("data_prontidao"));
			cliente.setNome(rs.getString("nome"));
			encomenda.setCliente(cliente);
			encomendas.add(encomenda);
		}
		
		conn.close();
		return encomendas;
	}
	
	public Encomenda listarUm(int id) throws SQLException {
		String sql = "SELECT e.id, e.data, e.data_prevista, e.data_entrega, e.data_vencimento, e.data_pagamento, e.data_prontidao, c.id as idcliente, c.nome "
				+ " FROM encomenda e INNER JOIN cliente c ON(e.id_cliente = c.id)"
				+ " WHERE e.id = ?";
		
		Connection conn = ConnectionFactory.getConnection();
		PreparedStatement stmt = conn.prepareStatement(sql);
		stmt.setInt(1, id);
		
		ResultSet rs = stmt.executeQuery();
		Encomenda encomenda = null;

		while(rs.next()) {
			encomenda = new Encomenda();
			Cliente cliente = new Cliente();
			
			encomenda.setId(rs.getInt("id"));
			encomenda.setData(rs.getString("data"));
			encomenda.setDataPrevista(rs.getString("data_prevista"));
			encomenda.setDataEntrega(rs.getString("data_entrega"));
			encomenda.setDataVencimento(rs.getString("data_vencimento"));
			encomenda.setDataPagamento(rs.getString("data_pagamento"));
			encomenda.setDataProntidao(rs.getString("data_prontidao"));
			cliente.setId(rs.getInt("idcliente"));
			cliente.setNome(rs.getString("nome"));
			encomenda.setCliente(cliente);
		}
		
		conn.close();
		return encomenda;
	}
	
	public Boolean editar(Encomenda encomenda) throws SQLException {
		Boolean result = false;
		String sql = "UPDATE encomenda SET data = ?, data_prevista = ?, data_entrega = ?, data_vencimento = ?, data_pagamento = ?, data_prontidao = ?, id_cliente = ?"
				+ " WHERE id = ?";
		Connection conn = ConnectionFactory.getConnection();
		PreparedStatement stmt = conn.prepareStatement(sql);
		stmt.setString(1, encomenda.getData());
		stmt.setString(2, encomenda.getDataPrevista());
		stmt.setString(3, encomenda.getDataEntrega());
		stmt.setString(4, encomenda.getDataVencimento());
		stmt.setString(5, encomenda.getDataPagamento());
		stmt.setString(6, encomenda.getDataProntidao());
		stmt.setInt(7, encomenda.getCliente().getId());
		stmt.setInt(8, encomenda.getId());
		
		int count = stmt.executeUpdate();
		
		if(count > 0)
			result = true;
		
		conn.close();
		return result;
	}
	
	public Boolean deletar(int id) throws SQLException {
		Boolean result = false;
		String sql = "DELETE FROM encomenda WHERE id = ?";
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
