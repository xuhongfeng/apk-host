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

import org.apache.commons.io.IOUtils;
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
            Path tmpPath = new Path("tmp/" + file.getOriginalFilename().hashCode()
                    + "-" + System.currentTimeMillis());
            //temporary save to HDFS
            hdfsStore.put(input, tmpPath);
            
            //compute md5
            String md5 = null;
            InputStream tmpInput = null;
            try {
                tmpInput = hdfsStore.open(tmpPath);
                md5 = md5Utils.md5(input);
            } finally {
                IOUtils.closeQuietly(tmpInput);
                hdfsStore.remove(tmpPath);
            }
            
            //check if exists
            if (redisStore.exists(md5)) {
                LOG.info("already exists");
                return false;
            }
            
            Path finalPath = new Path("apk/" + md5 + ".apk");
            hdfsStore.put(input, finalPath);
            //save ApkInfo to redis
            try {
                String fileName = file.getOriginalFilename();
                ApkInfo info = ApkInfo.newBuilder().setName(fileName)
                    .setMd5(md5).setSize(file.getSize()).build();
                LOG.info(info.toString());
                redisStore.put(info);
            } catch (Throwable e) {
                hdfsStore.remove(finalPath);
                if (e instanceof IOException) {
                    throw (IOException)e;
                } else {
                    throw new IOException(e);
                }
            }
            
            return true;
        } finally {
            input.close();
        }
    }
}
