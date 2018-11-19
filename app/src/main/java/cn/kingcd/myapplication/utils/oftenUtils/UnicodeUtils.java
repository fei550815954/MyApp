package cn.kingcd.myapplication.utils.oftenUtils;

/**
 * ==============================================
 * Unicode转换String
 * ==============================================
 * 版权所有 违法必究
 * <p>
 * 创建作者：lilingfei
 * <p>
 * 创建时间：2016/11/22 0022.
 * <p>
 * 修订历史：
 * <p>
 * 修订时间：
 * ==============================================
 * ==================《程序员》==================
 * =======十年生死两茫茫，写程序，到天亮。=======
 * ==============千行代码，Bug何处藏。===========
 * =======纵使上线又怎样，朝令改，夕断肠。=======
 * ----------------------------------------------
 * ======领导每天新想法，天天改，日日忙。========
 * =============相顾无言，惟有泪千行。===========
 * ======每晚灯火阑珊处，夜难寐，加班狂。========
 * ==============================================
 */

public class UnicodeUtils {

    /**
     * Don't let anyone instantiate this class.
     */
    private UnicodeUtils() {
        throw new Error("Do not need instantiate!");
    }

    /**
     * 速度快
     * @param theString
     * @return
     */
    public static String Unicode2String(String theString) {
        char aChar;
        int len = theString.length();
        StringBuffer outBuffer = new StringBuffer(len);
        for (int x = 0; x < len; ) {
            aChar = theString.charAt(x++);
            if (aChar == '\\') {
                aChar = theString.charAt(x++);
                if (aChar == 'u') {
                    // Read the xxxx
                    int value = 0;
                    for (int i = 0; i < 4; i++) {
                        aChar = theString.charAt(x++);
                        switch (aChar) {
                            case '0':
                            case '1':
                            case '2':
                            case '3':
                            case '4':
                            case '5':
                            case '6':
                            case '7':
                            case '8':
                            case '9':
                                value = (value << 4) + aChar - '0';
                                break;
                            case 'a':
                            case 'b':
                            case 'c':
                            case 'd':
                            case 'e':
                            case 'f':
                                value = (value << 4) + 10 + aChar - 'a';
                                break;
                            case 'A':
                            case 'B':
                            case 'C':
                            case 'D':
                            case 'E':
                            case 'F':
                                value = (value << 4) + 10 + aChar - 'A';
                                break;
                            default:
                                throw new IllegalArgumentException(
                                        "Malformed   \\uxxxx   encoding.");
                        }

                    }
                    outBuffer.append((char) value);
                } else {
                    if (aChar == 't')
                        aChar = '\t';
                    else if (aChar == 'r')
                        aChar = '\r';
                    else if (aChar == 'n')
                        aChar = '\n';
                    else if (aChar == 'f')
                        aChar = '\f';
                    outBuffer.append(aChar);
                }
            } else
                outBuffer.append(aChar);
        }
        return outBuffer.toString();
    }

    /**
     * 速度慢
     * @param string
     * @return
     */
    public static String unicode2String2(String string) {
        try {
            byte[] converttoBytes = string.getBytes("UTF-8");
            String s2 = new String(converttoBytes, "UTF-8");
            return s2;
        } catch (Exception e) {
            e.printStackTrace();
            return e.getMessage();
        }
    }

}
