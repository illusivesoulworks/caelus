# Caelus API [![](http://cf.way2muchnoise.eu/versions/caelus.svg)](https://www.curseforge.com/minecraft/mc-mods/caelus) [![](http://cf.way2muchnoise.eu/short_caelus_downloads.svg)](https://www.curseforge.com/minecraft/mc-mods/caelus/files) [![License: LGPL v3](https://img.shields.io/badge/License-LGPL%20v3-blue.svg?&style=flat-square)](https://www.gnu.org/licenses/lgpl-3.0) [![](https://img.shields.io/discord/500852157503766538.svg?color=green&label=Discord&style=flat-square)](https://discord.gg/JWgrdwt)

## Overview

Caelus is a small utility mod that abstracts the hardcoded vanilla elytra behavior into a more generic elytra flight attribute and exposes this to mod developers seeking to implement elytra flight for their own mods.

All modders have to do is call the CaelusApi.ELYTRA_FLIGHT attribute where applicable. The attribute has a default value of 0, which forbids any elytra flight. Attribute values greater than or equal to 1 will enable elytra flight. The vanilla elytra item has also been changed to use this attribute, keeping its behavior the same as normal.

## Adding to Your Project:

Add the following to your build.gradle file:
```
repositories {
    maven {
        url = "https://maven.theillusivec4.top"
    }
}

dependencies {
    runtimeOnly fg.deobf("top.theillusivec4.caelus:caelus-forge:${version}")
    compileOnly fg.deobf("top.theillusivec4.caelus:caelus-forge:${version}:api")
}
```

 Replace ${version} with the version of Caelus that you want to use.
 
## Developing

Help! I'm getting Mixin crashes when I try to launch in development on Forge!

Caelus API uses Mixins to implement its core features. This may cause issues when depending on Caelus API for your project inside a development environment since ForgeGradle/MixinGradle do not yet support this natively like on the Fabric toolchain. As a workaround, please disable the refmaps in development by setting the mixin.env.disableRefMap JVM argument to true in your run configuration.
