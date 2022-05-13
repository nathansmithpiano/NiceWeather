NiceWeather is a tool that allows users to search and get recommendations on where to go based on weather.
JSON Data is requested from the National Weather Service's free Weather.gov API.

Endpoint: https://api.weather.gov/points/39.11771,-106.445335
Using GPS coordinates (latitude, longitude), Weather.gov API returns:

##### @context: Schema details for a Point based on GeoJSON
```
"city": "s:addressLocality",
"state": "s:addressRegion",
"distance": {
    "@id": "s:Distance",
    "@type": "s:QuantitativeValue"
} ...
```

##### Details particular to a Point
```
"id": "https://api.weather.gov/points/39.1177,-106.4453",
"type": "Feature",
"geometry": {
    "type": "Point",
    "coordinates": [
        -106.4453,
        39.117699999999999
    ]
}
```

##### Properties for a Point
```
"properties": {
        "@id": "https://api.weather.gov/points/39.1177,-106.4453",
        "@type": "wx:Point",
        "cwa": "PUB",
        "forecastOffice": "https://api.weather.gov/offices/PUB",
        "gridId": "PUB",
        "gridX": 33,
        "gridY": 107,
        "forecast": "https://api.weather.gov/gridpoints/PUB/33,107/forecast",
        "forecastHourly": "https://api.weather.gov/gridpoints/PUB/33,107/forecast/hourly",
        "forecastGridData": "https://api.weather.gov/gridpoints/PUB/33,107",
        "observationStations": "https://api.weather.gov/gridpoints/PUB/33,107/stations",
        "relativeLocation": {
            "type": "Feature",
            "geometry": {
                "type": "Point",
                "coordinates": [
                    -106.318985,
                    39.103709000000002
                ]
            },
            "properties": {
                "city": "Twin Lakes",
                "state": "CO",
                "distance": {
                    "unitCode": "wmoUnit:m",
                    "value": 11008.865990169001
                },
                "bearing": {
                    "unitCode": "wmoUnit:degree_(angle)",
                    "value": 278
                }
            }
        },
        "forecastZone": "https://api.weather.gov/zones/forecast/COZ060",
        "county": "https://api.weather.gov/zones/county/COC065",
        "fireWeatherZone": "https://api.weather.gov/zones/fire/COZ220",
        "timeZone": "America/Denver",
        "radarStation": "KGJX"
    }
}
```

##### This ** Point ** contains several URL's for other data:
- ["@id": "https://api.weather.gov/points/39.1177,-106.4453"](Docs/Weather.gov API/point.md)
- ["forecastOffice": "https://api.weather.gov/offices/PUB"](Docs/Weather.gov API forecastOffice.md)
- ["forecast": "https://api.weather.gov/gridpoints/PUB/33,107/forecast"](Docs/Weather.gov API/forecast.md)
- ["forecastHourly": "https://api.weather.gov/gridpoints/PUB/33,107/forecast/hourly"](Docs/Weather.gov API/forecastHourly)
- ["forecastGridData": "https://api.weather.gov/gridpoints/PUB/33,107"](Docs/Weather.gov API/forecastGridData)
- ["observationStations": "https://api.weather.gov/gridpoints/PUB/33,107/stations"](Docs/Weather.gov API/observationStations)
- ["forecastZone": "https://api.weather.gov/zones/forecast/COZ060"](Docs/Weather.gov API/forecastZone)
- ["county": "https://api.weather.gov/zones/county/COC065"](Docs/Weather.gov API/county)
- ["fireWeatherZone": "https://api.weather.gov/zones/fire/COZ220"](Docs/Weather.gov API/fireWeatherZone)
