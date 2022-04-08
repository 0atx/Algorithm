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
 * @since 2022. 04. 07.
 * @see https://www.acmicpc.net/problem/1261
 * @category #Graph
 */

public class BOJ_Graph_G4_1261 {

	private static int N, M, result;
	private static int[][] map, dist;
	private static int[] dr = { -1, 1, 0, 0 };
	private static int[] dc = { 0, 0, -1, 1 };
	private static StringTokenizer st;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		st = new StringTokenizer(br.readLine());

		M = Integer.parseInt(st.nextToken());
		N = Integer.parseInt(st.nextToken());

		map = new int[N][M];
		dist = new int[N][M];

		for (int r = 0; r < N; r++) {
			char[] ch = br.readLine().toCharArray();
			for (int c = 0; c < M; c++) {
				map[r][c] = ch[c] - '0';
				dist[r][c] = Integer.MAX_VALUE;
			}
		}

		dijkstra();

		bw.write(result + "\n");

		br.close();
		bw.flush();
		bw.close();

	}

	private static void dijkstra() {
		PriorityQueue<Point> pq = new PriorityQueue<Point>();
		dist[0][0] = map[0][0];
		pq.offer(new Point(0, 0, map[0][0]));

		while (!pq.isEmpty()) {
			Point point = pq.poll();

			for (int d = 0; d < 4; d++) {
				int nr = point.r + dr[d];
				int nc = point.c + dc[d];

				if (isOut(nr, nc)) {
					continue;
				}

				if (dist[nr][nc] > dist[point.r][point.c] + map[nr][nc]) {
					dist[nr][nc] = dist[point.r][point.c] + map[nr][nc];
					pq.offer(new Point(nr, nc, dist[nr][nc]));
				}

			}
		}
		result = dist[N - 1][M - 1];
	}

	private static boolean isOut(int nr, int nc) {
		return nr < 0 || nr >= N || nc < 0 || nc >= M;
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
