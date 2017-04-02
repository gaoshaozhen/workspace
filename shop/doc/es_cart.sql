/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 50528
Source Host           : localhost:3306
Source Database       : javashop

Target Server Type    : MYSQL
Target Server Version : 50528
File Encoding         : 65001

Date: 2017-04-02 19:03:46
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `es_cart`
-- ----------------------------
DROP TABLE IF EXISTS `es_cart`;
CREATE TABLE `es_cart` (
  `cart_id` int(8) NOT NULL AUTO_INCREMENT,
  `goods_id` int(9) DEFAULT NULL,
  `product_id` int(8) DEFAULT NULL,
  `itemtype` int(8) DEFAULT '0',
  `num` int(8) DEFAULT NULL,
  `weight` decimal(20,3) DEFAULT NULL,
  `session_id` varchar(50) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `price` decimal(20,3) DEFAULT NULL,
  `addon` longtext,
  PRIMARY KEY (`cart_id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of es_cart
-- ----------------------------
