/**
 * 1009 Product of Polynomials
 * start: 2018年12月10日21:08:54
 * end: 2018年12月10日22:03:19
 * 考点：Map(dictionary), Map->Iter
 */


import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;

class ZJUPAT1009 {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int APolySize = scan.nextInt();
        Map<Integer, Double> APoly = new HashMap();
        for(int i=0;i<APolySize;i++){
            APoly.put(scan.nextInt(), scan.nextDouble());
        }
        int BPolySize = scan.nextInt();
        Map<Integer, Double> BPoly = new HashMap<>();
        for(int i=0;i<BPolySize;i++){
            BPoly.put(scan.nextInt(), scan.nextDouble());
        }
        Map<Integer, Double> result = new TreeMap<>();
        for(Integer Akey: APoly.keySet()){
            for (Integer Bkey: BPoly.keySet()){
                int exponent = Akey + Bkey;
                if(result.containsKey(exponent)){
                    double original = result.get(exponent);
                    double coefficient = original+APoly.get(Akey)*BPoly.get(Bkey);
                    if(coefficient==0.0)
                        result.remove(exponent);
                    else
                        result.put(exponent, coefficient);
                } else {
                    result.put(exponent, APoly.get(Akey)*BPoly.get(Bkey));
                }
            }
        }

        System.out.print(result.size());
        // "#.0" 保留一位小数 e.g. 6.0001->6.0; "#.#"若小数位为0，则省略 e.g. 6.0001=6
        DecimalFormat df = new DecimalFormat("0.0");
        for(Integer key: ((TreeMap<Integer, Double>) result).descendingKeySet()){
            System.out.print(" " + key + " " + df.format(result.get(key)+0.01));
        }
    }
}
