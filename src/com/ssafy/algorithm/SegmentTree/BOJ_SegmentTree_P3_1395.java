package com.ssafy.algorithm.SegmentTree;

import java.io.*;
import java.util.StringTokenizer;

/*
 * @author 0at_x
 * @since 2022. 06. 28.
 * @see https://www.acmicpc.net/problem/1395
 * @category #SegmentTree
 */

public class BOJ_SegmentTree_P3_1395 {

    static int N, M;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        SegmentTree seg = new SegmentTree(N + 1);

        for (int m = 0; m < M; m++) {
            st = new StringTokenizer(br.readLine());
            int o = Integer.parseInt(st.nextToken());
            int s = Integer.parseInt(st.nextToken());
            int t = Integer.parseInt(st.nextToken());

            switch (o) {
                case 0:
                    seg.update(1, 1, N, s, t);
                    break;
                case 1:
                    sb.append(seg.sum(1, 1, N, s, t) + "\n");
            }
        }

        bw.write(sb.toString());

        br.close();
        bw.flush();
        bw.close();

    }

    static class SegmentTree {

        int[] tree, lazy;

        SegmentTree(int n) {
            tree = new int[4 * n];
            lazy = new int[4 * n];
        }

        void propagate(int node, int start, int end) {
            if (lazy[node] % 2 == 0) {
                return;
            }

            if (start != end) {
                lazy[node * 2] += lazy[node];
                lazy[node * 2 + 1] += lazy[node];
            }

            tree[node] = (end - start + 1) - tree[node];
            lazy[node] = 0;
        }

        void update(int node, int start, int end, int left, int right) {
            propagate(node, start, end);

            if (left > end || right < start) {
                return;
            } else if (left <= start && end <= right) {
                lazy[node] = 1;
                propagate(node, start, end);
                return;
            }

            update(node * 2, start, (start + end) / 2, left, right);
            update(node * 2 + 1, (start + end) / 2 + 1, end, left, right);
            tree[node] = tree[node * 2] + tree[node * 2 + 1];
        }

        int sum(int node, int start, int end, int left, int right) {
            propagate(node, start, end);

            if (left > end || right < start) {
                return 0;
            } else if (left <= start && end <= right) {
                return tree[node];
            }

            return sum(node * 2, start, (start + end) / 2, left, right)
                    + sum(node * 2 + 1, (start + end) / 2 + 1, end, left, right);
        }
    }
}
