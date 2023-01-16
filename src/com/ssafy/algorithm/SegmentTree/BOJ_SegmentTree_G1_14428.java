package com.ssafy.algorithm.SegmentTree;

import java.io.*;
import java.util.StringTokenizer;

/*
 * @author 0at_x
 * @since 2022. 06. 22.
 * @see https://www.acmicpc.net/problem/14428
 * @category #SegmentTree
 */

public class BOJ_SegmentTree_G1_14428 {

    static int N, M, min;
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
            int i = Integer.parseInt(st.nextToken());

            switch (n) {
                case 1:
                    int v = Integer.parseInt(st.nextToken());
                    arr[i] = v;
                    seg.update(1, 1, N, i);
                    break;
                case 2:
                    min = Integer.MAX_VALUE;
                    int j = Integer.parseInt(st.nextToken());
                    seg.search(1, 1, N, i, j);
                    sb.append(min + "\n");
                    break;
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
                return tree[node] = start;
            } else {
                return tree[node] = findMin(init(node * 2, start, (start + end) / 2), init(node * 2 + 1, (start + end) / 2 + 1, end));
            }
        }

        int update(int node, int start, int end, int index) {
            if (index < start || index > end) {
                return tree[node];
            } else {
                if (start == end) {
                    return tree[node] = index;
                } else {
                    return tree[node] = findMin(update(node * 2, start, (start + end) / 2, index), update(node * 2 + 1, (start + end) / 2 + 1, end, index));

                }
            }
        }

        int findMin(int left, int right) {
            if (arr[left] == arr[right]) {
                return Math.min(left, right);
            } else if (arr[left] < arr[right]) {
                return left;
            } else {
                return right;
            }
        }

        void search(int node, int start, int end, int left, int right) {
            if (left > end || right < start) {
                return;
            } else if (left <= start && end <= right) {
                if (min == Integer.MAX_VALUE) {
                    min = Math.min(min, tree[node]);
                } else if (arr[min] > arr[tree[node]]) {
                    min = tree[node];
                }
            } else {
                search(node * 2, start, (start + end) / 2, left, right);
                search(node * 2 + 1, (start + end) / 2 + 1, end, left, right);
            }
        }
    }
}
