package templates.snippet.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author add by huyingzhao 2025-02-19 23:00
 */
@Controller
@RequestMapping("/ascii")
public class AsciiController {
    @GetMapping("/ascii")
    public String ascii() {
        return "ascii/ascii";
    }
}
