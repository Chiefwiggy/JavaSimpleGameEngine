package Game_Files.GameObjects;

import Engine.GameObjects.GameObject;
import Game_Files.Enums.EntityObjects;
import Game_Files.Enums.GridSpaceDirections;
import Game_Files.GameObjects.EntityObjects.EntityObject;
import Game_Files.Helpers.Coordinate;
import Game_Files.Interfaces.FactoryObject;
import Game_Files.Managers.GridManager;

import java.awt.*;
import java.util.ArrayList;
import java.util.function.Predicate;

import static Game_Files.Enums.GridSpaceDirections.*;

public class GridSpace<T> extends GameObject implements FactoryObject
{

    private Coordinate<Integer> gridCoords;

    private Coordinate<Float> worldCoords;

    private final float size = GridManager.GetGridSpaceSize();

    private Color color;

    private T data = null;

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

    public boolean Contains(EntityObjects entityType)
    {
        System.out.println("HERE");
        if (data != null) { System.out.println(((EntityObject) data).species.toString()); }
        return data != null && ((EntityObject) data).species == entityType;
    }

    public ArrayList<GridSpace<EntityObject>> GetAdjacentSpaces()
    {
        Predicate<GridSpace<EntityObject>> condition = GridSpace::IsEmpty;
        return GetAdjacentSpaces(condition, false);
    }
    public ArrayList<GridSpace<EntityObject>> GetAdjacentSpaces(Predicate<GridSpace<EntityObject>> condition, boolean diagonal)
    {
        ArrayList<GridSpace<EntityObject>> availableSpaces = new ArrayList<>();
        GridSpaceDirections[] directions = new GridSpaceDirections[] { UP, RIGHT, DOWN, LEFT };
        if (diagonal) { directions = GridSpaceDirections.values(); }
        for (GridSpaceDirections direction : directions)
        {
            int rowCheck = gridCoords.GetRow() + direction.yInc;
            int colCheck = gridCoords.GetCol() + direction.xInc;
            Coordinate<Integer> coord = new Coordinate<>(rowCheck, colCheck);
            System.out.println("Checking coord: ( Row: " + rowCheck + ", Col: " + colCheck + " )");
            GridSpace<EntityObject> check = GridManager.GetGridSpace(coord);
            if (check != null && condition.test(check))
            {
                //System.out.println(check.data);
                availableSpaces.add(check);
                //System.out.println("Added " + check.GetGridCoords().toString() + " to available spaces");
            }
        }
        return availableSpaces;
    }

    // A GridSpace's world space is different from an Entity's world space
    // GridSpace uses top left corner
    private Coordinate<Float> ConvertGridToWorldSpace(Coordinate<Integer> coords)
    {
        return new Coordinate<>(size * coords.GetRow(), size * coords.GetCol());
    }

    private Color SetColor()
    {
        if (gridCoords.GetRow() % 2 == 0)
            return (gridCoords.GetCol() % 2 == 0) ? Color.GRAY : Color.WHITE;
        else
            return (gridCoords.GetCol() % 2 != 0) ? Color.GRAY : Color.WHITE;
    }

    @Override
    public void GameDraw(Graphics2D g2)
    {
        g2.setColor(color);
        int x = worldCoords.GetCol().intValue(), y = worldCoords.GetRow().intValue();
        g2.fillRect(x, y, (int) size, (int) size);
    }

    @Override
    public String toString()
    {
        EntityObjects containedData = null;
        if (data != null) { containedData = ((EntityObject) (data)).species; }
        return "GridSpace with " + gridCoords.toString() + " contains " + containedData;
    }

}
