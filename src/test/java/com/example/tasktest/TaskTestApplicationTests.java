package com.example.tasktest;

import com.example.tasktest.controller.DataController;
import com.example.tasktest.handler.DataHandler;
import com.example.tasktest.model.data.DataRequest;
import com.example.tasktest.service.AuthService;
import com.example.tasktest.utils.RequestState;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Objects;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.spy;

@AutoConfigureMockMvc(printOnlyOnFailure = false)
@RunWith(SpringRunner.class)
public class TaskTestApplicationTests {

    @Test
    public void dataRequest_success() {
        DataController dataController = spy(DataController.class);

        AuthService authService = new AuthService("ha");
        DataHandler dataHandler = new DataHandler();

        dataController.setDataHandler(dataHandler);
        dataController.setAuthService(authService);

        var result = dataController.splitData(new DataRequest("maa"), "ha");

        assertTrue(result.getStatusCode().isSameCodeAs(HttpStatus.OK));
        assertEquals(Objects.requireNonNull(result.getBody()).getRequestState(), RequestState.SUCCESS);
        assertEquals(result.getBody().getErrors().size(), 0);

        int aSize = result.getBody().getResultMap().getOrDefault('a', 0);
        assertEquals(aSize, 2);
    }

    @Test
    public void dataRequest_error_noAuth() {
        DataController dataController = spy(DataController.class);

        AuthService authService = new AuthService("ha");
        DataHandler dataHandler = new DataHandler();

        dataController.setDataHandler(dataHandler);
        dataController.setAuthService(authService);

        var result = dataController.splitData(new DataRequest("maa"), "h");

        assertTrue(result.getStatusCode().isSameCodeAs(HttpStatus.UNAUTHORIZED));
        assertEquals(Objects.requireNonNull(result.getBody()).getRequestState(), RequestState.ERROR);
        assertEquals(result.getBody().getErrors().size(), 1);
        assertEquals(result.getBody().getErrors().get(0), "invalid token h");
    }
}
