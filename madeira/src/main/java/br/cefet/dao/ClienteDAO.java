package br.cefet.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.cefet.model.Cliente;

public class ClienteDAO {

	public Boolean registrar(Cliente cliente) throws SQLException {
		Boolean result = false;
		String sql = "INSERT INTO cliente (codigo, telefone, nome, senha) VALUES (?, ?, ?, ?)";
		
		Connection conn = ConnectionFactory.getConnection();
		PreparedStatement stmt = conn.prepareStatement(sql);
		
		stmt.setString(1, cliente.getCodigo());
		stmt.setString(2, cliente.getTelefone());
		stmt.setString(3, cliente.getNome());
		stmt.setString(4, cliente.getSenha());

		int count = stmt.executeUpdate();
			
		if(count > 0)
			result = true;
		
		conn.close();		
		return result;
	}
	
	public Cliente login(Cliente cliente) throws SQLException {
		String sql = "SELECT * from cliente WHERE nome = ? AND senha = ?";
		
		Connection conn = ConnectionFactory.getConnection();
		PreparedStatement stmt = conn.prepareStatement(sql);
		stmt.setString(1, cliente.getNome());
		stmt.setString(2, cliente.getSenha());
		ResultSet rs = stmt.executeQuery();
		
		Cliente cliente1 = null;
		
		while(rs.next()) {
			cliente1 = new Cliente();
			cliente1.setId(rs.getInt("id"));
			cliente1.setCodigo(rs.getString("codigo"));
			cliente1.setTelefone(rs.getString("telefone"));
			cliente1.setNome(rs.getString("nome"));
			cliente1.setSenha(rs.getString("senha"));	
		}
		
		conn.close();
		return cliente1;
	}
	
	public Boolean verificarSeExiste(String nome) throws SQLException {
		String sql = "SELECT COUNT(*) as quantidade FROM cliente WHERE nome = ?";
		Boolean result = false;
		
		Connection conn = ConnectionFactory.getConnection();
		PreparedStatement stmt = conn.prepareStatement(sql);
		stmt.setString(1, nome);
		ResultSet rs = stmt.executeQuery();
		int numColunas = 0;
		
		while(rs.next())
			numColunas = rs.getInt("quantidade");
		
		if(numColunas > 0)
			result = true;
		
		conn.close();
		return result;
	}
	
	public List<Cliente> listarTodos() throws SQLException {
		String sql = "SELECT c.id, c.codigo, c.telefone, c.nome"
				+ " FROM cliente c";
		Connection conn = ConnectionFactory.getConnection();
		PreparedStatement stmt = conn.prepareStatement(sql);
		ResultSet rs = stmt.executeQuery();
		
		Cliente cliente = null;
		List<Cliente> clientes = new ArrayList<Cliente>();
		
		while(rs.next()) {
			cliente = new Cliente();
			cliente.setId(rs.getInt("id"));
			cliente.setCodigo(rs.getString("codigo"));
			cliente.setTelefone(rs.getString("telefone"));
			cliente.setNome(rs.getString("nome"));
			clientes.add(cliente);
		}
		
		return clientes;
	}
	
	public Cliente listarUm(int id) throws SQLException {
		String sql = "SELECT * FROM cliente WHERE id = ?";
		
		Connection conn = ConnectionFactory.getConnection();
		PreparedStatement stmt = conn.prepareStatement(sql);
		stmt.setInt(1, id);
		ResultSet rs = stmt.executeQuery();

		Cliente cliente = new Cliente();
		
		while(rs.next()) {
			cliente.setId(rs.getInt("id"));
			cliente.setCodigo(rs.getString("codigo"));
			cliente.setTelefone(rs.getString("telefone"));
			cliente.setNome(rs.getString("nome"));
			cliente.setSenha(rs.getString("senha"));
		}

		return cliente;
	}
	
	// Retorna o total de clientes
	public int numClientes() throws SQLException{
		String sql = "SELECT COUNT(*) qtdClientes"
				+ " FROM cliente";
		
		Connection conn = ConnectionFactory.getConnection();
		PreparedStatement stmt = conn.prepareStatement(sql);
		ResultSet rs = stmt.executeQuery();
		int qtdClientes = 0;
		
		while(rs.next()) 
			qtdClientes = rs.getInt("qtdClientes");
		
		return qtdClientes;
	}
	
public List<Cliente> pesquisa(String text) throws SQLException {
		
		text = "%" + text + "%"; 
		String sql = "SELECT * FROM cliente "
				+ " WHERE nome LIKE ? || codigo LIKE ?";
		
		Connection conn = ConnectionFactory.getConnection();
		PreparedStatement stmt = conn.prepareStatement(sql);
		stmt.setString(1, text);
		stmt.setString(2, text);
		ResultSet rs = stmt.executeQuery();
		
		List<Cliente> clientes = new ArrayList<Cliente>();
		Cliente cliente;
		
		while(rs.next()) {
			cliente = new Cliente();
			cliente.setId(rs.getInt("id"));
			cliente.setCodigo(rs.getString("codigo"));
			cliente.setTelefone(rs.getString("telefone"));
			cliente.setNome(rs.getString("nome"));
			cliente.setSenha(rs.getString("senha"));
			clientes.add(cliente);
		}
		
		conn.close();
		return clientes;
	}
	
	public Boolean editar(Cliente cliente) throws SQLException {
		Boolean result = false;
		String sql = "UPDATE cliente SET telefone = ?, nome = ?, senha = ? "
				+ " WHERE id = ?";
		Connection conn = ConnectionFactory.getConnection();
		PreparedStatement stmt = conn.prepareStatement(sql);
		stmt.setString(1, cliente.getTelefone());
		stmt.setString(2, cliente.getNome());
		stmt.setString(3, cliente.getSenha());
		stmt.setInt(4, cliente.getId());
	
		int count = stmt.executeUpdate();
		if(count > 0)
			result = true;
		
		conn.close();
		return result;
	}
	
	public Boolean deletar(int id) throws SQLException {
		Boolean result = false;
		String sql = "DELETE FROM cliente WHERE id = ?";
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