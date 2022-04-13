package com.ssafy.algorithm.Graph.FW;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

/*
 * @author 0at_x
 * @since 2022. 04. 13.
 * @see https://www.acmicpc.net/problem/11404
 * @category #Graph 
 */

public class BOJ_Graph_G4_11404 {

	private static int N, M, INF = 9999999;
	private static int[][] cities;
	private static StringTokenizer st;
	private static StringBuilder sb = new StringBuilder();

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		N = Integer.parseInt(br.readLine());
		M = Integer.parseInt(br.readLine());

		cities = new int[N + 1][N + 1];
		for (int r = 1; r <= N; r++) {
			for (int c = 1; c <= N; c++) {
				if (r != c) {
					cities[r][c] = INF;
				}
			}
		}

		for (int m = 0; m < M; m++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());

			cities[a][b] = Math.min(cities[a][b], c);
		}

		floydWarshall();

		for (int i = 1; i <= N; i++) {
			for (int j = 1; j <= N; j++) {
				if (cities[i][j] == INF) {
					sb.append("0 ");
				} else {
					sb.append(cities[i][j] + " ");
				}
			}
			sb.setLength(sb.length() - 1);
			sb.append("\n");
		}

		bw.write(sb.toString());

		br.close();
		bw.flush();
		bw.close();

	}

	private static void floydWarshall() {
		for (int k = 1; k <= N; ++k) {
			for (int i = 1; i <= N; ++i) {
				if (k == i) {
					continue;
				}
				for (int j = 1; j <= N; ++j) {
					if (i == j || k == j) {
						continue;
					}
					cities[i][j] = Math.min(cities[i][j], cities[i][k] + cities[k][j]);
				}
			}
		}
	}

}
