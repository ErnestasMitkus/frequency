package org.zenitech.frequencyapi.controller;

import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.concurrent.Callable;

@RestController
public class ApiController {

    @GetMapping("/echo/{text}")
    public Callable<String> echo(@PathVariable("text") String text) {
        return () -> "echo: " + text;
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
