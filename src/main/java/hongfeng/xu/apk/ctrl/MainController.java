/**
 * @(#)BaseController.java, May 25, 2013. 
 * 
 */
package hongfeng.xu.apk.ctrl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author xuhongfeng
 *
 */
@Controller
public class MainController extends BaseController {
    private static Logger LOG = LoggerFactory.getLogger(MainController.class);

    @RequestMapping("/")
    public String home(ModelMap model) {
        return "home";
    }
}
