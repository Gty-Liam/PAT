/**
 * 1008 Elevator
 * start: 2018年11月26日17:47:12
 * end: 2018年11月26日18:06:07
 */

import java.util.Scanner;

public class ZJUPAT1008 {
    static int currentFloor = 0;
    static int MOVEUP = 6;
    static int MOVEDOWN = 4;
    static int STAY = 5;
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int[] floorLists = new int[scan.nextInt()];
        int totalTime = 0;
        for(int i=0;i<floorLists.length;i++){
            floorLists[i] = scan.nextInt();
            totalTime += moveTo(floorLists[i]);
            currentFloor = floorLists[i];
        }
        System.out.println(totalTime);
    }

    public static int moveTo(int target){
        int diff = target - currentFloor;
        int time = 0;
        if (diff>0){
            //向上移动
            return diff * MOVEUP + STAY;
        } else if(diff<0){
            //向下移动
            return (-diff) * MOVEDOWN + STAY;
        } else {
            return STAY;
        }
    }
}
