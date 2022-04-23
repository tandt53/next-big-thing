1. runner: -> for network firewall
- local machine
- gitlab

2. automation target: for selenium (appium not much meaning )
- local
- remote

3. development environment -> for data query
- uat
- qc
- staging 

=> create profile to manage properties


load config:
- Manager:
    - using ServiceLoader to get WebManager, MobileManager to load capability
- WebManager, MobileManager: ~ service
    - FileConfig
        - properties/xml
        - get properties config.web, config.mobile to select file to load
        - if not, get properties "runner" and "env" to find suitable properties files: web-runner-env.properties
    - CliConfig
    - RuntimeConfig
