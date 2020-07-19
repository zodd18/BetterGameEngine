package model.settings;

import java.util.Objects;

public abstract class GSetting<V> {

    private V value;

    private final String settingName;

    public GSetting(String settingName, V value) {
        this.settingName = settingName;
        this.value = value;
    }

    public String getSettingName() {
        return settingName;
    }

    public void setValue(V value) {
        this.value = value;
    }

    public V getCurrentValue() {
        return value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        GSetting<?> gSetting = (GSetting<?>) o;
        return Objects.equals(settingName, gSetting.settingName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(settingName);
    }
}
