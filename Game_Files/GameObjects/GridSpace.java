package Game_Files.GameObjects;

import Engine.GameObjects.GameObject;
import Game_Files.Helpers.Coordinate;
import Game_Files.Interfaces.FactoryObject;

import java.awt.*;

public class GridSpace<T> extends GameObject implements FactoryObject
{

    private Coordinate<Integer> gridCoords;

    private Coordinate<Float> worldCoords;

    private final float size = SQUARE_SIZE;

    private Color color;

    private T data;

    public GridSpace() {}

    public void Initialize()
    {
        this.worldCoords = ConvertGridToWorldSpace(gridCoords);
        this.color = SetColor();
        // Register with drawObject.
    }

    public void Deinitialize() {}

    public T GetData() { return data; }

    public Coordinate<Integer> GetGridCoords() { return gridCoords; }

    public Coordinate<Float> GetWorldCoords() { return worldCoords; }

    public void SetData(T item) { data = item; }

    public void SetCoordinates(Coordinate<Integer> coords)
    {
        gridCoords = coords;
        drawObject.SubmitDrawRegistration();
    }

    public void RemoveData()
    {
        data = null;
    }

    public boolean IsEmpty() { return data == null; }

    // A GridSpace's world space is different from an Entity's world space
    // GridSpace uses top left corner
    private Coordinate<Float> ConvertGridToWorldSpace(Coordinate<Integer> coords)
    {
        return new Coordinate<>(size * coords.GetX(), size * coords.GetY());
    }

    private Color SetColor()
    {
        if (gridCoords.GetY() % 2 == 0)
            return (gridCoords.GetX() % 2 == 0) ? Color.GRAY : Color.WHITE;
        else
            return (gridCoords.GetX() % 2 != 0) ? Color.WHITE : Color.GRAY;
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
