package Game_Files.Managers;

import Engine.GameObjects.GameObject;
import Game_Files.Factories.EntityFactory;
import Game_Files.Helpers.BoardEntities;
import Game_Files.GameObjects.BoardEntity;
import Game_Files.Helpers.Pair;
import org.jetbrains.annotations.NotNull;

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
        Spawn(new Pair<>(middle - 1, middle), BoardEntities.CORAL);
        Spawn(new Pair<>(middle, middle - 1), BoardEntities.CORAL);
        Spawn(new Pair<>(middle - 1, middle - 1), BoardEntities.CORAL);
        Spawn(new Pair<>(middle, middle), BoardEntities.CORAL);

        // Spawn 12 fish and 4 crocs
        int y = 0;
        BoardEntities species = BoardEntities.FISH;
        for (int i = 0; i < 16; i++) {
            if (i == BOARD_SIZE) {
                y = 1;
            } else if (i == 12) {
                species = BoardEntities.CROCODILE;
            }
            Spawn(new Pair<>(i % BOARD_SIZE, y), species);
        }
        print2DArray();
    }

    public static BoardEntity Spawn(Pair<Integer> xy, BoardEntities species) {
        return getInstance()._Spawn(xy, species);
    }

    private BoardEntity _Spawn(Pair<Integer> xy, BoardEntities species) {
        return entityFactory.GetEntity(xy, species);
    }

    public static void Register(BoardEntity entity) {
        getInstance()._Register(entity);
    }

    public static void Deregister(BoardEntity entity) {
        getInstance()._Deregister(entity);
    }

    private void _Register(@NotNull BoardEntity entity) {
        gridSpaces[entity.xy.get(0)][entity.xy.get(1)] = entity;
    }

    private void _Deregister(@NotNull BoardEntity entity) {
        gridSpaces[entity.xy.get(0)][entity.xy.get(1)] = null;
    }

    // For Debugging
    public static void print2DArray() {
        getInstance()._print2DArray();
    }

    private void _print2DArray() {
        StringBuilder sb = new StringBuilder();
        BoardEntities species;
        for (int j = 0; j < gridSpaces[0].length; j++) {
            sb.append("\n[ ");
            for (int i = 0; i < gridSpaces.length; i++) {
                if (gridSpaces[i][j] == null) {
                    sb.append("  NULL   ");
                } else {
                    species = gridSpaces[i][j].species;
                    if (species != BoardEntities.CROCODILE) { sb.append("  "); }
                    sb.append(species);
                    if (species == BoardEntities.FISH) { sb.append("   "); }
                    else if (species == BoardEntities.CORAL) { sb.append("  "); }
                }
                if (i == gridSpaces.length - 1) { sb.append(" ]"); }
                else { sb.append(", "); }
            }
        }
        System.out.println(sb);
    }
}
