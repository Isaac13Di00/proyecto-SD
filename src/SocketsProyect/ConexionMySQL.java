package SocketsProyect;

import java.sql.*;
import java.util.*;

public class ConexionMySQL {
	// Librería de MySQL
	private String driver = "com.mysql.jdbc.Driver";
	// Nombre de la base de datos
	private String database = "db_sd";
	// Host
	private String hostname = "localhost";
	// Puerto
	private String port = "3306";
	// Ruta de nuestra base de datos (desactivamos el uso de SSL con useSSL=false)
	private String url = "jdbc:mysql://" + hostname + ":" + port + "/" + database + "?useSSL=false";
	// Nombre de usuario
	private String username = "root";
	// Clave de usuario
	private String password = "";
//	We pero eso que me passte es para 1 sola calumna y nosotros tenemos 5
	public List<String> consultarPersonal(String query, String dato) {
		List<String> resultados = new ArrayList<>();
		int cont = 0;
		try {
			Connection conexion = DriverManager.getConnection(url, username, password);
			Statement st = conexion.createStatement();
			ResultSet rs = st.executeQuery(query);
			if (rs != null) {
				while (rs.next()) {
					String resultado = rs.getString(dato);
				    resultados.add(resultado);
				}
				rs.close();
			}
			st.close();
			conexion.close();
			return resultados;
		} catch (SQLException e) {
			System.out.println("Error: SQL.");
			System.out.println("SQLException: " + e.getMessage());
			return null;
		}
	}
}
