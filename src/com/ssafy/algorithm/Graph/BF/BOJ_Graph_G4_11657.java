package com.ssafy.algorithm.Graph.BF;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

/*
 * @author 0at_x
 * @since 2022. 04. 12.
 * @see https://www.acmicpc.net/problem/11657
 * @category #Graph
 */

public class BOJ_Graph_G4_11657 {

	private static int N, M;
	private static long[] dist;
	private static boolean cycle;
	private static ArrayList<Edge> graph = new ArrayList<>();
	private static StringTokenizer st;
	private static StringBuilder sb = new StringBuilder();

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		dist = new long[N + 1];
		Arrays.fill(dist, Integer.MAX_VALUE);
		dist[1] = 0;

		for (int m = 0; m < M; m++) {
			st = new StringTokenizer(br.readLine());

			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());

			graph.add(new Edge(a, b, c));
		}

		bellmanFord();

		if (cycle) {
			bw.write("-1\n");
		} else {
			for (int n = 2; n <= N; n++) {
				if (dist[n] == Integer.MAX_VALUE) {
					bw.write("-1\n");
				} else {
					bw.write(dist[n] + "\n");
				}
			}
		}

		br.close();
		bw.flush();
		bw.close();

	}

	private static void bellmanFord() {
		for (int n = 0; n < N; n++) {
			for (Edge node : graph) {
				if (dist[node.from] == Integer.MAX_VALUE) {
					continue;
				}
				if (dist[node.to] > dist[node.from] + node.cost) {
					dist[node.to] = dist[node.from] + node.cost;
					if (n == N - 1) {
						cycle = true;
					}
				}
			}
		}
	}

	private static class Edge {
		int from, to, cost;

		private Edge(int from, int to, int cost) {
			super();
			this.from = from;
			this.to = to;
			this.cost = cost;
		}
	}

}
