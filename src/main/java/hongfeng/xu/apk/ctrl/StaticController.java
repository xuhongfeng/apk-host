/**
 * @(#)StaticController.java, May 26, 2013. 
 * 
 */
package hongfeng.xu.apk.ctrl;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.IOUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author xuhongfeng
 *
 */
@Controller
@RequestMapping("/static")
public class StaticController extends BaseController {
    
    @RequestMapping("/**")
    public void get(HttpServletRequest request, HttpServletResponse response) {
        String uri = request.getRequestURI();

        String extname = uri.substring(uri.lastIndexOf(".") + 1, uri.length());

        if (extname.equals("js")) {
            response.setContentType("text/javascript; charset=UTF-8");
        } else if (extname.equals("css")) {
            response.setContentType("text/css; charset=UTF-8");
        }

        File file = new File(uri.replace("static/", "view/"));

        InputStream is = null;
        try {
            is = new FileInputStream(file);
            byte[] bytes = IOUtils.toByteArray(is);

            response.getOutputStream().write(bytes);
        } catch (IOException exception) {
            //TODO
        } finally {
            IOUtils.closeQuietly(is);
        }
        return;
    }
}
