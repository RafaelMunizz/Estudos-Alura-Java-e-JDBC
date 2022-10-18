package loja;
import java.sql.SQLException;

import loja.DAO.ProdutoDAO;

public class Main {

	public static void main(String[] args) throws SQLException {
		
		ProdutoDAO db = new ProdutoDAO();
		
		db.printProdutos();
		//db.deleteProduto("id", 5);
		//db.insertProduto("Cômoda", "Cômoda vertical");
		//db.printProdutos();
	}
}
