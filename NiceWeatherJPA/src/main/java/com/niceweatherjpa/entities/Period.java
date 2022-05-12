package com.niceweatherjpa.entities;

import java.time.OffsetDateTime;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "period")
public class Period {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	private Integer number;

	private String name;

	@Column(name = "start_time")
	private OffsetDateTime startTime;

	@Column(name = "end_time")
	private OffsetDateTime endTime;

	@Column(name = "is_daytime")
	private Boolean isDaytime;

	private Integer temperature;

	@Column(name = "temperature_unit")
	private String temperatureUnit;

	@Column(name = "temperature_trend")
	private String temperatureTrend;

	@Column(name = "wind_speed")
	private String windSpeed;

	@Column(name = "wind_max")
	private Integer windMin;

	@Column(name = "wind_min")
	private Integer windMax;

	@Column(name = "wind_direction")
	private String windDirection;

	private String icon;

	@Column(name = "short_forecast")
	private String shortForecast;

	@Column(name = "detailed_forecast")
	private String detailedForecast;

	@JsonIgnore
	@ManyToOne
	@JoinColumn(name = "forecast_id")
	@Cascade({ CascadeType.MERGE })
	private Forecast forecast;

	public Period() {
		super();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Integer getNumber() {
		return number;
	}

	public void setNumber(Integer number) {
		this.number = number;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public OffsetDateTime getStartTime() {
		return startTime;
	}

	public void setStartTime(OffsetDateTime startTime) {
		this.startTime = startTime;
	}

	public OffsetDateTime getEndTime() {
		return endTime;
	}

	public void setEndTime(OffsetDateTime endTime) {
		this.endTime = endTime;
	}

	public Boolean getIsDaytime() {
		return isDaytime;
	}

	public void setIsDaytime(Boolean isDaytime) {
		this.isDaytime = isDaytime;
	}

	public Integer getTemperature() {
		return temperature;
	}

	public void setTemperature(Integer temperature) {
		this.temperature = temperature;
	}

	public String getTemperatureUnit() {
		return temperatureUnit;
	}

	public void setTemperatureUnit(String temperatureUnit) {
		this.temperatureUnit = temperatureUnit;
	}

	public String getTemperatureTrend() {
		return temperatureTrend;
	}

	public void setTemperatureTrend(String temperatureTrend) {
		this.temperatureTrend = temperatureTrend;
	}

	public String getWindSpeed() {
		return windSpeed;
	}

	public void setWindSpeed(String windSpeed) {
		this.windSpeed = windSpeed;
	}

	public Integer getWindMin() {
		return windMin;
	}

	public void setWindMin(Integer windMin) {
		this.windMin = windMin;
	}

	public Integer getWindMax() {
		return windMax;
	}

	public void setWindMax(Integer windMax) {
		this.windMax = windMax;
	}

	public String getWindDirection() {
		return windDirection;
	}

	public void setWindDirection(String windDirection) {
		this.windDirection = windDirection;
	}

	public String getIcon() {
		return icon;
	}

	public void setIcon(String icon) {
		this.icon = icon;
	}

	public String getShortForecast() {
		return shortForecast;
	}

	public void setShortForecast(String shortForecast) {
		this.shortForecast = shortForecast;
	}

	public String getDetailedForecast() {
		return detailedForecast;
	}

	public void setDetailedForecast(String detailedForecast) {
		this.detailedForecast = detailedForecast;
	}

	public Forecast getForecast() {
		return forecast;
	}

	public void setForecast(Forecast forecast) {
		this.forecast = forecast;

		System.err.println("FORECAST " + forecast.getType() + " setForecast");
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
		Period other = (Period) obj;
		return id == other.id;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("\n*** Period ***\nid=");
		builder.append(id);
		builder.append("\nforecast=");
		builder.append(forecast);
		builder.append("\nnumber=");
		builder.append(number);
		builder.append("\nname=");
		builder.append(name);
		builder.append("\nstartTime=");
		builder.append(startTime);
		builder.append("\nendTime=");
		builder.append(endTime);
		builder.append("\nisDaytime=");
		builder.append(isDaytime);
		builder.append("\ntemperature=");
		builder.append(temperature);
		builder.append("\ntemperatureUnit=");
		builder.append(temperatureUnit);
		builder.append("\ntemperatureTrend=");
		builder.append(temperatureTrend);
		builder.append("\nwindSpeed=");
		builder.append(windSpeed);
		builder.append("\nwindMin=");
		builder.append(windMin);
		builder.append("\nwindMax=");
		builder.append(windMax);
		builder.append("\nwindDirection=");
		builder.append(windDirection);
		builder.append("\nicon=");
		builder.append(icon);
		builder.append("\nshortForecast=");
		builder.append(shortForecast);
		builder.append("\ndetailedForecast=");
		builder.append(detailedForecast);

		// if Period has Forecast
		if (forecast != null) {
			// print Forecast id, normal/hourly, and Point id and Location name
			builder.append("\nForecast: ");
			builder.append("(id: " + forecast.getId());
			builder.append(", " + (forecast.isHourly() ? "hourly" : "normal"));
			builder.append(", Point: ");

			if (forecast.getPoint() != null) {
				builder.append("id: " + forecast.getPoint().getId());

				if (forecast.getPoint().getLocation() != null) {
					builder.append(", Location name: " + forecast.getPoint().getLocation().getName());
				} else {
					builder.append(", NO LOCATION");
				}

			} else {
				builder.append(" NO POINT");
			}

		} else {
			builder.append("\nNO FORECAST");
		}

		builder.append("\n*** END Period ***");
		return builder.toString();
	}

}
