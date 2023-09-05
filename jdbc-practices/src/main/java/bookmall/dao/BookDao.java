package bookmall.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import bookmall.vo.BookVo;
import bookmall.vo.CategoryVo;

public class BookDao {

	public boolean insertBook(BookVo vo) {
		boolean result = false;
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		PreparedStatement pstmt2 = null;
		ResultSet rs = null;
		
		try {
			//1. JDBC Driver Class 로딩
			Class.forName("org.mariadb.jdbc.Driver");
			
			//2. 연결하기
			String url = "jdbc:mariadb://192.168.64.9:3307/bookmall?charset=utf8";
			conn = DriverManager.getConnection(url, "bookmall", "bookmall");

			//3. SQL 준비 
			// get category_no using category_name
			String selectSql = 
					"select no from category" +
					" where category_name = ?";
			pstmt = conn.prepareStatement(selectSql); // sql 준비 
			pstmt.setString(1, vo.getCategoryName()); // binding
			
			rs = pstmt.executeQuery();
			int category_no = 0;
			if(rs.next()) {
				category_no = rs.getInt(1);
			}
			System.out.println("category_no :" + category_no);
			
			String insertSql =
					"insert" +
					" into book(category_no, title, price)" +
					" values (?, ?, ?)";
			pstmt2 = conn.prepareStatement(insertSql); // sql 준비 
			// binding
			pstmt2.setInt(1, category_no);
			pstmt2.setString(2, vo.getTitle());
			pstmt2.setInt(3, vo.getPrice());
			
			//4. SQL 실행
			int count = pstmt2.executeUpdate();
			
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

	public List<BookVo> findAllBooks() {
		List<BookVo> result = new ArrayList<>();
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			//1. JDBC Driver Class 로딩
			Class.forName("org.mariadb.jdbc.Driver");
			
			//2. 연결하기
			String url = "jdbc:mariadb://192.168.64.9:3307/bookmall?charset=utf8";
			conn = DriverManager.getConnection(url, "bookmall", "bookmall");

			//3. SQL 준비 
			String sql = "select * from book;";
			pstmt = conn.prepareStatement(sql);
			
			//4. SQL 실행
			rs = pstmt.executeQuery();
			
			//5. 결과 처리
			while(rs.next()) {
				int no = rs.getInt(1);
				int category_no = rs.getInt(2);
				String title = rs.getString(3);
				int price = rs.getInt(4);
				
				BookVo vo = new BookVo();
				vo.setNo(no);
				vo.setCategoryNo(category_no);
				vo.setTitle(title);
				vo.setPrice(price);
				
				result.add(vo);
			}
			
		} catch (ClassNotFoundException e) {
			System.out.println("드라이버 로딩 실패:" + e);
		} catch (SQLException e) {
			System.out.println("error:" + e);
		} finally {
			try {
				// 6. 자원정리
				if(rs != null) {
					rs.close();
				}
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
