package com.telnet.jukebox.spring.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value = "price")
public class PriceDTO {

	@ApiModelProperty(dataType = "Long", example = "[1, 2, 3]", position = -1)
	private Long id;
	
	@ApiModelProperty(dataType = "Long", example = "[50, 60, 70]", position = -1)
	private Long price;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Long getPrice() {
		return price;
	}
	public void setPrice(Long price) {
		this.price = price;
	}
}
