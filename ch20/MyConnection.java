package ch20;
import java.sql.*;
public class MyConnection {
	public static void main(String[] args) {
		String driver = "com.mysql.cj.jdbc.Driver";
		String url = "jdbc:mysql://127.0.0.1:3306/test?useSSL=false&serverTimezone=UTC";
		try {
			Class.forName(driver);
			Connection conn = DriverManager.getConnection(url,"root","mysql");
			System.out.println("연결 성공 !!");
			conn.close();
		} catch(Exception e) {
			System.out.println("연결 실패 : " + e.getMessage());
		}

	}
}
