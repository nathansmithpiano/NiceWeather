### Weather.gov API: Point
##### https://api.weather.gov/points/39.11771,-106.445335
##### 5/13/22
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
    "id": "https://api.weather.gov/points/39.1177,-106.4453",
    "type": "Feature",
    "geometry": {
        "type": "Point",
        "coordinates": [
            -106.4453,
            39.117699999999999
        ]
    },
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
