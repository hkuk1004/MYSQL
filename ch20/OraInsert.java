package ch20;
import java.sql.*;
import java.util.Scanner;
public class OraInsert {
	public static void main(String[] args) {
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
		sc.close();
	}
}
