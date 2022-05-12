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
	@Cascade({ CascadeType.PERSIST, CascadeType.MERGE, CascadeType.DELETE })
	private List<Coordinate> coordinates;

	@JsonIgnore
	@OneToOne(mappedBy = "geometry")
	private Location location;

	@JsonIgnore
	@OneToMany(mappedBy = "geometry", orphanRemoval = true)
	@LazyCollection(LazyCollectionOption.FALSE)
//	@Cascade(CascadeType.MERGE)
	private List<RelativeLocation> relativeLocations;

	@JsonIgnore
	@OneToOne(mappedBy = "geometry")
	private Point point;

	@JsonIgnore
	@OneToMany(mappedBy = "geometry")
	@LazyCollection(LazyCollectionOption.FALSE)
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

	public List<Coordinate> getCoordinates() {
		return coordinates;
	}

	public void setCoordinates(List<Coordinate> coordinates) {
		this.coordinates = coordinates;
	}

	public void addCoordinate(Coordinate coordinate) {
		// only add if non-null
		if (coordinate != null) {
			if (coordinates == null) {
				coordinates = new ArrayList<>();
			}
			// add to both
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
	}

	public Location getLocation() {
		return location;
	}

	public void setLocation(Location location) {
		this.location = location;
	}

	public List<RelativeLocation> getRelativeLocations() {
		return relativeLocations;
	}

	public void setRelativeLocations(List<RelativeLocation> relativeLocations) {
		this.relativeLocations = relativeLocations;
	}

	public void addRelativeLocation(RelativeLocation relativeLocation) {
		// only add if non-null
		if (relativeLocation != null) {
			if (relativeLocations == null) {
				relativeLocations = new ArrayList<>();
			}
			// add only to Geometry (RelativeLocation updates Geometry)
			relativeLocations.add(relativeLocation);
//			relativeLocation.setGeometry(this);
		}
	}

	public void removeRelativeLocation(RelativeLocation relativeLocation) {
		// only remove if non-null
		if (relativeLocation != null) {
			relativeLocations.remove(relativeLocation);
			relativeLocation.setGeometry(null);
		}
	}

	public Point getPoint() {
		return point;
	}

	public void setPoint(Point point) {
		this.point = point;
	}

	public List<Forecast> getForecasts() {
		return forecasts;
	}

	public void setForecasts(List<Forecast> forecasts) {
		this.forecasts = forecasts;
	}

	public void addForecast(Forecast forecast) {
		// only add if non-null
		if (forecast != null) {
			if (forecasts == null) {
				forecasts = new ArrayList<>();
			}
			// add to both
			forecasts.add(forecast);
			forecast.setGeometry(this);
		}
	}

	public void removeForecast(Forecast forecast) {
		// only remove if non-null
		if (forecast != null) {
			forecasts.remove(forecast);
			forecast.setGeometry(null);
		}
	}

	public Set<String> getReferencedBy() {
		Set<String> output = new LinkedHashSet<>();
		StringBuilder builder = new StringBuilder();

		// if Geometry has Location
		if (location != null) {
			// show name and id of Location
			builder.append("Location: " + location.getName());
			builder.append(" (id: " + location.getId() + ")");
			output.add(builder.toString());
			builder = new StringBuilder();
		}

		// if Geometry has RelativeLocations
		if (relativeLocations != null && relativeLocations.size() > 0) {
			// show id and city of each RelativeLocation
			Iterator<RelativeLocation> it = relativeLocations.iterator();
			int count = 0;

			while (it.hasNext()) {
				RelativeLocation relativeLocation = it.next();
				count++;

				builder.append("RelativeLocation " + count);
				builder.append(" (id: " + relativeLocation.getId());
				builder.append(", city: " + relativeLocation.getCity() + ")");
				output.add(builder.toString());
				builder = new StringBuilder();
			}
		}

		// if Geometry has Point
		if (point != null) {
			// show point.getLocation().getName() and id of Point
			builder.append("Point Location Name: " + point.getLocation().getName());
			builder.append(" (Point id: " + point.getId() + ")");
			output.add(builder.toString());
			builder = new StringBuilder();
		}

		// if Geometry has forecasts
		if (forecasts != null && forecasts.size() > 0) {
			// show id and whether Forecast is hourly or normal
			// show Forecast's Location id and name
			// show Location's Point id
			// show number of Periods for each Forecast
			for (Forecast fc : forecasts) {
				builder.append("Forecast (");
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

				}

				builder.append(fc.getPeriods().size() + " periods");
			}
		}

		return output;
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
		builder.append("\nreferencedBy=");
		builder.append(getReferencedBy());

		builder.append("\n*** END Geometry ***");
		return builder.toString();
	}

}
