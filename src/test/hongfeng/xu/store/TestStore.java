/**
 * @(#)TestStore.java, May 27, 2013. 
 * 
 */
package hongfeng.xu.store;

import hongfeng.xu.BaseContextTest;
import hongfeng.xu.apk.store.HdfsStore;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

import org.apache.commons.io.IOUtils;
import org.apache.hadoop.fs.Path;
import org.springframework.beans.factory.annotation.Autowired;
import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * @author xuhongfeng
 *
 */
public class TestStore extends BaseContextTest {
    @Autowired
    private HdfsStore hdfsStore;

    @Test
    public void testHdfs() {
        Path path = new Path("test");
        try {
            hdfsStore.remove(path);
            String s = "hello world";
            byte[] buf = s.getBytes();
            ByteArrayInputStream input = new ByteArrayInputStream(buf);
            hdfsStore.put(input, path);
            Assert.assertTrue(hdfsStore.exists(path));
            InputStream is = hdfsStore.open(path);
            String str = IOUtils.toString(is);
            Assert.assertEquals(str, s);
            hdfsStore.remove(path);
            Assert.assertFalse(hdfsStore.exists(path));
        } catch (IOException e) {
            Assert.fail("", e);
        }
    }
}
