package org.zenitech.frequencyapi.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient("frequency-service")
public interface FrequencyServiceClient {

    @GetMapping("/sample/{text}")
    String echo(@PathVariable("text") String text);
}
