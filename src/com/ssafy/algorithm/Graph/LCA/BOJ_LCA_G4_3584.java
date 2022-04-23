package com.ssafy.algorithm.Graph.LCA;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

/*
 * @author 0at_x
 * @since 2022. 04. 23.
 * @see https://www.acmicpc.net/problem/3584
 * @category #LCA
 */

public class BOJ_LCA_G4_3584 {

	private static int T, N, result;
	private static List<List<Integer>> graph;
	private static int[] parent, depth;
	private static boolean[] root, checked;
	private static StringTokenizer st;
	private static StringBuilder sb = new StringBuilder();

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		T = Integer.parseInt(br.readLine());

		for (int t = 0; t < T; t++) {
			N = Integer.parseInt(br.readLine());

			graph = new ArrayList<>();
			parent = new int[N + 1];
			depth = new int[N + 1];
			checked = new boolean[N + 1];
			root = new boolean[N + 1];

			for (int n = 0; n <= N; n++) {
				graph.add(new ArrayList<>());
				root[n] = true;
			}

			for (int n = 1; n < N; n++) {
				st = new StringTokenizer(br.readLine());
				int a = Integer.parseInt(st.nextToken());
				int b = Integer.parseInt(st.nextToken());

				graph.get(a).add(b);
				graph.get(b).add(a);
				root[b] = false; // 임시 루트 찾을 용도
			}

			for (int n = 1; n <= N; n++) {
				if (root[n]) { // 루트 노드면
					dfs(n, 0);
					break;
				}
			}

			st = new StringTokenizer(br.readLine());
			result = LCA(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
			sb.append(result + "\n");
		}

		bw.write(sb.toString());

		br.close();
		bw.flush();
		bw.close();

	}

	private static void dfs(int node, int d) {
		checked[node] = true;
		depth[node] = d;

		for (int next : graph.get(node)) {
			if (checked[next]) {
				continue;
			}
			parent[next] = node;
			dfs(next, d + 1);
		}
	}

	private static int LCA(int a, int b) {
		while (depth[a] != depth[b]) {
			if (depth[a] > depth[b]) {
				a = parent[a];
			} else {
				b = parent[b];
			}
		}

		while (a != b) {
			a = parent[a];
			b = parent[b];
		}

		return a;
	}

}
