[Up](README.md) | [Back](point.md) | [Next](forecast.md)
<hr>

### Weather.gov API: Forecast Office
- **Example:** Mt. Elbert
- **Updated:** 5/13/22
- **Endpoint:**https://api.weather.gov/offices/PUB
- **Endpoint Referenced By:**
    - id
    - properties.@id
    - **Point:** properties.forecastOffice
    - **Forecast Office:** parentOrganization (different Forecast Office)
    - **Forecast Grid Data:** properties.forecastOffice
    - **Forecast Zone:** properties.forecastOffices[]
    - **Fire Weather Zone:** properties.forecastOffices[]
<hr>

#### Forward Endpoints in JSON
```
"sameAs": "https://www.weather.gov/pub",
"parentOrganization": "https://api.weather.gov/offices/CRH",
"responsibleCounties": [
    "https://api.weather.gov/zones/county/COC003",
    ...
],
"responsibleForecastZones": [
    "https://api.weather.gov/zones/forecast/COZ058",
    ...
],
"responsibleFireZones": [
    "https://api.weather.gov/zones/fire/COZ220",
    ...
],
"approvedObservationStations": [
    "https://api.weather.gov/stations/K04V",
    ...
]
```
<hr>

#### JSON
```
{
    "@context": {
        "@version": "1.1",
        "@vocab": "https://schema.org/"
    },
    "@type": "GovernmentOrganization",
    "@id": "https://api.weather.gov/offices/PUB",
    "id": "PUB",
    "name": "Pueblo, CO",
    "address": {
        "@type": "PostalAddress",
        "streetAddress": "3 Eaton Way",
        "addressLocality": "Pueblo",
        "addressRegion": "CO",
        "postalCode": "81001-4856"
    },
    "telephone": "(719) 948-9429",
    "faxNumber": "(719) 948-9729",
    "email": "nws.pueblo@noaa.gov",
    "sameAs": "https://www.weather.gov/pub",
    "nwsRegion": "cr",
    "parentOrganization": "https://api.weather.gov/offices/CRH",
    "responsibleCounties": [
        "https://api.weather.gov/zones/county/COC003",
        "https://api.weather.gov/zones/county/COC009",
        "https://api.weather.gov/zones/county/COC011",
        "https://api.weather.gov/zones/county/COC015",
        "https://api.weather.gov/zones/county/COC021",
        "https://api.weather.gov/zones/county/COC023",
        "https://api.weather.gov/zones/county/COC025",
        "https://api.weather.gov/zones/county/COC027",
        "https://api.weather.gov/zones/county/COC041",
        "https://api.weather.gov/zones/county/COC043",
        "https://api.weather.gov/zones/county/COC055",
        "https://api.weather.gov/zones/county/COC061",
        "https://api.weather.gov/zones/county/COC065",
        "https://api.weather.gov/zones/county/COC071",
        "https://api.weather.gov/zones/county/COC079",
        "https://api.weather.gov/zones/county/COC089",
        "https://api.weather.gov/zones/county/COC099",
        "https://api.weather.gov/zones/county/COC101",
        "https://api.weather.gov/zones/county/COC105",
        "https://api.weather.gov/zones/county/COC109",
        "https://api.weather.gov/zones/county/COC119"
    ],
    "responsibleForecastZones": [
        "https://api.weather.gov/zones/forecast/COZ058",
        "https://api.weather.gov/zones/forecast/COZ059",
        "https://api.weather.gov/zones/forecast/COZ060",
        "https://api.weather.gov/zones/forecast/COZ061",
        "https://api.weather.gov/zones/forecast/COZ062",
        "https://api.weather.gov/zones/forecast/COZ063",
        "https://api.weather.gov/zones/forecast/COZ064",
        "https://api.weather.gov/zones/forecast/COZ065",
        "https://api.weather.gov/zones/forecast/COZ066",
        "https://api.weather.gov/zones/forecast/COZ067",
        "https://api.weather.gov/zones/forecast/COZ068",
        "https://api.weather.gov/zones/forecast/COZ069",
        "https://api.weather.gov/zones/forecast/COZ070",
        "https://api.weather.gov/zones/forecast/COZ071",
        "https://api.weather.gov/zones/forecast/COZ072",
        "https://api.weather.gov/zones/forecast/COZ073",
        "https://api.weather.gov/zones/forecast/COZ074",
        "https://api.weather.gov/zones/forecast/COZ075",
        "https://api.weather.gov/zones/forecast/COZ076",
        "https://api.weather.gov/zones/forecast/COZ077",
        "https://api.weather.gov/zones/forecast/COZ078",
        "https://api.weather.gov/zones/forecast/COZ079",
        "https://api.weather.gov/zones/forecast/COZ080",
        "https://api.weather.gov/zones/forecast/COZ081",
        "https://api.weather.gov/zones/forecast/COZ082",
        "https://api.weather.gov/zones/forecast/COZ083",
        "https://api.weather.gov/zones/forecast/COZ084",
        "https://api.weather.gov/zones/forecast/COZ085",
        "https://api.weather.gov/zones/forecast/COZ086",
        "https://api.weather.gov/zones/forecast/COZ087",
        "https://api.weather.gov/zones/forecast/COZ088",
        "https://api.weather.gov/zones/forecast/COZ089",
        "https://api.weather.gov/zones/forecast/COZ093",
        "https://api.weather.gov/zones/forecast/COZ094",
        "https://api.weather.gov/zones/forecast/COZ095",
        "https://api.weather.gov/zones/forecast/COZ096",
        "https://api.weather.gov/zones/forecast/COZ097",
        "https://api.weather.gov/zones/forecast/COZ098",
        "https://api.weather.gov/zones/forecast/COZ099"
    ],
    "responsibleFireZones": [
        "https://api.weather.gov/zones/fire/COZ220",
        "https://api.weather.gov/zones/fire/COZ221",
        "https://api.weather.gov/zones/fire/COZ222",
        "https://api.weather.gov/zones/fire/COZ223",
        "https://api.weather.gov/zones/fire/COZ224",
        "https://api.weather.gov/zones/fire/COZ225",
        "https://api.weather.gov/zones/fire/COZ226",
        "https://api.weather.gov/zones/fire/COZ227",
        "https://api.weather.gov/zones/fire/COZ228",
        "https://api.weather.gov/zones/fire/COZ229",
        "https://api.weather.gov/zones/fire/COZ230",
        "https://api.weather.gov/zones/fire/COZ231",
        "https://api.weather.gov/zones/fire/COZ232",
        "https://api.weather.gov/zones/fire/COZ233",
        "https://api.weather.gov/zones/fire/COZ234",
        "https://api.weather.gov/zones/fire/COZ235",
        "https://api.weather.gov/zones/fire/COZ236",
        "https://api.weather.gov/zones/fire/COZ237"
    ],
    "approvedObservationStations": [
        "https://api.weather.gov/stations/K04V",
        "https://api.weather.gov/stations/K20V",
        "https://api.weather.gov/stations/KAEJ",
        "https://api.weather.gov/stations/KAFF",
        "https://api.weather.gov/stations/KALS",
        "https://api.weather.gov/stations/KAPA",
        "https://api.weather.gov/stations/KASE",
        "https://api.weather.gov/stations/KBJC",
        "https://api.weather.gov/stations/KBKF",
        "https://api.weather.gov/stations/KCAO",
        "https://api.weather.gov/stations/KCCU",
        "https://api.weather.gov/stations/KCOS",
        "https://api.weather.gov/stations/KCPW",
        "https://api.weather.gov/stations/KDEN",
        "https://api.weather.gov/stations/KEGE",
        "https://api.weather.gov/stations/KEHA",
        "https://api.weather.gov/stations/KFCS",
        "https://api.weather.gov/stations/KGUC",
        "https://api.weather.gov/stations/KITR",
        "https://api.weather.gov/stations/KLAA",
        "https://api.weather.gov/stations/KLHX",
        "https://api.weather.gov/stations/KLIC",
        "https://api.weather.gov/stations/KLXV",
        "https://api.weather.gov/stations/KMNH",
        "https://api.weather.gov/stations/KMYP",
        "https://api.weather.gov/stations/KPUB",
        "https://api.weather.gov/stations/KRTN",
        "https://api.weather.gov/stations/KSKX",
        "https://api.weather.gov/stations/KSPD",
        "https://api.weather.gov/stations/KTAD",
        "https://api.weather.gov/stations/KVTP"
    ]
}
```
