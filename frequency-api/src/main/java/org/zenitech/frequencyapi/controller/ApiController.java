package org.zenitech.frequencyapi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.zenitech.frequencyapi.client.FrequencyServiceClient;
import reactor.core.publisher.Mono;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.Callable;
import java.util.stream.Collectors;

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
        return Mono.just(frequencyServiceClient.echo(text));
    }

    @GetMapping("/config")
    public Mono<Integer> config(@Value("${my.value}") Integer value) {
        return Mono.just(value);
    }

    @GetMapping("/upload")
    public String uploading() {
        return "uploading";
    }

    @PostMapping(value = "/upload", consumes = "text/plain")
    public Callable<String> uploadFiles(@RequestPart("uploadingFiles") MultipartFile[] uploadingFiles) {
        for (MultipartFile uploadedFile : uploadingFiles) {
            System.out.println(uploadedFile.getName());
        }
        List<String> lines = Arrays.stream(uploadingFiles)
                .map(it -> {
                    try {
                        return it.getInputStream();
                    } catch (IOException e) {
                        return null;
                    }
                })
                .filter(Objects::nonNull)
                .map(it -> new InputStreamReader(it, StandardCharsets.UTF_8))
                .map(BufferedReader::new)
                .map(BufferedReader::lines)
                .flatMap(it -> it)
                .map(frequencyServiceClient::echo)
                .collect(Collectors.toList());

        System.out.println(lines);

        // convert files to array of lines
        // for each line call service to count frequency
        // merge frequencies with map reduce
        return () -> "redirect:/upload";
    }
    // avro, protobuf v3

}
