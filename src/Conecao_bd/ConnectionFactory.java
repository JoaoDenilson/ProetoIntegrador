
package Conecao_bd;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionFactory {
    public Connection getConnection() {
		try {
			return DriverManager.getConnection("jdbc:mysql://localhost/projetointegrador?useTimezone=true&serverTimezone=UTC","root","");
                        //return DriverManager.getConnection("jdbc:mysql://localhost/projetointegrador","root","");
		} catch(SQLException e) {
			throw new RuntimeException(e);
		}
	}
}
