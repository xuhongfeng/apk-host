/**
 * @(#)RedisStore.java, May 29, 2013. 
 * 
 */
package hongfeng.xu.apk.store;

import hongfeng.xu.apk.data.Protobuf.ApkInfo;

import java.io.IOException;

import org.springframework.stereotype.Repository;

import redis.clients.jedis.Jedis;

import com.google.protobuf.InvalidProtocolBufferException;

/**
 * @author xuhongfeng
 *
 */
@Repository("redisStore")
public class RedisStore {

    public void put(ApkInfo info) {
        borrowJedis().set(info.getMd5().getBytes(), info.toByteArray());
    }
    
    public boolean exists(String md5) {
        return borrowJedis().exists(md5.getBytes());
    }
    
    public ApkInfo get(String md5) throws IOException {
        byte[] bytes = borrowJedis().get(md5.getBytes());
        try {
            return ApkInfo.parseFrom(bytes);
        } catch (InvalidProtocolBufferException e) {
            throw new IOException(e);
        }
    }
    
    private Jedis borrowJedis() {
        return new Jedis("localhost");
    }
}
