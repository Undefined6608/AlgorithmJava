package com.study.DataStructure.LinkedList;

public class SingleLinkedListDemo {
    public static void main(String[] args) {
        // 进行测试
        // 先创建几个节点
        HeroNode hero1 = new HeroNode(1, "宋江", "及时雨");
        HeroNode hero2 = new HeroNode(2, "卢俊义", "玉麒麟");
        HeroNode hero3 = new HeroNode(3, "吴用", "智多星");
        HeroNode hero4 = new HeroNode(4, "林冲", "豹子头");
        // 创建链表
        SingleLinkedList singleLinkedList = new SingleLinkedList();
        // 直接添加加入
        /*
        singleLinkedList.add(hero1);
        singleLinkedList.add(hero4);
        singleLinkedList.add(hero2);
        singleLinkedList.add(hero3);
        */

        // 按编号顺序加入
        singleLinkedList.addByOrder(hero1);
        singleLinkedList.addByOrder(hero4);
        singleLinkedList.addByOrder(hero2);
        singleLinkedList.addByOrder(hero3);
        singleLinkedList.addByOrder(hero3);
        // 显示
        singleLinkedList.list();
    }
}

// 定义 SingleLinkedList 管理我们的英雄
class SingleLinkedList {
    // 先初始化一个头节点，不存放具体数据，头节点不要动
    private final HeroNode head = new HeroNode(0, "", "");

    // 添加节点到单向链表
    // 第一种方式，在添加英雄时，直接添加到链表的末尾。
    // 思路：
    // 当不考虑编号的顺序时：
    // 1. 找到当前列表的左后节点
    // 2. 将最后节点的 next 域指向新的节点
    public void add(HeroNode newNode) {
        // 因为 head 节点不能动，因此我们需要一个辅助变量 temp
        HeroNode temp = head;
        // 遍历链表，找到最后
        while (temp.next != null) {
            // 当 temp 节点的 next 域为空时，找到链表的最后
            // 如果没有找到最后，则将 temp 后移
            temp = temp.next;
        }
        // 当退出 while 循环时，temp 指向链表的结尾
        // 将最后这个节点的 next 指向新的节点
        temp.next = newNode;
    }

    // 第二种方式，在添加英雄时，根据排名将英雄插入到指定的位置
    // （如果有这个排名，则添加失败，给出提示）
    public void addByOrder(HeroNode newNode) {
        // 因为头节点不能动，因此我们仍然通过一个辅助指针（变量）来帮助找到添加的位置
        // 因为为单链表，因此我们找的 temp 是位于添加位置的前一个节点，否则插入失败
        // 定义 temp
        HeroNode temp = head;
        // 添加的编号是否存在，默认为 false
        boolean flag = false;
        // 建立循环
        while (true) {
            // 判断 temp 是否在链表末尾
            if (temp.next == null) {
                break;
            }
            if (temp.next.no > newNode.no) {
                // 位置找到，就在 temp 的后面插入
                break;
            }
            if (temp.next.no == newNode.no) {
                // 说明编号已经存在
                flag = true;
                break;
            }
            // 后移，遍当前链表
            temp = temp.next;
        }
        // 判断 flag 值
        if (flag) {
            // 不能添加，说明编号存在
            System.err.printf("准备插入英雄的编号 %d 已存在，不能加入\n", newNode.no);
            return;
        }
        // 可以加入到链表中
        newNode.next = temp.next;
        temp.next = newNode;
    }

    /**
     * 显示链表【遍历】
     */
    public void list() {
        // 当头节点的 next 域指向 null 时，链表为空
        if (head.next == null) {
            System.err.println("链表为空~~~~");
            return;
        }
        // 因为头节点不能动，因此我们需要一个辅助变量来遍历
        HeroNode temp = head.next;
        while (temp != null) {
            // 判断到链表最后
            // 输出节点的信息
            System.out.println(temp.toString());
            // 将 temp 后移
            temp = temp.next;

        }
    }

}

/**
 * 定义一个 HeroNode，每个 HeroNode 对象都是一个节点
 */
class HeroNode {
    // 英雄编号
    public int no;
    // 英雄名称
    public String name;
    // 英雄昵称
    public String nickName;
    // 指向下一个节点
    public HeroNode next;

    /**
     * 构造器
     *
     * @param no       英雄编号
     * @param name     英雄名称
     * @param nickName 英雄昵称
     */
    public HeroNode(int no, String name, String nickName) {
        this.no = no;
        this.name = name;
        this.nickName = nickName;
    }

    // 为了显示方便，重写 toString
    @Override
    public String toString() {
        return "HeroNode{" +
                "no=" + no +
                ", name='" + name + '\'' +
                ", nickName='" + nickName + '\'' +
                '}';
    }
}
