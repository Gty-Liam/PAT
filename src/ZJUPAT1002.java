/**
 * beginning: 2018-10-8 21:38:38
 * ending: 2018-10-8 22:19:18
 */

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class ZJUPAT1002 {
  public static void main(String[] main){
    Scanner scan = new Scanner(System.in);
    double[] firPolynomial = new double[1001];
    double[] secPolynomial = new double[1001];
    int firLineSize = scan.nextInt();
    for(int i=0;i<firLineSize;i++){
      firPolynomial[scan.nextInt()] = scan.nextDouble();
    }
    int secLineSize = scan.nextInt();
    for(int i=0;i<secLineSize;i++){
      secPolynomial[scan.nextInt()] = scan.nextDouble();
    }

    double[] sum = new double[1001];
    List<Integer> count = new ArrayList<>();
    for(int i=0;i<1001;i++){
      sum[i] = secPolynomial[i] + firPolynomial[i] + 1e-20;
      if(!(sum[i] < 1e-8 && -1e-8 < sum[i])){
        count.add(i);
      }
    }
    System.out.print(count.size());
    DecimalFormat df = new DecimalFormat("0.0");
    for(int i=count.size()-1;i>=0;i--){
      System.out.print(" " + count.get(i) + " " + df.format(sum[count.get(i)]));
    }
  }
}
