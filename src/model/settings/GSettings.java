package model.settings;

public interface GSettings {

    void addSetting(GSetting s);

    void removeSetting(String settingName);

    void enableAll();

    void disableAll();

    GSetting get(String settingName);

    BooleanSetting getBooleanSetting(String settingName);
}
