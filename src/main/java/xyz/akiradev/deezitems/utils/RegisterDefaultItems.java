package xyz.akiradev.deezitems.utils;

import xyz.akiradev.deezitems.DeezItems;
import xyz.akiradev.deezitems.defaultitems.*;

public class RegisterDefaultItems {

    public static void registerDefaultItems() {
        DeezItems.registerItem("deez_sword", new DeezSword());
        DeezItems.registerItem("lazer_sword", new LazerSword());
        DeezItems.registerItem("trench_pickaxe", new TrenchPick());
        DeezItems.registerItem("femboy_stick", new FemboyStick());
    }
}
