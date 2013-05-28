/**
 * @(#)MainService.java, May 27, 2013. 
 * 
 */
package hongfeng.xu.apk.service;

import hongfeng.xu.apk.store.HdfsStore;

import java.io.IOException;

import org.apache.hadoop.fs.Path;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author xuhongfeng
 *
 */
@Service("mainService")
public class MainService {
    
    @Autowired
    private HdfsStore hdfsStore;

    public void addApk(MultipartFile file) throws IOException {
        hdfsStore.put(file.getInputStream(), new Path("test"));
    }
}
