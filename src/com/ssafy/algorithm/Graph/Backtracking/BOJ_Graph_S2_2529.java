package com.ssafy.algorithm.Graph.Backtracking;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;

/*
 * @author 0at_x
 * @since 2022. 03. 12.
 * @see https://www.acmicpc.net/problem/2529
 * @category #Graph
 */

public class BOJ_Graph_S2_2529 {

	private static int K;
	private static char[] signs;
	private static boolean flag;
	private static boolean[] visited = new boolean[10];
	private static List<String> result = new ArrayList<>();

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		K = Integer.parseInt(br.readLine());
		signs = new char[K];

		String[] str = br.readLine().split(" ");

		for (int k = 0; k < K; k++) {
			signs[k] = str[k].charAt(0);
		}

		dfs(new String(), 0, true);
		flag = false;
		dfs(new String(), 0, false);

		bw.write(result.get(0) + "\n");
		bw.write(result.get(1) + "\n");

		br.close();
		bw.flush();
		bw.close();

	}

	private static void dfs(String str, int cnt, boolean isMax) {
		if (cnt == K + 1) {
			if (!flag) {
				result.add(str);
				flag = true;
			}
			return;
		}

		if (isMax && !flag) {
			for (int n = 9; n >= 0; n--) {
				if (cnt == 0 || !visited[n] && compareSign(str.charAt(str.length() - 1) - '0', n, signs[cnt - 1])) {
					visited[n] = true;
					dfs(str + n, cnt + 1, isMax);
					visited[n] = false;
				}
			}
		} else if (!flag) {
			for (int n = 0; n <= 9; n++) {
				if (cnt == 0 || !visited[n] && compareSign(str.charAt(str.length() - 1) - '0', n, signs[cnt - 1])) {
					visited[n] = true;
					dfs(str + n, cnt + 1, isMax);
					visited[n] = false;
				}
			}
		}
	}

	private static boolean compareSign(int a, int b, char c) {
		return c == '<' ? a < b : a > b;
	}

}
