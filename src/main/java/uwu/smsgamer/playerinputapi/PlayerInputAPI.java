package uwu.smsgamer.playerinputapi;

import lombok.*;
import org.bukkit.entity.*;
import org.bukkit.plugin.Plugin;
import uwu.smsgamer.playerinputapi.input.PlayerInput;

import java.util.HashMap;

public class PlayerInputAPI {
    @Getter
    @Setter
    private static Plugin plugin;
    public static HashMap<HumanEntity, PlayerInput> opened = new HashMap<>();

    public static void open(PlayerInput pi, HumanEntity p) {
        pi.open(p);
    }

    public static void put(PlayerInput pi, HumanEntity p) {
        opened.put(p, pi);
    }

    public static void remove(HumanEntity p) {
        opened.remove(p);
    }

    public static void setup(Plugin pl) {
        plugin = pl;
    }
}
