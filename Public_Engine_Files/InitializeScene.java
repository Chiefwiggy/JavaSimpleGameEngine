package Public_Engine_Files;

import Engine.Helpers.Scene;
import Engine.Rendering.DrawSettings;
import Engine.ResourceManagement.ImageManager;
import Engine.ResourceManagement.SpriteSheetManager;
import Game_Files.GameScene;
import Simulation_EXAMPLE.CustomRenderer.TextRenderer;

import java.awt.image.BufferedImage;

public class InitializeScene {
    public Scene getStartingScene() {
        return new GameScene();
    }

    public void LoadAssets() {
        SpriteSheetManager.LoadSpriteSheet("croc", "crocodile_sprite_sheet.png", 4, 3);
        SpriteSheetManager.LoadSpriteSheet("fish", "fish_sprite_sheet.png", 4, 3, 32, 32);
        SpriteSheetManager.LoadSpriteSheet("coral", "flower_sprite_sheet_v1-1.png", 1, 3);
    }

    public void AddRenderers(DrawSettings ds, BufferedImage image) {
        ds.AddSetting("text", new TextRenderer(image));
    }
}
