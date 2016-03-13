package com.ryan.util;  
  
import java.io.BufferedReader;  
import java.io.IOException;  
import java.io.UnsupportedEncodingException;  
import java.net.URLDecoder;  
import java.security.Principal;  
import java.util.*;  
  
import javax.servlet.RequestDispatcher;  
import javax.servlet.ServletInputStream;  
import javax.servlet.http.Cookie;  
import javax.servlet.http.HttpServletRequest;  
import javax.servlet.http.HttpSession;  
  
/** 
 * HttpRequestParser工具类 
 */  
public class HttpRequestParser {  
  /** 
   * 分析url字符串,采用utf-8解码 
   * @param urlString 
   * @return 
   */  
  public static Request parse(String urlString) {  
    return parse(urlString, "utf-8");  
  }  
  
  /** 
   * 分析url字符串,指定字符集进行解码 
   * @param urlString 
   * @param enc 
   * @return 
   */  
  public static Request parse(String urlString, String enc) {  
    if (urlString == null || urlString.length() == 0) {  
      return new Request();  
    }  
    int questIndex = urlString.indexOf('?');  
    if (questIndex == -1) {  
      return new Request(urlString);  
    }  
    String url = urlString.substring(0, questIndex);  
    String queryString = urlString.substring(questIndex + 1, urlString.length());  
    return new Request(url, getParamsMap(queryString, enc));  
  }  
  
  private static Map<String, String[]> getParamsMap(String queryString, String enc) {  
    Map<String, String[]> paramsMap = new HashMap<String, String[]>();  
    if (queryString != null && queryString.length() > 0) {  
      int ampersandIndex, lastAmpersandIndex = 0;  
      String subStr, param, value;  
      String[] paramPair, values, newValues;  
      do {  
        ampersandIndex = queryString.indexOf('&', lastAmpersandIndex) + 1;  
        if (ampersandIndex > 0) {  
          subStr = queryString.substring(lastAmpersandIndex, ampersandIndex - 1);  
          lastAmpersandIndex = ampersandIndex;  
        } else {  
          subStr = queryString.substring(lastAmpersandIndex);  
        }  
        paramPair = subStr.split("=");  
        param = paramPair[0];  
        value = paramPair.length == 1 ? "" : paramPair[1];  
        try {  
          value = URLDecoder.decode(value, enc);  
        } catch (UnsupportedEncodingException ignored) {  
        }  
        if (paramsMap.containsKey(param)) {  
          values = paramsMap.get(param);  
          int len = values.length;  
          newValues = new String[len + 1];  
          System.arraycopy(values, 0, newValues, 0, len);  
          newValues[len] = value;  
        } else {  
          newValues = new String[] { value };  
        }  
        paramsMap.put(param, newValues);  
      } while (ampersandIndex > 0);  
    }  
    return paramsMap;  
  }  
  
  /** 
   * 请求对象 
   * @author yy 
   * @date Jun 21, 2009 2:17:31 PM 
   */  
  public static class Request implements HttpServletRequest {  
    private String requestURI;  
    private Map<String, String[]> parameterMap;  
  
    Request() {  
      this("");  
    }  
  
    Request(String requestURI) {  
      this.requestURI = requestURI;  
      parameterMap = new HashMap<String, String[]>();  
    }  
  
    Request(String requestURI, Map<String, String[]> parameterMap) {  
      this.requestURI = requestURI;  
      this.parameterMap = parameterMap;  
    }  
  
    /** 
     * 获得指定名称的参数 
     * @param name 
     * @return 
     */  
    public String getParameter(String name) {  
      String[] values = parameterMap.get(name);  
      if (values != null && values.length > 0) {  
        return values[0];  
      }  
      return null;  
    }  
  
    /** 
     * 获得所有的参数名称 
     * @return 
     */  
    public Enumeration<String> getParameterNames() {  
      return Collections.enumeration(parameterMap.keySet());  
    }  
  
    /** 
     * 获得指定名称的参数值(多个) 
     * @param name 
     * @return 
     */  
    public String[] getParameterValues(String name) {  
      return parameterMap.get(name);  
    }  
  
    /** 
     * 获得请求的url地址 
     * @return 
     */  
    public String getRequestURI() {  
      return requestURI;  
    }  
  
    /** 
     * 获得 参数-值Map 
     * @return 
     */  
    public Map<String, String[]> getParameterMap() {  
      return parameterMap;  
    }  
  
    @Override  
    public String toString() {  
      StringBuilder buf = new StringBuilder();  
      buf.append("{");  
      buf.append("\n  url = ").append(this.requestURI);  
      buf.append("\n  paramsMap = {");  
      if (this.parameterMap.size() > 0) {  
        for (Map.Entry<String, String[]> e : this.parameterMap.entrySet()) {  
          buf.append(e.getKey()).append("=").append(Arrays.toString(e.getValue())).append(",");  
        }  
        buf.deleteCharAt(buf.length() - 1);  
      }  
      buf.append("}\n}");  
      return buf.toString();  
    }  
   //  剩下的函数,自己根据需求实现了,一般返回0或者null即可  
   //  这里就不贴了,HttpServletRequest的接口方法也太多了  

