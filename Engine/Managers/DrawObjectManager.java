package Engine.Managers;

import Engine.GameObjects.DrawObject;

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

    public void ProcessElements(Graphics2D g2) {
        for (DrawObject dob : drawList) {
            dob.Draw(g2);
        }
    }

    public void RenderElements(Graphics g) {
        for (DrawObject dob : drawList) {
            dob.Render(g);
        }
    }
}
