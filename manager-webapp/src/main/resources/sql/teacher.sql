/*
Navicat MySQL Data Transfer

Source Server         : huang
Source Server Version : 50155
Source Host           : localhost:3306
Source Database       : campus_security

Target Server Type    : MYSQL
Target Server Version : 50155
File Encoding         : 65001

Date: 2014-05-01 22:31:00
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for teacher
-- ----------------------------
DROP TABLE IF EXISTS `teacher`;
CREATE TABLE `teacher` (
  `teacher_id` int(11) NOT NULL,
  `teacher_name` varchar(30) DEFAULT NULL,
  `school_name` varchar(30) DEFAULT NULL,
  `school_id` int(11) DEFAULT NULL,
  `department` varchar(50) DEFAULT NULL,
  `birthday` datetime DEFAULT NULL,
  `sex` varchar(1) DEFAULT NULL,
  `education` varchar(20) DEFAULT NULL,
  `telephone` varchar(20) DEFAULT NULL,
  `level_name` varchar(30) DEFAULT NULL,
  `address` varchar(50) DEFAULT NULL,
  `entry_time` datetime DEFAULT NULL,
  `leave_time` datetime DEFAULT NULL,
  `status` int(11) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `modify_time` datetime DEFAULT NULL,
  PRIMARY KEY (`teacher_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of teacher
-- ----------------------------
