# Changelog
All notable changes to this project will be documented in this file.

The format is based on [Keep a Changelog](http://keepachangelog.com/en/1.0.0/) and this project adheres to [Semantic Versioning](http://semver.org/spec/v2.0.0.html).
Prior to version 3.1.0, this project used [Forge Recommended Versioning](https://mcforge.readthedocs.io/en/latest/conventions/versioning/).

## [6.0.0+1.20.6] - 2024.05.06
### Changed
- [API] Changed return of `CaelusApi#getFlightAttribute` to `Holder<Attribute>`
- Updated to Minecraft 1.20.6

## [5.1.0+1.20.4] - 2024.04.22
### Added
- Added `CaelusApi#canFallFly(LivingEntity)`
### Changed
- Refactored fall flying to account for three states instead of two for better mod compatibility
### Deprecated
- Deprecated `CaelusApi#canFly(LivingEntity)`

## [5.0.1+1.20.4] - 2024.03.04
### Changed
- Refactored networking [#39](https://github.com/TheIllusiveC4/Caelus/issues/39)
- [NeoForge] Requires NeoForge 20.4.167+

## [5.0.0+1.20.4] - 2023.12.19
### Added
- Added NeoForge version
### Changed
- Updated to Minecraft 1.20.4

## [4.0.0+1.20.2] - 2023.10.19
### Changed
- Updated to Minecraft 1.20.2

## [3.1.0+1.20] - 2023.06.11
### Changed
- Updated to Minecraft 1.20
- Changed to Semantic Versioning

## [1.19.4-3.0.0.10] - 2023.03.17
### Changed
- Updated to Minecraft 1.19.4
- Changed localization of attribute from "Elytra Flight" to "Fall Flying"

## [1.19.3-3.0.0.9] - 2023.03.16
### Fixed
- Fixed Italian (it_it) localization

## [1.19.3-3.0.0.8] - 2023.03.16
### Added
- Added Italian (it_it) localization (thanks WVam!) [#34](https://github.com/TheIllusiveC4/Caelus/pull/34)

## [1.19.3-3.0.0.7] - 2022.12.08
### Added
- Added Portuguese (pt_br) localization (thanks FITFC!) [#33](https://github.com/TheIllusiveC4/Caelus/pull/33)
### Changed
- Updated to Minecraft 1.19.3
- Updated to Forge 44+

## [1.19.2-3.0.0.6] - 2022.08.11
### Fixed
- Fixed display URL leading to the wrong mod page

## [1.19.2-3.0.0.5] - 2022.08.10
### Changed
- Updated to Minecraft 1.19.2
- Updated to Forge 43+

## [1.19.1-3.0.0.4] - 2022.07.29
### Changed
- Updated to Minecraft 1.19.1
- Updated to Forge 42+

## [1.19-3.0.0.3] - 2022.06.07
### Changed
- Updated to Minecraft 1.19
- Updated to Forge 41+

## [1.18.1-3.0.0.2] - 2022.02.16
### Fixed
- Fixed Caelus causing other modded elytras that do not depend on Caelus to stop functioning

## [1.18-3.0.0.1] - 2021.12.05
### Changed
- Updated to Minecraft 1.18
- Updated to Forge 38+
