package com.example.tasktest.model.data;

import com.example.tasktest.model.BaseResponse;
import com.example.tasktest.utils.RequestState;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.http.HttpStatus;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class DataResponse extends BaseResponse {
    @JsonProperty("result")
    private Map<Character, Integer> resultMap = new LinkedHashMap<>();

    public DataResponse() {
    }

    public static DataResponse success(Map<Character, Integer> outputMap) {
        DataResponse response = new DataResponse();

        response.setResultMap(outputMap);

        return response;
    }

    public static DataResponse error(String message, HttpStatus status) {
        DataResponse response = new DataResponse();
        response.serveError(message, status);
        return response;
    }

    public static DataResponse error(List<String> errors, HttpStatus status) {
        DataResponse response = new DataResponse();
        response.serveError(errors, status);
        return response;
    }

    public Map<Character, Integer> getResultMap() {
        return resultMap;
    }

    public void setResultMap(Map<Character, Integer> resultMap) {
        this.resultMap = resultMap;
    }

    protected void serveError(String message, HttpStatus httpStatus) {
        this.HTTP_STATUS = httpStatus;
        errors.add(message);
        requestState = RequestState.ERROR;
    }

    protected void serveError(List<String> messages, HttpStatus httpStatus) {
        this.HTTP_STATUS = httpStatus;
        errors.addAll(messages);
        requestState = RequestState.ERROR;
    }

}
