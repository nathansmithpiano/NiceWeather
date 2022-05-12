package com.niceweathertest.entities;

import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(value = { "@context" })
public class Point {

	private String id;

	private String type;

	private PointGeometry geometry;

	private PointProperties properties;
	
	public Point() {
		super();
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public PointGeometry getGeometry() {
		return geometry;
	}

	public void setGeometry(PointGeometry geometry) {
		this.geometry = geometry;
	}

	public PointProperties getProperties() {
		return properties;
	}

	public void setProperties(PointProperties properties) {
		this.properties = properties;
	}

	@Override
	public int hashCode() {
		return Objects.hash(geometry, id, properties, type);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Point other = (Point) obj;
		return Objects.equals(geometry, other.geometry) && Objects.equals(id, other.id)
				&& Objects.equals(properties, other.properties) && Objects.equals(type, other.type);
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("\n*** Point ***\nid=");
		builder.append(id);
		builder.append("\ntype=");
		builder.append(type);
		builder.append("\ngeometry=");
		builder.append(geometry);
		builder.append("\nproperties=");
		builder.append(properties);
		builder.append("\n*** END Point ***");
		return builder.toString();
	}

}
