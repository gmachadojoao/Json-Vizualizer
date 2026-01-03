package io.github.gmachadojao.back_end.Controllers;

import io.github.gmachadojao.back_end.Services.JsonServices;
import jakarta.validation.constraints.Size;
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
    public String parse( @RequestBody(required = true) @Size(max = 1048576) String json) {
        try {
            return jsonServices.parseJson(json);
        } catch (Exception e) {
            throw new RuntimeException("Error parsing JSON: " + e.getMessage(), e);
        }
    }
}