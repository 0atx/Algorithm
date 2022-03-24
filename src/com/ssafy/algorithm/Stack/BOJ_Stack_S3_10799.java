package com.ssafy.algorithm.Stack;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Stack;

/*
 * @author 0at_x
 * @since 2022. 03. 24.
 * @see https://www.acmicpc.net/problem/10799
 * @category #Stack
 */

public class BOJ_Stack_S3_10799 {

	private static String str;
	private static int result = 0;
	private static Stack<Character> stack = new Stack<>();

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		str = br.readLine();

		for (int s = 0; s < str.length(); s++) {
			if (str.charAt(s) == '(') {
				stack.push(str.charAt(s));
			} else {
				stack.pop();
				if (str.charAt(s - 1) == '(') {
					result += stack.size();
				} else {
					result++;
				}
			}
		}

		bw.write(result + "\n");

		br.close();
		bw.flush();
		bw.close();

	}

}
