package com.niceweathertest.client;

import java.io.IOException;
import java.net.URL;
import java.time.Duration;
import java.time.OffsetDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Set;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.niceweathertest.entities.Forecast;
import com.niceweathertest.entities.Period;
import com.niceweathertest.entities.Point;

public class WedMay11Test {

	public static void main(String[] args) {
		WedMay11Test app = new WedMay11Test();
		app.testPoint();
//		app.timeTest();
	}

	private void testPoint() {
		String url = "https://api.weather.gov/points/39.11771,-106.445335";
		ObjectMapper mapper = new ObjectMapper();

		Point point = null;

		try {
			point = mapper.readValue(new URL(url), Point.class);
		} catch (IOException e) {
			e.printStackTrace();
		}

//		OffsetDateTime updateTime = point.getProperties().getForecastList().get(0).getProperties().getUpdateTime();

		// See how long it's been since updated, generatedAt, updateTime, and startTime
		// endTime for each period

		ZoneId myZoneId = ZoneId.of("America/Denver");
		ZonedDateTime now = OffsetDateTime.now().atZoneSameInstant(myZoneId);
		System.out.println(now);

		for (Forecast forecast : point.getProperties().getForecastList()) {
			OffsetDateTime updated = forecast.getProperties().getUpdated();
			OffsetDateTime generatedAt = forecast.getProperties().getGeneratedAt();
			OffsetDateTime updateTime = forecast.getProperties().getUpdateTime();

			System.out.println("updated: " + Duration.between(now, updated.atZoneSameInstant(myZoneId)));
			System.out.println("generatedAt: " + Duration.between(now, generatedAt.atZoneSameInstant(myZoneId)));
			System.out.println("updateTime: " + Duration.between(now, updateTime.atZoneSameInstant(myZoneId)));

			for (Period period : forecast.getProperties().getPeriods()) {
				OffsetDateTime startTime = period.getStartTime();
				Duration startDuration = Duration.between(now, startTime.atZoneSameInstant(myZoneId));
				String startDurationString = String.format("%d:%02d", startDuration.toHours(), startDuration.toMinutes());
				System.out.println("Period startTime duration: " + startDurationString);

				OffsetDateTime endTime = period.getEndTime();
			}
		}

	}

	private void timeTest() {
		String str = "2022-05-11T21:00:00-06:00";

		OffsetDateTime updatedTime = OffsetDateTime.parse(str);
		System.out.println(updatedTime.atZoneSameInstant(ZoneId.of("America/Denver")));

		Set<String> allZoneIds = ZoneId.getAvailableZoneIds();
		System.out.println(allZoneIds);

	}

//	HttpURLConnection con = (HttpURLConnection) pointUrl.openConnection();
//	con.setRequestMethod("GET");
//	con.setRequestProperty("User-Agent", "nathansmithpiano@gmail.com");
//	
////	con.connect();
//	
//	int responseCode = con.getResponseCode();
//	System.out.println("RESPONSE CODE:\n" + responseCode);
//	
//	
//	
//	if (responseCode == 200) {
////		Scanner scan = new Scanner(pointUrl.openStream());
//		BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
//		
//		String inputLine;
//		StringBuffer response = new StringBuffer();
//		
//		while ((inputLine = in.readLine()) != null) {
//			response.append(inputLine);
//		}
//		
//		in.close();
//		
//		System.out.println("RESPONSE:\n" + response.toString());
//	}

//	BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
//	String inputLine;
//	StringBuffer response = new StringBuffer();
//	while ((inputLine = in.readLine()) != null) {
//		response.append(inputLine);
//	}
//	in.close();
//	
//	System.out.println("RESPONSE:\n" + response.toString());
//	
//	JSONObject myReponse = new JSONObject(response.toString());

}
