/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 50528
Source Host           : localhost:3306
Source Database       : javashop

Target Server Type    : MYSQL
Target Server Version : 50528
File Encoding         : 65001

Date: 2017-04-02 19:03:05
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `eop_user`
-- ----------------------------
DROP TABLE IF EXISTS `eop_user`;
CREATE TABLE `eop_user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(50) DEFAULT NULL,
  `companyname` varchar(50) DEFAULT NULL,
  `password` varchar(50) DEFAULT NULL,
  `address` varchar(255) DEFAULT NULL,
  `legalrepresentative` varchar(50) DEFAULT NULL,
  `linkman` varchar(50) DEFAULT NULL,
  `tel` varchar(50) DEFAULT NULL,
  `mobile` varchar(50) DEFAULT NULL,
  `email` varchar(50) DEFAULT NULL,
  `logofile` varchar(255) DEFAULT NULL,
  `licensefile` varchar(255) DEFAULT NULL,
  `defaultsiteid` int(11) DEFAULT NULL,
  `deleteflag` int(6) DEFAULT '0',
  `usertype` int(6) DEFAULT NULL,
  `createtime` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of eop_user
-- ----------------------------
INSERT INTO `eop_user` VALUES ('1', 'admin', '单机版用户', 'e1adc3949ba59abbe56e057f2f883e', '在这里输入详细地址', null, '在这里输入联系人信息', '010-12345678', '13888888888', 'youmail@domain.com', null, null, null, '0', '1', null);
