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
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

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
    
    @RequestMapping(value="/upload", method=RequestMethod.POST)
    @ResponseBody
    public String upload(@RequestParam("apkFile") MultipartFile apkFile) {
        LOG.info(apkFile.getOriginalFilename());
        return "";
    }
    
    public static class UploadApkForm {
        private MultipartFile apkFile;

        public MultipartFile getApkFile() {
            return apkFile;
        }
    
        public void setApkFile(MultipartFile apkFile) {
            this.apkFile = apkFile;
        }
    }
}
