package br.gov.sp.agendacontatos.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import br.gov.sp.agendacontatos.util.Constantes;

public class DAOConnectionFactory{

	public static Connection getConnection(){
		Connection connection = null;
		
		try {
					
			connection = DriverManager.getConnection(Constantes.URL, Constantes.USER, Constantes.PASSWORD);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return connection;

	}
	
}
