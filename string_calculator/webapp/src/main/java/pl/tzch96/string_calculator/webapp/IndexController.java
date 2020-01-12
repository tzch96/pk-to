package pl.tzch96.string_calculator.webapp;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.ui.Model;

@RestController
public class IndexController {
    String appName = "string_calculator";

    @GetMapping("/")
    public String indexPage(Model model) {
        model.addAttribute("appName", appName);
        return "index";
    }
}