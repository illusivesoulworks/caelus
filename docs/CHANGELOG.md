# Changelog
All notable changes to this project will be documented in this file.

The format is based on [Keep a Changelog](http://keepachangelog.com/en/1.0.0/) and this project does not adhere to [Semantic Versioning](http://semver.org/spec/v2.0.0.html).
This project uses MCVERSION-MAJORMOD.MAJORAPI.MINOR.PATCH.

## [0.11] - 2019.08.05
### Changed
- Updated to Forge version 28.0.45 to accommodate for a breaking change

## [0.10] - 2019.07.24
### Changed
- Updated to 1.14.4 Forge

## [0.9] - 2019.07.08
### Fixed
- Workaround fix for client-side desync of elytra flight attribute on dimension change

## [0.8] - 2019.07.08
### Fixed
- Fixed class-loading client server-side

## [0.7] - 2019.07.07
### Changed
- [API] Refactored RenderElytraEvent to avoid removing layers on mod load, changed Result returns to boolean returns [#1](https://github.com/TheIllusiveC4/Caelus/issues/1)

## [0.6] - 2019.07.04
### Changed
- Updated to 1.14.3 Forge
- Updated disable icon texture
- [API] Replaced RenderCapeCheckEvent with RenderElytraEvent that encompasses elytra/cape rendering and enchantment glow

## [0.5] - 2019.06.15
### Added
- Added deobf jar

### Changed
- Updated to last Forge and mappings for 1.13.2

## [0.4](https://github.com/TheIllusiveC4/Caelus/compare/cc44c517e2b5617b1a931471cade368eafc8f860...master) - 2019.03.29
### Fixed
- Fixed broken elytras still giving flight

## [0.3] - 2019.03.19
### Fixed
- Fixed coremod transformations in obfuscated environments

## [0.2] - 2019.03.17
### Added
- [API] Added RenderCapeCheckEvent

## [0.1] - 2019.03.13
Initial beta release