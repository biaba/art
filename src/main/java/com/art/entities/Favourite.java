package com.art.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "favourite")
public class Favourite {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	Long id;

	// Uni-Directional. Each user may have multiple favourite entries
	@ManyToOne
	User user;

	// Uni-Directional. User (Artist) can be favourite to many users
	@ManyToOne
	@JoinColumn(name = "artist_id")
	User favArtist;

	// Uni-Directional. Image can be favourite to many users
	@ManyToOne
	@JoinColumn(name = "image_id")
	Image favImage;

	// Favourite image from museum (queried through REST)
	@Column(name = "m_image_url")
	String favMusImage;

	public Favourite() {
	}

	public Favourite(User user, User favArtist, Image favImage, String favMusImage) {
		super();
		this.user = user;
		this.favArtist = favArtist;
		this.favImage = favImage;
		this.favMusImage = favMusImage;
	}

	@Override
	public String toString() {
		return "Favourite [id=" + id + ", user=" + user + ", favArtist=" + favArtist + ", favImage=" + favImage
				+ ", favMusImage=" + favMusImage + "]";
	}		

}
