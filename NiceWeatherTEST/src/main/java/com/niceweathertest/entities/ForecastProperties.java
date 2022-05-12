package com.niceweathertest.entities;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.Objects;

public class ForecastProperties {

	private OffsetDateTime updated;
	private String units;
	private String forecastGenerator;
	private OffsetDateTime generatedAt;
	private OffsetDateTime updateTime;
	private String validTimes;
	private Elevation elevation;
	private List<Period> periods;

	public ForecastProperties() {
		super();
	}

	public OffsetDateTime getUpdated() {
		return updated;
	}

	public void setUpdated(String str) {
		this.updated = OffsetDateTime.parse(str);
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

	public void setGeneratedAt(String str) {
		this.generatedAt = OffsetDateTime.parse(str);
	}

	public OffsetDateTime getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(String str) {
		this.updateTime = OffsetDateTime.parse(str);
	}

	public String getValidTimes() {
		return validTimes;
	}

	public void setValidTimes(String validTimes) {
		this.validTimes = validTimes;
	}

	public Elevation getElevation() {
		return elevation;
	}

	public void setElevation(Elevation elevation) {
		this.elevation = elevation;
	}

	public List<Period> getPeriods() {
		return periods;
	}

	public void setPeriods(List<Period> periods) {
		this.periods = periods;
	}

	@Override
	public int hashCode() {
		return Objects.hash(elevation, forecastGenerator, generatedAt, periods, units, updateTime, updated, validTimes);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ForecastProperties other = (ForecastProperties) obj;
		return Objects.equals(elevation, other.elevation) && Objects.equals(forecastGenerator, other.forecastGenerator)
				&& Objects.equals(generatedAt, other.generatedAt) && Objects.equals(periods, other.periods)
				&& Objects.equals(units, other.units) && Objects.equals(updateTime, other.updateTime)
				&& Objects.equals(updated, other.updated) && Objects.equals(validTimes, other.validTimes);
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("\n*** ForecastProperties ***\nupdated=");
		builder.append(updated);
		builder.append("\nunits=");
		builder.append(units);
		builder.append("\nforecastGenerator=");
		builder.append(forecastGenerator);
		builder.append("\ngeneratedAt=");
		builder.append(generatedAt);
		builder.append("\nupdateTime=");
		builder.append(updateTime);
		builder.append("\nvalidTimes=");
		builder.append(validTimes);
		builder.append("\nelevation=");
		builder.append(elevation);
		builder.append("\nperiods=");
		builder.append(periods);
		builder.append("\n*** END ForecastProperties ***");
		return builder.toString();
	}

}