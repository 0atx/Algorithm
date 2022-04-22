package com.ssafy.algorithm.DP;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

/*
 * @author 0at_x
 * @since 2022. 04. 22.
 * @see https://www.acmicpc.net/problem/2098
 * @category #DP
 */

public class BOJ_DP_G1_2098 {

	private static int N, INF = 100000000;
	private static int[][] map, dp;
	private static StringTokenizer st;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		N = Integer.parseInt(br.readLine());

		map = new int[N][N];
		dp = new int[N][(1 << N) - 1];

		for (int r = 0; r < N; r++) {
			st = new StringTokenizer(br.readLine());
			Arrays.fill(dp[r], INF);
			for (int c = 0; c < N; c++) {
				map[r][c] = Integer.parseInt(st.nextToken());
			}
		}

		bw.write(dfs(0, 1) + "\n");

		br.close();
		bw.flush();
		bw.close();

	}

	private static int dfs(int node, int visited) {
		if (visited == (1 << N) - 1) {
			if (map[node][0] == 0) {
				return INF;
			}

			return map[node][0];
		}

		if (dp[node][visited] != INF) {
			return dp[node][visited];
		}

		for (int i = 0; i < N; i++) {
			if ((visited & (1 << i)) == 0 && map[node][i] != 0) {
				dp[node][visited] = Math.min(dp[node][visited], dfs(i, visited | (1 << i)) + map[node][i]);
			}
		}

		return dp[node][visited];
	}

}
