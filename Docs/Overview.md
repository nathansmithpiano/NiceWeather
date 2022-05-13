NiceWeather is a tool that allows users to search and get recommendations on where to go based on weather.
Data is imported using the National Weather Service's free Weather.gov API.

Endpoint: https://api.weather.gov/points/39.11771,-106.445335
Using GPS coordinates (latitude, longitude), Weather.gov API returns:

@context: schema details based on GeoJSON
Example:
```
"city": "s:addressLocality",
"state": "s:addressRegion",
"distance": {
    "@id": "s:Distance",
    "@type": "s:QuantitativeValue"
} ...
```
