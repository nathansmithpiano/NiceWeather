[Up](../README.md) | [Next](point.md)
<hr>

## Weather.gov API Endpoints:

- [Point](point.md)
- [Forecast Office](forecastOffice.md)
- [Forecast](forecast.md)
- [Forecast Hourly](forecastHourly.md)
- [Forecast Grid Data](forecastGridData.md)
- [Observation Station](observationStation.md)
- [Observation Stations (used only by Point)](observationStations.md)
- [Forecast Zone](forecastZone.md)
- [County](county.md)
- [Fire Weather Zone](fireWeatherZone.md)

### From [Weather.gov's Documentation](https://www.weather.gov/documentation/services-web-api):
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

### All endpoints
Weather.gov API includes many more endpoints than are used in NiceWeather.

### Unused Endpoints:
#### Alerts
>- *GET /alerts* - Returns all alerts
>- *GET /alerts/active* - Returns all currently active alerts
>- *GET /alerts/active/count* - Returns info on the number of active alerts
>- *GET /alerts/active/zone/{zoneId}* - Returns active alerts for the given NWS public zone or county
>- *GET /alerts/active/area/{area}* - Returns active alerts for the given area (state or marine area)
>- *GET /alerts/active/region/{region}* - Returns active alerts for the given marine region
>- *GET /alerts/types* - Returns a list of alert types
>- *GET /alerts/{id}* - Returns a specific alert
#### Glossary
>- *GET /glossary* - Returns glossary terms
