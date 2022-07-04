package br.cefet.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.cefet.model.LinhaMovel;

public class LinhaMovelDAO {

	public Boolean registrar(LinhaMovel lMovel) throws SQLException {
		Boolean result = false;
		String sql = "INSERT INTO linha_movel (linha_nome, linha_descricao) VALUES(?, ?)";
		Connection conn = ConnectionFactory.getConnection();
		PreparedStatement stmt = conn.prepareStatement(sql);
		
		stmt.setString(1, lMovel.getNome());
		stmt.setString(2, lMovel.getDescricao());

		int count = stmt.executeUpdate();
		
		if(count > 0)
			result = true;
		
		conn.close();
		return result;
	}
	
	public List<LinhaMovel> listarTodos() throws SQLException{
		String sql = "SELECT * FROM linha_movel";
		
		Connection conn = ConnectionFactory.getConnection();
		PreparedStatement stmt = conn.prepareStatement(sql);
		ResultSet rs = stmt.executeQuery();
		List<LinhaMovel> linhasMoveis = new ArrayList<LinhaMovel>();
		LinhaMovel lm = null;
		
		while(rs.next()) {
			lm = new LinhaMovel();
			lm.setId(rs.getInt("id"));
			lm.setNome(rs.getString("linha_nome"));
			lm.setDescricao(rs.getString("linha_descricao"));
			
			linhasMoveis.add(lm);
		}
		
		return linhasMoveis;
	}
	
	public LinhaMovel listarUm(int id) throws SQLException {
		String sql = "SELECT lm.id, lm.linha_nome, lm.linha_descricao "
				  + " FROM linha_movel lm "
				  + " WHERE lm.id = ?";
		
		Connection conn = ConnectionFactory.getConnection();
		PreparedStatement stmt = conn.prepareStatement(sql);
		stmt.setInt(1, id);
		ResultSet rs = stmt.executeQuery();

		LinhaMovel lMovel = new LinhaMovel();
		
		while(rs.next()) {
			lMovel.setId(rs.getInt("id"));
			lMovel.setNome(rs.getString("linha_nome"));
			lMovel.setDescricao(rs.getString("linha_descricao"));
		}

		return lMovel;
	}
	
	public Boolean verificaSeExiste(String nome) throws SQLException {
		String sql = "SELECT COUNT(*) as quantidade"
		+ " FROM linha_movel lm "
		+ " WHERE lm.linha_nome = ?";
		
		Boolean result = false;
		int numColunas = 0;
		
		Connection conn = ConnectionFactory.getConnection();
		PreparedStatement stmt = conn.prepareStatement(sql);
		stmt.setString(1, nome);
		ResultSet rs = stmt.executeQuery();
		
		while(rs.next())
			numColunas = rs.getInt("quantidade");		
		
		if(numColunas > 0)
			result = true;
		
		conn.close();
		return result;
	}
	
	public List<LinhaMovel> pesquisa(String text) throws SQLException {
		
		text = "%" + text + "%"; 
		String sql = "SELECT linha_nome, linha_descricao "
				+ " FROM linha_movel "
				+ " WHERE linha_nome LIKE ?";
		
		Connection conn = ConnectionFactory.getConnection();
		PreparedStatement stmt = conn.prepareStatement(sql);
		stmt.setString(1, text);
		ResultSet rs = stmt.executeQuery();
		
		List<LinhaMovel> lMoveis = new ArrayList<LinhaMovel>();
		LinhaMovel lMovel;
		
		while(rs.next()) {
			lMovel = new LinhaMovel();
			lMovel.setNome(rs.getString("linha_nome"));
			lMovel.setDescricao(rs.getString("linha_descricao"));
			lMoveis.add(lMovel);
		}
		
		conn.close();
		return lMoveis;
	}
	
	public Boolean editar(LinhaMovel lMovel) throws SQLException {
		Boolean result = false;
		String sql = "UPDATE linha_movel SET linha_nome = ?, linha_descricao = ? "
				+ " WHERE id = ?";
		Connection conn = ConnectionFactory.getConnection();
		PreparedStatement stmt = conn.prepareStatement(sql);
		stmt.setString(1, lMovel.getNome());
		stmt.setString(2, lMovel.getDescricao());
		stmt.setInt(3, lMovel.getId());
	
		int count = stmt.executeUpdate();
		
		if(count > 0)
			result = true;
		
		conn.close();
		return result;
	}
	
	public Boolean deletar(int id) throws SQLException {
		Boolean result = false;
		String sql = "DELETE FROM linha_movel WHERE (SELECT COUNT(*) "
				  + " FROM movel m INNER JOIN linha_movel lm ON (lm.id = m.id_linha_movel) "
				  + " WHERE lm.id = ?) = 0 AND id = ?";
		Connection conn = ConnectionFactory.getConnection();
		PreparedStatement stmt = conn.prepareStatement(sql);
		stmt.setInt(1, id);
		stmt.setInt(2, id);
		
		int count = stmt.executeUpdate();
		
		if(count > 0)
			result = true;
		
		conn.close();
		return result;
	}
}
