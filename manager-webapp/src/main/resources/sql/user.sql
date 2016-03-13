/*
Navicat MySQL Data Transfer

Source Server         : huang
Source Server Version : 50155
Source Host           : localhost:3306
Source Database       : campus_security

Target Server Type    : MYSQL
Target Server Version : 50155
File Encoding         : 65001

Date: 2014-05-01 22:31:14
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `user_id` int(20) unsigned zerofill NOT NULL AUTO_INCREMENT,
  `user_name` varchar(100) DEFAULT NULL,
  `passwd` varchar(32) DEFAULT NULL,
  `real_name` varchar(100) DEFAULT NULL,
  `birthday` date DEFAULT NULL,
  `sex` varchar(2) DEFAULT NULL,
  `alias_name` varchar(100) DEFAULT NULL,
  `telephone` varchar(20) DEFAULT NULL,
  `email` varchar(30) DEFAULT NULL,
  `qq` varchar(15) DEFAULT NULL,
  `device_id` varchar(36) DEFAULT NULL,
  `rfid` varchar(36) DEFAULT NULL,
  `role_id` varchar(36) DEFAULT NULL,
  `create_time` date DEFAULT NULL,
  `modify_time` date DEFAULT NULL,
  `status` int(11) unsigned zerofill DEFAULT '00000000000',
  PRIMARY KEY (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES ('00000000000000000001', 'huangrui', '5c68967f800af4154cbf82bc7df14150', '黄睿', '2014-03-24', '男', 'ryan', '18676458869', 'hr19900125@126.com', '', '2123', '111', null, '2014-02-17', '2014-02-17', '00000000000');
