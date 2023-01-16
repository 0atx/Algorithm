package com.ssafy.algorithm.SegmentTree;

import java.io.*;
import java.util.StringTokenizer;

/*
 * @author 0at_x
 * @since 2022. 06. 25.
 * @see https://www.acmicpc.net/problem/2243
 * @category #SegmentTree
 */

public class BOJ_SegmentTree_P5_2243 {

    static int N;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();

        N = Integer.parseInt(br.readLine());
        SegmentTree seg = new SegmentTree(1000001);

        for (int n = 0; n < N; n++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int A = Integer.parseInt(st.nextToken());
            if (A == 1) {
                int B = Integer.parseInt(st.nextToken());
                sb.append(seg.get(1, 1, 1000000, B) + "\n");
            } else {
                int B = Integer.parseInt(st.nextToken());
                int C = Integer.parseInt(st.nextToken());
                seg.update(1, 1, 1000000, B, C);
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

        int get(int node, int start, int end, int rank) {
            tree[node]--;

            if (start == end) {
                return start;
            }

            if (tree[node * 2] >= rank) {
                return get(node * 2, start, (start + end) / 2, rank);
            }

            rank -= tree[node * 2];

            if (tree[node * 2 + 1] >= rank) {
                return get(node * 2 + 1, (start + end) / 2 + 1, end, rank);
            }

            return 0;
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
    }
}