/**
 * @(#)MD5Utils.java, May 28, 2013. 
 * 
 */
package hongfeng.xu.apk.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import org.springframework.stereotype.Component;

/**
 * @author xuhongfeng
 *
 */
@Component("md5Utils")
public class MD5Utils {
    
    public String md5(File file) throws IOException {
        MessageDigest md = null;
        try {
            md = MessageDigest.getInstance("MD5");
        } catch (NoSuchAlgorithmException e) {
            throw new IOException("can not found algorighm MD5", e);
        }
        FileInputStream fis = null;
        try {
            fis = new FileInputStream(file);
            return md5(fis);
        } catch (FileNotFoundException e) {
            throw new IOException("file for md5 not found!", e);
        }
    }
    
    public String md5(InputStream input) throws IOException {
        MessageDigest md = null;
        try {
            md = MessageDigest.getInstance("MD5");
        } catch (NoSuchAlgorithmException e) {
            throw new IOException("can not found algorighm MD5", e);
        }
        byte[] buf = new byte[1024];
        int len;
        while((len = input.read(buf, 0, buf.length)) != -1) {
            md.update(buf, 0, len);
        }
        byte[] digest = md.digest();
        return getHashtext(digest);
    }
    
    public String md5(String s) throws IOException {
        MessageDigest md = null;
        try {
            md = MessageDigest.getInstance("MD5");
        } catch (NoSuchAlgorithmException e) {
            throw new IOException("can not found algorighm MD5", e);
        }
        byte[] digest = md.digest(s.getBytes());
        return getHashtext(digest);
    }

    private String getHashtext(byte[] digest) {
        BigInteger bigInt = new BigInteger(1, digest);
        String hashtext = bigInt.toString(16);
        while (hashtext.length() < 32) {
            hashtext = "0" + hashtext;
        }
        return hashtext.toUpperCase();
    }
}