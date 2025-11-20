//Make Strings Equal

class Code {

    static final int INF = (int)1e9;

    public int minCost(String s, String t, char[][] transform, int[] cost) {
        int n = s.length();
        int[][] dist = new int[26][26];

        for (int i = 0; i < 26; i++) {
            for (int j = 0; j < 26; j++) {
                dist[i][j] = (i == j) ? 0 : INF;
            }
        }

        for (int i = 0; i < transform.length; i++) {
            int x = transform[i][0] - 'a';
            int y = transform[i][1] - 'a';
            dist[x][y] = Math.min(dist[x][y], cost[i]);
        }

        for (int k = 0; k < 26; k++) {
            for (int i = 0; i < 26; i++) {
                for (int j = 0; j < 26; j++) {
                    if (dist[i][k] < INF && dist[k][j] < INF) {
                        dist[i][j] = Math.min(dist[i][j], dist[i][k] + dist[k][j]);
                    }
                }
            }
        }

        int ans = 0;

        for (int i = 0; i < n; i++) {
            int u = s.charAt(i) - 'a';
            int v = t.charAt(i) - 'a';

            int best = INF;
            for (int k = 0; k < 26; k++) {
                if (dist[u][k] < INF && dist[v][k] < INF) {
                    best = Math.min(best, dist[u][k] + dist[v][k]);
                }
            }

            if (best == INF) return -1;
            ans += best;
        }

        return ans;
    }
}
