/*
Navicat MySQL Data Transfer

Source Server         : huang
Source Server Version : 50155
Source Host           : localhost:3306
Source Database       : campus_security

Target Server Type    : MYSQL
Target Server Version : 50155
File Encoding         : 65001

Date: 2014-05-01 22:30:26
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for homework
-- ----------------------------
DROP TABLE IF EXISTS `homework`;
CREATE TABLE `homework` (
  `homework_id` int(11) NOT NULL,
  `title` varchar(255) DEFAULT NULL,
  `context` text,
  `create_teacher_id` int(11) DEFAULT NULL,
  `create_teacher_name` varchar(30) DEFAULT NULL,
  `modify_time` datetime DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `status` int(10) unsigned zerofill DEFAULT '0000000000',
  PRIMARY KEY (`homework_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of homework
-- ----------------------------
