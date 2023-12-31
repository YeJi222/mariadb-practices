package bookmall.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import bookmall.vo.CartVo;

public class CartDao {

	public void insertToCart(CartVo vo) {
		Connection conn = null;
		PreparedStatement pstmt1 = null;
		PreparedStatement pstmt2 = null;
		PreparedStatement pstmt3 = null;
		ResultSet rs1 = null;
		ResultSet rs2 = null;
		
		try {
			conn = getConnection();

			// get member_no using name
			String selectSql1 = 
					"select no from member" +
					" where name = ?";
			pstmt1 = conn.prepareStatement(selectSql1);
			pstmt1.setString(1, vo.getUserName());
			
			rs1 = pstmt1.executeQuery();
			int member_no = 0;
			if(rs1.next()) {
				member_no = rs1.getInt(1);
			}
			
			// get book_no using title
			String selectSql2 = 
					"select no from book" +
					" where title = ?";
			pstmt2 = conn.prepareStatement(selectSql2);
			pstmt2.setString(1, vo.getTitle());
			
			rs2 = pstmt2.executeQuery();
			int book_no = 0;
			if(rs2.next()) {
				book_no = rs2.getInt(1);
			}
			
			String insertSql =
					"insert" +
					" into cart(member_no, book_no, count)" +
					" values (?, ?, ?)";
			pstmt3 = conn.prepareStatement(insertSql);
			// binding
			pstmt3.setInt(1, member_no);
			pstmt3.setInt(2, book_no);
			pstmt3.setInt(3, vo.getCount());
			
			pstmt3.executeUpdate();
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
				if(pstmt3 != null) {
					pstmt3.close();
				}
				if(rs1 != null) {
					rs1.close();
				}
				if(rs2 != null) {
					rs2.close();
				}
				if(conn != null) {
					conn.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	public List<CartVo> findAllCartBooks() {
		List<CartVo> result = new ArrayList<>();
		
		Connection conn = null;
		PreparedStatement pstmt1 = null;
		PreparedStatement pstmt2 = null;
		ResultSet rs1 = null;
		ResultSet rs2 = null;
		
		try {
			conn = getConnection();

			String sql1 = "select * from cart;";
			pstmt1 = conn.prepareStatement(sql1);
			rs1 = pstmt1.executeQuery();
			
			while(rs1.next()) {
				int no = rs1.getInt(1);
				int member_no = rs1.getInt(2);
				int book_no = rs1.getInt(3);
				int count = rs1.getInt(4);
				
				CartVo vo = new CartVo();
				vo.setNo(no);
				vo.setMemberNo(member_no);
				vo.setBookNo(book_no);
				vo.setCount(count);
				
				String sql2 = "select title, price from book where no = ?;";
				pstmt2 = conn.prepareStatement(sql2);
				pstmt2.setInt(1, book_no);
				rs2 = pstmt2.executeQuery();
				
				if(rs2.next()) {
					String title = rs2.getString(1);
					int price = rs2.getInt(2);
					
					vo.setTitle(title);
					vo.setPrice(price * count);
				}
				result.add(vo);
			}
			
		} catch (SQLException e) {
			System.out.println("error:" + e);
		} finally {
			try {
				if(rs1 != null) {
					rs1.close();
				}
				if(rs2 != null) {
					rs2.close();
				}
				if(pstmt1 != null) {
					pstmt1.close();
				}
				if(pstmt2 != null) {
					pstmt2.close();
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
