package com.ssafy.algorithm.Graph.Dijkstra;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

/*
 * @author 0at_x
 * @since 2022. 03. 16.
 * @see https://www.acmicpc.net/problem/1238
 * @category #Graph
 */

public class BOJ_Graph_G3_1238 {

	private static int N, M, X;
	private static int[] fromXdist, toXdist;
	private static ArrayList<ArrayList<Node>> fromXList, toXList;
	private static StringTokenizer st;

	private static class Node implements Comparable<Node> {
		int to;
		int dist;

		public Node(int to, int dist) {
			this.to = to;
			this.dist = dist;
		}

		@Override
		public int compareTo(Node o) {
			return Integer.compare(this.dist, o.dist);
		}

	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		X = Integer.parseInt(st.nextToken());

		fromXList = new ArrayList<>();
		toXList = new ArrayList<>();

		for (int n = 0; n <= N; n++) {
			fromXList.add(new ArrayList<>());
			toXList.add(new ArrayList<>());
		}

		for (int m = 0; m < M; m++) {
			st = new StringTokenizer(br.readLine());
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());

			fromXList.get(from).add(new Node(to, c));
			toXList.get(to).add(new Node(from, c));
		}

		fromXdist = dijkstra(fromXList);
		toXdist = dijkstra(toXList);

		int result = Integer.MIN_VALUE;

		for (int n = 1; n <= N; n++) {
			result = Math.max(fromXdist[n] + toXdist[n], result);
		}

		bw.write(result + "\n");

		br.close();
		bw.flush();
		bw.close();

	}

	private static int[] dijkstra(ArrayList<ArrayList<Node>> list) {
		int[] distance = new int[N + 1];
		boolean[] visited = new boolean[N + 1];
		PriorityQueue<Node> pq = new PriorityQueue<>();
		pq.add(new Node(X, 0));

		Arrays.fill(distance, Integer.MAX_VALUE);
		distance[X] = 0;

		while (!pq.isEmpty()) {
			Node cur = pq.poll();
			int index = cur.to;

			if (!visited[index]) {
				visited[index] = true;

				for (Node node : list.get(index)) {
					if (!visited[node.to] && distance[node.to] > distance[index] + node.dist) {
						distance[node.to] = distance[index] + node.dist;
						pq.add(new Node(node.to, distance[node.to]));
					}
				}
			}

		}
		return distance;

	}

}
