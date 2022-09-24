package book2.CollectionExample.chapter6;

import java.util.TreeSet;

public class TreeSetExample1 {

    /** 컬렉션에서 검색 기능을 강화시킨 TreeSet 과 TreeMap을 제공
     * [필수 지식]
     * Java에서 제공하는 이진트리 구조를 이용해서 계층적 구조(Tree구조)를 가지면서 객체를 저장함
     *
     * 이진트리 구조란?
     * 여러 개의 노드(node)가 트리 형태로 연결된 구조로
     * 최상단 노드를 루트노드(root node)라고 부르며,
     * 노드가 최대 2개의 노드로 뻗어가는 구조를 가지면,
     * 위아래로 부모-자식관게에 있다고 하며 위의 노드를 부모노드, 아래노드를 자식노드 라고한다.
     *
     * 객체를 저장할 때 첫번째로 저장되는 객체는 루트노드로 저장되고
     * 두번째부터 루트노드와 비교하면서 작으면 왼쪽 아래에 저장되고 높으면 오른쪽 아래로 저장되는 방식이다.
     * 그렇게 저장되면 왼쪽에는 작은 객체가 오른쪽은 높은 객체가 저장되면서 정렬이 되는 것이다.
     *
     * */

    public static void main(String[] args) {

        // TreeSet을 이용한 학점 찾는 방법
        TreeSet<Integer> binarySet = new TreeSet<Integer>();

        binarySet.add(85);
        binarySet.add(52);
        binarySet.add(92);
        binarySet.add(97);
        binarySet.add(77);
        binarySet.add(88);

        int scorces = binarySet.first();
        System.out.println("가장 낮은 점수 : " + scorces);

        scorces = binarySet.last();
        System.out.println("가장 높은 점수 : " + scorces);

        scorces = binarySet.lower(90);
        System.out.println("90점보다 낮은 점수 : " + scorces);

        scorces = binarySet.higher(90);
        System.out.println("90점보다 높은 점수 : " + scorces);

        // 객체를 가져오면 TreeSet 안에 객체를 삭제하는 메소드 pollFirst(), pollLast()
        while(!binarySet.isEmpty()){

            scorces = binarySet.pollFirst();
            System.out.println("남은 객체 수 : " + binarySet.size());

        }

    }

}
