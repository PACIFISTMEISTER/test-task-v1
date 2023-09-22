package com.example.tasktest.controller;

import com.example.tasktest.exception.NoAccessException;
import com.example.tasktest.handler.DataHandler;
import com.example.tasktest.model.data.DataRequest;
import com.example.tasktest.model.data.DataResponse;
import com.example.tasktest.service.AuthService;
import com.example.tasktest.utils.TaskUtils;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/data")
public class DataController {

    private DataHandler dataHandler;

    private AuthService authService;

    public DataController() {
    }

    @Autowired
    public DataController(DataHandler dataHandler,

                          AuthService authService) {
        this.dataHandler = dataHandler;

        this.authService = authService;
    }

    @PostMapping("")
    public ResponseEntity<DataResponse> splitData(
            @RequestBody @Valid DataRequest request,

            @RequestHeader(name = "TOKEN") String token) {

        DataResponse response = new DataResponse();

        try {

            var hasAccess = authService.checkIfUserHasAccess(token);
            if (!hasAccess) {
                throw NoAccessException.createExceptionByToken(token);
            }

            response = dataHandler.splitData(request);
        } catch (NoAccessException e) {
            response = DataResponse.error(e.getMessage(), HttpStatus.UNAUTHORIZED);
        } catch (Exception e) {
            response = DataResponse.error(TaskUtils.DEFAULT_ERROR_MESSAGE, HttpStatus.BAD_GATEWAY);
        }

        return ResponseEntity.status(response.HTTP_STATUS).body(response);
    }

    public DataHandler getDataHandler() {
        return dataHandler;
    }

    public void setDataHandler(DataHandler dataHandler) {
        this.dataHandler = dataHandler;
    }

    public AuthService getAuthService() {
        return authService;
    }

    public void setAuthService(AuthService authService) {
        this.authService = authService;
    }
}
