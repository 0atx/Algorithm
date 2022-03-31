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
 * @see https://www.acmicpc.net/problem/1922
 * @category #MST
 */

public class BOJ_MST_G4_1922 {

	private static int N, M, result;
	private static ArrayList<Edge> graph = new ArrayList<Edge>();
	private static int[] parents;
	private static StringTokenizer st;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		N = Integer.parseInt(br.readLine());
		M = Integer.parseInt(br.readLine());

		for (int m = 0; m < M; m++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());

			graph.add(new Edge(a, b, c));
		}

		parents = new int[N + 1];
		for (int n = 1; n <= N; n++) {
			parents[n] = n;
		}

		Collections.sort(graph);

		for (int g = 0; g < graph.size(); g++) {
			Edge edge = graph.get(g);
			if (union(edge.from, edge.to)) {
				result += edge.cost;
			}
		}

		bw.write(result + "\n");

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

	private static boolean union(int a, int b) {
		a = find(a);
		b = find(b);

		if (a != b) {
			parents[b] = a;
			return true;
		} else {
			return false;
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
