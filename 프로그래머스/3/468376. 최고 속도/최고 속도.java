import java.util.*;

class Solution {
    static final int INF = 1_000_000_001;

    static class Point {
        long x, y;

        Point(long x, long y) {
            this.x = x;
            this.y = y;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (!(o instanceof Point)) return false;
            Point p = (Point) o;
            return x == p.x && y == p.y;
        }

        @Override
        public int hashCode() {
            return Objects.hash(x, y);
        }
    }

    static class Road {
        long x1, y1, x2, y2;
        int limit;
        List<Point> points = new ArrayList<>();

        Road(int[] r) {
            x1 = r[0];
            y1 = r[1];
            x2 = r[2];
            y2 = r[3];
            limit = r[4];
        }

        boolean horizontal() {
            return y1 == y2;
        }

        boolean contains(Point p) {
            return x1 <= p.x && p.x <= x2 &&
                   y1 <= p.y && p.y <= y2;
        }

        Point camera() {
            return new Point((x1 + x2) / 2, (y1 + y2) / 2);
        }
    }

    static class Edge {
        int to;
        int limit;

        Edge(int to, int limit) {
            this.to = to;
            this.limit = limit;
        }
    }

    public int[] solution(int[][] city, int[][] road) {
        int m = road.length;
        Road[] roads = new Road[m];

        for (int i = 0; i < m; i++) {
            roads[i] = new Road(road[i]);
            roads[i].points.add(new Point(roads[i].x1, roads[i].y1));
            roads[i].points.add(new Point(roads[i].x2, roads[i].y2));
        }

        Map<Point, Integer> cameraLimit = new HashMap<>();

        for (Road r : roads) {
            Point camera = r.camera();
            cameraLimit.merge(camera, r.limit, Math::min);
        }

        for (int i = 0; i < m; i++) {
            for (int j = i + 1; j < m; j++) {
                Point p = intersection(roads[i], roads[j]);

                if (p != null) {
                    roads[i].points.add(p);
                    roads[j].points.add(p);
                }
            }
        }

        for (int i = 0; i < city.length; i++) {
            Point p = new Point(city[i][0], city[i][1]);

            for (Road r : roads) {
                if (r.contains(p)) {
                    r.points.add(p);
                }
            }
        }

        Map<Point, Integer> index = new HashMap<>();
        List<Point> vertices = new ArrayList<>();

        for (Road r : roads) {
            for (Point p : r.points) {
                if (!index.containsKey(p)) {
                    index.put(p, vertices.size());
                    vertices.add(p);
                }
            }
        }

        int v = vertices.size();
        List<Edge>[] graph = new ArrayList[v];

        for (int i = 0; i < v; i++) {
            graph[i] = new ArrayList<>();
        }

        for (Road r : roads) {
            List<Point> points = new ArrayList<>(r.points);

            points.sort((a, b) -> {
                if (r.horizontal()) {
                    return Long.compare(a.x, b.x);
                }
                return Long.compare(a.y, b.y);
            });

            List<Point> unique = new ArrayList<>();

            for (Point p : points) {
                if (unique.isEmpty() ||
                    !unique.get(unique.size() - 1).equals(p)) {
                    unique.add(p);
                }
            }

            for (int i = 0; i + 1 < unique.size(); i++) {
                Point a = unique.get(i);
                Point b = unique.get(i + 1);

                int limit = INF;

                Point camera = r.camera();

                if (onSegment(camera, a, b)) {
                    limit = Math.min(limit, r.limit);
                }

                if (cameraLimit.containsKey(a)) {
                    limit = Math.min(limit, cameraLimit.get(a));
                }

                if (cameraLimit.containsKey(b)) {
                    limit = Math.min(limit, cameraLimit.get(b));
                }

                int u = index.get(a);
                int w = index.get(b);

                graph[u].add(new Edge(w, limit));
                graph[w].add(new Edge(u, limit));
            }
        }

        int[] cityIndex = new int[city.length];

        for (int i = 0; i < city.length; i++) {
            cityIndex[i] = index.get(
                new Point(city[i][0], city[i][1])
            );
        }

        int[] best = new int[v];
        Arrays.fill(best, -1);

        int start = cityIndex[0];
        best[start] = INF;

        PriorityQueue<int[]> pq = new PriorityQueue<>(
            (a, b) -> Integer.compare(b[1], a[1])
        );

        pq.offer(new int[]{start, INF});

        while (!pq.isEmpty()) {
            int[] cur = pq.poll();

            int now = cur[0];
            int speed = cur[1];

            if (speed != best[now]) {
                continue;
            }

            for (Edge e : graph[now]) {
                int nextSpeed = Math.min(speed, e.limit);

                if (nextSpeed > best[e.to]) {
                    best[e.to] = nextSpeed;
                    pq.offer(new int[]{e.to, nextSpeed});
                }
            }
        }

        int[] answer = new int[city.length - 1];

        for (int i = 1; i < city.length; i++) {
            answer[i - 1] = best[cityIndex[i]] == INF
                ? 0
                : best[cityIndex[i]];
        }

        return answer;
    }

    static Point intersection(Road a, Road b) {
        if (a.horizontal() == b.horizontal()) {
            return null;
        }

        Road h = a.horizontal() ? a : b;
        Road v = a.horizontal() ? b : a;

        long x = v.x1;
        long y = h.y1;

        if (h.x1 <= x && x <= h.x2 &&
            v.y1 <= y && y <= v.y2) {
            return new Point(x, y);
        }

        return null;
    }

    static boolean onSegment(Point p, Point a, Point b) {
        return Math.min(a.x, b.x) <= p.x &&
               p.x <= Math.max(a.x, b.x) &&
               Math.min(a.y, b.y) <= p.y &&
               p.y <= Math.max(a.y, b.y);
    }
}