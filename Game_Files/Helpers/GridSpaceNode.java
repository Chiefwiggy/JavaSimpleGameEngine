package Game_Files.Helpers;

public class GridSpaceNode {

    protected int x, y;
    protected GridSpaceNode topLevelParent;

    public GridSpaceNode(int x, int y, GridSpaceNode topLevelParent) {
        this.x = x; this.y = y;
        this.topLevelParent = topLevelParent;
    }
    
}
