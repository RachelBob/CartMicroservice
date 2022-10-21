package com.lti.cartitemservice.modal;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Builder;
import lombok.Data;

@Data
@Entity
@Table(name = "Cartitems ")
@Builder
public class CartItems {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
	private long cartitem_id;

	private String productname;

	private String description;

	private long quantity;

	private double price;

	private double totalprice;

	private String uuid;

	@JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
	private String customer_id;

	@Transient
	@JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
	private String customer_uuid;

//	@ManyToOne
//	private Customers customers;

	public CartItems() {

	}

	public CartItems(long cartitem_id, String productname, String description, long quantity, double price,
			double totalprice, String uuid, String customer_id, String customer_uuid) {
		super();
		this.cartitem_id = cartitem_id;
		this.productname = productname;
		this.description = description;
		this.quantity = quantity;
		this.price = price;
		this.totalprice = totalprice;
		this.uuid = uuid;
		this.customer_id = customer_id;
		this.customer_uuid = customer_uuid;
	}

	public long getCartitem_id() {
		return cartitem_id;
	}

	public void setCartitem_id(long cartitem_id) {
		this.cartitem_id = cartitem_id;
	}

	public String getProductname() {
		return productname;
	}

	public void setProductname(String productname) {
		this.productname = productname;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public long getQuantity() {
		return quantity;
	}

	public void setQuantity(long quantity) {
		this.quantity = quantity;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public double getTotalprice() {
		return totalprice;
	}

	public void setTotalprice(double totalprice) {
		this.totalprice = totalprice;
	}

	public String getUuid() {
		return uuid;
	}

	public void setUuid(String uuid) {
		this.uuid = uuid;
	}

	public String getCustomer_id() {
		return customer_id;
	}

	public void setCustomer_id(String customer_id) {
		this.customer_id = customer_id;
	}

	public String getCustomer_uuid() {
		return customer_uuid;
	}

	public void setCustomer_uuid(String customer_uuid) {
		this.customer_uuid = customer_uuid;
	}

	@Override
	public String toString() {
		return "CartItems [cartitem_id=" + cartitem_id + ", productname=" + productname + ", description=" + description
				+ ", quantity=" + quantity + ", price=" + price + ", totalprice=" + totalprice + ", uuid=" + uuid
				+ ", customer_id=" + customer_id + ", customer_uuid=" + customer_uuid + "]";
	}
	
	

}
