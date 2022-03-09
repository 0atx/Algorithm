package com.ssafy.algorithm.Graph;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Collections;

/*
 * @author 0at_x
 * @since 2022. 03. 10.
 * @see https://www.acmicpc.net/problem/2667
 * @category #Graph
 */

public class BOJ_Graph_S1_2667 {

	private static int N, cnt;
	private static int[][] map;
	private static boolean[][] visited;
	private static ArrayList<Point> house;
	private static ArrayList<Integer> complex;
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

		N = Integer.parseInt(br.readLine());

		map = new int[N][N];
		visited = new boolean[N][N];
		house = new ArrayList<>();
		complex = new ArrayList<>();

		for (int r = 0; r < N; r++) {
			String[] str = br.readLine().split("");

			for (int c = 0; c < N; c++) {
				map[r][c] = Integer.parseInt(str[c]);
				if (map[r][c] == 1) {
					house.add(new Point(r, c));
				}
			}
		}

		for (int r = 0; r < N; r++) {
			for (int c = 0; c < N; c++) {
				if (map[r][c] == 1 && !visited[r][c]) {
					cnt = 1;
					search(r, c);
					complex.add(cnt);
				}
			}
		}

		Collections.sort(complex);

		bw.write(complex.size() + "\n");
		for (int c : complex) {
			bw.write(c + "\n");
		}

		br.close();
		bw.flush();
		bw.close();

	}

	private static void search(int r, int c) {
		visited[r][c] = true;

		for (int d = 0; d < 4; d++) {
			int nr = r + dr[d];
			int nc = c + dc[d];

			if (isIn(nr, nc) && map[nr][nc] == 1 && !visited[nr][nc]) {
				search(nr, nc);
				cnt++;
			}
		}
	}

	private static boolean isIn(int nr, int nc) {
		return nr >= 0 && nr < N && nc >= 0 && nc < N;
	}

}
