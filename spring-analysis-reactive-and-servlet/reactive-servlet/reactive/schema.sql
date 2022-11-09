drop table if exists user CASCADE;
create table user (
    id bigint NOT NULL AUTO_INCREMENT,
    name varchar(255),
    property double,
    primary key (id)
);

drop table if exists stock CASCADE;
create table stock (
    id bigint NOT NULL AUTO_INCREMENT,
    company_name varchar(255),
    price double,
    primary key (id)
);

drop table if exists stocktrade CASCADE;
create table stocktrade (
    id bigint NOT NULL AUTO_INCREMENT,
    time_stamp timestamp,
    company_name varchar(255),
    price decimal(19,2),
    trade_quantity int,
    trade_action varchar(255),
    user_id bigint,
    primary key (id)
);