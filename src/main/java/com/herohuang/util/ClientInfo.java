package com.herohuang.util;

import javax.servlet.http.HttpServletRequest;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/*  
 * 返回客户端信息工具类  
 * by netwild  
 */
public class ClientInfo {

	private String info = "";
	private String explorerVer = "未知";
	private String OSVer = "未知";

	/*
	 * 构造函数 参数：String request.getHeader("user-agent")
	 * 
	 * IE7:Mozilla/4.0 (compatible; MSIE 7.0; Windows NT 6.1; Trident/4.0;
	 * SLCC2; .NET CLR 2.0.50727; .NET CLR 3.5.30729; .NET CLR 3.0.30729; Media
	 * Center PC 6.0; .NET4.0C) IE8:Mozilla/4.0 (compatible; MSIE 8.0; Windows
	 * NT 6.1; Trident/4.0; SLCC2; .NET CLR 2.0.50727; .NET CLR 3.5.30729; .NET
	 * CLR 3.0.30729; Media Center PC 6.0; .NET4.0C) Maxthon:Mozilla/4.0
	 * (compatible; MSIE 7.0; Windows NT 6.1; Trident/4.0; SLCC2; .NET CLR
	 * 2.0.50727; .NET CLR 3.5.30729; .NET CLR 3.0.30729; Media Center PC 6.0;
	 * .NET4.0C; Maxthon 2.0) firefox:Mozilla/5.0 (Windows; U; Windows NT 6.1;
	 * zh-CN; rv:1.9.2.11) Gecko/20101012 Firefox/3.6.11 Chrome:Mozilla/5.0
	 * (Windows; U; Windows NT 6.1; en-US) AppleWebKit/534.7 (KHTML, like Gecko)
	 * Chrome/7.0.517.44 Safari/534.7 Opera:Opera/9.80 (Windows NT 6.1; U;
	 * zh-cn) Presto/2.6.30 Version/10.63
	 * 
	 * 操作系统： Win7 : Windows NT 6.1 WinXP : Windows NT 5.1
	 */
	public ClientInfo(String info) {
		this.info = info;
	}

	// 判断浏览器是IE

	public boolean isIe() {
		if (info.indexOf("MSIE") != -1) {
			return true;
		}
		return false;
	}

	/*
	 * 获取核心浏览器名称
	 */
	public String getExplorerName() {
		String str = "未知";
		Pattern pattern = Pattern.compile("");
		Matcher matcher;
		if (info.indexOf("MSIE") != -1) {
			str = "IE"; // 微软IE
			if (info.toLowerCase().indexOf("msie 10.0") > 0) {
				str += " 10";
			} else if (info.toLowerCase().indexOf("msie 9.0") > 0) {
				str += " 9";
			} else if (info.toLowerCase().indexOf("msie 8.0") > 0) {
				str += " 8";
			} else if (info.toLowerCase().indexOf("msie 7.0") > 0) {
				str += " 7";
			} else if (info.toLowerCase().indexOf("msie 6.0") > 0) {
				str += " 6";
			} else if (info.toLowerCase().indexOf("msie 11.0") > 0) {
				str += " 11";
			} else if (info.toLowerCase().indexOf("msie 12.0") > 0) {
				str += " 12";
			}

			pattern = Pattern.compile(str + "\\s([0-9.]+)");
		} else if (info.indexOf("Firefox") != -1) {
			str = "Firefox"; // 火狐
			pattern = Pattern.compile(str + "\\/([0-9.]+)");
		} else if (info.indexOf("Chrome") != -1) {
			str = "Chrome"; // Google
			pattern = Pattern.compile(str + "\\/([0-9.]+)");
		} else if (info.indexOf("Opera") != -1) {
			str = "Opera"; // Opera
			pattern = Pattern.compile("Version\\/([0-9.]+)");
		}
		matcher = pattern.matcher(info);
		if (matcher.find())
			explorerVer = matcher.group(1);
		return str;
	}

	/*
	 * 获取核心浏览器版本
	 */
	public String getExplorerVer() {
		return this.explorerVer;
	}

	/*
	 * 获取浏览器插件名称（例如：遨游、世界之窗等）
	 */
	public String getExplorerPlug() {
		String str = "无";
		if (info.indexOf("Maxthon") != -1)
			str = "Maxthon"; // 遨游
		return str;
	}

	/*
	 * 获取操作系统名称
	 */
	public String getOSName() {
		String str = "未知";
		Pattern pattern = Pattern.compile("");
		Matcher matcher;
		if (info.indexOf("Windows") != -1) {
			str = "Windows"; // Windows NT 6.1
			pattern = Pattern.compile(str + "\\s([a-zA-Z0-9]+\\s[0-9.]+)");
		}
		matcher = pattern.matcher(info);
		if (matcher.find())
			OSVer = matcher.group(1);
		return str;
	}

	/*
	 * 获取操作系统版本
	 */
	public String getOSVer() {
		return this.OSVer;
	}

	public String getIpAddr(HttpServletRequest request) {
		String ip = request.getHeader("x-forwarded-for");
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("WL-Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getRemoteAddr();
		}
		return ip;
	}

	/**
	 * 获取浏览器版本信息
	 * 
	 * @Title: getBrowserName
	 * @data:2015-1-12下午05:08:49
	 * @author:wolf
	 * 
	 * @param agent
	 * @return
	 */

	public static String getBrowserName(String agent) {
		if (agent.indexOf("msie 7") > 0) {
			return "ie7";
		} else if (agent.indexOf("msie 8") > 0) {
			return "ie8";
		} else if (agent.indexOf("msie 9") > 0) {
			return "ie9";
		} else if (agent.indexOf("msie 10") > 0) {
			return "ie10";
		} else if (agent.indexOf("msie") > 0) {
			return "ie";
		} else if (agent.indexOf("opera") > 0) {
			return "opera";
		} else if (agent.indexOf("opera") > 0) {
			return "opera";
		} else if (agent.indexOf("firefox") > 0) {
			return "firefox";
		} else if (agent.indexOf("webkit") > 0) {
			return "webkit";
		} else if (agent.indexOf("gecko") > 0 && agent.indexOf("rv:11") > 0) {
			return "ie11";
		} else {
			return "Others";
		}
	}

}