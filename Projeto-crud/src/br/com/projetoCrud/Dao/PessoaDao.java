package br.com.projetoCrud.Dao;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import br.com.projetoCrud.ConnctionFactory.ConnectionFactory;
import br.com.projetoCrud.domain.Pessoa;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class PessoaDao {
	// LISTA TODOS ELEMDNTOS DO MEU BACNO
	public ObservableList<Pessoa> listar() {

		Connection conn;
		ObservableList<Pessoa> itens = FXCollections.observableArrayList();
		try {
			conn = new ConnectionFactory().conexaoFactory();
			String sql = "select * from pessoa";
			PreparedStatement ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				Pessoa p = new Pessoa();
				p.setNome(rs.getString("nome"));
				p.setSobreNome(rs.getString("sobreNome"));
				p.setEmail(rs.getString("email"));
				itens.add(p);
			}

		} catch (SQLException e) {

			e.printStackTrace();
			System.out.println("Erro de consulta!");
		}
		return itens;
	}// FIM METHOD

	public void adiciona(Pessoa p) {
		Connection conn;
		try {

			conn = new ConnectionFactory().conexaoFactory();
			String sql = "INSERT INTO pessoa (nome, sobreNome, email) VALUES(?, ?, ?)";
			PreparedStatement ps = conn.prepareStatement(sql);

			ps.setString(1, p.getNome());
			ps.setString(2, p.getSobreNome());
			ps.setString(3, p.getEmail());
			ps.executeUpdate();

			ps.close();
			conn.close();

		} catch (Exception e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
		}
	}//FIM METHODO
	
	public void excluir(Pessoa p){
		Connection conn;
		try {

			conn = new ConnectionFactory().conexaoFactory();
			String sql = "DELETE FROM pessoa WHERE nome = ?";
			PreparedStatement ps = conn.prepareStatement(sql);

			ps.setString(1, p.getNome());			
			ps.executeUpdate();

			ps.close();
			conn.close();

		} catch (Exception e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
		}
	}
	
}
