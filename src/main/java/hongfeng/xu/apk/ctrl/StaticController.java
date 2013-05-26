/**
 * @(#)StaticController.java, May 26, 2013. 
 * 
 */
package hongfeng.xu.apk.ctrl;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author xuhongfeng
 *
 */
@Controller
@RequestMapping("/static")
public class StaticController extends BaseController {
    private static Logger LOG = LoggerFactory.getLogger(StaticController.class);
    
    @RequestMapping("/**")
    public void get(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String uri = request.getRequestURI();

        String extname = uri.substring(uri.lastIndexOf(".") + 1, uri.length());

        String path = uri.toString().replace("/static/", "");
        if (extname.equals("js")) {
            path = "js/" + path;
            response.setContentType("text/javascript; charset=UTF-8");
        } else if (extname.equals("css")) {
            path = "css/" + path;
            response.setContentType("text/css; charset=UTF-8");
        }
        path = "view/" + path;
        File file = new File(getWebHome(), path);
        LOG.info(file.getAbsolutePath());

        InputStream is = null;
        try {
            is = new FileInputStream(file);
            byte[] bytes = IOUtils.toByteArray(is);

            response.getOutputStream().write(bytes);
        } catch (FileNotFoundException e) {
            sendError(response, HttpServletResponse.SC_NOT_FOUND);
        } catch (IOException e) {
            throw e;
        } finally {
            IOUtils.closeQuietly(is);
        }
        return;
    }
}
