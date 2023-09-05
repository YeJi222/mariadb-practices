package bookmall.vo;

public class OrderBookVo {
	private int order_no;
	private int book_no;
	private int count;
	
	public int getOrder_no() {
		return order_no;
	}
	public void setOrder_no(int order_no) {
		this.order_no = order_no;
	}
	public int getBook_no() {
		return book_no;
	}
	public void setBook_no(int book_no) {
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
		return "OrderBookVo [order_no=" + order_no + ", book_no=" + book_no + ", count=" + count + "]";
	}
}
