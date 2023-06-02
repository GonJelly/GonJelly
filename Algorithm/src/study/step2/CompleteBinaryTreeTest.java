package study.step2;

import java.util.Scanner;

public class CompleteBinaryTreeTest {

    public static void main(String[] args) {

        CompleteBinaryTree tree = new CompleteBinaryTree(9);

        for ( int x = 0; x < 9; x++) {
            tree.add((char) ('A' + x));
        }

        tree.bfs();
        tree.dfs();

        System.out.println("============ dfs ============");
        tree.dfsByPreOrder(1);
        System.out.println();
        tree.dfsByInOrder(1);
        System.out.println();
        tree.dfsByPostOrder(1);
    }
}
