package com.ssafy.algorithm.SegmentTree;

import java.io.*;
import java.util.StringTokenizer;

/*
 * @author 0at_x
 * @since 2022. 06. 21.
 * @see https://www.acmicpc.net/problem/16975
 * @category #SegmentTree
 */

public class BOJ_SegmentTree_P4_16975 {

    static int N, M;
    static long[] arr;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();

        N = Integer.parseInt(br.readLine());

        arr = new long[N + 1];
        SegmentTree seg = new SegmentTree(N + 1);

        StringTokenizer st = new StringTokenizer(br.readLine());

        for (int n = 1; n <= N; n++) {
            arr[n] = Long.parseLong(st.nextToken());
        }

        seg.init(1, 1, N);

        M = Integer.parseInt(br.readLine());

        for (int m = 0; m < M; m++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());

            switch (a) {
                case 1:
                    int i = Integer.parseInt(st.nextToken());
                    int j = Integer.parseInt(st.nextToken());
                    long k = Integer.parseInt(st.nextToken());

                    seg.update(1, 1, N, i, j, k);
                    break;
                case 2:
                    int x = Integer.parseInt(st.nextToken());
                    sb.append(seg.print(1, 1, N, x, 0) + "\n");
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

        void init(int node, int start, int end) {
            if (start == end) {
                tree[node] = arr[start];
            } else {
                init(node * 2, start, (start + end) / 2);
                init(node * 2 + 1, (start + end) / 2 + 1, end);
            }
        }

        void update(int node, int start, int end, int left, int right, long add) {
            if (left > end || right < start) {
                return;
            } else if (left <= start && right >= end) {
                tree[node] += add;
            } else {
                update(node * 2, start, (start + end) / 2, left, right, add);
                update(node * 2 + 1, (start + end) / 2 + 1, end, left, right, add);
            }
        }

        long print(int node, int start, int end, int index, long sum) {
            if (index < start || index > end) {
                return 0;
            } else {
                sum += tree[node];
                if (start == end) {
                    return sum;
                } else {
                    return print(node * 2, start, (start + end) / 2, index, sum)
                            + print(node * 2 + 1, (start + end) / 2 + 1, end, index, sum);
                }
            }
        }
    }
}
