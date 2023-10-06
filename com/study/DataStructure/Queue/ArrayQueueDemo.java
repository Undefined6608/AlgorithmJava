package com.study.DataStructure.Queue;

import java.util.Scanner;

public class ArrayQueueDemo {
    public static void main(String[] args) {
        // 测试
        System.out.println("测试数组模拟队列的案例~~~~");
        // 创建一个队列
        ArrayQueue queue = new ArrayQueue(3);
        char key = ' ';// 接收用户输入
        // 实例化扫描器
        Scanner scanner = new Scanner(System.in);
        // 定义死循环
        boolean loop = true;
        // 输出一个菜单
        while (loop) {
            System.out.println("s(show):显示队列");
            System.out.println("e(exit):退出程序");
            System.out.println("a(add):添加数据到队列");
            System.out.println("g(get):从队列中取出数据");
            System.out.println("h(head):查看队列头的数据");
            // 接收一个字符
            key = scanner.next().charAt(0);
            switch (key) {
                case 's':
                    // 查看队列
                    queue.showQueue();
                    break;
                case 'e':
                    // 关闭扫描器
                    scanner.close();
                    loop = false;
                    break;
                case 'a':
                    // 向队列中添加数据
                    System.out.println("请输入一个数字");
                    int value = scanner.nextInt();
                    queue.addQueue(value);
                    break;
                case 'g':
                    // 在队列中取出数据
                    try {
                        int res = queue.getQueue();
                        System.out.printf("取出的数据是：%d\n", res);
                    } catch (Exception e) {
                        System.err.println(e.getMessage());
                    }
                    break;
                case 'h':
                    // 查看队列头的数据
                    try {
                        int res = queue.headQueue();
                        System.out.printf("队列头部的数据是：%d\n", res);
                    } catch (Exception e) {
                        System.err.println(e.getMessage());
                    }
                    break;
            }
        }
        System.out.println("程序退出~~~~");

    }
}

// 使用数组模拟队列--编写一个 ArrayQueue 的类
class ArrayQueue {
    private final int maxSize; // 表示数组的最大容量
    private int front; // 模拟队列头指针
    private int rear; // 模拟队列尾指针
    private final int[] arr; // 该数组用于存放数据，模拟队列

    /**
     * 创建队列的构造器
     *
     * @param arrMaxSize 队列长度
     */
    public ArrayQueue(int arrMaxSize) {
        maxSize = arrMaxSize;
        arr = new int[maxSize];
        // 指向队列头部，分析出 front 是指向队列头的前一个位置
        front = -1;
        // 指向队列尾部，分析出 rear 是指向队列尾的数据（即就是队列最后一个数据）
        rear = -1;
    }

    /**
     * 判断队列是否已满
     *
     * @return 返回队列是否已满
     */
    public boolean isFull() {
        return rear == maxSize - 1;
    }

    /**
     * 判断队列是否为空
     *
     * @return 返回队列是否为空
     */
    public boolean isEmpty() {
        return rear == front;
    }
    /**
     * 向队列中添加数据
     *
     * @param data 添加的数据
     */
    public void addQueue(int data) {
        // 判断队列是否满
        if (isFull()) {
            System.out.println("队列满，无法加入数据~~~~");
            return;
        }
        // 让 rear 后移,并给模拟队列数组赋值
        arr[++rear] = data;
    }

    // 获取队列的数据，出队列
    public int getQueue() {
        // 判断队列是否为空
        if (isEmpty()) {
            // 通过抛出异常处理队列为空
            throw new RuntimeException("队列为空，不能取数据~~~~");
        }
        // 将 front 后移并取出模拟队列数组里的值
        return arr[++front];
    }

    // 显示队列的所有数据
    public void showQueue() {
        // 判断队列是否为空
        if (isEmpty()) {
            System.out.println("队列为空，暂无数据~~~~");
            return;
        }
        // 遍历队列
        for (int i = 0; i < arr.length; i++) {
            System.out.printf("arr[%d]=%d\n", i, arr[i]);
        }
    }

    /**
     * 显示队列的头数据，注意不是取数据
     *
     * @return 队列头数据
     */
    public int headQueue() {
        if (isEmpty()) {
            throw new RuntimeException("队列为空，暂无头数据~~~~");
        }
        return arr[front + 1];
    }
}
