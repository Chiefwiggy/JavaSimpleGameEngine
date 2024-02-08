package Game_Files.GameObjects;

import Engine.GameObjects.GameObject;
import Game_Files.Enums.GridSpaceDirections;
import Game_Files.GameObjects.EntityObjects.EntityObject;
import Game_Files.Helpers.Coordinate;
import Game_Files.Interfaces.FactoryObject;
import Game_Files.Managers.GridManager;

import java.awt.*;
import java.util.ArrayList;

public class GridSpace<T> extends GameObject implements FactoryObject
{

    private Coordinate<Integer> gridCoords;

    private Coordinate<Float> worldCoords;

    private final float size = GridManager.GetGridSpaceSize();

    private Color color;

    private T data;

    public GridSpace() {}

    public void Initialize() {}

    public void Deinitialize() {}

    public T GetData() { return data; }

    public Coordinate<Integer> GetGridCoords() { return gridCoords; }

    public Coordinate<Float> GetWorldCoords() { return worldCoords; }

    public void SetData(T item) { data = item; }

    public void SetCoordinates(Coordinate<Integer> coords)
    {
        gridCoords = coords;
        worldCoords = ConvertGridToWorldSpace(gridCoords);
        color = SetColor();
        drawObject.SubmitDrawRegistration();
        updateObject.SubmitUpdateRegistration();
    }

    public void RemoveData()
    {
        data = null;
    }

    public boolean IsEmpty() { return data == null; }

    public ArrayList<GridSpace<EntityObject>> GetAdjacentSpaces() {
        ArrayList<GridSpace<EntityObject>> availableSpaces = new ArrayList<>();
        for (GridSpaceDirections direction : GridSpaceDirections.values()) {
            int xCheck = this.gridCoords.GetX() + direction.xInc;
            int yCheck = this.gridCoords.GetY() + direction.yInc;
            GridSpace<EntityObject> check = GridManager.GetGridSpace(new Coordinate<>(yCheck, xCheck));
            if (check.IsEmpty()) { availableSpaces.add(check); }
        }
        return availableSpaces;
    }

    // A GridSpace's world space is different from an Entity's world space
    // GridSpace uses top left corner
    private Coordinate<Float> ConvertGridToWorldSpace(Coordinate<Integer> coords)
    {
        return new Coordinate<>(size * coords.GetY(), size * coords.GetX());
    }

    private Color SetColor()
    {
        if (gridCoords.GetY() % 2 == 0)
            return (gridCoords.GetX() % 2 == 0) ? Color.GRAY : Color.WHITE;
        else
            return (gridCoords.GetX() % 2 != 0) ? Color.GRAY : Color.WHITE;
    }

    @Override
    public void GameDraw(Graphics2D g2)
    {
        g2.setColor(color);
        g2.fillRect(worldCoords.GetX().intValue(), worldCoords.GetY().intValue(), (int) size, (int) size);
    }

    @Override
    public String toString()
    {
        return "GridSpace with " + gridCoords.toString() + " contains " + data;
    }

}
