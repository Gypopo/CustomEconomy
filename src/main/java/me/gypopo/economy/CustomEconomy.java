package me.gypopo.economy;

import me.gypopo.economy.command.EcoCommand;
import me.gypopo.economy.hook.ESGUIEcoHook;
import me.gypopo.economyshopgui.api.events.EconomyPreLoadEvent;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandMap;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public final class CustomEconomy extends JavaPlugin implements Listener {

    private final Map<UUID, BigDecimal> balances = new HashMap<>();

    public final ESGUIEcoHook customEconomy = new ESGUIEcoHook(this);

    @Override
    public void onEnable() {
        Bukkit.getServer().getPluginManager().registerEvents(this, this);
        try {
            Field bukkitCommandMap = Bukkit.getServer().getClass().getDeclaredField("commandMap");
            bukkitCommandMap.setAccessible(true);
            CommandMap commandMap = (CommandMap) bukkitCommandMap.get(Bukkit.getServer());

            commandMap.register("customeconomy", new EcoCommand(this));
        } catch (NoSuchFieldException | IllegalAccessException e) {
            Bukkit.getServer().getConsoleSender().sendMessage("Exception occurred registering commands");
            e.printStackTrace();
        }
    }

    @Override
    public void onDisable() {
        this.balances.clear();
    }

    @EventHandler
    public void onEconomyPreLoadEvent(EconomyPreLoadEvent e) {
        e.registerExternal(this.customEconomy);
    }

    public BigDecimal getBalance(UUID uuid) {
        return this.balances.getOrDefault(uuid, BigDecimal.ZERO);
    }

    public void deposit(UUID uuid, BigDecimal amount) {
        this.balances.merge(uuid, amount, BigDecimal::add);
    }

    public void take(UUID uuid, BigDecimal amount) {
        this.balances.merge(uuid, amount, BigDecimal::subtract);
    }

    public void set(UUID uuid, BigDecimal amount) {
        this.balances.put(uuid, amount);
    }
}