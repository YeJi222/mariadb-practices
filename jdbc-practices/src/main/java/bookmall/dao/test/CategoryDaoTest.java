package bookmall.dao.test;

import java.util.List;

import bookmall.dao.CategoryDao;
import bookmall.vo.CategoryVo;

public class CategoryDaoTest {

	public static void main(String[] args) {
		boolean result1 = categoryInsertTest("컴퓨터/IT");
		System.out.println(result1 ? "result1 - success to insert" : "result1 - fail to insert");
		
		boolean result2 = categoryInsertTest("경제");
		System.out.println(result2 ? "result2 - success to insert" : "result2 - fail to insert");
		
		findAllTest(); // category table 확인 
	}

	private static boolean categoryInsertTest(String category_name) {
		CategoryDao dao = new CategoryDao();
		
		CategoryVo vo = new CategoryVo();
		vo.setCategoryName(category_name);
		
		boolean result = dao.insertCategory(vo);
		
		return result;
	}
	
	private static void findAllTest() {
		List<CategoryVo> list = new CategoryDao().findAllCategories();

		for(CategoryVo vo : list) {
			System.out.println(vo);
		}
	}

}
