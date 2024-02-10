package Game_Files.Enums;

public enum SpawnBehaviors {

    // Spawn completely randomly on the grid (in available spaces)
    RANDOM(),
    // Spawn randomly around an entity of a certain type
    ENTITY_BASED(),
    // Spawn based on a position/coordinate on the board
    FIXED()

}
