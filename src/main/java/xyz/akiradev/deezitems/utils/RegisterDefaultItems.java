package xyz.akiradev.deezitems.utils;

import xyz.akiradev.deezitems.DeezItems;
import xyz.akiradev.deezitems.defaultitems.DeezSword;
import xyz.akiradev.deezitems.defaultitems.LazerSword;

public class RegisterDefaultItems {

    public static void registerDefaultItems() {
        DeezItems.putItem("deez_sword", new DeezSword());
        DeezItems.putItem("lazer_sword", new LazerSword());
    }
}
