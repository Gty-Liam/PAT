import java.sql.Timestamp;
import java.util.Scanner;

/**
 * 1006 Sign In and Sign Out
 * start：2018年11月25日17:59:41
 * end：2018年11月25日18:17:06
 * 考点：timestamp 时间的相减
 */
public class ZJUPAT1006 {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int amount = scan.nextInt();
        String[][] records = new String[amount][3];
        for(int i=0;i<amount;i++){
            records[i][0] = scan.next();
            records[i][1] = "2000-01-01 " + scan.next();
            records[i][2] = "2000-01-01 " + scan.next();
        }
        long unlockTime = Timestamp.valueOf(records[0][1]).getTime();
        int indexOfunlockPerson = 0;
        for(int i=1;i<amount;i++){
            long temp = Timestamp.valueOf(records[i][1]).getTime();
            if((unlockTime - temp) > 0){
                unlockTime = temp;
                indexOfunlockPerson = i;
            }
        }
        long lockTime = Timestamp.valueOf(records[0][2]).getTime();
        int indexOfLockPerson = 0;
        for(int i=1;i<amount;i++){
            long temp = Timestamp.valueOf(records[i][2]).getTime();
            if((lockTime - temp) < 0){
                lockTime = temp;
                indexOfLockPerson = i;
            }
        }
        System.out.println(records[indexOfunlockPerson][0] + " " + records[indexOfLockPerson][0]);
    }
}
