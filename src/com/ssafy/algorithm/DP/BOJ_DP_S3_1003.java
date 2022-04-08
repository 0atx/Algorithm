package com.ssafy.algorithm.DP;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

/*
 * @author 0at_x
 * @since 2022. 03. 31.
 * @see https://www.acmicpc.net/problem/1003
 * @category #DP
 */

public class BOJ_DP_S3_1003 {

	private static int T, N;
	private static int[] dp = new int[3];

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		T = Integer.parseInt(br.readLine());

		for (int t = 0; t < T; t++) {
			N = Integer.parseInt(br.readLine());

			dp[0] = 1;
			dp[1] = 0;
			dp[2] = 1;

			for (int n = 0; n < N; n++) {
				dp[0] = dp[1];
				dp[1] = dp[2];
				dp[2] = dp[0] + dp[1];
			}

			bw.append(dp[0] + " " + dp[1] + "\n");
		}

		br.close();
		bw.flush();
		bw.close();

	}

}
