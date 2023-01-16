package com.ssafy.algorithm.SegmentTree;

import java.io.*;
import java.util.StringTokenizer;

/*
 * @author 0at_x
 * @since 2022. 07. 06.
 * @see https://www.acmicpc.net/problem/9426
 * @category #SegmentTree
 */

public class BOJ_SegmentTree_P5_9426 {

    static int N, K, SIZE = 65537;
    static long result;
    static int arr[];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        arr = new int[N + 1];
        BOJ_SegmentTree_P5_1572.SegmentTree seg = new BOJ_SegmentTree_P5_1572.SegmentTree(SIZE);

        for (int n = 1; n <= N; n++) {
            arr[n] = Integer.parseInt(br.readLine());
            seg.update(1, 0, SIZE, arr[n], 1);
            if (n >= K) {
                result += seg.search(1, 0, SIZE, (K + 1) / 2);
                seg.update(1, 0, SIZE, arr[n - K + 1], -1);
            }
        }

        bw.write(result + "\n");

        br.close();
        bw.flush();
        bw.close();

    }

    static class SegmentTree {

        int[] tree;

        SegmentTree(int n) {
            tree = new int[4 * n];
        }

        void update(int node, int start, int end, int index, int diff) {
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

        int search(int node, int start, int end, int mid) {
            if (start == end) {
                return start;
            } else {
                if (mid <= tree[node * 2]) {
                    return search(node * 2, start, (start + end) / 2, mid);
                } else {
                    return search(node * 2 + 1, (start + end) / 2 + 1, end, mid - tree[node * 2]);
                }
            }
        }
    }
}
