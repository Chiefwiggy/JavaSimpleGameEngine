package Simulation_EXAMPLE.BOARD_ENTITY;

import Engine.GameObjects.GameObject;

import java.util.ArrayList;
import java.util.List;

public class BoardManager extends GameObject {

    public ArrayList<ArrayList<List<BoardEntity>>> gridSpaces;

    public BoardManager() {
        gridSpaces = new ArrayList<>();
    }

    public boolean checkAdjacentSquares(int gx, int gy) {
        return true;
    }
}
