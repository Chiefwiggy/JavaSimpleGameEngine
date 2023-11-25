package Engine.ResourceManagement;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class ImageManager {
    private static ImageManager instance;
    private Map<String, BufferedImage> imageMap;
    private BufferedImage nullImage;
    private String assetFolderPath = "Public_Engine_Files/Assets/";
    private synchronized static ImageManager getInstance() {
        if (instance == null) {
            instance = new ImageManager();
        }
        return instance;
    }

    private ImageManager() {
        imageMap = new HashMap<>();
        try {
            nullImage = ImageIO.read(new File("src/Engine/Misc/error-image-generic.png"));

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void Load(String key, String path) { getInstance()._Load(key, path); }
    public static void LoadPartial(String key, String path, int tx, int ty, int width, int height) { getInstance()._LoadPartial(key, path, tx, ty, width, height);}
    public static BufferedImage Get(String key) {return getInstance()._Get(key);}

    private void _Load(String key, String path) {
        try {
            BufferedImage image = ImageIO.read(new File(assetFolderPath + path));
            imageMap.put(key, image);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void _LoadPartial(String key, String path, int tx, int ty, int width, int height) {
        try {
            BufferedImage image = ImageIO.read(new File(assetFolderPath + path));
            BufferedImage croppedImage = image.getSubimage(tx, ty, width, height);
            imageMap.put(key, croppedImage);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private BufferedImage _Get(String key) {
        return imageMap.getOrDefault(key, nullImage);
    }


}
