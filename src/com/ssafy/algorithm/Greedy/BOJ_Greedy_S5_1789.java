package com.ssafy.algorithm.Greedy;

import java.io.*;
import java.util.StringTokenizer;

/*
 * @author 0at_x
 * @since 2023. 01. 16.
 * @see https://www.acmicpc.net/problem/1789
 * @category #Greedy
 */

public class BOJ_Greedy_S5_1789 {

    static long S, sum;
    static int result;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        S = Integer.parseInt(br.readLine());

        sum = result = 0;

        for(int s = 1; ; s++) {
            if(sum > S)
                break;
            sum += s;
            result++;
        }

        bw.write(result - 1 + "\n");

        br.close();
        bw.flush();
        bw.close();

    }


}
