//package com.niceweatherjpa.entities;
//
//import java.util.ArrayList;
//import java.util.List;
//import java.util.Objects;
//
//import javax.persistence.Column;
//import javax.persistence.Entity;
//import javax.persistence.GeneratedValue;
//import javax.persistence.GenerationType;
//import javax.persistence.Id;
//import javax.persistence.JoinColumn;
//import javax.persistence.JoinTable;
//import javax.persistence.ManyToMany;
//import javax.persistence.OneToOne;
//import javax.persistence.Table;
//
//import com.fasterxml.jackson.annotation.JsonIgnore;
//
//@Entity
//@Table(name = "point")
//public class Point {
//
//	@Id
//	@GeneratedValue(strategy = GenerationType.IDENTITY)
//	private int id;
//	
//	@JsonIgnore
//	@ManyToMany
//	@JoinTable(name = "point_forecast", joinColumns = @JoinColumn(name = "point_id"), inverseJoinColumns = @JoinColumn(name = "forecast_id"))
//	private List<Forecast> forecastList;
//	
//	@JsonIgnore
//	@OneToOne
//	@JoinColumn(name = "location_id", referencedColumnName = "id")
//	private Location location;
//
//	@JsonIgnore
//	@OneToOne
//	@JoinColumn(name = "geometry_id", referencedColumnName = "id")
//	private Geometry geometry;
//	
//	@JsonIgnore
//	@OneToOne
//	@JoinColumn(name = "relative_location_id", referencedColumnName = "id")
//	private RelativeLocation relativeLocation;
//
//	@Column(name = "id_url")
//	private String idUrl;
//
//	private String type;
//
//	@Column(name = "properties_id")
//	private String propertiesId;
//
//	@Column(name = "properties_type")
//	private String propertiesType;
//
//	private String cwa;
//
//	@Column(name = "grid_id")
//	private String gridId;
//
//	@Column(name = "grid_x")
//	private Integer gridX;
//
//	@Column(name = "grid_y")
//	private Integer gridY;
//
//	@Column(name = "time_zone")
//	private String timeZone;
//
//	@Column(name = "radar_station")
//	private String radarStation;
//
//	public Point() {
//		super();
//	}
//
//	public int getId() {
//		return id;
//	}
//
//	public void setId(int id) {
//		this.id = id;
//	}
//
//	public Location getLocation() {
//		return location;
//	}
//
//	public void setLocation(Location location) {
//		this.location = location;
//	}
//
//	public Geometry getGeometry() {
//		return geometry;
//	}
//
//	public void setGeometry(Geometry geometry) {
//		this.geometry = geometry;
//	}
//
//	public RelativeLocation getRelativeLocation() {
//		return relativeLocation;
//	}
//
//	public void setRelativeLocation(RelativeLocation relativeLocation) {
//		this.relativeLocation = relativeLocation;
//	}
//
//	public String getIdUrl() {
//		return idUrl;
//	}
//
//	public void setIdUrl(String idUrl) {
//		this.idUrl = idUrl;
//	}
//
//	public String getType() {
//		return type;
//	}
//
//	public void setType(String type) {
//		this.type = type;
//	}
//
//	public String getPropertiesId() {
//		return propertiesId;
//	}
//
//	public void setPropertiesId(String propertiesId) {
//		this.propertiesId = propertiesId;
//	}
//
//	public String getPropertiesType() {
//		return propertiesType;
//	}
//
//	public void setPropertiesType(String propertiesType) {
//		this.propertiesType = propertiesType;
//	}
//
//	public String getCwa() {
//		return cwa;
//	}
//
//	public void setCwa(String cwa) {
//		this.cwa = cwa;
//	}
//
//	public String getGridId() {
//		return gridId;
//	}
//
//	public void setGridId(String gridId) {
//		this.gridId = gridId;
//	}
//
//	public Integer getGridX() {
//		return gridX;
//	}
//
//	public void setGridX(Integer gridX) {
//		this.gridX = gridX;
//	}
//
//	public Integer getGridY() {
//		return gridY;
//	}
//
//	public void setGridY(Integer gridY) {
//		this.gridY = gridY;
//	}
//
//	public String getTimeZone() {
//		return timeZone;
//	}
//
//	public void setTimeZone(String timeZone) {
//		this.timeZone = timeZone;
//	}
//
//	public String getRadarStation() {
//		return radarStation;
//	}
//
//	public void setRadarStation(String radarStation) {
//		this.radarStation = radarStation;
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
//			forecast.addPoint(this);
//		}
//	}
//
//	public void removeForecast(Forecast forecast) {
//		if (forecastList != null && forecastList.contains(forecast)) {
//			forecastList.remove(forecast);
//			forecast.removePoint(this);
//		}
//	}
//
//	@Override
//	public int hashCode() {
//		return Objects.hash(id);
//	}
//
//	@Override
//	public boolean equals(Object obj) {
//		if (this == obj)
//			return true;
//		if (obj == null)
//			return false;
//		if (getClass() != obj.getClass())
//			return false;
//		Point other = (Point) obj;
//		return Objects.equals(id, other.id);
//	}
//
//	@Override
//	public String toString() {
//		StringBuilder builder = new StringBuilder();
//		builder.append("\n*** Point ***\nid=");
//		builder.append(id);
//		if (forecastList != null && forecastList.size() > 0) {
//			builder.append("\nforecastList.size()=");
//			builder.append(forecastList.size());
//		} else {
//			builder.append("\nNO FORECASTS");
//		}
//		if (location != null) {
//			builder.append("\nlocation.getId()=");
//			builder.append(location.getId());
//		} else {
//			builder.append("\nNO LOCATION");
//		}
//		if (geometry != null) {
//			builder.append("\ngeometry.getId()=");
//			builder.append(geometry.getId());
//		} else {
//			builder.append("\nNO GEOMETRY");
//		}
//		if (relativeLocation != null) {
//			builder.append("\nrelativeLocation.getId()=");
//			builder.append(relativeLocation.getId());
//		} else {
//			builder.append("\nNO RELATIVE LOCATION");
//		}
//		builder.append("\nidUrl=");
//		builder.append(idUrl);
//		builder.append("\ntype=");
//		builder.append(type);
//		builder.append("\npropertiesId=");
//		builder.append(propertiesId);
//		builder.append("\npropertiesType=");
//		builder.append(propertiesType);
//		builder.append("\ncwa=");
//		builder.append(cwa);
//		builder.append("\ngridId=");
//		builder.append(gridId);
//		builder.append("\ngridX=");
//		builder.append(gridX);
//		builder.append("\ngridY=");
//		builder.append(gridY);
//		builder.append("\ntimeZone=");
//		builder.append(timeZone);
//		builder.append("\nradarStation=");
//		builder.append(radarStation);
//		builder.append("\n*** END Point ***");
//		return builder.toString();
//	}
//
//}
