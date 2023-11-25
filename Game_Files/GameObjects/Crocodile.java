package Game_Files.GameObjects;

import Engine.ResourceManagement.SpriteSheetManager;
import Game_Files.Managers.GameManager;
import Game_Files.Managers.GridManager;

import java.awt.*;
import java.util.ArrayList;

public class Crocodile extends BoardEntity{

    protected int fedMeter;

    public Crocodile(int x, int y) {
        super(x, y);
        this.canMoveDiagonally = true;
        this.color = new Color(0, 102, 51);
        this.fedMeter = 0;

        this.current_sp = SpriteSheetManager.Get("croc");

        // This could replace our function, but we already need the column function, so it's fine.
        // current_sp.GetSprite(0).getHeight();

        this.spriteHeight = (int) (this.current_sp.GetSpriteHeight() / 1.6);
        this.spriteWidth = (int) (this.current_sp.GetSpriteWidth() / 1.6);

    }

    @Override
    public void Move() {
        ArrayList<int[]> availableSpots = GridManager.GetAvailableAdjacentSpots(this.x, this.y, false, true);
        if (!availableSpots.isEmpty()) {
            int[] spot = availableSpots.get(GameManager.random.nextInt(0, availableSpots.size()));
            GridManager.Deregister(this);
            this.Eat(spot[0], spot[1]);
            this.SetCoords(spot[0], spot[1]);
            GridManager.Register(this);
        } else { super.Move(); }
    }

    private void Eat(int x, int y) {
        GridManager.ClearGridSpace(x, y);
        this.fedMeter += 3;
    }

}
