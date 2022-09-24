package book1.ExceptionExample.CreateException;

public class Account {

    private long balance; // 통장 잔고

    public Account() { }

    // 통장잔고가 얼마 남았는지 확인하기 위해서 getter를 이용
    public long getBalance() {
        return balance;
    }

    // 계정(통장)에 입금
    public void deposite(int money){
        this.balance += money;
    }

    // 계정(통장)에서 출금
    public void withdraw(int money) throws BalanceInsufficientException{

        if(balance < money){
            throw new BalanceInsufficientException("잔고 부족:" + (money - balance) + " 모자람");
        }

        balance -= money;

    }
}