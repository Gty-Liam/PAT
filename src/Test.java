import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Test {
    static boolean[] visited;
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        //输入操作
        int n = input.nextInt();
        int m = input.nextInt();
        int i = 0,j = 0,child,id,k;//id指本结点编号，child指孩子结点编号，k为孩子结点数目
        ArrayList<Integer> list;
        HashMap<Integer,ArrayList<Integer>> map = new HashMap<Integer, ArrayList<Integer>>();//用map来存储这个结构
        int[] record = new int[n];//记录每层叶子结点数目的结果数组，由于结点只有n个，所以最多n层

        while(i<m){
            id = input.nextInt();
            k = input.nextInt();
            list = new ArrayList<Integer>();
            for(j=0;j<k;j++){
                child = input.nextInt();
                list.add(child);
            }
            map.put(id, list);
            i++;
        }

        int p = DFS(map,record,1,0,0);
        for(i=0;i<p;i++)
            System.out.print(record[i] + " ");
        System.out.print(record[i]);
    }

    //map为该树结构，record数组用于记录结果，node为当前遍历到的结点，level为当前层是第几层，height用来记录树高度，为最后输出作准备
    public static int DFS(Map<Integer,ArrayList<Integer>> map,int[] record,int node,int level,int height){
        ArrayList<Integer> list = map.get(node);

        if(list==null){
            record[level]++;//每次遇到叶子结点，相应层数记录+1
            return height;
        }

        for(int i=0;i<list.size();i++){
            height = Math.max(height, DFS(map,record,list.get(i),level+1,level+1));

        }
        return height;
    }
}
