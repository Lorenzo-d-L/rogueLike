package com.ldldevelopment.roguelike;

import com.ldldevelopment.roguelike.config.GameConfig;
import com.ldldevelopment.roguelike.domain.actor.Player;
import com.ldldevelopment.roguelike.domain.map.GameMap;
import com.ldldevelopment.roguelike.ui.GameCanvas;
import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.collections.ObservableSet;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class RogueLikeApplication extends Application {
    @Override
    public void start(Stage stage) {
        GameMap map = GameMap.createStarterMap(GameConfig.MAP_WIDTH, GameConfig.MAP_HEIGHT);
        Player player = new Player(GameConfig.TILE_SIZE * 2.5, GameConfig.TILE_SIZE * 2.5);
        GameCanvas canvas = new GameCanvas(map, player, GameConfig.TILE_SIZE);

        BorderPane root = new BorderPane(canvas);
        Scene scene = new Scene(
                root,
                GameConfig.MAP_WIDTH * GameConfig.TILE_SIZE,
                GameConfig.MAP_HEIGHT * GameConfig.TILE_SIZE
        );
        ObservableSet<KeyCode> pressedKeys = javafx.collections.FXCollections.observableSet();
        scene.setOnKeyPressed(event -> pressedKeys.add(event.getCode()));
        scene.setOnKeyReleased(event -> pressedKeys.remove(event.getCode()));

        AnimationTimer gameLoop = new AnimationTimer() {
            private long previousFrameTime;

            @Override
            public void handle(long now) {
                if (previousFrameTime == 0L) {
                    previousFrameTime = now;
                    canvas.render();
                    return;
                }

                double deltaSeconds = (now - previousFrameTime) / 1_000_000_000.0;
                previousFrameTime = now;

                double dx = 0.0;
                double dy = 0.0;

                if (pressedKeys.contains(KeyCode.W) || pressedKeys.contains(KeyCode.UP)) {
                    dy -= 1.0;
                }
                if (pressedKeys.contains(KeyCode.S) || pressedKeys.contains(KeyCode.DOWN)) {
                    dy += 1.0;
                }
                if (pressedKeys.contains(KeyCode.A) || pressedKeys.contains(KeyCode.LEFT)) {
                    dx -= 1.0;
                }
                if (pressedKeys.contains(KeyCode.D) || pressedKeys.contains(KeyCode.RIGHT)) {
                    dx += 1.0;
                }

                if (dx != 0.0 || dy != 0.0) {
                    double length = Math.sqrt(dx * dx + dy * dy);
                    player.move(dx / length, dy / length, map, deltaSeconds);
                }

                canvas.render();
            }
        };
        gameLoop.start();

        stage.setTitle("RogueLike Prototype");
        stage.setScene(scene);
        stage.show();
        canvas.requestFocus();
    }
}
