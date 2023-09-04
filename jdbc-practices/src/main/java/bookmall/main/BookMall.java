package bookmall.main;

public class BookMall {

	public static void main(String[] args) {
		MemberDao memberDao = new MemberDao();
		
		memberDao.insert(memberVo1);
		memberDao.insert(memberVo2);
		
		System.out.println("## 회원리스트"); // 2명 
		
		
		System.out.println("## 카테고리"); // 3개 
		
		
		
		System.out.println("## 상품"); // 3개(제목, 가격만 나오게)
		
		
		System.out.println("## 카트"); // 2개 
		
		
		System.out.println("## 주문"); // 1개 
		
		
		System.out.println("## 주문 도서"); // 2개 

	}

}
