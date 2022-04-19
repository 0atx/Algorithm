package com.ssafy.algorithm.DP;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

/*
 * @author 0at_x
 * @since 2022. 04. 19.
 * @see https://www.acmicpc.net/problem/16456
 * @category #DP
 */

public class BOJ_DP_S1_16456 {

	private static int N, mod = 1000000009;
	private static long[] dp = new long[50001];

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		N = Integer.parseInt(br.readLine());

		dp[0] = 1;
		dp[1] = 1;
		dp[2] = 2;

		for (int n = 3; n < N; n++) {
			dp[n] = (dp[n - 1] + dp[n - 3]) % mod;
		}

		bw.write(dp[N - 1] + "\n");

		br.close();
		bw.flush();
		bw.close();

	}

}
