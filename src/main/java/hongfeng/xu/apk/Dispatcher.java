/**
 * @(#)Dispatcher.java, May 26, 2013. 
 * 
 */
package hongfeng.xu.apk;

import hongfeng.xu.apk.misc.Const;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;

import org.springframework.web.servlet.DispatcherServlet;

/**
 * @author xuhongfeng
 *
 */
public class Dispatcher extends DispatcherServlet {
    private static final long serialVersionUID = 7750996231391286313L;

    @Override
    public void init(ServletConfig config) throws ServletException {
        String homeDir = config.getServletContext().getRealPath("WEB-INF");
        System.setProperty(Const.PARAM_WEB_HOME_DIR, homeDir);
        super.init(config);
    }
}
