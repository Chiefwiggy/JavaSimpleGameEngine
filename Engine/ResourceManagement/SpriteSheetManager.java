package Engine.ResourceManagement;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class SpriteSheetManager {
    private static SpriteSheetManager instance;
    private String assetFolderPath = "Public_Engine_Files/Assets/";

    private synchronized static SpriteSheetManager getInstance() {
        if (instance == null) {
            instance = new SpriteSheetManager();
        }
        return instance;
    }

    private Map<String, SpriteSheet> spriteSheetMap;


    private SpriteSheetManager() {
        spriteSheetMap = new HashMap<>();
    }

    public static void LoadSpriteSheet(String key, String path, int rows, int columns) {
        getInstance()._LoadSpriteSheet(key, path, rows, columns);
    }

    public static void LoadSpriteSheet(String key, String path, int rows, int columns, int spriteHeight, int spriteWidth) {
        getInstance()._LoadSpriteSheet(key, path, rows, columns, spriteHeight, spriteWidth);
    }

    public static void LoadSpriteSheet(String key, String path, int rows, int columns, int spriteHeight, int spriteWidth, int startX, int startY) {
        getInstance()._LoadSpriteSheet(key, path, rows, columns, spriteHeight, spriteWidth, startX, startY);
    }

    public static SpriteSheet Get(String key) { return getInstance()._Get(key);}

    private void _LoadSpriteSheet(String key, String path, int rows, int columns) {
        try {
            BufferedImage image = ImageIO.read(new File(assetFolderPath + path));
            SpriteSheet spriteSheet = new SpriteSheet(image, rows, columns);
            spriteSheetMap.put(key, spriteSheet);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void _LoadSpriteSheet(String key, String path, int rows, int columns, int spriteHeight, int spriteWidth) {
        _LoadSpriteSheet(key, path, rows, columns, spriteHeight, spriteWidth, 0, 0);
    }

    private void _LoadSpriteSheet(String key, String path, int rows, int columns, int spriteHeight, int spriteWidth, int startX, int startY) {
        try {
            BufferedImage image = ImageIO.read(new File(assetFolderPath + path));
            BufferedImage croppedImage = image.getSubimage(startX,startY, columns*spriteWidth, rows*spriteHeight);
            SpriteSheet spriteSheet = new SpriteSheet(croppedImage, rows, columns);
            spriteSheetMap.put(key, spriteSheet);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private SpriteSheet _Get(String key) {
        return spriteSheetMap.getOrDefault(key, null);
    }


}
