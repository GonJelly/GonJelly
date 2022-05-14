package IOExample.chapter1;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

public class InputStreamExample {

    /** InputStream / OutputStream
     *
     * 자바에서 데이터는 스트림(Stream)을 통해서 입출력되므로 스트림의 특징을 알아야한다.
     *
     * 스트림의 특징 (중요)
     * 1. 단일 방향으로 연속저으로 흘러가는 것
     *
     * 데이터의 입출력 API는 java.io 패키지에서 제공하고 있음
     *
     * 다양한 입출력 클래스를 제공!!
     * 1. InputStream / OutputStream : 바이트 단위 입출력 최상위 클래스
     * 2. Reader / Writer : 문자 단위 입출력 최상위 클래스 ( 2Byte씩 )
     * 3. File : 파일시스템의 파일/디렉토리 정보를 얻을 때 사용 ( 파일 내용을 생성/삭제 불가 )
     * 4. FileInputStream / FileOutputStream : 파일시스템에서 파일을 읽고/쓰기 할 때 사용
     * 5. FileReader / FileWriter : 그림/멀티미디어/오디오/비디오 등의 파일은 읽기/쓰기 할 수 없고 텍스트 파일만 가능
     *
     * (보조스트림)
     * 1. InputStreamReader / OutputStreamWriter : 문자 변환 보조스트림
     * 2. BufferInputStream / BufferReader : 성능향상 보조스트림 ( 입력 )
     * 3. BufferOutputStream / BufferWriter : 성능향상 보조스트림 ( 출력 )
     * 4. DataInputStream / DataOutputStream : 기본타입 변환 보조스트림
     * 5. ObjectInputStream / ObjectOutputStream : 객체 변환 보조스트림
     * 등등
     *
     * 이외~~
     * 1. Console : 콘솔로부터 문자를 입출력하기 위해 사용
     * 2. System.in / System.out
     * 3. Scanner : 내부적으로 문자 변환을 해주는 기능을 갖추고 있다.
     *
     * */
    public static void main(String[] args) throws IOException {

        InputStream is = new FileInputStream("C:/test1.txt");
        byte[] bt = new byte[8];
        int rd;

        while (  (rd = is.read(bt)) != -1) {

            for(byte result : bt) {
                char cr = (char) result;
                System.out.print(cr);
            }

        }

        // 종료
        is.close();
    }

}
