package com.ldldevelopment.roguelike.domain.actor;

import com.ldldevelopment.roguelike.config.GameConfig;
import com.ldldevelopment.roguelike.domain.map.GameMap;

public class Player {
    private double x;
    private double y;
    public Player(double x, double y) {
        this.x = x;
        this.y = y;
    }
    public double x() {
        return x;
    }
    public double y() {
        return y;
    }
    public void move(double dx, double dy, GameMap map, double deltaSeconds) {
        double stepX = dx * GameConfig.PLAYER_SPEED * deltaSeconds;
        double stepY = dy * GameConfig.PLAYER_SPEED * deltaSeconds;
        double nextX = x + stepX;
        double nextY = y + stepY;

        if (map.isWalkable(nextX, y, GameConfig.PLAYER_RADIUS)) {
            x = nextX;
        }

        if (map.isWalkable(x, nextY, GameConfig.PLAYER_RADIUS)) {
            y = nextY;
        }
    }
}
