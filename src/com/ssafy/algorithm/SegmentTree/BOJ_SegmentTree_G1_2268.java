package com.ssafy.algorithm.SegmentTree;

import java.io.*;
import java.util.StringTokenizer;

/*
 * @author 0at_x
 * @since 2022. 06. 22.
 * @see https://www.acmicpc.net/problem/2268
 * @category #SegmentTree
 */

public class BOJ_SegmentTree_G1_2268 {

    static int N, M;
    static long[] arr;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();

        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        arr = new long[N + 1];
        SegmentTree seg = new SegmentTree(N + 1);

        for (int m = 0; m < M; m++) {
            st = new StringTokenizer(br.readLine());
            int n = Integer.parseInt(st.nextToken());
            int i = Integer.parseInt(st.nextToken());

            switch (n) {
                case 0:
                    int j = Integer.parseInt(st.nextToken());
                    sb.append(seg.sum(1, 1, N, Math.min(i, j), Math.max(i, j)) + "\n");
                    break;
                case 1:
                    long k = Long.parseLong(st.nextToken());
                    seg.modify(1, 1, N, i, k - arr[i]);
                    arr[i] = k;
                    break;
            }

        }

        bw.write(sb.toString());

        br.close();
        bw.flush();
        bw.close();

    }

    static class SegmentTree {

        long[] tree;

        SegmentTree(int n) {
            tree = new long[4 * n];
        }

        void modify(int node, int start, int end, int index, long diff) {
            if (index < start || index > end) {
                return;
            } else {
                tree[node] += diff;

                if (start != end) {
                    modify(node * 2, start, (start + end) / 2, index, diff);
                    modify(node * 2 + 1, (start + end) / 2 + 1, end, index, diff);
                }
            }
        }

        long sum(int node, int start, int end, int left, int right) {
            if (left > end || right < start) {
                return 0;
            } else if (left <= start && end <= right) {
                return tree[node];
            } else {
                return sum(node * 2, start, (start + end) / 2, left, right)
                        + sum(node * 2 + 1, (start + end) / 2 + 1, end, left, right);
            }
        }
    }
}
