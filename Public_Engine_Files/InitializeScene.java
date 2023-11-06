package Public_Engine_Files;

import Engine.Helpers.Scene;
import Engine.ResourceManagement.ImageManager;
import Engine.ResourceManagement.SpriteSheetManager;
import Game_Files.SampleScene;
import Simulation_EXAMPLE.MyFirstScene;

public class InitializeScene {
    public Scene getStartingScene() {
        // PUT YOUR SCENE HERE
        return new MyFirstScene();
    }

    public void LoadAssets() {
        ImageManager.Load("ant", "penguin_feature.jpg");
        ImageManager.LoadPartial("pant", "penguin_feature.jpg", 0, 0, 300, 300);
        SpriteSheetManager.LoadSpriteSheet("spant", "penguin_feature.jpg", 3, 3);
        SpriteSheetManager.LoadSpriteSheet("adventurer_idle", "adventurer_sprite_sheet_v1.1.png", 1, 13, 32, 32);
        SpriteSheetManager.LoadSpriteSheet("adventurer_walk", "adventurer_sprite_sheet_v1.1.png", 1, 8, 32, 32, 0, 32);
        SpriteSheetManager.LoadSpriteSheet("adventurer_strike", "adventurer_sprite_sheet_v1.1.png", 1, 10, 32, 32, 0, 64);
        SpriteSheetManager.LoadSpriteSheet("adventurer_slash", "adventurer_sprite_sheet_v1.1.png", 1, 10, 32, 32, 0, 96);
        SpriteSheetManager.LoadSpriteSheet("adventurer_slash_2", "adventurer_sprite_sheet_v1.1.png", 1, 10, 32, 32, 0, 128);
        SpriteSheetManager.LoadSpriteSheet("adventurer_jump", "adventurer_sprite_sheet_v1.1.png", 1, 6, 32, 32, 0, 160);
    }
}
