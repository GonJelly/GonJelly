package basicAPIExample;

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
}