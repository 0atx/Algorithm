package com.ssafy.algorithm.BS;

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
 * @see https://www.acmicpc.net/problem/10815
 * @category #BinarySearch
 */

public class BOJ_BS_S4_10815 {

	private static int N, M;
	private static int[] cards;
	private static StringTokenizer st;
	private static StringBuilder sb = new StringBuilder();

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		N = Integer.parseInt(br.readLine());
		cards = new int[N];

		st = new StringTokenizer(br.readLine());
		for (int n = 0; n < N; n++) {
			cards[n] = Integer.parseInt(st.nextToken());
		}

		Arrays.sort(cards);

		M = Integer.parseInt(br.readLine());

		st = new StringTokenizer(br.readLine());
		for (int m = 0; m < M; m++) {
			binarySearch(Integer.parseInt(st.nextToken()));
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
			if (cards[mid] == target) {
				sb.append("1 ");
				return;
			} else if (cards[mid] <= target) {
				start = mid + 1;
			} else {
				end = mid - 1;
			}
			mid = (start + end) / 2;
		}
		sb.append("0 ");
	}

}
