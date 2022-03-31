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
 * @see https://www.acmicpc.net/problem/14621
 * @category #MST
 */

public class BOJ_MST_G3_14621 {

	private static int N, M, cnt = 0, result = 0;
	private static ArrayList<Edge> graph = new ArrayList<Edge>();
	private static int[] parents;
	private static char[] univ;
	private static StringTokenizer st;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		univ = new char[N + 1];

		st = new StringTokenizer(br.readLine());
		for (int n = 1; n <= N; n++) {
			univ[n] = st.nextToken().charAt(0);
		}

		for (int m = 0; m < M; m++) {
			st = new StringTokenizer(br.readLine());
			int u = Integer.parseInt(st.nextToken());
			int v = Integer.parseInt(st.nextToken());
			int d = Integer.parseInt(st.nextToken());

			graph.add(new Edge(u, v, d));

		}

		parents = new int[N + 1];
		for (int n = 1; n <= N; n++) {
			parents[n] = n;
		}

		Collections.sort(graph);

		for (int g = 0; g < graph.size(); g++) {
			Edge edge = graph.get(g);
			if (find(edge.from) != find(edge.to)) {
				if (univ[edge.from] != univ[edge.to]) {
					cnt++;
					result += edge.cost;
					union(edge.from, edge.to);
				}
			}
		}

		bw.write((cnt != N - 1 ? -1 : result) + "\n");

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

	private static class Edge implements Comparable<Edge> {
		int from, to, cost;

		private Edge(int from, int to, int cost) {
			super();
			this.from = from;
			this.to = to;
			this.cost = cost;
		}

		@Override
		public int compareTo(Edge o) {
			return Integer.compare(this.cost, o.cost);
		}
	}
}
