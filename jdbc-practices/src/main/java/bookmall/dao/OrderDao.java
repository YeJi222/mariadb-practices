package bookmall.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import bookmall.vo.OrderBookVo;
import bookmall.vo.OrdersVo;

public class OrderDao {

	public void insertOrders(OrdersVo vo) {
		Connection conn = null;
		PreparedStatement pstmt1 = null;
		PreparedStatement pstmt2 = null;
		ResultSet rs = null;
		
		try {
			conn = getConnection();

			// get member_no using name
			String selectSql1 = 
					"select no from member" +
					" where name = ?";
			pstmt1 = conn.prepareStatement(selectSql1);
			pstmt1.setString(1, vo.getUserName());
			
			rs = pstmt1.executeQuery();
			int member_no = 0;
			if(rs.next()) {
				member_no = rs.getInt(1);
			}
			
			String insertSql =
					"insert" +
					" into orders(member_no, total_price, address, order_code)" +
					" values (?, 0, ?, ?)";
			pstmt2 = conn.prepareStatement(insertSql);
			// binding
			pstmt2.setInt(1, member_no);
			pstmt2.setString(2, vo.getAddress());
			pstmt2.setString(3, vo.getOrderCode());
			
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
	
	public void insertOrderBook(OrderBookVo vo) {
		Connection conn = null;
		PreparedStatement pstmt1 = null;
		PreparedStatement pstmt2 = null;
		PreparedStatement pstmt3 = null;
		PreparedStatement pstmt4 = null;
		PreparedStatement pstmt5 = null;
		PreparedStatement pstmt6 = null;
		ResultSet rs1 = null;
		ResultSet rs2 = null;
		ResultSet rs3 = null;
		ResultSet rs4 = null;
		
		try {
			conn = getConnection();

			// get book_no using title
			String selectSql1 = 
					"select no from book" +
					" where title = ?";
			pstmt1 = conn.prepareStatement(selectSql1);
			pstmt1.setString(1, vo.getTitle());
			
			rs1 = pstmt1.executeQuery();
			int book_no = 0;
			if(rs1.next()) {
				book_no = rs1.getInt(1);
			}
			
			// get order_no using order_code
			String selectSql2 = 
					"select no from orders" +
					" where order_code = ?";
			pstmt2 = conn.prepareStatement(selectSql2); 
			pstmt2.setString(1, vo.getOrderCode());
			
			rs2 = pstmt2.executeQuery();
			
			int order_no = 0;
			if(rs2.next()) {
				order_no = rs2.getInt(1);
			}
			
			String insertSql =
					"insert" +
					" into order_book(order_no, book_no, count)" +
					" values (?, ?, ?)";
			pstmt3 = conn.prepareStatement(insertSql);
			// binding
			pstmt3.setInt(1, order_no);
			pstmt3.setInt(2, book_no);
			pstmt3.setInt(3, vo.getOrderCount());
			
			pstmt3.executeUpdate();
			
			// update total_price 
			String selectSql3 = "select total_price from orders where no = ?;";
			pstmt4 = conn.prepareStatement(selectSql3);
			pstmt4.setInt(1, order_no);
			rs4 = pstmt4.executeQuery();
			int total_price = 0;
			if(rs4.next()) {
				total_price = rs4.getInt(1);
			}
			
			String selectSql4 = "select price from book where no = ?;";
			pstmt5 = conn.prepareStatement(selectSql4);
			pstmt5.setInt(1, book_no);
			rs3 = pstmt5.executeQuery();
			
			if(rs3.next()) {
				int price = rs3.getInt(1);
				total_price += (price * vo.getOrderCount());
			}
			
			String updateSql = "update orders set total_price = ?"
					+ " where no = ?;";
			pstmt6 = conn.prepareStatement(updateSql);
			pstmt6.setInt(1, total_price);
			pstmt6.setInt(2, order_no);
			pstmt6.executeQuery();
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
				if(pstmt4 != null) {
					pstmt4.close();
				}
				if(pstmt5 != null) {
					pstmt5.close();
				}
				if(pstmt6 != null) {
					pstmt6.close();
				}
				if(rs1 != null) {
					rs1.close();
				}
				if(rs2 != null) {
					rs2.close();
				}
				if(rs3 != null) {
					rs3.close();
				}
				if(rs4 != null) {
					rs4.close();
				}
				if(conn != null) {
					conn.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	public List<OrdersVo> findAllOrders() {
		List<OrdersVo> result = new ArrayList<>();
		
		Connection conn = null;
		PreparedStatement pstmt1 = null;
		PreparedStatement pstmt2 = null;
		ResultSet rs1 = null;
		ResultSet rs2 = null;
		
		try {
			conn = getConnection();

			String sql1 = "select * from orders;";
			pstmt1 = conn.prepareStatement(sql1);
			rs1 = pstmt1.executeQuery();
			
			while(rs1.next()) {
				int no = rs1.getInt(1);
				int member_no = rs1.getInt(2);
				int total_price = rs1.getInt(3);
				String address = rs1.getString(4);
				String order_code = rs1.getString(5);
				
				OrdersVo vo = new OrdersVo();
				vo.setNo(no);
				vo.setMemberNo(member_no);
				vo.setTotalPrice(total_price);
				vo.setAddress(address);
				vo.setOrderCode(order_code);
				
				String sql2 = "select name, email from member where no = ?;";
				pstmt2 = conn.prepareStatement(sql2);
				pstmt2.setInt(1, member_no);
				rs2 = pstmt2.executeQuery();
				
				if(rs2.next()) {
					String name = rs2.getString(1);
					String email = rs2.getString(2);
					
					vo.setUserName(name);
					vo.setEmail(email);
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
	
	public List<OrderBookVo> findAllOrderBook() {
		List<OrderBookVo> result = new ArrayList<>();
		
		Connection conn = null;
		PreparedStatement pstmt1 = null;
		PreparedStatement pstmt2 = null;
		ResultSet rs1 = null;
		ResultSet rs2 = null;
		
		try {
			conn = getConnection();

			String sql1 = "select book_no, count from order_book;";
			pstmt1 = conn.prepareStatement(sql1);
			rs1 = pstmt1.executeQuery();
			
			while(rs1.next()) {
				int book_no = rs1.getInt(1);
				int orderCount = rs1.getInt(2);
				
				OrderBookVo vo = new OrderBookVo();
				vo.setBookNo(book_no);
				vo.setOrderCount(orderCount);
				
				String sql2 = "select title from book where no = ?;";
				pstmt2 = conn.prepareStatement(sql2);
				pstmt2.setInt(1, book_no);
				rs2 = pstmt2.executeQuery();
				
				if(rs2.next()) {
					String title = rs2.getString(1);
					vo.setTitle(title);
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
