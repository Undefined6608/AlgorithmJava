package com.study.DataStructure.SparseArray;

import java.io.*;

public class SparseArray {
    public static void main(String[] args) throws IOException {
        // 创建一个原始的二维数组 11 * 11
        // 0：没有棋子，1：黑子，2：蓝子
        int[][] chessArr1 = new int[11][11];
        chessArr1[1][2] = 1;
        chessArr1[2][3] = 2;
        chessArr1[4][5] = 2;
        // 输出原始的二维数组
        System.out.println("原始的二维数组~~~~");
        for (int[] row : chessArr1) {
            for (int data : row) {
                System.out.printf("%d\t", data);
            }
            System.out.println();
        }

        // 将二维数组转化为稀疏数组
        // 1.先遍历二维数组，得到非 0 数据的个数
        int sum = 0;
        for (int[] ints : chessArr1) {
            for (int anInt : ints) {
                if (anInt != 0) {
                    sum++;
                }
            }
        }

        // 2. 创建对应的稀疏数组
        int[][] sparseArr = new int[sum + 1][3];
        // 3.给稀疏数组赋值
        sparseArr[0][0] = chessArr1.length;
        sparseArr[0][1] = chessArr1[0].length;
        sparseArr[0][2] = sum;
        // 遍历二维数组，将非 0 的值存放到 sparseArr 中
        // 定义计数器，用于记录是第几个有效数据
        int count = 0;
        for (int i = 0; i < chessArr1.length; i++) {
            for (int j = 0; j < chessArr1[i].length; j++) {
                if (chessArr1[i][j] != 0) {
                    count++;
                    sparseArr[count][0] = i;
                    sparseArr[count][1] = j;
                    sparseArr[count][2] = chessArr1[i][j];
                }
            }
        }
        System.out.println();
        System.out.println("得到的稀疏数组为~~~~");
        // 输出稀疏数组,并写入文件
        File file = new File("./map.data");
        // 创建写入流
        FileWriter out = new FileWriter(file);
        // 循环稀疏数组写入文件
        for (int[] row : sparseArr) {
            out.write(row[0] + "\t" + row[1] + "\t" + row[2] + "\r\n");
            System.out.printf("%d\t%d\t%d\n", row[0], row[1], row[2]);
        }
        // 关闭写入流
        out.close();
        // 开启读取流
        BufferedReader in = new BufferedReader(new FileReader(file));
        // 一行数据
        String line;
        // 定义当前行数
        int rowIn = 0;
        // 逐行读取，并将每个数组放入到数组中
        while ((line = in.readLine()) != null) {
            // 通过 \t 进行截取
            String[] temp = line.split("\t");
            // 循环写入稀疏数组
            for (int j = 0; j < temp.length; j++) {
                sparseArr[rowIn][j] = Integer.parseInt(temp[j]);
            }
            // 添加当前行数
            rowIn++;
        }
        // 关闭读取流
        in.close();

        // 将稀疏数组 --》 恢复为原始的二维数组
        // 1. 先读取稀疏数组的第一行，根据这一行进行创建二维数组
        int[][] chessArr2 = new int[sparseArr[0][0]][sparseArr[0][1]];
        // 2. 再读取稀疏数组后几行的数据（从第二行开始），并赋给原始的二维数组
        for (int i = 1; i < sparseArr.length; i++) {
            chessArr2[sparseArr[i][0]][sparseArr[i][1]] = sparseArr[i][2];
        }
        // 恢复后的二维数组
        System.out.println();
        System.out.println("恢复后的二维数组~~~~");
        for (int[] row : chessArr2) {
            for (int data : row) {
                System.out.printf("%d\t", data);
            }
            System.out.println();
        }
    }
}
