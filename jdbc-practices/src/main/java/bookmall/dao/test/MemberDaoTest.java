package bookmall.dao.test;

import java.util.List;

import bookmall.dao.BookDao;
import bookmall.dao.MemberDao;
import bookmall.vo.MemberVo;

public class MemberDaoTest {
	public static void main(String[] args) {
		boolean result1 = insertTest("홍예지", "010-1111-2222", "aaa@gmail.com", "0000");
		System.out.println(result1 ? "result1 - success to insert" : "result1 - fail to insert");
		
		boolean result2 = insertTest("둘리", "010-3333-4444", "bbb@gmail.com", "1111");
		System.out.println(result2 ? "result2 - success to insert" : "result2 - fail to insert");
		
		findAllTest(); // member table 확인 
	}

	private static boolean insertTest(String name, String phone, String email, String password) {
		MemberDao dao = new MemberDao();
		
		MemberVo vo = new MemberVo();
		vo.setName(name);
		vo.setPhone(phone);
		vo.setEmail(email);
		vo.setPassword(password);
		
		boolean result = dao.insertMember(vo);
		
		return result;
	}
	
	private static void findAllTest() {
		List<MemberVo> list = new MemberDao().findAllMembers();

		for(MemberVo vo : list) {
			System.out.println(vo);
		}
	}
}
