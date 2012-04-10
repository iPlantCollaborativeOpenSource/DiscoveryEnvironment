package org.iplantc.de.client.models;

import org.iplantc.core.jsonutil.JsonUtil;

import com.extjs.gxt.ui.client.data.BaseModelData;
import com.google.gwt.json.client.JSONObject;
import com.google.gwt.json.client.JSONString;

/**
 * Base class for window configurations. A window configuration is specific to a type of window
 * (Notifications, Analysis, etc.) and describes how a window should be presented. For example, data
 * could be filtered, or certain UI elements could be disabled.
 * 
 * @author hariolf, Paul
 * 
 */
public abstract class WindowConfig extends BaseModelData {
    private static final long serialVersionUID = 3602295075858973528L;
    public static final String IS_MAXIMIZED = "isMaximized";
    public static final String IS_MINIMIZED = "isMinimized";
    public static final String WIN_LEFT = "win_left";
    public static final String WIN_TOP = "win_top";
    public static final String WIN_WIDTH = "width";
    public static final String WIN_HEIGHT = "height";

    /**
     * Constructs a WindowConfig and adds all JSON key/value pairs as BaseModelData parameters.
     * 
     * @param json
     */
    protected WindowConfig(JSONObject json) {
        for (String key : json.keySet()) {
            String value = JsonUtil.getRawValueAsString(json.get(key));

            set(key, value);
        }
    }

    /**
     * Returns a string that identifies the window instance, if appropriate. This implementation returns
     * an empty string.
     * 
     * @return
     */
    public String getTagSuffix() {
        return ""; //$NON-NLS-1$
    }

    public JSONObject toJson() {
        JSONObject json = new JSONObject();

        for (String key : getPropertyNames()) {
            String value = get(key);
            if (value != null) {
                json.put(key, new JSONString(value));
            }
        }

        return json;
    }

    public boolean isWindowMinimized() {
        if (get(IS_MINIMIZED) != null) {
            return Boolean.parseBoolean(get(IS_MINIMIZED).toString());
        }

        return false;
    }

    public boolean isWindowMaximized() {
        if (get(IS_MAXIMIZED) != null) {
            return Boolean.parseBoolean(get(IS_MAXIMIZED).toString());
        }

        return false;
    }
}
