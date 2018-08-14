package cn.huelmall.domain;

/**
 * 购物项实体类
 * @author: Administrator
 * @function:TODO
 * @time:  2018年6月29日
 */
public class CartItem {
	private int number;
	private double subtotal;
	private Product product;
	public int getNumber() {
		return number;
	}
	public void setNumber(int number) {
		this.number = number;
	}
	public double getSubtotal() {
		return subtotal;
	}
	public void setSubtotal(double subtotal) {
		this.subtotal = subtotal;
	}
	public Product getProduct() {
		return product;
	}
	public void setProduct(Product product) {
		this.product = product;
	}
	
}
