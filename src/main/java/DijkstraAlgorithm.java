import java.util.Arrays;

public class DijkstraAlgorithm {
    static final int V = 9; // 정점의 수

    // 최단 경로를 찾는 메서드
    int minDistance(int dist[], Boolean sptSet[]) {
        int min = Integer.MAX_VALUE, min_index = -1;

        for (int v = 0; v < V; v++) {
            if (!sptSet[v] && dist[v] <= min) {
                min = dist[v];
                min_index = v;
            }
        }
        return min_index;
    }

    void dijkstra(int graph[][], int src) {
        int dist[] = new int[V]; // 최단 거리 배열
        Boolean sptSet[] = new Boolean[V]; // 최단 경로 트리에 포함된 정점 집합

        // 모든 정점의 거리 값을 초기화
        Arrays.fill(dist, Integer.MAX_VALUE);
        Arrays.fill(sptSet, false);

        dist[src] = 0; // 시작 정점의 거리를 0으로 설정

        for (int count = 0; count < V - 1; count++) {
            int u = minDistance(dist, sptSet);
            sptSet[u] = true;

            for (int v = 0; v < V; v++) {
                if (!sptSet[v] && graph[u][v] != 0 &&
                        dist[u] != Integer.MAX_VALUE &&
                        dist[u] + graph[u][v] < dist[v]) {
                    dist[v] = dist[u] + graph[u][v];
                }
            }
        }

        printSolution(dist);
    }

    // 최단 거리 결과 출력
    void printSolution(int dist[]) {
        System.out.println("정점 \t 최단 거리");
        for (int i = 0; i < V; i++) {
            System.out.println(i + " \t " + dist[i]);
        }
    }

    public static void main(String[] args) {
        DijkstraAlgorithm t = new DijkstraAlgorithm();
        int graph[][] = new int[][] {
                {0, 4, 0, 0, 0, 0, 0, 8, 0},
                {4, 0, 8, 0, 0, 0, 0, 11, 0},
                {0, 8, 0, 7, 0, 4, 0, 0, 2},
                {0, 0, 7, 0, 9, 14, 0, 0, 0},
                {0, 0, 0, 9, 0, 10, 0, 0, 0},
                {0, 0, 4, 14, 10, 0, 2, 0, 0},
                {0, 0, 0, 0, 0, 2, 0, 1, 6},
                {8, 11, 0, 0, 0, 0, 1, 0, 7},
                {0, 0, 2, 0, 0, 0, 6, 7, 0}
        };

        t.dijkstra(graph, 0); // 0번 정점에서 시작
    }
}
