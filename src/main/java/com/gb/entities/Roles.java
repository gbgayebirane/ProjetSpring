package com.gb.entities;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;

@Entity
@NamedQueries({
	@NamedQuery(name="Roles.findAll",query="SELECT r FROM Roles r"),
	@NamedQuery(name="Roles.findById",query="SELECT r FROM Roles r WHERE r.id=:id")
})
public class Roles
{
	
	@Id //pour la cle primaire
    @GeneratedValue(strategy=GenerationType.IDENTITY)  //AUTO INCREMENT
	private int  id ;
	@Column
	private String nom;
	@ManyToMany(mappedBy="roles")
	private List<User> users =new ArrayList<User>();
	public Roles() {
		super();
	}
	
	public Roles(int id, String nom, List<User> users) {
		super();
		this.id = id;
		this.nom = nom;
		this.users = users;
	}

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getNom() {
		return nom;
	}
	public void setNom(String nom) {
		this.nom = nom;
	}
	public List<User> getUsers() {
		return users;
	}
	public void setUsers(List<User> users) {
		this.users = users;
	}
	

}
