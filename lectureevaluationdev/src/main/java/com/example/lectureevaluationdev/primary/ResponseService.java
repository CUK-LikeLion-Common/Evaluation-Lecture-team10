package com.example.lectureevaluationdev.primary;

public class ResponseService {
    private EvaluationResponse.ResponseMap result;
    public EvaluationResponse setResponse(int code, String message, Object object) throws Exception {
        result = new EvaluationResponse.ResponseMap();
        result.setCode(code);
        result.setResponseData(message, object);
        return result;
    }

    public EvaluationResponse addResponse(String message, Object object) throws Exception {
        if(result == null)
            result = new EvaluationResponse.ResponseMap();
        result.setResponseData(message, object);
        return result;
    }

    public EvaluationResponse getResult() {
        return result;
    }

    public void closeResult() {
        if(result != null)
            result.clear();
    }
}
