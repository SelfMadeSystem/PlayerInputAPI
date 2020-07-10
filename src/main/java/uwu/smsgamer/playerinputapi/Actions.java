package uwu.smsgamer.playerinputapi;

import org.bukkit.entity.*;

public class Actions {
    public abstract static class PlayerAction {
        public abstract void run(HumanEntity player);
    }
    public abstract static class PlayerStringAction {
        public abstract void run(HumanEntity player, String string);
    }
}
