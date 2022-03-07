package com.ssafy.algorithm.Math;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

/*
 * @author 0at_x
 * @since 2022. 03. 07.
 * @see https://www.acmicpc.net/problem/1748
 * @category #Math
 */

public class BOJ_Math_S3_1748 {

	private static int N, result = 0;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		N = Integer.parseInt(br.readLine());

		int add = 1, ten = 10;

		for (int n = 1; n <= N; n++) {
			if (n % ten == 0) {
				add++;
				ten *= 10;
			}
			result += add;
		}

		bw.write(result + "\n");

		br.close();
		bw.flush();
		bw.close();

	}

}
