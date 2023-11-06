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
    alarmObject.SetAlarmRegistration(DESTROY_TIME, ALARM_ID.ALARM_0);
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
      alarmObject.SetAlarmRegistration(DESTROY_TIME, ALARM_ID.ALARM_0);
  }
```

## Other Functions

- `EngineManager.GetDeltaTime() -> float` (Returns in seconds the time between frames)
- `EngineManager.GetHeight() -> int` (gets window height)
- `EngineManager.GetWidth() -> int` (gets window width)

## Public Engine Files
Within the package Public_Engine_Files, there is an assets folder where you will put the assets for your project. In future, this folder will be reassignable.

Additionally, there is a single class called InitializeScene.


```java
public class InitializeScene {
    public Scene getStartingScene() {
        // PUT YOUR SCENE HERE
        return new MyFirstScene();
    }

    public void LoadAssets() {
        ImageManager.Load("ant", "penguin_feature.jpg");
        ImageManager.LoadPartial("pant", "penguin_feature.jpg", 0, 0, 300, 300);
        SpriteSheetManager.LoadSpriteSheet("spant", "penguin_feature.jpg", 3, 3);
        SpriteSheetManager.LoadSpriteSheet("adventurer_idle", "adventurer_sprite_sheet_v1.1.png", 1, 13, 32, 32);
        SpriteSheetManager.LoadSpriteSheet("adventurer_walk", "adventurer_sprite_sheet_v1.1.png", 1, 8, 32, 32, 0, 32);
    }
}
```
There are two methods to keep mind of.
`getStartingScene()` is a method where you will pass your own Scene inherited from the Scene class.
`LoadAssets()` is where you will load the image assets your program uses.

### Image Manager
The `ImageManager` is a class which stores and manages the static images you want to use in your program.

#### ImageManager.Load()
```java
  ImageManager.Load([KEY], [PATH])
```
This will load the entire image and store it with the key provided.
#### ImageManager.LoadPartial()
```java
  ImageManager.LoadPartial([KEY], [PATH], [LEFT-X], [TOP-Y], [WIDTH], [HEIGHT])
```
This will load only the part of an image that you desire, storing it with the key provided.

#### ImageManager.Get
```java
  ImageManager.Get([KEY]);
```
This will return a BufferedImage with the corresponding key.

### SpriteSheetManager
The sprite sheet manager will manage images which have multiple sprites on them. These images will not be stored in the image manager.
#### SpriteSheetManager.LoadSpriteSheet()
```java
  SpriteSheetManager.LoadSpriteSheet([KEY], [PATH], [ROWS], [COLUMNS])
  SpriteSheetManager.LoadSpriteSheet([KEY], [PATH], [ROWS], [COLUMNS], [SPRITE_HEIGHT], [SPRITE_WIDTH])
  SpriteSheetManager.LoadSpriteSheet([KEY], [PATH], [ROWS], [COLUMNS], [SPRITE_HEIGHT], [SPRITE_WIDTH], [START_X], [START_Y])
```
This will load a SpriteSheet into memory.

#### SpriteSheetManager.Get()
```java
  SpriteSheetManager.Get([KEY]);
```
This will return a SpriteSheet with the corresponding key.

### SpriteSheet
This is a class which has the following methods

#### SpriteSheet.GetSpriteCount()
This returns an Integer which displays the total number of frames (not indexes, subtract 1 for indexes)

#### SpriteSheet.GetSprite(index)
This returns a BufferedImage of the sprite at that index.

#### SpriteSheet(GetSprite(row, column)
This returns a BufferedImage of the sprite at that row and column (indexed-0).



### Scene

There is no Scene Management, but you will have to specify a scene in StartingObjects.java

### Simulation_EXAMPLE

The simulation folder contains samples and examples, but please create your own package and scene to start a project.


