import java.util.*;

class Solution {
    
    public int solution(int n, int[][] edge) {
        int answer = 0;
        // 노드와 간선을 표시할 2차원 배열 || 즉, 인접행렬
        List<ArrayList<Integer>> board = new ArrayList();
        for(int i = 0; i < n + 1; i++) {
            board.add(new ArrayList<Integer>());
        }
        boolean[] visited = new boolean[n + 1];
        int[] distance = new int[n + 1];
        // 1번부터 제일 멀리 있는 노드와의 거리
        int max = 0;
        // 인접행렬 초기화
        for(int[] vertex : edge) {
            int first = vertex[0];
            int second = vertex[1];
            // 무방향 그래프이기 대문에 양방향 이동이 가능하다.
            board.get(first).add(second);
            board.get(second).add(first);
        }
        // 1번에서부터 연결된 노드들의 최단의 경로를 추적
        // 1번과의 거리와 노드를 쌍으로 저장할 큐 [0] = 거리 [1] = 노드
        Queue<Integer> que = new LinkedList();
        que.offer(1);
        // 양방향 그래프이기 때문에 1번과 연결된 노드가 다시 1번을 탐지하지 못하도록 방문처리
        // 순환을 막는 역할도 포함
        visited[1] = true;
        // 더이상 탐지할 노드가 없으면 종료
        while(!que.isEmpty()) {
            int node = que.poll();
            // 인접행렬에서 연결된 노드를 탐지해서 큐에 넣는다.
            for(int el : board.get(node) ) {
                // 탐지한 노드가 방문한적이 없어야한다.
                if(visited[el]) continue;
                // 큐에 넣는다.
                que.offer(el);
                // 방문처리 ( 사용함 )
                visited[el] = true;
                distance[el] = distance[node] + 1;
                if(distance[el] > max) {
                    answer = 1;
                    max = distance[el];
                } else {
                    answer++;
                }
            }
        }
        return answer;
    }
}