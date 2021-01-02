# Changelog
All notable changes to this project will be documented in this file.

The format is based on [Keep a Changelog](http://keepachangelog.com/en/1.0.0/) and this project does not adhere to [Semantic Versioning](http://semver.org/spec/v2.0.0.html).
This project uses MCVERSION-MAJORMOD.MAJORAPI.MINOR.PATCH.

## [2.0-beta4] - 2021.01.01
### Fixed
- Fixed elytra flight triggers [#14](https://github.com/TheIllusiveC4/Caelus/issues/14)

## [2.0-beta3] - 2020.06.11
### Fixed
- Fixed cape and elytra mixed rendering [#5](https://github.com/TheIllusiveC4/Caelus/issues/5)

## [2.0-beta2] - 2020.04.18
### Changed
- Simple Takeoff can trigger fireworks mid-flight
- Removed restriction on Elytra trigger to match 1.15 Elytra changes [#4](https://github.com/TheIllusiveC4/Caelus/issues/4)

## [2.0-beta1] - 2020.02.11
### Added
- [API] CaelusAPI#getElytraRender to retrieve the current render state of the elytra on the entity
- [API] Added IMC processing for elytra render state, identifier is "elytraRender"/CaelusAPI.IMC.ELYTRA_RENDER and it should supply a Function<LivingEntity,ElytraRender> object
### Changed
- Ported to 1.15.2 Forge
### Removed
- Removed RenderElytraEvent in favor of a simpler IMC method for processing render state