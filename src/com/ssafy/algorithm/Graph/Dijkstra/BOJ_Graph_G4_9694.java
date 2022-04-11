package com.ssafy.algorithm.Graph.Dijkstra;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.Stack;
import java.util.StringTokenizer;

/*
 * @author 0at_x
 * @since 2022. 04. 08.
 * @see https://www.acmicpc.net/problem/9694
 * @category #Graph
 */

public class BOJ_Graph_G4_9694 {

	private static int T, N, M;
	private static int[][] dist;
	private static boolean[] visited;
	private static Stack<Integer> stack;
	private static ArrayList<ArrayList<Node>> graph;
	private static StringTokenizer st;
	private static StringBuilder sb = new StringBuilder();

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		T = Integer.parseInt(br.readLine());

		for (int t = 1; t <= T; t++) {
			st = new StringTokenizer(br.readLine());

			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());

			graph = new ArrayList<ArrayList<Node>>();

			for (int m = 0; m < M; m++) {
				graph.add(new ArrayList<>());
			}

			for (int n = 0; n < N; n++) {
				st = new StringTokenizer(br.readLine());
				int a = Integer.parseInt(st.nextToken());
				int b = Integer.parseInt(st.nextToken());
				int c = Integer.parseInt(st.nextToken());

				graph.get(a).add(new Node(b, c));
				graph.get(b).add(new Node(a, c));
			}

			dijkstra();

			sb.append("Case #" + t + ": ");
			if (stack.size() > 0) {
				while (!stack.isEmpty()) {
					sb.append(stack.pop() + " ");
				}
				sb.setLength(sb.length() - 1);
				sb.append("\n");
			} else {
				sb.append("-1\n");
			}

		}

		bw.write(sb.toString());

		br.close();
		bw.flush();
		bw.close();

	}

	private static void dijkstra() {
		dist = new int[M][2];
		visited = new boolean[M];
		PriorityQueue<Node> pq = new PriorityQueue<>();
		stack = new Stack<>();
		pq.offer(new Node(0, 0));

		for (int m = 0; m < M; m++) {
			dist[m] = new int[] { Integer.MAX_VALUE, -1 };
		}
		dist[0][0] = 0;

		while (!pq.isEmpty()) {
			Node cur = pq.poll();
			int index = cur.no;

			if (!visited[index]) {
				visited[index] = true;

				if (index == M - 1) {
					while (index != -1) {
						stack.push(index);
						index = dist[index][1];
					}
					break;
				}

				for (Node node : graph.get(index)) {
					if (dist[node.no][0] > dist[index][0] + node.priority) {
						dist[node.no][0] = dist[index][0] + node.priority;
						dist[node.no][1] = index;
						pq.add(new Node(node.no, dist[node.no][0]));
					}
				}
			}

		}
	}

	private static class Node implements Comparable<Node> {
		int no;
		int priority;

		public Node(int no, int priority) {
			this.no = no;
			this.priority = priority;
		}

		@Override
		public int compareTo(Node o) {
			return Integer.compare(this.priority, o.priority);
		}

	}

}
