package Game_Files.Managers;

import Engine.GameObjects.GameObject;
import Game_Files.Factories.EntityFactory;
import Game_Files.Helpers.BoardEntities;
import Game_Files.GameObjects.BoardEntity;
import Game_Files.Helpers.Multimap;
import Game_Files.Helpers.Pair;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Random;

import static Game_Files.GameObjects.Background.BOARD_SIZE;

public class BoardEntityManager extends GameObject {

    private static BoardEntityManager instance;

    private synchronized static BoardEntityManager getInstance() {
        if (instance == null) {
            instance = new BoardEntityManager();
        }
        return instance;
    }

    public EntityFactory entityFactory;
    public BoardEntity[][] gridSpaces;

    // Could use something like a hash table instead. We will see. Might not even need it.

    // Maybe do a check spaces every time we need to invoke the random function.
    // That will make it so that we can just have taken spaces as a local variable
    // whenever we need it. Keeps space complexity small. Also makes it so that we
    // don't have to constantly track the array list and remove pairs when some entity despawns.
    //public ArrayList<Pair<Integer>> takenSpaces;
    public Multimap<Integer, Integer> multimap;

    public static void Initialize() { getInstance()._Initialize(); }

    private void _Initialize() {
        gridSpaces = new BoardEntity[BOARD_SIZE][BOARD_SIZE];
        entityFactory = new EntityFactory();
        multimap = new Multimap<>();

        PopulateMultimap();
        // Fill 4 middle spots with coral
        int middle = BOARD_SIZE / 2;
        int[] spots = { 1, 0, 1, 0 };
        Pair<Integer> pair;
        for (int i = 0; i < 4; i++) {
            pair = new Pair<>(middle - spots[i], middle - spots[(i + 1) % 3]);
            multimap.Remove(pair.get(0), pair.get(1));
            Spawn(pair, BoardEntities.Coral);
        }

        // Spawn 12 fish and 4 crocs
        int randX; int randY; ArrayList<Integer> yValues; ArrayList<Integer> currentKeys;
        for (int i = 0; i < 16; i++) {
            currentKeys = multimap.GetKeys();
            randX = currentKeys.get(new Random().nextInt(0, currentKeys.size()));
            yValues = multimap.GetList(randX);
            randY = yValues.get(new Random().nextInt(0, yValues.size()));
            pair = new Pair<>(randX, randY);
            multimap.Remove(randX, randY);
            if (i < 12) { Spawn(pair, BoardEntities.Fish); }
            else { Spawn(pair, BoardEntities.Crocodile); }
        }
    }

    public void PopulateMultimap() {
        for (int i = 0; i < 10; i++) { for (int j = 0; j < 10; j++) { multimap.Add(i, j); } }
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

    private void _Register(@NotNull BoardEntity entity) {
        gridSpaces[entity.xy.get(0)][entity.xy.get(1)] = entity;
    }

    public static void Deregister(BoardEntity entity) {
        getInstance()._Deregister(entity);
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
}
