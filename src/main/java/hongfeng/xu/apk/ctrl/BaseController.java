/**
 * @(#)BaseController.java, May 25, 2013. 
 * 
 */
package hongfeng.xu.apk.ctrl;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

/**
 * @author xuhongfeng
 *
 */
public class BaseController {

    protected void sendError(HttpServletResponse response, int statusCode) {
        try {
            response.sendError(statusCode);
        } catch (IOException e) {
            //TODO
        }
    }
}
