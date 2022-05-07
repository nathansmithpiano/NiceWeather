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
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "coordinate")
public class Coordinate {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	private Double latitude;

	private Double longitude;

	@JsonIgnore
	@OneToOne
	@JoinTable(name = "geometry_coordinate", joinColumns = @JoinColumn(name = "coordinate_id"), inverseJoinColumns = @JoinColumn(name = "geometry_id"))
	private Geometry geometry;

	@JsonIgnore
	@OneToMany(mappedBy = "coordinate", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	private List<Location> locations;

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

	public List<String> getLocationsShort() {
		List<String> results = null;

		if (locations != null && locations.size() > 0) {
			results = new ArrayList<>();
			for (int i = 0; i < locations.size(); i++) {
				results.add(locations.get(i).getName() + " id (" + locations.get(i).getId() + ")");
			}
			if (results.size() > 1) {
				results.add("count: " + results.size());
			}
		}
		return results;
	}

	public Geometry getGeometry() {
		return geometry;
	}

	public void setGeometry(Geometry geometry) {
		this.geometry = geometry;
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
		if (!locations.contains(location)) {
			locations.add(location);
			location.setCoordinate(this);
		}
	}

	public void removeLocation(Location location) {
		if (locations != null && locations.contains(location)) {
			locations.remove(location);
			location.setCoordinate(null);
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
		if (locations != null && locations.size() > 0) {
			builder.append("\nlocations.size()=");
			builder.append(locations.size());
		} else {
			builder.append("\nNO LOCATIONS");
		}
		builder.append("\n*** END Coordinate ***");
		return builder.toString();
	}

}