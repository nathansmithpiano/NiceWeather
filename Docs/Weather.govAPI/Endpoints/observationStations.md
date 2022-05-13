[Up](README.md) | [Back](observationStation.md) | [Next](forecastZone.md)
<hr>

### Weather.gov API: Observation Stations
- **Example:** Mt. Elbert
- **Updated:** 5/13/22
- **Endpoint:** https://api.weather.gov/gridpoints/PUB/33,107/stations
- **Endpoint Referenced By:**
    - **Point:** properties.observationStations
- **COLLECTION**
    - features[] = each observationStation JSON (without @context)
    - observationStations[] - forward endpoint for each observationStation
<hr>

#### Forward Endpoints in JSON
```
"observationStations": [
    "https://api.weather.gov/stations/KLXV",
    ...
]
```
<hr>

#### JSON

####
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
            },
            "observationStations": {
                "@container": "@list",
                "@type": "@id"
            }
        }
    ],
    "type": "FeatureCollection",
    "features": [
        {
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
        },
        {
            "id": "https://api.weather.gov/stations/KASE",
            "type": "Feature",
            "geometry": {
                "type": "Point",
                "coordinates": [
                    -106.87051,
                    39.22992
                ]
            },
            "properties": {
                "@id": "https://api.weather.gov/stations/KASE",
                "@type": "wx:ObservationStation",
                "elevation": {
                    "unitCode": "wmoUnit:m",
                    "value": 2338.7303999999999
                },
                "stationIdentifier": "KASE",
                "name": "Aspen-Pitkin County Airport",
                "timeZone": "America/Denver",
                "forecast": "https://api.weather.gov/zones/forecast/COZ010",
                "county": "https://api.weather.gov/zones/county/COC097",
                "fireWeatherZone": "https://api.weather.gov/zones/fire/COZ205"
            }
        },
        {
            "id": "https://api.weather.gov/stations/KAEJ",
            "type": "Feature",
            "geometry": {
                "type": "Point",
                "coordinates": [
                    -106.12069,
                    38.814160000000001
                ]
            },
            "properties": {
                "@id": "https://api.weather.gov/stations/KAEJ",
                "@type": "wx:ObservationStation",
                "elevation": {
                    "unitCode": "wmoUnit:m",
                    "value": 2421.9407999999999
                },
                "stationIdentifier": "KAEJ",
                "name": "Central Colorado Regional Airport",
                "timeZone": "America/Denver",
                "forecast": "https://api.weather.gov/zones/forecast/COZ062",
                "county": "https://api.weather.gov/zones/county/COC015",
                "fireWeatherZone": "https://api.weather.gov/zones/fire/COZ220"
            }
        },
        {
            "id": "https://api.weather.gov/stations/KCCU",
            "type": "Feature",
            "geometry": {
                "type": "Point",
                "coordinates": [
                    -106.15228,
                    39.475230000000003
                ]
            },
            "properties": {
                "@id": "https://api.weather.gov/stations/KCCU",
                "@type": "wx:ObservationStation",
                "elevation": {
                    "unitCode": "wmoUnit:m",
                    "value": 3666.1343999999999
                },
                "stationIdentifier": "KCCU",
                "name": "Copper Mountain - Red Cliff Pass",
                "timeZone": "America/Denver",
                "forecast": "https://api.weather.gov/zones/forecast/COZ034",
                "county": "https://api.weather.gov/zones/county/COC117",
                "fireWeatherZone": "https://api.weather.gov/zones/fire/COZ212"
            }
        },
        {
            "id": "https://api.weather.gov/stations/KMYP",
            "type": "Feature",
            "geometry": {
                "type": "Point",
                "coordinates": [
                    -106.3197,
                    38.497199999999999
                ]
            },
            "properties": {
                "@id": "https://api.weather.gov/stations/KMYP",
                "@type": "wx:ObservationStation",
                "elevation": {
                    "unitCode": "wmoUnit:m",
                    "value": 3624.9863999999998
                },
                "stationIdentifier": "KMYP",
                "name": "Salida - Monarch Pass",
                "timeZone": "America/Denver",
                "forecast": "https://api.weather.gov/zones/forecast/COZ060",
                "county": "https://api.weather.gov/zones/county/COC015",
                "fireWeatherZone": "https://api.weather.gov/zones/fire/COZ220"
            }
        },
        {
            "id": "https://api.weather.gov/stations/KEGE",
            "type": "Feature",
            "geometry": {
                "type": "Point",
                "coordinates": [
                    -106.91667,
                    39.649999999999999
                ]
            },
            "properties": {
                "@id": "https://api.weather.gov/stations/KEGE",
                "@type": "wx:ObservationStation",
                "elevation": {
                    "unitCode": "wmoUnit:m",
                    "value": 1993.0871999999999
                },
                "stationIdentifier": "KEGE",
                "name": "Eagle County Regional",
                "timeZone": "America/Denver",
                "forecast": "https://api.weather.gov/zones/forecast/COZ008",
                "county": "https://api.weather.gov/zones/county/COC037",
                "fireWeatherZone": "https://api.weather.gov/zones/fire/COZ205"
            }
        },
        {
            "id": "https://api.weather.gov/stations/KGUC",
            "type": "Feature",
            "geometry": {
                "type": "Point",
                "coordinates": [
                    -106.93333,
                    38.533329999999999
                ]
            },
            "properties": {
                "@id": "https://api.weather.gov/stations/KGUC",
                "@type": "wx:ObservationStation",
                "elevation": {
                    "unitCode": "wmoUnit:m",
                    "value": 2339.9495999999999
                },
                "stationIdentifier": "KGUC",
                "name": "Gunnison, Gunnison-Crested Butte Regional Airport",
                "timeZone": "America/Denver",
                "forecast": "https://api.weather.gov/zones/forecast/COZ014",
                "county": "https://api.weather.gov/zones/county/COC051",
                "fireWeatherZone": "https://api.weather.gov/zones/fire/COZ293"
            }
        },
        {
            "id": "https://api.weather.gov/stations/K20V",
            "type": "Feature",
            "geometry": {
                "type": "Point",
                "coordinates": [
                    -106.3688899,
                    40.053609999999999
                ]
            },
            "properties": {
                "@id": "https://api.weather.gov/stations/K20V",
                "@type": "wx:ObservationStation",
                "elevation": {
                    "unitCode": "wmoUnit:m",
                    "value": 2258.8728000000001
                },
                "stationIdentifier": "K20V",
                "name": "Kremmling, Mc Elroy Airfield",
                "timeZone": "America/Denver",
                "forecast": "https://api.weather.gov/zones/forecast/COZ032",
                "county": "https://api.weather.gov/zones/county/COC049",
                "fireWeatherZone": "https://api.weather.gov/zones/fire/COZ213"
            }
        },
        {
            "id": "https://api.weather.gov/stations/K04V",
            "type": "Feature",
            "geometry": {
                "type": "Point",
                "coordinates": [
                    -106.16861,
                    38.09722
                ]
            },
            "properties": {
                "@id": "https://api.weather.gov/stations/K04V",
                "@type": "wx:ObservationStation",
                "elevation": {
                    "unitCode": "wmoUnit:m",
                    "value": 2385.0599999999999
                },
                "stationIdentifier": "K04V",
                "name": "Saguache, Saguache Municipal Airport",
                "timeZone": "America/Denver",
                "forecast": "https://api.weather.gov/zones/forecast/COZ069",
                "county": "https://api.weather.gov/zones/county/COC109",
                "fireWeatherZone": "https://api.weather.gov/zones/fire/COZ224"
            }
        },
        {
            "id": "https://api.weather.gov/stations/KAFF",
            "type": "Feature",
            "geometry": {
                "type": "Point",
                "coordinates": [
                    -104.81667,
                    38.966670000000001
                ]
            },
            "properties": {
                "@id": "https://api.weather.gov/stations/KAFF",
                "@type": "wx:ObservationStation",
                "elevation": {
                    "unitCode": "wmoUnit:m",
                    "value": 2003.1456000000001
                },
                "stationIdentifier": "KAFF",
                "name": "Air Force Academy",
                "timeZone": "America/Denver",
                "forecast": "https://api.weather.gov/zones/forecast/COZ085",
                "county": "https://api.weather.gov/zones/county/COC041",
                "fireWeatherZone": "https://api.weather.gov/zones/fire/COZ226"
            }
        },
        {
            "id": "https://api.weather.gov/stations/KBJC",
            "type": "Feature",
            "geometry": {
                "type": "Point",
                "coordinates": [
                    -105.10417,
                    39.900849999999998
                ]
            },
            "properties": {
                "@id": "https://api.weather.gov/stations/KBJC",
                "@type": "wx:ObservationStation",
                "elevation": {
                    "unitCode": "wmoUnit:m",
                    "value": 1691.9448
                },
                "stationIdentifier": "KBJC",
                "name": "Broomfield / Jeffco",
                "timeZone": "America/Denver",
                "forecast": "https://api.weather.gov/zones/forecast/COZ039",
                "county": "https://api.weather.gov/zones/county/COC059",
                "fireWeatherZone": "https://api.weather.gov/zones/fire/COZ239"
            }
        },
        {
            "id": "https://api.weather.gov/stations/KAPA",
            "type": "Feature",
            "geometry": {
                "type": "Point",
                "coordinates": [
                    -104.84841,
                    39.559910000000002
                ]
            },
            "properties": {
                "@id": "https://api.weather.gov/stations/KAPA",
                "@type": "wx:ObservationStation",
                "elevation": {
                    "unitCode": "wmoUnit:m",
                    "value": 1788.8712
                },
                "stationIdentifier": "KAPA",
                "name": "Denver - Centennial Airport",
                "timeZone": "America/Denver",
                "forecast": "https://api.weather.gov/zones/forecast/COZ040",
                "county": "https://api.weather.gov/zones/county/COC035",
                "fireWeatherZone": "https://api.weather.gov/zones/fire/COZ240"
            }
        },
        {
            "id": "https://api.weather.gov/stations/KFCS",
            "type": "Feature",
            "geometry": {
                "type": "Point",
                "coordinates": [
                    -104.75977,
                    38.683120000000002
                ]
            },
            "properties": {
                "@id": "https://api.weather.gov/stations/KFCS",
                "@type": "wx:ObservationStation",
                "elevation": {
                    "unitCode": "wmoUnit:m",
                    "value": 1780.6415999999999
                },
                "stationIdentifier": "KFCS",
                "name": "Butts Army Airfield (Fort Carson)",
                "timeZone": "America/Denver",
                "forecast": "https://api.weather.gov/zones/forecast/COZ085",
                "county": "https://api.weather.gov/zones/county/COC041",
                "fireWeatherZone": "https://api.weather.gov/zones/fire/COZ227"
            }
        },
        {
            "id": "https://api.weather.gov/stations/KCOS",
            "type": "Feature",
            "geometry": {
                "type": "Point",
                "coordinates": [
                    -104.68873000000001,
                    38.809489900000003
                ]
            },
            "properties": {
                "@id": "https://api.weather.gov/stations/KCOS",
                "@type": "wx:ObservationStation",
                "elevation": {
                    "unitCode": "wmoUnit:m",
                    "value": 1885.4928
                },
                "stationIdentifier": "KCOS",
                "name": "City of Colorado Springs Municipal Airport",
                "timeZone": "America/Denver",
                "forecast": "https://api.weather.gov/zones/forecast/COZ085",
                "county": "https://api.weather.gov/zones/county/COC041",
                "fireWeatherZone": "https://api.weather.gov/zones/fire/COZ227"
            }
        },
        {
            "id": "https://api.weather.gov/stations/KMNH",
            "type": "Feature",
            "geometry": {
                "type": "Point",
                "coordinates": [
                    -104.6422599,
                    39.223170000000003
                ]
            },
            "properties": {
                "@id": "https://api.weather.gov/stations/KMNH",
                "@type": "wx:ObservationStation",
                "elevation": {
                    "unitCode": "wmoUnit:m",
                    "value": 2146.4016000000001
                },
                "stationIdentifier": "KMNH",
                "name": "Elbert Mountain - Monument Pass",
                "timeZone": "America/Denver",
                "forecast": "https://api.weather.gov/zones/forecast/COZ041",
                "county": "https://api.weather.gov/zones/county/COC039",
                "fireWeatherZone": "https://api.weather.gov/zones/fire/COZ241"
            }
        },
        {
            "id": "https://api.weather.gov/stations/KBKF",
            "type": "Feature",
            "geometry": {
                "type": "Point",
                "coordinates": [
                    -104.75806,
                    39.71331
                ]
            },
            "properties": {
                "@id": "https://api.weather.gov/stations/KBKF",
                "@type": "wx:ObservationStation",
                "elevation": {
                    "unitCode": "wmoUnit:m",
                    "value": 1699.8696
                },
                "stationIdentifier": "KBKF",
                "name": "Buckley Air Force Base Airport",
                "timeZone": "America/Denver",
                "forecast": "https://api.weather.gov/zones/forecast/COZ040",
                "county": "https://api.weather.gov/zones/county/COC005",
                "fireWeatherZone": "https://api.weather.gov/zones/fire/COZ240"
            }
        },
        {
            "id": "https://api.weather.gov/stations/KDEN",
            "type": "Feature",
            "geometry": {
                "type": "Point",
                "coordinates": [
                    -104.65622,
                    39.846580000000003
                ]
            },
            "properties": {
                "@id": "https://api.weather.gov/stations/KDEN",
                "@type": "wx:ObservationStation",
                "elevation": {
                    "unitCode": "wmoUnit:m",
                    "value": 1647.1392000000001
                },
                "stationIdentifier": "KDEN",
                "name": "Denver, Denver International Airport",
                "timeZone": "America/Denver",
                "forecast": "https://api.weather.gov/zones/forecast/COZ040",
                "county": "https://api.weather.gov/zones/county/COC031",
                "fireWeatherZone": "https://api.weather.gov/zones/fire/COZ240"
            }
        },
        {
            "id": "https://api.weather.gov/stations/KCPW",
            "type": "Feature",
            "geometry": {
                "type": "Point",
                "coordinates": [
                    -106.80028,
                    37.451390000000004
                ]
            },
            "properties": {
                "@id": "https://api.weather.gov/stations/KCPW",
                "@type": "wx:ObservationStation",
                "elevation": {
                    "unitCode": "wmoUnit:m",
                    "value": 3584.1432
                },
                "stationIdentifier": "KCPW",
                "name": "Pagosa Springs, Wolf Creek Pass",
                "timeZone": "America/Denver",
                "forecast": "https://api.weather.gov/zones/forecast/COZ068",
                "county": "https://api.weather.gov/zones/county/COC079",
                "fireWeatherZone": "https://api.weather.gov/zones/fire/COZ223"
            }
        },
        {
            "id": "https://api.weather.gov/stations/KPUB",
            "type": "Feature",
            "geometry": {
                "type": "Point",
                "coordinates": [
                    -104.5057,
                    38.288690000000003
                ]
            },
            "properties": {
                "@id": "https://api.weather.gov/stations/KPUB",
                "@type": "wx:ObservationStation",
                "elevation": {
                    "unitCode": "wmoUnit:m",
                    "value": 1424.0255999999999
                },
                "stationIdentifier": "KPUB",
                "name": "Pueblo Memorial Airport",
                "timeZone": "America/Denver",
                "forecast": "https://api.weather.gov/zones/forecast/COZ086",
                "county": "https://api.weather.gov/zones/county/COC101",
                "fireWeatherZone": "https://api.weather.gov/zones/fire/COZ228"
            }
        },
        {
            "id": "https://api.weather.gov/stations/KALS",
            "type": "Feature",
            "geometry": {
                "type": "Point",
                "coordinates": [
                    -105.8618,
                    37.439329999999998
                ]
            },
            "properties": {
                "@id": "https://api.weather.gov/stations/KALS",
                "@type": "wx:ObservationStation",
                "elevation": {
                    "unitCode": "wmoUnit:m",
                    "value": 2296.9728
                },
                "stationIdentifier": "KALS",
                "name": "San Luis Valley Regional Airport",
                "timeZone": "America/Denver",
                "forecast": "https://api.weather.gov/zones/forecast/COZ070",
                "county": "https://api.weather.gov/zones/county/COC003",
                "fireWeatherZone": "https://api.weather.gov/zones/fire/COZ224"
            }
        },
        {
            "id": "https://api.weather.gov/stations/KVTP",
            "type": "Feature",
            "geometry": {
                "type": "Point",
                "coordinates": [
                    -105.16694,
                    37.501109999999997
                ]
            },
            "properties": {
                "@id": "https://api.weather.gov/stations/KVTP",
                "@type": "wx:ObservationStation",
                "elevation": {
                    "unitCode": "wmoUnit:m",
                    "value": 3114.1415999999999
                },
                "stationIdentifier": "KVTP",
                "name": "La Veta Mountain, La Veta Pass",
                "timeZone": "America/Denver",
                "forecast": "https://api.weather.gov/zones/forecast/COZ074",
                "county": "https://api.weather.gov/zones/county/COC055",
                "fireWeatherZone": "https://api.weather.gov/zones/fire/COZ225"
            }
        },
        {
            "id": "https://api.weather.gov/stations/KLIC",
            "type": "Feature",
            "geometry": {
                "type": "Point",
                "coordinates": [
                    -103.66737999999999,
                    39.273339999999997
                ]
            },
            "properties": {
                "@id": "https://api.weather.gov/stations/KLIC",
                "@type": "wx:ObservationStation",
                "elevation": {
                    "unitCode": "wmoUnit:m",
                    "value": 1633.1184000000001
                },
                "stationIdentifier": "KLIC",
                "name": "Limon Municipal Airport",
                "timeZone": "America/Denver",
                "forecast": "https://api.weather.gov/zones/forecast/COZ046",
                "county": "https://api.weather.gov/zones/county/COC073",
                "fireWeatherZone": "https://api.weather.gov/zones/fire/COZ246"
            }
        },
        {
            "id": "https://api.weather.gov/stations/KTAD",
            "type": "Feature",
            "geometry": {
                "type": "Point",
                "coordinates": [
                    -104.33184,
                    37.262180000000001
                ]
            },
            "properties": {
                "@id": "https://api.weather.gov/stations/KTAD",
                "@type": "wx:ObservationStation",
                "elevation": {
                    "unitCode": "wmoUnit:m",
                    "value": 1748.9423999999999
                },
                "stationIdentifier": "KTAD",
                "name": "Perry Stokes Airport",
                "timeZone": "America/Denver",
                "forecast": "https://api.weather.gov/zones/forecast/COZ088",
                "county": "https://api.weather.gov/zones/county/COC071",
                "fireWeatherZone": "https://api.weather.gov/zones/fire/COZ230"
            }
        },
        {
            "id": "https://api.weather.gov/stations/KLHX",
            "type": "Feature",
            "geometry": {
                "type": "Point",
                "coordinates": [
                    -103.51334,
                    38.049489999999999
                ]
            },
            "properties": {
                "@id": "https://api.weather.gov/stations/KLHX",
                "@type": "wx:ObservationStation",
                "elevation": {
                    "unitCode": "wmoUnit:m",
                    "value": 1278.0264
                },
                "stationIdentifier": "KLHX",
                "name": "La Junta Municipal Airport",
                "timeZone": "America/Denver",
                "forecast": "https://api.weather.gov/zones/forecast/COZ093",
                "county": "https://api.weather.gov/zones/county/COC089",
                "fireWeatherZone": "https://api.weather.gov/zones/fire/COZ232"
            }
        },
        {
            "id": "https://api.weather.gov/stations/KSKX",
            "type": "Feature",
            "geometry": {
                "type": "Point",
                "coordinates": [
                    -105.66667,
                    36.450000000000003
                ]
            },
            "properties": {
                "@id": "https://api.weather.gov/stations/KSKX",
                "@type": "wx:ObservationStation",
                "elevation": {
                    "unitCode": "wmoUnit:m",
                    "value": 2161.0320000000002
                },
                "stationIdentifier": "KSKX",
                "name": "Taos, Taos Regional Airport",
                "timeZone": "America/Denver",
                "forecast": "https://api.weather.gov/zones/forecast/NMZ216",
                "county": "https://api.weather.gov/zones/county/NMC055",
                "fireWeatherZone": "https://api.weather.gov/zones/fire/NMZ102"
            }
        },
        {
            "id": "https://api.weather.gov/stations/KRTN",
            "type": "Feature",
            "geometry": {
                "type": "Point",
                "coordinates": [
                    -104.502183,
                    36.741528000000002
                ]
            },
            "properties": {
                "@id": "https://api.weather.gov/stations/KRTN",
                "@type": "wx:ObservationStation",
                "elevation": {
                    "unitCode": "wmoUnit:m",
                    "value": 1936.6992
                },
                "stationIdentifier": "KRTN",
                "name": "Raton Municipal - Crews Field Airport",
                "timeZone": "America/Denver",
                "forecast": "https://api.weather.gov/zones/forecast/NMZ228",
                "county": "https://api.weather.gov/zones/county/NMC007",
                "fireWeatherZone": "https://api.weather.gov/zones/fire/NMZ103"
            }
        },
        {
            "id": "https://api.weather.gov/stations/KLAA",
            "type": "Feature",
            "geometry": {
                "type": "Point",
                "coordinates": [
                    -102.68745,
                    38.071779900000003
                ]
            },
            "properties": {
                "@id": "https://api.weather.gov/stations/KLAA",
                "@type": "wx:ObservationStation",
                "elevation": {
                    "unitCode": "wmoUnit:m",
                    "value": 1122.8832
                },
                "stationIdentifier": "KLAA",
                "name": "Lamar Municipal Airport",
                "timeZone": "America/Denver",
                "forecast": "https://api.weather.gov/zones/forecast/COZ098",
                "county": "https://api.weather.gov/zones/county/COC099",
                "fireWeatherZone": "https://api.weather.gov/zones/fire/COZ236"
            }
        },
        {
            "id": "https://api.weather.gov/stations/KITR",
            "type": "Feature",
            "geometry": {
                "type": "Point",
                "coordinates": [
                    -102.2818999,
                    39.241489999999999
                ]
            },
            "properties": {
                "@id": "https://api.weather.gov/stations/KITR",
                "@type": "wx:ObservationStation",
                "elevation": {
                    "unitCode": "wmoUnit:m",
                    "value": 1278.0264
                },
                "stationIdentifier": "KITR",
                "name": "Burlington - Carson County Airport",
                "timeZone": "America/Denver",
                "forecast": "https://api.weather.gov/zones/forecast/COZ091",
                "county": "https://api.weather.gov/zones/county/COC063",
                "fireWeatherZone": "https://api.weather.gov/zones/fire/COZ253"
            }
        },
        {
            "id": "https://api.weather.gov/stations/KSPD",
            "type": "Feature",
            "geometry": {
                "type": "Point",
                "coordinates": [
                    -102.61667,
                    37.283329999999999
                ]
            },
            "properties": {
                "@id": "https://api.weather.gov/stations/KSPD",
                "@type": "wx:ObservationStation",
                "elevation": {
                    "unitCode": "wmoUnit:m",
                    "value": 1335.0239999999999
                },
                "stationIdentifier": "KSPD",
                "name": "Springfield, Comanche National Grassland",
                "timeZone": "America/Denver",
                "forecast": "https://api.weather.gov/zones/forecast/COZ099",
                "county": "https://api.weather.gov/zones/county/COC009",
                "fireWeatherZone": "https://api.weather.gov/zones/fire/COZ237"
            }
        },
        {
            "id": "https://api.weather.gov/stations/KCAO",
            "type": "Feature",
            "geometry": {
                "type": "Point",
                "coordinates": [
                    -103.15367000000001,
                    36.448340000000002
                ]
            },
            "properties": {
                "@id": "https://api.weather.gov/stations/KCAO",
                "@type": "wx:ObservationStation",
                "elevation": {
                    "unitCode": "wmoUnit:m",
                    "value": 1515.1608000000001
                },
                "stationIdentifier": "KCAO",
                "name": "Clayton Municipal Airpark",
                "timeZone": "America/Denver",
                "forecast": "https://api.weather.gov/zones/forecast/NMZ230",
                "county": "https://api.weather.gov/zones/county/NMC059",
                "fireWeatherZone": "https://api.weather.gov/zones/fire/NMZ104"
            }
        },
        {
            "id": "https://api.weather.gov/stations/KEHA",
            "type": "Feature",
            "geometry": {
                "type": "Point",
                "coordinates": [
                    -101.88333,
                    37
                ]
            },
            "properties": {
                "@id": "https://api.weather.gov/stations/KEHA",
                "@type": "wx:ObservationStation",
                "elevation": {
                    "unitCode": "wmoUnit:m",
                    "value": 1103.9856
                },
                "stationIdentifier": "KEHA",
                "name": "Elkhart, Elkhart-Morton County Airport",
                "timeZone": "America/Chicago",
                "forecast": "https://api.weather.gov/zones/forecast/KSZ084",
                "county": "https://api.weather.gov/zones/county/KSC129",
                "fireWeatherZone": "https://api.weather.gov/zones/fire/KSZ084"
            }
        }
    ],
    "observationStations": [
        "https://api.weather.gov/stations/KLXV",
        "https://api.weather.gov/stations/KASE",
        "https://api.weather.gov/stations/KAEJ",
        "https://api.weather.gov/stations/KCCU",
        "https://api.weather.gov/stations/KMYP",
        "https://api.weather.gov/stations/KEGE",
        "https://api.weather.gov/stations/KGUC",
        "https://api.weather.gov/stations/K20V",
        "https://api.weather.gov/stations/K04V",
        "https://api.weather.gov/stations/KAFF",
        "https://api.weather.gov/stations/KBJC",
        "https://api.weather.gov/stations/KAPA",
        "https://api.weather.gov/stations/KFCS",
        "https://api.weather.gov/stations/KCOS",
        "https://api.weather.gov/stations/KMNH",
        "https://api.weather.gov/stations/KBKF",
        "https://api.weather.gov/stations/KDEN",
        "https://api.weather.gov/stations/KCPW",
        "https://api.weather.gov/stations/KPUB",
        "https://api.weather.gov/stations/KALS",
        "https://api.weather.gov/stations/KVTP",
        "https://api.weather.gov/stations/KLIC",
        "https://api.weather.gov/stations/KTAD",
        "https://api.weather.gov/stations/KLHX",
        "https://api.weather.gov/stations/KSKX",
        "https://api.weather.gov/stations/KRTN",
        "https://api.weather.gov/stations/KLAA",
        "https://api.weather.gov/stations/KITR",
        "https://api.weather.gov/stations/KSPD",
        "https://api.weather.gov/stations/KCAO",
        "https://api.weather.gov/stations/KEHA"
    ]
}
```
