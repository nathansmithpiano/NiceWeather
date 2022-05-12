package com.niceweathertest.entities;

import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(value = { "@context" })
public class Forecast {

	private String type;
	private PolygonGeometry geometry;
	private ForecastProperties properties;

	public Forecast() {
		super();
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public PolygonGeometry getGeometry() {
		return geometry;
	}

	public void setGeometry(PolygonGeometry geometry) {
		this.geometry = geometry;
	}

	public ForecastProperties getProperties() {
		return properties;
	}

	public void setProperties(ForecastProperties properties) {
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
		Forecast other = (Forecast) obj;
		return Objects.equals(geometry, other.geometry) && Objects.equals(properties, other.properties)
				&& Objects.equals(type, other.type);
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("\n*** Forecast ***\ntype=");
		builder.append(type);
		builder.append("\ngeometry=");
		builder.append(geometry);
		builder.append("\nproperties=");
		builder.append(properties);
		builder.append("\n*** END Forecast ***");
		return builder.toString();
	}

}
