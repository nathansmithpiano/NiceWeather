package com.niceweatherjpa.entities;

import java.time.OffsetDateTime;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.Objects;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "forecast")
public class Forecast {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	private String url;

	private String type;

	@Column(name = "is_hourly")
	private boolean isHourly;

	private OffsetDateTime updated;

	private String units;

	@Column(name = "forecast_generator")
	private String forecastGenerator;

	@Column(name = "generated_at")
	private OffsetDateTime generatedAt;

	@Column(name = "update_time")
	private OffsetDateTime updateTime;

	@Column(name = "valid_times")
	private String validTimes;

	@Column(name = "valid_times_begin")
	private OffsetDateTime validTimesBegin;

	@Column(name = "valid_times_end")
	private OffsetDateTime validTimesEnd;

	@Column(name = "elevation_unit_code")
	private String elevationUnitCode;

	private Double elevation;
	
	@JsonIgnore
	@ManyToOne
	@JoinColumn(name = "point_id")
	private Point point;

	@JsonIgnore
	@ManyToOne
	@JoinColumn(name = "geometry_id")
	private Geometry geometry;

	@JsonIgnore
	@OneToMany(mappedBy = "forecast")
	private Set<Period> periods;

	public Forecast() {
		super();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public boolean isHourly() {
		return isHourly;
	}

	public void setHourly(boolean isHourly) {
		this.isHourly = isHourly;
	}

	public OffsetDateTime getUpdated() {
		return updated;
	}

	public void setUpdated(OffsetDateTime updated) {
		this.updated = updated;
	}

	public String getUnits() {
		return units;
	}

	public void setUnits(String units) {
		this.units = units;
	}

	public String getForecastGenerator() {
		return forecastGenerator;
	}

	public void setForecastGenerator(String forecastGenerator) {
		this.forecastGenerator = forecastGenerator;
	}

	public OffsetDateTime getGeneratedAt() {
		return generatedAt;
	}

	public void setGeneratedAt(OffsetDateTime generatedAt) {
		this.generatedAt = generatedAt;
	}

	public OffsetDateTime getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(OffsetDateTime updateTime) {
		this.updateTime = updateTime;
	}

	public String getValidTimes() {
		return validTimes;
	}

	public void setValidTimes(String validTimes) {
		this.validTimes = validTimes;
	}

	public OffsetDateTime getValidTimesBegin() {
		return validTimesBegin;
	}

	public void setValidTimesBegin(OffsetDateTime validTimesBegin) {
		this.validTimesBegin = validTimesBegin;
	}

	public OffsetDateTime getValidTimesEnd() {
		return validTimesEnd;
	}

	public void setValidTimesEnd(OffsetDateTime validTimesEnd) {
		this.validTimesEnd = validTimesEnd;
	}

	public String getElevationUnitCode() {
		return elevationUnitCode;
	}

	public void setElevationUnitCode(String elevationUnitCode) {
		this.elevationUnitCode = elevationUnitCode;
	}

	public Double getElevation() {
		return elevation;
	}

	public void setElevation(Double elevation) {
		this.elevation = elevation;
	}
	
	public Point getPoint() {
		return point;
	}

	public void setPoint(Point point) {
		this.point = point;
		
		if (point != null && !point.getForecasts().contains(this)) {
			point.addForecast(this);
		}
	}

	public Geometry getGeometry() {
		return geometry;
	}

	public void setGeometry(Geometry geometry) {
		this.geometry = geometry;
		
		if (geometry != null && !geometry.getForecasts().contains(this)) {
			geometry.addForecast(this);
		}
	}

	public Set<Period> getPeriods() {
		return periods;
	}

	public void setPeriods(Set<Period> periods) {
		this.periods = periods;
	}
	
	public void addPeriod(Period period) {
		if (periods == null) {
			periods = new LinkedHashSet<>();
		}
		if (!periods.contains(period)) {
			periods.add(period);
			period.setForecast(this);
		}
	}

	public void removePeriod(Period period) {
		if (periods != null && periods.contains(period)) {
			periods.remove(period);
			period.setForecast(null);
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
		Forecast other = (Forecast) obj;
		return id == other.id;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("\n*** Forecast ***\nid=");
		builder.append(id);
		
		builder.append("\nurl=");
		builder.append(url);
		builder.append("\nisHourly=");
		builder.append(isHourly);
		builder.append("\ntype=");
		builder.append(type);
		builder.append("\nupdated=");
		builder.append(updated);
		builder.append("\nunits=");
		builder.append(units);
		builder.append("\nforecastGenerator=");
		builder.append(forecastGenerator);
		builder.append("\ngeneratedAt=");
		builder.append(generatedAt);
		builder.append("\nupdateTime=");
		builder.append(updateTime);
		builder.append("\nvalidTimesBegin=");
		builder.append(validTimesBegin);
		builder.append("\nvalidTimesEnd=");
		builder.append(validTimesEnd);
		builder.append("\nelevationUnitCode=");
		builder.append(elevationUnitCode);
		builder.append("\nelevation=");
		builder.append(elevation);
		
		// if Forecast has Point
		if (point != null) {
			// print point.getLocation().getName() and id of Point
			builder.append("\nPoint Location Name: " + point.getLocation().getName());
			builder.append(" (Point id: " + point.getId() + ")");
		} else {
			builder.append("\nNO POINT");
		}
		
		// if Forecast has Geometry
		if (geometry != null) {
			builder.append("\nGeometry: ");
			
			// if Geometry has 5 Coordinates (should have 5)
			if (geometry.getCoordinates() != null && geometry.getCoordinates().size() == 5) {
				// Print coordinates in Geometry
				Iterator<Coordinate> it = geometry.getCoordinates().iterator();
				
				while (it.hasNext()) {
					Coordinate coordinate = it.next();
					
					// print id, latitude, and longitude of Coordinate
					builder.append(" Coordinate (id: " + coordinate.getId());
					builder.append(", latitude: " + coordinate.getLatitude());
					builder.append(", longitude: " + coordinate.getLongitude() + ")");
				}
				
			} else {
				builder.append(" INVALID COORDINATES (should be 5), ERROR");
			}
			
		} else {
			builder.append("\nNO GEOMETRY");
		}
		
		// if Forecast has periods
		if (periods != null) {
			// if number of periods is valid (14 or 156)
			if (periods.size() == 14 || periods.size() == 156) {
				// Print number of periods
				builder.append("\nperiods.size()=");
				builder.append(periods.size());
			} else {
				builder.append("\nINVALID NUMBER OF PERIODS (should be 14 or 156)");
			}
			
		} else {
			builder.append("\nNO PERIODS");
		}
		
		builder.append("\n*** END Forecast ***");
		return builder.toString();
	}

}
