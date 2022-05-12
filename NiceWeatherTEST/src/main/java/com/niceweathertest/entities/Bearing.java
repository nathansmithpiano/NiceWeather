package com.niceweathertest.entities;

import java.util.Objects;

public class Bearing {

	private String unitCode;
	private Integer value;

	public Bearing() {
		super();
	}

	public String getUnitCode() {
		return unitCode;
	}

	public void setUnitCode(String unitCode) {
		this.unitCode = unitCode;
	}

	public Integer getValue() {
		return value;
	}

	public void setValue(Integer value) {
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
		Bearing other = (Bearing) obj;
		return Objects.equals(unitCode, other.unitCode) && Objects.equals(value, other.value);
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("\n*** Bearing ***\nunitCode=");
		builder.append(unitCode);
		builder.append("\nvalue=");
		builder.append(value);
		builder.append("\n*** END Bearing ***");
		return builder.toString();
	}

}
