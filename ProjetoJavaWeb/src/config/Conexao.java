package config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class Conexao {
	
	private static Connection con = null;

	private static Connection getConnection() throws Exception {
		if (con == null) {
			Class.forName("org.postgresql.Driver").newInstance();
			con = DriverManager.getConnection("jdbc:postgresql://192.168.0.3/projeto_java_web?user=USUARIO&password=SENHA");
		}
		return con;
	}
	
	public static Statement getStatement() throws Exception {
		Connection con = Conexao.getConnection();
		return con.createStatement();
	}
}