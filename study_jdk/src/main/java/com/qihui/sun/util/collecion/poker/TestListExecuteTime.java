package com.qihui.sun.util.collecion.poker;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class TestListExecuteTime {
	public static final int N = 50000;
	public static final int M = 40000;

	static void getTime(List<Integer> list) {
		insertByPosition(list);
		readByPosition(list);
		updateByPosition(list);
		deleteByPosition(list);
	}

	// 向list的指定位置插入N个元素，并统计时间
	private static void insertByPosition(List<Integer> list) {
		long startTime = System.currentTimeMillis();
		for (int i = 0; i < N; i++)
			list.add(0, i);
		long endTime = System.currentTimeMillis();
		long interval = endTime - startTime;
		System.out.println(getListName(list) + "插入" + N + "条数据耗时：" + interval + " ms");
	}

	// 从list中读取元素，并统计时间
	private static void readByPosition(List<Integer> list) {
		long startTime = System.currentTimeMillis();
		for (int i = 0; i < N; i++) {
			list.get(i);
		}
		long endTime = System.currentTimeMillis();
		long interval = endTime - startTime;
		System.out.println(getListName(list) + "查询" + N + "条数据耗时：" + interval + "ms");
	}

	// 从list的随机位置修改元素，并统计时间
	private static void updateByPosition(List<Integer> list) {
		long startTime = System.currentTimeMillis();
		for (int i = 0; i < M; i++) {
			int j = (int) (1 + Math.random() * (40000 - 1 + 1));
			list.set(j, 90000);
		}
		long endTime = System.currentTimeMillis();
		long interval = endTime - startTime;
		System.out.println(getListName(list) + "随机修改" + M + "条数据耗时" + interval + " ms");
	}

	// 从list的指定位置删除N个元素，并统计时间
	private static void deleteByPosition(List<Integer> list) {
		long startTime = System.currentTimeMillis();
		// 删除list第一个位置元素
		for (int i = 0; i < N; i++)
			list.remove(0);
		long endTime = System.currentTimeMillis();
		long interval = endTime - startTime;
		System.out.println(getListName(list) + "删除" + N + "条数据耗时" + interval + " ms");
	}

	// 获取list类型名称
	private static String getListName(List<Integer> list) {
		if (list instanceof LinkedList) {
			return "LinkedList";
		} else if (list instanceof ArrayList) {
			return "ArrayList";
		} else {
			return "error";
		}
	}

	public static void main(String[] args) {
		getTime(new ArrayList<Integer>());
		System.out.println("-------------------");
		getTime(new LinkedList<Integer>());
	}
}
