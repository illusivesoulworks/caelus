# Caelus API

Caelus is a small utility mod that abstracts the hardcoded vanilla elytra behavior into a more generic elytra flight attribute and exposes this to mod developers seeking to implement elytra flight for their own mods.

All modders have to do is call `CaelusApi.getInstance().getFallFlyingAttribute()` wherever they want to apply a modifier to the flight attribute. The attribute has a default value of 0, which forbids any elytra flight. Attribute values greater than or equal to 1 will enable elytra flight. The vanilla elytra item has also been changed to use this attribute, keeping its behavior the same as normal.

## Adding to Your Project:

Add the following to your build.gradle file:
```
repositories {
    maven {
        name = 'C4's Maven'
        url = "https://maven.theillusivec4.top/"
    }
}
```

### Forge
```
dependencies {
    runtimeOnly "com.illusivesoulworks.caelus:caelus-forge:${version}"
    compileOnly "com.illusivesoulworks.caelus:caelus-forge:${version}:api"
}
```

### NeoForge
```
dependencies {
    runtimeOnly "com.illusivesoulworks.caelus:caelus-neoforge:${version}"
    compileOnly "com.illusivesoulworks.caelus:caelus-neoforge:${version}:api"
}
```

Replace ${version} with the version of Caelus that you want to use.

# Downloads

**CurseForge**

- [![](http://cf.way2muchnoise.eu/short_caelus_downloads%20on%20Forge.svg)](https://www.curseforge.com/minecraft/mc-mods/caelus/files) [![](http://cf.way2muchnoise.eu/versions/caelus.svg)](https://www.curseforge.com/minecraft/mc-mods/caelus)
- [![](http://cf.way2muchnoise.eu/short_caelus-fabric_downloads%20on%20Fabric.svg)](https://www.curseforge.com/minecraft/mc-mods/caelus-fabric/files) [![](http://cf.way2muchnoise.eu/versions/caelus-fabric.svg)](https://www.curseforge.com/minecraft/mc-mods/caelus-fabric)

## Support

Please report all bugs, issues, and feature requests to the [issue tracker](https://github.com/TheIllusiveC4/Caelus/issues).

For non-technical support and questions, join the developer's [Discord](https://discord.gg/JWgrdwt).

## License

All source code and assets are licensed under LGPL-3.0-or-later.

## Donations

Donations to the developer can be sent through [Ko-fi](https://ko-fi.com/C0C1NL4O).

## Affiliates

[![BisectHosting](https://i.ibb.co/1G4QPdc/bh-illusive.png)](https://bisecthosting.com/illusive)
