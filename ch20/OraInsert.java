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
		System.out.println("�μ��ڵ� ?");
		int deptno = Integer.parseInt(sc.nextLine());
		System.out.println("�μ��� ?");
		String dname = sc.nextLine();
		System.out.println("�ٹ��� ?");
		String loc = sc.nextLine();
		String sql = String.format("insert into dept values(%d,'%s','%s')",deptno,dname,loc); 
		sc.close();
	}
}
