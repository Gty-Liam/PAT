import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;

public class ZJUPAT1001 {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        String line = scan.nextLine();
        String[] arr = line.split(" ");
        int a = Integer.parseInt(arr[0]);
        int b = Integer.parseInt(arr[1]);
        int sum = a + b;
        boolean negative = false;
        if (sum < 0) {
            negative = true;
            sum = 0 - sum;
        }
        List<String> list = new ArrayList<String>();
        String sumStr = sum + "";
        String newSumStr = "";
        while (sumStr.length() != 0) {
            if (sumStr.length() <= 3) {
                list.add(sumStr);
                sumStr = "";
            } else {
                list.add(sumStr.substring(sumStr.length() - 3));
                sumStr = sumStr.substring(0, sumStr.length() - 3);
            }
        }

        for (int i = list.size(); i > 0; i--) {
            newSumStr += list.get(i - 1);
            if (i != 1) {
                newSumStr += ",";
            }
        }
        if (negative) {
            newSumStr = "-" + newSumStr;
        }
        System.out.println(newSumStr);
    }
}