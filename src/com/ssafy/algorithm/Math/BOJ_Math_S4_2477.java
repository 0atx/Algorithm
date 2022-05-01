package com.ssafy.algorithm.Math;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

/*
 * @author 0at_x
 * @since 2022. 05. 01.
 * @see https://www.acmicpc.net/problem/2477
 * @category #Math
 */

public class BOJ_Math_S4_2477 {

	private static int K, max, sum, result;
	private static StringTokenizer st;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		K = Integer.parseInt(br.readLine());

		st = new StringTokenizer(br.readLine());
		st.nextToken();
		int first = Integer.parseInt(st.nextToken());
		int prev = first;

		for (int i = 1; i < 6; i++) {
			st = new StringTokenizer(br.readLine());
			st.nextToken();
			int now = Integer.parseInt(st.nextToken());
			max = Math.max(max, prev * now);
			sum += prev * now;
			prev = now;
		}

		max = Math.max(max, first * prev);
		sum += first * prev;

		result = (max - ((max * 3) - sum)) * K;

		bw.write(result + "\n");

		br.close();
		bw.flush();
		bw.close();

	}

}
