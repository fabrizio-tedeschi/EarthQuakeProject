# EarthQuakeProject 

## About

An application that shows earthquakes data taken from a REST API.

## Classes

### Model
* `Metadata`: contains general data about responses from `earthquake.usgs` API
* `Geometry`: represents geographic coordinates as latitude, longitude and altitude
* `Earthquake`: represents earthquake data (as magnitude, place, etc...)

### Rest
* `RequestMaker`: a father class that create instance of `OKHttpClient` and `ObjectMapper` in order to get data from a
generic Http request and storing them into a `JsonNode` object and into classes
* `GeometryRequestMaker`: returns a `Geometry` object with coordinates of a given place using `www.here.com` API
* `EarthquakeRequestMaker`: makes http requests to `earthquake.usgs.gov` API and returns a `List<Earthquake>` with filtered features by given arguments


### Controller
* `Overview controller`: manages button's actions, scenes, alerts. It also takes user's input data.

## How it works

Every action (associated to buttons or fields) on the user interface is managed by an `OverviewController` method.

The `OverviewController` creates an instance of `EarthquakeRequestMaker` and calls the `getByParams` method obtaining data as
`List<Earthquake>`. 

## Running the application

### Setting API key

1. Go to the [HERE API website](https://developer.here.com/) and generate a FREE geocode API key;
2. Create a `.env` file into THIS directory and add the following line:

```
API_KEY=yourAPIkey
```
### Running

Open the project folder into a java IDE, compile and start the project. We recommend to use [IntelliJ IDEA](https://www.jetbrains.com/idea/).

## Developers
**Vincenzo Parente**
> Model classes, User Interface, UI Controller

**Fabrizio Tedeschi**
> Rest classes, Data management, Http Requests, UI Controller
