package jp.co.internous.react.model.domain.dto;

import java.sql.Timestamp;

/*
 カートの初期表示のデータを渡すためのクラス
 */

public class CartDto {

	/** tbl_cart.id */
	private long id;
	/** mst_product.image_full_path */
	private String imageFullPath;
	/** mst_product.product_name */
	private String productName;
	/** mst_product.price */
	private long price;
	/** tbl_cart.product_count */
	private long productCount;
	private long subtotal;
	private Timestamp createdAt;
	private Timestamp updatedAt;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getImageFullPath() {
		return imageFullPath;
	}

	public void setImageFullPath(String imageFullPath) {
		this.imageFullPath = imageFullPath;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public long getPrice() {
		return price;
	}

	public void setPrice(long price) {
		this.price = price;
	}

	public long getProductCount() {
		return productCount;
	}

	public void setProductCount(long productCount) {
		this.productCount = productCount;
	}


	public long getSubtotal() {
		return subtotal;
	}


	public void setSubtotal(long subtotal) {
		this.subtotal = subtotal;
	}

	public Timestamp getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Timestamp createdAt) {
		this.createdAt = createdAt;
	}

	public Timestamp getUpdatedAt() {
		return updatedAt;
	}

	public void setUpdatedAt(Timestamp updatedAt) {
		this.updatedAt = updatedAt;
	}
	
}