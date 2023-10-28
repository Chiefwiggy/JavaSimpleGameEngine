package Game_Files;

import Engine.GameObjects.GameObject;

import static Game_Files.Background.BOARD_SIZE;


public class GameManager extends GameObject {

    private static GameManager instance;

    private synchronized static GameManager getInstance() {
        if (instance == null) {
            instance = new GameManager();
        }
        return instance;
    }

    public BoardEntity[][] gridSpaces;
    public StepManager stepManager;
    public EntityFactory entityFactory;
    public Background background;

    public static void Initialize() {
        getInstance()._Initialize();
    }

    private void _Initialize() {
        gridSpaces = new BoardEntity[BOARD_SIZE][BOARD_SIZE];
        stepManager = new StepManager();
        entityFactory = new EntityFactory();
        background = new Background();

        // Fill 4 middle spots with coral
        int middle = BOARD_SIZE / 2;
        gridSpaces[middle-1][middle] = Spawn(middle-1, middle, "Coral");
        gridSpaces[middle][middle-1] = Spawn(middle, middle-1, "Coral");
        gridSpaces[middle-1][middle-1] = Spawn(middle-1, middle-1, "Coral");
        gridSpaces[middle][middle] = Spawn(middle, middle, "Coral");

        // Spawn 12 fish and 4 crocs
        int y = 0;
        String t = "Fish";
        for (int i = 0; i < 16; i++) {
            if (i == BOARD_SIZE) { y = 1; } else if (i == 12) { t = "Croc"; }
            gridSpaces[i % BOARD_SIZE][y] = Spawn(i % BOARD_SIZE, y, t);
        }
        print2DArray();

        // Potentially create an EntityHandler. Responsible for spawning, despawning,
        // registering, & deregistering entities. Will also contain our 2D array.
        // Essentially it will deal with all array operations for our 2D array.
    }

    public static void Step() {
        getInstance()._Step();
    }

    private void _Step() {
        // Do what it does every step: The following is temporary
        System.out.println("Here");
        int x = (int) (Math.random() * 9.9);
        int y = (int) (Math.random() * 9.9);
        gridSpaces[x][y] = Spawn(x, y, "Croc");
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
