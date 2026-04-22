package com.ldldevelopment.roguelike.domain.map;

import com.ldldevelopment.roguelike.config.GameConfig;

public class GameMap {
    private final TileType[][] tiles;

    public GameMap(TileType[][] tiles) {
        this.tiles = tiles;
    }

    public static GameMap createStarterMap(int width, int height) {
        TileType[][] tiles = new TileType[height][width];

        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                boolean border = x == 0 || y == 0 || x == width - 1 || y == height - 1;
                tiles[y][x] = border ? TileType.WALL : TileType.FLOOR;
            }
        }

        for (int x = 4; x < width - 4; x++) {
            tiles[5][x] = TileType.WALL;
        }
        tiles[5][10] = TileType.FLOOR;
        tiles[5][11] = TileType.FLOOR;

        for (int y = 8; y < height - 3; y++) {
            tiles[y][7] = TileType.WALL;
        }
        tiles[11][7] = TileType.FLOOR;

        for (int x = 12; x < width - 2; x++) {
            tiles[12][x] = TileType.WALL;
        }
        tiles[12][17] = TileType.FLOOR;

        return new GameMap(tiles);
    }

    public int width() {
        return tiles[0].length;
    }

    public int height() {
        return tiles.length;
    }

    public int widthInPixels(int tileSize) {
        return width() * tileSize;
    }

    public int heightInPixels(int tileSize) {
        return height() * tileSize;
    }

    public TileType tileAt(int x, int y) {
        return tiles[y][x];
    }

    public boolean isWalkable(int x, int y) {
        return x >= 0 && y >= 0 && x < width() && y < height() && tileAt(x, y).walkable();
    }

    public boolean isWalkable(double worldX, double worldY, double radius) {
        return isWalkablePoint(worldX - radius, worldY - radius)
                && isWalkablePoint(worldX + radius, worldY - radius)
                && isWalkablePoint(worldX - radius, worldY + radius)
                && isWalkablePoint(worldX + radius, worldY + radius);
    }

    private boolean isWalkablePoint(double worldX, double worldY) {
        int tileX = (int) (worldX / GameConfig.TILE_SIZE);
        int tileY = (int) (worldY / GameConfig.TILE_SIZE);
        return isWalkable(tileX, tileY);
    }
}
