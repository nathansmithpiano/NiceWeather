package com.niceweatherjpa.entities;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "geometry")
public class Geometry {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	private String type;
	
	@OneToMany(mappedBy = "geometry", orphanRemoval = true)
	@LazyCollection(LazyCollectionOption.FALSE)
	@Cascade({CascadeType.PERSIST, CascadeType.MERGE, CascadeType.DELETE})
	private Set<Coordinate> coordinates;
	
	@JsonIgnore
	@OneToOne(mappedBy = "geometry")
	private Location location;
	
	@JsonIgnore
	@OneToMany(mappedBy = "geometry")
	private List<RelativeLocation> relativeLocations;
	
//	@JsonIgnore
	@OneToOne(mappedBy = "geometry")
	private Point point;
	
//	@JsonIgnore
	@OneToMany(mappedBy = "geometry")
	private List<Forecast> forecasts;

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
	
	public Set<Coordinate> getCoordinates() {
		return coordinates;
	}
	
	public void setCoordinates(Set<Coordinate> coordinates) {
		this.coordinates = coordinates;
	}
	
	public void addCoordinate(Coordinate coordinate) {
		// only add if non-null
		if (coordinate != null) {
			if (coordinates == null) {
				coordinates = new LinkedHashSet<>();
			}
			//add to both
			coordinates.add(coordinate);
			coordinate.setGeometry(this);
		}
	}
	
	public void removeCoordinate(Coordinate coordinate) {
		// only remove if non-null
		if (coordinate != null) {
			coordinates.remove(coordinate);
			coordinate.setGeometry(null);
		}
//		if (coordinates.contains(coordinate)) {
//			coordinates.remove(coordinate);
//			
//			if (coordinate != null && coordinate.getGeometry() == this) {
//				coordinate.setGeometry(null);
//			}
//		}
	}
	
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
		
		if (location != null && location.getGeometry() != this) {
			location.setGeometry(this);
		}
	}
	
	public List<RelativeLocation> getRelativeLocations() {
		return relativeLocations;
	}

	public void setRelativeLocations(List<RelativeLocation> relativeLocations) {
		this.relativeLocations = relativeLocations;
	}
	
	public void addRelativeLocation(RelativeLocation relativeLocation) {
		if (relativeLocations == null) {
			relativeLocations = new ArrayList<>();
		}
		if (relativeLocation != null && !relativeLocations.contains(relativeLocation)) {
			relativeLocations.add(relativeLocation);
			relativeLocation.setGeometry(this);
		}
	}
	
	public void removeRelativeLocation(RelativeLocation relativeLocation) {
		if (relativeLocations != null && relativeLocations.contains(relativeLocation)) {
			relativeLocations.remove(relativeLocation);
			
			if (relativeLocation.getGeometry() != null && relativeLocation.getGeometry() == this) {
				relativeLocation.setGeometry(null);
			}
		}
	}
	
	public Point getPoint() {
		return point;
	}

	public void setPoint(Point point) {
		this.point = point;
		
		if (point != null && point.getGeometry() != this) {
			point.setGeometry(this);
		}
	}

	public List<Forecast> getForecasts() {
		return forecasts;
	}

	public void setForecasts(List<Forecast> forecasts) {
		this.forecasts = forecasts;
	}
	
	public void addForecast(Forecast forecast) {
		if (forecasts == null) {
			forecasts = new ArrayList<>();
		}
		if (forecast != null && !forecasts.contains(forecast)) {
			forecasts.add(forecast);
			forecast.setGeometry(this);
		}
	}

	public void removeForecast(Forecast forecast) {
		if (forecasts != null && forecasts.contains(forecast)) {
			forecasts.remove(forecast);
			
			if (forecast != null && forecast.getGeometry() == this) {
				forecast.setGeometry(null);
			}
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
		
		// if Geometry has RelativeLocations
		if (relativeLocations != null && relativeLocations.size() > 0) {
			// print id and city of each RelativeLocation
			Iterator<RelativeLocation> it = relativeLocations.iterator();
			int count = 0;
			
			while (it.hasNext()) {
				RelativeLocation relativeLocation = it.next();
				count++;
				
				builder.append("\nRelativeLocation " + count);
				builder.append(" (id: " + relativeLocation.getId());
				builder.append(", city: " + relativeLocation.getCity());
			}
		} else {
			builder.append("\nNO RELATIVE LOCATIONS");
		}
		
		// if Geometry has Point
		if (point != null) {
			// print point.getLocation().getName() and id of Point
			builder.append("\nPoint Location Name: " + point.getLocation().getName());
			builder.append(" (Point id: " + point.getId() + ")");
		} else {
			builder.append("\nNO POINT");
		}
		
		// if Geometry has forecasts 
		if (forecasts != null && forecasts.size() > 0) {
			//print hourly/normal, id, location id and name, point id, and number of periods for each Forecast
			for (Forecast fc : forecasts) {
				builder.append("\nForecast (");
				builder.append(fc.isHourly() ? "hourly" : "normal");
				builder.append(", id: " + fc.getId() + "): ");
				
				// Point and Location details
				if (fc.getPoint() != null) {
					builder.append("Point ");
					builder.append("(id: " + point.getId());
					
					if (fc.getPoint().getLocation() != null) {
						builder.append(", Location id:" + fc.getPoint().getLocation().getId());
						builder.append(", name: " + fc.getPoint().getLocation().getName() + ")");
					} else {
						builder.append(" NO LOCATION)");
					}
					
				} else {
					builder.append("NO POINT");
				}
				
				builder.append(fc.getPeriods().size() + " periods");
			}
		} else {
			builder.append("\nNO FORECASTS");
		}
		
		builder.append("\n*** END Geometry ***");
		return builder.toString();
	}

}
