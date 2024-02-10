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

    public static void PrintGrid() { getInstance()._PrintGrid(); }

    //public static float GetGridSize() { return getInstance()._GetGridSize(); }

    public static float GetGridSpaceSize() { return getInstance()._GetGridSpaceSize(); }

    public static void FillGridSpace(GridSpace<EntityObject> gridSpace, EntityObject entity)
    {
        getInstance()._FillGridSpace(gridSpace, entity);
    }

    public static void ClearGridSpace(GridSpace<EntityObject> gridSpace)
    {
        getInstance()._ClearGridSpace(gridSpace);
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

    public void _PrintGrid() { grid.PrintGrid(); }

    //public int _GetGridSize() { return grid.Size(); }

    public float _GetGridSpaceSize() { return (float) worldSize / gridSize; }

    private void _FillGridSpace(GridSpace<EntityObject> gridSpace, EntityObject entity)
    {
        grid.SetData(gridSpace, entity);
        entity.SetCurrentGridSpace(gridSpace);
    }

    private void _ClearGridSpace(GridSpace<EntityObject> gridSpace)
    {
        grid.ClearData(gridSpace);
    }

    private GridSpace<EntityObject> _GetGridSpace() {
        return grid.GetRandomSpace();
    }
    private GridSpace<EntityObject> _GetGridSpace(Coordinate<Integer> coord)
    {
        int row = coord.GetRow(), col = coord.GetCol();
        if ((row < 0 || row >= gridSize) || (col < 0 || col >= gridSize)) { return null; }
        GridSpace<EntityObject> gridSpace = grid.GetSpace(coord);
        if (!gridSpace.IsEmpty()) { return null; }
        else return gridSpace;
    }

}
