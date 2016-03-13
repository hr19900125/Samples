/*
Navicat MySQL Data Transfer

Source Server         : huang
Source Server Version : 50155
Source Host           : localhost:3306
Source Database       : campus_security

Target Server Type    : MYSQL
Target Server Version : 50155
File Encoding         : 65001

Date: 2014-05-01 22:30:36
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for parent_account
-- ----------------------------
DROP TABLE IF EXISTS `parent_account`;
CREATE TABLE `parent_account` (
  `parent_account_id` int(11) NOT NULL,
  `username` varchar(30) DEFAULT NULL,
  `passwd` varchar(20) DEFAULT NULL,
  `school_id` int(11) DEFAULT NULL,
  `school_name` varchar(30) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `modify_time` datetime DEFAULT NULL,
  PRIMARY KEY (`parent_account_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of parent_account
-- ----------------------------
