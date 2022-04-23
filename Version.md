## Light modules' version management in release

All modules must have 'light.modules.version' property
Example:
```xml
<properties>
    <light.modules.version>1.0</light.modules.version>
</properties>
```

Whenever release new version (Ex. version 1.0), it is necessary to execute commands:

- Set version to all modules: `mvn versions:set -DnewVersion=1.0`
- Set property for `light.modules.version`: `mvn versions:set-property -Dproperty=light.modules.version -DnewVersion=1.0`

Guide from [Versions Maven Plugin][version-mvn-plugin]

[version-mvn-plugin]: https://www.mojohaus.org/versions-maven-plugin/index.html