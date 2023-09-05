package bookmall.dao.test;

import java.sql.Timestamp;
import java.util.List;
import java.util.Random;

import bookmall.dao.OrderDao;
import bookmall.vo.OrderBookVo;
import bookmall.vo.OrdersVo;

public class OrderDaoTest {
	// create order code - 주문번호 
	// format : timestamp-랜덤키 
	private static String createOrderCode() {
		String order_code = null;
		
		Timestamp timestamp = new Timestamp(System.currentTimeMillis());
		String timestampCode = String.valueOf(timestamp.getTime());
		
		Random random = new Random();
		int randomNo = random.nextInt(100); // 0 ~ 99 
		order_code = timestampCode + "-" + randomNo;
		
		return order_code;
	}

	public static void main(String[] args) {
		String order_code = createOrderCode();
		
		// Orders 
		OrdersVo vo = new OrdersVo();
		
		vo.setUserName("홍예지");
		vo.setAddress("서울특별시 서초구 서초대로74길 33");
		vo.setOrderCode(order_code);
		
		ordersInsertTest(vo);
		
		// Order_book
		OrderBookVo vo1 = new OrderBookVo();
		OrderBookVo vo2 = new OrderBookVo();
		
		vo1.setTitle("새롭게 읽는 서양미술사");
		vo1.setOrderCount(2);
		vo1.setOrderCode(order_code);
		
		vo2.setTitle("화폐의 미래");
		vo2.setOrderCount(1);
		vo2.setOrderCode(order_code);
		
		orderBookInsertTest(vo1);
		orderBookInsertTest(vo2);
		
		ordersFindAllTest(); // orders table 확인 
		orderBookFindAllTest(); // order_book table 확인 
	}
	
	private static void ordersInsertTest(OrdersVo vo) {
		new OrderDao().insertOrders(vo);
	}
	
	private static void orderBookInsertTest(OrderBookVo vo) {
		new OrderDao().insertOrderBook(vo);
	}

	private static void ordersFindAllTest() {
		List<OrdersVo> list = new OrderDao().findAllOrders();

		int idx = 1;
		System.out.println("## 주문");
		for(OrdersVo vo : list) {
			System.out.println("[" + idx++ + "] 주문 번호: " + vo.getOrderCode() + 
					", 주문자(이름/이메일): " + vo.getUserName() + "/" +
					vo.getEmail() + ", 결제금액: " + vo.getTotalPrice() +
					", 배송지: " + vo.getAddress());
		}
	}
	
	private static void orderBookFindAllTest() {
		List<OrderBookVo> list = new OrderDao().findAllOrderBook();

		int idx = 1;
		System.out.println();
		System.out.println("## 주문 도서");
		for(OrderBookVo vo : list) {
			System.out.println("[" + idx++ + "] 도서 번호: " + vo.	getBookNo() + 
					", 도서 제목: " + vo.getTitle() + 
					", 수량: " + vo.getOrderCount());
		}	
	}
}
