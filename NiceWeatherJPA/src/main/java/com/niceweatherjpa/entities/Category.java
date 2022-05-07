package com.niceweatherjpa.entities;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "category")
public class Category {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	private String name;
	
	@JsonIgnore
	@ManyToMany(fetch = FetchType.EAGER, 
	cascade = CascadeType.ALL)
		@JoinTable(name = "location_category", 
		joinColumns = @JoinColumn(name = "category_id"), 
		inverseJoinColumns = @JoinColumn(name = "location_id"))
	private List<Location> locationList;

	public Category() {
		super();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

//	public List<Location> getLocationList() {
//		return locationList;
//	}
//
//	public void setLocationList(List<Location> locationList) {
//		this.locationList = locationList;
//	}

//	public void addLocation(Location location) {
//		if (locationList == null) {
//			locationList = new ArrayList<>();
//		}
//		if (!locationList.contains(location)) {
//			locationList.add(location);
//		}
//		if (!location.getCategoryList().contains(this)) {
//			location.addCategory(this);
//		}
//	}
//
//	public void removeLocation(Location location) {
//		if (locationList != null && locationList.contains(location)) {
//			locationList.remove(location);
//		}
//		if (location.getCategoryList() != null && location.getCategoryList().contains(this)) {
//			location.removeCategory(this);
//		}
//	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Category other = (Category) obj;
		return id == other.id;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("\n*** Category ***\nid=");
		builder.append(id);
		builder.append("\nname=");
		builder.append(name);
//		if (locationList != null && locationList.size() > 0) {
//			builder.append("\nlocationList.size()=");
//			builder.append(locationList.size());
//		} else {
//			builder.append("\nNO LOCATIONS");
//		}
		builder.append("\n*** END Category ***");
		return builder.toString();
	}

}
