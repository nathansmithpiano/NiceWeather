package com.niceweathertest.entities;

import java.util.Map;
import java.util.Objects;

public class RelativeLocationProperties {

	private String city;
	private String state;
	private Distance distance;
	private Bearing bearing;

	public RelativeLocationProperties() {
		super();
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public Distance getDistance() {
		return distance;
	}

	public void setDistance(Distance distance) {
		this.distance = distance;
	}

	public Bearing getBearing() {
		return bearing;
	}

	public void setBearing(Bearing bearing) {
		this.bearing = bearing;
	}

	@Override
	public int hashCode() {
		return Objects.hash(bearing, city, distance, state);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		RelativeLocationProperties other = (RelativeLocationProperties) obj;
		return Objects.equals(bearing, other.bearing) && Objects.equals(city, other.city)
				&& Objects.equals(distance, other.distance) && Objects.equals(state, other.state);
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("\n*** RelativeLocationProperties ***\ncity=");
		builder.append(city);
		builder.append("\nstate=");
		builder.append(state);
		builder.append("\ndistance=");
		builder.append(distance);
		builder.append("\nbearing=");
		builder.append(bearing);
		builder.append("\n*** END RelativeLocationProperties ***");
		return builder.toString();
	}

}
