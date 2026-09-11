import java.util.*;

class Solution {
    static class Edge {
        int from, to, cost;

        Edge(int from, int to, int cost) {
            this.from = from;
            this.to = to;
            this.cost = cost;
        }
    }

    static class Node {
        int room, state;
        long dist;

        Node(int room, int state, long dist) {
            this.room = room;
            this.state = state;
            this.dist = dist;
        }
    }

    public int solution(int n, int start, int end, int[][] roads, int[] traps) {
        int trapCount = traps.length;

        int[] trapIndex = new int[n + 1];
        Arrays.fill(trapIndex, -1);

        for (int i = 0; i < trapCount; i++) {
            trapIndex[traps[i]] = i;
        }

        List<Edge>[] graph = new ArrayList[n + 1];

        for (int i = 1; i <= n; i++) {
            graph[i] = new ArrayList<>();
        }

        for (int[] road : roads) {
            int from = road[0];
            int to = road[1];
            int cost = road[2];

            Edge edge = new Edge(from, to, cost);
            graph[from].add(edge);
            graph[to].add(edge);
        }

        int maxState = 1 << trapCount;
        long[][] dist = new long[n + 1][maxState];

        for (int i = 1; i <= n; i++) {
            Arrays.fill(dist[i], Long.MAX_VALUE);
        }

        PriorityQueue<Node> pq = new PriorityQueue<>(
            Comparator.comparingLong(a -> a.dist)
        );

        dist[start][0] = 0;
        pq.offer(new Node(start, 0, 0));

        while (!pq.isEmpty()) {
            Node cur = pq.poll();

            if (cur.dist != dist[cur.room][cur.state]) {
                continue;
            }

            if (cur.room == end) {
                return (int) cur.dist;
            }

            for (Edge edge : graph[cur.room]) {
                boolean fromActive = isActive(edge.from, cur.state, trapIndex);
                boolean toActive = isActive(edge.to, cur.state, trapIndex);

                int nextRoom;

                if (fromActive == toActive) {
                    if (edge.from != cur.room) {
                        continue;
                    }
                    nextRoom = edge.to;
                } else {
                    if (edge.to != cur.room) {
                        continue;
                    }
                    nextRoom = edge.from;
                }

                int nextState = cur.state;

                if (trapIndex[nextRoom] != -1) {
                    nextState ^= 1 << trapIndex[nextRoom];
                }

                long nextDist = cur.dist + edge.cost;

                if (nextDist < dist[nextRoom][nextState]) {
                    dist[nextRoom][nextState] = nextDist;
                    pq.offer(new Node(nextRoom, nextState, nextDist));
                }
            }
        }

        return -1;
    }

    static boolean isActive(int room, int state, int[] trapIndex) {
        if (trapIndex[room] == -1) {
            return false;
        }

        return (state & (1 << trapIndex[room])) != 0;
    }
}