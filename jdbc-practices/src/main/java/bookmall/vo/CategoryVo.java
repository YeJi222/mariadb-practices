package bookmall.vo;

public class CategoryVo {
	private int no;
	private String category_name;
	
	public int getNo() {
		return no;
	}
	public void setNo(int no) {
		this.no = no;
	}
	public String getCategoryName() {
		return category_name;
	}
	public void setCategoryName(String category_name) {
		this.category_name = category_name;
	}
	
	@Override
	public String toString() {
		return "CategoryVo [category_name=" + category_name + "]";
	}
}
