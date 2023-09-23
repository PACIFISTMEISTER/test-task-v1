package com.example.tasktest.handler;

import com.example.tasktest.model.data.DataRequest;
import com.example.tasktest.model.data.DataResponse;
import com.example.tasktest.utils.TaskUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class DataHandler {

    private static final Logger log = LogManager.getLogger(DataHandler.class);

    @Autowired
    public DataHandler() {
    }

    public DataResponse splitData(DataRequest request) {
        try {
            Map<Character, Integer> characterToFrequency = new LinkedHashMap<>();
            var str = request.getData();

            Map<Character, Integer> finalCharacterToFrequency = characterToFrequency;
            str.chars().mapToObj(ch -> (char) ch)
                    .forEach(key -> {
                                if (!finalCharacterToFrequency.containsKey(key)) {
                                    finalCharacterToFrequency.put(key, 1);
                                } else {
                                    Integer value = finalCharacterToFrequency.get(key);
                                    finalCharacterToFrequency.put(key, ++value);
                                }
                            }
                    );

            characterToFrequency = characterToFrequency.entrySet().stream()
                    .sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))
                    .collect(Collectors.toMap(
                            Map.Entry::getKey, Map.Entry::getValue, (e1, e2) -> e1, LinkedHashMap::new));

            return DataResponse.success(characterToFrequency);
        } catch (Exception e) {
            log.error(e.getMessage(), e);

            return DataResponse.error(TaskUtils.DEFAULT_ERROR_MESSAGE, HttpStatus.BAD_GATEWAY);
        }
    }
}
