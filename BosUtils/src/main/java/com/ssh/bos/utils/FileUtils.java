package com.ssh.bos.utils;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import org.apache.commons.codec.binary.Base64;

/**
 * 文件工具，对下载的附件名进行编码
 * 
 * @author slzhang
 */
public class FileUtils {
	/**
	 * 
	 * @param fileName
	 * @param agent
	 * @return
	 */
	public static String encodeDownloadFilename(String fileName, String agent) {
		if (agent.contains("Firefox")) {
			// 火狐浏览器
			try {
				fileName = "=?UTF-8?B?" + Base64.encodeBase64String(fileName.getBytes("utf-8")) + "?=";
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}
			fileName = fileName.replaceAll("\r\n", "");
		} else {
			// IE及其他浏览器
			try {
				fileName = URLEncoder.encode(fileName, "utf-8");
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}
			fileName = fileName.replace("+", " ");
		}
		return fileName;
	}
	
}
