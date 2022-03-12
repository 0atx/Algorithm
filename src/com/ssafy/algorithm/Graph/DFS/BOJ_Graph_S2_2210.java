package com.ssafy.algorithm.Graph.DFS;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.HashSet;
import java.util.StringTokenizer;

/*
 * @author 0at_x
 * @since 2022. 03. 10.
 * @see https://www.acmicpc.net/problem/2210
 * @category #Graph
 */

public class BOJ_Graph_S2_2210 {

	private static final int N = 5;
	private static int[][] board = new int[N][N];
	private static HashSet<String> result = new HashSet<>();
	private static int[] dr = { -1, 1, 0, 0 }; // 상, 하, 좌, 우
	private static int[] dc = { 0, 0, -1, 1 };
	private static StringTokenizer st;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		for (int r = 0; r < N; r++) {
			st = new StringTokenizer(br.readLine());
			for (int c = 0; c < N; c++) {
				board[r][c] = Integer.parseInt(st.nextToken());
			}
		}

		for (int r = 0; r < N; r++) {
			for (int c = 0; c < N; c++) {
				dfs(r, c, 0, new String());
			}
		}

		bw.write(result.size() + "\n");

		br.close();
		bw.flush();
		bw.close();

	}

	private static void dfs(int r, int c, int cnt, String str) {
		if (cnt == 6) {
			result.add(str);
			return;
		}

		for (int d = 0; d < 4; d++) {
			int nr = r + dr[d];
			int nc = c + dc[d];

			if (isOut(nr, nc)) {
				continue;
			}

			dfs(nr, nc, cnt + 1, str + board[r][c]);
		}

	}

	private static boolean isOut(int nr, int nc) {
		return nr < 0 || nr >= N || nc < 0 || nc >= N;
	}

}
