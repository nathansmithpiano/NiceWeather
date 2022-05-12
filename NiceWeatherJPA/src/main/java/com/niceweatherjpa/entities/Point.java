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
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "point")
public class Point {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@Column(name = "id_url")
	private String idUrl;

	private String type;

	@Column(name = "properties_id")
	private String propertiesId;

	@Column(name = "properties_type")
	private String propertiesType;

	private String cwa;

	@Column(name = "grid_id")
	private String gridId;

	@Column(name = "grid_x")
	private Integer gridX;

	@Column(name = "grid_y")
	private Integer gridY;

	@Column(name = "time_zone")
	private String timeZone;

	@Column(name = "radar_station")
	private String radarStation;

	@JsonIgnore
	@OneToOne
	@Cascade(CascadeType.MERGE)
	@JoinColumn(name = "location_id")
	private Location location;

	@JsonIgnore
	@OneToOne
	@Cascade({ CascadeType.PERSIST, CascadeType.MERGE, CascadeType.DELETE })
	@JoinColumn(name = "geometry_id")
	private Geometry geometry;

	@JsonIgnore
	@OneToOne
	@Cascade({ CascadeType.PERSIST, CascadeType.MERGE, CascadeType.DELETE })
	@JoinColumn(name = "relative_location_id")
	private RelativeLocation relativeLocation;

	@JsonIgnore
	@OneToMany(mappedBy = "point", orphanRemoval = true)
	@Cascade({ CascadeType.PERSIST, CascadeType.MERGE, CascadeType.DELETE })
	@LazyCollection(LazyCollectionOption.FALSE)
	private List<Forecast> forecasts;

	public Point() {
		super();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getIdUrl() {
		return idUrl;
	}

	public void setIdUrl(String idUrl) {
		this.idUrl = idUrl;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getPropertiesId() {
		return propertiesId;
	}

	public void setPropertiesId(String propertiesId) {
		this.propertiesId = propertiesId;
	}

	public String getPropertiesType() {
		return propertiesType;
	}

	public void setPropertiesType(String propertiesType) {
		this.propertiesType = propertiesType;
	}

	public String getCwa() {
		return cwa;
	}

	public void setCwa(String cwa) {
		this.cwa = cwa;
	}

	public String getGridId() {
		return gridId;
	}

	public void setGridId(String gridId) {
		this.gridId = gridId;
	}

	public Integer getGridX() {
		return gridX;
	}

	public void setGridX(Integer gridX) {
		this.gridX = gridX;
	}

	public Integer getGridY() {
		return gridY;
	}

	public void setGridY(Integer gridY) {
		this.gridY = gridY;
	}

	public String getTimeZone() {
		return timeZone;
	}

	public void setTimeZone(String timeZone) {
		this.timeZone = timeZone;
	}

	public String getRadarStation() {
		return radarStation;
	}

	public void setRadarStation(String radarStation) {
		this.radarStation = radarStation;
	}

	public Location getLocation() {
		return location;
	}

	public void setLocation(Location location) {
		this.location = location;
		// set to Location's Point (Point will update Location)
		location.setPoint(this);
	}

	public Geometry getGeometry() {
		return geometry;
	}

	public void setGeometry(Geometry geometry) {
		this.geometry = geometry;

		// set to Geometry
		if (geometry != null) {
			geometry.setPoint(this);
		}
	}

	public RelativeLocation getRelativeLocation() {
		return relativeLocation;
	}

	public void setRelativeLocation(RelativeLocation relativeLocation) {
		this.relativeLocation = relativeLocation;
		// add to RelativeLocation's Points (Point is owner of RelativeLocation)
		if (relativeLocation != null) {
			relativeLocation.addPoint(this);
		}
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
			forecast.setPoint(this);
		}
	}

	public void removeForecast(Forecast forecast) {
		// only remove if non-null
		if (forecast != null) {
			forecasts.remove(forecast);
			forecast.setPoint(null);
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
		Point other = (Point) obj;
		return id == other.id;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("\n*** Point ***\nid=");
		builder.append(id);
		builder.append("\nidUrl=");
		builder.append(idUrl);
		builder.append("\ntype=");
		builder.append(type);
		builder.append("\npropertiesId=");
		builder.append(propertiesId);
		builder.append("\npropertiesType=");
		builder.append(propertiesType);
		builder.append("\ncwa=");
		builder.append(cwa);
		builder.append("\ngridId=");
		builder.append(gridId);
		builder.append("\ngridX=");
		builder.append(gridX);
		builder.append("\ngridY=");
		builder.append(gridY);
		builder.append("\ntimeZone=");
		builder.append(timeZone);
		builder.append("\nradarStation=");
		builder.append(radarStation);
		builder.append("\n*** END Point ***");

		// if Point has Location
		if (location != null) {
			// print name and id of Location
			builder.append("\nLocation: " + location.getName());
			builder.append(" (id: " + location.getId() + ")");
		} else {
			builder.append("\nNO LOCATION");
		}

		// if Point has Geometry
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

		// if Point has RelativeLocation
		if (relativeLocation != null) {
			// print ID of RelativeLocation
			builder.append("\nrelativeLocation.getId()=");
			builder.append(relativeLocation.getId());
		} else {
			builder.append("\nNO RELATIVE LOCATION");
		}

		// if Point has forecasts
		if (forecasts != null && forecasts.size() > 0) {
			// if number of forecasts is valid (2)
			if (forecasts.size() == 2) {
				// print hourly/normal, id, and number of periods for each Forecast
				for (Forecast fc : forecasts) {
					builder.append("\nForecast (");
					builder.append(fc.isHourly() ? "hourly" : "normal");
					builder.append(", id: " + fc.getId() + "): ");
					builder.append(fc.getPeriods().size() + " periods");
				}

			} else {
				builder.append("\nINVALID NUMBER OF FORECASTS (should be 2");
			}
		} else {
			builder.append("\nNO FORECASTS");
		}

		return builder.toString();
	}

}
