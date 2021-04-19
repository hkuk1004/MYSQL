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
		System.out.println("������ �μ��ڵ� ? ");
		int deptno = Integer.parseInt(sc.nextLine());
		System.out.println("������ �μ��� ? ");
		String dname = sc.nextLine();
		System.out.println("������ �ٹ��� ? ");
		String loc = sc.nextLine();
		String sql = "update dept set dname=?,loc=? where deptno=?";
		try {
			Class.forName(driver);
			conn = DriverManager.getConnection(url,"scott","tiger");
			pstmt = conn.prepareStatement(sql);// ���⼭ sql
			pstmt.setInt(1, deptno); // ù�� ° ? ���� deptno
			pstmt.setString(2, dname); // �ι� �� ? ���� dname
			pstmt.setString(3, loc); // ���� ° ? ���� loc
			int result = pstmt.executeUpdate(); // ����� sql�� ����
			if(result > 0) System.out.println("���� ����!!");
			else System.out.println("���� ���ФФ�");
		}catch (Exception e) {
			System.out.println("���� ���� : "+ e.getMessage());
		}finally {
			pstmt.close(); conn.close();
		}
		sc.close();
	}
}