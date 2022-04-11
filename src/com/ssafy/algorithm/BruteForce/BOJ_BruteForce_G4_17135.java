package com.ssafy.algorithm.BruteForce;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

/*
 * @author 0at_x
 * @since 2022. 04. 08.
 * @see https://www.acmicpc.net/problem/17135
 * @category #BruteForce
 */

public class BOJ_BruteForce_G4_17135 {

	private static int R, C, D, MAX = Integer.MIN_VALUE;
	private static List<Enemy> enemies;
	private static StringTokenizer st;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		st = new StringTokenizer(br.readLine());
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		D = Integer.parseInt(st.nextToken());

		enemies = new ArrayList<>();
		for (int r = 0; r < R; r++) {
			st = new StringTokenizer(br.readLine());
			for (int c = 0; c < C; c++) {
				if (st.nextToken().equals("1")) {
					enemies.add(new Enemy(r, c));
				}
			}
		}

		combination(3, new int[3], 0);

		bw.write(MAX + "\n");

		br.close();
		bw.flush();
		bw.close();

	}

	private static void combination(int toChoose, int[] choosed, int start) {
		if (toChoose == 0) {
			game(choosed);
			return;
		}
		for (int i = start; i < C; i++) {
			choosed[choosed.length - toChoose] = i;
			combination(toChoose - 1, choosed, i + 1);
		}
	}

	private static void game(int[] choosed) {
		List<Enemy> copied = new ArrayList<>();
		for (Enemy e : enemies) {
			copied.add(new Enemy(e.r, e.c));
		}

		int deadMan = 0;
		while (true) {
			for (int archer : choosed) {
				Enemy target = null;
				for (Enemy enemy : copied) {
					enemy.setDistance(archer);
					if (enemy.inRange) {
						if (target == null || target.compareTo(enemy) > 0) {
							target = enemy;
						}
					}
				}

				if (target != null) {
					target.isTarget = true;
				}
			}

			for (int e = copied.size() - 1; e >= 0; e--) {
				Enemy enemy = copied.get(e);
				if (enemy.isTarget) {
					deadMan++;
					copied.remove(enemy);
				} else {
					if (enemy.r == R - 1) {
						copied.remove(enemy);
					} else {
						enemy.r++;
					}
				}
			}

			if (copied.size() == 0) {
				break;
			}
		}

		MAX = Math.max(MAX, deadMan);
	}

	static class Enemy implements Comparable<Enemy> {
		int r, c, d;

		boolean inRange;
		boolean isTarget;

		public Enemy(int r, int c) {
			this.r = r;
			this.c = c;
		}

		public void setDistance(int archer) {
			d = Math.abs(archer - this.c) + Math.abs(R - this.r);
			inRange = d <= D;
		}

		@Override
		public int compareTo(Enemy o) {
			if (d == o.d) {
				return Integer.compare(c, o.c);
			} else {
				return Integer.compare(d, o.d);
			}
		}

	}
}