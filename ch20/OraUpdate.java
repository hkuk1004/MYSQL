package ch20;
import java.sql.*;
import java.util.Scanner;
public class OraUpdate {
	public static void main(String[] args) throws SQLException {
		String driver = "oracle.jdbc.driver.OracleDriver";
		String url = "jdbc:oracle:thin:@127.0.0.1:1521:xe";
		Connection conn = null;
		PreparedStatement pstmt = null;
		Scanner sc = new Scanner(System.in);
		System.out.println("수정할 부서코드 ? ");
		int deptno = Integer.parseInt(sc.nextLine());
		System.out.println("수정할 부서명 ? ");
		String dname = sc.nextLine();
		System.out.println("수정할 근무지 ? ");
		String loc = sc.nextLine();
		String sql = "update dept set dname=?,loc=? where deptno=?";
		try {
			Class.forName(driver);
			conn = DriverManager.getConnection(url,"scott","tiger");
			pstmt = conn.prepareStatement(sql);// 여기서 sql
			pstmt.setInt(3, deptno); // 세번 째 ? 에는 deptno
			pstmt.setString(1, dname); // 첫번 째 ? 에는 dname
			pstmt.setString(2, loc); // 두번 째 ? 에는 loc
			int result = pstmt.executeUpdate(); // 여기는 sql이 없음
			if(result > 0) System.out.println("수정 성공!!");
			else System.out.println("수정 실패ㅠㅠ");
		}catch (Exception e) {
			System.out.println("수정 실패 : "+ e.getMessage());
		}finally {
			pstmt.close(); conn.close();
		}
		sc.close();
	}
}
