package com.ssafy.algorithm.Graph.BFS;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

/*
 * @author 0at_x
 * @since 2022. 04. 08.
 * @see https://www.acmicpc.net/problem/14502
 * @category #Graph
 */

public class BOJ_Graph_G5_14502 {

	private static int N, M, result;
	private static int[][] map;
	private static List<Point> virus, spaces;
	private static int[] dr = { -1, 1, 0, 0 };
	private static int[] dc = { 0, 0, -1, 1 };
	private static StringTokenizer st;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		map = new int[N][M];
		virus = new ArrayList<>();
		spaces = new ArrayList<>();

		for (int r = 0; r < N; r++) {
			st = new StringTokenizer(br.readLine());
			for (int c = 0; c < M; c++) {
				map[r][c] = Integer.parseInt(st.nextToken());
				if (map[r][c] == 0) {
					spaces.add(new Point(r, c));
				} else if (map[r][c] == 2) {
					virus.add(new Point(r, c));
				}
			}
		}

		makeWall(3, new Point[3], 0);

		bw.write(result + "\n");

		br.close();
		bw.flush();
		bw.close();

	}

	private static void makeWall(int toChoose, Point[] choosed, int start) {
		if (toChoose == 0) {
			check(choosed);
			return;
		}

		for (int i = start; i < spaces.size(); i++) {
			choosed[choosed.length - toChoose] = spaces.get(i);
			makeWall(toChoose - 1, choosed, i + 1);
		}
	}

	private static void check(Point[] choosed) {
		for (Point p : choosed) {
			map[p.r][p.c] = 1;
		}

		int polluted = spread();

		result = Math.max(result, spaces.size() - polluted - 3);

		for (Point p : choosed) {
			map[p.r][p.c] = 0;
		}
	}

	private static int spread() {
		int pollute = 0;
		Queue<Point> q = new LinkedList<>();
		boolean[][] visited = new boolean[N][M];

		for (Point p : virus) {
			q.offer(p);
			visited[p.r][p.c] = true;
		}

		while (!q.isEmpty()) {
			Point v = q.poll();

			for (int d = 0; d < 4; d++) {
				int nr = v.r + dr[d];
				int nc = v.c + dc[d];

				if (isIn(nr, nc) && !visited[nr][nc] && map[nr][nc] == 0) {
					visited[nr][nc] = true;
					pollute++;
					q.add(new Point(nr, nc));
				}
			}
		}

		return pollute;
	}

	private static boolean isIn(int nr, int nc) {
		return nr >= 0 && nr < N && nc >= 0 && nc < M;
	}

	private static class Point {
		int r;
		int c;

		public Point(int r, int c) {
			this.r = r;
			this.c = c;
		}
	}

}
