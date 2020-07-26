package model.settings;

public interface GSettings {

    void addSetting(GSetting s);

    void addSetting(Object key, Object value);

    void removeSetting(String settingName);

    void enableAll();

    void disableAll();

    GSetting get(Object o);

    GSetting get(String settingName);

    BooleanSetting getBooleanSetting(String settingName);
}
