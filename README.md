<h1 align="center">Quinque-API 👋</h1>
<p>
  <img alt="Version" src="https://img.shields.io/badge/version-1.0.0-blue.svg?cacheSeconds=2592000" />
  <a href="#" target="_blank">
    <img alt="License: MIT" src="https://img.shields.io/badge/License-MIT-yellow.svg" />
  </a>
</p>

> An API for a 5 letter guessing word game, that legally-speaking could have been inspired by anything at all

## Author

👤 **Jamie Buckley**

* Website: https://jamiebuckley.dev/
* Github: [@jamiebuckley](https://github.com/jamiebuckley)
* LinkedIn: [@jamie-buckley-95a447a9](https://linkedin.com/in/jamie-buckley-95a447a9)

## Running Locally

Currently the docker-compose/docker set-up is geared towards a Mac M1.

Knock the `"arm64v8/"` off the beginning of the `FROM arm64v8/eclipse-temurin:17-jdk-focal`
to run it on a different system.

Then, to build and run locally
```
./gradlew build
docker-compose up
./gradlew flywayMigrate
```

The API docs can then be browsed [here](http://localhost:8080/api/swagger-ui/index.html)

## Show your support

Give a ⭐️ if this project helped you!

***
_This README was generated with ❤️ by [readme-md-generator](https://github.com/kefranabg/readme-md-generator)_