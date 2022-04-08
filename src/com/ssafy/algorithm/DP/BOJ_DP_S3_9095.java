package com.ssafy.algorithm.DP;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

/*
 * @author 0at_x
 * @since 2022. 04. 01.
 * @see https://www.acmicpc.net/problem/9095
 * @category #DP
 */

public class BOJ_DP_S3_9095 {

	private static int T, N;
	private static int[] dp = new int[11];

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		T = Integer.parseInt(br.readLine());

		dp[1] = 1;
		dp[2] = 2;
		dp[3] = 4;

		for (int i = 4; i <= 10; i++) {
			dp[i] = dp[i - 1] + dp[i - 2] + dp[i - 3];
		}

		for (int t = 0; t < T; t++) {
			N = Integer.parseInt(br.readLine());
			bw.append(dp[N] + "\n");
		}

		br.close();
		bw.flush();
		bw.close();

	}

}
