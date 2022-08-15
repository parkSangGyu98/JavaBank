CREATE TABLE Customer(
	id				VARCHAR(20)		PRIMARY KEY,
	passwd			VARCHAR(20)		NOT NULL,
	name			VARCHAR(20)		NOT NULL,
	ssn				VARCHAR(20)		NOT NULL, 
	phone			VARCHAR(20)		NOT NULL 
);

SELECT * FROM Customer;

CREATE TABLE Account(
	aid				BIGINT			PRIMARY KEY AUTO_INCREMENT,
	customerId		VARCHAR(20)		NOT NULL,
	accountNum		VARCHAR(20)		NOT NULL,   
	accType			VARCHAR(1)		NOT NULL DEFAULT 'S',
	balance			DOUBLE			NOT NULL DEFAULT 0,
	interestRate	DOUBLE			NOT NULL DEFAULT 0.0,
	overAmount		DOUBLE			NOT NULL DEFAULT 0.0,
	regDate         TIMESTAMP       NOT NULL DEFAULT CURRENT_TIMESTAMP,
	CONSTRAINT Account_customerId_FK
		FOREIGN KEY(customerId) REFERENCES Customer(id)
)AUTO_INCREMENT = 1;

SELECT * FROM Account;

UPDATE Account SET balance = 1000000 WHERE customerId = 'lty';

DROP TABLE Customer;
DROP TABLE Account;

UPDATE Account A INNER JOIN Customer C ON A.customerId = C.id 
SET A.balance = 1000000 WHERE A.accountNum = '539-11-1187' AND C.passwd = '1234';