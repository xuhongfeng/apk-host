/**
 * @(#)BaseController.java, May 25, 2013. 
 * 
 */
package hongfeng.xu.apk.ctrl;

import hongfeng.xu.apk.misc.Const;

import java.io.File;
import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author xuhongfeng
 *
 */
public class BaseController {
    private static Logger LOG = LoggerFactory.getLogger(BaseController.class);

    protected void sendError(HttpServletResponse response, int statusCode) {
        try {
            response.sendError(statusCode);
        } catch (IOException e) {
            LOG.error("send error failed", e);
        }
    }
    
    protected File getWebHome() {
        return new File(System.getProperty(Const.PARAM_WEB_HOME_DIR));
    }
}
