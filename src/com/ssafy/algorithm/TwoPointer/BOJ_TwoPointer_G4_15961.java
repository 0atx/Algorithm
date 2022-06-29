package com.ssafy.algorithm.TwoPointer;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

/*
 * @author 0at_x
 * @since 2022. 04. 05.
 * @see https://www.acmicpc.net/problem/15961
 * @category #TwoPointer
 */

public class BOJ_TwoPointer_G4_15961 {

	private static int N, d, k, c, result;
	private static int[] sushi, visited;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		d = Integer.parseInt(st.nextToken());
		k = Integer.parseInt(st.nextToken());
		c = Integer.parseInt(st.nextToken());

		sushi = new int[N];
		visited = new int[d + 1];

		for (int n = 0; n < N; n++) {
			sushi[n] = Integer.parseInt(br.readLine());
		}

		slidingWindow();

		bw.write(result + "\n");

		br.close();
		bw.flush();
		bw.close();

	}

	private static void slidingWindow() {
		int cnt = 0;
		for (int i = 0; i < k; i++) {
			if (visited[sushi[i]] == 0) {
				cnt++;
			}
			visited[sushi[i]]++;
		}

		for (int n = 1; n < N; n++) {
			if (result <= cnt) {
				if (visited[c] == 0) {
					result = cnt + 1;
				} else {
					result = cnt;
				}
			}

			visited[sushi[n - 1]]--;

			if (visited[sushi[n - 1]] == 0) {
				cnt--;
			}

			if (visited[sushi[(n + k - 1) % N]] == 0) {
				cnt++;
			}
			visited[sushi[(n + k - 1) % N]]++;
		}
	}

}
