## onboarding modules' version management in release

All modules must have 'onboarding.modules.version' property
Example:
```xml
<properties>
    <onboarding.modules.version>1.0</onboarding.modules.version>
</properties>
```

Whenever release new version (Ex. version 1.0), it is necessary to execute commands:

- Set version to all modules: `mvn versions:set -DnewVersion=1.0.1`
- Set property for `onboarding.modules.version`: `mvn versions:set-property -Dproperty=onboarding.modules.version -DnewVersion=1.0.1`

Guide from [Versions Maven Plugin][version-mvn-plugin]

[version-mvn-plugin]: https://www.mojohaus.org/versions-maven-plugin/index.html