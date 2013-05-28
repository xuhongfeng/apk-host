package hongfeng.xu;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.testng.annotations.Test;

/**
 * @(#)Test.java, May 27, 2013. 
 * 
 */

/**
 * @author xuhongfeng
 *
 */
public class TestProperties extends BaseContextTest {
    private static final Logger LOG = LoggerFactory.getLogger(TestProperties.class);

    @Value("${hadoop.home}")
    private String hadoopHome;
    
    @Test
    public void TestProperties() {
        LOG.info(hadoopHome);
    }
    
    
    public static void main (String args[]) {
        LOG.info(TestProperties.class.getResource("").toString());
        LOG.info(TestProperties.class.getResource("/").toString());
    }
}
