package Engine.ResourceManagement;

import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

public class SpriteSheet {

    private final BufferedImage spriteSheet;
    private final int rows, cols;
    private final int spriteHeight, spriteWidth;
    private final int maxElems;

    private ArrayList<BufferedImage> spriteList;
    public SpriteSheet(BufferedImage image, int rows, int columns) {
        this.spriteSheet = image;
        this.rows = rows;
        this.cols = columns;
        this.spriteHeight = Math.floorDiv(spriteSheet.getHeight(), rows);
        this.spriteWidth = Math.floorDiv(spriteSheet.getWidth(), columns);
        this.maxElems = rows*columns;
        this.spriteList = null;
    }

    public ArrayList<BufferedImage> GetSpriteArray() {
        return getSpriteList();
    }

    public BufferedImage GetSprite(int index) throws ArrayIndexOutOfBoundsException {
        if (index >= maxElems) {
            throw new ArrayIndexOutOfBoundsException("Sprite Index Out of Bounds");
        }
        return getSpriteList().get(index);
    }

    public BufferedImage GetSprite(int row, int column) throws ArrayIndexOutOfBoundsException {
        if (row >= this.rows) {
            throw new ArrayIndexOutOfBoundsException("Row " + row + " is not present within spritesheet. (Max Rows: " + (this.rows-1) + ") Make sure you're indexed from 0.");
        }
        if (column >= this.cols) {
            throw new ArrayIndexOutOfBoundsException("Column " + column + " is not present within spritesheet. (Max Columns: " + (this.cols-1) + ") Make sure you're indexed from 0.");
        }
        return GetSprite(row*this.cols + column);
    }

    public int GetSpriteCount() {
        return maxElems;
    }

    private ArrayList<BufferedImage> getSpriteList() {
        if (spriteList == null) {
            spriteList = new ArrayList<>();
            IntStream.range(0, rows*cols).forEachOrdered(index -> {
                int currentRow = Math.floorDiv(index, cols);
                int currentColumn = index % cols;
                BufferedImage currentSprite = spriteSheet.getSubimage(spriteWidth*currentColumn,spriteHeight*currentRow, spriteWidth, spriteHeight);
                spriteList.add(index, currentSprite);
            });
        }
        return spriteList;
    }

    public int GetSpriteWidth() { return this.spriteWidth; }
    public int GetSpriteHeight() { return this.spriteHeight; }
    public int GetSpriteSheetCols() { return this.cols; }
    public int GetSpriteSheetRows() { return this.rows; }

}
