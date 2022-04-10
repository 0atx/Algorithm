package com.ssafy.algorithm.Graph.FW;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

/*
 * @author 0at_x
 * @since 2022. 04. 08.
 * @see https://www.acmicpc.net/problem/1058
 * @category #Graph 
 */

public class BOJ_Graph_S2_1058 {

	private static int N, result, INF = 9999999;
	private static int[][] friends;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		N = Integer.parseInt(br.readLine());
		friends = new int[N][N];

		for (int i = 0; i < N; ++i) {
			char[] ch = br.readLine().toCharArray();
			for (int j = 0; j < N; ++j) {
				if (ch[j] == 'Y') {
					friends[i][j] = 1;
				}
				if (i != j && friends[i][j] == 0) {
					friends[i][j] = INF;
				}
			}
		}

		floydWarshall();

		for (int i = 0; i < N; i++) {
			int cnt = 0;
			for (int j = 0; j < N; j++) {
				if (i == j) {
					continue;
				}
				if (friends[i][j] <= 2) {
					cnt++;
				}
			}
			result = Math.max(result, cnt);
		}

		bw.write(result + "\n");

		br.close();
		bw.flush();
		bw.close();
	}

	private static void floydWarshall() {
		for (int k = 0; k < N; ++k) {
			for (int i = 0; i < N; ++i) {
				for (int j = 0; j < N; ++j) {
					if (i == j || k == j || k == i) {
						continue;
					}
					friends[i][j] = Math.min(friends[i][j], friends[i][k] + friends[k][j]);
				}
			}
		}
	}

}
