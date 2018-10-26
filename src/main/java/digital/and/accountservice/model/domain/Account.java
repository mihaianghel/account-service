package digital.and.accountservice.model.domain;

import digital.and.accountservice.util.Status;
import lombok.Getter;

@Getter
public class Account {

    private Long accountId;
    private String owner;
    private int balance;
    private Status status = Status.OPEN;

    private Account() {
    }

    public static Account createAccount() {
        return new Account();
    }

    public void closeAccount() {
        this.status = Status.CLOSED;
    }

    public void performDeposit(int amount) {
        this.balance = this.balance + amount;
    }

    public void performWithdrawal(int amount) {
        this.balance = (amount >= this.balance) ? 0 : this.balance - amount;
    }

    public void setAccountId(Long accountId) {
        this.accountId = accountId;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }
}
