package com.ssafy.algorithm.DP;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

/*
 * @author 0at_x
 * @since 2022. 03. 21.
 * @see https://www.acmicpc.net/problem/1463
 * @category #DP
 */

public class BOJ_DP_S3_1463 {

	private static int N;
	private static int[] calc;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		N = Integer.parseInt(br.readLine());
		calc = new int[N + 1];

		for (int n = 2; n <= N; n++) {
			calc[n] = calc[n - 1] + 1;
			if (n % 2 == 0) {
				calc[n] = Math.min(calc[n], calc[n / 2] + 1);
			}
			if (n % 3 == 0) {
				calc[n] = Math.min(calc[n], calc[n / 3] + 1);
			}
		}

		bw.write(calc[N] + "\n");

		br.close();
		bw.flush();
		bw.close();

	}

}
