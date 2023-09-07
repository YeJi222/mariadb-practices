package bookmall.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import bookmall.vo.BookVo;

public class BookDao {

	public void insertBook(BookVo vo) {
		Connection conn = null;
		PreparedStatement pstmt1 = null;
		PreparedStatement pstmt2 = null;
		ResultSet rs = null;
		
		try {
			conn =  getConnection();
			
			String selectSql = 
					"select no from category" +
					" where category_name = ?";
			pstmt1 = conn.prepareStatement(selectSql);
			pstmt1.setString(1, vo.getCategoryName());
			
			rs = pstmt1.executeQuery();
			int category_no = 0;
			if(rs.next()) {
				category_no = rs.getInt(1);
			}
			
			String insertSql =
					"insert" +
					" into book(category_no, title, price)" +
					" values (?, ?, ?)";
			pstmt2 = conn.prepareStatement(insertSql);
			// binding
			pstmt2.setInt(1, category_no);
			pstmt2.setString(2, vo.getTitle());
			pstmt2.setInt(3, vo.getPrice());
			
			pstmt2.executeUpdate();
		} catch (SQLException e) {
			System.out.println("error:" + e);
		} finally {
			try {
				if(pstmt1 != null) {
					pstmt1.close();
				}
				if(pstmt2 != null) {
					pstmt2.close();
				}
				if(rs != null) {
					rs.close();
				}
				if(conn != null) {
					conn.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	public List<BookVo> findAllBooks() {
		List<BookVo> result = new ArrayList<>();
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			conn = getConnection();

			String sql = "select * from book;";
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();

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
			
		} catch (SQLException e) {
			System.out.println("error:" + e);
		} finally {
			try {
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

	private Connection getConnection() throws SQLException {
		Connection conn = null;
		
		try {
			Class.forName("org.mariadb.jdbc.Driver");
			String url = "jdbc:mariadb://192.168.64.9:3307/bookmall?charset=utf8";
			conn = DriverManager.getConnection(url, "bookmall", "bookmall");
		} catch (ClassNotFoundException e) {
			System.out.println("드라이버 로딩 실패:" + e);
		}
		
		return conn;
	}
}
