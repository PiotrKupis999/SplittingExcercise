package com.basket.splitter.excercise.controllers;

import com.basket.splitter.excercise.services.SplitterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.List;
import java.util.Map;
@RestController

public class SplitterController {
    private final SplitterService splitterService;

    @Autowired
    public SplitterController(SplitterService splitterService) {
        this.splitterService = splitterService;
    }

    @PostMapping("/split")
    public Map<String, List<String>> Split(@RequestBody List<String> items) throws IOException {
        return splitterService.splitBasket(items);
    }
}
