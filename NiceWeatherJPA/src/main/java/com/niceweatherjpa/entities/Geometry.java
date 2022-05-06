package com.niceweatherjpa.entities;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "geometry")
public class Geometry {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	private String type;
	
	@JsonIgnore
	@OneToOne(mappedBy = "geometry")
	private Point point;
	
	@JsonIgnore
	@OneToMany
	@JoinTable(name = "geometry_coordinate", joinColumns = @JoinColumn(name = "geometry_id"), inverseJoinColumns = @JoinColumn(name = "coordinate_id"))
	private List<Coordinate> coordinateList;
	
	@JsonIgnore
	@OneToMany(mappedBy = "geometry")
	private List<RelativeLocation> relativeLocationList;

	@JsonIgnore
	@OneToMany(mappedBy = "geometry")
	private List<Forecast> forecastList;

	public Geometry() {
		super();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Point getPoint() {
		return point;
	}

	public void setPoint(Point point) {
		this.point = point;
	}

	public List<Coordinate> getCoordinateList() {
		return coordinateList;
	}

	public void setCoordinateList(List<Coordinate> coordinateList) {
		this.coordinateList = coordinateList;
	}

	public void addCoordinate(Coordinate coordinate) {
		if (coordinateList == null) {
			coordinateList = new ArrayList<>();
		}
		if (!coordinateList.contains(coordinate)) {
			coordinateList.add(coordinate);
			coordinate.setGeometry(this);
		}
	}

	public void removeCoordinate(Coordinate coordinate) {
		if (coordinate != null && coordinateList.contains(coordinate)) {
			coordinateList.remove(coordinate);
			coordinate.setGeometry(null);
		}
	}

	public List<RelativeLocation> getRelativeLocationList() {
		return relativeLocationList;
	}

	public void setRelativeLocationList(List<RelativeLocation> relativeLocationList) {
		this.relativeLocationList = relativeLocationList;
	}

	public void addRelativeLocation(RelativeLocation relativeLocation) {
		if (relativeLocationList == null) {
			relativeLocationList = new ArrayList<>();
		}
		if (!relativeLocationList.contains(relativeLocation)) {
			relativeLocationList.add(relativeLocation);
			relativeLocation.setGeometry(this);
		}
	}

	public void removeRelativeLocation(RelativeLocation relativeLocation) {
		if (relativeLocation != null && relativeLocationList.contains(relativeLocation)) {
			relativeLocationList.remove(relativeLocation);
			relativeLocation.setGeometry(null);
		}
	}

	public List<Forecast> getForecastList() {
		return forecastList;
	}

	public void setForecastList(List<Forecast> forecastList) {
		this.forecastList = forecastList;
	}

	public void addForecast(Forecast forecast) {
		if (forecastList == null) {
			forecastList = new ArrayList<>();
		}
		if (!forecastList.contains(forecast)) {
			forecastList.add(forecast);
			forecast.setGeometry(this);
		}
	}

	public void removeForecast(Forecast forecast) {
		if (forecast != null && forecastList.contains(forecast)) {
			forecastList.remove(forecast);
			forecast.setGeometry(null);
		}
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Geometry other = (Geometry) obj;
		return id == other.id;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("\n*** Geometry ***\nid=");
		builder.append(id);
		builder.append("\ntype=");
		builder.append(type);
		if (point != null) {
			builder.append("\npoint.getId()=");
			builder.append(point.getId());
		} else {
			builder.append("\nNO POINT");
		}
		if (coordinateList != null && coordinateList.size() > 0) {
			builder.append("\ncoordinateList.size()=");
			builder.append(coordinateList.size());
		} else {
			builder.append("\nNO COORDINATES");
		}
		if (relativeLocationList != null && relativeLocationList.size() > 0) {
			builder.append("\nrelativeLocationList.size()=");
			builder.append(relativeLocationList.size());
		} else {
			builder.append("\nNO RELATIVE LOCATION");
		}
		if (forecastList != null && forecastList.size() > 0) {
			builder.append("\nforecastList.size()=");
			builder.append(forecastList.size());
		} else {
			builder.append("\nNO FORECASTS");
		}
		builder.append("\n*** END Geometry ***");
		return builder.toString();
	}

}
