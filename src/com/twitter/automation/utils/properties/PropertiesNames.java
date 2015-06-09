package com.twitter.automation.utils.properties;

public enum PropertiesNames {
    BROWSER("browser"), DRIVERS_DIR("drivers.dir");

    private String value;

    private PropertiesNames(String value) {
        this.value = value;
    }

    @Override
    public String toString(){
        return value;
    }

}
