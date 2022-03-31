package com.ssafy.algorithm.DP;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

/*
 * @author 0at_x
 * @since 2022. 03. 29.
 * @see https://www.acmicpc.net/problem/12852
 * @category #DP
 */

public class BOJ_DP_S1_12852 {

	private static int N;
	private static int[] calc, num;
	private static StringBuilder sb = new StringBuilder();

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		N = Integer.parseInt(br.readLine());
		calc = new int[N + 1];
		num = new int[N + 1];

		calc[1] = 0;

		for (int n = 2; n <= N; n++) {
			calc[n] = calc[n - 1] + 1;
			num[n] = n - 1;
			if (n % 3 == 0) {
				if (calc[n] > calc[n / 3] + 1) {
					calc[n] = calc[n / 3] + 1;
					num[n] = n / 3;
				}
			}

			if (n % 2 == 0) {
				if (calc[n] > calc[n / 2] + 1) {
					calc[n] = calc[n / 2] + 1;
					num[n] = n / 2;
				}
			}

		}

		bw.write(calc[N] + "\n");

		while (N != 0) {
			sb.append(N + " ");
			N = num[N];
		}

		bw.write(sb.toString());

		br.close();
		bw.flush();
		bw.close();

	}

}
