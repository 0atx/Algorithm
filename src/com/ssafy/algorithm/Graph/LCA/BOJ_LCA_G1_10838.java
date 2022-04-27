package com.ssafy.algorithm.Graph.LCA;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.HashSet;
import java.util.StringTokenizer;

/*
 * @author 0at_x
 * @since 2022. 04. 27.
 * @see https://www.acmicpc.net/problem/10838
 * @category #LCA
 */

public class BOJ_LCA_G1_10838 {

	private static int N, K, R, checkNum;
	private static int[] parents, colors, check;
	private static StringTokenizer st;
	private static StringBuilder sb = new StringBuilder();

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());

		parents = new int[N];
		colors = new int[N];
		check = new int[N];

		for (int k = 0; k < K; k++) {
			st = new StringTokenizer(br.readLine());

			R = Integer.parseInt(st.nextToken());

			switch (R) {
			case 1: // paint
				paint(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()),
						Integer.parseInt(st.nextToken()));
				break;
			case 2: // move
				parents[Integer.parseInt(st.nextToken())] = Integer.parseInt(st.nextToken());
				break;
			case 3: // count
				sb.append(count(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())) + "\n");
				break;
			}
		}

		bw.write(sb.toString());

		br.close();
		bw.flush();
		bw.close();

	}

	private static void paint(int a, int b, int c) {
		int lca = LCA(a, b);
		while (a != lca) {
			colors[a] = c;
			a = parents[a];
		}

		while (b != lca) {
			colors[b] = c;
			b = parents[b];
		}
	}

	private static int LCA(int a, int b) {
		if (a == b) {
			return a;
		}

		int cnt = 0;
		checkNum++;
		while (cnt++ < 1000) {
			if (a != 0) {
				if (check[a] == checkNum) {
					return a;
				}
				check[a] = checkNum;
				a = parents[a];
			}

			if (b != 0) {
				if (check[b] == checkNum) {
					return b;
				}
				check[b] = checkNum;
				b = parents[b];
			}

		}
		return 0;
	}

	private static int count(int a, int b) {
		HashSet<Integer> set = new HashSet<>();
		int lca = LCA(a, b);

		while (a != lca) {
			set.add(colors[a]);
			a = parents[a];
		}

		while (b != lca) {
			set.add(colors[b]);
			b = parents[b];
		}

		return set.size();
	}

}
