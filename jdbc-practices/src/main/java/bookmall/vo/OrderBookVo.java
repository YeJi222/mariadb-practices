package bookmall.vo;

public class OrderBookVo {
	private int order_no;
	private int book_no;
	private int orderCount;
	
	private String title;
	private String order_code;
	
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public int getOrderNo() {
		return order_no;
	}
	public void setOrderNo(int order_no) {
		this.order_no = order_no;
	}
	public int getBookNo() {
		return book_no;
	}
	public void setBookNo(int book_no) {
		this.book_no = book_no;
	}
	public int getOrderCount() {
		return orderCount;
	}
	public void setOrderCount(int orderCount) {
		this.orderCount = orderCount;
	}
	public String getOrderCode() {
		return order_code;
	}
	public void setOrderCode(String order_code) {
		this.order_code = order_code;
	}
	
	@Override
	public String toString() {
		return "OrderBookVo [order_no=" + order_no + ", book_no=" + book_no + ", orderCount=" + orderCount + ", title="
				+ title + ", order_code=" + order_code + "]";
	}
}
