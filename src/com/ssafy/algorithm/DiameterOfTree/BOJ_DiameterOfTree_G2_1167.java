package com.ssafy.algorithm.DiameterOfTree;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

/*
 * @author 0at_x
 * @since 2022. 06. 23.
 * @see https://www.acmicpc.net/problem/1167
 * @category #DiameterOfTree
 */

public class BOJ_DiameterOfTree_G2_1167 {

    static int V;
    static boolean[] visited;
    static int[] dist;
    static ArrayList<ArrayList<Node>> graph;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        V = Integer.parseInt(br.readLine());

        visited = new boolean[V + 1];
        dist = new int[V + 1];
        graph = new ArrayList<ArrayList<Node>>();

        for (int v = 0; v <= V; v++) {
            graph.add(new ArrayList<>());
        }

        for (int v = 1; v <= V; v++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken());
            while(st.hasMoreTokens()) {
                int to = Integer.parseInt(st.nextToken());
                if(to == -1) {
                    break;
                }
                int weight = Integer.parseInt(st.nextToken());

                graph.get(from).add(new Node(to, weight));
            }
        }

        int b = dfs(1);
        Arrays.fill(visited, false);
        dfs(b);
        bw.write(dist[b] + "\n");

        br.close();
        bw.flush();
        bw.close();

    }

    static int dfs(int no) {
        visited[no] = true;
        int lastVertex = no;
        dist[no] = 0;

        for (Node next : graph.get(no)) {
            if (!visited[next.no]) {
                visited[next.no] = true;
                int vertex = dfs(next.no);

                if (dist[next.no] + next.weight > dist[no]) {
                    dist[no] = dist[next.no] + next.weight;
                    lastVertex = vertex;
                }
            }
        }
        return lastVertex;
    }

    static class Node {
        int no;
        int weight;

        Node(int no, int weight) {
            this.no = no;
            this.weight = weight;
        }
    }
}
