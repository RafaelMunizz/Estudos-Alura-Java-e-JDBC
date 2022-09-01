import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionFactory {
	
	public Connection openConnection(){
		
		Connection cnc = null;
		
		try {
			//"jdbc:mysql://localhost/NOME_DO_BANCO?useTimezone=true&serverTimezone=UTC", "USUÁRIO", "SENHA"
			cnc = DriverManager.getConnection("jdbc:mysql://localhost/loja_virtual?useTimezone=true&serverTimezone=UTC", "root", "root");
			//System.out.print("\nConectou!!\n");
			
		} catch (SQLException e) {
			System.out.printf("\nNão conectou!! Erro: ", e);
		}
		return cnc;
	}
	
	public void closeConnection(Connection cnc) {
		
		try {
			cnc.close();
			//System.out.print("\nConexão fechada!!");
		} catch (SQLException e) {
			System.out.printf("\nConexão não fechada!! Erro: ", e);
		}
	}
}
