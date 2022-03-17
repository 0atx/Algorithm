package com.ssafy.algorithm.BS;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

/*
 * @author 0at_x
 * @since 2022. 03. 17.
 * @see https://www.acmicpc.net/problem/19637
 * @category #BinarySearch
 */

public class BOJ_BS_S3_19637 {

	private static int N, M;
	private static String[] titles;
	private static int[] powers;
	private static StringTokenizer st;
	private static StringBuilder sb = new StringBuilder();

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		titles = new String[N];
		powers = new int[N];

		for (int n = 0; n < N; n++) {
			st = new StringTokenizer(br.readLine());
			titles[n] = st.nextToken();
			powers[n] = Integer.parseInt(st.nextToken());

		}

		for (int m = 0; m < M; m++) {
			binarySearch(Integer.parseInt(br.readLine()));
		}

		bw.write(sb.toString());

		br.close();
		bw.flush();
		bw.close();

	}

	private static void binarySearch(int target) {
		int start = 0;
		int end = N - 1;
		int mid = (start + end) / 2;

		while (end >= start) {
			if (powers[mid] < target) {
				start = mid + 1;
			} else {
				end = mid - 1;
			}
			mid = (start + end) / 2;
		}
		sb.append(titles[end + 1] + "\n");

	}

}
