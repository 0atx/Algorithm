package com.ssafy.algorithm.Greedy;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

/*
 * @author 0at_x
 * @since 2022. 03. 24.
 * @see https://www.acmicpc.net/problem/2212
 * @category #Greedy
 */

public class BOJ_Greedy_G5_2212 {

	private static int N, K, result = 0;
	private static int[] sensor;
	private static Integer[] dist;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		N = Integer.parseInt(br.readLine());
		K = Integer.parseInt(br.readLine());

		if (K >= N) {
			bw.write(0 + "\n");

			bw.flush();
			bw.close();
			return;
		}

		sensor = new int[N];

		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int n = 0; n < N; n++) {
			sensor[n] = Integer.parseInt(st.nextToken());
		}

		Arrays.sort(sensor);

		dist = new Integer[N - 1];
		for (int n = 0; n < N - 1; n++) {
			dist[n] = sensor[n + 1] - sensor[n];
		}

		Arrays.sort(dist);

		for (int n = 0; n < N - K; n++) {
			result += dist[n];
		}

		bw.write(result + "\n");

		br.close();
		bw.flush();
		bw.close();

	}

}
