package com.ssafy.algorithm.Graph.Backtracking;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

/*
 * @author 0at_x
 * @since 2022. 03. 31.
 * @see https://www.acmicpc.net/problem/15684
 * @category #Graph
 */

public class BOJ_Graph_G4_15684 {

	private static int N, M, H, result = -1;
	private static int[][] ladder;
	private static boolean isFinish = false;
	private static StringTokenizer st;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		H = Integer.parseInt(st.nextToken());

		ladder = new int[N + 1][H + 1];

		for (int m = 0; m < M; m++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken()); // 세로선

			ladder[b][a] = 1;
			ladder[b + 1][a] = -1;
		}

		int cnt = 0;

		for (int n = 1; n < N; n++) {
			int addCnt = 0;
			for (int h = 1; h <= H; h++) {
				if (ladder[n][h] == 1) {
					addCnt++;
				}
			}

			if (addCnt % 2 == 1) {
				cnt++;
			}

			if (cnt > 3) {
				bw.write("-1\n");
				br.close();
				bw.flush();
				bw.close();
				return;
			}
		}

		for (int i = 0; i <= 3; i++) {
			dfs(0, i);
		}

		bw.write(result + "\n");

		br.close();
		bw.flush();
		bw.close();

	}

	private static void dfs(int num, int end) {
		if (isFinish) {
			return;
		}

		if (num == end) {
			if (check()) {
				result = end;
				isFinish = true;
			}
			return;
		}

		for (int r = 1; r < N; r++) {
			for (int c = 1; c <= H; c++) {
				if (ladder[r][c] == 0 && ladder[r + 1][c] == 0) {
					ladder[r][c] = 1;
					ladder[r + 1][c] = -1;
					dfs(num + 1, end);
					ladder[r][c] = 0;
					ladder[r + 1][c] = 0;
				}
			}
		}
	}

	private static boolean check() {
		for (int n = 1; n <= N; n++) {
			int r = n;
			int c = 1;

			while (c <= H) {
				if (ladder[r][c] == 1) {
					r++;
				} else if (ladder[r][c] == -1) {
					r--;
				}
				c++;
			}
			if (r != n) {
				return false;
			}
		}
		return true;
	}

}
