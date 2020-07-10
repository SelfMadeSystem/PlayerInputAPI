package uwu.smsgamer.playerinputapi.input;

import org.bukkit.Bukkit;
import org.bukkit.entity.HumanEntity;
import org.bukkit.event.Listener;
import org.bukkit.plugin.Plugin;
import uwu.smsgamer.playerinputapi.PlayerInputAPI;

public abstract class PlayerInput implements Listener {
    // Anvil
    // Sign
    // Text
    protected Plugin pl;
    protected PlayerInput() {
        if (PlayerInputAPI.getPlugin() == null) throw new NullPointerException("Plugin not initialized. Forget to setup PlayerInputAPI?");
        pl = PlayerInputAPI.getPlugin();
        Bukkit.getPluginManager().registerEvents(this, pl);
    }

    public void open(HumanEntity player) { //override
        PlayerInputAPI.put(this, player);
    }
    public void close(HumanEntity player) {//override
        PlayerInputAPI.remove(player);
    }
}
