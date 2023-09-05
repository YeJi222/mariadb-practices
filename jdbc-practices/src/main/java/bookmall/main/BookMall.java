package bookmall.main;

import java.util.List;

import bookmall.dao.CategoryDao;
import bookmall.dao.MemberDao;
import bookmall.vo.CategoryVo;
import bookmall.vo.MemberVo;

public class BookMall {

	public static void main(String[] args) {
		// Member DAO
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
		
		// Category DAO
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
		
		// Book DAO
		
		
		
//		
//		System.out.println("## 상품"); // 3개(제목, 가격만 나오게)
//		
//		
//		System.out.println("## 카트"); // 2개 
//		
//		
//		System.out.println("## 주문"); // 1개 
//		
//		
//		System.out.println("## 주문 도서"); // 2개 

	}

	private static void categoryFindAll() {
		List<CategoryVo> list = new CategoryDao().findAllCategories();

		int idx = 1;
		for(CategoryVo vo : list) {
			System.out.println("[" + idx++ + "] 카테고리: " + vo.getCategoryName());
		}
	}

	private static void memberFindAll() {
		List<MemberVo> list = new MemberDao().findAllMembers();

		int idx = 1;
		for(MemberVo vo : list) {
			System.out.println("[" + idx++ + "] 이름: " + vo.getName() + ", 전화번호: " + vo.getPhone()
					+ ", 이메일: " + vo.getEmail() + ", 비밀번호: " + vo.getPassword());
		}
	}

}
