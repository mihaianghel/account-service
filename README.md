# Event sourcing demo app

### Build the app

```
gradle build
```

### Run the app

```
gradle bootRun
```

### Endpoints

#### Get account current state
```
curl http://localhost:8080/accounts/{account_id}
```

#### Get account state at a specific time and date
```
curl http://localhost:8080/accounts/{account_id}?t=dd.MM.yyyy-HH:mm:ss
```
Example:
```
curl http://localhost:8080/accounts/51041011?t=26.10.2018-07:39:00
```

#### Send events
```
curl -d '{event_specific_payload}' -H "Content-Type: application/json" -X POST http://localhost:8080/events
```

###### Event types and payloads: 
* BANK_ACCOUNT_CREATED
```
{
	"type":"BANK_ACCOUNT_CREATED",
	"owner" : "John Doe"
}
```
* DEPOSIT_PERFORMED
```
{
	"type":"DEPOSIT_PERFORMED",
	"accountId": "85542345",
	"amount": "40"
}
```
* WITHDRAWAL_PERFORMED
```
{
	"type":"WITHDRAWAL_PERFORMED",
	"accountId": "85542345",
	"amount": "40"
}
```
* OWNER_CHANGED
```
{
	"type":"OWNER_CHANGED",
	"accountId": "85542345",
	"owner": "Jane Doe"
}
```
* BANK_ACCOUNT_CLOSED
```
{
	"type":"BANK_ACCOUNT_CLOSED",
	"accountId": "85542345",
	"reason": "High interest rates"
}
```
#### Access the H2 database console
```
http://localhost:8080/h2-console
```
and make sure you have the following fields filled in:
 * Driver Class: org.h2.Driver
 * JDBC URL: jdbc:h2:mem:testdb
 * User name: sa
