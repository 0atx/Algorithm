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
 * @see https://www.acmicpc.net/problem/1600
 * @category #Graph
 */

public class BOJ_Graph_G4_1600 {

	private static int K, W, H, result = -1;
	private static int[][] map;
	private static boolean[][][] visited;
	private static int[] dr = { -1, 1, 0, 0 }; // 상, 하, 좌, 우
	private static int[] dc = { 0, 0, -1, 1 };
	private static int[] hr = { -2, -2, 2, 2, -1, 1, -1, 1 }; // 말 이동
	private static int[] hc = { -1, 1, -1, 1, -2, -2, 2, 2 };
	private static StringTokenizer st;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		K = Integer.parseInt(br.readLine());

		st = new StringTokenizer(br.readLine());
		W = Integer.parseInt(st.nextToken());
		H = Integer.parseInt(st.nextToken());
		map = new int[H][W];

		for (int r = 0; r < H; r++) {
			st = new StringTokenizer(br.readLine());
			for (int c = 0; c < W; c++) {
				map[r][c] = Integer.parseInt(st.nextToken());
			}
		}

		bfs();

		bw.write(result + "\n");

		br.close();
		bw.flush();
		bw.close();

	}

	private static void bfs() {
		visited = new boolean[H][W][K + 1];
		Queue<Monkey> monkey = new LinkedList<>();

		visited[0][0][0] = true;
		monkey.offer(new Monkey(0, 0, 0, 0));

		while (!monkey.isEmpty()) {
			Monkey cur = monkey.poll();

			if (cur.r == H - 1 && cur.c == W - 1) {
				result = cur.cnt;
				return;
			}

			for (int d = 0; d < 4; d++) {
				int nr = cur.r + dr[d];
				int nc = cur.c + dc[d];
				int nk = cur.k;
				int ncnt = cur.cnt + 1;

				if (isOut(nr, nc) || map[nr][nc] == 1 || visited[nr][nc][nk]) {
					continue;
				}

				visited[nr][nc][nk] = true;
				monkey.offer(new Monkey(nr, nc, nk, ncnt));
			}

			if (cur.k == K) {
				continue;
			}

			for (int h = 0; h < 8; h++) {
				int nr = cur.r + hr[h];
				int nc = cur.c + hc[h];
				int nk = cur.k + 1;
				int ncnt = cur.cnt + 1;

				if (isOut(nr, nc) || map[nr][nc] == 1 || visited[nr][nc][nk]) {
					continue;
				}

				visited[nr][nc][nk] = true;
				monkey.offer(new Monkey(nr, nc, nk, ncnt));
			}

		}

		result = -1;
	}

	private static boolean isOut(int nr, int nc) {
		return nr < 0 || nr >= H || nc < 0 || nc >= W;
	}

	private static class Monkey {
		int r, c, k, cnt;

		public Monkey(int r, int c, int k, int cnt) {
			this.r = r;
			this.c = c;
			this.k = k;
			this.cnt = cnt;
		}
	}

}
