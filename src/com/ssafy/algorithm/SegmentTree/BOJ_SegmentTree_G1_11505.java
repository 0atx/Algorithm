package com.ssafy.algorithm.SegmentTree;

import java.io.*;
import java.util.StringTokenizer;

/*
 * @author 0at_x
 * @since 2022. 06. 21.
 * @see https://www.acmicpc.net/problem/11505
 * @category #SegmentTree
 */

public class BOJ_SegmentTree_G1_11505 {

    static int N, M, K, MOD = 1000000007;
    static long[] arr;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();

        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        arr = new long[N + 1];
        SegmentTree seg = new SegmentTree(N + 1);

        for (int n = 1; n <= N; n++) {
            arr[n] = Long.parseLong(br.readLine());
        }

        seg.init(1, 1, N);

        for (int cnt = 0; cnt < M + K; cnt++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            long c = Long.parseLong(st.nextToken());

            switch (a) {
                case 1:
                    seg.update(1, 1, N, b, c);
                    arr[b] = c;
                    break;
                case 2:
                    sb.append(seg.product(1, 1, N, b, (int) c) + "\n");
                    break;
            }
        }

        bw.write(sb.toString());

        br.close();
        bw.flush();
        bw.close();
    }

    static class SegmentTree {

        // 트리 배열
        long[] tree;

        // 생성자
        SegmentTree(int n) {
            tree = new long[4 * n];
        }

        // 초기화 함수
        long init(int node, int start, int end) {
            if (start == end) {
                return tree[node] = arr[start];
            } else {
                return tree[node] = init(node * 2, start, (start + end) / 2)
                        * init(node * 2 + 1, (start + end) / 2 + 1, end)
                        % MOD;
            }
        }

        // 값 변경 함수
        long update(int node, int start, int end, int index, long diff) {
            if (index < start || index > end) {
                return tree[node];
            } else {
                if (start == end) {
                    return tree[node] = diff;
                } else {
                    return tree[node] = update(node * 2, start, (start + end) / 2, index, diff)
                    * update(node * 2 + 1, (start + end) / 2 + 1, end, index, diff)
                    % MOD;
                }
            }
        }

        // 곱 계산 함수
        long product(int node, int start, int end, int left, int right) {
            if (left > end || right < start) {
                return 1;
            } else if (left <= start && right >= end) {
                return tree[node];
            } else {
                return product(node * 2, start, (start + end) / 2, left, right)
                        * product(node * 2 + 1, (start + end) / 2 + 1, end, left, right)
                        % MOD;
            }
        }
    }
}
