package Game_Files.Managers;

import Engine.GameObjects.GameObject;
import Game_Files.Factories.EntityFactory;
import Game_Files.GameObjects.BoardEntity;

import static Game_Files.GameObjects.Background.BOARD_SIZE;

public class BoardEntityManager extends GameObject {
    // BoardEntityManager. Responsible for spawning, despawning,
    // registering, & deregistering entities. Will also contain our 2D array.
    // Essentially it will deal with all array operations for our 2D array.
    // I feel like this should be a singleton.

    private static BoardEntityManager instance;

    private synchronized static BoardEntityManager getInstance() {
        if (instance == null) {
            instance = new BoardEntityManager();
        }
        return instance;
    }

    public EntityFactory entityFactory;
    public BoardEntity[][] gridSpaces;

    public static void Initialize() { getInstance()._Initialize(); }

    private void _Initialize() {
        gridSpaces = new BoardEntity[BOARD_SIZE][BOARD_SIZE];
        entityFactory = new EntityFactory();
        // Fill 4 middle spots with coral
        int middle = BOARD_SIZE / 2;
        Spawn(middle - 1, middle, "Coral");
        Spawn(middle, middle - 1, "Coral");
        Spawn(middle - 1, middle - 1, "Coral");
        Spawn(middle, middle, "Coral");

        // Spawn 12 fish and 4 crocs
        int y = 0;
        String t = "Fish";
        for (int i = 0; i < 16; i++) {
            if (i == BOARD_SIZE) {
                y = 1;
            } else if (i == 12) {
                t = "Croc";
            }
            Spawn(i % BOARD_SIZE, y, t);
        }
        print2DArray();
    }

    public static BoardEntity Spawn(int x, int y, String t) {
        return getInstance()._Spawn(x, y, t);
    }

    private BoardEntity _Spawn(int x, int y, String t) {
        return entityFactory.GetEntity(x, y, t);
    }

    public static void Register(BoardEntity entity) {
        getInstance()._Register(entity);
    }

    public static void Deregister(BoardEntity entity) {
        getInstance()._Deregister(entity);
    }

    private void _Register(@org.jetbrains.annotations.NotNull BoardEntity entity) {
        gridSpaces[entity.x][entity.y] = entity;
    }

    private void _Deregister(BoardEntity entity) {
        gridSpaces[entity.x][entity.y] = null;
    }

    // For Debugging
    public static void print2DArray() {
        getInstance()._print2DArray();
    }

    private void _print2DArray() {
        StringBuilder sb = new StringBuilder();
        String s;
        for (int i = 0; i < gridSpaces[0].length; i++) {
            sb.append("\n[ ");
            for (BoardEntity[] gridSpace : gridSpaces) {
                if (gridSpace[i] == null) {
                    sb.append("null ");
                } else {
                    s = gridSpace[i].species;
                    sb.append(s);
                    if (!s.equals("Coral")) {
                        sb.append(" ");
                    }
                }
                sb.append(", ");
            }
            sb.append(gridSpaces[gridSpaces[0].length - 1][gridSpaces.length - 1]).append(" ]");
        }
        System.out.println(sb);
    }
}
