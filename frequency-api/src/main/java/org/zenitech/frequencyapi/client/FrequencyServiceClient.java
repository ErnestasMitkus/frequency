package org.zenitech.frequencyapi.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import reactor.core.publisher.Mono;

@FeignClient("frequency-service")
public interface FrequencyServiceClient {

    @GetMapping("/sample/{text}")
    Mono<String> echo(@PathVariable("text") String text);

}
