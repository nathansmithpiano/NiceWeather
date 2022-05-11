package com.niceweatherjpa.entities;

import java.util.ArrayList;
import java.util.Iterator;
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

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

@Entity
@Table(name = "location")
public class Location {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	private String name;

	private Double elevation;

	@OneToOne
	@JoinColumn(name = "geometry_id")
	@Cascade(CascadeType.ALL)
	private Geometry geometry;

	@ManyToOne
	@JoinColumn(name = "mountain_range_id")
	private MountainRange mountainRange;

	@ManyToMany
	@LazyCollection(LazyCollectionOption.FALSE)
	@JoinTable(name = "location_category", joinColumns = @JoinColumn(name = "location_id"), inverseJoinColumns = @JoinColumn(name = "category_id"))
	@Cascade(CascadeType.MERGE)
	private List<Category> categories;

	@OneToOne(mappedBy = "location")
	@Cascade(CascadeType.ALL)
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

	public Double getElevation() {
		return elevation;
	}

	public void setElevation(Double elevation) {
		this.elevation = elevation;
	}

	public Geometry getGeometry() {
		return geometry;
	}

	public void setGeometry(Geometry geometry) {
		this.geometry = geometry;
		
		if (geometry != null && geometry.getLocation() != this) {
			geometry.setLocation(this);
		}
	}

	public MountainRange getMountainRange() {
		return mountainRange;
	}

	public void setMountainRange(MountainRange mountainRange) {
		this.mountainRange = mountainRange;
		
		if (mountainRange != null && !mountainRange.getLocations().contains(this)) {
			mountainRange.addLocation(this);
		}
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
		}
		if (category != null && !categories.contains(category)) {
			categories.add(category);
			category.addLocation(this);
		}
	}

	public void removeCategory(Category category) {
		if (categories != null && categories.contains(category)) {
			categories.remove(category);
			
			if (category.getLocations() != null && category.getLocations().contains(this)) {
				category.removeLocation(this);
			}
		}
	}

	public Point getPoint() {
		return point;
	}

	public void setPoint(Point point) {
		this.point = point;
		
		if (point != null && point.getLocation() != this) {
			point.setLocation(this);
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

		// if Location has Geometry
		if (geometry != null) {
			builder.append("\nGeometry: ");

			// if Geometry has only 1 Coordinate (should only have 1)
			if (geometry.getCoordinates() != null && geometry.getCoordinates().size() == 1) {
				// Get first coordinate in Geometry
				Coordinate coordinate = geometry.getCoordinates().iterator().next();

				// print id, latitude, and longitude of Coordinate
				builder.append(" Coordinate (id: " + coordinate.getId());
				builder.append(", latitude: " + coordinate.getLatitude());
				builder.append(", longitude: " + coordinate.getLongitude() + ")");
			} else {
				builder.append(" INVALID COORDINATE, ERROR");
			}

		} else {
			builder.append("\nNO GEOMETRY");
		}

		// if Location has MountainRange
		if (mountainRange != null) {
			// print name and id of MountainRange
			builder.append("\nMountainRange: " + mountainRange.getName());
			builder.append(" (id: " + mountainRange.getId() + ")");
		} else {
			builder.append("\nNO MOUNTAIN RANGE");
		}

		// if Location has categories
		if (categories != null && categories.size() > 0) {
			// print name and id of each Category
			Iterator<Category> it = categories.iterator();
			int count = 0;

			while (it.hasNext()) {
				Category category = it.next();
				count++;

				builder.append("\nCategory " + count + ": ");
				builder.append(category.getName());
				builder.append(" (id: " + category.getId() + ")");
			}
		} else {
			builder.append("\nNO CATEGORIES");
		}

		// if Location has Point
		if (point != null) {
			// print point.getLocation().getName() and id of Point
			builder.append("\nPoint Location Name: " + point.getLocation().getName());
			builder.append(" (Point id: " + point.getId() + ")");
		} else {
			builder.append("\nNO POINT");
		}
		
		builder.append("\n*** END Location ***");
		return builder.toString();
	}

}
