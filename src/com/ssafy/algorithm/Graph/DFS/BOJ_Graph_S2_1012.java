package com.ssafy.algorithm.Graph.DFS;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/*
 * @author 0at_x
 * @since 2022. 04. 20. 
 * @see https://www.acmicpc.net/problem/1012
 * @category #Graph
 */

public class BOJ_Graph_S2_1012 {

	private static int T, N, M, K, result;
	private static int[][] map;
	private static boolean[][] visited;
	private static int[] dr = { -1, 1, 0, 0 };
	private static int[] dc = { 0, 0, -1, 1 };
	private static StringTokenizer st;
	private static StringBuilder sb = new StringBuilder();

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		T = Integer.parseInt(br.readLine());

		for (int t = 0; t < T; t++) {
			st = new StringTokenizer(br.readLine());

			M = Integer.parseInt(st.nextToken());
			N = Integer.parseInt(st.nextToken());
			K = Integer.parseInt(st.nextToken());

			map = new int[M][N];
			visited = new boolean[M][N];
			result = 0;

			for (int k = 0; k < K; k++) {
				st = new StringTokenizer(br.readLine());
				map[Integer.parseInt(st.nextToken())][Integer.parseInt(st.nextToken())] = 1;
			}

			for (int r = 0; r < M; r++) {
				for (int c = 0; c < N; c++) {
					if (map[r][c] == 1 && !visited[r][c]) {
						// bfs(r, c);
						dfs(r, c);
						result++;
					}
				}
			}

			sb.append(result + "\n");

		}

		bw.write(sb.toString());

		br.close();
		bw.flush();
		bw.close();

	}

	private static void dfs(int r, int c) {
		visited[r][c] = true;

		for (int d = 0; d < 4; d++) {
			int nr = r + dr[d];
			int nc = c + dc[d];

			if (isOut(nr, nc)) {
				continue;
			}

			if (map[nr][nc] == 1 && !visited[nr][nc]) {
				dfs(nr, nc);
			}
		}
	}

	private static void bfs(int r, int c) {
		Queue<int[]> q = new LinkedList<>();
		q.offer(new int[] { r, c });

		while (!q.isEmpty()) {
			int[] point = q.poll();

			r = point[0];
			c = point[1];
			visited[r][c] = true;

			for (int d = 0; d < 4; d++) {
				int nr = r + dr[d];
				int nc = c + dc[d];

				if (isOut(nr, nc)) {
					continue;
				}

				if (map[nr][nc] == 1 && !visited[nr][nc]) {
					q.offer(new int[] { nr, nc });
					visited[nr][nc] = true;
				}
			}
		}
	}

	private static boolean isOut(int r, int c) {
		return r < 0 || r >= M || c < 0 || c >= N;
	}

}
