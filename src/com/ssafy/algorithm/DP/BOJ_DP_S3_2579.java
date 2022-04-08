package com.ssafy.algorithm.DP;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

/*
 * @author 0at_x
 * @since 2022. 04. 01.
 * @see https://www.acmicpc.net/problem/2579
 * @category #DP
 */

public class BOJ_DP_S3_2579 {
	private static int N;
	private static int[] stair, dp;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		N = Integer.parseInt(br.readLine());

		stair = new int[301];
		dp = new int[301];

		for (int n = 1; n <= N; n++) {
			stair[n] = Integer.parseInt(br.readLine());
		}

		dp[1] = stair[1];
		dp[2] = stair[1] + stair[2];

		for (int i = 3; i <= N; i++) {
			dp[i] = stair[i] + Math.max(stair[i - 1] + dp[i - 3], dp[i - 2]);
		}

		bw.write(dp[N] + "\n");

		br.close();
		bw.flush();
		bw.close();

	}
}
