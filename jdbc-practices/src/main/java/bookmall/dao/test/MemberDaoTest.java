package bookmall.dao.test;

import java.util.List;

import bookmall.dao.MemberDao;
import bookmall.vo.MemberVo;

public class MemberDaoTest {
	public static void main(String[] args) {
		MemberVo vo1 = new MemberVo();
		MemberVo vo2 = new MemberVo();
		vo1.setName("홍예지");
		vo1.setPhone("010-1111-2222");
		vo1.setEmail("aaa@gmail.com");
		vo1.setPassword("0000");
		memberInsertTest(vo1);
		
		vo2.setName("둘리");
		vo2.setPhone("010-3333-4444");
		vo2.setEmail("bbb@gmail.com");
		vo2.setPassword("1111");
		memberInsertTest(vo2);
		
		memberFindAllTest(); // member table 확인 
	}

	private static void memberInsertTest(MemberVo vo) {
		new MemberDao().insertMember(vo);
	}
	
	private static void memberFindAllTest() {
		List<MemberVo> list = new MemberDao().findAllMembers();

		for(MemberVo vo : list) {
			System.out.println("이름: " + vo.getName() + ", 전화번호: " + vo.getPhone()
					+ ", 이메일: " + vo.getEmail() + ", 비밀번호: " + vo.getPassword());
		}
	}
}
