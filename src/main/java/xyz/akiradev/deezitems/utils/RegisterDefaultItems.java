package xyz.akiradev.deezitems.utils;

import xyz.akiradev.deezitems.DeezItems;
import xyz.akiradev.deezitems.defaultitems.*;

public class RegisterDefaultItems {

    public static void registerDefaultItems() {
        DeezItems.putItem("deez_sword", new DeezSword());
        DeezItems.putItem("lazer_sword", new LazerSword());
        DeezItems.putItem("trench_pickaxe", new TrenchPick());
        DeezItems.putItem("femboy_stick", new FemboyStick());
        DeezItems.putItem("kick_stick", new KickStick());
    }
}
