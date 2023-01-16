package com.ssafy.algorithm.SegmentTree;

import java.io.*;
import java.util.StringTokenizer;

/*
 * @author 0at_x
 * @since 2022. 06. 22.
 * @see https://www.acmicpc.net/problem/1275
 * @category #SegmentTree
 */

public class BOJ_SegmentTree_G1_1275 {

    static int N, Q;
    static long[] arr;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();

        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        Q = Integer.parseInt(st.nextToken());

        arr = new long[N + 1];
        SegmentTree seg = new SegmentTree(N + 1);

        st = new StringTokenizer(br.readLine());

        for (int n = 1; n <= N; n++) {
            arr[n] = Long.parseLong(st.nextToken());
        }

        seg.init(1, 1, N);

        for (int q = 0; q < Q; q++) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            int a = Integer.parseInt(st.nextToken());
            long b = Long.parseLong(st.nextToken());
            sb.append(seg.sum(1, 1, N, Math.min(x, y), Math.max(x, y)) + "\n");
            seg.update(1, 1, N, a, b - arr[a]);
            arr[a] = b;
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

        long init(int node, int start, int end) {
            if (start == end) {
                return tree[node] = arr[start];
            } else {
                return tree[node] = init(node * 2, start, (start + end) / 2)
                        + init(node * 2 + 1, (start + end) / 2 + 1, end);
            }
        }

        void update(int node, int start, int end, int index, long diff) {
            if (index < start || index > end) {
                return;
            } else {
                tree[node] += diff;

                if (start != end) {
                    update(node * 2, start, (start + end) / 2, index, diff);
                    update(node * 2 + 1, (start + end) / 2 + 1, end, index, diff);
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
