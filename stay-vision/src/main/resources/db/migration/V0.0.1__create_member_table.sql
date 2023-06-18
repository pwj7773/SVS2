CREATE TABLE IF NOT EXISTS MEMBER (
    ID              BIGINT              AUTO_INCREMENT	PRIMARY KEY,
	USER_ID	        VARCHAR(30)		    NOT NULL UNIQUE,
	USER_PW	        VARCHAR(255)		NOT NULL,
	USER_NAME	    VARCHAR(20)		    NOT NULL,
	EMAIL	        VARCHAR(100)	    NOT NULL,
	PHONE           VARCHAR(30)	        NOT NULL,
    ROLENAME	    VARCHAR(20),
    status          VARCHAR(30)                     
);