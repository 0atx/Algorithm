package com.ssafy.algorithm.Graph.DFS;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

/*
 * @author 0at_x
 * @since 2022. 04. 06.
 * @see https://www.acmicpc.net/problem/17471
 * @category #Graph
 */

public class BOJ_Graph_G4_17471 {

	private static int N, findCnt = 0, MIN = Integer.MAX_VALUE;
	private static int[] pops;
	private static boolean[][] graph;
	private static StringTokenizer st;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		N = Integer.parseInt(br.readLine()) + 1;
		pops = new int[N];

		st = new StringTokenizer(br.readLine());
		for (int n = 1; n < N; n++) {
			pops[n] = Integer.parseInt(st.nextToken());
		}

		// graph 구성하기
		graph = new boolean[N][N];
		for (int n = 1; n < N; n++) {
			st = new StringTokenizer(br.readLine());
			int cnt = Integer.parseInt(st.nextToken());

			for (int c = 0; c < cnt; c++) {
				int to = Integer.parseInt(st.nextToken());
				graph[n][to] = true;
			}
		}

		for (int n = 1; n <= N / 2; n++) {
			combination(n, new boolean[N], 1, n);
		}

		bw.write((MIN == Integer.MAX_VALUE ? -1 : MIN) + "\n");

		br.close();
		bw.flush();
		bw.close();
	}

	private static void combination(int toChoose, boolean[] choosed, int start, int size) {
		if (toChoose == 0) {
			int si = getStart(choosed, true);
			findCnt = 0;
			int pop1 = dfs(si, choosed, new boolean[N], true);

			if (findCnt != size) {
				return;
			}

			si = getStart(choosed, false);
			findCnt = 0;
			int pop2 = dfs(si, choosed, new boolean[N], false);

			if (findCnt != ((N - 1) - size)) {
				return;
			}

			MIN = Math.min(MIN, Math.abs(pop1 - pop2));

			return;
		}

		for (int n = start; n < N; n++) {
			choosed[n] = true;
			combination(toChoose - 1, choosed, n + 1, size);
			choosed[n] = false;
		}
	}

	private static int dfs(int i, boolean[] choosed, boolean[] visited, boolean check) {
		visited[i] = true;
		int pop = pops[i];
		findCnt++;

		for (int c = 1; c < N; c++) {
			if (!visited[c] && graph[i][c] && choosed[c] == check) {
				pop += dfs(c, choosed, visited, check);
			}
		}

		return pop;
	}

	private static int getStart(boolean[] choosed, boolean check) {
		for (int c = 1; c < choosed.length; c++) {
			if (choosed[c] == check) {
				return c;
			}
		}
		return -1;
	}

}
