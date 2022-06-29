package com.ssafy.algorithm.BinarySearch;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/*
 * @author 0at_x
 * @since 2022. 04. 05.
 * @see https://www.acmicpc.net/problem/12738
 * @category #BinarySearch
 */

public class BOJ_BinarySearch_G2_12738 {

	private static int[] arr;

	public static int search(int left, int right, int key) {
		int mid = 0;

		while (left < right) {
			mid = (left + right) / 2;

			if (arr[mid] < key) {
				left = mid + 1;
			} else {
				right = mid;
			}
		}
		return right;
	}

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		int N = Integer.parseInt(br.readLine());
		arr = new int[N + 1];
		Arrays.fill(arr, Integer.MIN_VALUE);
		int len = 0;
		int idx = 0;

		st = new StringTokenizer(br.readLine());

		for (int n = 1; n <= N; n++) {
			int num = Integer.parseInt(st.nextToken());
			if (arr[len] < num) {
				arr[++len] = num;
			} else {
				idx = search(0, len, num);
				arr[idx] = num;
			}

		}

		System.out.println(len);
	}

}
