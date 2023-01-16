package com.ssafy.algorithm.SegmentTree;

import java.io.*;
import java.util.StringTokenizer;

/*
 * @author 0at_x
 * @since 2022. 06. 21.
 * @see https://www.acmicpc.net/problem/2042
 * @category #SegmentTree
 */

public class BOJ_SegmentTree_G1_2042 {

    static int N, M, K;
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

        for(int n = 1; n <= N; n++) {
            arr[n] = Long.parseLong(br.readLine());
        }

        seg.init(1, 1, N);

        for(int cnt = 0; cnt < M + K; cnt++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            long c = Long.parseLong(st.nextToken());

            switch(a) {
                case 1:
                    seg.update(1, 1, N, b, c - arr[b]);
                    arr[b] = c;
                    break;
                case 2:
                    sb.append(seg.sum(1, 1, N, b, (int) c) + "\n");
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
            if(start == end) {
                return tree[node] = arr[start];
            } else {
                return tree[node] = init(node * 2, start, (start + end) / 2)
                        + init(node * 2 + 1, (start + end) / 2 + 1, end);
            }
        }

        // 값 변경 함수
        void update(int node, int start, int end, int index, long diff) {
            // 범위 밖이라면
            if(index < start || index > end) {
                // 아무것도 안하고 리턴
                return;
            } else {  // 범위 안이라면
               tree[node] += diff;

                // 리프노드가 아니라면 > 계속 탐색
                if(start != end) {
                    update(node * 2, start, (start + end) /2 , index, diff);
                    update(node * 2 + 1, (start + end) / 2 + 1, end, index, diff);
                }
            }
        }

        // 합 계산 함수
        long sum(int node, int start, int end, int left, int right) {
            // 범위가 벗어남
            if(left > end || right < start) {
                return 0;
            } else if(left <= start && right >= end) { // 범위 포함하고 있음
                return tree[node];
            } else { // 그 외
                return sum(node * 2, start, (start + end) / 2, left, right)
                        + sum(node * 2 + 1, (start + end) / 2 + 1, end, left, right);
            }
        }
    }
}
