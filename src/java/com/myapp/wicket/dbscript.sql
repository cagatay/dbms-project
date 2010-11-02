CREATE DATABASE blg361;
USE blg361;

CREATE TABLE members (
  id INTEGER NOT NULL AUTO_INCREMENT,
  email VARCHAR(40),
  pass VARCHAR(10),
  PRIMARY KEY (id)
);

CREATE TABLE products (
  id INTEGER NOT NULL AUTO_INCREMENT,
  brand VARCHAR(40),
  model VARCHAR(40),
  category VARCHAR(40),
  image VARCHAR(20),
  votes INTEGER DEFAULT 0,
  total INTEGER DEFAULT 0,
  PRIMARY KEY (id)
);

CREATE TABLE categories (
  id INTEGER NOT NULL AUTO_INCREMENT,
  name VARCHAR(40),
  parent VARCHAR(40) DEFAULT 'none',
  PRIMARY KEY (id)
);

CREATE TABLE comments (
  id INTEGER NOT NULL AUTO_INCREMENT,
  com TEXT,
  productid INTEGER,
  userid INTEGER,
  tm TIMESTAMP,
  PRIMARY KEY (id)
);

INSERT INTO members (email, pass) VALUES ('admin@admin', 'admin');
INSERT INTO categories (name, parent) VALUES ('computer', 'none');
INSERT INTO categories (name, parent) VALUES ('notebook', 'computer');
INSERT INTO categories (name, parent) VALUES ('netbook', 'computer');
INSERT INTO categories (name, parent) VALUES ('music', 'none');
INSERT INTO categories (name, parent) VALUES ('players', 'music');
INSERT INTO categories (name, parent) VALUES ('instruments', 'music');
INSERT INTO categories (name, parent) VALUES ('records', 'music');
INSERT INTO categories (name, parent) VALUES ('watches', 'none');
INSERT INTO categories (name, parent) VALUES ('wrist watches', 'watches');
INSERT INTO categories (name, parent) VALUES ('sports', 'none');
INSERT INTO categories (name, parent) VALUES ('sportswear', 'sports');
INSERT INTO categories (name, parent) VALUES ('balls', 'sports');
INSERT INTO categories (name, parent) VALUES ('cell phones', 'none');
INSERT INTO categories (name, parent) VALUES ('electronics', 'none');
INSERT INTO categories (name, parent) VALUES ('tv', 'electronics');
INSERT INTO categories (name, parent) VALUES ('camera', 'electronics');
INSERT INTO categories (name, parent) VALUES ('stationery', 'none');
INSERT INTO categories (name, parent) VALUES ('pens', 'stationery');
INSERT INTO categories (name, parent) VALUES ('shoe', 'none');

INSERT INTO products (brand, model, category, image)
 VALUES ('yamaha', 'piano', 'instruments', 'yamaha.jpg');
INSERT INTO products (brand, model, category, image)
 VALUES ('sony', 'vaio p', 'netbook', 'vaiop.jpg');
INSERT INTO products (brand, model, category, image)
 VALUES ('fender', 'telecaster', 'instruments', 'telecaster.jpg');
INSERT INTO products (brand, model, category, image)
 VALUES ('spalding', 'basketball', 'balls', 'spalding.jpg');
INSERT INTO products (brand, model, category, image)
 VALUES ('samsung', 'lcd tv', 'tv', 'samsungtv.jpg');
INSERT INTO products (brand, model, category, image)
 VALUES ('rotring', 'pen', 'pens', 'rotring.jpg');
INSERT INTO products (brand, model, category, image)
 VALUES ('rolex', 'damn expensive', 'wrist watches', 'rolex.jpg');
INSERT INTO products (brand, model, category, image)
 VALUES ('radiohead', 'ok computer', 'records', 'okcomputer.jpg');
INSERT INTO products (brand, model, category, image)
 VALUES ('nokia', 'n97', 'cell phones', 'nokian97.jpg');
INSERT INTO products (brand, model, category, image)
 VALUES ('apple', 'macbook air', 'notebook', 'macbookair.jpg');
INSERT INTO products (brand, model, category, image)
 VALUES ('korg', 'keyboard', 'instruments', 'korg.jpg');
INSERT INTO products (brand, model, category, image)
 VALUES ('miles davis', 'kind of blue', 'records', 'kindofblue.jpg');
INSERT INTO products (brand, model, category, image)
 VALUES ('apple', 'ipod', 'players', 'ipod.jpg');
INSERT INTO products (brand, model, category, image)
 VALUES ('apple', 'iphone', 'cell phones', 'iphone.jpg');
INSERT INTO products (brand, model, category, image)
 VALUES ('sony', 'cybershot', 'camera', 'cybershot.jpg');
INSERT INTO products (brand, model, category, image)
 VALUES ('converse', 'blue', 'shoe', 'converse.jpg');
INSERT INTO products (brand, model, category, image)
 VALUES ('canon', 'cam', 'camera', 'canon.jpg');
INSERT INTO products (brand, model, category, image)
 VALUES ('beatles', 'sgt. peppers', 'records', 'beatles.jpg');
INSERT INTO products (brand, model, category, image)
 VALUES ('nike', 'barcelona', 'sportswear', 'barcelona.jpg');
INSERT INTO products (brand, model, category, image)
 VALUES ('acer', 'aspire one', 'netbook', 'aspireone.jpg');