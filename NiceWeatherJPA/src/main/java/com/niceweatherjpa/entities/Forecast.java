package com.niceweatherjpa.entities;

import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
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
	
	@JsonIgnore
	@ManyToMany
	@JoinTable(name = "point_forecast", joinColumns = @JoinColumn(name = "forecast_id"), inverseJoinColumns = @JoinColumn(name = "point_id"))
	private List<Point> pointList;
	
	@JsonIgnore
	@OneToMany(mappedBy = "forecast")
	private List<Period> periodList;

	@ManyToOne
	@JoinColumn(name = "geometry_id")
	private Geometry geometry;

	private String url;

	@Column(name = "is_hourly")
	private boolean isHourly;

	private String type;

	private OffsetDateTime updated;

	private String units;

	@Column(name = "forecast_generator")
	private String forecastGenerator;

	@Column(name = "generated_at")
	private OffsetDateTime generatedAt;

	@Column(name = "update_time")
	private OffsetDateTime updateTime;

	@Column(name = "valid_times_begin")
	private OffsetDateTime validTimesBegin;

	@Column(name = "valid_times_end")
	private OffsetDateTime validTimesEnd;

	@Column(name = "elevation_unit_code")
	private String elevationUnitCode;

	private Double elevation;

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

	public Geometry getGeometry() {
		return geometry;
	}

	public void setGeometry(Geometry geometry) {
		this.geometry = geometry;
	}

	public boolean isHourly() {
		return isHourly;
	}

	public void setHourly(boolean isHourly) {
		this.isHourly = isHourly;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
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
			point.addForecast(this);
		}
	}

	public void removePoint(Point point) {
		if (pointList != null && pointList.contains(point)) {
			pointList.remove(point);
			point.removeForecast(null);
		}
	}

	public List<Period> getPeriodList() {
		return periodList;
	}

	public void setPeriodList(List<Period> periodList) {
		this.periodList = periodList;
	}

	public void addPeriod(Period period) {
		if (periodList == null) {
			periodList = new ArrayList<>();
		}
		if (!periodList.contains(period)) {
			periodList.add(period);
			period.setForecast(this);
		}
	}

	public void removePeriod(Period period) {
		if (periodList != null && periodList.contains(period)) {
			periodList.remove(period);
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
		if (pointList != null && pointList.size() > 0) {
			builder.append("\npointList.size()=");
			builder.append(pointList.size());
		} else {
			builder.append("\nNO POINTS");
		}
		if (periodList != null && periodList.size() > 0) {
			builder.append("\nperiodList.size()=");
			builder.append(periodList.size());
		} else {
			builder.append("\nNO PERIODS");
		}
		if (geometry != null) {
			builder.append("\ngeometry.getId()=");
			builder.append(geometry.getId());
		} else {
			builder.append("\nNO GEOMETRY");
		}
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
		builder.append("\n*** END Forecast ***");
		return builder.toString();
	}

}
