package com.niceweatherjpa.entities;

import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.Objects;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
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
	
//	@JsonIgnore
//	@OneToOne(mappedBy = "geometry")
//	private Point point;
	
	@OneToMany(mappedBy = "geometry")
	private Set<Coordinate> coordinates;
	
	@JsonIgnore
	@OneToOne(mappedBy = "geometry")
	private Location location;
	
//	@JsonIgnore
//	@OneToMany(mappedBy = "geometry")
//	private List<RelativeLocation> relativeLocationList;

//	@JsonIgnore
//	@OneToMany(mappedBy = "geometry")
//	private List<Forecast> forecastList;

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

//	public Point getPoint() {
//		return point;
//	}
//
//	public void setPoint(Point point) {
//		this.point = point;
//	}
	
	public String getLocationName() {
		if (location != null) {
			return location.getName();
		} else {
			return "NO LOCATION";
		}
	}
	
	public Integer getLocationId() {
		if (location != null) {
			return location.getId();
		} else {
			return null;
		}
	}
	
	public Location getLocation() {
		return location;
	}

	public void setLocation(Location location) {
		this.location = location;
	}
	
	public Set<Coordinate> getCoordinates() {
		return coordinates;
	}
	
	public void setCoordinates(Set<Coordinate> coordinates) {
		this.coordinates = coordinates;
	}

	public void addCoordinate(Coordinate coordinate) {
		if (coordinates == null) {
			coordinates = new LinkedHashSet<>();
		}
		if (!coordinates.contains(coordinate)) {
			coordinates.add(coordinate);
			coordinate.setGeometry(this);
		}
	}

	public void removeCoordinate(Coordinate coordinate) {
		if (coordinate != null && coordinates.contains(coordinate)) {
			coordinates.remove(coordinate);
			coordinate.setGeometry(null);
		}
	}

//	public List<RelativeLocation> getRelativeLocationList() {
//		return relativeLocationList;
//	}
//
//	public void setRelativeLocationList(List<RelativeLocation> relativeLocationList) {
//		this.relativeLocationList = relativeLocationList;
//	}
//
//	public void addRelativeLocation(RelativeLocation relativeLocation) {
//		if (relativeLocationList == null) {
//			relativeLocationList = new ArrayList<>();
//		}
//		if (!relativeLocationList.contains(relativeLocation)) {
//			relativeLocationList.add(relativeLocation);
//			relativeLocation.setGeometry(this);
//		}
//	}
//
//	public void removeRelativeLocation(RelativeLocation relativeLocation) {
//		if (relativeLocation != null && relativeLocationList.contains(relativeLocation)) {
//			relativeLocationList.remove(relativeLocation);
//			relativeLocation.setGeometry(null);
//		}
//	}
//
//	public List<Forecast> getForecastList() {
//		return forecastList;
//	}
//
//	public void setForecastList(List<Forecast> forecastList) {
//		this.forecastList = forecastList;
//	}
//
//	public void addForecast(Forecast forecast) {
//		if (forecastList == null) {
//			forecastList = new ArrayList<>();
//		}
//		if (!forecastList.contains(forecast)) {
//			forecastList.add(forecast);
//			forecast.setGeometry(this);
//		}
//	}
//
//	public void removeForecast(Forecast forecast) {
//		if (forecast != null && forecastList.contains(forecast)) {
//			forecastList.remove(forecast);
//			forecast.setGeometry(null);
//		}
//	}

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
		
		// if Geometry has coordinates
		if (coordinates != null && coordinates.size() > 0) {
			// print latitude and longitude of each Coordinate
			Iterator<Coordinate> it = coordinates.iterator();
			int count = 0;
			
			while (it.hasNext()) {
				Coordinate coordinate = it.next();
				count++;
				
				builder.append("\nCoordinate " + count);
				builder.append(" (id: " + coordinate.getId() + "): ");
				builder.append("latitude: " + coordinate.getLatitude());
				builder.append(", longitude: " + coordinate.getLongitude());
				builder.append(", geometryId: " + coordinate.getGeometryId());
			}
		} else {
			builder.append("\nNO COORDINATES");
		}
		
		// if Geometry has Location
		if (location != null) {
			// print name and id of Location
			builder.append("\nLocation: " + location.getName());
			builder.append(" (id: " + location.getId() + ")");
		} else {
			builder.append("\nNO LOCATION");
		}
		
//		if (point != null) {
//			builder.append("\npoint.getId()=");
//			builder.append(point.getId());
//		} else {
//			builder.append("\nNO POINT");
//		}
		
//		if (relativeLocationList != null && relativeLocationList.size() > 0) {
//			builder.append("\nrelativeLocationList.size()=");
//			builder.append(relativeLocationList.size());
//		} else {
//			builder.append("\nNO RELATIVE LOCATION");
//		}
//		if (forecastList != null && forecastList.size() > 0) {
//			builder.append("\nforecastList.size()=");
//			builder.append(forecastList.size());
//		} else {
//			builder.append("\nNO FORECASTS");
//		}
		
		builder.append("\n*** END Geometry ***");
		return builder.toString();
	}

}
