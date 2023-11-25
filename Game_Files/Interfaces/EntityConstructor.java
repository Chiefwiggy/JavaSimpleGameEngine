package Game_Files.Interfaces;

import Game_Files.GameObjects.BoardEntity;

@FunctionalInterface
public interface EntityConstructor {
    BoardEntity construct(int x, int y);
}