	public Object getAttribute(String name) {
		// TODO Auto-generated method stub
		return null;
	}

	public Enumeration getAttributeNames() {
		// TODO Auto-generated method stub
		return null;
	}

	public String getCharacterEncoding() {
		// TODO Auto-generated method stub
		return null;
	}

	public void setCharacterEncoding(String env)
			throws UnsupportedEncodingException {
		// TODO Auto-generated method stub
		
	}

	public int getContentLength() {
		// TODO Auto-generated method stub
		return 0;
	}

	public String getContentType() {
		// TODO Auto-generated method stub
		return null;
	}

	public ServletInputStream getInputStream() throws IOException {
		// TODO Auto-generated method stub
		return null;
	}

	public String getProtocol() {
		// TODO Auto-generated method stub
		return null;
	}

	public String getScheme() {
		// TODO Auto-generated method stub
		return null;
	}

	public String getServerName() {
		// TODO Auto-generated method stub
		return null;
	}

	public int getServerPort() {
		// TODO Auto-generated method stub
		return 0;
	}

	public BufferedReader getReader() throws IOException {
		// TODO Auto-generated method stub
		return null;
	}

	public String getRemoteAddr() {
		// TODO Auto-generated method stub
		return null;
	}

	public String getRemoteHost() {
		// TODO Auto-generated method stub
		return null;
	}

	public void setAttribute(String name, Object o) {
		// TODO Auto-generated method stub
		
	}

	public void removeAttribute(String name) {
		// TODO Auto-generated method stub
		
	}

	public Locale getLocale() {
		// TODO Auto-generated method stub
		return null;
	}

	public Enumeration getLocales() {
		// TODO Auto-generated method stub
		return null;
	}

	public boolean isSecure() {
		// TODO Auto-generated method stub
		return false;
	}

	public RequestDispatcher getRequestDispatcher(String path) {
		// TODO Auto-generated method stub
		return null;
	}

	public String getRealPath(String path) {
		// TODO Auto-generated method stub
		return null;
	}

	public int getRemotePort() {
		// TODO Auto-generated method stub
		return 0;
	}

	public String getLocalName() {
		// TODO Auto-generated method stub
		return null;
	}

	public String getLocalAddr() {
		// TODO Auto-generated method stub
		return null;
	}

	public int getLocalPort() {
		// TODO Auto-generated method stub
		return 0;
	}

	public String getAuthType() {
		// TODO Auto-generated method stub
		return null;
	}

	public Cookie[] getCookies() {
		// TODO Auto-generated method stub
		return null;
	}

	public long getDateHeader(String name) {
		// TODO Auto-generated method stub
		return 0;
	}

	public String getHeader(String name) {
		// TODO Auto-generated method stub
		return null;
	}

	public Enumeration getHeaders(String name) {
		// TODO Auto-generated method stub
		return null;
	}

	public Enumeration getHeaderNames() {
		// TODO Auto-generated method stub
		return null;
	}

	public int getIntHeader(String name) {
		// TODO Auto-generated method stub
		return 0;
	}

	public String getMethod() {
		// TODO Auto-generated method stub
		return null;
	}

	public String getPathInfo() {
		// TODO Auto-generated method stub
		return null;
	}

	public String getPathTranslated() {
		// TODO Auto-generated method stub
		return null;
	}

	public String getContextPath() {
		// TODO Auto-generated method stub
		return null;
	}

	public String getQueryString() {
		// TODO Auto-generated method stub
		return null;
	}

	public String getRemoteUser() {
		// TODO Auto-generated method stub
		return null;
	}

	public boolean isUserInRole(String role) {
		// TODO Auto-generated method stub
		return false;
	}

	public Principal getUserPrincipal() {
		// TODO Auto-generated method stub
		return null;
	}

	public String getRequestedSessionId() {
		// TODO Auto-generated method stub
		return null;
	}

	public StringBuffer getRequestURL() {
		// TODO Auto-generated method stub
		return null;
	}

	public String getServletPath() {
		// TODO Auto-generated method stub
		return null;
	}

	public HttpSession getSession(boolean create) {
		// TODO Auto-generated method stub
		return null;
	}

	public HttpSession getSession() {
		// TODO Auto-generated method stub
		return null;
	}

	public boolean isRequestedSessionIdValid() {
		// TODO Auto-generated method stub
		return false;
	}

	public boolean isRequestedSessionIdFromCookie() {
		// TODO Auto-generated method stub
		return false;
	}

	public boolean isRequestedSessionIdFromURL() {
		// TODO Auto-generated method stub
		return false;
	}

	public boolean isRequestedSessionIdFromUrl() {
		// TODO Auto-generated method stub
		return false;
	}
  }  
}  
