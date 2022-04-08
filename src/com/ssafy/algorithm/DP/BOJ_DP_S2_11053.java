package com.ssafy.algorithm.DP;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

/*
 * @author 0at_x
 * @since 2022. 04. 01.
 * @see https://www.acmicpc.net/problem/11053
 * @category #DP
 */

public class BOJ_DP_S2_11053 {

	private static int N, result;
	private static int[] sequence, dp;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		N = Integer.parseInt(br.readLine());
		sequence = new int[N];
		dp = new int[N];

		StringTokenizer st = new StringTokenizer(br.readLine());

		for (int n = 0; n < N; n++) {
			sequence[n] = Integer.parseInt(st.nextToken());
		}

		for (int n = 0; n < N; n++) {
			dp[n] = 1;

			for (int m = 0; m < n; m++) {
				if (sequence[m] < sequence[n] && dp[n] < dp[m] + 1) {
					dp[n] = dp[m] + 1;
				}
			}
		}

		for (int n = 0; n < N; n++) {
			result = Math.max(result, dp[n]);
		}

		bw.write(result + "\n");

		br.close();
		bw.flush();
		bw.close();

	}
}
