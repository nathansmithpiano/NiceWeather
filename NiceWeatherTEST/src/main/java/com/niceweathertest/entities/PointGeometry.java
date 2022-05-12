package com.niceweathertest.entities;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class PointGeometry {

	private String type;

//	private List<Double> coordinates;

	private List<Coordinate> coordinateList;

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

//	public List<Double> getCoordinates() {
//		return coordinates;
//	}

	public void setCoordinates(List<Double> list) {
		for (int i = 0; i < list.size(); i += 2) {
			if (list.get(i) != null && list.get(i + 1) != null) {
				addCoordinate(new Coordinate(list.get(i + 1), list.get(i)));
			}
		}
	}

	public void addCoordinate(Coordinate coordinate) {
		if (coordinateList == null) {
			coordinateList = new ArrayList<>();
		}
		coordinateList.add(coordinate);
	}

	@Override
	public int hashCode() {
		return Objects.hash(coordinateList, type);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		PointGeometry other = (PointGeometry) obj;
		return Objects.equals(coordinateList, other.coordinateList) && Objects.equals(type, other.type);
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("\n*** Geometry ***\ntype=");
		builder.append(type);
		builder.append("\ncoordinateList=");
		builder.append(coordinateList);
		builder.append("\n*** END Geometry ***");
		return builder.toString();
	}

}
