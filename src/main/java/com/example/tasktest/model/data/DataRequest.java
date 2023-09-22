package com.example.tasktest.model.data;

import com.example.tasktest.model.BaseRequest;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.hibernate.validator.constraints.Length;

public class DataRequest extends BaseRequest {
    @JsonProperty("data")
    @NotNull(message = "data cant be null")
    @NotBlank(message = "data cant be blank")
    @Length(min = 1, max = 50, message = "data must be between 1 and 50")
    private String data;

    public DataRequest() {
    }

    public DataRequest(String data) {
        this.data = data;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }
}
