package com.homidea.smartgarden.helper;

import java.util.List;

/**
 * Created by wuyoude on 2016/1/27.
 */
public class HistoryDataBean {
    private boolean Successful;
    private String Message;
    private List<HistoryData> Data;

    public boolean isSuccessful() {
        return Successful;
    }

    public void setSuccessful(boolean successful) {
        Successful = successful;
    }

    public String getMessage() {
        return Message;
    }

    public void setMessage(String message) {
        Message = message;
    }

    public List<HistoryData> getData() {
        return Data;
    }

    public void setData(List<HistoryData> data) {
        Data = data;
    }

    public class HistoryData {
        private String updateTime;
        private String value;

        public String getUpdateTime() {
            return updateTime;
        }

        public void setUpdateTime(String updateTime) {
            this.updateTime = updateTime;
        }

        public String getValue() {
            return value;
        }

        public void setValue(String value) {
            this.value = value;
        }
    }
}
