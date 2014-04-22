package com.ryan.java.util.common;


public class ByteUtil
{
  public static final int readInt(byte[] b, int start) {
    int r = 0;
    
    for (int i = 0; i < 4; i++) {
      r <<= 8;
      r += (b[(start + i)] & 0xFF);
    }
    
    return r;
  }
  
  public static final long readLong(byte[] b, int start) {
    long r = 0L;
    
    for (int i = 0; i < 8; i++) {
      r <<= 8;
      r += (b[(start + i)] & 0xFFFF);
    }
    
    return r;
  }
  
  public static final String readString(byte[] b, int start, int len) {
    if (len < 1) {
      return "";
    }
    try
    {
      byte[] d = new byte[len];
      System.arraycopy(b, start, d, 0, len);
      return new String(d, "GBK");
    } catch (Exception ex) {
    	ex.printStackTrace();
    }
    
    return null;
  }
  
  public static final int readShort(byte[] b, int start) {
    int r = 0;
    
    for (int i = 0; i < 2; i++) {
      r <<= 8;
      r += (b[(start + i)] & 0xFF);
    }
    
    return r;
  }
  
  public static final int readByte(byte b) {
    return b & 0xFF;
  }
}
