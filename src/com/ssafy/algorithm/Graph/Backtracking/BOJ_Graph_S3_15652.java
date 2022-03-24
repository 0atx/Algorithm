package com.ssafy.algorithm.Graph.Backtracking;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

/*
 * @author 0at_x
 * @since 2022. 03. 24.
 * @see https://www.acmicpc.net/problem/15652
 * @category #Graph
 */

public class BOJ_Graph_S3_15652 {

	private static int N, M;
	private static int[] arr;
	private static StringBuilder sb = new StringBuilder();

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		arr = new int[M];

		dfs(1, 0);

		bw.write(sb.toString());

		br.close();
		bw.flush();
		bw.close();

	}

	private static void dfs(int n, int m) {
		if (m == M) {
			for (int a : arr) {
				sb.append(a + " ");
			}
			sb.append("\n");
			return;
		}

		for (; n <= N; n++) {
			arr[m] = n;
			dfs(n, m + 1);
		}
	}

}
