package com.ssafy.algorithm.SegmentTree;

import java.io.*;
import java.util.StringTokenizer;

/*
 * @author 0at_x
 * @since 2022. 06. 22.
 * @see https://www.acmicpc.net/problem/18436
 * @category #SegmentTree
 */

public class BOJ_SegmentTree_G1_18436 {

    static int N, M;
    static int[] arr;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();

        N = Integer.parseInt(br.readLine());

        arr = new int[N + 1];
        SegmentTree seg = new SegmentTree(N + 1);

        StringTokenizer st = new StringTokenizer(br.readLine());

        for (int n = 1; n <= N; n++) {
            arr[n] = Integer.parseInt(st.nextToken());
        }

        seg.init(1, 1, N);

        M = Integer.parseInt(br.readLine());

        for (int m = 0; m < M; m++) {
            st = new StringTokenizer(br.readLine());
            int n = Integer.parseInt(st.nextToken());

            if (n == 1) {
                int i = Integer.parseInt(st.nextToken());
                int x = Integer.parseInt(st.nextToken());
                if (arr[i] % 2 == 0 && x % 2 == 1) {
                    seg.update(1, 1, N, i, 0);
                } else if (arr[i] % 2 == 1 && x % 2 == 0) {
                    seg.update(1, 1, N, i, 1);
                }
                arr[i] = x;
            } else {
                int l = Integer.parseInt(st.nextToken());
                int r = Integer.parseInt(st.nextToken());

                if (n == 2) {
                    sb.append(seg.sum(1, 1, N, l, r) + "\n");
                } else {
                    sb.append(r - l + 1 - seg.sum(1, 1, N, l, r) + "\n");
                }
            }
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
                if (arr[start] % 2 == 0) {
                    return tree[node] = 1;
                } else {
                    return tree[node];
                }
            } else {
                return tree[node] = init(node * 2, start, (start + end) / 2)
                        + init(node * 2 + 1, (start + end) / 2 + 1, end);
            }
        }

        int update(int node, int start, int end, int index, int diff) {
            if (index < start || index > end) {
                return tree[node];
            } else {
                if (start == end) {
                    return tree[node] = diff;
                } else {
                    return tree[node] = update(node * 2, start, (start + end) / 2, index, diff)
                            + update(node * 2 + 1, (start + end) / 2 + 1, end, index, diff);
                }
            }
        }

        int sum(int node, int start, int end, int left, int right) {
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
