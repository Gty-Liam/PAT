import java.util.Scanner;

/**
 * make out:2018年12月13日21:35:56
 * start: 2018年12月13日22:18:46
 * end: 2018年12月13日23:18:34
 * 考点：进制转换
 */
class Main {
    static String N1 = "jiho2";  //作为radix表示的进制
    static String N2 = "lh";
    static int which = 1;
    static long radix = 36;
    static long resultRadix;
    static long top; //大的数
    static long bottom;
    public static void main(String[] args) {

        Scanner scan = new Scanner(System.in);
        N1 = scan.next();
        N2 = scan.next();
        which = scan.nextInt();
        radix = scan.nextLong();
        if(which == 2) {
            String temp = N1;
            N1 = N2;
            N2 = temp;
        }
        long N1Decimal = convertToDecimal(N1, radix);
        top = N1Decimal;  //可能可以优化
        bottom = 0;
        boolean isFind = false;
        resultRadix = (top + bottom) / 2;
        while(bottom != resultRadix) {
//            System.out.println(convertToDecimal(N2, resultRadix) + " ----- " + convertToDecimal(N1, radix));
            if (convertToDecimal(N2, resultRadix) > convertToDecimal(N1, radix)) {
                top = resultRadix;
            } else if(convertToDecimal(N2, resultRadix) < convertToDecimal(N1, radix)){
                bottom = resultRadix;
            } else {
                isFind = true;
                break;
            }
            resultRadix = (top + bottom) / 2;
        }
        if(isFind){
            System.out.println(resultRadix);
        } else {
            if (convertToDecimal(N2, resultRadix+1) == convertToDecimal(N1, radix))
                System.out.println(resultRadix+1);
            else
                System.out.println("Impossible");
        }
//        System.out.println(convertToDecimal("110", 2));
    }

    static long convertToDecimal(String number, long rad){
        long total = 0;
        char[] numberChars = number.toCharArray();
        for(int i=0;i < numberChars.length; i++){
            total += getCharNumber(numberChars[i]) * Math.pow(rad, numberChars.length-i-1);
        }
        return total;
    }

    static int getCharNumber(char theChar){
        if(theChar-'a' >= 0){
            return 10+theChar-'a';
        } else {
            return Integer.parseInt(theChar+"");
        }
    }
}
