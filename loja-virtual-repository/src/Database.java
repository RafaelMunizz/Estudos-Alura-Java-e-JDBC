import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


// Classe para gerenciar métodos de manipulação e consulta do banco de dados.
public class Database {
	
	ConnectionFactory createCnc = new ConnectionFactory();
	Connection cnc = null;
	
	public void printProdutos() throws SQLException {
		
		// Abrindo a conexão com o banco
		cnc = createCnc.openConnection();
		
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
		
		// Fechando a conexão com o banco
		createCnc.closeConnection(cnc);
	}
	
	public void insertProduto(String nome, String descricao) throws SQLException {
		
		// Abrindo a conexão com o banco
		cnc = createCnc.openConnection();
		
		// PreparedStatement serve para passar parâmetros para as consultas por meio de '?' na query (consulta).
		// Uso do "RETURN_GENERATED_KEYS" para que possamos obter um retorno do ID que foi inserido dessa vez.
		PreparedStatement stmt = cnc.prepareStatement("insert into produto (nome, descricao) values (?, ?)", Statement.RETURN_GENERATED_KEYS);
		
		// Setando os valores que irão ser substituídos na query.
		stmt.setString(1, nome);
		stmt.setString(2, descricao);
		
		// Executando a query
		stmt.execute();
		
		// Resultado
		ResultSet rst = stmt.getGeneratedKeys();
		
		while(rst.next()) {
			Integer id = rst.getInt(1);
			System.out.printf("ID criado: %d", id);
		}
		
		// Fechando a conexão com o banco
		createCnc.closeConnection(cnc);
	}
	
	// Método com sobrecarga para que o usuário possa passar tanto uma string e um inteiro
	// quanto duas strings como parâmetros, que servirão como a coluna e o dado a ser removido.
	public void deleteProduto(String coluna, int dado) throws SQLException {
		
		// Abrindo a conexão com o banco
		cnc = createCnc.openConnection();
		
		// PreparedStatement serve para passar parâmetros para as consultas por meio de '?' na query (consulta).
		// Uso do "RETURN_GENERATED_KEYS" para que possamos obter um retorno do ID que foi inserido dessa vez.
		PreparedStatement stmt = cnc.prepareStatement("delete from produto where (?) = (?)", Statement.RETURN_GENERATED_KEYS);
		
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
		
		// Fechando a conexão com o banco
		createCnc.closeConnection(cnc);
	}
	
	public void deleteProduto(String coluna, String dado) throws SQLException {
		
		// Abrindo a conexão com o banco
		cnc = createCnc.openConnection();
		
		// PreparedStatement serve para passar parâmetros para as consultas por meio de '?' na query (consulta).
		// Uso do "RETURN_GENERATED_KEYS" para que possamos obter um retorno do ID que foi inserido dessa vez.
		PreparedStatement stmt = cnc.prepareStatement("delete from produto where (?) = (?)", Statement.RETURN_GENERATED_KEYS);
		
		// Setando os valores que irão ser substituídos na query.
		stmt.setString(1, coluna);
		stmt.setString(2, dado);
		
		// Executando a query
		stmt.execute();
		
		// Variável que armazenará a quantidade de modificações feitas apartir da query
		Integer modifiedLines = stmt.getUpdateCount();
		
		if (modifiedLines == 0) {
			System.out.printf("\nDado não encontrado na tabela! Linhas modificadas: %d", modifiedLines);
		} else {
			System.out.printf("\nDeletado com sucesso! Linhas modificadas: %d", modifiedLines);
		}
		
		// Fechando a conexão com o banco
		createCnc.closeConnection(cnc);
	}
}
