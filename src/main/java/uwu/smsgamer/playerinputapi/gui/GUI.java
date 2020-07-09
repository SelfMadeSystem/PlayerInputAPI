package uwu.smsgamer.playerinputapi.gui;

import org.bukkit.entity.*;
import org.bukkit.event.*;
import org.bukkit.event.inventory.InventoryCloseEvent;
import uwu.smsgamer.playerinputapi.*;

import java.util.*;

public abstract class GUI implements Listener {
    protected Set<HumanEntity> opened = new HashSet<>();
    protected Actions.PlayerAction closeAction;

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
        PlayerInputAPI.history.get(player).remove(this);
        this.closeAction.run(player);
    }

    @EventHandler(priority = EventPriority.HIGHEST)
    public void onClose(InventoryCloseEvent event) {
        close(event.getPlayer());
    }
}
