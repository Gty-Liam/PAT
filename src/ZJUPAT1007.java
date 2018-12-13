import java.util.Scanner;

/**
 * 1007 Maximum Subsequence Sum
 * start：2018年11月25日18:24:44
 * end：2018年11月25日18:53:03
 * 考点：动态规划
 */
class ZJUPAT1007 {

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int size = scan.nextInt();
//        int[] seq = new int[10010];
        int subSeqStart = 0;
        int subSeqEnd = size - 1;
        int max = -1;
        int totalFromAbandon = 0;
        int maxStart = 0;
        int maxEnd = 0;
        int firstnumber = -1;
        int lastnumber = -1;
        int temp;
        boolean re = true; //判断是不是重新开始一段子序列

        for(int i=0;i<size;i++){
            temp = scan.nextInt();

            if(i==0){
                firstnumber = temp;
            }
            if(i==size-1){
                lastnumber = temp;
            }

            totalFromAbandon += temp;;
            if (totalFromAbandon < 0){
                subSeqStart = temp;
                subSeqEnd = temp;
                totalFromAbandon = 0;
                re = true;
            } else {
                if(re) {
                    subSeqStart = temp;
                    re = false;
                }
                subSeqEnd = temp;
                if(totalFromAbandon>max){
                    max = totalFromAbandon;
                    maxStart = subSeqStart;
                    maxEnd = subSeqEnd;
                }
            }
        }
        if(max < 0){
            System.out.println(0 + " " + firstnumber + " " + lastnumber);
        } else{
            System.out.println(max + " " + maxStart + " " + maxEnd);
        }
    }
}
