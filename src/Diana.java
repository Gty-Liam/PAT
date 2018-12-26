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
      int K = 4;
      ArrayList<ArrayList<double[]>> allCluster = new ArrayList<>();
      allCluster.add(cluster1);
      while (allCluster.size() < K) {
        ArrayList<double[]> c0 = allCluster.get(getBiggsetClusterIndix(allCluster));  //选出直径最大的簇
        ArrayList<double[]> cs = new ArrayList<>();  //最大的簇的分簇
        double[] distance = averageDistanceOneClu(c0);
        int outIndex = getOutPoint(distance);
        cs.add(c0.remove(outIndex));  //找到第一个离群点,转移到cs
        while(true){  //没有离cs更近的点了
          boolean isFind = false;
          for(int i=0;i<c0.size();i++){
            double pToS = getDistancePointCluster(c0.get(i),cs);
            ArrayList<double[]> temp = new ArrayList<>(c0); //建一个临时的c0副本，把其中的i删掉
            temp.remove(i);
            double pTp0 = getDistancePointCluster(c0.get(i), temp);
            if(pToS <= pTp0){
              cs.add(c0.remove(i));
              isFind = true;
              break;
            }
          }
          if(!isFind){
            break;
          }
        }
        allCluster.add(cs);
      }
      //output
      for(ArrayList<double[]> clu: allCluster){
        System.out.println();
        for(double[] point: clu){
          System.out.print("(" + point[0] + "," + point[1] + ")");
        }
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
    double temp = Math.pow(a[0] - b[0],2) + Math.pow(a[1] - b[1],2);
    double result = Math.pow(temp, 1/2.0);
    return result;
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

  static int getBiggsetClusterIndix(ArrayList<ArrayList<double[]>> allClu){
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

  /***
   * 返回一个簇中每个点与平均距离
   * @param clu
   * @return
   */
  static double[] averageDistanceOneClu(ArrayList<double[]> clu){
    double[] distance = new double[clu.size()];
    for(int i=0;i<distance.length;i++){
      distance[i] = 0;
//      System.out.println();
//      System.out.print(i + "点与其他各个点的距离：");
      for(int j=0;j<distance.length;j++){
        double temp = diffOfPoints(clu.get(i), clu.get(j));
//        System.out.print(temp + "  ");
        distance[i] += temp;
      }
      distance[i] /= distance.length;
    }
    return distance;
  }

  /**
   * 得到簇中出局的点(平均数最大的点)的下标
   * @return
   */
  static int getOutPoint(double[] arr){
    int index = -1;
    double value = -1;
    for (int i=0;i<arr.length;i++){
      if (value < arr[i]){
        index = i;
        value = arr[i];
      }
    }
    return index;
  }

  /**
   * 得到一个点和一个簇之间的最小距离
   * @param point
   * @param clu
   * @return
   */
  static double getDistancePointCluster(double[] point, ArrayList<double[]> clu){
    double distance = 9999;
    if(clu.size()==0)
      return -1;
    for(double[] p: clu){
      double temp = diffOfPoints(p, point);
      if (distance > temp)
        distance = temp;
    }
    return distance;
  }
}
