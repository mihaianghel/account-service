package digital.and.accountservice.model.transfer;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AccountDto {

    private Long accountId;
    private String owner;
    private Integer balance;
}
