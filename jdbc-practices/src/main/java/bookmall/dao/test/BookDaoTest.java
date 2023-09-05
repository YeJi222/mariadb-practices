package bookmall.dao.test;

import java.util.List;

import bookmall.dao.BookDao;
import bookmall.dao.CategoryDao;
import bookmall.vo.BookVo;
import bookmall.vo.CategoryVo;

public class BookDaoTest {
	
	public static void main(String[] args) {
		boolean result1 = bookInsertTest("Do it! 점프 투 자바", 18900, "컴퓨터/IT");
		System.out.println(result1 ? "result1 - success to insert" : "result1 - fail to insert");
		
		boolean result2 = bookInsertTest("새롭게 읽는 서양미술사", 24300, "예술");
		System.out.println(result2 ? "result2 - success to insert" : "result2 - fail to insert");
		
		boolean result3 = bookInsertTest("화폐의 미래", 26820, "경제");
		System.out.println(result3 ? "result3 - success to insert" : "result3 - fail to insert");
		
		findAllTest(); // book table 확인 
	}

	private static boolean bookInsertTest(String title, int price, String category_name) {
		BookDao dao = new BookDao();
		
		BookVo vo = new BookVo();
		vo.setTitle(title);
		vo.setPrice(price);
		vo.setCategoryName(category_name);
		
		boolean result = dao.insertBook(vo);
		
		return result;
	}
	
	private static void findAllTest() {
		List<BookVo> list = new BookDao().findAllBooks();

		for(BookVo vo : list) {
			System.out.println(vo);
		}
	}
}
