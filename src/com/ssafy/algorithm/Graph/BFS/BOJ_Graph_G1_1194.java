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
 * @since 2022. 04. 05.
 * @see https://www.acmicpc.net/problem/1194
 * @category #Graph
 */

public class BOJ_Graph_G1_1194 {

	private static int R, C;
	private static char[][] map;
	private static MinSik ms;
	private static int[][] deltas = { { -1, 0 }, { 1, 0 }, { 0, -1 }, { 0, 1 } };

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		StringTokenizer st = new StringTokenizer(br.readLine());
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());

		map = new char[R][C];

		for (int r = 0; r < R; r++) {
			map[r] = br.readLine().toCharArray();
			for (int c = 0; c < C; c++) {
				if (map[r][c] == '0') {
					ms = new MinSik(r, c, 0);
				}
			}
		}

		bw.write(bfs() + "\n");

		br.close();
		bw.flush();
		bw.close();

	}

	private static int bfs() {
		boolean[][][] visited = new boolean[R][C][1 << 6];
		Queue<MinSik> q = new LinkedList<MinSik>();

		q.offer(ms);
		visited[ms.r][ms.c][ms.keys] = true;

		int depth = 0;
		while (!q.isEmpty()) {
			int size = q.size();
			while (size-- > 0) {
				// 1. 맨 처음 노드 데려오기
				MinSik head = q.poll();

				// 2. 사용한다. - 정답을 찾거나 부가적인 행동
				if (map[head.r][head.c] == '1') {
					return depth;
				}
				// 키의 위에 있다면 --> update
				if (map[head.r][head.c] >= 'a' && map[head.r][head.c] <= 'f') {
					head.updateKey(map[head.r][head.c]);
				}

				// 3. 자식 노드 탐색
				for (int d = 0; d < deltas.length; d++) {
					int nr = head.r + deltas[d][0];
					int nc = head.c + deltas[d][1];

					if (isIn(nr, nc) && !visited[nr][nc][head.keys]) {
						if (map[nr][nc] == '#') {
							continue;
						}
						if (map[nr][nc] >= 'A' && map[nr][nc] <= 'F' && !head.hasKey(map[nr][nc])) {
							continue;
						}
						visited[nr][nc][head.keys] = true;
						q.offer(new MinSik(nr, nc, head.keys));
					}
				}
			}
			depth++;
		}

		return -1;
	}

	private static boolean isIn(int r, int c) {
		return 0 <= r && r < R && 0 <= c && c < C;
	}

	private static class MinSik {
		int r, c;
		int keys;

		public MinSik(int r, int c, int keys) {
			this.r = r;
			this.c = c;
			this.keys = keys;
		}

		public boolean hasKey(char gate) {
			return (keys & (1 << (gate - 'A'))) > 0;
		}

		public void updateKey(char key) {
			keys |= (1 << (key - 'a'));
		}
	}

}
