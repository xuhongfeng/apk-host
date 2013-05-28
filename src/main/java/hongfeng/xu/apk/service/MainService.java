/**
 * @(#)MainService.java, May 27, 2013. 
 * 
 */
package hongfeng.xu.apk.service;

import hongfeng.xu.apk.data.Protobuf.ApkInfo;
import hongfeng.xu.apk.store.HdfsStore;
import hongfeng.xu.apk.store.RedisStore;
import hongfeng.xu.apk.util.MD5Utils;

import java.io.IOException;
import java.io.InputStream;

import org.apache.hadoop.fs.Path;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author xuhongfeng
 *
 */
@Service("mainService")
public class MainService {
    private static final Logger LOG = LoggerFactory.getLogger(MainService.class);
    
    @Autowired
    private HdfsStore hdfsStore;
    
    @Autowired
    private RedisStore  redisStore;
    
    @Autowired
    private MD5Utils md5Utils;

    /**
     * return false if is already exists
     * @param file
     * @return
     * @throws IOException
     */
    public boolean addApk(MultipartFile file) throws IOException {
        InputStream input = file.getInputStream();
        try {
            //compute md5
            input.mark(Integer.MAX_VALUE);
            String md5 = md5Utils.md5(input);
            input.reset();
            
            //check if exists
            if (redisStore.exists(md5)) {
                LOG.info("already exists");
                return false;
            }
            
            //save to HDFS
            hdfsStore.put(input, new Path("apk/" + md5));
            
            //save ApkInfo to redis
            String fileName = file.getOriginalFilename();
            ApkInfo info = ApkInfo.newBuilder().setName(fileName)
                .setMd5(md5).setSize(file.getSize()).build();
            LOG.info(info.toString());
            redisStore.put(info);
            
            return true;
        } finally {
            input.close();
        }
    }
}
