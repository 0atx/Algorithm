package com.ssafy.algorithm.Graph.BFS;

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
 * @since 2022. 03. 30.
 * @see https://www.acmicpc.net/problem/2636
 * @category #Graph
 */

public class BOJ_Graph_G4_2636 {

	private static int R, C, time, cheese, cnt;
	private static int[][] map;
	private static boolean[][] visited;
	private static int[] dr = { -1, 1, 0, 0 }; // 상, 하, 좌, 우
	private static int[] dc = { 0, 0, -1, 1 };
	private static StringTokenizer st;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		st = new StringTokenizer(br.readLine());
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		map = new int[R][C];

		for (int r = 0; r < R; r++) {
			st = new StringTokenizer(br.readLine());
			for (int c = 0; c < C; c++) {
				map[r][c] = Integer.parseInt(st.nextToken());
				if (map[r][c] == 1) {
					cheese++;
				}
			}
		}

		while (cheese != 0) {
			cnt = cheese;
			bfs();
			time++;
		}

		bw.write(time + "\n");
		bw.write(cnt + "\n");

		br.close();
		bw.flush();
		bw.close();

	}

	private static void bfs() {
		visited = new boolean[R][C];
		Queue<Point> air = new LinkedList<>(); // 공기를 탐색하기 위한 Queue
		// 출발!!
		visited[0][0] = true; // 외곽은 언제나 비어있다.
		air.offer(new Point(0, 0));

		while (!air.isEmpty()) {
			Point head = air.poll();

			for (int d = 0; d < 4; d++) {
				int nr = head.r + dr[d];
				int nc = head.c + dc[d];

				if (isOut(nr, nc) || visited[nr][nc]) {
					continue;
				}

				visited[nr][nc] = true;
				if (map[nr][nc] == 0) {
					air.offer(new Point(nr, nc));
				} else {
					cheese--;
					map[nr][nc] = 0;
				}
			}
		}
	}

	private static boolean isOut(int nr, int nc) {
		return nr < 0 || nr >= R || nc < 0 || nc >= C;
	}

	private static class Point {
		int r, c;

		public Point(int r, int c) {
			this.r = r;
			this.c = c;
		}
	}
}
