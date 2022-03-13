package com.ssafy.algorithm.Graph.Backtracking;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

/*
 * @author 0at_x
 * @since 2022. 03. 14.
 * @see https://www.acmicpc.net/problem/15270
 * @category #Graph
 */

public class BOJ_Graph_S3_15270 {

	private static int N, M, result = 0;
	private static int[][] friends;
	private static boolean[] visited;
	private static StringTokenizer st;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		friends = new int[M][2];
		visited = new boolean[N + 1];

		for (int m = 0; m < M; m++) {
			st = new StringTokenizer(br.readLine());

			friends[m][0] = Integer.parseInt(st.nextToken());
			friends[m][1] = Integer.parseInt(st.nextToken());
		}

		staging(0, 0);

		result *= 2;

		result = result < N ? result + 1 : result;

		bw.write(result + "\n");

		br.close();
		bw.flush();
		bw.close();

	}

	private static void staging(int n, int cnt) {
		if (n == M) {
			result = Math.max(result, cnt);
			return;
		}

		if (visited[friends[n][0]] || visited[friends[n][1]]) {
			staging(n + 1, cnt);
		} else {
			visited[friends[n][0]] = true;
			visited[friends[n][1]] = true;

			staging(n + 1, cnt + 1);

			visited[friends[n][0]] = false;
			visited[friends[n][1]] = false;

			staging(n + 1, cnt);
		}
	}

}