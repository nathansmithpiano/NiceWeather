package com.niceweatherjpa.entities;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "category")
public class Category {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	private String name;

	@JsonIgnore
	@ManyToMany
	@LazyCollection(LazyCollectionOption.FALSE)
	@JoinTable(name = "location_category", joinColumns = @JoinColumn(name = "category_id"), inverseJoinColumns = @JoinColumn(name = "location_id"))
	@Cascade(CascadeType.MERGE)
	private List<Location> locations;

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

	public String getLocationCount() {
		// if Category has locations
		if (locations != null) {
			// show number of locations
			return locations.size() + " locations";
		} else {
			return "NO LOCATIONS";
		}
	}

	public List<Location> getLocations() {
		return locations;
	}

	public void setLocations(List<Location> locations) {
		this.locations = locations;
	}

	public void addLocation(Location location) {
		if (locations == null) {
			locations = new ArrayList<>();
		}
		if (location != null && !locations.contains(location)) {
			locations.add(location);
			location.addCategory(this);
		}
	}

	public void removeLocation(Location location) {
		if (locations != null && locations.contains(location)) {
			locations.remove(location);
			
			if (location.getCategories() != null && location.getCategories().contains(this)) {
				location.removeCategory(this);
			}
		}
	}

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

		// if Category has locations
		if (locations != null & locations.size() > 0) {
			// print number of locations
			builder.append("\nLocations: ");
			builder.append(locations.size());
		} else {
			builder.append("\nNO LOCATIONS");
		}

		builder.append("\n*** END Category ***");
		return builder.toString();
	}

}
