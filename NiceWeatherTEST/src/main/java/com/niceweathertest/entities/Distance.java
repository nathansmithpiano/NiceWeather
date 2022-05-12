package com.niceweathertest.entities;

import java.util.Objects;

public class Distance {

	private String unitCode;
	private Double value;

	public Distance() {
		super();
	}

	public String getUnitCode() {
		return unitCode;
	}

	public void setUnitCode(String unitCode) {
		this.unitCode = unitCode;
	}

	public Double getValue() {
		return value;
	}

	public void setValue(Double value) {
		this.value = value;
	}

	@Override
	public int hashCode() {
		return Objects.hash(unitCode, value);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Distance other = (Distance) obj;
		return Objects.equals(unitCode, other.unitCode) && Objects.equals(value, other.value);
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("\n*** Distance ***\nunitCode=");
		builder.append(unitCode);
		builder.append("\nvalue=");
		builder.append(value);
		builder.append("\n*** END Distance ***");
		return builder.toString();
	}

}
