package com.niceweatherjpa.entities;

import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

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
	@ManyToOne
	@JoinColumn(name = "geometry_id")
	private Geometry geometry;

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
		
		if (geometry != null && !geometry.getCoordinates().contains(this)) {
			geometry.addCoordinate(this);
		}
	}
	
	public Integer getGeometryId() {
		if (geometry != null) {
			return geometry.getId();
		} else {
			return null;
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
			builder.append("\nGeometry ID: ");
			builder.append(geometry.getId());
		} else {
			builder.append("\nNO GEOMETRY");
		}
		builder.append("\n*** END Coordinate ***");
		return builder.toString();
	}

}