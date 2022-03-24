package com.ssafy.algorithm.Greedy;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

/*
 * @author 0at_x
 * @since 2022. 03. 24.
 * @see https://www.acmicpc.net/problem/15729
 * @category #Greedy
 */

public class BOJ_Greedy_S2_15729 {

	private static int N, result = 0;
	private static int[] button;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		N = Integer.parseInt(br.readLine());

		button = new int[N + 2];

		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int n = 0; n < N; n++) {
			button[n] = Integer.parseInt(st.nextToken());
		}

		for (int n = 0; n < N; n++) {
			if (button[n] == 1) {
				button[n + 1] = button[n + 1] == 1 ? 0 : 1;
				button[n + 2] = button[n + 2] == 1 ? 0 : 1;
				result++;
			}
		}

		bw.write(result + "\n");

		br.close();
		bw.flush();
		bw.close();

	}

}
