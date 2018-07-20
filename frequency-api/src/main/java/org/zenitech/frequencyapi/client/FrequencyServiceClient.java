package org.zenitech.frequencyapi.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.Map;

@FeignClient("frequency-service")
public interface FrequencyServiceClient {

    @GetMapping("/sample/{text}")
    String echo(@PathVariable("text") String text);

    @PostMapping("/process")
    Map<String, Integer> process(@RequestBody String text);
}
