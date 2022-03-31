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
 * @see https://www.acmicpc.net/problem/1774
 * @category #MST
 */

public class BOJ_MST_G3_1774 {

	private static int N, M, cnt = 0;
	private static double result = 0;
	private static ArrayList<Planet> planets = new ArrayList<>();
	private static ArrayList<Edge>[] graph;
	private static boolean[] visited;
	private static PriorityQueue<Edge> pq = new PriorityQueue<>();
	private static StringTokenizer st;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		if (M == N - 1) {
			bw.write("0.00\n");
			br.close();
			bw.flush();
			bw.close();
			return;
		}

		graph = new ArrayList[N];
		visited = new boolean[N];

		for (int n = 0; n < N; n++) {
			st = new StringTokenizer(br.readLine());
			planets.add(new Planet(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())));
			graph[n] = new ArrayList<>();
		}

		for (int m = 0; m < N; m++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken()) - 1;
			int b = Integer.parseInt(st.nextToken()) - 1;
			double cost = Math.sqrt(Math.pow(planets.get(a).x - planets.get(b).x, 2)
					+ Math.pow(planets.get(a).y - planets.get(b).y, 2));
			graph[a].add(new Edge(b, cost));
			graph[b].add(new Edge(a, cost));
			cnt -= cost;
		}

		for (int n = 0; n < N; n++) {
			for (int m = n + 1; m < N; m++) {
				double cost = Math.sqrt(Math.pow(planets.get(n).x - planets.get(m).x, 2)
						+ Math.pow(planets.get(n).y - planets.get(m).y, 2));
				graph[n].add(new Edge(m, cost));
				graph[m].add(new Edge(n, cost));
			}
		}

		pq.offer(new Edge(0, 0));

		while (!pq.isEmpty()) {
			Edge edge = pq.poll();

			if (visited[edge.node]) {
				continue;
			}

			visited[edge.node] = true;
			result += edge.cost;

			for (int s = 0; s < graph[edge.node].size(); s++) {
				Edge e = graph[edge.node].get(s);
				if (!visited[e.node]) {
					pq.offer(e);
				}
			}
		}

		result -= cnt;
		bw.write(String.format("%.2f", result));

		br.close();
		bw.flush();
		bw.close();

	}

	private static class Planet {
		int x;
		int y;

		public Planet(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}

	private static class Edge implements Comparable<Edge> {
		int node;
		double cost;

		public Edge(int node, double cost) {
			super();
			this.node = node;
			this.cost = cost;
		}

		@Override
		public int compareTo(Edge o) {
			return Double.compare(this.cost, o.cost);
		}

	}

}
