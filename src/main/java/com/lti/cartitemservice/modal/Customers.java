package com.lti.cartitemservice.modal;

import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OrderColumn;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;



@Entity
@Data
public class Customers {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long customer_id;
	
	private String firstname;
	
	private String lastname;
	
	private String username;
	
	private String email;
	
	private String password;
	
	@OneToMany(mappedBy = "customers")  
	private List<CartItems> cartitems;

	public Customers(long customer_id, String firstname, String lastname, String username, String email,
			String password, List<CartItems> cartitems) {
		super();
		this.customer_id = customer_id;
		this.firstname = firstname;
		this.lastname = lastname;
		this.username = username;
		this.email = email;
		this.password = password;
		this.cartitems = cartitems;
	}

	public long getCustomer_id() {
		return customer_id;
	}

	public void setCustomer_id(long customer_id) {
		this.customer_id = customer_id;
	}

	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public List<CartItems> getCartitems() {
		return cartitems;
	}

	public void setCartitems(List<CartItems> cartitems) {
		this.cartitems = cartitems;
	}

	@Override
	public String toString() {
		return "Customers [customer_id=" + customer_id + ", firstname=" + firstname + ", lastname=" + lastname
				+ ", username=" + username + ", email=" + email + ", password=" + password + ", cartitems=" + cartitems
				+ "]";
	}  
	
	
	

}
