package com.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import com.model.Contato;

import conexao.ConectaPgAdmin;

public class ContatoDao {

	public void adiciona(Contato contato) {
		Connection conexao = null;
		PreparedStatement insereSt = null;
		String sql = "insert into contato(nome,endereco, email,datanascimento) values (?,?,?,?)";
		try {
			conexao = ConectaPgAdmin.getConexao();
			insereSt = (PreparedStatement) conexao.prepareStatement(sql);
			insereSt.setString(1, contato.getNome());
			insereSt.setString(2, contato.getEndereco());
			insereSt.setString(3, contato.getEmail());
			insereSt.setDate(4, new java.sql.Date(contato.getDataNascimento().getTimeInMillis()));
			// executeUpdate para insert,update,delete
			insereSt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();

		} finally {
			try {
				insereSt.close();
				conexao.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	public List<Contato> getLista() {
		Connection conexao = null;
		List<Contato> contatos = new ArrayList<Contato>();
		Statement consulta = null;
		ResultSet resultado = null;
		Contato contato = null;
		String sql = "select * from contato";
		try {
			conexao = ConectaPgAdmin.getConexao();

			// createStatement utilizamos quando executamos uma instrução SQL
			// sem parametro
			consulta = (Statement) conexao.createStatement();
			// executeQuery para seleção de dados
			resultado = consulta.executeQuery(sql);
			while (resultado.next()) {
				contato = new Contato();
				contato.setId(resultado.getInt("id"));
				contato.setNome(new String(resultado.getString("nome")));
				contato.setEmail(new String(resultado.getString("email")));
				contato.setEndereco(new String(resultado.getString("endereco")));

				Date date = resultado.getDate("datanascimento");
				Calendar dataNascimento = Calendar.getInstance();
				dataNascimento.setTime(date);
				contato.setDataNascimento(dataNascimento);
				contatos.add(contato);
			}

		} catch (SQLException e1) {
			e1.printStackTrace();
		} finally {
			try {
				resultado.close();
				conexao.close();
				consulta.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return contatos;
	}

	public void exclui(Contato contato) {
		String sql = "delete from contato where id=? ";

		try {
			Connection connection = ConectaPgAdmin.getConexao();
			PreparedStatement stmt = connection.prepareStatement(sql);

			// seta os valores
			stmt.setInt(1,contato.getId());
			
			// executa
			stmt.executeUpdate();
			stmt.close();
		} catch (Exception e) {
			
		}
	}

}
