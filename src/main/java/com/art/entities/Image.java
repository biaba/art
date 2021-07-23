package com.art.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "image")
public class Image {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotNull
	private String name;

	// picture stored as a path to file storage
	@NotNull
	@Column(name = "path")
	private String fileUrl;

	// Bi-directional @ManyToOne mapping
	// One user may have created many images
	@NotNull
	@ManyToOne
	@JoinColumn(name = "creator_user_id")
	User creator;

	@NotNull
	@Enumerated(EnumType.STRING)
	private CategoryType category;

	@NotNull
	@Column(name = "for_sale")
	private Boolean forSale;

	private Integer price;

	private String description;

	private Boolean sold;

	// Bi-directional @ManyToOne mapping
	// One user may have bought many images
	@ManyToOne
	@JoinColumn(name = "buyer_user_id")
	private User buyer;

	public Image() {
	}

	public Image(String name, String fileUrl, User creator, CategoryType category, Boolean forSale) {
		super();
		this.name = name;
		this.fileUrl = fileUrl;
		this.creator = creator;
		this.category = category;
		this.forSale = forSale;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getFileUrl() {
		return fileUrl;
	}

	public void setFileUrl(String fileUrl) {
		this.fileUrl = fileUrl;
	}

	public User getCreator() {
		return creator;
	}

	public void setCreator(User creator) {
		this.creator = creator;
	}

	public User getBuyer() {
		return buyer;
	}

	public void setBuyer(User buyer) {
		this.buyer = buyer;
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

	public Boolean isSold() {
		return sold;
	}

	public void setSold(Boolean sold) {
		this.sold = sold;
	}

	public Integer getPrice() {
		return price;
	}

	public void setPrice(Integer price) {
		this.price = price;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Override
	public String toString() {
		return "Image [id=" + id + ", name=" + name + ", fileUrl=" + fileUrl + ", creator=" + creator + ", category="
				+ category + ", forSale=" + forSale + ", price=" + price + ", description=" + description + ", sold="
				+ sold + ", buyer=" + buyer + "]";
	}

}
