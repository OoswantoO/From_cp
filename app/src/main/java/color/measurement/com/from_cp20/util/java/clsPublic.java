package color.measurement.com.from_cp20.util.java;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;

/**
 * Created by wpc on 2016/9/14.
 */
public class clsPublic {

    public static String insertStringInParticularPosition(String src, String dec, int position) {
        StringBuffer stringBuffer = new StringBuffer(src);
        return stringBuffer.insert(position, dec).toString();

    }

    public static float[] getFloat(byte[] b) {

        float[] fs = new float[b.length / 4];

        if (b.length > 0 && b.length % 4 == 0) {
            for (int i = 0; i < fs.length; i++) {
                int accum = 0;
                accum = accum | (b[i * 4] & 0xff) << 0;
                accum = accum | (b[i * 4 + 1] & 0xff) << 8;
                accum = accum | (b[i * 4 + 2] & 0xff) << 16;
                accum = accum | (b[i * 4 + 3] & 0xff) << 24;
                fs[i] = Float.intBitsToFloat(accum);
            }
        } else {
        }
        return fs;
    }

//    public static double[] getDouble(byte[] b) {
//
//        double[] fs = new double[b.length / 4];
//
//        if (b.length > 0 && b.length % 4 == 0) {
//            for (int i = 0; i < fs.length; i++) {
//                int accum = 0;
//                accum = accum | (b[i * 4] & 0xff) << 0;
//                accum = accum | (b[i * 4 + 1] & 0xff) << 8;
//                accum = accum | (b[i * 4 + 2] & 0xff) << 16;
//                accum = accum | (b[i * 4 + 3] & 0xff) << 24;
//                fs[i] = Float.intBitsToFloat(accum);
//            }
//        } else {
//            Log.i("getFloat", "数据源长度检验不符合");
//        }
//        return fs;
//    }

    public static ArrayList<Double> getDouble(byte[] b, int length) {
        ArrayList<Double> fs = new ArrayList<>();
        if (b.length > 0 && b.length % 4 == 0) {
            for (int i = 0; i < length; i++) {
                int accum = 0;
                accum = accum | (b[i * 4] & 0xff) << 0;
                accum = accum | (b[i * 4 + 1] & 0xff) << 8;
                accum = accum | (b[i * 4 + 2] & 0xff) << 16;
                accum = accum | (b[i * 4 + 3] & 0xff) << 24;
                fs.add((double) Float.intBitsToFloat(accum));
            }
        }
        return fs;
    }

    public static byte[] hexStringToByte(String hex) {
        int len = (hex.length() / 2);
        byte[] result = new byte[len];
        char[] achar = hex.toCharArray();
        for (int i = 0; i < len; i++) {
            int pos = i * 2;
            result[i] = (byte) (toByte(achar[pos]) << 4 | toByte(achar[pos + 1]));
        }
        return result;
    }

    private static byte toByte(char c) {
        byte b = (byte) "0123456789ABCDEF".indexOf(c);
        return b;
    }

    /**
     * 把字节数组转换成16进制字符串
     *
     * @param bArray
     * @return
     */
    public static final String bytesToHexString(byte[] bArray) {
        StringBuffer sb = new StringBuffer(bArray.length);
        String sTemp;
        for (int i = 0; i < bArray.length; i++) {
            sTemp = Integer.toHexString(0xFF & bArray[i]);
            if (sTemp.length() < 2)
                sb.append(0);
            sb.append(sTemp.toUpperCase());
        }
        return sb.toString();
    }

    public static Short[] toShortArray(byte[] src) {
        int count = src.length / 2;
        Short[] dest = new Short[count];
        for (int i = 0; i < count; i++) {
            dest[i] = (short) (((src[i * 2 + 1] & 0xff) << 8) + (src[2 * i] & 0xff));
        }
        return dest;
    }

