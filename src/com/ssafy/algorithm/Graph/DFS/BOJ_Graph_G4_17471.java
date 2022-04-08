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

	/**
	 * 크기에 따라 요소를 선택해보자!!
	 * 
	 * @param toChoose 선택해야 할(남은) 개수
	 * @param choosed  선택 결과
	 * @param start    출발점
	 * @param size     선택 해야하는 개수
	 */
	private static void combination(int toChoose, boolean[] choosed, int start, int size) {
		if (toChoose == 0) {
			// true인 그룹 탐색해보기 - 어디서 시작하지?
			int si = getStart(choosed, true);
			findCnt = 0;
			int pop1 = dfs(si, choosed, new boolean[N], true);

			// 완탐 실패!!
			if (findCnt != size) {
				return;
			}

			// false인 그룹 탐색해보기 - 어디서 시작하지?
			si = getStart(choosed, false);
			findCnt = 0;
			int pop2 = dfs(si, choosed, new boolean[N], false);

			// 완탐 실패!!
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

	/**
	 * 
	 * @param i       방문하는 idx
	 * @param choosed 구성 정보
	 * @param visited 방문 여부
	 * @param check   탐색할 값
	 * @return 탐색 결과 인구수의 합
	 */
	private static int dfs(int i, boolean[] choosed, boolean[] visited, boolean check) {
		// 1. 방문 표시
		visited[i] = true;
		// 2. 할 일 처리
		int pop = pops[i]; // 인구 가져오기
		findCnt++;

		// 3. 자식 탐색 - 미방문, 연결되어있고, check일 것
		for (int c = 1; c < N; c++) {
			if (!visited[c] && graph[i][c] && choosed[c] == check) {
				pop += dfs(c, choosed, visited, check);
			}
		}

		return pop;
	}

	/**
	 * 배열에서 check가 처음 나온 시점을 반환해보자.
	 * 
	 * @param choosed
	 * @param check
	 * @return
	 */
	private static int getStart(boolean[] choosed, boolean check) {
		for (int c = 1; c < choosed.length; c++) {
			if (choosed[c] == check) {
				return c;
			}
		}
		return -1;
	}

}
