package com.ssafy.algorithm.DP;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

/*
 * @author 0at_x
 * @since 2022. 03. 15.
 * @see https://www.acmicpc.net/problem/11660
 * @category #D
 */

public class BOJ_DP_S1_11660 {

	private static int N, M;
	private static int[][] table;
	private static StringTokenizer st;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		table = new int[N + 1][N + 1];

		for (int r = 1; r <= N; r++) {
			st = new StringTokenizer(br.readLine());
			for (int c = 1; c <= N; c++) {
				table[r][c] = Integer.parseInt(st.nextToken()) + table[r - 1][c] + table[r][c - 1]
						- table[r - 1][c - 1];
			}
		}

		for (int m = 0; m < M; m++) {
			int result = 0;

			st = new StringTokenizer(br.readLine());
			int r1 = Integer.parseInt(st.nextToken());
			int c1 = Integer.parseInt(st.nextToken());
			int r2 = Integer.parseInt(st.nextToken());
			int c2 = Integer.parseInt(st.nextToken());

			result = table[r2][c2] - table[r1 - 1][c2] - table[r2][c1 - 1] + table[r1 - 1][c1 - 1];

			bw.write(result + "\n");
		}

		br.close();
		bw.flush();
		bw.close();

	}

}
