package com.ssafy.algorithm.Greedy;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;

/*
 * @author 0at_x
 * @since 2022. 03. 24.
 * @see https://www.acmicpc.net/problem/16678
 * @category #Greedy
 */

public class BOJ_Greedy_G5_16678 {

	private static int N;
	private static long result = 0;
	private static int[] honor;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		N = Integer.parseInt(br.readLine());

		honor = new int[N];

		for (int n = 0; n < N; n++) {
			honor[n] = Integer.parseInt(br.readLine());
		}

		Arrays.sort(honor);

		for (int n = 0, defile = 1; n < N; n++) {
			if (honor[n] < defile)
				continue;
			result += honor[n] - defile;
			honor[n] = defile++;
		}

		bw.write(result + "\n");

		br.close();
		bw.flush();
		bw.close();

	}

}
