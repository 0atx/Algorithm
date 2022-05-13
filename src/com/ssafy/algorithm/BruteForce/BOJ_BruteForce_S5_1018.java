package com.ssafy.algorithm.BruteForce;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

/*
 * @author 0at_x
 * @since 2022. 05. 13.
 * @see https://www.acmicpc.net/problem/1018
 * @category #BruteForce
 */

public class BOJ_BruteForce_S5_1018 {

	private static int M, N, result = Integer.MAX_VALUE;
	private static boolean[][] chess;
	private static StringTokenizer st;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		chess = new boolean[N][M];

		for (int r = 0; r < N; r++) {
			String str = br.readLine();
			for (int c = 0; c < M; c++) {
				if (str.charAt(c) == 'W') {
					chess[r][c] = true;
				}
			}
		}

		for (int r = 0; r < N - 7; r++) {
			for (int c = 0; c < M - 7; c++) {
				check(r, c);
			}
		}

		bw.write(result + "\n");

		br.close();
		bw.flush();
		bw.close();

	}

	private static void check(int a, int b) {
		int cnt = 0;

		boolean color = chess[a][b];

		for (int r = a; r < a + 8; r++) {
			for (int c = b; c < b + 8; c++) {
				if (chess[r][c] != color) {
					cnt++;
				}
				color = !color;
			}
			color = !color;
		}

		cnt = Math.min(cnt, 64 - cnt);

		result = Math.min(result, cnt);
	}

}
