﻿/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 50715
Source Host           : localhost:3306
Source Database       : lt-shop

Target Server Type    : MYSQL
Target Server Version : 50715
File Encoding         : 65001

Date: 2017-04-21 20:28:00
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `lt_cart`
-- ----------------------------
DROP TABLE IF EXISTS `lt_cart`;
CREATE TABLE `lt_cart` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `goods_id` bigint(20) DEFAULT NULL,
  `num` int(11) NOT NULL DEFAULT '1',
  `user_id` bigint(20) DEFAULT NULL,
  `add_time` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `index_gid_uid` (`goods_id`,`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of lt_cart
-- ----------------------------
INSERT INTO `lt_cart` VALUES ('1', '15', '2', '2', '1492741901046');
INSERT INTO `lt_cart` VALUES ('2', '14', '1', '2', '1492742431371');

-- ----------------------------
-- Table structure for `lt_goods`
-- ----------------------------
DROP TABLE IF EXISTS `lt_goods`;
CREATE TABLE `lt_goods` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `shop_id` bigint(20) DEFAULT NULL,
  `user_id` bigint(20) DEFAULT NULL,
  `title` varchar(300) DEFAULT NULL,
  `title_short` varchar(100) DEFAULT NULL,
  `cid` bigint(20) DEFAULT NULL,
  `price_market` decimal(10,2) DEFAULT NULL,
  `price` decimal(10,2) DEFAULT NULL,
  `price_cost` decimal(10,2) DEFAULT NULL,
  `freight` decimal(10,2) DEFAULT '0.00' COMMENT '0按重量整单计算',
  `unit` varchar(10) DEFAULT NULL,
  `weight` double(5,2) DEFAULT NULL,
  `status` int(1) DEFAULT NULL COMMENT '0新品，1上架，2，下架，3售罄',
  `def_img` varchar(300) DEFAULT NULL,
  `ablums` varchar(1000) DEFAULT NULL,
  `note` blob,
  `buy_total` int(11) DEFAULT '0',
  `add_time` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of lt_goods
-- ----------------------------
INSERT INTO `lt_goods` VALUES ('14', '1', '1', '超级无敌大鸡蛋1', null, '1', '22.00', '16.00', '12.00', '0.00', '个', '0.50', '1', 'i/1/14/1492500768478.jpg', 'i/1/14/1492500768478.jpg,i/1/14/1492500768628.jpg,i/1/14/1492500768628.jpg', 0x3C703EE694B6E588B0E4BA86E7A68FE5BBBAE79C81E794B5E58A9BE688BFE997B4E9878CE79A84E4BA8BE8BFB9E6B395E5BE8BE6898BE6AEB5E8A7A3E694BEE8B7AFE4B889E5ADA3E5BAA6E4B8A4E4B8AAE8AEA1E9878FE6A380E5AE9AE68980E7AEA1E79086E59B9BE9838AE5A49AE59E923C2F703E, '0', '1492419662611');
INSERT INTO `lt_goods` VALUES ('15', '1', '1', '德青源 爱的鲜鸡蛋 32枚', null, '1', '46.80', '36.80', '26.80', '10.00', '盒', '2.25', '1', 'i/1/15/1492659347308.jpg', 'i/1/15/1492659347308.jpg,i/1/15/1492659347525.jpg,i/1/15/1492659347701.jpg,i/1/15/1492659347881.jpg', 0x3C703E3C6120687265663D2268747470733A2F2F73616C652E6A642E636F6D2F6163742F68795677445462586764452E68746D6C22207461726765743D225F626C616E6B223E3C696D6720616C743D2222207372633D2268747470733A2F2F696D6733302E333630627579696D672E636F6D2F6A6773712D70726F64756374736F612F6A66732F74343938372F3133342F313836343935333736332F38383732352F33353665636466372F35386635366339364E32633764663833612E6A7067222F3E3C2F613E3C2F703E3C7461626C6520616C69676E3D2263656E746572222077696474683D22373530223E3C74626F64793E3C747220636C6173733D226669727374526F77223E3C746420636F6C7370616E3D2234223E3C6120687265663D2268747470733A2F2F6D616C6C2E6A642E636F6D2F696E6465782D313030303031373634322E68746D6C223E3C696D6720616C743D2222207372633D2268747470733A2F2F696D6731312E333630627579696D672E636F6D2F636D732F6A66732F74343833342F3134342F37303037373930392F35333531382F37363836346465302F35386461316461344E63623034616531622E6A706722206865696768743D22323232222077696474683D22373530222F3E3C2F613E3C2F74643E3C2F74723E3C74723E3C74643E3C6120687265663D2268747470733A2F2F6974656D2E6A642E636F6D2F323130383931392E68746D6C223E3C696D6720616C743D2222207372633D2268747470733A2F2F696D6731302E333630627579696D672E636F6D2F636D732F6A66732F74343139322F31352F333038363530323433392F32323832312F33353765393832612F35386461316461654E31363536656265652E6A706722206865696768743D22323738222077696474683D22313933222F3E3C2F613E3C2F74643E3C74643E3C6120687265663D2268747470733A2F2F6974656D2E6A642E636F6D2F313336303938392E68746D6C223E3C696D6720616C743D2222207372633D2268747470733A2F2F696D6731342E333630627579696D672E636F6D2F636D732F6A66732F74343630392F3331382F313138303835383637332F32333039352F39646632643837392F35386461316462354E64636666346437342E6A706722206865696768743D22323738222077696474683D22313832222F3E3C2F613E3C2F74643E3C74643E3C6120687265663D2268747470733A2F2F6974656D2E6A642E636F6D2F313336323937302E68746D6C223E3C696D6720616C743D2222207372633D2268747470733A2F2F696D6731302E333630627579696D672E636F6D2F636D732F6A66732F74343930302F3331322F37363139353631362F32333638362F64366232316362642F35386461316462634E36346239316265632E6A706722206865696768743D22323738222077696474683D22313833222F3E3C2F613E3C2F74643E3C74643E3C6120687265663D2268747470733A2F2F6974656D2E6A642E636F6D2F343537393030302E68746D6C223E3C696D6720616C743D2222207372633D2268747470733A2F2F696D6731332E333630627579696D672E636F6D2F636D732F6A66732F74343531332F3336322F313231373232373539332F32343539322F61343836313439372F35386461316463314E64616234346439322E6A706722206865696768743D22323738222077696474683D22313932222F3E3C2F613E3C2F74643E3C2F74723E3C2F74626F64793E3C2F7461626C653E3C703E266E6273703B20266E6273703B20266E6273703B20266E6273703B20266E6273703B20266E6273703B20266E6273703B20266E6273703B20266E6273703B20266E6273703B20266E6273703B20266E6273703B20266E6273703B20266E6273703B20266E6273703B20266E6273703B20266E6273703B20266E6273703B20266E6273703B20266E6273703B20266E6273703B20266E6273703B20266E6273703B20266E6273703B20266E6273703B20266E6273703B20266E6273703B20266E6273703B20266E6273703B20266E6273703B20266E6273703B20266E6273703B20266E6273703B20266E6273703B20266E6273703B20266E6273703B20266E6273703B20266E6273703B20266E6273703B20266E6273703B20266E6273703B20266E6273703B20266E6273703B20266E6273703B20266E6273703B20266E6273703B20266E6273703B20266E6273703B093C2F703E3C703E3C696D6720616C743D2222207372633D2268747470733A2F2F696D6732302E333630627579696D672E636F6D2F76632F6A66732F74333133392F3238302F3337373539333230332F3238383532342F32643834323961382F35376233656164624E34363764663062312E6A7067222F3E3C696D6720616C743D2222207372633D2268747470733A2F2F696D6732302E333630627579696D672E636F6D2F76632F6A66732F74323635302F3236352F343236393432333639322F3130393937372F38373331323634652F35376233656165374E61373837633539352E6A7067222F3E3C696D6720616C743D2222207372633D2268747470733A2F2F696D6732302E333630627579696D672E636F6D2F76632F6A66732F74323834382F3334372F343237323233393237342F35373333322F37636533303965322F35376234323439614E32643436313537312E6A7067222F3E3C2F703E3C703E3C696D6720616C743D2222207372633D2268747470733A2F2F696D6732302E333630627579696D672E636F6D2F76632F6A66732F74333231312F3136302F3335393431333533392F3135343436362F63306130303939662F35376233656166314E37393064633933382E6A7067222F3E3C2F703E3C703E3C696D6720616C743D2222207372633D2268747470733A2F2F696D6732302E333630627579696D672E636F6D2F76632F6A66732F74333632322F35352F3233343535373336372F3137363936372F34643433306331352F35383037323130344E31346462393066612E6A7067222F3E3C2F703E3C703E3C62722F3E3C2F703E, '0', '1492659391059');

-- ----------------------------
-- Table structure for `lt_order`
-- ----------------------------
DROP TABLE IF EXISTS `lt_order`;
CREATE TABLE `lt_order` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `order_sn` varchar(50) DEFAULT NULL,
  `order_status` int(11) DEFAULT NULL COMMENT '0已取消,1新订单,2已支付,3已发货,4已收货,5已完成',
  `pay_status` int(11) DEFAULT NULL COMMENT '1未付款，2已付款，3已退款',
  `deliver_status` char(10) DEFAULT NULL COMMENT '1未发货，2已发货，3已退货',
  `user_id` bigint(20) DEFAULT NULL,
  `amount` decimal(10,2) DEFAULT NULL,
  `freight` decimal(10,2) DEFAULT NULL,
  `receipt_name` varchar(100) DEFAULT NULL,
  `receipt_phone` varchar(100) DEFAULT NULL,
  `province` varchar(100) DEFAULT NULL,
  `city` varchar(100) DEFAULT NULL,
  `district` varchar(100) DEFAULT NULL,
  `address` varchar(300) DEFAULT NULL,
  `add_time` bigint(20) DEFAULT NULL,
  `amount_refund` decimal(10,2) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of lt_order
-- ----------------------------

-- ----------------------------
-- Table structure for `lt_order_item`
-- ----------------------------
DROP TABLE IF EXISTS `lt_order_item`;
CREATE TABLE `lt_order_item` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `goods_id` bigint(20) DEFAULT NULL,
  `num` int(11) DEFAULT NULL,
  `user_id` bigint(20) DEFAULT NULL,
  `add_time` bigint(20) DEFAULT NULL,
  `order_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of lt_order_item
-- ----------------------------

-- ----------------------------
-- Table structure for `lt_shop`
-- ----------------------------
DROP TABLE IF EXISTS `lt_shop`;
CREATE TABLE `lt_shop` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `shop_name` varchar(300) DEFAULT NULL,
  `user_id` bigint(20) DEFAULT NULL,
  `add_time` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `index_uid` (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of lt_shop
-- ----------------------------
INSERT INTO `lt_shop` VALUES ('1', '鸡蛋之家', '1', null);

-- ----------------------------
-- Table structure for `lt_user`
-- ----------------------------
DROP TABLE IF EXISTS `lt_user`;
CREATE TABLE `lt_user` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `uname` varchar(50) DEFAULT NULL,
  `mobile` varchar(11) DEFAULT NULL,
  `upwd` varchar(50) DEFAULT NULL,
  `email` varchar(50) DEFAULT NULL,
  `tname` varchar(50) DEFAULT NULL,
  `mobileconfrim` int(1) DEFAULT '0' COMMENT '0未验证\r\n            1已验证',
  `emailconfrim` int(1) DEFAULT '0' COMMENT '0未验证\r\n            1已验证',
  `imghead` varchar(255) DEFAULT NULL,
  `nickname` varchar(50) DEFAULT NULL,
  `sex` int(1) DEFAULT '0' COMMENT '0未知\r\n            1男\r\n            2女',
  `birth` date DEFAULT NULL,
  `mysign` varchar(80) DEFAULT NULL,
  `flag` int(1) DEFAULT '1' COMMENT '0禁用,1正常',
  `province` varchar(50) DEFAULT NULL,
  `city` varchar(50) DEFAULT NULL,
  `district` varchar(50) DEFAULT NULL,
  `addr` varchar(255) DEFAULT NULL,
  `idcard` varchar(20) DEFAULT NULL,
  `frontimg` varchar(255) DEFAULT NULL,
  `reversedimg` varchar(255) DEFAULT NULL,
  `handimg` varchar(255) DEFAULT NULL,
  `regtime` bigint(20) DEFAULT NULL,
  `openid` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `index_uname` (`uname`),
  UNIQUE KEY `index_mobile` (`mobile`),
  UNIQUE KEY `index_email` (`email`),
  UNIQUE KEY `index_openid` (`openid`),
  FULLTEXT KEY `index_fulltext` (`uname`,`mobile`,`email`,`tname`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of lt_user
-- ----------------------------
INSERT INTO `lt_user` VALUES ('1', 'xiaoli', null, '0192023a7bbd73250516f069df18b500', null, null, '0', '0', null, null, '0', null, null, '1', null, null, null, null, null, null, null, null, null, null);
INSERT INTO `lt_user` VALUES ('2', '13811451717', '13811451717', '0192023a7bbd73250516f069df18b500', null, null, null, null, null, null, null, null, null, '1', null, null, null, null, null, null, null, null, '1492674035291', null);
-- ----------------------------
-- Table structure for `lt_order_log`
-- ----------------------------
DROP TABLE IF EXISTS `lt_order_log`;
CREATE TABLE `lt_order_log` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `order_id` int(11) DEFAULT NULL COMMENT '订单ID',
  `ltime` datetime DEFAULT NULL COMMENT '时间',
  `ltype` varchar(20) DEFAULT NULL COMMENT '类型，1:订单；2:费用；3：物流；',
  `lmemo` varchar(255) DEFAULT NULL COMMENT '说明',
  `lremark` varchar(255) DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
-- ----------------------------
-- Table structure for `lt_user_addr`
-- ----------------------------
DROP TABLE IF EXISTS `lt_user_addr`;
CREATE TABLE `lt_user_addr` (
  `id` int(11) DEFAULT NULL,
  `user_id` int(11) DEFAULT NULL,
  `province_id` int(255) DEFAULT NULL,
  `city_id` int(11) DEFAULT NULL,
  `district_id` int(11) DEFAULT NULL,
  `address` varchar(500) DEFAULT NULL,
  `receipt_name` varchar(50) DEFAULT NULL,
  `receipt_phone` varchar(50) DEFAULT NULL,
  `add_time` datetime DEFAULT NULL,
  `default_used` int(255) DEFAULT NULL COMMENT '默认，1，是，0否'
) ENGINE=InnoDB DEFAULT CHARSET=utf8;