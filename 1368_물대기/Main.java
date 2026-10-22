import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        // Write your solution here.
        int N = Integer.parseInt(br.readLine());
        int[][] edges = new int[N + 1][N + 1];

        for (int i = 0; i < N; i++) {
            edges[i + 1][i + 1] = Integer.parseInt(br.readLine());
        }
        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                int temp = Integer.parseInt(st.nextToken());
                if (i != j) {
                    edges[i + 1][j + 1] = temp;
                }
            }
        }

        Queue<int[]> queue = new PriorityQueue<>((a, b) -> a[1] - b[1]); //[노드,비용]
        for (int i = 1; i <= N; i++) {
            queue.add(new int[]{i, edges[i][i]});
        }

        boolean[] visited = new boolean[N + 1];
        int ans = 0;

        while (!queue.isEmpty()) {
            int[] cur = queue.poll();
            int node = cur[0];
            int cost = cur[1];

            if (visited[node]) {
                continue;
            }
            visited[node] = true;
            ans += cost;

            for (int i = 1; i <= N; i++) {
                int nextCost = edges[node][i];
                if (visited[i]) {
                    continue;
                }
                queue.add(new int[]{i, nextCost});
            }
        }

        System.out.println(ans);
    }
}