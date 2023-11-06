package Engine.Rendering;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DrawSettings {

    Map<String, GraphicsOptions> settingsList;
    GraphicsOptions defaultRenderer;

    public DrawSettings() {
        settingsList = new HashMap<>();
    }

    public void AddSetting(String key, GraphicsOptions opt) {
        settingsList.put(key, opt);
        if (defaultRenderer == null) {
            defaultRenderer = opt;
        }
    }

    public void SetDefaultRenderer(String key) {
        defaultRenderer = settingsList.get(key);
    }

    public GraphicsOptions GetRenderer(String key) {
        return settingsList.getOrDefault(key, defaultRenderer);
    }

    public GraphicsOptions GetDefaultRenderer() {
        return defaultRenderer;
    }
}
