package Engine.Managers;

import Engine.Drivers.AntGame;

import javax.swing.*;
import java.awt.*;

public class EngineManager {

    private static EngineManager instance;
    private static synchronized EngineManager getInstance() {
        if (instance == null) {
            instance = new EngineManager();
        }
        return instance;
    }

    public static void Start(AntGame ref) {getInstance()._Start(ref);}
    public static void Update() { getInstance()._Update();}
    public static void Draw(Graphics2D g2) { getInstance()._Draw(g2);}
    public static void Render(Graphics g) { getInstance()._Render(g);}



    private AntGame antGame;


    public static int GetWidth() {return getInstance().antGame.getWidth();}
    public static int GetHeight() {return getInstance().antGame.getHeight();}
    public static float GetDeltaTime() {return getInstance().antGame.getDeltaTime();}

    public static JPanel GetDrawPanel() {return getInstance().antGame.getJPanelRef();}


    private void _Start(AntGame ref) {

        antGame = ref;
    }

    private void _Update() {

    }

    private void _Draw(Graphics2D g2) {

    }

    private void _Render(Graphics g) {

    }

}
