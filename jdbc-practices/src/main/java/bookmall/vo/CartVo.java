package bookmall.vo;

public class CartVo {
	private int no;
	private int member_no;
	private int book_no;
	private int count;
	private String userName;
	private String title;
	private int price;
	
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public int getNo() {
		return no;
	}
	public void setNo(int no) {
		this.no = no;
	}
	public int getMemberNo() {
		return member_no;
	}
	public void setMemberNo(int member_no) {
		this.member_no = member_no;
	}
	public int getBookNo() {
		return book_no;
	}
	public void setBookNo(int book_no) {
		this.book_no = book_no;
	}
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
	
	@Override
	public String toString() {
		return "CartVo [no=" + no + ", member_no=" + member_no + ", book_no=" + book_no + ", count=" + count
				+ ", userName=" + userName + ", title=" + title + ", price=" + price + "]";
	}
}
