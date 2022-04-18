package com.ssafy.algorithm.Tree;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Iterator;
import java.util.StringTokenizer;
import java.util.TreeMap;

/*
 * @author 0at_x
 * @since 2022. 04. 15.
 * @see https://www.acmicpc.net/problem/1822
 * @category #Tree
 */

public class BOJ_Tree_S4_1822 {

	private static int A, B;
	private static TreeMap<Integer, Integer> tree = new TreeMap<Integer, Integer>();
	private static StringTokenizer st;
	private static StringBuilder sb = new StringBuilder();

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		st = new StringTokenizer(br.readLine());
		A = Integer.parseInt(st.nextToken());
		B = Integer.parseInt(st.nextToken());

		st = new StringTokenizer(br.readLine());
		for (int a = 0; a < A; a++) {
			tree.put(Integer.parseInt(st.nextToken()), 1);
		}

		st = new StringTokenizer(br.readLine());
		for (int b = 0; b < B; b++) {
			int n = Integer.parseInt(st.nextToken());
			if (tree.containsKey(n)) {
				tree.remove(n);
			}
		}

		if (tree.isEmpty()) {
			sb.append("0\n");
		} else {
			sb.append(tree.size() + "\n");
			Iterator<Integer> keys = tree.keySet().iterator();
			while (keys.hasNext()) {
				Integer key = keys.next();
				sb.append(key + " ");

			}
			sb.setLength(sb.length() - 1);
			sb.append("\n");
		}

		bw.write(sb.toString());

		br.close();
		bw.flush();
		bw.close();

	}

}
