package com.ssafy.algorithm.BinarySearch;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

/*
 * @author 0at_x
 * @since 2022. 03. 22.
 * @see https://www.acmicpc.net/problem/3020
 * @category #BinarySearch
 */

public class BOJ_BinarySearch_G5_3020 {

	private static int N, H;
	private static int[] stalactite, stalagmite;
	private static int obstacles = Integer.MAX_VALUE, result = 0;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		H = Integer.parseInt(st.nextToken());

		stalactite = new int[H + 1];
		stalagmite = new int[H + 1];

		for (int n = 0; n < N; n++) {
			int h = Integer.parseInt(br.readLine());
			if (n % 2 == 0) {
				stalagmite[h]++;
			} else {
				stalactite[h]++;
			}
		}

		for (int h = H - 2; h > 0; h--) {
			stalactite[h] += stalactite[h + 1];
			stalagmite[h] += stalagmite[h + 1];
		}

		for (int h = 1; h <= H; h++) {
			if (stalagmite[h] + stalactite[H - h + 1] < obstacles) {
				obstacles = stalagmite[h] + stalactite[H - h + 1];
				result = 1;
			} else if (stalagmite[h] + stalactite[H - h + 1] == obstacles) {
				result++;
			}
		}

		bw.write(obstacles + " " + result + "\n");

		br.close();
		bw.flush();
		System.out.println();
		bw.close();

	}

}
