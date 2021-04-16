package ch20;

import java.sql.*;

public class Oraselect1 {
	public static void main(String[] args) throws SQLException {
		String driver = "oracle.jdbc.driver.OracleDriver";
		String url = "jdbc:oracle:thin:@127.0.0.1:1521:xe";
		Connection conn = null; // DB연결
		Statement stmt = null; // sql문장을 실행하는 객체
		ResultSet rs = null; // 조회 결과를 저장하는 객체
		String sql = "select * from dept";
		try {
			Class.forName(driver); // driver load
			conn = DriverManager.getConnection(url,"scott","tiger"); // db연결
			stmt = conn.createStatement(); // 연결된 db에 sql실행할 객체 생성
			rs = stmt.executeQuery(sql); // sql을 실행하고 그 결과를 rs에 저장
			while(rs.next()) { // 다음 데이터 읽는 명령 데이터가 있으면 실행, 없으면 종료
				System.out.println("부서코드 : "+ rs.getInt("deptno"));
				System.out.println("부서명 : "+ rs.getString("dname"));
				System.out.println("근무지 : "+ rs.getString("loc"));
				System.out.println("========================");
			}
		}catch(Exception e) {
			System.out.println(e.getMessage());
		} finally {
			rs.close(); stmt.close(); conn.close();
		}
	}
}
