package Engine.Rendering;

import java.awt.*;
import java.awt.image.BufferedImage;

public abstract class GraphicsOptions {

    public Graphics2D g2;

    public GraphicsOptions(BufferedImage image) {
        g2 = image.createGraphics();
        SetRenderingHints();
    }

    protected abstract void SetRenderingHints();
}
