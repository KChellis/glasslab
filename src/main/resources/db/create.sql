SET MODE PostgreSQL;

CREATE TABLE IF NOT EXISTS art (
  id int PRIMARY KEY auto_increment,
  name VARCHAR,
  type VARCHAR,
  materials VARCHAR,
  price INTEGER,
  images VARCHAR,
  description VARCHAR,
  keywords VARCHAR,
  style VARCHAR

);

CREATE TABLE IF NOT EXISTS orders (
  id int PRIMARY KEY auto_increment,
  unread BOOLEAN,
  sent BOOLEAN,
  firstName VARCHAR,
  lastName VARCHAR,
  street VARCHAR,
  city VARCHAR,
  state VARCHAR,
  zip INTEGER,
  email VARCHAR,
  phone VARCHAR,
  createdAt BIGINT

);

CREATE TABLE IF NOT EXISTS orders_art (
  id int PRIMARY KEY auto_increment,
  orderId INTEGER,
  artId INTEGER

);
