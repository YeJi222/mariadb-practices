package bookmall.main;

import java.sql.Timestamp;
import java.util.List;
import java.util.Random;

import bookmall.dao.BookDao;
import bookmall.dao.CartDao;
import bookmall.dao.CategoryDao;
import bookmall.dao.MemberDao;
import bookmall.dao.OrderDao;
import bookmall.vo.BookVo;
import bookmall.vo.CartVo;
import bookmall.vo.CategoryVo;
import bookmall.vo.MemberVo;
import bookmall.vo.OrderBookVo;
import bookmall.vo.OrdersVo;

public class BookMall {

	public static void main(String[] args) {
		
		////////////////// Member DAO //////////////////
		MemberDao memberDao = new MemberDao();
		MemberVo memberVo1 = new MemberVo();
		MemberVo memberVo2 = new MemberVo();
		
		memberVo1.setName("홍예지");
		memberVo1.setPhone("010-1111-2222");
		memberVo1.setEmail("aaa@gmail.com");
		memberVo1.setPassword("0000");
		
		memberVo2.setName("둘리");
		memberVo2.setPhone("010-3333-3333");
		memberVo2.setEmail("bbb@gmail.com");
		memberVo2.setPassword("1111");
		
		memberDao.insertMember(memberVo1); // member1 insert 
		memberDao.insertMember(memberVo2); // member2 insert 
		
		System.out.println("## 회원리스트"); // 2명 
		memberFindAll(); 
		
		////////////////// Category DAO //////////////////
		CategoryDao categoryDao = new CategoryDao();
		CategoryVo categoryVo1 = new CategoryVo();
		CategoryVo categoryVo2 = new CategoryVo();
		CategoryVo categoryVo3 = new CategoryVo();
		
		categoryVo1.setCategoryName("컴퓨터/IT");
		categoryVo2.setCategoryName("경제");
		categoryVo3.setCategoryName("예술");
		
		categoryDao.insertCategory(categoryVo1);
		categoryDao.insertCategory(categoryVo2);
		categoryDao.insertCategory(categoryVo3);
		
		System.out.println();
		System.out.println("## 카테고리"); // 3개
		categoryFindAll(); 
		
		////////////////// Book DAO //////////////////
		BookDao bookDao = new BookDao();
		BookVo bookVo1 = new BookVo();
		BookVo bookVo2 = new BookVo();
		BookVo bookVo3 = new BookVo();
		
		bookVo1.setTitle("Do it! 점프 투 자바");
		bookVo1.setPrice(18900);
		bookVo1.setCategoryName("컴퓨터/IT");
		
		bookVo2.setTitle("새롭게 읽는 서양미술사");
		bookVo2.setPrice(24300);
		bookVo2.setCategoryName("예술");
		
		bookVo3.setTitle("화폐의 미래");
		bookVo3.setPrice(26820);
		bookVo3.setCategoryName("경제");
		
		bookDao.insertBook(bookVo1);
		bookDao.insertBook(bookVo2);
		bookDao.insertBook(bookVo3);
		
		System.out.println();
		System.out.println("## 상품"); // 3개
		bookFindAll(); 
		
		////////////////// Cart DAO //////////////////
		CartDao cartDao = new CartDao();
		CartVo cartVo1 = new CartVo();
		CartVo cartVo2 = new CartVo();
		
		cartVo1.setUserName("홍예지");
		cartVo1.setTitle("Do it! 점프 투 자바");
		cartVo1.setCount(2);
		
		cartVo2.setUserName("둘리");
		cartVo2.setTitle("새롭게 읽는 서양미술사");
		cartVo2.setCount(1);
		
		cartDao.insertToCart(cartVo1);
		cartDao.insertToCart(cartVo2);
		
		System.out.println();
		System.out.println("## 카트"); // 2개 
		cartFindAll(); 
		
		////////////////// Orders DAO //////////////////
		String order_code = createOrderCode();
		
		// Orders 
		OrderDao orderDao = new OrderDao();
		OrdersVo ordersVo1 = new OrdersVo();
		
		ordersVo1.setUserName("홍예지");
		ordersVo1.setAddress("서울특별시 서초구 서초대로74길 33");
		ordersVo1.setOrderCode(order_code);
		
		orderDao.insertOrders(ordersVo1);
		
		// Order_book
		OrderBookVo orderBookVo1 = new OrderBookVo();
		OrderBookVo orderBookVo2 = new OrderBookVo();
		
		orderBookVo1.setTitle("새롭게 읽는 서양미술사");
		orderBookVo1.setOrderCount(2);
		orderBookVo1.setOrderCode(order_code);
		
		orderBookVo2.setTitle("화폐의 미래");
		orderBookVo2.setOrderCount(1);
		orderBookVo2.setOrderCode(order_code);
		
		orderDao.insertOrderBook(orderBookVo1);
		orderDao.insertOrderBook(orderBookVo2);
		
		System.out.println();
		System.out.println("## 주문"); // 1개 
		ordersFindAll();
		
		System.out.println();
		System.out.println("## 주문 도서"); // 2개 
		orderBookFindAll();
	}

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

	private static void memberFindAll() {
		List<MemberVo> list = new MemberDao().findAllMembers();

		int idx = 1;
		for(MemberVo vo : list) {
			System.out.println("[" + idx++ + "] 이름: " + vo.getName() + ", 전화번호: " + vo.getPhone()
					+ ", 이메일: " + vo.getEmail() + ", 비밀번호: " + vo.getPassword());
		}
	}

	private static void categoryFindAll() {
		List<CategoryVo> list = new CategoryDao().findAllCategories();

		int idx = 1;
		for(CategoryVo vo : list) {
			System.out.println("[" + idx++ + "] 카테고리: " + vo.getCategoryName());
		}
	}
	
	private static void bookFindAll() {
		List<BookVo> list = new BookDao().findAllBooks();

		int idx = 1;
		for(BookVo vo : list) {
			System.out.println("[" + idx++ + "] 제목: " + vo.getTitle() 
				+ ", 가격: " + vo.getPrice());
		}
	}
	
	private static void cartFindAll() {
		List<CartVo> list = new CartDao().findAllCartBooks();

		int idx = 1;
		for(CartVo vo : list) {
			System.out.println("[" + idx++ + "] 도서 제목: " + vo.getTitle() + 
					", 수량: " + vo.getCount() + ", 가격: " + vo.getPrice());
		}
	}
	
	private static void ordersFindAll() {
		List<OrdersVo> list = new OrderDao().findAllOrders();

		int idx = 1;
		for(OrdersVo vo : list) {
			System.out.println("[" + idx++ + "] 주문 번호: " + vo.getOrderCode() + 
					", 주문자(이름/이메일): " + vo.getUserName() + "/" +
					vo.getEmail() + ", 결제금액: " + vo.getTotalPrice() +
					", 배송지: " + vo.getAddress());
		}
	}
	
	private static void orderBookFindAll() {
		List<OrderBookVo> list = new OrderDao().findAllOrderBook();

		int idx = 1;
		for(OrderBookVo vo : list) {
			System.out.println("[" + idx++ + "] 도서 번호: " + vo.	getBookNo() + 
					", 도서 제목: " + vo.getTitle() + 
					", 수량: " + vo.getOrderCount());
		}	
	}
}
