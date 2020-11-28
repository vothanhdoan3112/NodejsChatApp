package com.study.nodejsappchat.entities;

public class Setting {
    private String settingName;
    private int icon;

    public Setting(String settingName, int icon) {
        this.settingName = settingName;
        this.icon = icon;
    }

    public String getSettingName() {
        return settingName;
    }

    public void setSettingName(String settingName) {
        this.settingName = settingName;
    }

    public int getIcon() {
        return icon;
    }

    public void setIcon(int icon) {
        this.icon = icon;
    }
}
