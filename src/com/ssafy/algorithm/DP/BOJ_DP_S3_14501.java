package com.ssafy.algorithm.DP;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

/*
 * @author 0at_x
 * @since 2022. 04. 14.
 * @see https://www.acmicpc.net/problem/14501
 * @category #DP
 */

public class BOJ_DP_S3_14501 {

	private static int N;
	private static int[] T, P, dp;
	private static StringTokenizer st;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		N = Integer.parseInt(br.readLine());

		T = new int[N];
		P = new int[N];
		dp = new int[N + 1];

		for (int n = 0; n < N; n++) {
			st = new StringTokenizer(br.readLine());

			T[n] = Integer.parseInt(st.nextToken());
			P[n] = Integer.parseInt(st.nextToken());
		}

		for (int n = 0; n < N; n++) {
			if (n + T[n] <= N) {
				dp[n + T[n]] = Math.max(dp[n + T[n]], dp[n] + P[n]);
			}
			dp[n + 1] = Math.max(dp[n + 1], dp[n]);
		}

		bw.write(dp[N] + "\n");

		br.close();
		bw.flush();
		bw.close();

	}

}
