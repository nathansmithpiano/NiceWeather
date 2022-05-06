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

	private String name;

	@ManyToOne
	@JoinColumn(name = "coordinate_id")
	private Coordinate coordinate;
	
	@JsonIgnore
	@ManyToMany
	@JoinTable(name = "location_category", joinColumns = @JoinColumn(name = "location_id"), inverseJoinColumns = @JoinColumn(name = "category_id"))
	private List<Category> categoryList;
	
	@ManyToOne
	@JoinColumn(name = "mountain_range_id")
	private MountainRange mountainRange;

	@OneToOne(mappedBy = "location")
	private Point point;

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

	public Coordinate getCoordinate() {
		return coordinate;
	}

	public void setCoordinate(Coordinate coordinate) {
		this.coordinate = coordinate;
	}

	public MountainRange getMountainRange() {
		return mountainRange;
	}

	public void setMountainRange(MountainRange mountainRange) {
		this.mountainRange = mountainRange;
	}

	public List<Category> getCategoryList() {
		return categoryList;
	}

	public void setCategoryList(List<Category> categoryList) {
		this.categoryList = categoryList;
	}

	public void addCategory(Category category) {
		if (categoryList == null) {
			categoryList = new ArrayList<>();
		}
		if (!categoryList.contains(category)) {
			categoryList.add(category);
		}
		if (category.getLocationList().contains(this)) {
			category.removeLocation(this);
		}
	}

	public void removeCategory(Category category) {
		if (categoryList != null && categoryList.contains(category)) {
			categoryList.remove(category);
		}
		if (category.getLocationList() != null && category.getLocationList().contains(this)) {
			category.removeLocation(this);
		}
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
		if (coordinate != null) {
			builder.append("\ncoordinate.getLatitude()=");
			builder.append(coordinate.getLatitude());
			builder.append("\ncoordinate.getLongitude()=");
			builder.append(coordinate.getLongitude());
		} else {
			builder.append("\nNO POINT");
		}
		if (categoryList != null && categoryList.size() > 0) {
			builder.append("\ncategoryList.size()=");
			builder.append(categoryList.size());
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
