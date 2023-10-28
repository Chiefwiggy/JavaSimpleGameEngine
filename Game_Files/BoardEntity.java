package Game_Files;

import Engine.GameObjects.GameObject;

import java.awt.*;

import static Game_Files.Background.squareSize;

public abstract class BoardEntity extends GameObject {

    public int x;
    public int y;
    public int drawX;
    public int drawY;
    public String species;
    public double entitySizeDivisor;
    public BoardEntity(int x, int y, String species) {
        this.x = x;
        this.y = y;
        this.species = species;
        if (species.equals("Croc")) { this.entitySizeDivisor = 1.5; }
        else { this.entitySizeDivisor = 3.0; }
    }

    public void Initialize(int x, int y, String species) {
        GameManager.Register(this);
        this.x = x;
        this.y = y;
        this.species = species;
        if (!species.equals("Coral")) { drawObject.SubmitDrawRegistration(); }
    }

    public void Deinitialize() {
        drawObject.SubmitDrawDeregistration();
        GameManager.Deregister(this);
    }

    @Override
    public void GameDraw(Graphics2D g2) {
        drawX = convertGridToWorldSpace(x) - (int) (squareSize / this.entitySizeDivisor / 2);
        drawY = convertGridToWorldSpace(y) - (int) (squareSize / this.entitySizeDivisor / 2);
    }

    // Returns the center of the gridSpace
    protected int convertGridToWorldSpace(int n) {
        return (int) squareSize * n + ((int) squareSize / 2);
    }

}
