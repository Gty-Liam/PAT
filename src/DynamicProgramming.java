import java.util.Scanner;

public class DynamicProgramming {
  static int max(int a, int b){
    int t;
    if(a > b)
      return a;
    else
      return b;
  }

  public static void main(String[] args) {
    int keep = -999;
    int unkeep = 0;
    int n = 0; //取样点个数
    Scanner in = new Scanner(System.in);
    String str = in.nextLine();
    String[] strs = str.split(" ");
    n = Integer.parseInt(strs[0]);
    int[] price = new int[n];
    for(int i=0;i<n;i++){
      price[i] = Integer.parseInt(strs[i+1]);
    }

    for(int i=0;i<n;i++){
      unkeep = max(unkeep, keep+price[i]);
      keep = max(keep, unkeep-price[i]);
    }
    System.out.println(unkeep);
  }
}
