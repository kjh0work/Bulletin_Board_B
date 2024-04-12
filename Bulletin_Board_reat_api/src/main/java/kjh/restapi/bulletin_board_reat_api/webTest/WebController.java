package kjh.restapi.bulletin_board_reat_api.webTest;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class WebController {

    @GetMapping("/home")
    public String homePage(){
        return "home";
    }
}
