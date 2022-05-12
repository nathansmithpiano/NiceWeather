package com.niceweathertest.entities;

import java.time.OffsetDateTime;
import java.util.Objects;

public class Period {

	private int number;
	private String name;
	private OffsetDateTime startTime;
	private OffsetDateTime endTime;
	private boolean isDaytime;
	private int temperature;
	private String temperatureUnit;
	private String temperatureTrend;
	private String windSpeed;
	private String windDirection;
	private String icon;
	private String shortForecast;
	private String detailedForecast;

	public Period() {
		super();
	}

	public int getNumber() {
		return number;
	}

	public void setNumber(int number) {
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

	public void setStartTime(String str) {
		this.startTime = OffsetDateTime.parse(str);
	}

	public OffsetDateTime getEndTime() {
		return endTime;
	}

	public void setEndTime(String str) {
		this.endTime = OffsetDateTime.parse(str);
	}

	public boolean isDaytime() {
		return isDaytime;
	}

	public void setIsDaytime(boolean isDaytime) {
		this.isDaytime = isDaytime;
	}

	public int getTemperature() {
		return temperature;
	}

	public void setTemperature(int temperature) {
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

	@Override
	public int hashCode() {
		return Objects.hash(detailedForecast, endTime, icon, isDaytime, name, number, shortForecast, startTime,
				temperature, temperatureTrend, temperatureUnit, windDirection, windSpeed);
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
		return Objects.equals(detailedForecast, other.detailedForecast) && Objects.equals(endTime, other.endTime)
				&& Objects.equals(icon, other.icon) && isDaytime == other.isDaytime && Objects.equals(name, other.name)
				&& number == other.number && Objects.equals(shortForecast, other.shortForecast)
				&& Objects.equals(startTime, other.startTime) && temperature == other.temperature
				&& Objects.equals(temperatureTrend, other.temperatureTrend)
				&& Objects.equals(temperatureUnit, other.temperatureUnit)
				&& Objects.equals(windDirection, other.windDirection) && Objects.equals(windSpeed, other.windSpeed);
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("\n*** Period ***\nnumber=");
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
		builder.append("\nwindDirection=");
		builder.append(windDirection);
		builder.append("\nicon=");
		builder.append(icon);
		builder.append("\nshortForecast=");
		builder.append(shortForecast);
		builder.append("\ndetailedForecast=");
		builder.append(detailedForecast);
		builder.append("\n*** END Period ***");
		return builder.toString();
	}

}
