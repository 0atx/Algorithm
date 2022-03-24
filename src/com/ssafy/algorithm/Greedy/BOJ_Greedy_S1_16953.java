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
 * @see https://www.acmicpc.net/problem/16953
 * @category #Greedy
 */

public class BOJ_Greedy_S1_16953 {

	private static int A, B, result = 1;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		StringTokenizer st = new StringTokenizer(br.readLine());

		A = Integer.parseInt(st.nextToken());
		B = Integer.parseInt(st.nextToken());

		while (A != B) {
			if (A > B) {
				bw.write(-1 + "\n");
				bw.flush();
				bw.close();
				return;
			}

			if (B % 10 == 1) {
				B /= 10;
			} else if (B % 2 == 0) {
				B /= 2;
			} else {
				bw.write(-1 + "\n");
				bw.flush();
				bw.close();
				return;
			}

			result++;
		}

		bw.write(result + "\n");

		br.close();
		bw.flush();
		bw.close();

	}

}
