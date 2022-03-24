package com.ssafy.algorithm.Greedy;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

/*
 * @author 0at_x
 * @since 2022. 03. 24.
 * @see https://www.acmicpc.net/problem/16112
 * @category #Greedy
 */

public class BOJ_Greedy_S2_16112 {

	private static int N, K;
	private static long[] Arcane;
	private static long result = 0;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());

		Arcane = new long[N];

		st = new StringTokenizer(br.readLine());
		for (int n = 0; n < N; n++) {
			Arcane[n] = Integer.parseInt(st.nextToken());
		}

		Arrays.sort(Arcane);

		for (int n = 1; n < N; n++) {
			if (n < K) {
				result += Arcane[n] * n; // 100 + 400
				n++;
			} else {
				result += Arcane[n] * K; // 300
			}
		}

		bw.write(result + "\n");

		br.close();
		bw.flush();
		bw.close();

	}

}
