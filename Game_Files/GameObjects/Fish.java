package Game_Files.GameObjects;

import Engine.ResourceManagement.SpriteSheetManager;
import java.awt.*;

public class Fish extends BoardEntity{

    public Fish(int x, int y) {
        super(x, y);
        this.canMoveDiagonally = false;
        this.color = new Color(0, 102, 204);

        this.current_sp = SpriteSheetManager.Get("fish");

        this.spriteHeight = (int) (this.current_sp.GetSpriteHeight() / 1.1);
        this.spriteWidth = (int) (this.current_sp.GetSpriteWidth() / 1.1);
    }

}
