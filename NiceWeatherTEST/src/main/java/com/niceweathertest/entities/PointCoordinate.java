package com.niceweathertest.entities;

import java.util.Objects;

public class PointCoordinate {

	private double latitude;
	private double longitude;

	public PointCoordinate() {
		super();
	}
	
	public PointCoordinate(double[] coordinate) {
		super();
		this.setCoordinates(coordinate);
	}

	public void setCoordinates(double[] coordinates) {
		this.longitude = coordinates[0];
		this.latitude = coordinates[1];
	}

	public double getLatitude() {
		return latitude;
	}

	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}

	public double getLongitude() {
		return longitude;
	}

	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}

	@Override
	public int hashCode() {
		return Objects.hash(latitude, longitude);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		PointCoordinate other = (PointCoordinate) obj;
		return Double.doubleToLongBits(latitude) == Double.doubleToLongBits(other.latitude)
				&& Double.doubleToLongBits(longitude) == Double.doubleToLongBits(other.longitude);
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("\n*** PointCoordinates ***\nlatitude=");
		builder.append(latitude);
		builder.append("\nlongitude=");
		builder.append(longitude);
		builder.append("\n*** END PointCoordinates ***");
		return builder.toString();
	}

}
