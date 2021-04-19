package ch20;

import java.util.Scanner;
import java.sql.*;
public class OraSelect2 {
	public static void main(String[] args) throws SQLException {
		String driver = "oracle.jdbc.driver.OracleDriver";
		String url = "jdbc:oracle:thin:@127.0.0.1:1521:xe";
		Scanner sc = new Scanner(System.in);
		System.out.println("보고싶은 부서정보 코드를 입력하세요");
		int deptno = sc.nextInt();
		String sql = "select * from dept where deptno = " + deptno;
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		try {
			Class.forName(driver);
			conn = DriverManager.getConnection(url,"scott","tiger");
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
			if(rs.next()) {
				System.out.println("부서코드 : " + deptno);
				//System.out.println("부서코드 : " + rs.getInt("deptno"));
				System.out.println("부서명 : " + rs.getString("dname"));
				System.out.println("근무지 : " + rs.getString("loc"));
				
			}else System.out.println("없는 부서입니다");
		}catch (Exception e) {
			System.out.println(e.getMessage());
		}finally {
			rs.close(); stmt.close(); conn.close();
		}
		
	}
}
