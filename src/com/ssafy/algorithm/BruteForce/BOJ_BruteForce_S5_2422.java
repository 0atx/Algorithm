package com.ssafy.algorithm.BruteForce;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

/*
 * @author 0at_x
 * @since 2022. 04. 04.
 * @see https://www.acmicpc.net/problem/2422
 * @category #BruteForce
 */

public class BOJ_BruteForce_S5_2422 { 
	private static int N, M, result;
	private static boolean[][] arr;
	private static StringTokenizer st;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		arr = new boolean[N + 1][N + 1];

		for (int m = 0; m < M; m++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());

			arr[a][b] = true;
			arr[b][a] = true;
		}

		bruteforce();

		bw.write(result + "\n");

		br.close();
		bw.flush();
		bw.close();

	}

	private static void bruteforce() {
		for (int i = 1; i <= N; i++) {
			for (int j = i + 1; j <= N; j++) {
				for (int k = j + 1; k <= N; k++) {
					if (arr[i][j] || arr[i][k] || arr[j][k]) {
						continue;
					}
					result++;
				}
			}
		}
	}
}
