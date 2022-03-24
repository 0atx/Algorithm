package com.ssafy.algorithm.Math;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

/*
 * @author 0at_x
 * @since 2022. 03. 24.
 * @see https://www.acmicpc.net/problem/1629
 * @category #Math
 */

public class BOJ_Math_S1_1629 {

	private static long A, B, C;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		StringTokenizer st = new StringTokenizer(br.readLine());

		A = Long.parseLong(st.nextToken());
		B = Long.parseLong(st.nextToken());
		C = Long.parseLong(st.nextToken());

		bw.write(calc(A, B) + "\n");

		br.close();
		bw.flush();
		bw.close();

	}

	private static long calc(long a, long b) {
		if (b == 1) {
			return a % C;
		}

		long tmp = calc(a, b / 2);

		if (b % 2 == 1) {
			return (tmp * tmp % C) * a % C;
		}

		return tmp * tmp % C;

	}

}
