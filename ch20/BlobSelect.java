package ch20;
import java.io.*;
import java.sql.*;
public class BlobSelect {
	public static void main(String[] args) throws SQLException {
		String driver = "oracle.jdbc.driver.OracleDriver";
		String url = "jdbc:oracle:thin:@127.0.0.1:1521:xe";
		String sql = "select photo from test where id='a1'";
		Connection conn = null;  PreparedStatement pstmt= null;
		try {
			File file = new File("kk.jpg");
			Class.forName(driver);
			conn = DriverManager.getConnection(url,"scott","tiger");
			pstmt = conn.prepareStatement(sql);
			ResultSet rs = pstmt.executeQuery();
			if (rs.next()) {
				Blob blob = rs.getBlob("photo");   // 1
				InputStream is = blob.getBinaryStream();
				FileOutputStream fos = new FileOutputStream(file);
				byte[] buffer = new byte[1024];
				int i = 0;
				while((i=is.read(buffer)) != -1) {
					fos.write(buffer, 0, i);
				}
				is.close(); fos.close(); rs.close();
				System.out.println("그림 출력 완료");
			} else System.out.println("그림 없는데");
		}catch (Exception e) {	System.out.println(e.getMessage());
		}finally {		pstmt.close();  conn.close();		}
	}
}