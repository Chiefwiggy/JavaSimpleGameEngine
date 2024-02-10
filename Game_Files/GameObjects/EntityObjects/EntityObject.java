package Game_Files.GameObjects.EntityObjects;

import Engine.GameObjects.GameObject;
import Engine.Helpers.*;
import Engine.Misc.ALARM_ID;
import Game_Files.GameObjects.GridSpace;
import Game_Files.Helpers.Sprite;
import Game_Files.Enums.EntityObjects;
import Game_Files.Interfaces.FactoryObject;
import Game_Files.Managers.GridManager;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public abstract class EntityObject extends GameObject implements FactoryObject
{

    protected GridSpace<EntityObject> currentGridSpace;

    public EntityObjects species;

    protected Sprite sprite;

    protected final Random random = new Random();

    public EntityObject() { SetRenderer("pixel"); }

    public void Initialize()
    {
        alarmObject.SubmitAlarmRegistration(CONSTANTS.SECOND/4, ALARM_ID.ALARM_0);
        drawObject.SubmitDrawRegistration();
    }

    public void Deinitialize()
    {
        alarmObject.SubmitAlarmDeregistration(ALARM_ID.ALARM_0);
        drawObject.SubmitDrawDeregistration();
        currentGridSpace.RemoveData();
        currentGridSpace = null;
    }

    public GridSpace<EntityObject> GetCurrentGridSpace() { return currentGridSpace; }

    public void SetCurrentGridSpace(GridSpace<EntityObject> gridSpace)
    {
        currentGridSpace = gridSpace;
    }

    public ArrayList<GridSpace<EntityObject>> GetAdjacentSpaces()
    {
        return currentGridSpace.GetAdjacentSpaces();
    }

    public void Move()
    {
        List<GridSpace<EntityObject>> availableSpaces = GetAdjacentSpaces();
        if (!availableSpaces.isEmpty())
        {
            GridSpace<EntityObject> space = availableSpaces.get(random.nextInt(availableSpaces.size()));
            GridManager.ClearGridSpace(currentGridSpace);
            SetCurrentGridSpace(null);
            GridManager.FillGridSpace(space, this);
            //System.out.println("Fish moved to: " + space.GetGridCoords().toString());
        }
    }

    @Override
    public void GameDraw(Graphics2D g2)
    {
        sprite.DrawSprite(g2, currentGridSpace.GetGridCoords());
    }

    @Override
    public void GameAlarm0()
    {
        sprite.UpdateSprite();
        alarmObject.SubmitAlarmRegistration(CONSTANTS.SECOND/4, ALARM_ID.ALARM_0);
    }

}
