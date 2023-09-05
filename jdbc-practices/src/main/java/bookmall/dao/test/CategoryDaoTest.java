package bookmall.dao.test;

import java.util.List;

import bookmall.dao.CategoryDao;
import bookmall.vo.CategoryVo;

public class CategoryDaoTest {

	public static void main(String[] args) {
		CategoryVo vo1 = new CategoryVo();
		CategoryVo vo2 = new CategoryVo();
		CategoryVo vo3 = new CategoryVo();
		vo1.setCategoryName("컴퓨터/IT");
		categoryInsertTest(vo1);
		
		vo2.setCategoryName("경제");
		categoryInsertTest(vo2);
		
		vo3.setCategoryName("예술");
		categoryInsertTest(vo3);
		
		categoryFindAllTest(); // category table 확인 
	}

	private static void categoryInsertTest(CategoryVo vo) {
		new CategoryDao().insertCategory(vo);
	}
	
	private static void categoryFindAllTest() {
		List<CategoryVo> list = new CategoryDao().findAllCategories();

		int idx = 1;
		for(CategoryVo vo : list) {
			System.out.println("[" + idx++ + "] " + "카테고리 이름: " + vo.getCategoryName());
		}
	}

}
