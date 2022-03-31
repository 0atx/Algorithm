package com.ssafy.algorithm.DP;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

/*
 * @author 0at_x
 * @since 2022. 03. 31.
 * @see https://www.acmicpc.net/problem/12865
 * @category #DP
 */

public class BOJ_DP_G5_12865 {

	private static int N, K;
	private static int[] W, V;
	private static int[][] dp;
	private static StringTokenizer st;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());

		dp = new int[N + 1][K + 1];

		W = new int[N + 1];
		V = new int[N + 1];

		for (int n = 1; n <= N; n++) {
			st = new StringTokenizer(br.readLine());
			W[n] = Integer.parseInt(st.nextToken());
			V[n] = Integer.parseInt(st.nextToken());
		}

		for (int n = 1; n <= N; n++) {
			for (int k = 1; k <= K; k++) {
				// dp[n][k] = dp[n - 1][k];
				if (k - W[n] >= 0) {
					dp[n][k] = Math.max(dp[n - 1][k], dp[n - 1][k - W[n]] + V[n]);
				} else {
					dp[n][k] = dp[n - 1][k];
				}

			}
		}

		bw.write(dp[N][K] + "\n");

		br.close();
		bw.flush();
		bw.close();

	}

}
