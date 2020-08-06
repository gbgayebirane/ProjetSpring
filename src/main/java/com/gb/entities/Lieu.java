package com.gb.entities;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;

@Entity
@NamedQueries({
	@NamedQuery(name="Lieu.findAll",query="SELECT l FROM Lieu l"),
	@NamedQuery(name="Lieu.findById",query="SELECT l FROM Lieu l WHERE l.id=:id")
})
public class Lieu 
{
	@Id //pour la cle primaire
    @GeneratedValue(strategy=GenerationType.IDENTITY)  //AUTO INCREMENT
	private int id;
	@Column
	private String nom;
	@ManyToMany
	private List<Formation> formations =new ArrayList<Formation>();
	@ManyToOne
	private User user= new User();
	public Lieu() {
		super();
	}
	public Lieu(int id, String nom, List<Formation> formations, User user) {
		super();
		this.id = id;
		this.nom = nom;
		this.formations = formations;
		this.user = user;
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
	public List<Formation> getFormations() {
		return formations;
	}
	public void setFormations(List<Formation> formations) {
		this.formations = formations;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	
}
