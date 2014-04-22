package com.ryan.java.util.common;


public class IpUtil
{
  static int IP_ARRAY_LENGTH = 4;
  
  public static String decodeIp(long ip)
  {
    if (ip <= 0L) {
      return "";
    }
    StringBuilder sb = new StringBuilder();
    sb.append(ip >> 24 & 0xFF).append(".")
      .append(ip >> 16 & 0xFF).append(".")
      .append(ip >> 8 & 0xFF).append(".")
      .append(ip & 0xFF);
    return sb.toString();
  }
  
  public static long encodeIp(String ipString)
  {
    long ipNumber = 0L;
    if (StringUtil.isNotBlank(ipString)) {
      String[] ipArray = StringUtil.split(ipString, ".");
      if (ipArray.length == IP_ARRAY_LENGTH) {
        ipNumber = Long.parseLong(ipArray[0]) << 24 | 
          Long.parseLong(ipArray[1]) << 16 | 
          Long.parseLong(ipArray[2]) << 8 | 
          Long.parseLong(ipArray[3]);
      }
    }
    return ipNumber;
  }
}
