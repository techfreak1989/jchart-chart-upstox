package com.jchart.model.mongodb;

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
public class TradeIndex {

	private String instrument_key;
	private Integer exchange_token;
	private String name;
	private Double last_price;
	private String instrument_type;
	private String exchange;
	private String tradingsymbol;
	private Double tick_size;
	private Integer lot_size;
	public String getInstrument_key() {
		return instrument_key;
	}
	public void setInstrument_key(String instrument_key) {
		this.instrument_key = instrument_key;
	}
	public Integer getExchange_token() {
		return exchange_token;
	}
	public void setExchange_token(Integer exchange_token) {
		this.exchange_token = exchange_token;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Double getLast_price() {
		return last_price;
	}
	public void setLast_price(Double last_price) {
		this.last_price = last_price;
	}
	public String getInstrument_type() {
		return instrument_type;
	}
	public void setInstrument_type(String instrument_type) {
		this.instrument_type = instrument_type;
	}
	public String getExchange() {
		return exchange;
	}
	public void setExchange(String exchange) {
		this.exchange = exchange;
	}
	public String getTradingsymbol() {
		return tradingsymbol;
	}
	public void setTradingsymbol(String tradingsymbol) {
		this.tradingsymbol = tradingsymbol;
	}
	public Double getTick_size() {
		return tick_size;
	}
	public void setTick_size(Double tick_size) {
		this.tick_size = tick_size;
	}
	public Integer getLot_size() {
		return lot_size;
	}
	public void setLot_size(Integer lot_size) {
		this.lot_size = lot_size;
	}
	
}
