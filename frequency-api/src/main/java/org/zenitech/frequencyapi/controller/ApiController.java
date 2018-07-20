package org.zenitech.frequencyapi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.view.RedirectView;
import org.zenitech.frequencyapi.client.FrequencyServiceClient;

import java.io.IOException;
import java.util.Arrays;
import java.util.Collection;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.Callable;
import java.util.function.ToIntFunction;
import java.util.stream.Collectors;

@RestController
public class ApiController {

    private final FrequencyServiceClient frequencyServiceClient;

    private final Map<String, Map<String, Integer>> calculations;

    @SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
    @Autowired
    public ApiController(FrequencyServiceClient frequencyServiceClient) {
        this.frequencyServiceClient = frequencyServiceClient;
        calculations = new HashMap<>();
    }

    @GetMapping("/echo/{text}")
    public Callable<String> echo(@PathVariable("text") String text) {
        return () -> frequencyServiceClient.echo(text);
    }

    @GetMapping("/config")
    public Callable<Integer> config(@Value("${my.value}") Integer value) {
        return () -> value;
    }

    @GetMapping("/result/{uid}")
    public String result(@PathVariable("uid") String uid) {
        Map<String, Integer> resultMap = calculations.getOrDefault(uid, new HashMap<>());
        StringBuilder sb = new StringBuilder();

        resultMap.entrySet().stream()
            .sorted(Comparator.comparingInt((ToIntFunction<Map.Entry<String, Integer>>) Map.Entry::getValue)
                    .thenComparing(Map.Entry::getKey))
            .map(e -> String.format("%s ==>> %d", e.getKey(), e.getValue()))
            .collect(Collectors.toCollection(LinkedList::new))
            .descendingIterator().forEachRemaining(it -> sb.append(it).append("\n"));

        return sb.toString();
    }

    @PostMapping(value = "/upload", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public RedirectView uploadFiles(@RequestParam("uploadingFiles") List<MultipartFile> files) {

        Map<String, Integer> frequency = files.stream().flatMap(file -> {
            try {
                byte[] bytes = file.getBytes();
                return Arrays.stream(new String(bytes).split("\n"));
            } catch (IOException e) {
                e.printStackTrace();
                return Arrays.stream(new String[] {});
            }
        })
        .parallel()
        .map(frequencyServiceClient::process)
        .map(Map::entrySet)
        .flatMap(Collection::stream)
        .collect(Collectors.toConcurrentMap(
            Map.Entry::getKey,
            Map.Entry::getValue,
            Integer::sum
        ));

        String uid = generateUID();
        calculations.put(uid, frequency);

        return new RedirectView("http://localhost:4000/result.html?id=" + uid);
    }

    private static String generateUID() {
        return "" + System.currentTimeMillis() + new Random().nextInt(10000);
    }
}
