package com.ssafy.algorithm.MST.Prim;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

/*
 * @author 0at_x
 * @since 2022. 03. 31.
 * @see https://www.acmicpc.net/problem/1922
 * @category #MST
 */

public class BOJ_MST_G4_1922 {

	private static int N, M, cnt = 0, result = 0;
	private static ArrayList<Edge>[] graph;
	private static boolean[] visited;
	private static PriorityQueue<Edge> pq = new PriorityQueue<>();
	private static StringTokenizer st;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		N = Integer.parseInt(br.readLine());
		M = Integer.parseInt(br.readLine());

		graph = new ArrayList[N + 1];
		visited = new boolean[N + 1];

		for (int n = 1; n <= N; n++) {
			graph[n] = new ArrayList<>();
		}

		for (int m = 0; m < M; m++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());

			graph[a].add(new Edge(b, c));
			graph[b].add(new Edge(a, c));
		}

		pq.offer(new Edge(1, 0));

		while (!pq.isEmpty()) {
			Edge edge = pq.poll();

			if (visited[edge.node]) {
				continue;
			}

			visited[edge.node] = true;
			result += edge.cost;

			if (cnt++ == N) {
				break;
			}

			for (int g = 0; g < graph[edge.node].size(); g++) {
				Edge e = graph[edge.node].get(g);
				if (!visited[e.node]) {
					pq.offer(e);
				}
			}
		}

		bw.write(result + "\n");

		br.close();
		bw.flush();
		bw.close();

	}

	private static class Edge implements Comparable<Edge> {
		int node;
		int cost;

		public Edge(int node, int cost) {
			super();
			this.node = node;
			this.cost = cost;
		}

		@Override
		public int compareTo(Edge o) {
			return Integer.compare(this.cost, o.cost);
		}

	}

}
