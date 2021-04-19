package ch20;
import java.sql.*;
import java.util.Scanner;
// 사번을 입력 받아서 사원정보 출력(mySql)
public class Myselect2 {
	public static void main(String[] args) throws SQLException {
		String driver = "com.mysql.cj.jdbc.Driver";
		String url = "jdbc:mysql://127.0.0.1:3306/test?useSSL=false&serverTimezone=UTC";
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		Scanner sc = new Scanner(System.in);
		System.out.println("보고싶은 직원의 사번을 입력하세요 : ");
		int empno = sc.nextInt();
		String sql = "select * from emp where empno = " + empno;
		try {
			Class.forName(driver);
			conn = DriverManager.getConnection(url, "root","mysql");
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
			if(rs.next()) {
				System.out.println("사번 : " + rs.getInt("empno")); //rs.getInt(1)
				System.out.println("이름 : " + rs.getString("ename")); // rs.getString(3)
				System.out.println("업무 : " + rs.getString("job"));// rs.getString(4)
				System.out.println("관리자 : " + rs.getInt("mgr")); //rs.getInt(4)
				System.out.println("입사일 : " + rs.getDate("hiredate")); // rs.getDate(5)
				System.out.println("급여 : " + rs.getInt("sal")); //rs.getInt(6)
				System.out.println("comm : " + rs.getInt("comm")); //rs.getInt(7)
				System.out.println("부서코드 : " + rs.getInt("deptno")); //rs.getInt(8)
			}else System.out.println("없는 직원입니다!!");
		}catch(Exception e) {
			System.out.println(e.getMessage());
		}finally {
			rs.close(); stmt.close(); conn.close();
		}
		sc.close();
	}
}
