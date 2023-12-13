package Game_Files.Managers;

import Engine.Managers.EngineManager;
import Game_Files.GameObjects.EntityObjects.EntityObject;
import Game_Files.Helpers.TwoDimensionalGrid;


// This will manage adding and removing entities on the grid
public class GridManager
{

    private GridManager() { gridSize = 10; }

    private static GridManager instance;

    private synchronized static GridManager getInstance()
    {
        if (instance == null) { instance = new GridManager(); }
        return instance;
    }

    public static void Initialize() { getInstance()._Initialize(); }

    public static int GetGridSize() { return getInstance()._GetGridSize(); }

    public static float GetGridSpaceSize() { return getInstance()._GetGridSpaceSize(); }

    public static void FillGridSpace(EntityObject entity) { getInstance()._FillGridSpace(entity); }

    private TwoDimensionalGrid<EntityObject> grid;

    private final int gridSize;

    private void _Initialize()
    {
        grid = new TwoDimensionalGrid<>(gridSize);
    }

    public int _GetGridSize() { return grid.Size(); }

    public float _GetGridSpaceSize() { return (float) EngineManager.GetWidth() / gridSize; }

    private void _FillGridSpace(EntityObject entity)
    {
        //grid.SetData(entity.);
    }

}