    public static byte[] toByteArray(short[] src) {

        int count = src.length;
        byte[] dest = new byte[count * 2];
        for (int i = 0; i < count; i++) {
            dest[i * 2] = (byte) (src[i] >> 8);
            dest[i * 2 + 1] = (byte) (src[i] >> 0);
        }

        return dest;
    }

/** */
    /**
     * 把字节数组转换为对象
     *
     * @param bytes
     * @return
     * @throws IOException
     * @throws ClassNotFoundException
     */
    public static final Object bytesToObject(byte[] bytes) throws IOException, ClassNotFoundException {
        ByteArrayInputStream in;
        in = new ByteArrayInputStream(bytes);
        ObjectInputStream oi = new ObjectInputStream(in);
        Object o = oi.readObject();
        oi.close();
        return o;
    }

/** */
    /**
     * 把可序列化对象转换成字节数组
     *
     * @param s
     * @return
     * @throws IOException
     */
    public static final byte[] objectToBytes(Serializable s) throws IOException {
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        ObjectOutputStream ot = new ObjectOutputStream(out);
        ot.writeObject(s);
        ot.flush();
        ot.close();
        return out.toByteArray();
    }

    public static final String objectToHexString(Serializable s) throws IOException {
        return bytesToHexString(objectToBytes(s));
    }

    public static final Object hexStringToObject(String hex) throws IOException, ClassNotFoundException {
        return bytesToObject(hexStringToByte(hex));
    }

/** */
    /**
     * @函数功能: BCD码转为10进制串(阿拉伯数据)
     * @输入参数: BCD码
     * @输出结果: 10进制串
     */
    public static String bcd2Str(byte[] bytes) {
        StringBuffer temp = new StringBuffer(bytes.length * 2);

        for (int i = 0; i < bytes.length; i++) {
            temp.append((byte) ((bytes[i] & 0xf0) >>> 4));
            temp.append((byte) (bytes[i] & 0x0f));
        }
        return temp.toString().substring(0, 1).equalsIgnoreCase("0") ? temp.toString().substring(1) : temp.toString();
    }

/** */
    /**
     * @函数功能: 10进制串转为BCD码
     * @输入参数: 10进制串
     * @输出结果: BCD码
     */
    public static byte[] str2Bcd(String asc) {
        int len = asc.length();
        int mod = len % 2;

        if (mod != 0) {
            asc = "0" + asc;
            len = asc.length();
        }

        byte abt[] = new byte[len];
        if (len >= 2) {
            len = len / 2;
        }

        byte bbt[] = new byte[len];
        abt = asc.getBytes();
        int j, k;

        for (int p = 0; p < asc.length() / 2; p++) {
            if ((abt[2 * p] >= '0') && (abt[2 * p] <= '9')) {
                j = abt[2 * p] - '0';
            } else if ((abt[2 * p] >= 'a') && (abt[2 * p] <= 'z')) {
                j = abt[2 * p] - 'a' + 0x0a;
            } else {
                j = abt[2 * p] - 'A' + 0x0a;
            }

            if ((abt[2 * p + 1] >= '0') && (abt[2 * p + 1] <= '9')) {
                k = abt[2 * p + 1] - '0';
            } else if ((abt[2 * p + 1] >= 'a') && (abt[2 * p + 1] <= 'z')) {
                k = abt[2 * p + 1] - 'a' + 0x0a;
            } else {
                k = abt[2 * p + 1] - 'A' + 0x0a;
            }

            int a = (j << 4) + k;
            byte b = (byte) a;
            bbt[p] = b;
        }
        return bbt;
    }
/** */
    /**
     * @函数功能: BCD码转ASC码
     * @输入参数: BCD串
     * @输出结果: ASC码
     */
//    public static String BCD2ASC(byte[] bytes) {
//        StringBuffer temp = new StringBuffer(bytes.length * 2);
//
//        for (int i = 0; i < bytes.length; i++) {
//            int h = ((bytes[i] & 0xf0) >>> 4);
//            int l = (bytes[i] & 0x0f);
//            temp.append(BToA[h]).append(BToA[l]);
//        }
//        return temp.toString();
//    }

/** */
    /**
     * MD5加密字符串，返回加密后的16进制字符串
     *
     * @param origin
     * @return
     */
    public static String MD5EncodeToHex(String origin) {
        return bytesToHexString(MD5Encode(origin));
    }

/** */
    /**
     * MD5加密字符串，返回加密后的字节数组
     *
     * @param origin
     * @return
     */
    public static byte[] MD5Encode(String origin) {
        return MD5Encode(origin.getBytes());
    }

/** */
    /**
     * MD5加密字节数组，返回加密后的字节数组
     *
     * @param bytes
     * @return
     */
    public static byte[] MD5Encode(byte[] bytes) {
        MessageDigest md = null;
        try {
            md = MessageDigest.getInstance("MD5");
            return md.digest(bytes);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return new byte[0];
        }

    }
}