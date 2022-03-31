package com.ssafy.algorithm.Graph.Dijkstra;

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
 * @since 2022. 03. 30.
 * @see https://www.acmicpc.net/problem/1240
 * @category #Graph
 */

public class BOJ_Graph_G5_1240 {

	private static int N, M;
	private static ArrayList<ArrayList<Node>> graph = new ArrayList<ArrayList<Node>>();
	private static StringTokenizer st;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		for (int n = 0; n <= N; n++) {
			graph.add(new ArrayList<>());
		}

		M = Integer.parseInt(st.nextToken());

		for (int n = 0; n < N - 1; n++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			int cost = Integer.parseInt(st.nextToken());

			graph.get(a).add(new Node(b, cost));
			graph.get(b).add(new Node(a, cost));
		}

		for (int m = 0; m < M; m++) {
			st = new StringTokenizer(br.readLine());
			int start = Integer.parseInt(st.nextToken());
			int end = Integer.parseInt(st.nextToken());

			bw.write(dijkstra(start, end) + "\n");
		}

		br.close();
		bw.flush();
		bw.close();

	}

	private static int dijkstra(int start, int end) {
		boolean[] visited = new boolean[N + 1];
		int[] dist = new int[N + 1];

		Arrays.fill(dist, Integer.MAX_VALUE);

		dist[start] = 0;

		while (!visited[end]) {
			int minIdx = 0;
			int minCost = Integer.MAX_VALUE;

			for (int n = 1; n < N + 1; n++) {
				if (!visited[n] && dist[n] < minCost) {
					minCost = dist[n];
					minIdx = n;
				}
			}

			visited[minIdx] = true;

			for (int g = 0; g < graph.get(minIdx).size(); g++) {
				Node next = graph.get(minIdx).get(g);
				if (dist[next.idx] > dist[minIdx] + next.cost) {
					dist[next.idx] = dist[minIdx] + next.cost;
				}
			}
		}

		return dist[end];
	}

	private static class Node {
		int idx;
		int cost;

		public Node(int idx, int cost) {
			this.idx = idx;
			this.cost = cost;
		}
	}

}
