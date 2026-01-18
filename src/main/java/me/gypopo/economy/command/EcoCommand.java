package me.gypopo.economy.command;

import me.gypopo.economy.CustomEconomy;
import me.gypopo.economyshopgui.api.objects.ExternalEconomy;
import me.gypopo.economyshopgui.providers.economys.ExternalEconomyProvider;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.command.defaults.BukkitCommand;
import org.bukkit.entity.Player;

import java.math.BigDecimal;
import java.util.Collections;

public class EcoCommand extends BukkitCommand {

    private final CustomEconomy plugin;

    public EcoCommand(CustomEconomy plugin) {
        super("customeconomy");
        this.description = "One command to rule your server's economy";
        this.setPermission("customeconomy.admin");
        this.usageMessage = "/customeconomy set <player> <amount>";
        this.setAliases(Collections.singletonList("eco"));

        this.plugin = plugin;
    }

    @Override
    public boolean execute(CommandSender commandSender, String s, String[] args) {
        if (commandSender instanceof Player sender) {
            if (args.length > 1 && args[0].equals("set")) {
                Player target = Bukkit.getPlayer(args[1]);
                if (target != null) {
                    try {
                        double amount = Double.parseDouble(args[2]);
                        this.plugin.set(target.getUniqueId(), BigDecimal.valueOf(amount));

                        target.sendMessage("§aYour balance has been set to §f" + amount + " " + this.plugin.provider.getCustomEconomy().getFriendly());
                    } catch (NumberFormatException e) {
                        sender.sendMessage("§cInvalid amount");
                    }
                } else {
                    sender.sendMessage("§cPlayer is not online or not found");
                }
            }
        }

        return false;
    }
}