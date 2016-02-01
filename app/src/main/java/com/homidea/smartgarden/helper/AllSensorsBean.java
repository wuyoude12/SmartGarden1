package com.homidea.smartgarden.helper;

import java.util.List;

/**
 * Created by wuyoude on 2016/1/26.
 */
public class AllSensorsBean {
    private int id;
    private String idName;
    private String templateName;
    private String name;
    private String typeName;
    private String description;
    private boolean isPublic;
    private boolean isControlled;
    private boolean internetAvailable;
    private String apiAddress;
    private String apiAddressInternet;
    private List<Sensors> sensors;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getIdName() {
        return idName;
    }

    public void setIdName(String idName) {
        this.idName = idName;
    }

    public String getTemplateName() {
        return templateName;
    }

    public void setTemplateName(String templateName) {
        this.templateName = templateName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isPublic() {
        return isPublic;
    }

    public void setIsPublic(boolean isPublic) {
        this.isPublic = isPublic;
    }

    public boolean isControlled() {
        return isControlled;
    }

    public void setIsControlled(boolean isControlled) {
        this.isControlled = isControlled;
    }

    public boolean isInternetAvailable() {
        return internetAvailable;
    }

    public void setInternetAvailable(boolean internetAvailable) {
        this.internetAvailable = internetAvailable;
    }

    public String getApiAddress() {
        return apiAddress;
    }

    public void setApiAddress(String apiAddress) {
        this.apiAddress = apiAddress;
    }

    public String getApiAddressInternet() {
        return apiAddressInternet;
    }

    public void setApiAddressInternet(String apiAddressInternet) {
        this.apiAddressInternet = apiAddressInternet;
    }

    public List<Sensors> getSensors() {
        return sensors;
    }

    public void setSensors(List<Sensors> sensors) {
        this.sensors = sensors;
    }

    public class Sensors {
        private int id;
        private String idName;
        private String name;
        private String value;
        private String type;
        private String typeName;
        private String unit;
        private String description;
        private String lastUpdateTime;
        private boolean isOnline;
        private boolean isError;
        private boolean isAlarm;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getIdName() {
            return idName;
        }

        public void setIdName(String idName) {
            this.idName = idName;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getValue() {
            return value;
        }

        public void setValue(String value) {
            this.value = value;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getTypeName() {
            return typeName;
        }

        public void setTypeName(String typeName) {
            this.typeName = typeName;
        }

        public String getUnit() {
            return unit;
        }

        public void setUnit(String unit) {
            this.unit = unit;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public String getLastUpdateTime() {
            return lastUpdateTime;
        }

        public void setLastUpdateTime(String lastUpdateTime) {
            this.lastUpdateTime = lastUpdateTime;
        }

        public boolean isOnline() {
            return isOnline;
        }

        public void setIsOnline(boolean isOnline) {
            this.isOnline = isOnline;
        }

        public boolean isError() {
            return isError;
        }

        public void setIsError(boolean isError) {
            this.isError = isError;
        }

        public boolean isAlarm() {
            return isAlarm;
        }

        public void setIsAlarm(boolean isAlarm) {
            this.isAlarm = isAlarm;
        }
    }
}
