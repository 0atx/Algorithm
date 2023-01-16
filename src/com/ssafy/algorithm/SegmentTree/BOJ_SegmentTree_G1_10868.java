package com.ssafy.algorithm.SegmentTree;

import java.io.*;
import java.util.StringTokenizer;

/*
 * @author 0at_x
 * @since 2022. 06. 22.
 * @see https://www.acmicpc.net/problem/10868
 * @category #SegmentTree
 */

public class BOJ_SegmentTree_G1_10868 {

    static int N, M, min;
    static int[] arr;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();

        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        arr = new int[N + 1];
        SegmentTree seg = new SegmentTree(N + 1);


        for (int n = 1; n <= N; n++) {
            arr[n] = Integer.parseInt(br.readLine());
        }

        seg.init(1, 1, N);

        for (int m = 0; m < M; m++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            min = Integer.MAX_VALUE;

            seg.search(1, 1, N, a, b);
            sb.append(min + "\n");
        }

        bw.write(sb.toString());

        br.close();
        bw.flush();
        bw.close();

    }

    static class SegmentTree {

        int[] tree;

        SegmentTree(int n) {
            tree = new int[4 * n];
        }

        int init(int node, int start, int end) {
            if (start == end) {
                return tree[node] = arr[start];
            } else {
                return tree[node] = Math.min(init(node * 2, start, (start + end) / 2),
                        init(node * 2 + 1, (start + end) / 2 + 1, end));
            }
        }

        void search(int node, int start, int end, int left, int right) {
            if (left > end || right < start) {
                return;
            } else if (left <= start && end <= right) {
                min = Math.min(min, tree[node]);
            } else {
                search(node * 2, start, (start + end) / 2, left, right);
                search(node * 2 + 1, (start + end) / 2 + 1, end, left, right);
            }
        }
    }
}
