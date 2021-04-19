package ch20;
import java.sql.*;
import java.util.Scanner;
public class MyUpdate {
	public static void main(String[] args) throws SQLException{
		String driver = "com.mysql.cj.jdbc.Driver";
		String url = "jdbc:mysql://127.0.0.1:3306/test?useSSL=false&serverTimezone=UTC&allowPublicKeyRetrieval=true";
		Connection conn = null;
		PreparedStatement pstmt = null;
		Scanner sc = new Scanner(System.in);
		System.out.println("수정할 부서코드");
		int deptno = Integer.parseInt(sc.nextLine());
		System.out.println("수정할 부서명");
		String dname = sc.nextLine();
		System.out.println("수정할 근무지");
		String loc = sc.nextLine();
		String sql = "update dept set dname=?,loc=? where deptno=?";
		try {
			Class.forName(driver);
			conn = DriverManager.getConnection(url,"root","mysql");
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, dname);
			pstmt.setString(2, loc);
			pstmt.setInt(3, deptno);
			int result = pstmt.executeUpdate();
			if(result > 0) System.out.println("수정 성공!!");
			else System.out.println("수정 실패ㅠㅠ");
		}catch(Exception e) {
			System.out.println("수정 실패 : " + e.getMessage());
		}finally {
			pstmt.close(); conn.close();
		}
	}
}
