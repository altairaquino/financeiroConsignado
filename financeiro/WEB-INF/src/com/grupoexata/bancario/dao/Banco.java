package com.grupoexata.bancario.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Banco {
	
	private static Connection con = null;
	
	public static Connection getConnection() {
		//int m = new Date().getMinutes();
		try{				
			//if (con == null || (m%2==0)){
				Class.forName("org.firebirdsql.jdbc.FBDriver");
				con = DriverManager.getConnection("jdbc:firebirdsql:localhost:bancario?defaultResultSetHoldable=True","SYSDBA","masterkey");			
//				con = DriverManager.getConnection("jdbc:firebirdsql:firebird03.kinghost.net:/firebird/investflexsc.gdb?defaultResultSetHoldable=True","investflexsc","wwwflex123");
			//}
						
		}catch (SQLException e) {
			e.printStackTrace();
		}catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return con;
	}
		
}
