package bookmall.dao.test;

import java.util.List;

import bookmall.dao.BookDao;
import bookmall.vo.BookVo;

public class BookDaoTest {
	
	public static void main(String[] args) {
		BookVo vo1 = new BookVo();
		BookVo vo2 = new BookVo();
		BookVo vo3 = new BookVo();
		
		vo1.setTitle("Do it! 점프 투 자바");
		vo1.setPrice(18900);
		vo1.setCategoryName("컴퓨터/IT");
		bookInsertTest(vo1);
		
		vo2.setTitle("새롭게 읽는 서양미술사");
		vo2.setPrice(24300);
		vo2.setCategoryName("예술");
		bookInsertTest(vo2);
		
		vo3.setTitle("화폐의 미래");
		vo3.setPrice(26820);
		vo3.setCategoryName("경제");
		bookInsertTest(vo3);
		
		bookFindAllTest(); // book table 확인 
	}

	private static void bookInsertTest(BookVo vo) {
		new BookDao().insertBook(vo);
	}
	
	private static void bookFindAllTest() {
		List<BookVo> list = new BookDao().findAllBooks();

		for(BookVo vo : list) {
			System.out.println(vo);
		}
	}
}
