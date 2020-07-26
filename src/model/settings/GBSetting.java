package model.settings;


public class GBSetting extends GSetting<Boolean> implements BooleanSetting {

    public GBSetting(String settingName, Boolean value) {
        super(settingName, value);
    }

    public GBSetting(String settingName) {
        this(settingName, false);
    }

    @Override
    public void enable() {
        super.setValue(true);
    }

    @Override
    public void disable() {
        super.setValue(false);
    }

    @Override
    public Boolean value() {
        return super.value();
    }
}
