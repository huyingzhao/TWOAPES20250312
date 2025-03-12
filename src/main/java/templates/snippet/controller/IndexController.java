package templates.snippet.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
/**
 * @author add by twoapes
 * 2022-07-11 14:03
 */
@Controller
@RequestMapping("/index")
public class IndexController {
    @GetMapping("/index")
    public String index() {
        return "index";
    }
}
