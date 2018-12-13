/**
 * 甲级PAT1004
 * start： 2018年11月21日22:02:50
 * end:  2018年11月21日23:57:09
 * 考点：
 */

import java.util.*;

class ZJUPAT1004 {
    static Map memberMap = new HashMap<Integer, Integer>();
    static int[][] parentAndChildren;
    static Set<Integer> memberSet = new TreeSet<Integer>();
    static int maxGeneration = 1;


    public static void main(String[] args) {
        //Input
        Scanner scan = new Scanner(System.in);
        int numOfNode = scan.nextInt();
        int numOfNonLeafNode = scan.nextInt();
        parentAndChildren = new int[numOfNonLeafNode][];
        for(int i=0;i<numOfNonLeafNode;i++){
            int father = scan.nextInt();
            int numOfChildren = scan.nextInt();
            parentAndChildren[i] = new int[2+numOfChildren];
            parentAndChildren[i][0] = father;
            parentAndChildren[i][1] = numOfChildren;
            for(int j=0;j<numOfChildren;j++){
                parentAndChildren[i][2+j] = scan.nextInt();
            }
        }
        //bulid tree
        memberMap.put(01, 1);
        memberSet.add(01);
        for(int i=0;i<parentAndChildren.length;i++){
            for(int j=0;j<parentAndChildren[i].length;j++){
                int generation = getGeneration(parentAndChildren[i][j]);
                if(generation>maxGeneration)
                    maxGeneration = generation;
                memberMap.put(parentAndChildren[i][j], generation);
                memberSet.add(parentAndChildren[i][j]);
            }
        }
//        System.out.println(memberMap);
        for(int i=0;i<numOfNonLeafNode;i++){
            memberSet.remove(parentAndChildren[i][0]);
        }
//        System.out.println("Children: " + memberSet);
        int[] countGen = new int[maxGeneration];
        Iterator iter = memberSet.iterator();
        while(iter.hasNext()){
            countGen[(int)memberMap.get((int)iter.next())-1]++;
        }

        //output
        System.out.print(countGen[0]);
        for(int i=1;i<maxGeneration;i++){
            System.out.print(" "+countGen[i]);
        }
    }

    public static int getGeneration(int obj){
        int countGeneration = 1;
        int parent = -999;
        if (memberMap.containsKey(obj)){
            return (int) memberMap.get(obj);
        }
        while(true){
            if (memberMap.containsKey(obj)){
                countGeneration += (int) memberMap.get(obj) - 1;
                return countGeneration;
            }
            parent = isRoot(obj);
            countGeneration++;
            if (parent == 999){
                countGeneration--;
                return countGeneration;
            }
            obj = parent;
        }
    }

    public static int isRoot(int obj){
        for(int i=0;i<parentAndChildren.length;i++){
            for(int j=2;j<parentAndChildren[i].length;j++){
                if(obj == parentAndChildren[i][j])
                    return parentAndChildren[i][0];
            }
        }
        return 999;
    }
}
