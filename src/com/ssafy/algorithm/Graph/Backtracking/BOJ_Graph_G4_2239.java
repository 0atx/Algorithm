package com.ssafy.algorithm.Graph.Backtracking;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

/*
 * @author 0at_x
 * @since 2022. 04. 06.
 * @see https://www.acmicpc.net/problem/2239
 * @category #SlidingWindow
 */

public class BOJ_Graph_G4_2239 {

	private static int[][] map = new int[9][9];
	private static List<Point> blanks = new ArrayList<>();
	private static StringTokenizer st;
	private static StringBuilder sb = new StringBuilder();

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		for (int r = 0; r < 9; r++) {
			char[] line = br.readLine().toCharArray();
			for (int c = 0; c < 9; c++) {
				map[r][c] = line[c] - '0';
				if (map[r][c] == 0) {
					blanks.add(new Point(r, c));
				}
			}
		}

		makePerDup(0);

		bw.write(sb.toString());

		br.close();
		bw.flush();
		bw.close();

	}

	private static boolean makePerDup(int idx) {
		if (idx == blanks.size()) {
			for (int r = 0; r < 9; r++) {
				for (int c = 0; c < 9; c++) {
					sb.append(map[r][c]);
				}
				sb.append("\n");
			}
			return true;
		}

		Point p = blanks.get(idx);
		for (int i = 1; i < 10; i++) {
			if (canUse(p, i)) {
				map[p.r][p.c] = i;
				if (makePerDup(idx + 1)) {
					return true;
				}
				map[p.r][p.c] = 0;
			}

		}

		return false;
	}

	private static boolean canUse(Point p, int v) {
		int pr = p.r;
		int pc = p.c;
		
		for (int r = 0; r < 9; r++) {
			if (map[r][pc] == v) {
				return false;
			}
		}

		for (int c = 0; c < 9; c++) {
			if (map[pr][c] == v) {
				return false;
			}
		}

		int sr = pr / 3 * 3;
		int sc = pc / 3 * 3;
		for (int r = sr; r < sr + 3; r++) {
			for (int c = sc; c < sc + 3; c++) {
				if (map[r][c] == v) {
					return false;
				}
			}
		}
		return true;
	}

	private static class Point {
		int r, c;

		public Point(int r, int c) {
			this.r = r;
			this.c = c;
		}
	}

}
