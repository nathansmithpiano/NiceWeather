[Up](../README.md) | [Next](Endpoints/README.md)
<hr>

## Weather.gov API

### [Endpoints](Endpoints/README.md)

From [Weather.gov's Documentation](https://www.weather.gov/documentation/services-web-api):
>### Overview
>The National Weather Service (NWS) API allows developers access to critical forecasts, alerts, and observations, along with other weather data. The API was designed with a cache-friendly approach that expires content based upon the information life cycle. The API is based upon of JSON-LD to promote machine data discovery.
>
>The API is located at: https://api.weather.gov
>
>Operational issues should be reported to nco.ops@noaa.gov.
>
>General use questions can be asked on the [API github site](https://weather-gov.github.io/api/).
>
>### Pricing
>All of the information presented via the API is intended to be open data, free to use for any purpose. As a public service of the United States Government, we do not charge any fees for the usage of this service, although there are reasonable rate limits in place to prevent abuse and help ensure that everyone has access. The rate limit is not public information, but allows a generous amount for typical use. If the rate limit is execeed a request will return with an error, and may be retried after the limit clears (typically within 5 seconds). Proxies are more likely to reach the limit, whereas requests directly from clients are not likely.
>
>###Content Negotiation
>The new API will use headers to modify the version and format of the response. Every request, either by browser or application, sends header information every time you visit any website. For example, a commonly used header called "UserAgent" tells a website what type of device you are using so it can tailor the best experience for you. No private information is shared in a header, and this is a standard practice for all government and private sites. Developers can override these headers for specific purposes (see the "API Specifications" tab for more information). You can get full details by visiting the header field definitions page at the [World Wide Web Consortium](https://www.w3.org/Protocols/rfc2616/rfc2616-sec14.html) site.
>
>###Authentication
>A User Agent is required to identify your application. This string can be anything, and the more unique to your application the less likely it will be affected by a security event. If you include contact information (website or email), we can contact you if your string is associated to a security event. This will be replaced with an API key in the future.
>
>```User-Agent: (myweatherapp.com, contact@myweatherapp.com)```
