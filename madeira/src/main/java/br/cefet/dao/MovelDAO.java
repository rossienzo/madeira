 package br.cefet.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.cefet.model.LinhaMovel;
import br.cefet.model.Movel;

public class MovelDAO {
	
	public Boolean registrar(Movel movel) throws SQLException {
		Boolean result = false;
		String sql = "INSERT INTO movel (nome, modelo, descricao, preco, id_linha_movel) VALUES(?, ?, ?, ?, ?)";
		Connection conn = ConnectionFactory.getConnection();
		PreparedStatement stmt = conn.prepareStatement(sql);
		
		stmt.setString(1, movel.getNome());
		stmt.setString(2, movel.getModelo());
		stmt.setString(3, movel.getDescricao());
		stmt.setDouble(4, movel.getPreco());
		stmt.setInt(5, movel.getLinhaMovel().getId());

		int count = stmt.executeUpdate();
		
		if(count > 0)
			result = true;
		
		conn.close();
		return result;
	}
	
	public List<Movel> listarTodos() throws SQLException {
		String sql = "SELECT m.id, m.nome, m.modelo, m.descricao, m.preco, lm.linha_nome, lm.linha_descricao "
				+ "FROM movel m INNER JOIN linha_movel lm ON(m.id_linha_movel = lm.id)";
		Connection conn = ConnectionFactory.getConnection();
		PreparedStatement stmt = conn.prepareStatement(sql);
		ResultSet rs = stmt.executeQuery();
		
		List<Movel> moveis = new ArrayList<Movel>();
		Movel movel;
		LinhaMovel lMovel;
		
		while(rs.next()) {
			movel = new Movel();
			lMovel = new LinhaMovel();
			movel.setId(rs.getInt("id"));
			movel.setNome(rs.getString("nome"));
			movel.setModelo(rs.getString("modelo"));
			movel.setDescricao(rs.getString("descricao"));
			movel.setPreco(rs.getDouble("preco"));
			lMovel.setNome(rs.getString("linha_nome"));
			lMovel.setDescricao(rs.getString("linha_descricao"));
			movel.setLinhaMovel(lMovel);
			moveis.add(movel);
		}
		
		conn.close();
		return moveis;
	}
	
	public Movel listarUm(int id) throws SQLException {
		String sql = "SELECT m.id, m.nome, m.modelo, m.descricao, m.preco, m.id_linha_movel, lm.linha_nome, lm.linha_descricao "
		+ " FROM movel m INNER JOIN linha_movel lm ON(m.id_linha_movel = lm.id) "
		+ " WHERE m.id = ?";
		
		Connection conn = ConnectionFactory.getConnection();
		PreparedStatement stmt = conn.prepareStatement(sql);
		stmt.setInt(1, id);
		ResultSet rs = stmt.executeQuery();
		
		Movel movel = new Movel();
		LinhaMovel lMovel = new LinhaMovel();
		
		while(rs.next()) {
			movel.setId(rs.getInt("id"));
			movel.setNome(rs.getString("nome"));
			movel.setModelo(rs.getString("modelo"));
			movel.setDescricao(rs.getString("descricao"));
			movel.setPreco(rs.getDouble("preco"));
			lMovel.setId(rs.getInt("id_linha_movel"));
			lMovel.setNome(rs.getString("linha_nome"));
			lMovel.setDescricao(rs.getString("linha_descricao"));
			movel.setLinhaMovel(lMovel);
		}

		return movel;
	}
	
	public List<Movel> pesquisa(String text) throws SQLException {
		
		text = "%" + text + "%"; 
		String sql = "SELECT m.id, m.nome, m.modelo, m.descricao, m.preco, lm.linha_nome, lm.linha_descricao "
				+ "FROM movel m INNER JOIN linha_movel lm ON(m.id_linha_movel = lm.id) "
				+ "WHERE m.nome LIKE ? || m.modelo LIKE ?";
		
		Connection conn = ConnectionFactory.getConnection();
		PreparedStatement stmt = conn.prepareStatement(sql);
		stmt.setString(1, text);
		stmt.setString(2, text);
		ResultSet rs = stmt.executeQuery();
		
		List<Movel> moveis = new ArrayList<Movel>();
		Movel movel;
		LinhaMovel lMovel;
		
		while(rs.next()) {
			movel = new Movel();
			lMovel = new LinhaMovel();
			movel.setId(rs.getInt("id"));
			movel.setNome(rs.getString("nome"));
			movel.setModelo(rs.getString("modelo"));
			movel.setDescricao(rs.getString("descricao"));
			movel.setPreco(rs.getDouble("preco"));
			lMovel.setNome(rs.getString("linha_nome"));
			lMovel.setDescricao(rs.getString("linha_descricao"));
			movel.setLinhaMovel(lMovel);
			moveis.add(movel);
		}
		
		conn.close();
		return moveis;
	}
	
	public Boolean editar(Movel movel) throws SQLException {
		Boolean result = false;
		String sql = "UPDATE movel SET nome = ?, modelo = ?, descricao = ?, preco = ?, id_linha_movel = ?"
				+ " WHERE id = ?";
		Connection conn = ConnectionFactory.getConnection();
		PreparedStatement stmt = conn.prepareStatement(sql);
		stmt.setString(1, movel.getNome());
		stmt.setString(2, movel.getModelo());
		stmt.setString(3, movel.getDescricao());
		stmt.setDouble(4, movel.getPreco());
		stmt.setInt(5, movel.getLinhaMovel().getId());
		stmt.setInt(6, movel.getId());
	
		int count = stmt.executeUpdate();
		if(count > 0)
			result = true;
		
		conn.close();
		return result;
	}
	
	public Boolean deletar(int id) throws SQLException {
		Boolean result = false;
		String sql = "DELETE FROM movel WHERE id = ?";
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
