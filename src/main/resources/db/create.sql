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
