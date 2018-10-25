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

#### Get account state
```
GET http://localhost:8080/accounts/{id}
```

#### Send events
```
curl -d '{ "type":"${event_type}", "owner" : "${owner_name}", "accountId" : ${account_id}":, "amount": "${amount}"}' -H "Content-Type: application/json" -X POST http://localhost:8080/accounts/events
```
for example:
```
curl -d '{ "type":"WITHDRAWAL_PERFORMED", "owner" : "John", "accountId": "71799074", "amount": "70"}' -H "Content-Type: application/json" -X POST http://localhost:8080/accounts/events
```

##### Event types: 
* BANK_ACCOUNT_CREATED
* DEPOSIT_PERFORMED
* WITHDRAWAL_PERFORMED
* OWNER_CHANGED


