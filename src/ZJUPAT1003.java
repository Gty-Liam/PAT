import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class ZJUPAT1003{
    private static int noOfPath = 0;  //最短路径的数量
    private static int shortestLength = 1000000000;
    private static int totalRescue = 0;
    private static int cityNo;
    private static int roadNo;
    private static int startPoint;
    private static int endPoint;
    private static int[] rescue;  //各城救援人数
    private static int[][] map;

    public static void main(String[] args) throws FileNotFoundException {
//        File f = new File("1.txt");
        Scanner scan = new Scanner(System.in);
        cityNo = scan.nextInt();
        roadNo = scan.nextInt();
        startPoint = scan.nextInt();
        endPoint = scan.nextInt();
        rescue = new int[cityNo];
        for(int i=0;i<cityNo;i++){
            rescue[i] = scan.nextInt();
        }
        map = new int[roadNo][3];
        for(int i=0;i<roadNo;i++) {
            map[i][0] = scan.nextInt();
            map[i][1] = scan.nextInt();
            map[i][2] = scan.nextInt();
        }
        List<Integer> startPointNeighbor = getNeighborPoint(startPoint);
        for(int i=0;i<startPointNeighbor.size();i++){
            List<Integer> passPoint = new ArrayList<>();
            passPoint.add(startPoint);
            pathSearching(startPoint, startPointNeighbor.get(i), getTwoPointLength(startPoint,startPointNeighbor.get(i)), 0, passPoint, rescue[startPoint]);
        }
        System.out.println(noOfPath+" "+totalRescue);
    }

    public static void pathSearching(int start, int end, int twoPointsLength, int currentLength, List<Integer> passPoint, int currentRescue){
        if(end == endPoint){
            currentLength += twoPointsLength;
            currentRescue += rescue[endPoint];
            if (currentLength < shortestLength){  //刷新最小距离
                noOfPath = 1;
                shortestLength = currentLength;
                totalRescue = currentRescue;
            } else if (currentLength == shortestLength) {
                noOfPath ++;
                if(currentRescue > totalRescue){
                    totalRescue = currentRescue;
                }
            }
        } else {
            if(passPoint.contains(end)){
                //重复即形成环路,do nothing
            } else {
                start = end; //走到end点
                currentLength += twoPointsLength;
                currentRescue += rescue[end];
                passPoint.add(end);
                List<Integer> neighborPointList = getNeighborPoint(end);
                for(int i=0;i<neighborPointList.size();i++){
                    pathSearching(end, neighborPointList.get(i), getTwoPointLength(end,neighborPointList.get(i)), currentLength, passPoint, currentRescue);
                }

            }
        }
    }
    public static List<Integer> getNeighborPoint(int point) {
        List<Integer> neighbor = new ArrayList<>();
        for(int i=0;i<roadNo;i++){
            if(map[i][0] == point){
                neighbor.add(map[i][1]);
            } else if(map[i][1] == point){
                neighbor.add(map[i][0]);
            }
        }
        return neighbor;
    }

    public static int getTwoPointLength(int a, int b){
        for(int i=0;i<roadNo;i++){
            if(map[i][0] == a){
               if(map[i][1] == b)
                   return map[i][2];
            } else if(map[i][0] == b) {
                if (map[i][1] == a)
                    return map[i][2];
            }
        }
        System.out.println("ERROR 两点之间没有路");
        return 0;
    }
}
