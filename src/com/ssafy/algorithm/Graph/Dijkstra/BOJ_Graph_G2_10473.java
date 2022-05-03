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
 * @since 2022. 05. 03.
 * @see https://www.acmicpc.net/problem/10473
 * @category #Graph
 */

public class BOJ_Graph_G2_10473 {

	private static int N;
	private static double X, Y;
	private static ArrayList<Node> list;
	private static double[] times;
	private static boolean[] visited;
	private static StringTokenizer st;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		list = new ArrayList<>();

		st = new StringTokenizer(br.readLine());
		X = Double.parseDouble(st.nextToken());
		Y = Double.parseDouble(st.nextToken());

		list.add(new Node(X, Y));

		st = new StringTokenizer(br.readLine());
		X = Double.parseDouble(st.nextToken());
		Y = Double.parseDouble(st.nextToken());

		N = Integer.parseInt(br.readLine());

		visited = new boolean[N + 2];
		times = new double[N + 2];
		Arrays.fill(times, 1000000000);

		for (int n = 0; n < N; n++) {
			st = new StringTokenizer(br.readLine());
			double x = Double.parseDouble(st.nextToken());
			double y = Double.parseDouble(st.nextToken());
			list.add(new Node(x, y));
		}

		list.add(new Node(X, Y));

		dijkstra();

		bw.write(String.format("%.6f\n", times[N + 1]));

		br.close();
		bw.flush();
		bw.close();

	}

	private static void dijkstra() {
		PriorityQueue<Edge> pq = new PriorityQueue<>();
		pq.offer(new Edge(0, 0));
		times[0] = 0;

		while (!pq.isEmpty()) {
			Edge cur = pq.poll();

			if (visited[cur.node]) {
				continue;
			}

			visited[cur.node] = true;
			double x = list.get(cur.node).x;
			double y = list.get(cur.node).y;

			for (int s = 0; s < list.size(); s++) {
				if (s == cur.node) {
					continue;
				}
				double nx = list.get(s).x;
				double ny = list.get(s).y;

				if (cur.node == 0) {
					times[s] = Math.sqrt(Math.pow((x - nx), 2) + Math.pow((y - ny), 2)) / 5.0;
					pq.offer(new Edge(s, times[s]));
				} else {
					double time = Math.min(Math.sqrt(Math.pow((x - nx), 2) + Math.pow((y - ny), 2)) / 5.0,
							Math.abs(Math.sqrt(Math.pow((x - nx), 2) + Math.pow((y - ny), 2)) - 50) / 5.0 + 2);
					if (times[s] > times[cur.node] + time) {
						times[s] = times[cur.node] + time;
						pq.offer(new Edge(s, times[s]));
					}
				}
			}

		}

	}

	private static class Node {
		double x, y;

		public Node(double x, double y) {
			this.x = x;
			this.y = y;
		}
	}

	private static class Edge implements Comparable<Edge> {
		int node;
		double time;

		public Edge(int node, double time) {
			this.node = node;
			this.time = time;
		}

		public int compareTo(Edge o) {
			return Double.compare(this.time, o.time);
		}
	}

}
