package uwu.smsgamer.playerinputapi;

import org.bukkit.entity.HumanEntity;
import org.bukkit.event.*;
import org.bukkit.event.player.PlayerJoinEvent;
import uwu.smsgamer.playerinputapi.gui.GUI;

import java.util.*;

public final class PlayerInputAPI implements Listener {
    public static HashMap<HumanEntity, List<GUI>> history = new HashMap<>();

    public static void closeAll(HumanEntity player) {
        player.closeInventory();
        for (GUI gui : history.get(player))
            gui.close(player);
        history.put(player, new ArrayList<>());
    }

    public static boolean opened(HumanEntity player, GUI gui) {
        if (!history.containsKey(player)) return false;
        List<GUI> list = history.get(player);
        if (!list.contains(gui)) return false;
        return list.lastIndexOf(gui) == list.size() - 1;
    }

    @EventHandler
    public void onJoin(PlayerJoinEvent event) {
        history.put(event.getPlayer(), new ArrayList<>());
    }
}
