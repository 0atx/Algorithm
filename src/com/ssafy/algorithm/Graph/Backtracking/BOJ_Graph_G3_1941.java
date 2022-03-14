package com.ssafy.algorithm.Graph.Backtracking;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.LinkedList;
import java.util.Queue;

/*
 * @author 0at_x
 * @since 2022. 03. 14.
 * @see https://www.acmicpc.net/problem/1941
 * @category #Graph
 */

public class BOJ_Graph_G3_1941 {

	private static int result = 0;
	private static int[] comb = new int[7];
	private static char[] seat = new char[25];
	private static boolean[] visited = new boolean[7];

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		for (int r = 0; r < 5; r++) {
			String str = br.readLine();
			for (int c = 0; c < 5; c++) {
				seat[r * 5 + c] = str.charAt(c);
			}
		}

		dfs(0, 0, 0);

		bw.write(result + "\n");

		br.close();
		bw.flush();
		bw.close();

	}

	private static void dfs(int s, int dy, int cnt) {
		if (dy > 3) {
			return;
		}

		if (cnt == 7) {
			visited = new boolean[7];
			bfs();
			return;
		}

		for (int i = s; i < 25; i++) {
			comb[cnt] = i;
			if (seat[i] != 'Y') {
				dfs(i + 1, dy, cnt + 1);
			} else {
				dfs(i + 1, dy + 1, cnt + 1);
			}
		}

	}

	private static void bfs() {
		Queue<Integer> princess = new LinkedList<Integer>();
		princess.offer(comb[0]);
		visited[0] = true;

		int cnt = 1;

		while (!princess.isEmpty()) {
			int next = princess.poll();

			for (int p = 0; p < 7; p++) {
				if (!visited[p]) {
					if (isNext(p, next)) {
						visited[p] = true;
						princess.offer(comb[p]);
						cnt++;
					}
				}
			}
		}
		if (cnt == 7) {
			result++;
		}

	}

	private static boolean isNext(int p, int next) {
		return (comb[p] / 5 == next / 5) && (comb[p] - 1 == next) || (comb[p] / 5 == next / 5) && (comb[p] + 1 == next)
				|| (comb[p] == next + 5) || (comb[p] == next - 5);
	}

}
