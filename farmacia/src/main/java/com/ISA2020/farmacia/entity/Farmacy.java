package com.ISA2020.farmacia.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonView;

@Entity
public class Farmacy {

	@Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
	@JsonView(Views.Simple.class)
	private String farmacyId;
	@Column(nullable=false)
	@JsonView(Views.Simple.class)
	private String name;
	 @Column(nullable=false)
	 @JsonView(Views.Simple.class)
	private String adress;
	 @JsonView(Views.Simple.class)
	private float stars;
	 
	@OneToMany(mappedBy = "farmacy", fetch = FetchType.LAZY,
		            cascade = CascadeType.ALL, targetEntity = Price.class)
	@JsonView(Views.Detailed.class)
	 private List<Price> prices; 
	 
	 
	
	public Farmacy() {}
	
	public Farmacy(String name, String adress, float stars) {
		super();
		this.name = name;
		this.adress = adress;
		this.stars = stars;
	}
	public String getId() {
		return farmacyId;
	}
	public void setId(String id) {
		this.farmacyId = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getAdress() {
		return adress;
	}
	public void setAdress(String adress) {
		this.adress = adress;
	}
	public float getStars() {
		return stars;
	}
	public void setStars(float stars) {
		this.stars = stars;
	}

	public List<Price> getPrices() {
		return prices;
	}

	public void setPrices(List<Price> prices) {
		this.prices = prices;
	}

	

	
	
	
}
