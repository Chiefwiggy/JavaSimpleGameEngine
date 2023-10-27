package Engine.GameObjects;

import java.awt.*;
import java.awt.image.BufferedImage;

public class GraphicsObject {

    protected BufferedImage image;

    public void Render(Graphics g) {
        g.drawImage(image, 0, 0, null);
        g.dispose();
    }


}
