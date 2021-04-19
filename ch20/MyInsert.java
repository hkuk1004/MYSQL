package ch20;
import java.sql.*;
import java.util.Scanner;
public class MyInsert {
	public static void main(String[] args) throws SQLException {
		String driver = "com.mysql.cj.jdbc.Driver";
		String url = "jdbc:mysql://127.0.0.1:3306/test?useSSL=false&serverTimezone=UTC&allowPublicKeyRetrieval=true";
		Connection conn = null; 
		Statement stmt = null;
		Scanner sc = new Scanner(System.in);
		System.out.println("부서코드 ? ");
		String deptno = sc.nextLine();
		System.out.println("부서명 ? ");
		String dname = sc.nextLine();
		System.out.println("근무지 ? ");
		String loc = sc.nextLine();
		String sql = String.format("insert into dept values (%s,'%s','%s')",deptno,dname,loc );
		try {
			Class.forName(driver);
			conn = DriverManager.getConnection(url,"root","mysql");
			stmt = conn.createStatement();
			int result = stmt.executeUpdate(sql);
			if(result > 0) System.out.println("입력 성공!!");
			else System.out.println("입력 실패ㅠㅠ");
		}catch (Exception e) {
			System.out.println("입력 실패 : " + e.getMessage());
		}finally {
			stmt.close(); conn.close();
		}
		sc.close();
	}
}
