package io.github.gmachadojao.back_end.Controllers;

import io.github.gmachadojao.back_end.Services.JsonServices;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping("/json")
public class JsonController {

    private final JsonServices jsonServices;

    @GetMapping("/test")
    public String test() {
        return "Hello from JSON endpoint!";
    }

    @PostMapping("/parse")
    public String parse(@RequestBody String json) {
        try {
            return jsonServices.parseJson(json);
        } catch (Exception e) {
            throw new RuntimeException("Error parsing JSON: " + e.getMessage(), e);
        }
    }
}