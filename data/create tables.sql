/*==============================================================*/
/* DBMS name:      MySQL 5.0                                    */
/* Created on:     2017/4/25 15:32:42                           */
/*==============================================================*/



drop table if exists lt_cart;

drop table if exists lt_goods;

drop table if exists lt_order;

drop table if exists lt_order_item;

drop table if exists lt_order_log;

drop table if exists lt_shop;

drop table if exists lt_user;

drop table if exists lt_user_addr;

/*==============================================================*/
/* Table: lt_cart                                               */
/*==============================================================*/
create table lt_cart
(
   id                   bigint not null auto_increment,
   goods_id             bigint,
   num                  int not null default 1,
   user_id              bigint,
   add_time             bigint,
   primary key (id)
);

/*==============================================================*/
/* Index: index_gid_uid                                         */
/*==============================================================*/
create unique index index_gid_uid on lt_cart
(
   goods_id,
   user_id
);

/*==============================================================*/
/* Table: lt_goods                                              */
/*==============================================================*/
create table lt_goods
(
   id                   bigint not null auto_increment,
   shop_id              bigint,
   user_id              bigint,
   title                varchar(300),
   title_short          varchar(100),
   cid                  bigint,
   price_market         decimal(10,2),
   price                decimal(10,2),
   price_cost           decimal(10,2),
   freight              decimal(10,2) default 0 comment '0按重量整单计算',
   unit                 varchar(10),
   weight               double(5,2),
   status               int(1) comment '0新品，1上架，2，下架，3售罄',
   def_img              varchar(300),
   ablums               varchar(1000),
   note                 blob,
   buy_total            int default 0,
   add_time             bigint,
   primary key (id)
);

/*==============================================================*/
/* Table: lt_order                                              */
/*==============================================================*/
create table lt_order
(
   id                   bigint not null auto_increment,
   order_sn             varchar(50),
   order_status         int comment '0已取消,1新订单,2已支付,3已发货,4已收货,5已完成',
   pay_status           int comment '1未付款，2已付款，3已退款',
   deliver_status       int comment '1未发货，2已发货，3已退货',
   user_id              bigint,
   amount               decimal(10,2),
   freight              decimal(10,2),
   receipt_name         varchar(100),
   receipt_phone        varchar(100),
   province_id          bigint,
   province             varchar(100),
   city_id              bigint,
   city                 varchar(100),
   district_id          bigint,
   district             varchar(100),
   address              varchar(300),
   add_time             bigint,
   amount_refund        decimal(10,2),
   primary key (id)
);

/*==============================================================*/
/* Index: index_province_id                                     */
/*==============================================================*/
create index index_province_id on lt_order
(
   province_id
);

/*==============================================================*/
/* Index: index_city_id                                         */
/*==============================================================*/
create index index_city_id on lt_order
(
   city_id
);

/*==============================================================*/
/* Index: index_district_id                                     */
/*==============================================================*/
create index index_district_id on lt_order
(
   district_id
);

/*==============================================================*/
/* Table: lt_order_item                                         */
/*==============================================================*/
create table lt_order_item
(
   id                   bigint not null auto_increment,
   goods_id             bigint,
   price                decimal(10,2),
   num                  int,
   user_id              bigint,
   add_time             bigint,
   order_id             bigint,
   primary key (id)
);

/*==============================================================*/
/* Table: lt_order_log                                          */
/*==============================================================*/
create table lt_order_log
(
   id                   bigint not null,
   order_id             bigint,
   ltime                datetime,
   ltype                int(1) comment '1;订单;2:费用;3:物流',
   lmemo                varchar(100),
   lremark              varchar(400),
   primary key (id)
);

/*==============================================================*/
/* Table: lt_shop                                               */
/*==============================================================*/
create table lt_shop
(
   id                   bigint not null auto_increment,
   shop_name            varchar(300),
   user_id              bigint,
   add_time             bigint,
   primary key (id)
);

/*==============================================================*/
/* Index: index_uid                                             */
/*==============================================================*/
create unique index index_uid on lt_shop
(
   user_id
);

/*==============================================================*/
/* Table: lt_user                                               */
/*==============================================================*/
create table lt_user
(
   id                   bigint not null auto_increment,
   uname                varchar(50),
   mobile               varchar(11),
   upwd                 varchar(50),
   email                varchar(50),
   tname                varchar(50),
   mobileconfrim        int(1) default 0 comment '0未验证
            1已验证',
   emailconfrim         int(1) default 0 comment '0未验证
            1已验证',
   imghead              varchar(255),
   nickname             varchar(50),
   sex                  int(1) default 0 comment '0未知
            1男
            2女',
   birth                date,
   mysign               varchar(80),
   flag                 int(1) default 1 comment '0禁用,1正常',
   province             varchar(50),
   city                 varchar(50),
   district             varchar(50),
   addr                 varchar(255),
   idcard               varchar(20),
   frontimg             varchar(255),
   reversedimg          varchar(255),
   handimg              varchar(255),
   regtime              bigint,
   openid               varchar(50),
   primary key (id)
)
engine = innodb;

/*==============================================================*/
/* Index: index_fulltext                                        */
/*==============================================================*/
create fulltext index index_fulltext on lt_user
(
   uname,
   mobile,
   email,
   tname
);

/*==============================================================*/
/* Index: index_uname                                           */
/*==============================================================*/
create unique index index_uname on lt_user
(
   uname
);

/*==============================================================*/
/* Index: index_mobile                                          */
/*==============================================================*/
create unique index index_mobile on lt_user
(
   mobile
);

/*==============================================================*/
/* Index: index_email                                           */
/*==============================================================*/
create unique index index_email on lt_user
(
   email
);

/*==============================================================*/
/* Index: index_openid                                          */
/*==============================================================*/
create unique index index_openid on lt_user
(
   openid
);

/*==============================================================*/
/* Table: lt_user_addr                                          */
/*==============================================================*/
create table lt_user_addr
(
   id                   bigint not null,
   user_id              bigint,
   province_id          bigint,
   city_id              bigint,
   district_id          bigint,
   address              varchar(200),
   receipt_name         varchar(40),
   receipt_phone        varchar(40),
   add_time             datetime,
   default_used         int(1) comment '1:是;0:否;',
   primary key (id)
);

