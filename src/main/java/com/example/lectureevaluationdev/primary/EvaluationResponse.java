package com.example.lectureevaluationdev.primary;

import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

import java.util.HashMap;
import java.util.Map;

@Setter
@Getter
// Login response class
public class EvaluationResponse {
    private int code = HttpStatus.OK.value();
    private Object result;

    //private boolean success;
    //private String message;
    public EvaluationResponse(){};
    public void setResult(Object result) {
        this.result = result;
    }

    public static class ResponseMap extends EvaluationResponse {

        private Map responseData = new HashMap();

        public ResponseMap() {
            setResult(responseData);
        }

        public void setResponseData(String key, Object value) {
            this.responseData.put(key, value);
        }
        public void clear() {
            this.responseData.clear();
        }

    }

    /*
    public LoginResponse(boolean success, String message) {
        this.success = success;
        this.message = message;
    }

    public boolean isSuccess() {
        return success;
    }

    public String getMessage() {
        return message;
    }
    */

}