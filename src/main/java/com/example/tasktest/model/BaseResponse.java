package com.example.tasktest.model;

import com.example.tasktest.utils.RequestState;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.http.HttpStatus;

import java.util.ArrayList;
import java.util.List;

public abstract class BaseResponse {
    @JsonProperty("status")
    protected RequestState requestState = RequestState.SUCCESS;
    @JsonProperty("errors")
    protected List<String> errors = new ArrayList<>();

    @JsonIgnore
    public HttpStatus HTTP_STATUS = HttpStatus.OK;

    public BaseResponse() {
    }

    public BaseResponse(RequestState requestState, List<String> errors) {
        this.requestState = requestState;
        this.errors = errors;
    }

    public RequestState getRequestState() {
        return requestState;
    }

    public void setRequestState(RequestState requestState) {
        this.requestState = requestState;
    }

    public List<String> getErrors() {
        return errors;
    }

    public void setErrors(List<String> errors) {
        this.errors = errors;
    }

    private void addError(String message) {
        errors.add(message);
    }

}
