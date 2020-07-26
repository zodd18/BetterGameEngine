package model.settings;

import model.generalInterfaces.Named;

import java.util.Objects;

public class GSetting<V> implements Named {

    private V value, initialValue;

    private String settingName;

    public GSetting(String settingName, V value) {
        this.settingName = settingName;
        this.initialValue = value;
        this.value = value;
    }

    public GSetting(Object o, V value) {
        this(o.toString(), value);
    }

    public void setInitialValue(V initialValue) {
        this.initialValue = initialValue;
    }

    public void setValue(V value) {
        this.value = value;
    }

    public V value() {
        return value;
    }

    public V initialValue() {
        return initialValue;
    }

    @Override
    public void setName(String name) {
        settingName = name;
    }

    @Override
    public String getName() {
        return settingName;
    }

    public int valueAsInt() {
        return (Integer) value();
    }

    public int initialValueAsInt() {
        return (Integer) initialValue();
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
