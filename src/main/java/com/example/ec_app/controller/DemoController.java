package com.example.ec_app.controller;

import java.util.List;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import com.example.ec_app.service.SampleService;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class DemoController {

  private final SampleService sampleService;

  @GetMapping("/api/demo/sample")
  public List<String> getSample() {
    final List<String> result = sampleService.selectString();
    System.out.println(result);

    return result;
  }

}
