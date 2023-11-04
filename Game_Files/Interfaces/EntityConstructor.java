package Game_Files.Interfaces;

import Game_Files.GameObjects.BoardEntity;
import Game_Files.Helpers.Pair;

@FunctionalInterface
public interface EntityConstructor {
    BoardEntity construct(Pair<Integer> xy);
}
