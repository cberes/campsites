-- campground
create sequence seq_campground_id;
create table campground (
  id bigint primary key default nextval('seq_campground_id'),
  active boolean not null default '1',
  name varchar(100) not null,
  description varchar(1024) not null
);
alter sequence seq_campground_id owned by campground.id;

-- area
create sequence seq_area_id;
create table area (
  id bigint primary key default nextval('seq_area_id'),
  campgroud_id bigint not null references campground(id),
  active boolean not null default '1',
  name varchar(100) not null,
  description varchar(1024) not null
);
alter sequence seq_area_id owned by area.id;

-- campsite
create type site_type as enum ('CABIN', 'CAMPSITE', 'GROUP', 'RV_ONLY', 'TENT_ONLY');
create type site_access as enum ('UNKNOWN', 'DRIVE_IN', 'WALK_IN');
create type site_electric as enum ('NONE', 'UNKNOWN_AMP', 'THIRTY_AMP', 'FIFTY_AMP');
create type site_water as enum ('NO', 'YES');
create type site_sewer as enum ('NO', 'YES');
create sequence seq_campsite_id;
create table campsite (
  id bigint primary key default nextval('seq_campsite_id'),
  campgroud_id bigint not null references campground(id),
  area_id bigint not null references area(id),
  active boolean not null default '1',
  name varchar(100) not null,
  description varchar(256) not null,
  notes varchar(256),
  type site_type not null,
  access site_access not null,
  size integer not null default 0,
  max_occupancy integer not null default 0,
  max_vehicles integer not null default 0,
  pets_allowed integer not null default 0,
  electric  site_electric not null,
  water site_water not null,
  sewer site_sewer not null
);
alter sequence seq_campsite_id owned by campsite.id;

-- payment_processor
create sequence seq_payment_processor_id;
create table payment_processor (
  id integer primary key default nextval('seq_payment_processor_id'),
  active boolean not null default '1',
  name varchar(50) not null
);
alter sequence seq_payment_processor_id owned by payment_processor.id;

-- payment
create sequence seq_payment_id;
create table payment (
  id bigint primary key default nextval('seq_payment_id'),
  payment_processor_id integer not null references payment_processor(id),
  transaction_id varchar(50) not null,
  amount decimal(16,2) not null,
  tax decimal(16, 2) not null,
  fee decimal(16, 2) not null
);
alter sequence seq_payment_id owned by payment.id;
create unique index payment_idx ON payment (payment_processor_id, transaction_id);

-- customer
create sequence seq_customer_id;
create table customer (
  id bigint primary key default nextval('seq_customer_id'),
  active boolean not null default '1',
  first_name varchar(50) not null,
  last_name varchar(50) not null,
  email varchar(100) not null,
  phone varchar(20),
  data json(1024)
);
alter sequence seq_customer_id owned by customer.id;

-- reservation
create sequence seq_reservation_id;
create table reservation (
  id bigint primary key default nextval('seq_reservation_id'),
  campsite_id bigint not null references campsite(id),
  customer_id bigint not null references customer(id),
  payment_id bigint not null references payment(id),
  starting date not null,
  ending date not null
);
alter sequence seq_reservation_id owned by reservation.id;
