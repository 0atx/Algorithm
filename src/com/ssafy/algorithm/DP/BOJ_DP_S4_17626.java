package com.ssafy.algorithm.DP;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

/*
 * @author 0at_x
 * @since 2022. 04. 16.
 * @see https://www.acmicpc.net/problem/17626
 * @category #DP
 */

public class BOJ_DP_S4_17626 {

	private static int N;
	private static int[] dp;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		N = Integer.parseInt(br.readLine());
		dp = new int[N + 1];

		dp[1] = 1;

		for (int n = 2; n <= N; n++) {
			int min = Integer.MAX_VALUE;
			for (int m = 1; m * m <= n; m++) {
				min = Math.min(min, dp[n - m * m]);
			}
			dp[n] = min + 1;
		}

		bw.write(dp[N] + "\n");

		br.close();
		bw.flush();
		bw.close();

	}

}
