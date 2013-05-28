/**
 * @(#)BaseContextTest.java, May 27, 2013. 
 * 
 */
package hongfeng.xu;

import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;

/**
 * @author xuhongfeng
 *
 */
@ContextConfiguration(locations = { "classpath:servletContext.xml" })
public class BaseContextTest extends AbstractTestNGSpringContextTests {

}
