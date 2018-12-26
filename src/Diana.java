import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;

public class Diana {
  public static void main(String[] args) {
    int size = 0;
    ArrayList<double[]> cluster1 = new ArrayList<>();
    // 测试点
    File file = new File("./data.txt");
    BufferedReader br = null;
    String lineContent = "";
    try{
      br = new BufferedReader(new FileReader(file));
      while((lineContent = br.readLine()) != null){
        String[] temp= lineContent.split(" ");
        double[] X = new double[2];
        X[0] = Double.parseDouble(temp[0]);
        X[1] = Double.parseDouble(temp[1]);
        cluster1.add(X);
      }
      //Diana
      int K = 2;
      ArrayList<ArrayList> allCluster = new ArrayList<>();
      allCluster.add(cluster1);
      while (allCluster.size() == K) {
        ArrayList<double[]> c0 = allCluster.get(getBiggsetClusterIndix(allCluster));
        ArrayList<double[]> cs = new ArrayList<>();

      }

    }catch (Exception e){
      e.printStackTrace();
    } finally {
      if(br != null){
        try {
          br.close();
        }catch (Exception ex){
          ex.printStackTrace();
        }
      }
    }
  }

  private static double diffOfPoints(double[] a, double[] b){
    return Math.pow(Math.pow(a[0] - b[0],2) + Math.pow(a[1] - b[1],2), 1/2);
  }

  static double diffOfCluster(ArrayList<double[]> clu){
    double result = 0;
    for(int i=0;i<clu.size();i++){
      for(int j=i;j<clu.size();j++){
        double temp = diffOfPoints(clu.get(i), clu.get(j));
        if(temp>result){
          result = temp;
        }
      }
    }
    return result;
  }

  static int getBiggsetClusterIndix(ArrayList<ArrayList> allClu){
    int index = -1;
    double value = -1;
    for(int i=0;i<allClu.size();i++){
      double temp = diffOfCluster(allClu.get(i));
      if (temp > value){
        value = temp;
        index = i;
      }
    }
    return index;
  }

  static double[] averageDistanceOneClu(ArrayList<double[]> clu){
    double[] distance = new double[clu.size()];
    for(int i=0;i<distance.length;i++){
      distance[i] = 0;
      System.out.print(i + "点与其他各个点的距离：");
      for(int j=0;j<distance.length;j++){
        double temp = diffOfPoints(clu.get(i), clu.get(j));
        System.out.print(); + temp);
        distance[i] += ;
      }
      distance[i] /= distance.length;
    }
  }
}
