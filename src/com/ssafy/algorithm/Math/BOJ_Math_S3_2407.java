package com.ssafy.algorithm.Math;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.math.BigInteger;
import java.util.StringTokenizer;

/*
 * @author 0at_x
 * @since 2022. 03. 25.
 * @see https://www.acmicpc.net/problem/2407
 * @category #Math
 */

public class BOJ_Math_S3_2407 {

	private static int N, M;
	private static BigInteger A = new BigInteger("1"), B = new BigInteger("1");

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		for (int m = 0; m < M; m++) {
			A = A.multiply(new BigInteger(String.valueOf(N - m)));
			B = B.multiply(new BigInteger(String.valueOf(m + 1)));
		}

		bw.write(A.divide(B) + "\n");

		br.close();
		bw.flush();
		bw.close();

	}

}
