package com.art.entities;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "user")
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@NotNull
	@Column(name = "username")
	private String userName;
	@NotNull
	private String password;
	@NotNull
	private String email;
	
	private String about;

	// Bi-directional @OneToMany mapping
	// One user may have created many images
	@OneToMany(mappedBy = "creator")
	private List<Image> createdImages = new ArrayList<Image>();

	// Bi-directional @OneToMany mapping
	// One user may have bought many images
	@OneToMany(mappedBy = "buyer")
	private List<Image> boughtImages = new ArrayList<Image>();

	// one user can have many roles
	// fetchType = default -lazy. we get applicant roles by calling getters()
	// cascade = all operations except DELETE are performed on associated table
	@JsonIgnore
	@ManyToMany(fetch = FetchType.EAGER, cascade = { CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH,
			CascadeType.DETACH })
	@JoinTable(name = "users_roles", joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name = "role_id"))
	private Set<Role> roles = new HashSet<>();

	public User() {
	}

	public User(String username, String password, String email) {
		this.userName = username;
		this.password = password;
		this.email = email;
	}

	public User(String userName, String password, String email, Set<Role> roles) {
		this.userName = userName;
		this.password = password;
		this.email = email;
		this.roles = roles;
	}

	// methods to add/delete role
	// returns true if success
	public boolean addRole(Role role) {
		boolean added = roles.add(role);
		boolean added2 = role.getUsers().add(this);

		return added && added2;
	}

	public boolean deleteRole(Role role) {
		boolean deleted = roles.remove(role);
		boolean deleted2 = role.getUsers().remove(this);

		return deleted && deleted2;
	}

	// setters and getters
	public String getUsername() {
		return userName;
	}

	public void setUsername(String username) {
		this.userName = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public Set<Role> getRoles() {
		return roles;
	}

	public void setRoles(Set<Role> roles) {
		this.roles = roles;
	}	

	public String getAbout() {
		return about;
	}

	public void setAbout(String about) {
		this.about = about;
	}

	// helper method not to forget update both sides of bi-directional mapping
	public void addCreatedImage(Image image) {
		this.createdImages.add(image);
		image.setCreator(this);
	}

	// helper method not to forget update both sides of bi-directional mapping
	public void addBoughtImage(Image image) {
		this.boughtImages.add(image);
		image.setBuyer(this);
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", userName=" + userName +", about=" + about + ", password=" + password + ", email=" + email;
	}

}
