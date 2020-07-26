package model.settings;

import java.util.HashMap;

public class Settings extends HashMap<String, GSetting> implements GSettings {

    public Settings() {

    }

    public Settings(GSetting... settings) {
        for (GSetting s : settings) {
            addSetting(s);
        }
    }

    public Settings(Iterable<GSetting> it) {
        for (GSetting s : it) {
            addSetting(s);
        }
    }

    @Override
    public void addSetting(GSetting s) {
        put(s.getName(), s);
    }

    @Override
    public void addSetting(Object key, Object value) {
        addSetting(new GSetting(key, value));
    }

    @Override
    public void removeSetting(String settingName) {
        remove(settingName);
    }

    @Override
    public void enableAll() {
        for (GSetting s : this.values()) {
            if (s instanceof BooleanSetting)
                ((BooleanSetting) s).enable();
        }
    }

    @Override
    public void disableAll() {
        for (GSetting s : this.values()) {
            if (s instanceof BooleanSetting)
                ((BooleanSetting) s).disable();
        }
    }

    @Override
    public GSetting get(Object o) {
        return get(o.toString());
    }

    @Override
    public GSetting get(String settingName) {
        GSetting s = super.get(settingName);

        if (s == null) {
            try {
                throw new SettingException(String.format("setting \"%s\" does not exist", settingName));
            } catch (SettingException e) {
                e.printStackTrace();
            }
        }

        return s;
    }

    @Override
    public BooleanSetting getBooleanSetting(String settingName) {
        return (BooleanSetting) get(settingName);
    }
}
