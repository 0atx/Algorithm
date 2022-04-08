package com.ssafy.algorithm.Math;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

/*
 * @author 0at_x
 * @since 2022. 04. 02.
 * @see https://www.acmicpc.net/problem/1002
 * @category #Math
 */

public class BOJ_Math_S4_1002 {

	private static int T, x1, x2, y1, y2, r1, r2;
	private static StringTokenizer st;
	private static StringBuilder sb = new StringBuilder();

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		T = Integer.parseInt(br.readLine());

		for (int t = 0; t < T; t++) {
			st = new StringTokenizer(br.readLine());

			x1 = Integer.parseInt(st.nextToken());
			y1 = Integer.parseInt(st.nextToken());
			r1 = Integer.parseInt(st.nextToken());

			x2 = Integer.parseInt(st.nextToken());
			y2 = Integer.parseInt(st.nextToken());
			r2 = Integer.parseInt(st.nextToken());

			sb.append(find(x1, y1, r1, x2, y2, r2) + "\n");

		}

		bw.write(sb.toString());

		br.close();
		bw.flush();
		bw.close();

	}

	private static int find(int x1, int y1, int r1, int x2, int y2, int r2) {
		int dist = (int) (Math.pow(x1 - x2, 2) + Math.pow(y1 - y2, 2)); // 중점 간 거리

		if (x1 == x2 && y1 == y2 && r1 == r2) { // 중점 같고 반지름도 같은 경우
			return -1;
		} else if (dist > Math.pow(r1 + r2, 2)) { // 두 원의 반지름 합보다 중점 간 거리가 더 길 때
			return 0;
		} else if (dist < Math.pow(r1 - r2, 2)) { // 원 안에 원이 있으나 내접하지 않을 때
			return 0;
		} else if (dist == Math.pow(r1 - r2, 2)) { // 내접할 때
			return 1;
		} else if (dist == Math.pow(r1 + r2, 2)) { // 외접할 때
			return 1;
		} else {
			return 2;
		}
	}

}
