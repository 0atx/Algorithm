package com.ssafy.algorithm.DP;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

/*
 * @author 0at_x
 * @since 2022. 03. 08.
 * @see https://www.acmicpc.net/problem/1932
 */

public class BOJ_DP_S1_1932 {

	private static int N, result = Integer.MIN_VALUE;
	private static int[][] triangle;
	private static StringTokenizer st;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		N = Integer.parseInt(br.readLine());

		triangle = new int[N][N];

		for (int r = 0; r < N; r++) {
			st = new StringTokenizer(br.readLine());
			for (int c = 0; c <= r; c++) {
				triangle[r][c] = Integer.parseInt(st.nextToken());
			}
		}

		for (int r = 1; r < N; r++) {
			for (int c = 0; c <= r; c++) {
				if (c == 0) {
					triangle[r][c] = triangle[r][c] + triangle[r - 1][c];
				} else if (r == c) {
					triangle[r][c] = triangle[r][c] + triangle[r - 1][c - 1];
				} else {
					triangle[r][c] = triangle[r][c] + Math.max(triangle[r - 1][c], triangle[r - 1][c - 1]);
				}
			}
		}

		for (int t : triangle[N - 1]) {
			result = Math.max(t, result);
		}

		bw.write(result + "\n");

		br.close();
		bw.flush();
		bw.close();

	}

}
