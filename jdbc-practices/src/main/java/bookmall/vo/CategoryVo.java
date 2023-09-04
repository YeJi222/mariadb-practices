package bookmall.vo;

public class CategoryVo {
	private int no;
	private int category_name;
	
	public int getNo() {
		return no;
	}
	public void setNo(int no) {
		this.no = no;
	}
	public int getCategory_name() {
		return category_name;
	}
	public void setCategory_name(int category_name) {
		this.category_name = category_name;
	}
	
	@Override
	public String toString() {
		return "CategoryVo [category_name=" + category_name + "]";
	}
}
