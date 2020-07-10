package uwu.smsgamer.playerinputapi.gui;

import org.bukkit.Bukkit;
import org.bukkit.entity.*;
import org.bukkit.event.*;
import uwu.smsgamer.playerinputapi.*;

import java.util.*;

public abstract class GUI implements Listener {
    protected Set<HumanEntity> opened = new HashSet<>();
    protected Actions.PlayerAction closeAction;

    public GUI() {
        Bukkit.getPluginManager().registerEvents(this, PlayerInputAPI.getPlugin());
    }

    public void openPlayer(HumanEntity player) {
        try {
            opened.add(player);
            PlayerInputAPI.history.get(player).add(this);
        } catch (Exception e) {
            e.printStackTrace();
            opened.remove(player);
            PlayerInputAPI.history.get(player).remove(this);
        }
    }

    public boolean isOpened(HumanEntity player) {
        return opened.contains(player);
    }

    public void setCloseAction(Actions.PlayerAction action) {
        this.closeAction = action;
    }

    public void close(HumanEntity player) {
        this.opened.remove(player);
        this.closeAction.run(player);
    }

    /* TODO: Will do this for inventory guis.
    @EventHandler(priority = EventPriority.MONITOR)
    public void onClose(InventoryCloseEvent event) {
        if (PlayerInputAPI.opened(event.getPlayer(), this))
            PlayerInputAPI.prevGUI(event.getPlayer());
    }*/
}
