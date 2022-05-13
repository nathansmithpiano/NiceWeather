[Up](README.md) | [Back](forecastOffice.md) | [Next](forecastHourly.md)
<hr>

### Weather.gov API: Forecast
- **Example:** Mt. Elbert
- **Updated:** 5/13/22
- **Endpoint:** https://api.weather.gov/gridpoints/PUB/33,107/forecast
- **Endpoint Referenced By:**
    - **Point:** properties.forecast
    - **Observation Station:** properties.forecast
<hr>

#### Forward Endpoints in JSON
```
(none)
```
<hr>

#### JSON
```
{
    "@context": [
        "https://geojson.org/geojson-ld/geojson-context.jsonld",
        {
            "@version": "1.1",
            "wx": "https://api.weather.gov/ontology#",
            "geo": "http://www.opengis.net/ont/geosparql#",
            "unit": "http://codes.wmo.int/common/unit/",
            "@vocab": "https://api.weather.gov/ontology#"
        }
    ],
    "type": "Feature",
    "geometry": {
        "type": "Polygon",
        "coordinates": [
            [
                [
                    -106.4610958,
                    39.117267400000003
                ],
                [
                    -106.4586896,
                    39.095231600000005
                ],
                [
                    -106.4302713,
                    39.097097800000007
                ],
                [
                    -106.43267160000001,
                    39.119133800000007
                ],
                [
                    -106.4610958,
                    39.117267400000003
                ]
            ]
        ]
    },
    "properties": {
        "updated": "2022-05-13T15:48:22+00:00",
        "units": "us",
        "forecastGenerator": "BaselineForecastGenerator",
        "generatedAt": "2022-05-13T17:34:51+00:00",
        "updateTime": "2022-05-13T15:48:22+00:00",
        "validTimes": "2022-05-13T09:00:00+00:00/P7DT16H",
        "elevation": {
            "unitCode": "wmoUnit:m",
            "value": 4042.8672000000001
        },
        "periods": [
            {
                "number": 1,
                "name": "Today",
                "startTime": "2022-05-13T11:00:00-06:00",
                "endTime": "2022-05-13T18:00:00-06:00",
                "isDaytime": true,
                "temperature": 36,
                "temperatureUnit": "F",
                "temperatureTrend": null,
                "windSpeed": "25 mph",
                "windDirection": "W",
                "icon": "https://api.weather.gov/icons/land/day/wind_few?size=medium",
                "shortForecast": "Sunny",
                "detailedForecast": "Sunny, with a high near 36. West wind around 25 mph, with gusts as high as 40 mph."
            },
            {
                "number": 2,
                "name": "Tonight",
                "startTime": "2022-05-13T18:00:00-06:00",
                "endTime": "2022-05-14T06:00:00-06:00",
                "isDaytime": false,
                "temperature": 19,
                "temperatureUnit": "F",
                "temperatureTrend": null,
                "windSpeed": "20 to 25 mph",
                "windDirection": "W",
                "icon": "https://api.weather.gov/icons/land/night/wind_few?size=medium",
                "shortForecast": "Mostly Clear",
                "detailedForecast": "Mostly clear, with a low around 19. West wind 20 to 25 mph."
            },
            {
                "number": 3,
                "name": "Saturday",
                "startTime": "2022-05-14T06:00:00-06:00",
                "endTime": "2022-05-14T18:00:00-06:00",
                "isDaytime": true,
                "temperature": 44,
                "temperatureUnit": "F",
                "temperatureTrend": null,
                "windSpeed": "25 mph",
                "windDirection": "W",
                "icon": "https://api.weather.gov/icons/land/day/wind_few?size=medium",
                "shortForecast": "Sunny",
                "detailedForecast": "Sunny, with a high near 44. West wind around 25 mph."
            },
            {
                "number": 4,
                "name": "Saturday Night",
                "startTime": "2022-05-14T18:00:00-06:00",
                "endTime": "2022-05-15T06:00:00-06:00",
                "isDaytime": false,
                "temperature": 27,
                "temperatureUnit": "F",
                "temperatureTrend": null,
                "windSpeed": "20 to 25 mph",
                "windDirection": "W",
                "icon": "https://api.weather.gov/icons/land/night/wind_few?size=medium",
                "shortForecast": "Mostly Clear",
                "detailedForecast": "Mostly clear, with a low around 27. West wind 20 to 25 mph."
            },
            {
                "number": 5,
                "name": "Sunday",
                "startTime": "2022-05-15T06:00:00-06:00",
                "endTime": "2022-05-15T18:00:00-06:00",
                "isDaytime": true,
                "temperature": 47,
                "temperatureUnit": "F",
                "temperatureTrend": null,
                "windSpeed": "20 mph",
                "windDirection": "WNW",
                "icon": "https://api.weather.gov/icons/land/day/few?size=medium",
                "shortForecast": "Sunny",
                "detailedForecast": "Sunny, with a high near 47. West northwest wind around 20 mph."
            },
            {
                "number": 6,
                "name": "Sunday Night",
                "startTime": "2022-05-15T18:00:00-06:00",
                "endTime": "2022-05-16T06:00:00-06:00",
                "isDaytime": false,
                "temperature": 29,
                "temperatureUnit": "F",
                "temperatureTrend": null,
                "windSpeed": "15 to 20 mph",
                "windDirection": "SSW",
                "icon": "https://api.weather.gov/icons/land/night/sct?size=medium",
                "shortForecast": "Partly Cloudy",
                "detailedForecast": "Partly cloudy, with a low around 29. South southwest wind 15 to 20 mph."
            },
            {
                "number": 7,
                "name": "Monday",
                "startTime": "2022-05-16T06:00:00-06:00",
                "endTime": "2022-05-16T18:00:00-06:00",
                "isDaytime": true,
                "temperature": 48,
                "temperatureUnit": "F",
                "temperatureTrend": null,
                "windSpeed": "15 to 20 mph",
                "windDirection": "WSW",
                "icon": "https://api.weather.gov/icons/land/day/bkn/snow,20?size=medium",
                "shortForecast": "Partly Sunny then Slight Chance Snow Showers",
                "detailedForecast": "A slight chance of snow showers after noon. Partly sunny, with a high near 48. West southwest wind 15 to 20 mph, with gusts as high as 35 mph. Chance of precipitation is 20%."
            },
            {
                "number": 8,
                "name": "Monday Night",
                "startTime": "2022-05-16T18:00:00-06:00",
                "endTime": "2022-05-17T06:00:00-06:00",
                "isDaytime": false,
                "temperature": 29,
                "temperatureUnit": "F",
                "temperatureTrend": null,
                "windSpeed": "20 mph",
                "windDirection": "W",
                "icon": "https://api.weather.gov/icons/land/night/sct?size=medium",
                "shortForecast": "Partly Cloudy",
                "detailedForecast": "Partly cloudy, with a low around 29. West wind around 20 mph."
            },
            {
                "number": 9,
                "name": "Tuesday",
                "startTime": "2022-05-17T06:00:00-06:00",
                "endTime": "2022-05-17T18:00:00-06:00",
                "isDaytime": true,
                "temperature": 47,
                "temperatureUnit": "F",
                "temperatureTrend": null,
                "windSpeed": "20 mph",
                "windDirection": "W",
                "icon": "https://api.weather.gov/icons/land/day/sct/snow,20?size=medium",
                "shortForecast": "Mostly Sunny then Slight Chance Snow Showers",
                "detailedForecast": "A slight chance of snow showers after noon. Mostly sunny, with a high near 47. West wind around 20 mph. Chance of precipitation is 20%."
            },
            {
                "number": 10,
                "name": "Tuesday Night",
                "startTime": "2022-05-17T18:00:00-06:00",
                "endTime": "2022-05-18T06:00:00-06:00",
                "isDaytime": false,
                "temperature": 29,
                "temperatureUnit": "F",
                "temperatureTrend": null,
                "windSpeed": "20 mph",
                "windDirection": "WNW",
                "icon": "https://api.weather.gov/icons/land/night/sct?size=medium",
                "shortForecast": "Partly Cloudy",
                "detailedForecast": "Partly cloudy, with a low around 29. West northwest wind around 20 mph."
            },
            {
                "number": 11,
                "name": "Wednesday",
                "startTime": "2022-05-18T06:00:00-06:00",
                "endTime": "2022-05-18T18:00:00-06:00",
                "isDaytime": true,
                "temperature": 47,
                "temperatureUnit": "F",
                "temperatureTrend": null,
                "windSpeed": "20 to 25 mph",
                "windDirection": "W",
                "icon": "https://api.weather.gov/icons/land/day/wind_sct/snow?size=medium",
                "shortForecast": "Mostly Sunny then Slight Chance Snow Showers",
                "detailedForecast": "A slight chance of snow showers and a slight chance of thunderstorms after noon. Mostly sunny, with a high near 47. West wind 20 to 25 mph, with gusts as high as 40 mph."
            },
            {
                "number": 12,
                "name": "Wednesday Night",
                "startTime": "2022-05-18T18:00:00-06:00",
                "endTime": "2022-05-19T06:00:00-06:00",
                "isDaytime": false,
                "temperature": 30,
                "temperatureUnit": "F",
                "temperatureTrend": null,
                "windSpeed": "20 to 25 mph",
                "windDirection": "WSW",
                "icon": "https://api.weather.gov/icons/land/night/wind_few?size=medium",
                "shortForecast": "Mostly Clear",
                "detailedForecast": "Mostly clear, with a low around 30. West southwest wind 20 to 25 mph."
            },
            {
                "number": 13,
                "name": "Thursday",
                "startTime": "2022-05-19T06:00:00-06:00",
                "endTime": "2022-05-19T18:00:00-06:00",
                "isDaytime": true,
                "temperature": 47,
                "temperatureUnit": "F",
                "temperatureTrend": null,
                "windSpeed": "20 to 25 mph",
                "windDirection": "WSW",
                "icon": "https://api.weather.gov/icons/land/day/wind_few?size=medium",
                "shortForecast": "Sunny",
                "detailedForecast": "Sunny, with a high near 47. West southwest wind 20 to 25 mph, with gusts as high as 40 mph."
            },
            {
                "number": 14,
                "name": "Thursday Night",
                "startTime": "2022-05-19T18:00:00-06:00",
                "endTime": "2022-05-20T06:00:00-06:00",
                "isDaytime": false,
                "temperature": 29,
                "temperatureUnit": "F",
                "temperatureTrend": null,
                "windSpeed": "25 mph",
                "windDirection": "WSW",
                "icon": "https://api.weather.gov/icons/land/night/wind_sct?size=medium",
                "shortForecast": "Partly Cloudy",
                "detailedForecast": "Partly cloudy, with a low around 29. West southwest wind around 25 mph, with gusts as high as 40 mph."
            }
        ]
    }
}
```
