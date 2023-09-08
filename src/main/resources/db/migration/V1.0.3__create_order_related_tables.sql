create table
  "order_items" (
    "order_id" serial primary key,
    "user_id" int not null,
    "order_date" timestamptz not null,
    "sub_total" decimal not null,
    constraint "fk_users" foreign key ("user_id") references "users" ("user_id") on delete restrict
  );

create table
  "orders_details" (
    "order_detail_id" serial not null primary key,
    "order_id" int not null,
    "product_id" int not null,
    "quantity" int not null,
    "order_price" decimal not null,
    constraint "fk_order_items" foreign key ("order_id") references "order_items" ("order_id") on delete restrict
  );