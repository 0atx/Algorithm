import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

/*
 * @author 0at_x
 * @since 2022. 04. 10.
 * @see https://www.acmicpc.net/problem/21278
 * @category #Graph
 */

public class BOJ_Graph_G5_21278 {

	private static int N, M, result, rFirst, rSecond, INF = 9999999;
	private static int[][] chickens;
	private static StringTokenizer st;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		chickens = new int[N + 1][N + 1];
		for (int r = 1; r <= N; r++) {
			for (int c = 1; c <= N; c++) {
				if (r != c) {
					chickens[r][c] = INF;
				}
			}
		}

		for (int m = 0; m < M; m++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());

			chickens[a][b] = 1;
			chickens[b][a] = 1;
		}

		floydWarshall();

		result = Integer.MAX_VALUE;
		for (int i = 1; i <= N; i++) {
			for (int j = i + 1; j <= N; j++) {
				int sum = 0;
				for (int k = 1; k <= N; k++) {
					sum += Math.min(chickens[i][k], chickens[j][k]);
				}
				if (result > sum) {
					rFirst = i;
					rSecond = j;
					result = sum;
				}
			}
		}

		bw.write(rFirst + " " + rSecond + " " + result * 2 + "\n");

		br.close();
		bw.flush();
		bw.close();

	}

	private static void floydWarshall() {
		for (int k = 1; k <= N; ++k) {
			for (int i = 1; i <= N; ++i) {
				if (k == i) {
					continue;
				}
				for (int j = 1; j <= N; ++j) {
					if (i == j || k == j) {
						continue;
					}
					chickens[i][j] = Math.min(chickens[i][j], chickens[i][k] + chickens[k][j]);
				}
			}
		}
	}

}
