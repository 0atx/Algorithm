package com.ssafy.algorithm.Stack;

import java.io.*;
import java.util.Stack;

/*
 * @author 0at_x
 * @since 2022. 07. 14.
 * @see https://www.acmicpc.net/problem/1725
 * @category #Stack
 */

public class BOJ_Stack_P5_1725 {

    static int N;
    static int[] arr;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        N = Integer.parseInt(br.readLine());
        arr = new int[N];

        for (int n = 0; n < N; n++) {
            arr[n] = Integer.parseInt(br.readLine());
        }

        bw.write(getArea() + "\n");

        br.close();
        bw.flush();
        bw.close();

    }

    static class Histogram {
        long index;
        long height;

        Histogram(long index, long height) {
            this.index = index;
            this.height = height;
        }

    }

    static long getArea() {
        Stack<Histogram> stack = new Stack<>();
        long area = 0;

        for (int n = 0; n < N; n++) {
            while (!stack.isEmpty() && stack.peek().height > arr[n]) {
                long height = stack.pop().height;
                long weight = n;

                if (!stack.isEmpty()) {
                    weight -= stack.peek().index + 1;
                }

                area = Math.max(area, height * weight);
            }
            stack.push(new Histogram(n, arr[n]));
        }

        while (!stack.isEmpty()) {
            long height = stack.pop().height;
            long weight = N;

            if (!stack.isEmpty()) {
                weight -= stack.peek().index + 1;
            }

            area = Math.max(area, height * weight);
        }

        return area;
    }
}
