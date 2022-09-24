package jungol;

public class test {

    public static void main(String[] args) {
        StringBuilder sql = new StringBuilder();
        sql.append("insert into apt(code,lawd_cd,deal_ymd,deal_amount,regcode,eubmyudong,dong,aptName,areaExUse,jibun,floor,build_Year,read_Name,bonbun,bubun) \n");
        sql.append("value(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?");
        sql.toString().replaceAll("\n"," ");
        System.out.println(sql);
    }
}
