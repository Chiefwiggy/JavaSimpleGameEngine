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
        PrintGrid();
    }

    private void InitializeGrid(int gridSize)
    {
        for (int row = 0; row < gridSize; row++)
        {
            for (int col = 0; col < gridSize; col++)
            {
                gridSpaces[row][col] = (GridSpace<T>) gridSpaceFactory.GetEntity();
                gridSpaces[row][col].SetCoordinates(new Coordinate<>(col, row));
                availableSpaces.Add(col, row);
            }
        }
    }

    public GridSpace<T> GetSpace(Coordinate<Integer> coord)
    {
        return gridSpaces[coord.GetY()][coord.GetX()];
    }

    public void SetData(GridSpace<T> gridSpace, T data)
    {
        gridSpace.SetData(data);
        availableSpaces.Remove(gridSpace.GetGridCoords().GetY(), gridSpace.GetGridCoords().GetX());
    }

    public T GetData(Coordinate<Integer> coord) { return GetSpace(coord).GetData(); }

    public GridSpace<T> GetRandomSpace()
    {
        System.out.println(availableSpaces.toString());
        Object[] randomValues = availableSpaces.GetRandom();
        return GetSpace(new Coordinate<>((int) randomValues[1], (int) randomValues[0]));
    }

    public int Size() { return size; }

    public void PrintGrid()
    {
        for (GridSpace<T>[] row : gridSpaces)
        {
            for (GridSpace<T> space : row)
            {
                System.out.println(space.toString());
            }
        }
    }

}
