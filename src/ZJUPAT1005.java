import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
 * 1005 Spell It Right
 * start: 2018年11月25日17:33:42
 * end: 2018年11月25日17:52:44
 * 考点：String <-> Char[]
 */
public class ZJUPAT1005 {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        String numberStr = scan.next();
        char[] numberChar = numberStr.toCharArray();
        int sum = 0;
        for(int i=0;i<numberChar.length;i++){
            sum += Integer.parseInt(numberChar[i]+"");
        }
        char[] sumChar = (sum + "").toCharArray();
        Map map = new HashMap();
        map.put('1', "one");
        map.put('2', "two");
        map.put('3', "three");
        map.put('4', "four");
        map.put('5', "five");
        map.put('6', "six");
        map.put('7', "seven");
        map.put('8', "eight");
        map.put('9', "nine");
        map.put('0', "zero");

        System.out.print(map.get(sumChar[0]));
        for(int i=1;i<sumChar.length;i++){
            System.out.print(" " + map.get(sumChar[i]));
        }
    }
}


