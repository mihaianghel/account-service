package digital.and.accountservice.model.transfer;

import digital.and.accountservice.util.Status;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AccountDto {

    private Long accountId;
    private String owner;
    private int balance;
    private Status status;
}
