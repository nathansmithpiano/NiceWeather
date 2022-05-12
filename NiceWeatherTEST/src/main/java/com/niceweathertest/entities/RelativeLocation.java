package com.niceweathertest.entities;

import java.util.Objects;


public class RelativeLocation {

	private String type;
	private PointGeometry geometry;
	private RelativeLocationProperties properties;

	public RelativeLocation() {
		super();
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

	public RelativeLocationProperties getProperties() {
		return properties;
	}

	public void setProperties(RelativeLocationProperties properties) {
		this.properties = properties;
	}

	@Override
	public int hashCode() {
		return Objects.hash(geometry, properties, type);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		RelativeLocation other = (RelativeLocation) obj;
		return Objects.equals(geometry, other.geometry) && Objects.equals(properties, other.properties)
				&& Objects.equals(type, other.type);
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("\n*** RelativeLocation ***\ntype=");
		builder.append(type);
		builder.append("\ngeometry=");
		builder.append(geometry);
		builder.append("\nproperties=");
		builder.append(properties);
		builder.append("\n*** END RelativeLocation ***");
		return builder.toString();
	}

}
