package com.enigma.wmb.constant;

import lombok.Getter;

@Getter
public enum MenuCategory {
    FOOD("Makanan"),
    BEVERAGE("Minuman");

    private final String name;

    MenuCategory(String name) {
        this.name = name;
    }

    public static MenuCategory findByName(String name) {
        for (MenuCategory category : MenuCategory.values()) {
            if (category.getName().equals(name)) {
                return category;
            }
        }
        return null;
    }
}
