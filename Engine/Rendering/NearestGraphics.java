package Engine.Rendering;

import java.awt.*;
import java.awt.image.BufferedImage;

public class NearestGraphics extends GraphicsOptions{

    public NearestGraphics(BufferedImage image) {
        super(image);
    }

    @Override
    protected void SetRenderingHints() {
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_NEAREST_NEIGHBOR);
    }


}
