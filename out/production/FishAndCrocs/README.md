# Java Game Engine

This is a sample project I made for my brother. It's nothing special, but it's a very simplified game engine to teach him about design patterns.

You do not require Maven/Gradle to compile. Within the Engine.Drivers folder, Driver.java contains the main function.

## Game Objects

The main component you'll be extending is GameObject.
GameObject has a couple of components:

- drawObject
- updateObject
- alarmObject

### DrawObject
This is an object which will be drawn to the screen. If you're using a GameObject, you can register it to the engine as such:

```java

public class Peanut extends GameObject {


  //constructor
  public Peanut() {
    //to register a game object to be drawn, call
    drawObject.SubmitDrawRegistration();
  }

  public void GameDraw(Graphics2D g2) {
    //this is where you put the code you want drawn every frame.
  }

  public void Hide() {
    drawObject.SubmitDrawDeregistration();
  }

  public void Show() {
    drawObject.SubmitDrawRegistration();
  }
}

```
if you attempt to Deregister an object that's already registered, or vice versa, an error will be thrown.

### UpdateObject

```java

public class Peanut extends GameObject {

  //constructor
  public Peanut() {
    //to register a game object to be updated, call
    updateObject.SubmitDrawRegistration();
  }

  public void GameUpdate() {
    //this is where you put the code you want updated every frame.
  }
}

```

### AlarmObject

```java

public class Peanut extends GameObject {

  //constructor

  private long DESTROY_TIME = 5 * CONSTANTS.SECOND;

  //can call this in any function.
  public void SetDespawn() {
    // register ALARM_0 to go off in DESTROY_TIME seconds.
    alarmObject.SetAlarmRegistration(DESTROY_TIME, AlarmObjectManager.ALARM_ID.ALARM_0);
  }

  @Override
  public void GameAlarm0() {
    //write the code you want to go off when the alarm goes off here.
  }
}

```

There are 3 alarms: ALARM_0, ALARM_1, ALARM_2
These alarms go off on the following functions

`public void GameAlarm0() {}`
`public void GameAlarm1() {}`
`public void GameAlarm2() {}`

You can also register an alarm to go off at the conclusion of another alarm:

```java
  @Override
  public void GameAlarm0() {
      alarmObject.SetAlarmRegistration(DESTROY_TIME, AlarmObjectManager.ALARM_ID.ALARM_0);
  }
```

## Other Functions

- `EngineManager.GetDeltaTime() -> float` (Returns in seconds the time between frames)
- `EngineManager.GetHeight() -> int` (gets window height)
- `EngineManager.GetWidth() -> int` (gets window width)


### Scene

There is no Scene Management, but you will have to specify a scene in StartingObjects.java

### Simulation_EXAMPLE

The simulation folder contains samples and examples, but please create your own package and scene to start a project.


