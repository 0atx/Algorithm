package com.ssafy.algorithm.Graph.BFS;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

/*
 * @author 0at_x
 * @since 2022. 03. 10.
 * @see https://www.acmicpc.net/problem/2667
 * @category #Graph
 */

public class BOJ_Graph_G4_2206 {

	private static int N, M;
	private static int[][] map;
	private static boolean[][][] visited;
	private static int[] dr = { -1, 1, 0, 0 }; // 상, 하, 좌, 우
	private static int[] dc = { 0, 0, -1, 1 };
	private static Queue<Route> route = new ArrayDeque<>();
	private static StringBuilder sb = new StringBuilder();

	private static class Route {
		int r;
		int c;
		int d;
		int b;

		Route(int r, int c, int d, int b) {
			this.r = r;
			this.c = c;
			this.d = d;
			this.b = b;
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		map = new int[N + 1][M + 1];
		visited = new boolean[N + 1][M + 1][2];

		for (int r = 1; r <= N; r++) {
			String str = br.readLine();
			for (int c = 1; c <= M; c++) {
				map[r][c] = str.charAt(c - 1) - '0';
			}
		}

		bfs(1, 1);

		bw.write(sb.toString() + "\n");

		br.close();
		bw.flush();
		bw.close();

	}

	private static void bfs(int r, int c) {
		route.offer(new Route(r, c, 1, 0));

		while (!route.isEmpty()) {
			Route cur = route.poll();

			if (cur.r == N && cur.c == M) {
				sb.append(cur.d + "\n");
				return;
			}

			for (int d = 0; d < 4; d++) {
				int nr = cur.r + dr[d];
				int nc = cur.c + dc[d];
				int nd = cur.d + 1;

				if (isOut(nr, nc)) {
					continue;
				}

				if (map[nr][nc] == 0) {
					if (cur.b == 0 && !visited[nr][nc][0]) {
						route.offer(new Route(nr, nc, nd, cur.b));
						visited[nr][nc][cur.b] = true;
					} else if (cur.b == 1 && !visited[nr][nc][1]) {
						route.offer(new Route(nr, nc, nd, cur.b));
						visited[nr][nc][cur.b] = true;
					}
				} else if (map[nr][nc] == 1) {
					if (cur.b == 0) {
						route.offer(new Route(nr, nc, nd, cur.b + 1));
						visited[nr][nc][cur.b + 1] = true;
					}
				}
			}
		}

		sb.append("-1\n");
	}

	private static boolean isOut(int nr, int nc) {
		return nr < 1 || nr > N || nc < 1 || nc > M;
	}

}