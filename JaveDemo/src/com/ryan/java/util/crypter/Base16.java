package com.ryan.java.util.crypter;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;

public class Base16
{
  private static final String Base16 = "0123456789ABCDEF";

  public static final String toString(byte[] b)
  {
    ByteArrayOutputStream os = new ByteArrayOutputStream();

    for (int i = 0; i < b.length; i++) {
      short value = (short)(b[i] & 0xFF);
      byte high = (byte)(value >> 4);
      byte low = (byte)(value & 0xF);
      os.write("0123456789ABCDEF".charAt(high));
      os.write("0123456789ABCDEF".charAt(low));
    }

    return new String(os.toByteArray());
  }

  public static final byte[] fromString(String str)
  {
    ByteArrayOutputStream bs = new ByteArrayOutputStream();
    byte[] raw = str.getBytes();

    for (int i = 0; i < raw.length; i++) {
      if (!Character.isWhitespace((char)raw[i])) {
        bs.write(raw[i]);
      }
    }

    byte[] in = bs.toByteArray();

    if (in.length % 2 != 0) {
      return null;
    }

    bs.reset();

    DataOutputStream ds = new DataOutputStream(bs);

    for (int i = 0; i < in.length; i += 2) {
      byte high = (byte)"0123456789ABCDEF".indexOf(Character.toUpperCase(
        (char)in[i]));
      byte low = (byte)"0123456789ABCDEF".indexOf(Character.toUpperCase(
        (char)in[(i + 1)]));
      try
      {
        ds.writeByte((high << 4) + low);
      }
      catch (IOException localIOException) {
      }
    }
    return bs.toByteArray();
  }
}