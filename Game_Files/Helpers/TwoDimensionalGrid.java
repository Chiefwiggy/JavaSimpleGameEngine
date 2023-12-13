package Game_Files.Helpers;

import Game_Files.Factories.GridSpaceFactory;
import Game_Files.GameObjects.GridSpace;
import java.util.function.Consumer;

// Wraps our GridSpace Array with a corresponding adjacency map to perform methods succinctly
@SuppressWarnings("unchecked")
public class TwoDimensionalGrid<T> {

    private final GridSpace<T>[] gridSpaces;

    private final AdjacencyMap<Integer, Integer> availableSpaces;

    private final GridSpaceFactory gridSpaceFactory;

    private final int size;

    public TwoDimensionalGrid(int gridSize)
    {
        size = gridSize;
        gridSpaces = new GridSpace[gridSize*gridSize];
        availableSpaces = new AdjacencyMap<>();
        gridSpaceFactory = new GridSpaceFactory();
        InitializeGrid(gridSize);
    }

    private void InitializeGrid(int gridSize) {
        for (int row = 0; row < gridSize; row++) {
            for (int col = 0; col < gridSize; col++) {
                int index = CoordinateToArrayIndex(new Coordinate(col, row));
                gridSpaces[index] = (GridSpace<T>) gridSpaceFactory.GetEntity();
            }
        }
    }

    public void SetData(Coordinate coord, T data)
    {
        gridSpaces[CoordinateToArrayIndex(coord)].SetData(data);
        availableSpaces.Remove(coord.GetY().intValue(), coord.GetX().intValue());
    }

    public T GetData(Coordinate coord)
    {
        return gridSpaces[CoordinateToArrayIndex(coord)].GetData();
    }

    public GridSpace<T> GetRandomSpace()
    {
        Integer[] randomValues = (Integer[]) availableSpaces.GetRandom();
        Coordinate coord = new Coordinate(randomValues[1], randomValues[0]);
        return gridSpaces[CoordinateToArrayIndex(coord)];
    }

    private int CoordinateToArrayIndex(Coordinate coord)
    {
        return Integer.parseInt(coord.GetX().toString() + coord.GetY().toString());
    }

    public int Size() { return size; }

    public void ForEach(Consumer<GridSpace<T>> action)
    {
        for (GridSpace<T> gridSpace : gridSpaces) { action.accept(gridSpace); }
    }

}
