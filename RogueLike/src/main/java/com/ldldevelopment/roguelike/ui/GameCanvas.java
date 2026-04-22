package com.ldldevelopment.roguelike.ui;

import com.ldldevelopment.roguelike.domain.actor.Player;
import com.ldldevelopment.roguelike.domain.map.GameMap;
import com.ldldevelopment.roguelike.domain.map.TileType;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

public class GameCanvas extends Canvas {
    private final GameMap map;
    private final Player player;
    private final int tileSize;

    public GameCanvas(GameMap map, Player player, int tileSize) {
        super(map.widthInPixels(tileSize), map.heightInPixels(tileSize));
        this.map = map;
        this.player = player;
        this.tileSize = tileSize;
        setFocusTraversable(true);
    }

    public void render() {
        GraphicsContext graphics = getGraphicsContext2D();
        graphics.setFill(Color.web("#08141b"));
        graphics.fillRect(0, 0, getWidth(), getHeight());

        for (int y = 0; y < map.height(); y++) {
            for (int x = 0; x < map.width(); x++) {
                TileType tile = map.tileAt(x, y);
                graphics.setFill(tile.color());
                graphics.fillRect(x * tileSize, y * tileSize, tileSize, tileSize);
            }
        }

        graphics.setFill(Color.web("#f9c74f"));
        graphics.fillOval(player.x() - 12, player.y() - 12, 24, 24);

        graphics.setFill(Color.web("#d9e2ec"));
        graphics.setFont(Font.font("Consolas", FontWeight.BOLD, 14));
        graphics.fillText("Move freely: WASD / Arrows", 10, 18);
    }
}
