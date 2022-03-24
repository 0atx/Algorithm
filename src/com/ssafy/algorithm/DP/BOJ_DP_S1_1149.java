package com.ssafy.algorithm.DP;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

/*
 * @author 0at_x
 * @since 2022. 03. 24.
 * @see https://www.acmicpc.net/problem/1149
 * @category #DP
 */

public class BOJ_DP_S1_1149 {

	private static int N, R, G, B;
	private static int[] houses = new int[3];

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		N = Integer.parseInt(br.readLine());

		for (int r = 0; r < N; r++) {
			StringTokenizer st = new StringTokenizer(br.readLine());

			R = Integer.parseInt(st.nextToken()) + Math.min(houses[1], houses[2]);
			G = Integer.parseInt(st.nextToken()) + Math.min(houses[0], houses[2]);
			B = Integer.parseInt(st.nextToken()) + Math.min(houses[0], houses[1]);

			houses[0] = R;
			houses[1] = G;
			houses[2] = B;

		}

		bw.write(Math.min(Math.min(R, G), B) + "\n");

		br.close();
		bw.flush();
		bw.close();

	}

}
