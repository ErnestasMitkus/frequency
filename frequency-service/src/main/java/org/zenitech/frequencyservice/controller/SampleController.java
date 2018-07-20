package org.zenitech.frequencyservice.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

@RestController
public class SampleController {

    @GetMapping("/sample/{text}")
    public Mono<String> echo(@PathVariable("text") String text) {
        return Mono.just(text);
    }

    @PostMapping("/process")
    public Mono<Map<String, Integer>> process(@RequestBody String text) {
        System.out.println(text);
        HashMap<String, Integer> result = new HashMap<>();

        Arrays.stream(text.split("\\s+"))
            .filter(SampleController::isWord)
            .map(SampleController::trimSymbols)
            .filter(it -> !it.isEmpty())
            .forEach(s ->
                result.put(s, result.getOrDefault(s, 0) + 1)
            );
        return Mono.just(result);
    }

    private static String trimSymbols(String str) {
        return str.replaceAll("[^a-zA-Z0-9\\-]","");
    }

    private static boolean isWord(String str) {
        return !str.matches("\\d+");
    }
}
