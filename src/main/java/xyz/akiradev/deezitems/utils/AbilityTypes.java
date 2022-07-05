package xyz.akiradev.deezitems.utils;

public enum AbilityTypes {
    NONE(""),
    LEFT_CLICK("left_click"),
    RIGHT_CLICK("right_click"),
    MIDDLE_CLICK("middle_click");

    private String name;

    AbilityTypes(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
