package com.ssafy.algorithm.Graph.LCA;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

/*
 * @author 0at_x
 * @since 2022. 04. 24.
 * @see https://www.acmicpc.net/problem/11812
 * @category #LCA
 */

public class BOJ_LCA_G3_11812 {

	private static long N;
	private static int K, Q;
	private static StringTokenizer st;
	private static StringBuilder sb = new StringBuilder();

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		st = new StringTokenizer(br.readLine());
		N = Long.parseLong(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		Q = Integer.parseInt(st.nextToken());

		for (int q = 0; q < Q; q++) {
			st = new StringTokenizer(br.readLine());
			long x = Long.parseLong(st.nextToken());
			long y = Long.parseLong(st.nextToken());

			if (K == 1) {
				sb.append(Math.abs(x - y) + "\n");
			} else {
				sb.append(LCA(x, y) + "\n");
			}
		}

		bw.write(sb.toString());

		br.close();
		bw.flush();
		bw.close();

	}

	private static int LCA(long x, long y) {
		int cnt = 0;
		while (x != y) {
			while (x > y) {
				x = (x + K - 2) / K;
				cnt++;
			}
			while (x < y) {
				y = (y + K - 2) / K;
				cnt++;
			}
		}
		return cnt;
	}

}
