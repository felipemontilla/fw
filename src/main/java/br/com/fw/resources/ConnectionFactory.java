package br.com.fw.resources;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionFactory {
	public static Connection getConnection() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			String url = "jdbc:mysql://localhost:3306/fastwaiter";
			String usuario = "root";
			String senha = "";
			return DriverManager.getConnection(url, usuario, senha);
		} catch (ClassNotFoundException | SQLException e) {
			System.out.println("Falha ao conectar ao Banco de Dados.");
			throw new ExceptionInInitializerError(e.getMessage());
		} 
	}
}
