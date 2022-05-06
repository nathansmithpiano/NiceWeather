package com.niceweatherjpa.entities;

import java.util.List;
import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "coordinate")
public class Coordinate {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	private Double latitude;

	private Double longitude;

	@OneToOne
	@JoinTable(name = "geometry_coordinate", joinColumns = @JoinColumn(name = "coordinate_id"), inverseJoinColumns = @JoinColumn(name = "geometry_id"))
	private Geometry geometry;

	@OneToMany(mappedBy = "coordinate")
	private List<Location> locationList;

	public Coordinate() {
		super();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Double getLatitude() {
		return latitude;
	}

	public void setLatitude(Double latitude) {
		this.latitude = latitude;
	}

	public Double getLongitude() {
		return longitude;
	}

	public void setLongitude(Double longitude) {
		this.longitude = longitude;
	}

	public Geometry getGeometry() {
		return geometry;
	}

	public void setGeometry(Geometry geometry) {
		this.geometry = geometry;
	}

	public List<Location> getLocationList() {
		return locationList;
	}

	public void setLocationList(List<Location> locationList) {
		this.locationList = locationList;
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
		Coordinate other = (Coordinate) obj;
		return id == other.id;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("\n*** Coordinate ***\nid=");
		builder.append(id);
		builder.append("\nlatitude=");
		builder.append(latitude);
		builder.append("\nlongitude=");
		builder.append(longitude);
		if (geometry != null) {
			builder.append("\ngeometry.getId()=");
			builder.append(geometry.getId());
		} else {
			builder.append("\nNO GEOMETRY");
		}
		if (locationList != null && locationList.size() > 0) {
			builder.append("\nlocationList.size()=");
			builder.append(locationList.size());
		} else {
			builder.append("\nNO LOCATIONS");
		}
		builder.append("\n*** END Coordinate ***");
		return builder.toString();
	}

}