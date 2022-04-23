# Configuration 

## Configuration file 

Configuration of automation test project are placed in the proties file. 
That file has name that follow the template `automation-<runner>-<env>.properties`, where:
- `<runner>`: the machine that automated scripts will be executed, for example `local` or `remote`.
- `<env>`: the environment that the project will be executed, for example `dev`, or `qc` or `prod`.

This information was configured as `local` and `qc` in default active profile in pom.xml.
The properties file should be put in test resource directory (`src/test/resource`). It is ok if you put it in main resource directory, but it causes some problems when you want to package the project and share to others.

If you want to change configuration file, there are two options:
- Set the `runner` and `env` in an expected profile in the `pom.xml` file. Then select it by adding `-P <profile>` to the `mvn test` command.
- Specify directly file name in the `-DconfigFile` option.

## Content of configuration file 

### Prefix 
Every property must have prefixed with `light.` to determine the property is for automation test using Next-Big-Thing framework.

### Selenium
Every property for Selenium will add `light.selenium` for setting selenium webdriver. 

### Appium
Every property for Appium will add `light.appium` for setting appium driver. 

### Device farm 
