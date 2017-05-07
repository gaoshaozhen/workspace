package test;

import java.security.MessageDigest;

public class Test
{
    public static byte[] getMd5(byte[] b)
    {
        byte[] newByte = null;
        try
        {
            MessageDigest messageDigest = MessageDigest.getInstance("MD5");
            messageDigest.update(b);
            newByte = messageDigest.digest();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return newByte;
    }

    public static void main(String[] args)
    {
//        http://localhost:8080/javashop/member_order.html?status=0
//        HttpClientUtil.sendHttpPostJson("http://localhost:8080/shop/paytest/pay.shtm", "{\"we\":\"test\"}");
//        Vector vector = new Vector();
//        vector.add("sdf");
//        vector.add("sdvfsdv");
//        vector.clear();
        
        String javaVersion = System
                .getProperty("java.version");
        System.out.println(new JdkVersion1().getJavaVersion());
        System.out.println(new JdkVersion1().getMajorJavaVersion());
    }

    public static String byteArrayToHex(byte[] byteArray)
    {
        // 首先初始化一个字符数组，用来存放每个16进制字符
        char[] hexDigits = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9',
                'A', 'B', 'C', 'D', 'E', 'F' };
        // new一个字符数组，这个就是用来组成结果字符串的（解释一下：一个byte是八位二进制，也就是2位十六进制字符（2的8次方等于16的2次方））
        char[] resultCharArray = new char[byteArray.length * 2];
        // 遍历字节数组，通过位运算（位运算效率高），转换成字符放到字符数组中去
        int index = 0;
        for (byte b : byteArray)
        {
            resultCharArray[index++] = hexDigits[b >>> 4 & 0xf];
            resultCharArray[index++] = hexDigits[b & 0xf];
        }
        // 字符数组组合成字符串返回
        return new String(resultCharArray);
    }
}

class JdkVersion1 {

    /**
     * Constant identifying the 1.3.x JVM (JDK 1.3).
     */
    public static final int JAVA_13 = 0;

    /**
     * Constant identifying the 1.4.x JVM (J2SE 1.4).
     */
    public static final int JAVA_14 = 1;

    /**
     * Constant identifying the 1.5 JVM (Java 5).
     */
    public static final int JAVA_15 = 2;

    /**
     * Constant identifying the 1.6 JVM (Java 6).
     */
    public static final int JAVA_16 = 3;

    /**
     * Constant identifying the 1.7 JVM (Java 7).
     */
    public static final int JAVA_17 = 4;

    /**
     * Constant identifying the 1.8 JVM (Java 8).
     */
    public static final int JAVA_18 = 5;

    /**
     * Constant identifying the 1.9 JVM (Java 9).
     */
    public static final int JAVA_19 = 6;


    private static final String javaVersion;

    private static final int majorJavaVersion;

    static {
        javaVersion = System.getProperty("java.version");
        // version String should look like "1.4.2_10"
        if (javaVersion.contains("1.9.")) {
            majorJavaVersion = JAVA_19;
        }
        else if (javaVersion.contains("1.8.")) {
            majorJavaVersion = JAVA_18;
        }
        else if (javaVersion.contains("1.7.")) {
            majorJavaVersion = JAVA_17;
        }
        else {
            // else leave 1.6 as default (it's either 1.6 or unknown)
            majorJavaVersion = JAVA_16;
        }
    }


    /**
     * Return the full Java version string, as returned by
     * {@code System.getProperty("java.version")}.
     * @return the full Java version string
     * @see System#getProperty(String)
     */
    public static String getJavaVersion() {
        return javaVersion;
    }

    /**
     * Get the major version code. This means we can do things like
     * {@code if (getMajorJavaVersion() >= JAVA_17)}.
     * @return a code comparable to the {@code JAVA_XX} codes in this class
     * @see #JAVA_16
     * @see #JAVA_17
     * @see #JAVA_18
     * @see #JAVA_19
     */
    public static int getMajorJavaVersion() {
        return majorJavaVersion;
    }

}