package loja.DAO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import loja.ConnectionFactory;


// Classe para gerenciar métodos de manipulação e consulta do banco de dados.
public class ProdutoDAO {
	
	ConnectionFactory createCnc = new ConnectionFactory();
	
	public void printProdutos() throws SQLException {
		
		// Abrindo a conexão com o banco
		try(Connection cnc = createCnc.openConnection()){	
			// Usar "Statement" quando a query sql não receber alterações para executar sua função
			Statement stmt = cnc.createStatement();
			stmt.execute("select id, nome, descricao from produto");
			
			// Resultado
			ResultSet rst = stmt.getResultSet();
			
			System.out.println();
			while(rst.next()) {
				Integer id = rst.getInt("id");
				String nome = rst.getString("nome");
				String descricao = rst.getString("descricao");
				
				System.out.printf("| ID: %s | NOME: %s | DESCRIÇÃO: %s |\n", id, nome, descricao);
			}
		
		// Fechando a conexão com o banco (OldConnectionFactory)
		//createCnc.closeConnection(cnc);
		}
	}
	
	public void insertProduto(String nome, String descricao) throws SQLException {
		
		// Abrindo a conexão com o banco
		Connection cnc = createCnc.openConnection();
		
		cnc.setAutoCommit(false);
		
		// PreparedStatement serve para passar parâmetros para as consultas por meio de '?' na query (consulta).
		// Uso do "RETURN_GENERATED_KEYS" para que possamos obter um retorno do ID que foi inserido dessa vez.
		try (PreparedStatement stmt = cnc.prepareStatement("insert into produto (nome, descricao) values (?, ?)", Statement.RETURN_GENERATED_KEYS);){
			
			
			// Setando os valores que irão ser substituídos na query.
			stmt.setString(1, nome);
			stmt.setString(2, descricao);
			
			// Executando a query
			stmt.execute();
			
			// Resultado
			try(ResultSet rst = stmt.getGeneratedKeys()){
			
				while(rst.next()) {
					Integer id = rst.getInt(1);
					System.out.printf("ID criado: %d", id);
				}
			}
			
			// Validando a transação
			cnc.commit();
			
			// Fechando a conexão com o banco (OldConnectionFactory)
			//createCnc.closeConnection(cnc);
			
		} catch(Exception e) {
			e.printStackTrace();
			System.out.println("!!!ROLLBACK EXECUTADO!!!");
			// Retornar os dados em caso de erro para que não sejam perdidos
			cnc.rollback();
		}
		
	}
	
	// Método com sobrecarga para que o usuário possa passar tanto uma string e um inteiro
	// quanto duas strings como parâmetros, que servirão como a coluna e o dado a ser removido.
	public void deleteProduto(String coluna, int dado) throws SQLException {
		
		// Abrindo a conexão com o banco
		try(Connection cnc = createCnc.openConnection()){
		
			// PreparedStatement serve para passar parâmetros para as consultas por meio de '?' na query (consulta).
			// Uso do "RETURN_GENERATED_KEYS" para que possamos obter um retorno do ID que foi inserido dessa vez.
			PreparedStatement stmt = cnc.prepareStatement("delete from produto where ? = ?", Statement.RETURN_GENERATED_KEYS);
			
			// Setando os valores que irão ser substituídos na query.
			stmt.setString(1, coluna);
			stmt.setInt(2, dado);
			
			// Executando a query
			stmt.execute();
			
			// Variável que armazenará a quantidade de modificações feitas apartir da query
			Integer modifiedLines = stmt.getUpdateCount();
			
			if (modifiedLines == 0) {
				System.out.printf("\nDado não encontrado na tabela! Linhas modificadas: %d", modifiedLines);
			} else {
				System.out.printf("\nDeletado com sucesso! Linhas modificadas: %d", modifiedLines);
			}
			
			// Fechando a conexão com o banco (OldConnectionFactory)
			//createCnc.closeConnection(cnc);
		}
	}
	
	public void deleteProduto(String coluna, String dado) throws SQLException {
		
		// Abrindo a conexão com o banco
		try( Connection cnc = createCnc.openConnection()){
			
			// PreparedStatement serve para passar parâmetros para as consultas por meio de '?' na query (consulta).
			// Uso do "RETURN_GENERATED_KEYS" para que possamos obter um retorno do ID que foi inserido dessa vez.
			//PreparedStatement stmt = cnc.prepareStatement("delete from produto where " + coluna + " = "  + dado, Statement.RETURN_GENERATED_KEYS);
			PreparedStatement stmt = cnc.prepareStatement("delete from produto where ? = ?", Statement.RETURN_GENERATED_KEYS);
			// Setando os valores que irão ser substituídos na query.
			stmt.setString(1, coluna);
			stmt.setString(2, dado);
			
			System.out.println(stmt);
			System.out.println(coluna);
			
			// Executando a query
			stmt.execute();
			
			// Variável que armazenará a quantidade de modificações feitas apartir da query
			Integer modifiedLines = stmt.getUpdateCount();
			 
			if (modifiedLines == 0) {
				System.out.printf("\nDado não encontrado na tabela! Linhas modificadas: %d", modifiedLines);
			} else {
				System.out.printf("\nDeletado com sucesso! Linhas modificadas: %d", modifiedLines);
			}
			
			// Fechando a conexão com o banco (OldConnectionFactory)
			//createCnc.closeConnection(cnc);
		}
	}
}
