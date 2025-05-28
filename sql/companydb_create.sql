create database companydb;
use companydb;

create table employee (
    ssn varchar(9),
    name varchar(20) not null,
    phone_number char(10) not null,
    date_of_birth date not null,
    address varchar(50) not null,
    sex char(1) not null,
    salary bigint not null,
    mgr_ssn varchar(9),

    constraint pk_employee primary key (ssn)
);

create table project (
    project_id int default 1,
    project_name varchar(20),

    constraint pk_project primary key (project_id)
);  

create table works_on (
    emp_ssn varchar(9) unique,
    project_id int,
    hours_spent int default 0,

    constraint pk_works_on primary key (emp_ssn, project_id)
);

create table dependent (
    emp_ssn varchar(9),
    name varchar(20),
    phone_number char(10),
    sex char(1),
    relationship varchar(20),

    constraint pk_dependent primary key (emp_ssn, name)
);