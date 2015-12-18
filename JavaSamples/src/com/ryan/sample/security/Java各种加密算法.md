#Java 各种加密算法
基本单向加密算法：
> * BASE64 严格地说，属于编码格式，而非加密算法
> * MD5(Message Digest algorithm 5，信息摘要算法)
> * SHA(Secure Hash Algorithm，安全散列算法)
> * HMAC(Hash Message Authentication Code，散列消息鉴别码)

MD5、SHA、HMAC 是非可逆加密，即加密后不可解密回来

###Base64 加密/解密
```
/**
 * 使用 BASE64Decoder/BASE64Encoder 进行base64的加密/解密操作
 */
public class Base64 {

	public static byte[] decryptBASE64(String key) throws IOException {
		return (new BASE64Decoder()).decodeBuffer(key);
	}

	public static String encryptBASE64(byte[] key) {
		return (new BASE64Encoder()).encodeBuffer(key);
	}
	
}
```

###MD5 加密算法
```
public static String getMD5code(String string) {
		try {
			MessageDigest md = MessageDigest.getInstance("MD5");
			md.update(string.getBytes());
			byte b[] = md.digest();

			int i;

			StringBuffer buf = new StringBuffer("");
			for (int offset = 0; offset < b.length; offset++) {
				i = b[offset];
				if (i < 0)
					i += 256;
				if (i < 16)
					buf.append("0");
				buf.append(Integer.toHexString(i));
			}

			return buf.toString();

		} catch (Exception e) {
			return "";
		}
	}
```

###SHA 加密算法
>安全散列算法SHA（Secure Hash Algorithm，SHA）是一种数据加密算法，该算法经过加密专家多年来的发展和改进已日益完善，现在已成为公认的最安全的散列算法之一，并被广泛使用。该算法的思想是接收一段明文，然后以一种不可逆的方式将它转换成一段（通常更小）密文，也可以简单的理解为取一串输入码（称为预映射或信息），并把它们转化为长度较短、位数固定的输出序列即散列值（也称为信息摘要或信息认证代码）的过程。散列函数值可以说是对明文的一种“指纹”或是“摘要”所以对散列值的数字签名就可以视为对此明文的数字签名。

```
     /***
	 * SHA加密 生成40位SHA码
	 * 
	 * @param 待加密字符串
	 * @return 返回40位SHA码
	 */
	public static String shaEncode(String inStr) throws Exception {
		MessageDigest sha = null;
		try {
			sha = MessageDigest.getInstance("SHA");
		} catch (Exception e) {
			System.out.println(e.toString());
			e.printStackTrace();
			return "";
		}

		byte[] byteArray = inStr.getBytes("UTF-8");
		byte[] md5Bytes = sha.digest(byteArray);
		StringBuffer hexValue = new StringBuffer();
		for (int i = 0; i < md5Bytes.length; i++) {
			int val = ((int) md5Bytes[i]) & 0xff;
			if (val < 16) {
				hexValue.append("0");
			}
			hexValue.append(Integer.toHexString(val));
		}
		return hexValue.toString();
	}
```

#### 参考博客
[常用加密算法的Java实现(一)](http://www.blogjava.net/amigoxie/archive/2014/06/01/414299.html)
[各种Java加密算法](http://www.open-open.com/lib/view/open1397274257325.html)

