package br.com.danilo.util;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;

/* 
 * https://www.digitalocean.com/community/tutorials/java-gzip-example-compress-decompress-file
 */
public class UtilGZIP {

	  public static byte[] decompressGzip(byte[] gzipData) {
	        try {
	            ByteArrayInputStream bis = new ByteArrayInputStream(gzipData);
	            GZIPInputStream gis = new GZIPInputStream(bis);
	            ByteArrayOutputStream bos = new ByteArrayOutputStream();
	            byte[] buffer = new byte[1024];
	            int len;
	            while ((len = gis.read(buffer)) != -1) {
	                bos.write(buffer, 0, len);
	            }
	            
	            // close resources
	            bos.close();
	            gis.close();
	            bis.close();
	            return bos.toByteArray();
	        } catch (IOException e) {
	            e.printStackTrace();
	        }
	        return null;
	    }

	    public static byte[] compressGzip(byte[] data) {
	        try {
	            ByteArrayOutputStream bos = new ByteArrayOutputStream();
	            GZIPOutputStream gzipOS = new GZIPOutputStream(bos);
	            gzipOS.write(data);
	            
	            // close resources
	            gzipOS.close();
	            bos.close();
	            return bos.toByteArray();
	        } catch (IOException e) {
	            e.printStackTrace();
	        }
	        return null;
	    }
}
