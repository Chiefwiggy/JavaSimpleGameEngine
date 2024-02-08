package Game_Files.Managers;

import Game_Files.GameObjects.EntityObjects.EntityObject;
import Game_Files.GameObjects.GridSpace;
import Game_Files.Helpers.Coordinate;
import Game_Files.Helpers.TwoDimensionalGrid;


// This will manage adding and removing entities on the grid
public class GridManager
{

    private GridManager() { gridSize = 10; worldSize = 600; }

    private static GridManager instance;

    private synchronized static GridManager getInstance()
    {
        if (instance == null) { instance = new GridManager(); }
        return instance;
    }

    public static void Initialize() { getInstance()._Initialize(); }

    public static float GetGridSize() { return getInstance()._GetGridSize(); }

    public static float GetGridSpaceSize() { return getInstance()._GetGridSpaceSize(); }

    public static void FillGridSpace(GridSpace<EntityObject> gridSpace, EntityObject entity)
    {
        getInstance()._FillGridSpace(gridSpace, entity);
    }

    public static GridSpace<EntityObject> GetGridSpace(Coordinate<Integer> coord)
    {
        return getInstance()._GetGridSpace(coord);
    }

    public static GridSpace<EntityObject> GetGridSpace() { return getInstance()._GetGridSpace(); }

    private TwoDimensionalGrid<EntityObject> grid;

    private final int gridSize;

    private final int worldSize;

    private void _Initialize()
    {
        grid = new TwoDimensionalGrid<>(gridSize);
    }

    public int _GetGridSize() { return grid.Size(); }

    public float _GetGridSpaceSize() { return (float) worldSize / gridSize; }

    private void _FillGridSpace(GridSpace<EntityObject> gridSpace, EntityObject entity)
    {
        grid.SetData(gridSpace, entity);
        entity.SetCurrentGridSpace(gridSpace);
    }

    private GridSpace<EntityObject> _GetGridSpace() {
        return grid.GetRandomSpace();
    }
    private GridSpace<EntityObject> _GetGridSpace(Coordinate<Integer> coord)
    {
        int x = coord.GetX(), y = coord.GetY();
        if ((x < 0 || x >= gridSize) || (y < 0 || y >= gridSize)) { return null; }
        else return grid.GetSpace(coord);
    }

}
