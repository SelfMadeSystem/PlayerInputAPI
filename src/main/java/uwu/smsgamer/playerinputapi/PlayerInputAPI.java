package uwu.smsgamer.playerinputapi;

import lombok.*;
import org.bukkit.entity.HumanEntity;
import org.bukkit.event.*;
import org.bukkit.event.player.*;
import org.bukkit.plugin.Plugin;
import uwu.smsgamer.playerinputapi.gui.GUI;

import java.util.*;

public final class PlayerInputAPI implements Listener {
    public static final HashMap<HumanEntity, List<GUI>> history = new HashMap<>();
    @Getter
    @Setter
    private static Plugin plugin;

    public static void setup(Plugin pl) {
        plugin = pl;
    }

    public static void closeAll(HumanEntity player) {
        player.closeInventory();
        List<GUI> get = history.get(player);
        for (int i = get.size() - 1; i >= 0; i--) {
            GUI gui = get.get(i);
            gui.close(player);
        }
        history.put(player, new ArrayList<>());
    }

    public static void prevGUI(HumanEntity player) {
        List<GUI> list = history.get(player);
        if (!list.isEmpty()) {
            GUI o = getOpened(player);
            if (o == null) throw new NullPointerException("No opened GUI for player: " + player.getName());
            list.remove(list.size() - 1);
            GUI g = getOpened(player);
            o.close(player);
            if (g != null) g.openPlayer(player);
        }
    }

    public static GUI getOpened(HumanEntity player) {
        if (!history.containsKey(player)) throw new NullPointerException("HumanEntity " + player.getName() + " not in history map.");
        List<GUI> list = history.get(player);
        return list.isEmpty() ? null : list.get(list.size()-1);
    }

    public static boolean opened(HumanEntity player, GUI gui) {
        GUI o = getOpened(player);
        return o != null && o.equals(gui);
    }

    @EventHandler
    public void onJoin(PlayerJoinEvent event) {
        history.put(event.getPlayer(), new ArrayList<>());
    }

    @EventHandler
    public void onQuit(PlayerQuitEvent event) {
        closeAll(event.getPlayer());
    }
}
