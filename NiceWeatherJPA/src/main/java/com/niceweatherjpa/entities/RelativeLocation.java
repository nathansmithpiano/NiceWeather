package com.niceweatherjpa.entities;

import java.util.ArrayList;
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

@Entity
@Table(name = "relative_location")
public class RelativeLocation {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@ManyToOne
	@JoinColumn(name = "geometry_id")
	private Geometry geometry;

	@OneToMany(mappedBy = "relativeLocation")
	private List<Point> pointList;

	private String type;

	private String city;

	private String state;

	@Column(name = "distance_unit_code")
	private String distanceUnitCode;

	private Double distance;

	@Column(name = "bearing_unit_code")
	private String bearingUnitCode;

	private Integer bearing;

	public RelativeLocation() {
		super();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Geometry getGeometry() {
		return geometry;
	}

	public void setGeometry(Geometry geometry) {
		this.geometry = geometry;
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

	public List<Point> getPointList() {
		return pointList;
	}

	public void setPointList(List<Point> pointList) {
		this.pointList = pointList;
	}

	public void addPoint(Point point) {
		if (pointList == null) {
			pointList = new ArrayList<>();
		}
		if (!pointList.contains(point)) {
			pointList.add(point);
			point.setRelativeLocation(this);
		}
	}

	public void removePoint(Point point) {
		if (point != null && pointList.contains(point)) {
			pointList.remove(point);
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
		if (geometry != null) {
			builder.append("\ngeometry.getId()=");
			builder.append(geometry.getId());
		} else {
			builder.append("\nNO GEOMETRY");
		}
		if (pointList != null && pointList.size() > 0) {
			builder.append("\npointList.size()=");
			builder.append(pointList.size());
		} else {
			builder.append("\nNO POINTS");
		}
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
		builder.append("\n*** END RelativeLocation ***");
		return builder.toString();
	}

}
