package book1.BasicAPIExample.Object.equalsExample;

public class Member{

    private String id;

    public Member(String id){
        this.id = id;
    }

    public boolean equals(Object obj) {
        if(obj instanceof Member){
            Member member = (Member) obj;

            if(id.equals(member.id)){
                return true;
            }
        }
        return false;
    }


    /** 문자열이 같으면 hashCode()는 같은 값을 리턴한다. */
    @Override
    public int hashCode() {
        return id.hashCode();
    }

}