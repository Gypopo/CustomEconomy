package me.gypopo.economy.hook;

import me.gypopo.economy.CustomEconomy;
import me.gypopo.economyshopgui.api.events.EconomyPreLoadEvent;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

public class EconomyProvider implements Listener {

    private final ESGUIEcoHook customEconomy;

    public EconomyProvider(CustomEconomy plugin) {
        this.customEconomy = new ESGUIEcoHook(plugin);
    }

    public ESGUIEcoHook getCustomEconomy() {
        return this.customEconomy;
    }

    @EventHandler
    public void onEconomyPreLoadEvent(EconomyPreLoadEvent e) {
        e.registerExternal(this.customEconomy);
    }
}