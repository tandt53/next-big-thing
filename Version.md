## TANDT modules' version management in release

All modules must have 'tandt.modules.version' property
Example:
```xml
<properties>
    <tandt.modules.version>1.0</tandt.modules.version>
</properties>
```

Whenever release new version (Ex. version 1.0), it is necessary to execute commands:

- Set version to all modules: `mvn versions:set -DnewVersion=1.0`
- Set property for `tandt.modules.version`: `mvn versions:set-property -Dproperty=tandt.modules.version -DnewVersion=1.0`

Guide from [Versions Maven Plugin][version-mvn-plugin]

[version-mvn-plugin]: https://www.mojohaus.org/versions-maven-plugin/index.html