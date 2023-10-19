package Managers;

import Commands.RegistrationCommand;
import Drivers.AntGame;
import GameObjects.AlarmObject;
import GameObjects.DrawObject;
import GameObjects.UpdateObject;
import Helpers.Multimap;
import Helpers.Scene;
import zGame2.StartingObjects;

import java.awt.*;

public class GameManager {

    private static GameManager instance;
    private static synchronized GameManager getInstance() {
        if (instance == null) {
            instance = new GameManager();
        }
        return instance;
    }

    public static void Start(AntGame ref) {getInstance()._Start(ref);}
    public static void Update() { getInstance()._Update();}
    public static void Draw(Graphics2D g2) { getInstance()._Draw(g2);}
    public static void Render(Graphics g) { getInstance()._Render(g);}

    private DrawObjectManager drawManager;
    private CommandBroker commandBroker;
    private UpdateObjectManager updateManager;
    private AlarmObjectManager alarmManager;

    private StartingObjects startingObjects;


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

    public static Multimap.MultimapIterator Register(long t, AlarmObjectManager.ALARM_ID id, AlarmObject aob) { return getInstance().alarmManager.Register(t, id, aob); }

    public static void Deregister(Multimap.MultimapIterator it) {
        getInstance().alarmManager.Deregister(it);
    }
    public static void SubmitCommand(RegistrationCommand pCmd) {
        getInstance().commandBroker.AddCommand(pCmd);
    }

    public static AntGame GetAntGame() { return getInstance().antGame; }


    private void _Start(AntGame ref) {

        drawManager = new DrawObjectManager();
        commandBroker = new CommandBroker();
        updateManager = new UpdateObjectManager();
        alarmManager = new AlarmObjectManager();
        startingObjects = new StartingObjects();
        currentScene = startingObjects.getStartingScene();
        currentScene.Initialize();
        antGame = ref;
    }

    private void _Update() {
        commandBroker.ExecuteCommands();

        alarmManager.ProcessAlarms();

        updateManager.ProcessElements();
    }

    private void _Draw(Graphics2D g2) {
        drawManager.ProcessElements(g2);
    }

    private void _Render(Graphics g) {
        drawManager.RenderElements(g);
    }

}
