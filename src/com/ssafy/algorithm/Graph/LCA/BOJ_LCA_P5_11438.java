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
 * @since 2022. 04. 29.
 * @see https://www.acmicpc.net/problem/11438
 * @category #LCA
 */

public class BOJ_LCA_P5_11438 {

	private static int N, M, result, logN = 21;
	private static int[] depth;
	private static int[][] parent;
	private static boolean[] check;
	private static List<List<Integer>> graph = new ArrayList<>();
	private static StringTokenizer st;
	private static StringBuilder sb = new StringBuilder();

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		N = Integer.parseInt(br.readLine());

		parent = new int[N + 1][logN];
		depth = new int[N + 1];
		check = new boolean[N + 1];

		for (int n = 0; n <= N; n++) {
			graph.add(new ArrayList<>());
		}

		for (int n = 0; n < N - 1; n++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());

			graph.get(a).add(b);
			graph.get(b).add(a);
		}

		setParent();

		M = Integer.parseInt(br.readLine());

		for (int m = 0; m < M; m++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());

			result = LCA(a, b);

			sb.append(result + "\n");
		}

		bw.write(sb.toString());

		br.close();
		bw.flush();
		bw.close();

	}

	private static void dfs(int node, int d) {
		check[node] = true;
		depth[node] = d;

		for (int next : graph.get(node)) {
			if (check[next]) {
				continue;
			}
			parent[next][0] = node;
			dfs(next, d + 1);
		}
	}

	private static void setParent() {
		dfs(1, 0);

		for (int i = 1; i < logN; i++) {
			for (int j = 1; j <= N; j++) {
				parent[j][i] = parent[parent[j][i - 1]][i - 1];
			}
		}
	}

	private static int LCA(int a, int b) {
		if (depth[a] > depth[b]) {
			int temp = a;
			a = b;
			b = temp;
		}

		for (int i = logN - 1; i >= 0; i--) {
			if (depth[b] - depth[a] >= (1 << i)) {
				b = parent[b][i];
			}
		}

		if (a == b) {
			return a;
		}

		for (int i = logN - 1; i >= 0; i--) {
			if (parent[a][i] != parent[b][i]) {
				a = parent[a][i];
				b = parent[b][i];
			}
		}

		return parent[a][0];

	}

}
