package com.niceweathertest.entities;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class PolygonGeometry {

	private String type;

	private List<Coordinate> coordinateList;

	public PolygonGeometry() {
		super();
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
	
	public void setCoordinates(List<List<List<Double>>> coordinates) {
		if (coordinates.size() == 1) {
			if (coordinates.get(0).size() > 1) {
				List<List<Double>> list = coordinates.get(0);
				for (List<Double> sublist : list) {
					addCoordinate(new Coordinate(sublist.get(1), sublist.get(0)));
				}
			}
		} else {
			System.err.println("PolygonGeometry ERROR");
		}
		
	}
	
	public void addCoordinate(Coordinate coordinate) {
		if (coordinateList == null) {
			coordinateList = new ArrayList<>();
		}
		coordinateList.add(coordinate);
	}

	public List<Coordinate> getCoordinateList() {
		return coordinateList;
	}

	public void setCoordinateList(List<Coordinate> coordinateList) {
		this.coordinateList = coordinateList;
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
		PolygonGeometry other = (PolygonGeometry) obj;
		return Objects.equals(coordinateList, other.coordinateList) && Objects.equals(type, other.type);
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("\n*** PolygonGeometry ***\ntype=");
		builder.append(type);
		builder.append("\ncoordinateList=");
		builder.append(coordinateList);
		builder.append("\n*** END PolygonGeometry ***");
		return builder.toString();
	}

}
