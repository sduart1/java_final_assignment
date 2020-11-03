
/* delete tables if they exist already - ensuring a clean db*/
DROP TABLE IF EXISTS stocks.quotes CASCADE;


/* creates a table to store a list of hobbies and their recommended ages */
create table stocks.quotes(
	id INT NOT NULL AUTO_INCREMENT,
	symbol VARCHAR(4) NOT NULL,
	time DATETIME NOT NULL,
	price DECIMAL NOT NULL,
	PRIMARY KEY ( id )
);


/** now add some sample data */

INSERT INTO stocks.quotes (symbol,time,price) VALUES ('GOOG','2004-08-19 00:00:01','85.00');
INSERT INTO stocks.quotes (symbol,time,price) VALUES ('GOOG','2015-02-03 00:00:01','527.35');
INSERT INTO stocks.quotes (symbol,time,price) VALUES ('APPL','2000-01-01 00:00:01','118.27');
INSERT INTO stocks.quotes (symbol,time,price) VALUES ('APPL','2011-02-01 00:00:01','212.15');
INSERT INTO stocks.quotes (symbol,time,price) VALUES ('APPL','2005-02-09 00:00:01','185.68');
INSERT INTO stocks.quotes (symbol,time,price) VALUES ('AMZN','2019-11-01 00:00:01','356.99');
