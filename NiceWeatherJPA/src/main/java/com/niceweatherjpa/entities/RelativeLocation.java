package com.niceweatherjpa.entities;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "relative_location")
public class RelativeLocation {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	private String type;

	private String city;

	private String state;

	@Column(name = "distance_unit_code")
	private String distanceUnitCode;

	private Double distance;

	@Column(name = "bearing_unit_code")
	private String bearingUnitCode;

	private Integer bearing;

//	@JsonIgnore
	@ManyToOne
	@JoinColumn(name = "geometry_id")
	@Cascade({ CascadeType.PERSIST, CascadeType.MERGE, CascadeType.DELETE })
	private Geometry geometry;

	@JsonIgnore
	@OneToMany(mappedBy = "relativeLocation")
	@Cascade({ CascadeType.MERGE })
	@LazyCollection(LazyCollectionOption.FALSE)
	private List<Point> points;

	public RelativeLocation() {
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

	public String getDistanceUnitCode() {
		return distanceUnitCode;
	}

	public void setDistanceUnitCode(String distanceUnitCode) {
		this.distanceUnitCode = distanceUnitCode;
	}

	public Double getDistance() {
		return distance;
	}

	public void setDistance(Double distance) {
		this.distance = distance;
	}

	public String getBearingUnitCode() {
		return bearingUnitCode;
	}

	public void setBearingUnitCode(String bearingUnitCode) {
		this.bearingUnitCode = bearingUnitCode;
	}

	public Integer getBearing() {
		return bearing;
	}

	public void setBearing(Integer bearing) {
		this.bearing = bearing;
	}

	public Geometry getGeometry() {
		return geometry;
	}

	public void setGeometry(Geometry geometry) {
		this.geometry = geometry;
		
		// add to Geometry (RelativeLocation updates Geometry)
		if (geometry != null) {
			geometry.addRelativeLocation(this);
		}
	}

	public List<Point> getPoints() {
		return points;
	}

	public void setPoints(List<Point> points) {
		this.points = points;
	}

	public void addPoint(Point point) {
		// only add if non-null
		if (point != null) {
			if (points == null) {
				points = new ArrayList<>();
			}
			// add only to RelativeLocation (Point updates RelativeLocation)
			points.add(point);
		}
	}

	public void removePoint(Point point) {
		// only remove if non-null
		if (point != null) {
			points.remove(point);
			point.setRelativeLocation(null);
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
		RelativeLocation other = (RelativeLocation) obj;
		return id == other.id;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("\n*** RelativeLocation ***\nid=");
		builder.append(id);
		builder.append("\ntype=");
		builder.append(type);
		builder.append("\ncity=");
		builder.append(city);
		builder.append("\nstate=");
		builder.append(state);
		builder.append("\ndistanceUnitCode=");
		builder.append(distanceUnitCode);
		builder.append("\ndistance=");
		builder.append(distance);
		builder.append("\nbearingUnitCode=");
		builder.append(bearingUnitCode);
		builder.append("\nbearing=");
		builder.append(bearing);

		// if RelativeLocation has Geometry
		if (geometry != null) {
			builder.append("\nGeometry: ");

			// if Geometry has only 1 Coordinate (should only have 1)
			if (geometry.getCoordinates() != null && geometry.getCoordinates().size() == 1) {
				// Get first coordinate in Geometry
				Coordinate coordinate = geometry.getCoordinates().iterator().next();

				// print id, latitude, and longitude of Coordinate
				builder.append(" Coordinate (id: " + coordinate.getId());
				builder.append(", latitude: " + coordinate.getLatitude());
				builder.append(", longitude: " + coordinate.getLongitude() + ")");
			} else {
				builder.append(" INVALID COORDINATE, ERROR");
			}

		} else {
			builder.append("\nNO GEOMETRY");
		}

		// if RelativeLocation has points
		if (points != null && points.size() > 0) {
			// print location.getName() and id of each Point
			Iterator<Point> it = points.iterator();
			int count = 0;

			while (it.hasNext()) {
				Point point = it.next();
				count++;

				builder.append("\nPoint " + count + ": ");

				// print name of Point's Location
				if (point.getLocation() != null) {
					builder.append(point.getLocation().getName());
				} else {
					builder.append("NO LOCATION");
				}

				builder.append(" (id: " + point.getId() + ")");
			}
		} else {
			builder.append("\nNO POINTS");
		}

		builder.append("\n*** END RelativeLocation ***");
		return builder.toString();
	}

}
