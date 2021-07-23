package com.art.entities.learning;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Item {

	private String primaryImage;

	public Item() {
	}

	public String getPrimaryImage() {
		return primaryImage;
	}

	public void setPrimaryImage(String primaryImage) {
		this.primaryImage = primaryImage;
	}

	@Override
	public String toString() {
		return "Item [primaryImage=" + primaryImage + "]";
	}

}
