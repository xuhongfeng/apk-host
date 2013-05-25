/**
 * @(#)BaseController.java, May 25, 2013. 
 * 
 */
package hongfeng.xu.apk.ctrl;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author xuhongfeng
 *
 */
@Controller
public class MainController extends BaseController {

    @RequestMapping("/")
    public String home(ModelMap model) {
        model.addAttribute("label", "upload");
        return "home";
    }
}
