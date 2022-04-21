
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DbConnect {

	public static Connection getconnect() throws SQLException, ClassNotFoundException {
		Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
		String dbpath= "csdl/csdl.accdb";
		String databaseURL = "jdbc:ucanaccess://"+ dbpath;
		Connection connection = DriverManager.getConnection(databaseURL);
		if (connection==null) {
			System.out.println("Ket noi CSDL that bai");
		}else {
			System.out.println("Ket noi CSDL thanh cong");
		}
		return connection;
	}
}