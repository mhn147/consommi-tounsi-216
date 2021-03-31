package tn.esprit.pidev.consommitounsi.controllers.payment;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class TmpWebController {
    @GetMapping("/checkout")
    public String home() {
        return "index";
    }
}
