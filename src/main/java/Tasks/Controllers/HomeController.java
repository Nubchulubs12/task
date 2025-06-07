package Tasks.Controllers;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {
    @GetMapping({"/", "/index"})
    public String index(Model model, OAuth2AuthenticationToken token) {
        if (token != null && token.isAuthenticated()) {
            String name = token.getPrincipal().getAttribute("name");
            model.addAttribute("userName", name);
        }
        return "index";
    }


}
