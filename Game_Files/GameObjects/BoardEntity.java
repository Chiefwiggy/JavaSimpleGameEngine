package Game_Files.GameObjects;

import Engine.GameObjects.GameObject;
import Game_Files.Helpers.BoardEntities;
import Game_Files.Helpers.Pair;
import Game_Files.Managers.BoardEntityManager;

import java.awt.*;

import static Game_Files.GameObjects.Background.squareSize;

public abstract class BoardEntity extends GameObject {

    public Pair<Integer> xy;
    public Pair<Integer> drawXY;
    public BoardEntities species;
    public double entitySizeDivisor;
    public BoardEntity(Pair<Integer> xy, BoardEntities species) {
        this.xy = xy;
        this.species = species;
        if (species == BoardEntities.CROCODILE) { this.entitySizeDivisor = 1.5; }
        else { this.entitySizeDivisor = 3.0; }
    }

    public void Initialize(Pair<Integer> xy, BoardEntities species) {
        BoardEntityManager.Register(this);
        this.xy = xy;
        this.species = species;
        if (species != BoardEntities.CORAL) { drawObject.SubmitDrawRegistration(); }
    }

    public void Deinitialize() {
        drawObject.SubmitDrawDeregistration();
        BoardEntityManager.Deregister(this);
    }

    @Override
    public void GameDraw(Graphics2D g2) {
        drawXY = new Pair<>(
        convertGridToWorldSpace(xy.get(0)) - (int) (squareSize / this.entitySizeDivisor / 2),
        convertGridToWorldSpace(xy.get(1)) - (int) (squareSize / this.entitySizeDivisor / 2)
        );
    }

    // Returns the center of the gridSpace
    protected int convertGridToWorldSpace(int n) {
        return (int) squareSize * n + ((int) squareSize / 2);
    }

}
