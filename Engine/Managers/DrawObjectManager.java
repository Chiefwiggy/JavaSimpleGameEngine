package Engine.Managers;

import Engine.GameObjects.DrawObject;
import Engine.Rendering.DrawSettings;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class DrawObjectManager {

    private List<DrawObject> drawList;

    public DrawObjectManager() {
        drawList = new ArrayList<>();
    }

    public void Register(DrawObject dob) {
        drawList.add(dob);
    }

    public void Deregister(DrawObject dob) {
        drawList.remove(dob);
    }

    public void ProcessElements(DrawSettings ds) {
        for (DrawObject dob : drawList) {
            dob.Draw(ds);
        }
    }

    public void RenderElements(Graphics g) {
        for (DrawObject dob : drawList) {
            dob.Render(g);
        }
    }
}
