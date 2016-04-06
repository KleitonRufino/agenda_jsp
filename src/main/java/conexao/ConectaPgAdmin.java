package conexao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConectaPgAdmin {
		public static Connection getConexao() throws SQLException {  
		     try {  
		             Class.forName("org.postgresql.Driver");  
		             return DriverManager.getConnection("jdbc:postgresql://localhost:5432/agenda","postgres","postgres");  
		     }  
		     catch (ClassNotFoundException e) {  
		             throw new SQLException(e.getMessage());  
		     }  
		} 
	}

