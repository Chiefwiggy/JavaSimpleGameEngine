package Simulation_EXAMPLE.BOARD_ENTITY;

import Engine.GameObjects.GameObject;

import java.awt.*;

public abstract class BoardEntity extends GameObject {

    private int x, y;
    public int gridX, gridY;
    public int width, height;

    protected BoardEntity() {
        gridX = 0;
        gridY = 0;
        width = 0;
        height = 0;
        updateObject.SubmitUpdateRegistration();
    }


    @Override
    public void GameUpdate() {
        x = convertGridToWorldSpace(gridX);
        y = convertGridToWorldSpace(gridY);
    }

    public int getWorldX() {
        return x;
    }

    public int getWorldY() {
        return y;
    }

    protected int convertWorldSpaceToGrid(int x) {
        return 0;
    }

    protected int convertGridToWorldSpace(int x) {
        return 0;
    }


}
