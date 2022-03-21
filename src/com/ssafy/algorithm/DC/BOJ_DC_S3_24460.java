package com.ssafy.algorithm.DC;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

/*
 * @author 0at_x
 * @since 2022. 03. 18.
 * @see https://www.acmicpc.net/problem/24460
 * @category #Divide&Conquer
 */

public class BOJ_DC_S3_24460 {

	private static int N, result;
	private static int[][] chairs;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		N = Integer.parseInt(br.readLine());
		chairs = new int[N][N];

		for (int r = 0; r < N; r++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int c = 0; c < N; c++) {
				chairs[r][c] = Integer.parseInt(st.nextToken());
			}
		}

		result = raffle(N, 0, 0, N - 1, N - 1);

		bw.write(result + "\n");

		br.close();
		bw.flush();
		bw.close();

	}

	private static int raffle(int s, int r, int c, int nr, int nc) {
		if (s == 1) {
			return chairs[r][c];
		}

		int[] nums = new int[4];

		s /= 2;

		nums[0] = raffle(s, r, c, nr / 2, nc / 2);
		nums[1] = raffle(s, r, c + s, nr / 2, nc);
		nums[2] = raffle(s, r + s, c, nr, nc / 2);
		nums[3] = raffle(s, r + s, c + s, nr, nc);

		Arrays.sort(nums);

		return nums[1];
	}

}
