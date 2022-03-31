package com.ssafy.algorithm.MST.Kruskal;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

/*
 * @author 0at_x
 * @since 2022. 03. 31.
 * @see https://www.acmicpc.net/problem/1774
 * @category #MST
 */

public class BOJ_MST_G3_1774 {

	private static int N, M;
	private static double result;
	private static ArrayList<Edge> graph = new ArrayList<Edge>();
	private static int[] parents;
	private static StringTokenizer st;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		Planet[] planets = new Planet[N + 1];

		parents = new int[N + 1];
		for (int n = 1; n <= N; n++) {
			parents[n] = n;
		}

		for (int n = 1; n <= N; n++) {
			st = new StringTokenizer(br.readLine());
			planets[n] = new Planet(n, Double.parseDouble(st.nextToken()), Double.parseDouble(st.nextToken()));
		}

		for (int m = 0; m < M; m++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());

			union(a, b);

		}

		for (int n = 1; n <= N; n++) {
			for (int m = n + 1; m <= N; m++) {
				double cost = Math
						.sqrt(Math.pow(planets[n].x - planets[m].x, 2) + Math.pow(planets[n].y - planets[m].y, 2));
				graph.add(new Edge(planets[n].num, planets[m].num, cost));

			}
		}

		Collections.sort(graph);

		for (int g = 0; g < graph.size(); g++) {
			Edge edge = graph.get(g);
			if (find(edge.from) != find(edge.to)) {
				result += edge.cost;
				union(edge.from, edge.to);
			}
		}

		bw.write(String.format("%.2f", result));

		br.close();
		bw.flush();
		bw.close();

	}

	private static int find(int a) {
		if (parents[a] == a) {
			return a;
		}
		return parents[a] = find(parents[a]);
	}

	private static void union(int a, int b) {
		a = find(a);
		b = find(b);

		if (a != b) {
			parents[b] = a;
		}

	}

	private static class Planet {
		int num;
		double x;
		double y;

		public Planet(int num, double x, double y) {
			this.num = num;
			this.x = x;
			this.y = y;
		}
	}

	private static class Edge implements Comparable<Edge> {
		int from, to;
		double cost;

		private Edge(int from, int to, double cost) {
			super();
			this.from = from;
			this.to = to;
			this.cost = cost;
		}

		@Override
		public int compareTo(Edge o) {
			return Double.compare(this.cost, o.cost);
		}
	}
}
