package com.example.lectureevaluationdev.primary;

import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;
import java.util.HashMap;
import java.util.Map;

@Getter
@Setter
public class EvaluationResponse {
    private int code = HttpStatus.OK.value();
    private Object result;

    public void setResult(Object result) { this.result = result; }

    public static class ResponseMap extends com.example.lectureevaluationdev.primary.EvaluationResponse {
        private Map responseData = new HashMap();

        public ResponseMap() { setResult(responseData); }

        public void setResponseData(String key, Object value) { this.responseData.put(key, value); }

        public void clear() { this.responseData.clear(); }
    }
}
