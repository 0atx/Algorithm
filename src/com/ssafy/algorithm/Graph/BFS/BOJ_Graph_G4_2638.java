package com.ssafy.algorithm.Graph.BFS;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Queue;
import java.util.StringTokenizer;

/*
 * @author 0at_x
 * @since 2022. 03. 09.
 * @see https://www.acmicpc.net/problem/2638
 * @category #Graph
 */

public class BOJ_Graph_G4_2638 {

	private static int N, M, result;
	private static int[][] grid;
	private static boolean[][] visited;
	private static ArrayList<Point> cheese;
	private static int[] dr = { -1, 1, 0, 0 }; // 상, 하, 좌, 우
	private static int[] dc = { 0, 0, -1, 1 };

	private static class Point {
		int r;
		int c;

		Point(int r, int c) {
			this.r = r;
			this.c = c;
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		grid = new int[N][M];
		visited = new boolean[N][M];
		cheese = new ArrayList<>();

		for (int r = 0; r < N; r++) {
			st = new StringTokenizer(br.readLine());
			for (int c = 0; c < M; c++) {
				grid[r][c] = Integer.parseInt(st.nextToken());
				if (grid[r][c] == 1) {
					cheese.add(new Point(r, c));
				}
			}
		}

		visited = new boolean[N][M];
		dfs(0, 0);
		// bfs(0, 0);

		while (!cheese.isEmpty()) {
			search();
			visited = new boolean[N][M];
			dfs(0, 0);
			// bfs(0, 0);
		}

		bw.write(result + "\n");

		br.close();
		bw.flush();
		bw.close();

	}

	private static void search() {
		ArrayList<Point> melt = new ArrayList<>();

		for (int i = 0; i < cheese.size(); i++) {
			Point pt = cheese.get(i);
			int cnt = 0;

			for (int d = 0; d < 4; d++) {
				int nr = pt.r + dr[d];
				int nc = pt.c + dc[d];

				if (isOut(nr, nc)) {
					continue;
				}

				if (grid[nr][nc] == 2) {
					cnt++;
				}
			}

			if (cnt >= 2) {
				cheese.remove(i--);
				melt.add(pt);
			}
		}

		for (Point pt : melt) {
			grid[pt.r][pt.c] = 2;
		}

		result++;
	}

	private static void dfs(int r, int c) {
		visited[r][c] = true;
		grid[r][c] = 2;

		for (int d = 0; d < 4; d++) {
			int nr = r + dr[d];
			int nc = c + dc[d];

			if (isOut(nr, nc) || visited[nr][nc]) {
				continue;
			}

			if (grid[nr][nc] == 0 || grid[nr][nc] == 2) {
				dfs(nr, nc);
			}
		}
	}

	private static void bfs(int r, int c) {
		Queue<Point> melt = new ArrayDeque<>();
		melt.offer(new Point(r, c));
		visited[r][c] = true;

		while (!melt.isEmpty()) {
			Point pt = melt.poll();

			for (int d = 0; d < 4; d++) {
				int nr = pt.r + dr[d];
				int nc = pt.c + dc[d];

				if (isOut(nr, nc) || visited[nr][nc]) {
					continue;
				}

				if (grid[nr][nc] == 0 || grid[nr][nc] == 2) {
					melt.offer(new Point(nr, nc));
					grid[nr][nc] = 2;
					visited[nr][nc] = true;
				}
			}
		}
	}

	private static boolean isOut(int nr, int nc) {
		return nr < 0 || nr >= N || nc < 0 || nc >= M;
	}

}
