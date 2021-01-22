# Changelog
All notable changes to this project will be documented in this file.

The format is based on [Keep a Changelog](http://keepachangelog.com/en/1.0.0/) and this project does not adhere to [Semantic Versioning](http://semver.org/spec/v2.0.0.html).
This project uses MCVERSION-MAJORMOD.MAJORAPI.MINOR.PATCH.

## [1.16.5-2.1.2.0] - 2021.01.21
### Added
- Added color overrides to RenderElytraEvent

## [1.16.5-2.1.1.0] - 2021.01.21
### Added
- Added taggable elytra implementations with "forge:elytra"

## [1.16.4-2.1.0.2] - 2021.01.06
### Fixed
- Fixed enchantment glint not being applied to the render layer

## [1.16.4-2.1.0.1] - 2021.01.06
### Fixed
- Fixed incorrect mixin injection

## [1.16.4-2.1.0.0] - 2020.11.16
### Changed
- Updated to 1.16.4

## [1.16.3-2.0.0.3] - 2020.09.29
### Fixed
- Fixed FML injection crash [#11](https://github.com/TheIllusiveC4/Caelus/issues/11)

## [1.16.3-2.0.0.2] - 2020.09.27
### Fixed
- Fixed jar build

## [1.16.3-2.0.0.1] - 2020.09.27
### Changed
- Updated to 1.16.3

## [1.16.2-2.0.0.0] - 2020.08.14
### Changed
- Updated to 1.16.2

## [2.0-beta7] - 2020.08.07
### Fixed
- Fixed dedicated server crash [#8](https://github.com/TheIllusiveC4/Caelus/issues/8)

## [2.0-beta6] - 2020.08.06
### Changed
- Updated to Forge 32.0.101+
- Javascript coremods replaced with mixins

## [2.0-beta5] - 2020.07.14
### Fixed
- Fixed NoClassDefFoundError

## [2.0-beta4] - 2020.06.11
### Added
- [API] Added RenderElytraEvent for rendering non-vanilla elytras, including enchantment glow and custom textures
### Changed
- Ported to 1.16.1 Forge
### Removed
- Removed elytra rendering IMC in favor of bringing back RenderElytraEvent
- Removed all advanced elytra controls (these will return in a separate mod TBA)