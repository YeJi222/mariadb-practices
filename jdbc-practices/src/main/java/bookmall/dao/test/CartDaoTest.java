package bookmall.dao.test;

import java.util.List;

import bookmall.dao.BookDao;
import bookmall.dao.CartDao;
import bookmall.dao.CategoryDao;
import bookmall.vo.BookVo;
import bookmall.vo.CartVo;
import bookmall.vo.CategoryVo;

public class CartDaoTest {
	public static void main(String[] args) {
		CartVo vo1 = new CartVo();
		CartVo vo2 = new CartVo();
		
		vo1.setUserName("홍예지");
		vo1.setTitle("Do it! 점프 투 자바");
		vo1.setCount(2);
		cartInsertTest(vo1);
		
		vo2.setUserName("둘리");
		vo2.setTitle("새롭게 읽는 서양미술사");
		vo2.setCount(1);
		cartInsertTest(vo2);
		
		cartFindAllTest(); // cart table 확인 
	}

	private static void cartInsertTest(CartVo vo) {
		new CartDao().insertToCart(vo);
	}
	
	private static void cartFindAllTest() {
		List<CartVo> list = new CartDao().findAllCartBooks();

		int idx = 1;
		for(CartVo vo : list) {
			System.out.println("[" + idx++ + "] 도서 제목: " + vo.getTitle() + 
					", 수량: " + vo.getCount() + ", 가격: " + vo.getPrice());
		}
	}
	
}
