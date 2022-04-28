package com.ssafy.algorithm.Graph.TS;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

/*
 * @author 0at_x
 * @since 2022. 04. 28.
 * @see https://www.acmicpc.net/problem/2252
 * @category #Topological Sort
 */

public class BOJ_TS_G3_2252 {

	private static int N, M;
	private static Node[] graph;
	private static StringTokenizer st;
	private static StringBuilder sb = new StringBuilder();

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		graph = new Node[N + 1];

		for (int n = 1; n <= N; n++) {
			graph[n] = new Node();
		}

		for (int m = 0; m < M; m++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());

			graph[a].tree.add(b);
			graph[b].degree++;
		}

		bfs();

		bw.write(sb.toString());

		br.close();
		bw.flush();
		bw.close();

	}

	private static void bfs() {
		Queue<Integer> q = new ArrayDeque<>();

		for (int n = 1; n <= N; n++) {
			if (graph[n].degree == 0) {
				q.add(n);
			}
		}

		while (!q.isEmpty()) {
			int cur = q.poll();
			sb.append(cur + " ");

			for (int n = 0, s = graph[cur].tree.size(); n < s; n++) {
				int next = graph[cur].tree.get(n);
				if (--graph[next].degree == 0) {
					q.add(next);
				}
			}
		}

		sb.setLength(sb.length() - 1);
		sb.append("\n");
	}

	private static class Node {
		int degree;
		List<Integer> tree;

		public Node() {
			this.degree = 0;
			this.tree = new ArrayList<>();
		}
	}

}
