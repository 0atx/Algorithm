package com.ssafy.algorithm.Graph.Backtracking;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

/*
 * @author 0at_x
 * @since 2022. 03. 25.
 * @see https://www.acmicpc.net/problem/15657
 * @category #Graph
 */

public class BOJ_Graph_S3_15657 {

	private static int N, M;
	private static int[] num, arr;
	private static StringBuilder sb = new StringBuilder();

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		num = new int[N];
		arr = new int[M];

		st = new StringTokenizer(br.readLine());
		for (int n = 0; n < N; n++) {
			num[n] = Integer.parseInt(st.nextToken());
		}

		Arrays.sort(num);

		dfs(0, 0);

		bw.write(sb.toString());

		br.close();
		bw.flush();
		bw.close();

	}

	private static void dfs(int i, int m) {
		if (m == M) {
			for (int a : arr) {
				sb.append(a + " ");
			}
			sb.append("\n");
			return;
		}

		for (; i < N; i++) {
			arr[m] = num[i];
			dfs(i, m + 1);
		}
	}

}
