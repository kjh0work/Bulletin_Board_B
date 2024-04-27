package kjh.restapi.bulletin_board_reat_api.webTest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@Controller
public class WebController {

    @GetMapping("/homePage")
    public String homePage(){
        return "homePage";
    }
}
