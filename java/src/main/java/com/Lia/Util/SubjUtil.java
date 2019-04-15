package com.Lia.Util;

public class SubjUtil {
	public static Object[] getSubj(String table) {
		Object[] subj = null;
		if(table=="大一上") {
			Object[] subjects = {"计算机学科导论","认知实习","高等数学B（上）","高级语言程序设计","高级语言程序设计实践","线性代数"};
			subj = subjects;
		}
		if(table=="大一下") {
			Object[] subjects = {"大学物理A(上)","大学物理实验A（上）","高等数学B（下）","基础电路与电子学","电子线路综合实验","面向对象程序设计"};
			subj = subjects;
		}
		if(table=="大二上") {
			Object[] subjects = {"大学物理A(下)	","大学物理实验A（下）","离散数学","	数字电路与逻辑设计","数字逻辑电路设计实验","	算法与数据结构"}	;
			subj = subjects;
		}
		if(table=="大二下") {
			Object[] subjects = {"计算机组成原理A","计算机组成原理实践","计算机网络","概率论与数理统计","汇编语言程序设计","马克思主义基本原理"};
			subj = subjects;
		}
		return subj;
	}
}
