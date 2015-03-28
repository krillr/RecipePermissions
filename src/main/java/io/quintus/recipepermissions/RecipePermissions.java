package io.quintus.recipepermissions;

import net.md_5.bungee.api.ChatColor;
import org.bukkit.entity.HumanEntity;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.PrepareItemCraftEvent;
import org.bukkit.plugin.java.JavaPlugin;
import org.mcstats.Metrics;

import java.io.IOException;
import java.util.logging.Level;

public class RecipePermissions extends JavaPlugin implements Listener {

    @Override
    public void onEnable() {
        try {
            Metrics metrics = new Metrics(this);
            metrics.start();
        } catch (IOException e) {}
        getServer().getPluginManager().registerEvents(this, this);
    }

    @EventHandler
    public void onPrepareItemCraft(PrepareItemCraftEvent event) {
        for (HumanEntity entity : event.getViewers()) {
            if (!entity.hasPermission("recipepermissions.craft." + event.getRecipe().getResult().getType().toString()) || entity.hasPermission("recipepermissions.nocraft." + event.getRecipe().getResult().getType().toString())) {
                event.getView().setItem(0, null);
                for (HumanEntity _entity : event.getViewers()) {
                    _entity.sendMessage(ChatColor.RED + "You do not have permission to craft that recipe.");
                }
                return;
            }
        }
    }

}
