package com.example.tasktest.utils;

import com.fasterxml.jackson.annotation.JsonProperty;

public enum RequestState {
    @JsonProperty("success")
    SUCCESS,
    @JsonProperty("error")
    ERROR
}
