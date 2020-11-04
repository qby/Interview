package com.qibenyu.algorithm;

import java.util.ArrayDeque;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

/**
 * 中序表达式转化成后缀表达式
 * <p>
 * <p>
 * （1+2）*（3-5）
 * <p>
 * <p>
 * 12+35-*
 */
public class PostfixExpression {

    public void postfix(String[] expression) {

        // 存储操作数的栈
        Stack<String> opStack = new Stack<String>();
        // 存储转换后的逆波兰式的队列
        Queue<String> reversePolish = new LinkedList<String>();

        for (String s : expression) {

            // 如果是数字
            if (isDigit(s)) {
                reversePolish.offer(s);
                // 如果是操作符
            } else if (isOperator(s)) {
                //是左括号直接入栈
                if ("(".equals(s)) {

                    opStack.push(s);
                    // 如果是右括号
                } else if (")".equals(s)) {

                    // 把离上一个“（”之间的操作符全部弹出来放入逆波兰式的队列中
                    while (!opStack.isEmpty()) {

                        String op = opStack.peek();
                        if (op.equals("(")) {
                            opStack.pop();
                            break;
                        } else {
                            reversePolish.offer(opStack.pop());
                        }
                    }
                } else {

                    while (!opStack.isEmpty()) {
                        // 如果栈顶元素为"("直接入栈
                        if ("(".equals(opStack.peek())) {
                            opStack.push(s);
                            break;
                            //如果栈顶元素优先级大于s
                        } else if (isGreat(opStack.peek(), s)) {
                            reversePolish.offer(opStack.pop());
                        } else if (isGreat(s, opStack.peek())) {
                            opStack.push(s);
                            break;
                        }
                    }
                    // 如果栈为空，直接入栈
                    if (opStack.isEmpty())
                        opStack.push(s);
                }
                // 将剩余的操作符入队
                while(!opStack.isEmpty()){
                    reversePolish.offer(opStack.pop());
                }
            }
        }
    }

    /**
     * 判断是否为运算符（暂时只判断四则运算的运算符）
     *
     * @param s
     * @return
     */
    static boolean isOperator(String s) {

        if ("(".equals(s) || ")".equals(s) || "+".equals(s) || "-".equals(s)
                || "*".equals(s) || "/".equals(s))

            return true;

        else

            return false;
    }

    /**
     * 判断是否为数字
     *
     * @param s
     * @return
     */
    static boolean isDigit(String s) {

        for (int i = 0; i < s.length(); i++) {

            if (!Character.isDigit(s.charAt(i)))

                return false;
        }

        return true;
    }


    /**
     * 判断op1和op2的优先级，如果op1>op2，返回true，如果op1<=op2，返回false
     *
     * @param op1
     * @param op2
     * @return
     */
    static boolean isGreat(String op1, String op2) {

        if (getPriority(op1) > getPriority(op2))

            return true;

        else

            return false;
    }

    /**
     * 获取运算符的优先级
     *
     * @param op
     * @return
     */
    static int getPriority(String op) {

        if ("+".equals(op) || "-".equals(op))

            return 1;

        else if ("*".equals(op) || "/".equals(op))

            return 2;

        else

            throw new IllegalArgumentException("Unsupported operator!");
    }


}
