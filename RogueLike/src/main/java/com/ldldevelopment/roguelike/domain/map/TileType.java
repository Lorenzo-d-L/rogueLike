package com.ldldevelopment.roguelike.domain.map;

import javafx.scene.paint.Color;

public enum TileType {
    FLOOR(true, Color.web("#21313c")),
    WALL(false, Color.web("#5c677d"));

    private final boolean walkable;
    private final Color color;

    TileType(boolean walkable, Color color) {
        this.walkable = walkable;
        this.color = color;
    }

    public boolean walkable() {
        return walkable;
    }

    public Color color() {
        return color;
    }
}
