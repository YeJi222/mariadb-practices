package bookmall.vo;

public class OrdersVo {
	private int no;
	private String order_code;
	private int member_no;
	private int total_price;
	private String address;
	
	private String userName;
	private String email;
	
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getOrderCode() {
		return order_code;
	}
	public void setOrderCode(String order_code) {
		this.order_code = order_code;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
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
	public int getTotalPrice() {
		return total_price;
	}
	public void setTotalPrice(int total_price) {
		this.total_price = total_price;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	
	@Override
	public String toString() {
		return "OrdersVo [no=" + no + ", order_code=" + order_code + ", member_no=" + member_no + ", total_price="
				+ total_price + ", address=" + address + ", userName=" + userName + "]";
	}
}
