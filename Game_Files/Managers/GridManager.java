package Game_Files.Managers;

import Engine.GameObjects.GameObject;
import Game_Files.GameObjects.BoardEntity;
import Game_Files.Helpers.BoardEntities;
import Game_Files.Helpers.AdjacencyMap;
import Game_Files.Helpers.Pair;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Random;

import static Game_Files.GameObjects.Background.BOARD_SIZE;

public class GridManager extends GameObject {

    private static GridManager instance;

    private synchronized static GridManager getInstance() {
        if (instance == null) {
            instance = new GridManager();
        }
        return instance;
    }

    public static void Initialize() { getInstance()._Initialize(); }
    public static void FillGridSpace(int x, int y, BoardEntities entity) { getInstance()._FillGridSpace(x, y, entity); }
    public static void Register(@NotNull BoardEntity entity) { getInstance()._Register(entity); }
    public static void Deregister(@NotNull BoardEntity entity) { getInstance()._Deregister(entity); }
    public static ArrayList<Pair<Integer>> GetAvailableAdjacentSpots(Pair<Integer> xy, boolean diagonal) {
        return getInstance()._GetAvailableAdjacentSpots(xy, diagonal);
    }

    public BoardEntity[][] gridSpaces;

    // Used to represent available spaces
    public AdjacencyMap<Integer, Integer> adjacencyMap;

    public GridManager() {}

    private void _Initialize() {
        gridSpaces = new BoardEntity[BOARD_SIZE][BOARD_SIZE];
        adjacencyMap = InitializeAdjacencyMap();
        InitializeGrid();
    }

    private void InitializeGrid() {
        // Fill 4 middle spots with coral
        int middle = BOARD_SIZE / 2; int[] spots = { 1, 0, 1, 0 };
        for (int i = 0; i < 4; i++) {
            FillGridSpace(middle - spots[i], middle - spots[(i + 1) % 3], BoardEntities.CORAL);
        }
        // Spawn 12 fish and 4 crocs
        int randX; int randY; Random r = new Random();
        ArrayList<Integer> yValues; ArrayList<Integer> currentKeys;
        for (int i = 0; i < 16; i++) {
            currentKeys = adjacencyMap.GetKeys();
            randX = currentKeys.get(r.nextInt(0, currentKeys.size()));
            yValues = adjacencyMap.GetList(randX);
            randY = yValues.get(r.nextInt(0, yValues.size()));
            if (i < 12) { FillGridSpace(randX, randY, BoardEntities.FISH); }
            else { FillGridSpace(randX, randY, BoardEntities.CROCODILE); }
        }
    }

    private ArrayList<Pair<Integer>> _GetAvailableAdjacentSpots(Pair<Integer> xy, boolean diagonal) {
        int[] spots = { 0, 1, 0, -1 }; int checks = 4;
        if (diagonal) { spots = new int[]{ 0, 1, -1, 1, 1, -1, -1, 0, -1 }; checks = 9; }
        ArrayList<Pair<Integer>> availableSpots = new ArrayList<>();
        int x = xy.get(0); int y = xy.get(1); ArrayList<Integer> yValues;
        for (int i = 0; i < checks; i++) {
            yValues = adjacencyMap.GetList(x);
            if (yValues != null && yValues.contains(y)) {
                availableSpots.add(new Pair<>(x+spots[i], y+spots[spots.length-i-1]));
            }
        }
        return availableSpots;
    }

    private void _FillGridSpace(int x, int y, BoardEntities entity) {
        Pair<Integer> pair = new Pair<>(x, y);
        adjacencyMap.Remove(x, y);
        EntityManager.Spawn(pair, entity);
    }

    private void _Register(@NotNull BoardEntity entity) {
        gridSpaces[entity.xy.get(0)][entity.xy.get(1)] = entity;
    }

    private void _Deregister(@NotNull BoardEntity entity) {
        gridSpaces[entity.xy.get(0)][entity.xy.get(1)] = null;
    }

    private AdjacencyMap<Integer, Integer> InitializeAdjacencyMap() {
        AdjacencyMap<Integer, Integer> am = new AdjacencyMap<>();
        for (int i = 0; i < 10; i++) { for (int j = 0; j < 10; j++) { am.Add(i, j); } }
        return am;
    }

    /*
    // For Debugging
    public static void print2DArray() {
        getInstance()._print2DArray();
    }

    private void _print2DArray() {
        StringBuilder sb = new StringBuilder();
        String species;
        for (int j = 0; j < gridSpaces[0].length; j++) {
            sb.append("\n[ ");
            for (int i = 0; i < gridSpaces.length; i++) {
                if (gridSpaces[i][j] == null) {
                    sb.append("  Null   ");
                } else {
                    species = gridSpaces[i][j].getClass().getName().replace("Game_Files.GameObjects.", "");
                    if (!(species.equals(BoardEntities.Crocodile.name()))) { sb.append("  "); }
                    sb.append(species);
                    if (species.equals(BoardEntities.Fish.name())) { sb.append("   "); }
                    else if (species.equals(BoardEntities.Coral.name())) { sb.append("  "); }
                }
                if (i == gridSpaces.length - 1) { sb.append(" ]"); }
                else { sb.append(", "); }
            }
        }
        System.out.println(sb);
    }
     */

}
