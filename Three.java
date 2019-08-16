import java.util.Scanner;
import java.util.Arrays;
import java.util.*;
public class Main {
     int N;
    Map<Integer, List<Integer>> graph;
    Integer[][] memo;

    public int numSquarefulPerms(int[] A) {
        if(A.length==1){
            return 0;
        }
        N = A.length;
        graph = new HashMap();
        memo = new Integer[N][1 << N];

        for (int i = 0; i < N; ++i)
            graph.put(i, new ArrayList());

        for (int i = 0; i < N; ++i)
            for (int j = i+1; j < N; ++j) {
                int r = (int) (Math.sqrt(A[i] + A[j]) + 0.5);
                if (r * r == A[i] + A[j]) {
                    graph.get(i).add(j);
                    graph.get(j).add(i);
                }
            }


        int[] factorial = new int[20];
        factorial[0] = 1;
        for (int i = 1; i < 20; ++i)
            factorial[i] = i * factorial[i-1];

        int ans = 0;
        for (int i = 0; i < N; ++i)
            ans += dfs(i, 1 << i);

        Map<Integer, Integer> count = new HashMap();
        for (int x: A)
            count.put(x, count.getOrDefault(x, 0) + 1);
        for (int v: count.values())
            ans /= factorial[v];

        return ans;
    }

    public int dfs(int node, int visited) {
        if (visited == (1 << N) - 1)
            return 1;
        if (memo[node][visited] != null)
            return memo[node][visited];

        int ans = 0;
        for (int nei: graph.get(node))
            if (((visited >> nei) & 1) == 0)
                ans += dfs(nei, visited | (1 << nei));
        memo[node][visited] = ans;
        return ans;
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);

        int arrayLength = in.nextInt();
        Main temp = new Main();
        int[] a = new int[arrayLength];
        for (int i = 0; i < arrayLength; i++) {
            a[i] = in.nextInt();
        }
        System.out.println(temp.numSquarefulPerms(a));

    }
}



///https://leetcode-cn.com/problems/number-of-squareful-arrays/solution/zheng-fang-xing-shu-zu-de-shu-mu-by-leetcode/
