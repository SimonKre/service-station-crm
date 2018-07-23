CREATE SCHEMA `car_workshop`
  DEFAULT CHARACTER SET utf8mb4;

USE car_workshop;

CREATE TABLE employee
(
  id       int PRIMARY KEY NOT NULL AUTO_INCREMENT,
  name     VARCHAR(255)    NOT NULL,
  surname  VARCHAR(255)    NOT NULL,
  adress   text            NOT NULL,
  phone    VARCHAR(255),
  note     text,
  man_hour double(8, 2)    NOT NULL
);

CREATE TABLE customer
(
  id        int PRIMARY KEY NOT NULL AUTO_INCREMENT,
  name      varchar(255)    NOT NULL,
  surname   varchar(255)    NOT NULL,
  birthdate DATE
);

CREATE TABLE vehicle
(
  id                  int PRIMARY KEY NOT NULL AUTO_INCREMENT,
  model               varchar(255)    NOT NULL,
  maker               VARCHAR(255)    NOT NULL,
  production_year     int             NOT NULL,
  registration_number VARCHAR(12)     NOT NULL,
  next_overhaul       DATE            NOT NULL,
  customer_id         int             NOT NULL,
  CONSTRAINT vehicle_customer_id_fk FOREIGN KEY (customer_id) REFERENCES customer (id)
    ON DELETE CASCADE
    ON UPDATE CASCADE
);

CREATE TABLE status
(
  id     int PRIMARY KEY NOT NULL AUTO_INCREMENT,
  status varchar(50)     NOT NULL
);
CREATE UNIQUE INDEX status_status_uindex
  ON status (status);

CREATE TABLE `order`
(
  id            int PRIMARY KEY NOT NULL AUTO_INCREMENT,
  planned_start DATE,
  start         DATE,
  employee_id   int,
  problem_desc  TEXT,
  repair_desc   TEXT,
  status_id     smallint                 DEFAULT 1,
  vehicle_id    int             NOT NULL,
  total_cost    DOUBLE,
  parts_cost    DOUBLE,
  hours_spent   FLOAT,
  CONSTRAINT order_employee_id_fk FOREIGN KEY (employee_id) REFERENCES employee (id)
    ON DELETE SET NULL,
  CONSTRAINT order_vehicle_id_fk FOREIGN KEY (vehicle_id) REFERENCES vehicle (id),
  CONSTRAINT order_status_id_fk FOREIGN KEY (status_id) REFERENCES status (id)
);

INSERT INTO `car_workshop`.`status` (`status`) VALUES ('Zlecenie przyjęte');
INSERT INTO `car_workshop`.`status` (`status`) VALUES ('Zatwierdzone koszty naprawy');
INSERT INTO `car_workshop`.`status` (`status`) VALUES ('W naprawie');
INSERT INTO `car_workshop`.`status` (`status`) VALUES ('Gotowy do odbioru');
INSERT INTO `car_workshop`.`status` (`status`) VALUES ('Rezygnacja');