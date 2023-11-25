package Game_Files.Managers;

import Engine.GameObjects.GameObject;
import Game_Files.GameObjects.BoardEntity;
import Game_Files.GameObjects.Fish;
import Game_Files.Helpers.BoardEntities;
import Game_Files.Helpers.AdjacencyMap;

import java.util.ArrayList;
import java.util.Objects;

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
    public static void ClearGridSpace(int x, int y) { getInstance()._ClearGridSpace(x, y); }
    public static void Register(BoardEntity entity) { getInstance()._Register(entity); }
    public static void Deregister(BoardEntity entity) { getInstance()._Deregister(entity); }
    public static ArrayList<int[]> GetAvailableAdjacentSpots(int x, int y, boolean diagonal, boolean checkFish) {
        return getInstance()._GetAvailableAdjacentSpots(x, y, diagonal, checkFish);
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

    private ArrayList<int[]> _GetAvailableAdjacentSpots(int x, int y, boolean diagonal, boolean checkFish) {
        int[] spots = { 0, 1, 0, -1 }; int checks = 4;
        if (diagonal) { spots = new int[]{ 0, 1, -1, 1, 1, -1, -1, 0, -1 }; checks = 9; }
        ArrayList<int[]> availableSpots = new ArrayList<>();
        int xNew; int yNew;
        ArrayList<Integer> yValues;
        for (int i = 0; i < checks; i++) {
            xNew = x+spots[i]; yNew = y+spots[spots.length-i-1];
            yValues = adjacencyMap.GetList(xNew);
            if ((yValues != null) && (yValues.contains(yNew) != checkFish)) {
                if ((0 <= xNew && xNew < BOARD_SIZE) && (0 <= yNew && yNew < BOARD_SIZE)) {
                    if (checkFish && !Objects.equals(gridSpaces[xNew][yNew].getClass(), Fish.class)) {
                        continue;
                    }
                    availableSpots.add(new int[]{xNew, yNew});
                }
            }
        }
        return availableSpots;
    }

    // There's more to this. Will change according to how things play out.
    private void _FillGridSpace(int x, int y, BoardEntities entity) {
        EntityManager.Spawn(x, y, entity);
    }

    private void _ClearGridSpace(int x, int y) {
        EntityManager.Despawn(gridSpaces[x][y]);
    }

    private void _Register(BoardEntity entity) {
        int x = entity.GetCoords()[0];
        int y = entity.GetCoords()[1];
        gridSpaces[x][y] = entity;
        adjacencyMap.Remove(x, y);
    }

    private void _Deregister(BoardEntity entity) {
        int x = entity.GetCoords()[0];
        int y = entity.GetCoords()[1];
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
