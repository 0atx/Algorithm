package com.ssafy.algorithm.DP;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

/*
 * @author 0at_x
 * @since 2022. 04. 18.
 * @see https://www.acmicpc.net/problem/11055
 * @category #DP
 */

public class BOJ_DP_S2_11055 {

	private static int N, result;
	private static int[] A, dp;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		N = Integer.parseInt(br.readLine());
		A = new int[N];
		dp = new int[N];

		StringTokenizer st = new StringTokenizer(br.readLine());

		for (int n = 0; n < N; n++) {
			A[n] = Integer.parseInt(st.nextToken());
		}

		dp[0] = A[0];
		result = dp[0];

		for (int n = 1; n < N; n++) {
			dp[n] = A[n];
			for (int m = 0; m < n; m++) {
				if (A[n] > A[m]) {
					dp[n] = Math.max(dp[n], dp[m] + A[n]);
				}
			}
			result = Math.max(result, dp[n]);
		}

		bw.write(result + "\n");

		br.close();
		bw.flush();
		bw.close();

	}

}
