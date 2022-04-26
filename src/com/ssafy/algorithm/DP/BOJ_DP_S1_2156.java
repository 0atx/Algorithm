package com.ssafy.algorithm.DP;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

/*
 * @author 0at_x
 * @since 2022. 04. 26.
 * @see https://www.acmicpc.net/problem/2156
 * @category #DP
 */

public class BOJ_DP_S1_2156 {

	private static int N;
	private static int[] wines, dp;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		N = Integer.parseInt(br.readLine());

		wines = new int[N];
		dp = new int[N];

		for (int n = 0; n < N; n++) {
			wines[n] = Integer.parseInt(br.readLine());
		}

		dp[0] = wines[0];

		if (N > 1) {
			dp[1] = wines[0] + wines[1];
		}

		if (N > 2) {
			dp[2] = Math.max(dp[1], Math.max(wines[0] + wines[2], wines[1] + wines[2]));
		}

		for (int i = 3; i < N; i++) {
			dp[i] = Math.max(dp[i - 1], Math.max(dp[i - 2] + wines[i], dp[i - 3] + wines[i - 1] + wines[i]));
		}

		bw.write(dp[N - 1] + "\n");

		br.close();
		bw.flush();
		bw.close();

	}

}
