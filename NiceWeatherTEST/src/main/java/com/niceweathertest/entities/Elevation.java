package com.niceweathertest.entities;

import java.util.Objects;

public class Elevation {

	private String unitCode;
	private double value;

	public Elevation() {
		super();
	}

	public String getUnitCode() {
		return unitCode;
	}

	public void setUnitCode(String unitCode) {
		this.unitCode = unitCode;
	}

	public double getValue() {
		return value;
	}

	public void setValue(double value) {
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
		Elevation other = (Elevation) obj;
		return Objects.equals(unitCode, other.unitCode)
				&& Double.doubleToLongBits(value) == Double.doubleToLongBits(other.value);
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("\n*** Elevation ***\nunitCode=");
		builder.append(unitCode);
		builder.append("\nvalue=");
		builder.append(value);
		builder.append("\n*** END Elevation ***");
		return builder.toString();
	}

}
