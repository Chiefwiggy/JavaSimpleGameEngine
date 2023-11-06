package Simulation_EXAMPLE.CustomRenderer;

import Engine.Rendering.GraphicsOptions;

import java.awt.*;
import java.awt.image.BufferedImage;

public class TextRenderer extends GraphicsOptions {

    public TextRenderer(BufferedImage image) {
        super(image);
    }
    @Override
    protected void SetRenderingHints() {
        g2.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_LCD_HRGB);
        g2.setRenderingHint(RenderingHints.KEY_STROKE_CONTROL, RenderingHints.VALUE_STROKE_NORMALIZE);
    }
}
