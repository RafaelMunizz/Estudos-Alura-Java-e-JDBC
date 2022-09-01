import java.sql.SQLException;

public class Main {

	public static void main(String[] args) throws SQLException {
		
		Database db = new Database();
		
		db.printProdutos();
		db.deleteProduto("id", 5);
		db.printProdutos();
	}
}
