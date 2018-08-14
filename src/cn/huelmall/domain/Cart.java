package cn.huelmall.domain;

import java.util.ArrayList;
import java.util.List;

public class Cart {
	private double total;
	private double socre;
	private List<CartItem> cartItemList = new ArrayList<CartItem>();

	public double getTotal() {
		return total;
	}

	public void setTotal(double total) {
		this.total = total;
	}

	public double getSocre() {
		return socre;
	}

	public void setSocre(double socre) {
		this.socre = socre;
	}

	public List<CartItem> getCartItemList() {
		return cartItemList;
	}

	public void setCartItemList(List<CartItem> cartItemList) {
		this.cartItemList = cartItemList;
	}

}
