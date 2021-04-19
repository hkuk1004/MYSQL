package ch20;
import java.sql.*;
import java.util.Scanner;
public class OraInsert {
	public static void main(String[] args) throws SQLException {
		String driver = "oracle.jdbc.driver.OracleDriver";
		String url = "jdbc:oracle:thin:@127.0.0.1:1521:xe";
		Connection conn = null;
		Statement stmt = null;
		Scanner sc = new Scanner(System.in);
		System.out.println("부서코드 ?");
		int deptno = Integer.parseInt(sc.nextLine());
		System.out.println("부서명 ?");
		String dname = sc.nextLine();
		System.out.println("근무지 ?");
		String loc = sc.nextLine();
		String sql = String.format("insert into dept values(%d,'%s','%s')",deptno,dname,loc); 
		try {
			Class.forName(driver);
			conn = DriverManager.getConnection(url,"scott","tiger");
			stmt = conn.createStatement();
			int result = stmt.executeUpdate(sql);
			if(result > 0) System.out.println("입력 성공!!");
			else System.out.println("입력 실패ㅠㅠ");
		}catch(Exception e) {
			System.out.println(e.getMessage());
		}finally {
			stmt.close(); conn.close(); 
		}
		sc.close();
	}
}
