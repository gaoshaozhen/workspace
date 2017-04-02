/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 50528
Source Host           : localhost:3306
Source Database       : javashop

Target Server Type    : MYSQL
Target Server Version : 50528
File Encoding         : 65001

Date: 2017-04-02 19:02:51
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `eop_site`
-- ----------------------------
DROP TABLE IF EXISTS `eop_site`;
CREATE TABLE `eop_site` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `userid` int(11) DEFAULT NULL,
  `sitename` varchar(255) DEFAULT NULL,
  `productid` varchar(50) DEFAULT NULL,
  `descript` longtext,
  `icofile` varchar(255) DEFAULT NULL,
  `logofile` varchar(255) DEFAULT NULL,
  `deleteflag` int(6) DEFAULT '0',
  `keywords` varchar(255) DEFAULT NULL,
  `themepath` varchar(50) DEFAULT NULL,
  `adminthemeid` int(11) DEFAULT NULL,
  `themeid` int(11) DEFAULT NULL,
  `point` int(11) DEFAULT '0',
  `createtime` bigint(20) DEFAULT NULL,
  `lastlogin` bigint(20) DEFAULT NULL,
  `lastgetpoint` bigint(20) DEFAULT NULL,
  `logincount` int(11) DEFAULT NULL,
  `bkloginpicfile` varchar(255) DEFAULT NULL,
  `bklogofile` varchar(255) DEFAULT NULL,
  `sumpoint` bigint(20) DEFAULT '0',
  `sumaccess` bigint(20) DEFAULT '0',
  `title` varchar(255) DEFAULT NULL,
  `username` varchar(255) DEFAULT NULL,
  `usersex` int(11) DEFAULT NULL,
  `usertel` varchar(50) DEFAULT NULL,
  `usermobile` varchar(50) DEFAULT NULL,
  `usertel1` varchar(50) DEFAULT NULL,
  `useremail` varchar(50) DEFAULT NULL,
  `state` int(6) DEFAULT '1',
  `qqlist` varchar(255) DEFAULT '25106942:客户服务!comma;52560956:技术支持',
  `msnlist` varchar(255) DEFAULT NULL,
  `wwlist` varchar(255) DEFAULT NULL,
  `tellist` varchar(255) DEFAULT NULL,
  `worktime` varchar(255) DEFAULT '9:00到18:00',
  `siteon` int(6) DEFAULT '0',
  `closereson` varchar(255) DEFAULT NULL,
  `copyright` varchar(1000) DEFAULT 'Copyright &copy; 2010-2012 本公司版权所有',
  `icp` varchar(255) DEFAULT '京ICP备05037293号',
  `address` varchar(255) DEFAULT '北京市某区某街某号',
  `zipcode` varchar(50) DEFAULT '000000',
  `qq` int(11) DEFAULT '1',
  `msn` int(11) DEFAULT '0',
  `ww` int(11) DEFAULT '0',
  `tel` int(11) DEFAULT '0',
  `wt` int(11) DEFAULT '1',
  `linkman` varchar(255) DEFAULT '刘先生',
  `linktel` varchar(255) DEFAULT '010-61750491',
  `email` varchar(255) DEFAULT 'enation@126.com',
  `multi_site` smallint(1) DEFAULT '0',
  `relid` varchar(255) DEFAULT NULL,
  `sitestate` smallint(1) DEFAULT '0',
  `isimported` smallint(1) DEFAULT '0',
  `imptype` int(6) DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of eop_site
-- ----------------------------
INSERT INTO `eop_site` VALUES ('1', '1', '示例商城', 'simple', null, 'http://localhost:8080/javashop/statics/images/default/favicon.ico', 'http://localhost:8080/javashop/statics/images/default/logo.gif', '0', null, 'default', '1', '1', '1000', '1489772944', '0', '0', '0', null, null, '0', '0', '示例商城', 'admin', null, '010-12345678', '13888888888', null, 'youmail@domain.com', '1', '25106942:客户服务!comma;52560956:技术支持', null, null, null, '9:00到18:00', '0', null, 'Copyright &copy; 2010-2012 本公司版权所有', '京ICP备05037293号', '北京市某区某街某号', '000000', '1', '0', '0', '0', '1', '刘先生', '010-61750491', 'enation@126.com', '0', null, '0', '0', '0');
