package Game_Files.Factories;

import Game_Files.GameObjects.EntityObjects.EntityObject;
import Game_Files.GameObjects.GridSpace;

public class GridSpaceFactory extends AbstractFactory {

    public GridSpaceFactory() {}

    protected GridSpace<EntityObject> build() { return new GridSpace<>(); }

}
