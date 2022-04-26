package com.ssafy.algorithm.MST.Kruskal;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Queue;
import java.util.StringTokenizer;

/*
 * @author 0at_x
 * @since 2022. 04. 26.
 * @see https://www.acmicpc.net/problem/1944
 * @category #MST
 */

public class BOJ_MST_G2_1944 {

	private static int N, M, pointCnt, edgeCnt, result;
	private static boolean findAll;
	private static char[][] maze;
	private static int[] dr = { -1, 1, 0, 0 };
	private static int[] dc = { 0, 0, -1, 1 };
	private static ArrayList<Point> keys = new ArrayList<>();
	private static ArrayList<Edge> graph = new ArrayList<>();
	private static int[] parents;
	private static boolean[][] visited;
	private static StringTokenizer st;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		maze = new char[N][N];

		for (int r = 0; r < N; r++) {
			String str = br.readLine();
			for (int c = 0; c < N; c++) {
				maze[r][c] = str.charAt(c);
				if (isSorK(maze[r][c])) {
					keys.add(new Point(r, c));
					pointCnt++;
				}
			}
		}

		for (int m = 0; m <= M; m++) {
			Point p = keys.get(m);
			bfs(p.r, p.c, m);
		}

		if (graph.size() != 0) {
			Collections.sort(graph);

			parents = new int[M + 1];
			for (int m = 0; m <= M; m++) {
				parents[m] = m;
			}

			for (int g = 0; g < graph.size(); g++) {
				Edge edge = graph.get(g);

				if (union(edge.from, edge.to)) {
					result += edge.dist;
					edgeCnt++;
				}

				if (edgeCnt == M) {
					findAll = true;
					break;
				}
			}

		}

		if (findAll) {
			bw.write(result + "\n");
		} else {
			bw.write("-1\n");
		}

		br.close();
		bw.flush();
		bw.close();

	}

	private static void bfs(int r, int c, int idx) {
		Queue<Point> q = new ArrayDeque<>();
		visited = new boolean[N][N];
		q.offer(new Point(r, c));
		visited[r][c] = true;
		int cnt = 0;
		int stop = pointCnt - 1;

		while (!q.isEmpty()) {
			int size = q.size();

			while (size-- > 0) {
				Point cur = q.poll();
				if (cnt != 0 && isSorK(maze[cur.r][cur.c])) {
					for (int m = 0; m <= M; m++) {
						if (keys.get(m).r == cur.r && keys.get(m).c == cur.c) {
							graph.add(new Edge(idx, m, cnt));
						}
					}
					stop--;
				}

				if (stop == 0) {
					return;
				}

				for (int d = 0; d < 4; d++) {
					int nr = cur.r + dr[d];
					int nc = cur.c + dc[d];

					if (isOut(nr, nc) || visited[nr][nc] || maze[nr][nc] == '1') {
						continue;
					}

					visited[nr][nc] = true;
					q.offer(new Point(nr, nc));
				}
			}

			cnt++;
		}
	}

	private static boolean isOut(int nr, int nc) {
		return nr < 0 || nr >= N || nc < 0 || nc >= N;
	}

	private static boolean isSorK(char c) {
		return c == 'S' || c == 'K';
	}

	private static int find(int a) {
		if (parents[a] == a) {
			return a;
		}
		return parents[a] = find(parents[a]);
	}

	private static boolean union(int a, int b) {
		a = find(a);
		b = find(b);

		if (a != b) {
			parents[b] = a;
			return true;
		} else {
			return false;
		}
	}

	private static class Edge implements Comparable<Edge> {
		int from, to, dist;

		private Edge(int from, int to, int cost) {
			super();
			this.from = from;
			this.to = to;
			this.dist = cost;
		}

		@Override
		public int compareTo(Edge o) {
			return Integer.compare(this.dist, o.dist);
		}
	}

	private static class Point {
		int r;
		int c;

		public Point(int r, int c) {
			this.r = r;
			this.c = c;
		}
	}

}
