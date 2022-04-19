package com.ssafy.algorithm.DP;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

/*
 * @author 0at_x
 * @since 2022. 04. 16.
 * @see https://www.acmicpc.net/problem/10844
 * @category #DP
 */

public class BOJ_DP_S1_10844 {

	private static int N, mod = 1000000000;
	private static long result;
	private static long[][] dp = new long[101][11];

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		N = Integer.parseInt(br.readLine());

		for (int i = 1; i <= 9; i++) {
			dp[1][i] = 1;
		}

		for (int n = 2; n <= N; n++) {
			dp[n][0] = dp[n - 1][1];
			for (int m = 1; m <= 9; m++) {
				dp[n][m] = (dp[n - 1][m - 1] + dp[n - 1][m + 1]) % mod;
			}
		}

		for (int i = 0; i < 10; i++) {
			result += dp[N][i];
		}

		result %= mod;

		bw.write(result + "\n");

		br.close();
		bw.flush();
		bw.close();

	}

}
