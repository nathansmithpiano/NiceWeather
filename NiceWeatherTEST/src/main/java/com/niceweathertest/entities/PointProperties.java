package com.niceweathertest.entities;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.ObjectMapper;

@JsonIgnoreProperties(value = { "@id", "@type" })
public class PointProperties {

	private String cwa;
	private String forecastOffice;
	private String gridId;
	private Integer gridX;
	private Integer gridY;
//	private String forecast;
//	private String forecastHourly;
	private String forecastGridData;
	private String observationStations;
	private RelativeLocation relativeLocation;
	private String forecastZone;
	private String county;
	private String fireWeatherZone;
	private String timeZone;
	private String radarStation;

	private List<Forecast> forecastList;

	public PointProperties() {
		super();
	}

	public String getCwa() {
		return cwa;
	}

	public void setCwa(String cwa) {
		this.cwa = cwa;
	}

	public String getForecastOffice() {
		return forecastOffice;
	}

	public void setForecastOffice(String forecastOffice) {
		this.forecastOffice = forecastOffice;
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

	public void setForecast(String forecast) {
		addForecast(createForecastFromUrl(forecast));
	}

	public Forecast createForecastFromUrl(String url) {
		ObjectMapper mapper = new ObjectMapper();

		Forecast forecast = null;

		try {
			forecast = mapper.readValue(new URL(url), Forecast.class);
		} catch (IOException e) {
			e.printStackTrace();
		}

		return forecast;
	}

	public void addForecast(Forecast forecast) {
		if (forecastList == null) {
			forecastList = new ArrayList<>();
		}
		forecastList.add(forecast);
	}

	public void setForecastHourly(String forecastHourly) {
		addForecast(createForecastFromUrl(forecastHourly));
	}
	
	public List<Forecast> getForecastList() {
		return forecastList;
	}

	public void setForecastList(List<Forecast> forecastList) {
		this.forecastList = forecastList;
	}

	public String getForecastGridData() {
		return forecastGridData;
	}

	public void setForecastGridData(String forecastGridData) {
		this.forecastGridData = forecastGridData;
	}

	public String getObservationStations() {
		return observationStations;
	}

	public void setObservationStations(String observationStations) {
		this.observationStations = observationStations;
	}

	public RelativeLocation getRelativeLocation() {
		return relativeLocation;
	}

	public void setRelativeLocation(RelativeLocation relativeLocation) {
		this.relativeLocation = relativeLocation;
	}

	public String getForecastZone() {
		return forecastZone;
	}

	public void setForecastZone(String forecastZone) {
		this.forecastZone = forecastZone;
	}

	public String getCounty() {
		return county;
	}

	public void setCounty(String county) {
		this.county = county;
	}

	public String getFireWeatherZone() {
		return fireWeatherZone;
	}

	public void setFireWeatherZone(String fireWeatherZone) {
		this.fireWeatherZone = fireWeatherZone;
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

	@Override
	public int hashCode() {
		return Objects.hash(county, cwa, fireWeatherZone, forecastGridData, forecastList, forecastOffice, forecastZone,
				gridId, gridX, gridY, observationStations, radarStation, relativeLocation, timeZone);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		PointProperties other = (PointProperties) obj;
		return Objects.equals(county, other.county) && Objects.equals(cwa, other.cwa)
				&& Objects.equals(fireWeatherZone, other.fireWeatherZone)
				&& Objects.equals(forecastGridData, other.forecastGridData)
				&& Objects.equals(forecastList, other.forecastList)
				&& Objects.equals(forecastOffice, other.forecastOffice)
				&& Objects.equals(forecastZone, other.forecastZone) && Objects.equals(gridId, other.gridId)
				&& Objects.equals(gridX, other.gridX) && Objects.equals(gridY, other.gridY)
				&& Objects.equals(observationStations, other.observationStations)
				&& Objects.equals(radarStation, other.radarStation)
				&& Objects.equals(relativeLocation, other.relativeLocation) && Objects.equals(timeZone, other.timeZone);
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("\n*** PointProperties ***\ncwa=");
		builder.append(cwa);
		builder.append("\nforecastOffice=");
		builder.append(forecastOffice);
		builder.append("\ngridId=");
		builder.append(gridId);
		builder.append("\ngridX=");
		builder.append(gridX);
		builder.append("\ngridY=");
		builder.append(gridY);
		builder.append("\nforecastGridData=");
		builder.append(forecastGridData);
		builder.append("\nobservationStations=");
		builder.append(observationStations);
		builder.append("\nrelativeLocation=");
		builder.append(relativeLocation);
		builder.append("\nforecastZone=");
		builder.append(forecastZone);
		builder.append("\ncounty=");
		builder.append(county);
		builder.append("\nfireWeatherZone=");
		builder.append(fireWeatherZone);
		builder.append("\ntimeZone=");
		builder.append(timeZone);
		builder.append("\nradarStation=");
		builder.append(radarStation);
		builder.append("\nforecastList=");
		builder.append(forecastList);
		builder.append("\n*** END PointProperties ***");
		return builder.toString();
	}

}
