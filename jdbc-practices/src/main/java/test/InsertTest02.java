package test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class InsertTest02 {

	public static void main(String[] args) {
		boolean result = insertDepartment("QA팀");
		System.out.println(result ? "성공" : "실패");
	}

	private static boolean insertDepartment(String name) {
		boolean result = false;
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		try {
			//1. JDBC Driver Class 로딩
			Class.forName("org.mariadb.jdbc.Driver");
			
			//2. 연결하기
			String url = "jdbc:mariadb://192.168.64.9:3307/webdb?charset=utf8";
			conn = DriverManager.getConnection(url, "webdb", "webdb");

			//3. SQL 준비 
			String sql =
					"insert" +
					" into dept" +
					" values (?, ?)";
			pstmt = conn.prepareStatement(sql); // sql 준비 
			
			//4. 값 binding
			pstmt.setLong(1, 1);
			pstmt.setString(2, name);
			
			//4. SQL 실행
			int count = pstmt.executeUpdate(); // 인자로 sql 주면 안됨 
			
			//5. 결과 처리
			result = count == 1;
		} catch (ClassNotFoundException e) {
			System.out.println("드라이버 로딩 실패:" + e);
		} catch (SQLException e) {
			System.out.println("error:" + e);
		} finally {
			try {
				// 6. 자원정리
				if(pstmt != null) {
					pstmt.close();
				}
				if(conn != null) {
					conn.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}		
		
		return result;
	}

}
