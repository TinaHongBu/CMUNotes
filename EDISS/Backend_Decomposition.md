# Components Decomposition

A sample project repo:

```
----build                           # Webpack config file
        ...
----src
--------assets                      # source file
--------components
------------GoodsInfo               
------------RiskPromt
------------ShareHeader
------------SharePanel
            utils.js                # Business unrelated, visualization helper functions
--------dal                         # Data Access Layer
            index.js                # Interface & Fake Data
            getInfoById.js          # Interface request
            getInfoById.json        # Return fake data
--------Main                        # Default entry point
            Main.html               # page template
            Main.js                 # business logic
            Main.less               # page layout
--------MainBanner                  # Banner page entry point
        app.js                      # Abstracted common methods
        common.js                   # Business logic helper functions
        common.less
    package.json
    README.md
```