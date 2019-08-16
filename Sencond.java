import java.util.Scanner;
import java.util.Arrays;
public class Main {
     public static int uniquePaths(int m, int n) {
        int[] cur = new int[n];
        Arrays.fill(cur,1);
        for (int i = 1; i < m;i++){
            for (int j = 1; j < n; j++){
                cur[j] += cur[j-1] ;
            }
        }
        return cur[n-1];
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
          int a = in.nextInt();
          int b = in.nextInt();
            System.out.println(uniquePaths(a,b));
    }
}

///1..https://leetcode.com/problems/maximum-sum-circular-subarray/
///2 ..https://leetcode-cn.com/problems/unique-paths/solution/dong-tai-gui-hua-by-powcai-2/
