create table client(
                       id integer,
                       name varchar2,
                       phone_number varchar2
);

create table employee(
                         id integer,
                         name varchar2
);

create table service_type(
                             id integer,
                             name integer
);

create table booking_info
(
    id integer,
    date_time datetime,
    client_id integer,
    foreign key (client_id) references client(id)
);

create table schedule(
    employee_id integer,
    date_time datetime,
    booking_id integer default null,
    service_type_id integer,
    foreign key (employee_id) references employee(id),
    foreign key (booking_id) references booking_info(id),
    foreign key (service_type_id) references service_type(id)
);