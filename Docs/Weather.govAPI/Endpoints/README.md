[Up](../README.md) | [Next](point.md)
<hr>

## Weather.gov API Endpoints:
#### [Point](point.md)
>- **GET /points/{point}** - Returns metadata about a given latitude/longitude point
#### [Forecast Office](forecastOffice.md)
>- **GET /offices/{officeId}** - Returns metadata about a NWS forecast office
#### [Forecast](forecast.md)
>- **GET /gridpoints/{wfo}/{x},{y}/forecast** - Returns a textual forecast for a 2.5km grid area
#### [Forecast Hourly](forecastHourly.md)
>- **GET /gridpoints/{wfo}/{x},{y}/forecast/hourly** - Returns a textual hourly forecast for a 2.5km grid area
#### [Forecast Grid Data](forecastGridData.md)
>- **GET /gridpoints/{wfo}/{x},{y}** - Returns raw numerical forecast data for a 2.5km grid area
#### [Observation Station](observationStation.md)
>- **GET /stations/{stationId}** - Returns metadata about a given observation station
#### [Observation Stations (used only by Point)](observationStations.md)
>- **GET /gridpoints/{wfo}/{x},{y}/stations** - Returns a list of observation stations usable for a given 2.5km grid area
#### [Forecast Zone](forecastZone.md)
>- **GET /zones/{type}/{zoneId}** - Returns metadata about a given zone
#### [County](county.md)
>- **GET /zones/{type}/{zoneId}** - Returns metadata about a given zone
#### [Fire Weather Zone](fireWeatherZone.md)
>- **GET /zones/{type}/{zoneId}** - Returns metadata about a given zone

<hr>

## Unused Endpoints:
Weather.gov API includes many more endpoints than are used in NiceWeather.
#### Alerts
>- **GET /alerts** - Returns all alerts
>- **GET /alerts/active** - Returns all currently active alerts
>- **GET /alerts/active/count** - Returns info on the number of active alerts
>- **GET /alerts/active/zone/{zoneId}** - Returns active alerts for the given NWS public zone or county
>- **GET /alerts/active/area/{area}** - Returns active alerts for the given area (state or marine area)
>- **GET /alerts/active/region/{region}** - Returns active alerts for the given marine region
>- **GET /alerts/types** - Returns a list of alert types
>- **GET /alerts/{id}** - Returns a specific alert
#### Glossary
>- **GET /glossary** - Returns glossary terms
#### Stations
>- **GET /stations/{stationId}/observations** - Returns a list of observations for a given station
>- **GET /stations/{stationId}/observations/latest** - Returns the latest observation for a station
>- **GET /stations/{stationId}/observations/{time}** - Returns a single observation.
>- **GET /stations** - Returns a list of observation stations.

#### Offices
>- **GET /offices/{officeId}/headlines/{headlineId}** - Returns a specific news headline for a given NWS office
>- **GET /offices/{officeId}/headlines** - Returns a list of news headlines for a given NWS office

#### Radar
>- **GET /radar/servers** - Returns a list of radar servers
>- **GET /radar/servers/{id}** - Returns metadata about a given radar server
>- **GET /radar/stations** - Returns a list of radar stations
>- **GET /radar/stations/{stationId}** - Returns metadata about a given radar station
>- **GET /radar/stations/{stationId}/alarms** - Returns metadata about a given radar station alarms
>- **GET /radar/queues/{host}** - Returns metadata about a given radar queue
>- **GET /radar/profilers/{stationId}** - Returns metadata about a given radar wind profiler

#### Products
>- **GET /products** - Returns a list of text products
>- **GET /products/locations** - Returns a list of valid text product issuance locations
>- **GET /products/types** - Returns a list of valid text product types and codes
>- **GET /products/{productId}** - Returns a specific text product
>- **GET /products/types/{typeId}** - Returns a list of text products of a given type
>- **GET /products/types/{typeId}/locations** - Returns a list of valid text product issuance locations for a given product type
>- **GET /products/locations/{locationId}/types** - Returns a list of valid text product types for a given issuance location
>- **GET /products/types/{typeId}/locations/{locationId}** - Returns a list of text products of a given type for a given issuance location
>- **GET /zones** - Returns a list of zones
>- **GET /zones/{type}** - Returns a list of zones of a given type
>- **GET /zones/{type}/{zoneId}/forecast** - Returns the current zone forecast for a given zone
>- **GET /zones/forecast/{zoneId}/observations** - Returns a list of observations for a given zone
>- **GET /zones/forecast/{zoneId}/stations** - Returns a list of observation stations for a given zone

<hr>

## From [Weather.gov's Documentation](https://www.weather.gov/documentation/services-web-api):
>### Formats
>Endpoints typically have a GeoJSON default format, given the inclusion of geometry data. See the Specification tab for details on each endpoint. Below are common formats available by the API.  
> - GeoJSON: application/geo+json
> - JSON-LD: application/ld+json
> - DWML: application/vnd.noaa.dwml+xml
> - OXML: application/vnd.noaa.obs+xml
> - CAP: application/cap+xml
> - ATOM: application/atom+xml  
>
> Accept: application/cap+xml
