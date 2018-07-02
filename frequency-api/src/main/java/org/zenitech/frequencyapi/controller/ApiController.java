package org.zenitech.frequencyapi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.zenitech.frequencyapi.client.FrequencyServiceClient;
import reactor.core.publisher.Mono;

import java.util.concurrent.Callable;

@RestController
public class ApiController {

    private final FrequencyServiceClient frequencyServiceClient;

    @SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
    @Autowired
    public ApiController(FrequencyServiceClient frequencyServiceClient) {
        this.frequencyServiceClient = frequencyServiceClient;
    }

    @GetMapping("/echo/{text}")
    public Mono<String> echo(@PathVariable("text") String text) {
        return frequencyServiceClient.echo(text);
    }

    @GetMapping("/config")
    public Mono<Integer> config(@Value("${my.value}") Integer value) {
        return Mono.just(value);
    }

    @GetMapping("/upload")
    public String uploading() {
        return "uploading";
    }


    @PostMapping("/upload")
    public Callable<String> uploadFiles(@RequestPart("uploadingFiles") MultipartFile[] uploadingFiles) {
        for (MultipartFile uploadedFile : uploadingFiles) {
            System.out.println(uploadedFile.getName());
        }
        return () -> "redirect:/upload";
    }
    // avro, protobuf v3

}
