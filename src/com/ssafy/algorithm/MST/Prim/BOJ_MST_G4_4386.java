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
 * @since 2022. 03. 28.
 * @see https://www.acmicpc.net/problem/4386
 * @category #MST
 */

public class BOJ_MST_G4_4386 {

	private static int N;
	private static double result = 0;
	private static ArrayList<Star> stars = new ArrayList<>();
	private static ArrayList<Edge>[] graph;
	private static boolean[] visited;
	private static PriorityQueue<Edge> pq = new PriorityQueue<>();

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		N = Integer.parseInt(br.readLine());

		graph = new ArrayList[N];
		visited = new boolean[N];

		for (int n = 0; n < N; n++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			stars.add(new Star(Double.parseDouble(st.nextToken()), Double.parseDouble(st.nextToken())));
			graph[n] = new ArrayList<>();
		}

		for (int n = 0; n < N; n++) {
			for (int m = n + 1; m < N; m++) {
				double cost = Math.sqrt(
						Math.pow(stars.get(n).x - stars.get(m).x, 2) + Math.pow(stars.get(n).y - stars.get(m).y, 2));
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

		bw.write(String.format("%.2f", result));

		br.close();
		bw.flush();
		bw.close();

	}

	private static class Star {
		double x;
		double y;

		public Star(double x, double y) {
			super();
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
