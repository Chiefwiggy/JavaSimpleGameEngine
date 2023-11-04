package Game_Files.Managers;

import Engine.GameObjects.GameObject;
import Game_Files.GameObjects.BoardEntity;
import Game_Files.GameObjects.Fish;
import Game_Files.Helpers.BoardEntities;
import Game_Files.Helpers.AdjacencyMap;
import Game_Files.Helpers.Pair;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Objects;
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
    public static ArrayList<Pair<Integer>> GetAvailableAdjacentSpots(Pair<Integer> xy, boolean diagonal, boolean checkFish) {
        return getInstance()._GetAvailableAdjacentSpots(xy, diagonal, checkFish);
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
        int randX; int randY;
        ArrayList<Integer> yValues; ArrayList<Integer> currentKeys;
        for (int i = 0; i < 16; i++) {
            currentKeys = adjacencyMap.GetKeys();
            randX = currentKeys.get(GameManager.random.nextInt(0, currentKeys.size()));
            yValues = adjacencyMap.GetList(randX);
            randY = yValues.get(GameManager.random.nextInt(0, yValues.size()));
            if (i < 12) { FillGridSpace(randX, randY, BoardEntities.FISH); }
            else { FillGridSpace(randX, randY, BoardEntities.CROCODILE); }
        }
    }

    private ArrayList<Pair<Integer>> _GetAvailableAdjacentSpots(Pair<Integer> xy, boolean diagonal, boolean checkFish) {
        // Need to make this so that it can check for fish too.
        // Another bool param like checkFish
        // If that's true we need to check to make sure yValues does not contain xy
            // If that's true, we need to check the actual grid to check if it's a fish
                // We should already not be checking the spot if it's out of bounds at this point.
        int[] spots = { 0, 1, 0, -1 }; int checks = 4;
        if (diagonal) { spots = new int[]{ 0, 1, -1, 1, 1, -1, -1, 0, -1 }; checks = 9; }
        ArrayList<Pair<Integer>> availableSpots = new ArrayList<>();
        int x = xy.get(0); int y = xy.get(1); int xNew; int yNew;
        ArrayList<Integer> yValues;
        for (int i = 0; i < checks; i++) {
            xNew = x+spots[i]; yNew = y+spots[spots.length-i-1];
            yValues = adjacencyMap.GetList(xNew);
            if ((yValues != null) && (yValues.contains(yNew) != checkFish)) {
                if ((0 <= xNew && xNew < BOARD_SIZE) && (0 <= yNew && yNew < BOARD_SIZE)) {
                    // If checkFish == true, check the supposed gridSpace for a fish
                    if (checkFish && !Objects.equals(gridSpaces[xNew][yNew].getClass(), Fish.class)) {
                        continue;
                    }
                    availableSpots.add(new Pair<>(xNew, yNew));
                }
            }
        }
        return availableSpots;
    }

    // There's more to this. Will change according to how things play out.
    private void _FillGridSpace(int x, int y, BoardEntities entity) {
        Pair<Integer> pair = new Pair<>(x, y);
        EntityManager.Spawn(pair, entity);
    }

    private void _Register(@NotNull BoardEntity entity) {
        int x = entity.GetCoords().get(0); int y = entity.GetCoords().get(1);
        gridSpaces[x][y] = entity;
        adjacencyMap.Remove(x, y);
    }

    private void _Deregister(@NotNull BoardEntity entity) {
        int x = entity.GetCoords().get(0); int y = entity.GetCoords().get(1);
        gridSpaces[x][y] = null;
        adjacencyMap.Add(x, y);
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
