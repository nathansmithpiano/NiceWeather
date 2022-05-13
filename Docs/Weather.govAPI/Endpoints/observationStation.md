[Up](README.md) | [Back](forecastGridData.md) | [Next](observationStations.md)

### Weather.gov API: Observation Station
- **Example:** Mt. Elbert
- **Updated:** 5/13/22
- **Endpoint:** https://api.weather.gov/stations/KLXV
- **Endpoint Referenced By:**
    - id
    - properties.@id
    - **Forecast Office:** approvedObservationStations[]
    - **Fire Weather Zone:** properties.observationStations[]
- **INCLUDED IN COLLECTIONS**
    - **Observation Stations:** features[] (shows all JSON for each station without @context)
<hr>

#### Forward Endpoints in JSON
```
"properties": {
    "forecast": "https://api.weather.gov/zones/forecast/COZ059",
    "county": "https://api.weather.gov/zones/county/COC065",
    "fireWeatherZone": "https://api.weather.gov/zones/fire/COZ220
}
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
            "s": "https://schema.org/",
            "geo": "http://www.opengis.net/ont/geosparql#",
            "unit": "http://codes.wmo.int/common/unit/",
            "@vocab": "https://api.weather.gov/ontology#",
            "geometry": {
                "@id": "s:GeoCoordinates",
                "@type": "geo:wktLiteral"
            },
            "city": "s:addressLocality",
            "state": "s:addressRegion",
            "distance": {
                "@id": "s:Distance",
                "@type": "s:QuantitativeValue"
            },
            "bearing": {
                "@type": "s:QuantitativeValue"
            },
            "value": {
                "@id": "s:value"
            },
            "unitCode": {
                "@id": "s:unitCode",
                "@type": "@id"
            },
            "forecastOffice": {
                "@type": "@id"
            },
            "forecastGridData": {
                "@type": "@id"
            },
            "publicZone": {
                "@type": "@id"
            },
            "county": {
                "@type": "@id"
            }
        }
    ],
    "id": "https://api.weather.gov/stations/KLXV",
    "type": "Feature",
    "geometry": {
        "type": "Point",
        "coordinates": [
            -106.31610999999999,
            39.228059999999999
        ]
    },
    "properties": {
        "@id": "https://api.weather.gov/stations/KLXV",
        "@type": "wx:ObservationStation",
        "elevation": {
            "unitCode": "wmoUnit:m",
            "value": 3026.0544
        },
        "stationIdentifier": "KLXV",
        "name": "Leadville, Lake County Airport",
        "timeZone": "America/Denver",
        "forecast": "https://api.weather.gov/zones/forecast/COZ059",
        "county": "https://api.weather.gov/zones/county/COC065",
        "fireWeatherZone": "https://api.weather.gov/zones/fire/COZ220"
    }
}
```
