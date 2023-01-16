package com.ssafy.algorithm.DiameterOfTree;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

/*
 * @author 0at_x
 * @since 2022. 06. 28.
 * @see https://www.acmicpc.net/problem/19581
 * @category #DiameterOfTree
 */

public class BOJ_DiameterOfTree_G1_19581 {

    static int N;
    static boolean[] visited;
    static long[] dist;
    static ArrayList<ArrayList<Edge>> graph;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        N = Integer.parseInt(br.readLine());

        visited = new boolean[N + 1];
        dist = new long[N + 1];
        graph = new ArrayList<ArrayList<Edge>>();

        for (int n = 0; n <= N; n++) {
            graph.add(new ArrayList<>());
        }

        for (int n = 1; n < N; n++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            long cost = Long.parseLong(st.nextToken());

            graph.get(from).add(new Edge(from, to, cost));
            graph.get(to).add(new Edge(to, from, cost));
        }

        int a = dfs(1, 0);
        Arrays.fill(visited, false);
        int b = dfs(a, 0);

        visited = new boolean[N + 1];
        dist = new long[N + 1];

        dfs(a, b);
        visited = new boolean[N + 1];
        dfs(b, a);

        bw.write(Math.max(dist[a], dist[b]) + "\n");

        br.close();
        bw.flush();
        bw.close();

    }

    static int dfs(int no, int except) {
        visited[no] = true;
        int lastVertex = no;
        dist[no] = 0;

        for (Edge next : graph.get(no)) {
            if (next.to == except) {
                continue;
            }
            if (!visited[next.to]) {
                visited[next.to] = true;
                int vertex = dfs(next.to, except);

                if (dist[next.to] + next.cost > dist[no]) {
                    dist[no] = dist[next.to] + next.cost;
                    lastVertex = vertex;
                }
            }
        }
        return lastVertex;
    }

    static class Edge {
        int from;
        int to;
        long cost;

        Edge(int from, int to, long cost) {
            this.from = from;
            this.to = to;
            this.cost = cost;
        }
    }
}
