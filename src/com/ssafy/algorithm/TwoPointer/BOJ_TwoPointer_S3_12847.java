package com.ssafy.algorithm.TwoPointer;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

/*
 * @author 0at_x
 * @since 2022. 04. 06.
 * @see https://www.acmicpc.net/problem/12847
 * @category #TwoPointer
 */ 

public class BOJ_TwoPointer_S3_12847 {

	private static int N, M;
	private static long sum, result;
	private static int[] T;
	private static StringTokenizer st;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		T = new int[N];

		st = new StringTokenizer(br.readLine());
		for (int n = 0; n < N; n++) {
			T[n] = Integer.parseInt(st.nextToken());

			if (n < M) {
				sum += T[n];
			} else {
				sum = sum - T[n - M] + T[n];
			}

			result = Math.max(result, sum);
		}

		bw.write(result + "\n");

		br.close();
		bw.flush();
		bw.close();

	}

}
