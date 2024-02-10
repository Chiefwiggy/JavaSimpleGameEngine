package Game_Files.Helpers;

import Game_Files.Factories.GridSpaceFactory;
import Game_Files.GameObjects.GridSpace;

// Wraps our GridSpace Array with a corresponding adjacency map to perform methods succinctly
@SuppressWarnings("unchecked")
public class TwoDimensionalGrid<T> {

    private final GridSpace<T>[][] gridSpaces;

    private final AdjacencyMap<Integer, Integer> availableSpaces;

    private final GridSpaceFactory gridSpaceFactory;

    private final int size;

    public TwoDimensionalGrid(int gridSize)
    {
        size = gridSize;
        gridSpaces = new GridSpace[gridSize][gridSize];
        availableSpaces = new AdjacencyMap<>();
        gridSpaceFactory = new GridSpaceFactory();
        InitializeGrid(gridSize);
        //PrintGrid();
    }

    private void InitializeGrid(int gridSize)
    {
        for (int row = 0; row < gridSize; row++)
        {
            for (int col = 0; col < gridSize; col++)
            {
                gridSpaces[row][col] = (GridSpace<T>) gridSpaceFactory.GetEntity();
                gridSpaces[row][col].SetCoordinates(new Coordinate<>(row, col));
                availableSpaces.Add(row, col);
            }
        }
    }

    public GridSpace<T> GetSpace(Coordinate<Integer> coord)
    {
        return gridSpaces[coord.GetRow()][coord.GetCol()];
    }

    public void SetData(GridSpace<T> gridSpace, T data)
    {
        gridSpace.SetData(data);
        availableSpaces.Remove(gridSpace.GetGridCoords().GetRow(), gridSpace.GetGridCoords().GetCol());
    }

    //public T GetData(Coordinate<Integer> coord) { return GetSpace(coord).GetData(); }

    public void ClearData(GridSpace<T> gridSpace)
    {
        gridSpace.SetData(null);
        availableSpaces.Add(gridSpace.GetGridCoords().GetRow(), gridSpace.GetGridCoords().GetCol());
    }

    public GridSpace<T> GetRandomSpace()
    {
        //System.out.println(availableSpaces.toString());
        Object[] randomValues = availableSpaces.GetRandom();
        return GetSpace(new Coordinate<>((int) randomValues[0], (int) randomValues[1]));
    }

    //public int Size() { return size; }

    public void PrintGrid()
    {
        for (int row = 0; row < size; row++)
        {
            for (int col = 0; col < size; col++)
            {
                System.out.println(gridSpaces[row][col].toString());
            }
        }
    }

    public AdjacencyMap<Integer, Integer> GetMap() { return availableSpaces; }

}
