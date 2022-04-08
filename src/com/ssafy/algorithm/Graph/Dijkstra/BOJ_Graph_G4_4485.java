package com.ssafy.algorithm.Graph.Dijkstra;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

/*
 * @author 0at_x
 * @since 2022. 04. 01.
 * @see https://www.acmicpc.net/problem/4485
 * @category #Graph
 */

public class BOJ_Graph_G4_4485 {

	private static int N, result;
	private static int[][] cave, link;
	private static int[] dr = { -1, 1, 0, 0 }; // 상, 하, 좌, 우
	private static int[] dc = { 0, 0, -1, 1 };
	private static StringTokenizer st;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		int t = 0;
		while (true) {

			N = Integer.parseInt(br.readLine());
			result = 0;

			if (N == 0) {
				break;
			}

			cave = new int[N][N];
			link = new int[N][N];

			for (int r = 0; r < N; r++) {
				st = new StringTokenizer(br.readLine());
				for (int c = 0; c < N; c++) {
					cave[r][c] = Integer.parseInt(st.nextToken());
					link[r][c] = Integer.MAX_VALUE;
				}
			}

			dijkstra();
			t++;

			bw.write("Problem " + t + ": " + result + "\n");
		}

		br.close();
		bw.flush();
		bw.close();

	}

	private static void dijkstra() {
		PriorityQueue<Point> pq = new PriorityQueue<Point>();
		link[0][0] = cave[0][0];
		pq.offer(new Point(0, 0, cave[0][0]));

		while (!pq.isEmpty()) {
			Point point = pq.poll();

			for (int d = 0; d < 4; d++) {
				int nr = point.r + dr[d];
				int nc = point.c + dc[d];

				if (isIn(nr, nc)) {
					if (link[nr][nc] > link[point.r][point.c] + cave[nr][nc]) {
						link[nr][nc] = link[point.r][point.c] + cave[nr][nc];
						pq.offer(new Point(nr, nc, link[nr][nc]));
					}
				}

			}
		}
		result = link[N - 1][N - 1];
	}

	private static boolean isIn(int nr, int nc) {
		return nr >= 0 && nr < N && nc >= 0 && nc < N;
	}

	private static class Point implements Comparable<Point> {
		int r;
		int c;
		int cost;

		public Point(int r, int c, int cost) {
			this.r = r;
			this.c = c;
			this.cost = cost;
		}

		@Override
		public int compareTo(Point o) {
			return this.cost - o.cost;
		}
	}

}
