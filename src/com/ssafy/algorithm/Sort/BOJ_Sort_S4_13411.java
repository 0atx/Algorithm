package com.ssafy.algorithm.Sort;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map.Entry;
import java.util.StringTokenizer;
import java.util.TreeMap;

/*
 * @author 0at_x
 * @since 2022. 03. 21.
 * @see https://www.acmicpc.net/problem/13411
 * @category #Sort
 */

public class BOJ_Sort_S4_13411 {

	private static int N;
	private static TreeMap<Integer, Double> robots = new TreeMap<>();

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		N = Integer.parseInt(br.readLine());

		for (int n = 1; n <= N; n++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());

			robots.put(n, Math.sqrt((x * x) + (y * y)) / Integer.parseInt(st.nextToken()));
		}

		List<Entry<Integer, Double>> entryList = new ArrayList<Entry<Integer, Double>>(robots.entrySet());

		Collections.sort(entryList, new Comparator<Entry<Integer, Double>>() {
			public int compare(Entry<Integer, Double> o1, Entry<Integer, Double> o2) {
				return o1.getValue().compareTo(o2.getValue());
			}
		});

		for (Entry<Integer, Double> entry : entryList) {
			bw.write(entry.getKey() + "\n");
		}

		br.close();
		bw.flush();
		bw.close();

	}

}
