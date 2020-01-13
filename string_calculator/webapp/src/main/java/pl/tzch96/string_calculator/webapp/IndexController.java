package pl.tzch96.string_calculator.webapp;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.ui.Model;

@Controller
public class IndexController {
    String appName = "string_calculator";

    @RequestMapping("/")
    public String indexPage(Model model) {
        model.addAttribute("appName", appName);
        return "index";
    }
}