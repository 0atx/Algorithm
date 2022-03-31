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
 * @since 2022. 03. 30.
 * @see https://www.acmicpc.net/problem/3124
 * @category #MST
 */

public class SWEA_MST_D4_3124 {

	static int T, V, E;
	static long cnt, result;
	private static ArrayList<Edge>[] graph;
	private static boolean[] visited;
	private static PriorityQueue<Edge> pq = new PriorityQueue<>();
	private static StringTokenizer st;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		T = Integer.parseInt(br.readLine());

		for (int t = 1; t <= T; t++) {

			cnt = 0;
			result = 0;

			st = new StringTokenizer(br.readLine());

			V = Integer.parseInt(st.nextToken());
			E = Integer.parseInt(st.nextToken());

			graph = new ArrayList[V + 1];
			visited = new boolean[V + 1];

			for (int v = 1; v <= V; v++) {
				graph[v] = new ArrayList<>();
			}

			for (int e = 0; e < E; e++) {
				st = new StringTokenizer(br.readLine());
				int a = Integer.parseInt(st.nextToken());
				int b = Integer.parseInt(st.nextToken());
				int w = Integer.parseInt(st.nextToken());
				graph[a].add(new Edge(b, w));
				graph[b].add(new Edge(a, w));
			}

			pq.offer(new Edge(1, 0));

			while (!pq.isEmpty()) {
				Edge edge = pq.poll();

				if (visited[edge.node]) {
					continue;
				}

				visited[edge.node] = true;
				result += edge.weight;

				if (cnt++ == V) {
					break;
				}

				for (int g = 0; g < graph[edge.node].size(); g++) {
					Edge e = graph[edge.node].get(g);
					if (!visited[e.node]) {
						pq.offer(e);
					}
				}
			}

			bw.write("#" + t + " " + result + "\n");
		}

		br.close();
		bw.flush();
		bw.close();

	}

	private static class Edge implements Comparable<Edge> {
		int node;
		int weight;

		public Edge(int node, int weight) {
			super();
			this.node = node;
			this.weight = weight;
		}

		@Override
		public int compareTo(Edge o) {
			return Integer.compare(this.weight, o.weight);
		}

	}

}
