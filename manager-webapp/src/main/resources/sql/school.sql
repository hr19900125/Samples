/*
Navicat MySQL Data Transfer

Source Server         : huang
Source Server Version : 50155
Source Host           : localhost:3306
Source Database       : campus_security

Target Server Type    : MYSQL
Target Server Version : 50155
File Encoding         : 65001

Date: 2014-05-01 22:30:56
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for school
-- ----------------------------
DROP TABLE IF EXISTS `school`;
CREATE TABLE `school` (
  `school_id` int(11) NOT NULL AUTO_INCREMENT,
  `school_name` varchar(30) DEFAULT NULL,
  `province_id` varchar(32) DEFAULT NULL,
  `city_id` varchar(32) DEFAULT NULL,
  `county_id` varchar(32) DEFAULT NULL,
  `region` varchar(30) DEFAULT NULL,
  `modify_time` datetime DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `status` int(10) unsigned zerofill DEFAULT '0000000000',
  PRIMARY KEY (`school_id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of school
-- ----------------------------
INSERT INTO `school` VALUES ('4', '珠海四中', null, null, null, '珠海市凤凰北', null, '2014-04-15 22:55:43', '0000000000');
INSERT INTO `school` VALUES ('5', '珠海五中', null, null, null, '珠海市', null, '2014-04-15 22:56:02', '0000000000');
INSERT INTO `school` VALUES ('6', '珠海六中', null, null, null, '珠海市嗷嗷嗷', null, '2014-04-15 22:56:25', '0000000000');
INSERT INTO `school` VALUES ('7', '北京一中', '8E6iYOwATjmULsiKbixqFrHzfPg=', 'SgGkHXTSRs+qGY3q3JNaP1C+DzM=', 'W3AO1RHLSYGqhlTSN6NZAlC9RlU=', '北京-北京市-东城区', null, '2014-04-20 18:58:54', '0000000000');
