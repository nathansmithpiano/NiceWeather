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
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "location")
public class Location {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "coordinate_id")
	private Coordinate coordinate;
	
	@ManyToMany(fetch = FetchType.EAGER, 
			cascade = CascadeType.ALL)
	@JoinTable(name = "location_category", 
	joinColumns = @JoinColumn(name = "location_id"), 
	inverseJoinColumns = @JoinColumn(name = "category_id"))
	private List<Category> categories;
	
	@ManyToOne(cascade = CascadeType.MERGE)
	@JoinColumn(name = "mountain_range_id")
	private MountainRange mountainRange;

	@OneToOne(mappedBy = "location", cascade = CascadeType.ALL)
	private Point point;
	
	private String name;
	
	private Double elevation;

	public Location() {
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
	
	public Double getElevation() {
		return elevation;
	}

	public void setElevation(Double elevation) {
		this.elevation = elevation;
	}

	public Coordinate getCoordinate() {
		return coordinate;
	}

	public void setCoordinate(Coordinate coordinate) {
		this.coordinate = coordinate;
		System.err.println("made it");
		coordinate.addLocation(this);
	}

	public MountainRange getMountainRange() {
		return mountainRange;
	}

	public void setMountainRange(MountainRange mountainRange) {
		this.mountainRange = mountainRange;
		mountainRange.addLocation(this);
	}

	public List<Category> getCategories() {
		return categories;
	}

	public void setCategories(List<Category> categories) {
		this.categories = categories;
	}

	public void addCategory(Category category) {
		if (categories == null) {
			categories = new ArrayList<>();
			System.err.println("this shouldn't happen");
		}
		if (!categories.contains(category)) {
			categories.add(category);
			System.err.println("made it");
		}
//		if (!category.getLocationList().contains(this)) {
//			category.addLocation(this);
//		}
	}

	public void removeCategory(Category category) {
		if (categories != null && categories.contains(category)) {
			categories.remove(category);
		}
//		if (category.getLocationList() != null && category.getLocationList().contains(this)) {
//			category.removeLocation(this);
//		}
	}

	public Point getPoint() {
		return point;
	}

	public void setPoint(Point point) {
		this.point = point;
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
		Location other = (Location) obj;
		return id == other.id;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("\n*** Location ***\nid=");
		builder.append(id);
		builder.append("\nname=");
		builder.append(name);
		builder.append("\nelevation=");
		builder.append(elevation);
		if (coordinate != null) {
			builder.append("\ncoordinate.getLatitude()=");
			builder.append(coordinate.getLatitude());
			builder.append("\ncoordinate.getLongitude()=");
			builder.append(coordinate.getLongitude());
		} else {
			builder.append("\nNO POINT");
		}
		if (categories != null && categories.size() > 0) {
			builder.append("\ncategories.size()=");
			builder.append(categories.size());
		} else {
			builder.append("\nNO CATEGORIES");
		}
		if (mountainRange != null) {
			builder.append("\nmountainRange.getId()=");
			builder.append(mountainRange.getId());
		} else {
			builder.append("\nNO MOUNTAIN RANGE");
		}
		if (point != null) {
			builder.append("\npoint.getId()=");
			builder.append(point.getId());
		} else {
			builder.append("\nNO POINT");
		}
		builder.append("\n*** END Location ***");
		return builder.toString();
	}

}
