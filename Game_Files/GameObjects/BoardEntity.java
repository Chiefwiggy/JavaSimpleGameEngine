package Game_Files.GameObjects;

import Engine.GameObjects.GameObject;
import Engine.Helpers.CONSTANTS;
import Engine.Managers.EngineManager;
import Engine.Misc.ALARM_ID;
import Engine.ResourceManagement.SpriteSheet;
import Game_Files.Helpers.Directions;
import Game_Files.Managers.GameManager;
import Game_Files.Managers.GridManager;

import java.awt.*;
import java.util.ArrayList;

import static Game_Files.GameObjects.Background.squareSize;
import static Game_Files.Helpers.Directions.*;
import static Game_Files.Helpers.Directions.values;

public abstract class BoardEntity extends GameObject {

    protected int gridX; protected int gridY;
    protected float worldX; protected float worldY;
    protected float goalX; protected float goalY;
    protected final float speed = 90f;
    protected boolean canMoveDiagonally;
    protected Color color;

    SpriteSheet current_sp;

    protected int spriteHeight;
    protected int spriteWidth;
    int currentAnimation = 0;
    Directions currentDirection = DOWN;

    public BoardEntity(int x, int y) {
        this.gridX = x; this.gridY = y;
        this.worldX = ConvertGridToWorldSpace(gridX);
        this.worldY = ConvertGridToWorldSpace(gridY);
        SetRenderer("pixel");
        alarmObject.SubmitAlarmRegistration(CONSTANTS.SECOND/4, ALARM_ID.ALARM_0);
    }

    public void Initialize(int x, int y) {
        GridManager.Register(this);
        this.gridX = x; this.gridY = y;
        drawObject.SubmitDrawRegistration();
        //updateObject.SubmitUpdateRegistration();
    }

    public void Deinitialize() {
        drawObject.SubmitDrawDeregistration();
        //updateObject.SubmitUpdateDeregistration();
        GridManager.Deregister(this);
    }

    public int[] GetCoords() { return new int[]{ this.gridX, this.gridY }; }
    public void SetCoords(int x, int y) {
        this.gridX = x; this.gridY = y;
        this.worldX = ConvertGridToWorldSpace(x);
        this.worldY = ConvertGridToWorldSpace(y);
    }

    public void Move() {
        ArrayList<int[]> availableSpots = GridManager.GetAvailableAdjacentSpots(this.gridX, this.gridY, this.canMoveDiagonally, false);
        if (!availableSpots.isEmpty()) {
            int[] spot = availableSpots.get(GameManager.random.nextInt(0, availableSpots.size()));
            GridManager.Deregister(this);
            this.SetCoords(spot[0], spot[1]);
            GridManager.Register(this);
        }
    }

    @Override
    public void GameDraw(Graphics2D g2) {
        int drawX = ConvertGridToWorldSpace(this.gridX) - this.spriteWidth / 2;
        int drawY = ConvertGridToWorldSpace(this.gridY) - this.spriteHeight / 2;
        g2.drawImage(current_sp.GetSprite(currentAnimation), drawX, drawY, this.spriteWidth, this.spriteHeight, null);
    }

    // Returns the center of the gridSpace
    public int ConvertGridToWorldSpace(int n) {
        return (int) squareSize * n + ((int) squareSize / 2);
    }

    @Override
    public void GameAlarm0() {
        currentAnimation++;
        int cols = current_sp.GetSpriteSheetCols();
        int dv = currentDirection.value;
        if (currentAnimation >= (dv * cols) + cols) { currentAnimation = dv * cols; }
        // 1/10 chance to change directions randomly
        if (GameManager.random.nextInt(0, 10) == 5) {
            this.currentDirection = values()[GameManager.random.nextInt(0, values().length)];
        }
        alarmObject.SubmitAlarmRegistration(CONSTANTS.SECOND/4, ALARM_ID.ALARM_0);
    }

    /*@Override
    public void GameUpdate() {
        if ((int) goalX != (int) worldX) {
            worldX += (int) (Math.signum(goalX - worldX)*Math.min(speed * EngineManager.GetDeltaTime(), Math.abs(goalX - worldX)));
        }
        if ((int) goalY != (int) worldY) {
            worldY += (int) (Math.signum(goalY - worldY)*Math.min(speed * EngineManager.GetDeltaTime(), Math.abs(goalY - worldY)));
        }
    }*/

}
