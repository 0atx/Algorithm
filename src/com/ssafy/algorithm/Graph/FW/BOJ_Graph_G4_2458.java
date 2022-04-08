package com.ssafy.algorithm.Graph.FW;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

/*
 * @author 0at_x
 * @since 2022. 04. 08.
 * @see https://www.acmicpc.net/problem/2458
 * @category #Graph
 */

public class BOJ_Graph_G4_2458 {

	private static int N, M, result, INF = 9999999;
	private static int[][] heights;
	private static StringTokenizer st;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		heights = new int[N + 1][N + 1];

		for (int m = 0; m < M; m++) {
			st = new StringTokenizer(br.readLine());

			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());

			heights[a][b] = 1;
		}

		floydWarshall();

		for (int i = 1; i <= N; i++) {
			for (int j = 1; j <= N; j++) {
				heights[i][0] += heights[i][j];
				heights[0][j] += heights[i][j];
			}
		}

		for (int i = 1; i <= N; i++) {
			if (heights[i][0] + heights[0][i] == N - 1) {
				result++;
			}
		}

		bw.write(result + "\n");

		br.close();
		bw.flush();
		bw.close();

	}

	private static void floydWarshall() {
		for (int k = 1; k <= N; ++k) {
			for (int i = 1; i <= N; ++i) {
				if (i == k || heights[i][k] == 0) {
					continue;
				}
				for (int j = 1; j <= N; ++j) {
					if (heights[i][j] == 1) {
						continue;
					}
					if (heights[k][j] == 1) {
						heights[i][j] = 1;
					}
				}
			}
		}
	}

}
