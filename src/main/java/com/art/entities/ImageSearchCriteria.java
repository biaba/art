package com.art.entities;

public class ImageSearchCriteria {

	private String artist;
	private String artName;
	private CategoryType category;
	private Boolean forSale;

	public ImageSearchCriteria() {
	}

	public ImageSearchCriteria(String artist, String artName, CategoryType category, Boolean forSale) {
		super();
		this.artist = artist;
		this.artName = artName;
		this.category = category;
		this.forSale = forSale;
	}

	public String getArtist() {
		return artist;
	}

	public void setArtist(String artist) {
		this.artist = artist;
	}

	public String getArtName() {
		return artName;
	}

	public void setArtName(String artName) {
		this.artName = artName;
	}

	public CategoryType getCategory() {
		return category;
	}

	public void setCategory(CategoryType category) {
		this.category = category;
	}

	public Boolean isForSale() {
		return forSale;
	}

	public void setForSale(Boolean forSale) {
		this.forSale = forSale;
	}

	@Override
	public String toString() {
		return "ImageSearchCriteria [artist=" + artist + ", artName=" + artName + ", category=" + category
				+ ", forSale=" + forSale + "]";
	}

}
