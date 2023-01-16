package com.ssafy.algorithm.SegmentTree;

import java.io.*;
import java.util.StringTokenizer;

/*
 * @author 0at_x
 * @since 2022. 06. 21.
 * @see https://www.acmicpc.net/problem/2357
 * @category #SegmentTree
 */

public class BOJ_SegmentTree_G1_2357 {

    static int N, M, min, max;
    static int[] arr;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();

        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        arr = new int[N + 1];
        SegmentTree segMin = new SegmentTree(N + 1);
        SegmentTree segMax = new SegmentTree(N + 1);

        for (int n = 1; n <= N; n++) {
            arr[n] = Integer.parseInt(br.readLine());
        }

        segMin.initMin(1, 1, N);
        segMax.initMax(1, 1, N);

        for (int m = 0; m < M; m++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            min = Integer.MAX_VALUE;
            max = Integer.MIN_VALUE;

            segMin.findMin(1, 1, N, a, b);
            segMax.findMax(1, 1, N, a, b);

            sb.append(min + " " + max + "\n");
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

        // 초기화 함수
        int initMin(int node, int start, int end) {
            if (start == end) {
                return tree[node] = arr[start];
            } else {
                return tree[node] = Math.min(initMin(node * 2, start, (start + end) / 2),
                        initMin(node * 2 + 1, (start + end) / 2 + 1, end));
            }
        }

        int initMax(int node, int start, int end) {
            if (start == end) {
                return tree[node] = arr[start];
            } else {
                return tree[node] = Math.max(initMax(node * 2, start, (start + end) / 2),
                        initMax(node * 2 + 1, (start + end) / 2 + 1, end));
            }
        }

        // 최솟값 함수
        void findMin(int node, int start, int end, int left, int right) {
            if (left > end || right < start) {
                return;
            } else if (left <= start && right >= end) {
                min = Math.min(min, tree[node]);
            } else {
                findMin(node * 2, start, (start + end) / 2, left, right);
                findMin(node * 2 + 1, (start + end) / 2 + 1, end, left, right);
            }
        }

        // 최댓값 함수
        void findMax(int node, int start, int end, int left, int right) {
            if (left > end || right < start) {
                return;
            } else if (left <= start && right >= end) {
                max = Math.max(max, tree[node]);
            } else {
                findMax(node * 2, start, (start + end) / 2, left, right);
                findMax(node * 2 + 1, (start + end) / 2 + 1, end, left, right);
            }
        }
    }
}
