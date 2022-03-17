package com.ssafy.algorithm.DC;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

/*
 * @author 0at_x
 * @since 2022. 03. 17.
 * @see https://www.acmicpc.net/problem/11582
 * @category #Divide&Conquer
 */

public class BOJ_DC_S4_11582 {

	private static int N, K;
	private static int[] tastes;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		N = Integer.parseInt(br.readLine());
		tastes = new int[N];

		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int n = 0; n < N; n++) {
			tastes[n] = Integer.parseInt(st.nextToken());
		}

		K = Integer.parseInt(br.readLine());

		int size = tastes.length / K;
		int s = 0;

		for (int k = 0; k < K; k++) {
			Arrays.sort(tastes, s, s + size);
			s += size;
		}

		for (int t : tastes) {
			bw.write(t + " ");
		}
		bw.write("\n");

		br.close();
		bw.flush();
		bw.close();

	}

}