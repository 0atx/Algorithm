package com.ssafy.algorithm.Stack;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Stack;

/*
 * @author 0at_x
 * @since 2022. 03. 07.
 * @see https://www.acmicpc.net/problem/9935
 * @category #Stack
 */

public class BOJ_Stack_G4_9935 {

	private static String str, bomb;
	private static boolean findBomb;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		str = br.readLine();
		bomb = br.readLine();
		int strlen = str.length();
		int bomblen = bomb.length();
		Stack<Character> stack = new Stack<>();

		for (int s = 0; s < strlen; s++) {
			stack.push(str.charAt(s));

			if (stack.size() >= bomblen) {
				findBomb = true;

				for (int i = 0; i < bomblen; i++) {
					if (stack.get(stack.size() - bomblen + i) != bomb.charAt(i)) {
						findBomb = false;
						break;
					}
				}

				if (findBomb) {
					for (int b = 0; b < bomblen; b++) {
						stack.pop();
					}
				}

			}
		}

		if (stack.isEmpty()) {
			bw.write("FRULA\n");
		} else {
			StringBuilder sb = new StringBuilder();

			for (char c : stack) {
				sb.append(c);
			}

			bw.write(sb.toString() + "\n");
		}

		br.close();
		bw.flush();
		bw.close();
	}

}
