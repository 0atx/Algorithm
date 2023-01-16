package com.ssafy.algorithm.SegmentTree;

import java.io.*;
import java.util.StringTokenizer;

/*
 * @author 0at_x
 * @since 2022. 06. 24.
 * @see https://www.acmicpc.net/problem/5676
 * @category #SegmentTree
 */

public class BOJ_SegmentTree_G1_5676 {

    static int N, K;
    static char result;
    static long[] arr;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();

        while (br.ready()) {

            StringTokenizer st = new StringTokenizer(br.readLine());

            N = Integer.parseInt(st.nextToken());
            K = Integer.parseInt(st.nextToken());

            arr = new long[N + 1];
            SegmentTree seg = new SegmentTree(N + 1);

            st = new StringTokenizer(br.readLine());

            for (int n = 1; n <= N; n++) {
                arr[n] = Long.parseLong(st.nextToken());
            }

            seg.init(1, 1, N);

            for (int k = 0; k < K; k++) {
                st = new StringTokenizer(br.readLine());
                char c = st.nextToken().charAt(0);
                int i = Integer.parseInt(st.nextToken());

                switch (c) {
                    case 'C':
                        int v = Integer.parseInt(st.nextToken());
                        seg.update(1, 1, N, i, v);
                        arr[i] = v;
                        break;
                    case 'P':
                        int j = Integer.parseInt(st.nextToken());
                        result = '+';
                        seg.product(1, 1, N, i, j);
                        sb.append(result);
                        break;
                }
            }
            sb.append("\n");
        }

        bw.write(sb.toString());

        br.close();
        bw.flush();
        bw.close();
    }

    static class SegmentTree {

        // 트리 배열
        char[] tree;

        // 생성자
        SegmentTree(int n) {
            tree = new char[4 * n];
        }

        // 초기화 함수
        char init(int node, int start, int end) {
            if (start == end) {
                if (arr[start] > 0) {
                    return tree[node] = '+';
                } else if (arr[start] < 0) {
                    return tree[node] = '-';
                } else {
                    return tree[node] = '0';
                }
            } else {
                char c1 = init(node * 2, start, (start + end) / 2);
                char c2 = init(node * 2 + 1, (start + end) / 2 + 1, end);

                if (c1 == '0' || c2 == '0') {
                    return tree[node] = '0';
                } else if (c1 == '+' && c2 == '+') {
                    return tree[node] = '+';
                } else if (c1 == '-' && c2 == '-') {
                    return tree[node] = '+';
                } else {
                    return tree[node] = '-';
                }
            }
        }

        // 값 변경 함수
        char update(int node, int start, int end, int index, long diff) {
            if (index < start || index > end) {
                return tree[node];
            } else {
                if (start == end) {
                    if (diff > 0) {
                        return tree[node] = '+';
                    } else if (diff < 0) {
                        return tree[node] = '-';
                    } else {
                        return tree[node] = '0';
                    }
                } else {
                    char c1 = update(node * 2, start, (start + end) / 2, index, diff);
                    char c2 = update(node * 2 + 1, (start + end) / 2 + 1, end, index, diff);

                    if (c1 == '0' || c2 == '0') {
                        return tree[node] = '0';
                    } else if (c1 == '+' && c2 == '+') {
                        return tree[node] = '+';
                    } else if (c1 == '-' && c2 == '-') {
                        return tree[node] = '+';
                    } else {
                        return tree[node] = '-';
                    }
                }
            }
        }

        // 곱 계산 함수
        void product(int node, int start, int end, int left, int right) {
            if (left > end || right < start) {
                return;
            } else if (left <= start && right >= end) {
                if (tree[node] == '0' || result == '0') {
                    result = '0';
                } else if (tree[node] == '-' && result == '-') {
                    result = '+';
                } else if (tree[node] == '-' && result == '+') {
                    result = '-';
                } else if (tree[node] != result) {
                    result = '-';
                } else {
                    result = '+';
                }
            } else {
                product(node * 2, start, (start + end) / 2, left, right);
                product(node * 2 + 1, (start + end) / 2 + 1, end, left, right);
            }
        }
    }
}
