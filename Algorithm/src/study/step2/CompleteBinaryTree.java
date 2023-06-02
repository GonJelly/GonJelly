package study.step2;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class CompleteBinaryTree {

    private static char[] nodes;
    private static int lastIndex;
    private final int SIZE;

    public CompleteBinaryTree(int size) {
        SIZE = size;
        nodes = new char[SIZE + 1];
    }

    public boolean add(char e) {

        if( lastIndex == SIZE ) {
            return false;
        }

        nodes[++lastIndex] = e;
        return true;
    }

    public void bfs() {

        Queue<Integer> que = new LinkedList<>();
        que.offer(1);

        // 큐에 저장된 노드가 없으면 종료 ( 방문할 대상이 있을때 반복 )
        while( !que.isEmpty() ) {
            // 방문대상 정보를 가져오기
            int current  = que.poll();
            System.out.print(nodes[current] + " ");

            // 현재노드의 자식 노드를 저장한다.
            if( current * 2 <= lastIndex ) que.offer(current * 2 );
            if( (current * 2) + 1 <= lastIndex ) que.offer((current * 2) + 1);
        }
        System.out.println();
    }

    public void dfs() {

        Stack<Integer> stack = new Stack<>();
        stack.push(1);

        // 스택에 저장된 노드가 없으면 종료 ( 방문할 대상이 있을때 반복 )
        while( !stack.isEmpty() ) {
            // 방문대상 정보를 가져오기
            int current  = stack.pop();
            System.out.print(nodes[current] + " ");

            // 현재노드의 자식 노드를 저장한다.
            if( current * 2 <= lastIndex ) stack.push(current * 2 );
            if( (current * 2) + 1 <= lastIndex ) stack.push((current * 2) + 1);
        }
        System.out.println();
    }


    public void dfsByPreOrder(int current) {
        System.out.print(nodes[current] + " ");
        if( current * 2 <= lastIndex ) dfsByPreOrder( current * 2 );
        if( ( current * 2 + 1) <= lastIndex ) dfsByPreOrder( (current * 2) + 1);
    }

    public void dfsByInOrder(int current) {
        if( current > lastIndex ) return;

        dfsByInOrder(current * 2);
        System.out.print(nodes[current] + " ");
        dfsByInOrder((current * 2) + 1);
    }

    public void dfsByPostOrder(int current) {
        if( current * 2 <= lastIndex ) dfsByPreOrder( current * 2 );
        if( ( current * 2 + 1) <= lastIndex ) dfsByPreOrder( (current * 2) + 1);
        System.out.print(nodes[current] + " ");
    }


}
