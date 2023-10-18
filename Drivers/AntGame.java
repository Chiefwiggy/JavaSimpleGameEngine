package Drivers;

import Managers.GameManager;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

public class AntGame extends JComponent {


    private Graphics2D g2;
    private BufferedImage image;

    private final int FPS = 60;
    private final int TARGET_TIME = 1000000000 / FPS;

    private int width;
    private int height;
    private Thread thread;
    private boolean start = true;

    private float deltaTime = 0;

    public float getDeltaTime() {return deltaTime;}

    public void start() {
        width = getWidth();
        height = getHeight();
        image = new BufferedImage(width | 800, height | 800, BufferedImage.TYPE_INT_ARGB);
        g2 = image.createGraphics();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
        thread = new Thread(new Runnable() {
            @Override
            public void run() {
                while(start) {
                    long startTime = System.nanoTime();
                    runUpdate();
                    drawBackground();
                    drawGame();
                    render();

                    if (deltaTime < TARGET_TIME) {
                        long sleepTime = Math.max((TARGET_TIME-(System.nanoTime() - startTime))/1000000, 0);
                        sleep(sleepTime);
                        //System.out.println(sleepTime);
                    }
                    deltaTime = Math.max((System.nanoTime() - startTime) / 1000000000f, (1f/FPS));
                }
            }
        });
        GameManager.Start(this);
        thread.start();
    }

    private void drawBackground() {
        g2.setColor(new Color(30, 30, 30));
        g2.fillRect(0,0,width, height);
    }

    private void drawGame() {
        GameManager.Draw(g2);
    }

    private void render() {
        Graphics g=getGraphics();
        g.drawImage(image, 0, 0, null);
        g.dispose();
        GameManager.Render(g);
    }

    private void runUpdate() {
        GameManager.Update();
    }


    private void sleep(long speed) {
        try {
            Thread.sleep(speed);
        } catch (InterruptedException ex) {
            System.err.println(ex);
        }
    }
}
