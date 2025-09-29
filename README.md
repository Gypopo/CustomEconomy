A in memory economy provider which directly hooks into EconomyShopGUI using its API.

This repository is simply a example of how to use the API of EconomyShopGUI to register a external economy provider.

Make sure to add EconomyShopGUI/EconomyShopGUI-Premium as a load before in your plugin.yml or the plugin might not be able to register the EconomyPreLoadEvent, this is important!

When registering the external economy provider using the EconomyPreLoadEvent, it will be available in EconomyShopGUI to use as `economy-provider: EXTERNAL:CentjesEco` inside the config.yml or using `economy: EXTERNAL:CentjesEco` inside section/shop configs
