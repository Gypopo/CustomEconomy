package me.gypopo.economy.hook;

import me.gypopo.economy.CustomEconomy;
import me.gypopo.economyshopgui.api.objects.ExternalEconomy;
import org.bukkit.OfflinePlayer;

import java.math.BigDecimal;

public class ESGUIEcoHook extends ExternalEconomy {

    private final CustomEconomy economy;

    public ESGUIEcoHook(CustomEconomy economy) {
        this.economy = economy;
    }

    @Override
    public double getBalance(OfflinePlayer p) {
        return this.economy.getBalance(p.getUniqueId()).doubleValue();
    }

    @Override
    public void depositBalance(OfflinePlayer p, double amount) {
        this.economy.deposit(p.getUniqueId(), BigDecimal.valueOf(amount));
    }

    @Override
    public void withdrawBalance(OfflinePlayer p, double amount) {
        this.economy.take(p.getUniqueId(), BigDecimal.valueOf(amount));
    }

    @Override
    public String getName() {
        return "CentjesEco";
    }

    @Override
    public String getPlural() {
        return "%price% §e§lCentjes";
    }

    @Override
    public String getSingular() {
        return "%price% §e§lCentje";
    }

    @Override
    public String getFriendly() {
        return "§e§lcentjes";
    }

    @Override
    public boolean isDecimal() {
        return true;
    }
}
