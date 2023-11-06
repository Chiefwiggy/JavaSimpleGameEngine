package Engine.Managers;

import Engine.Commands.RegistrationCommand;
import Engine.Drivers.AntGame;
import Engine.GameObjects.AlarmObject;
import Engine.GameObjects.DrawObject;
import Engine.GameObjects.UpdateObject;
import Engine.Helpers.Multimap;
import Engine.Helpers.Scene;
import Engine.Misc.ALARM_ID;
import Engine.Rendering.DrawSettings;
import Public_Engine_Files.InitializeScene;

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
    public static void Draw(DrawSettings ds) { getInstance()._Draw(ds);}
    public static void Render(Graphics g) { getInstance()._Render(g);}

    private DrawObjectManager drawManager;
    private CommandBroker commandBroker;
    private UpdateObjectManager updateManager;
    private AlarmObjectManager alarmManager;

    private InitializeScene startingObjects;


    private AntGame antGame;

    private Scene currentScene;

    public static void Register(DrawObject dob) {
        getInstance().drawManager.Register(dob);
    }

    public static void Deregister(DrawObject dob) {
        getInstance().drawManager.Deregister(dob);
    }

    public static void Register(UpdateObject uob) { getInstance().updateManager.Register(uob); }

    public static void Deregister(UpdateObject uob) {
        getInstance().updateManager.Deregister(uob);
    }

    public static Multimap.MultimapIterator Register(long t, ALARM_ID id, AlarmObject aob) { return getInstance().alarmManager.Register(t, id, aob); }

    public static void Deregister(Multimap.MultimapIterator it) {
        getInstance().alarmManager.Deregister(it);
    }
    public static void SubmitCommand(RegistrationCommand pCmd) {
        getInstance().commandBroker.AddCommand(pCmd);
    }

    public static int GetWidth() {return getInstance().antGame.getWidth();}
    public static int GetHeight() {return getInstance().antGame.getHeight();}
    public static float GetDeltaTime() {return getInstance().antGame.getDeltaTime();}

    public static JPanel GetDrawPanel() {return getInstance().antGame.getJPanelRef();}


    private void _Start(AntGame ref) {

        drawManager = new DrawObjectManager();
        commandBroker = new CommandBroker();
        updateManager = new UpdateObjectManager();
        alarmManager = new AlarmObjectManager();
        startingObjects = new InitializeScene();
        currentScene = startingObjects.getStartingScene();
        startingObjects.LoadAssets();
        startingObjects.AddRenderers(ref.drawSettings, ref.image);
        currentScene.Initialize();
        antGame = ref;
    }

    private void _Update() {
        commandBroker.ExecuteCommands();

        alarmManager.ProcessAlarms();

        updateManager.ProcessElements();
    }

    private void _Draw(DrawSettings ds) {
        drawManager.ProcessElements(ds);
    }

    private void _Render(Graphics g) {
        drawManager.RenderElements(g);
    }

}
