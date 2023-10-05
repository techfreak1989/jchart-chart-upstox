package com.jchart.model.mongodb;

import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Document
@JsonIgnoreProperties
public class TokenResponse {

	@Id
    private String id;
	private String email;
	private List<String> exchanges;
	private List<String> products;
	private String broker;
	private String user_id;
	private String user_name;
	private List<String> order_types;
	private String user_type;
	private Boolean poa;
	private Boolean is_active;
	private String access_token;
	

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public List<String> getExchanges() {
		return exchanges;
	}

	public void setExchanges(List<String> exchanges) {
		this.exchanges = exchanges;
	}

	public List<String> getProducts() {
		return products;
	}

	public void setProducts(List<String> products) {
		this.products = products;
	}

	public String getBroker() {
		return broker;
	}

	public void setBroker(String broker) {
		this.broker = broker;
	}

	public String getUser_id() {
		return user_id;
	}

	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}

	public String getUser_name() {
		return user_name;
	}

	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}

	public List<String> getOrder_types() {
		return order_types;
	}

	public void setOrder_types(List<String> order_types) {
		this.order_types = order_types;
	}

	public String getUser_type() {
		return user_type;
	}

	public void setUser_type(String user_type) {
		this.user_type = user_type;
	}

	public Boolean getPoa() {
		return poa;
	}

	public void setPoa(Boolean poa) {
		this.poa = poa;
	}

	public Boolean getIs_active() {
		return is_active;
	}

	public void setIs_active(Boolean is_active) {
		this.is_active = is_active;
	}

	public String getAccess_token() {
		return access_token;
	}

	public void setAccess_token(String access_token) {
		this.access_token = access_token;
	}
	
	
}
