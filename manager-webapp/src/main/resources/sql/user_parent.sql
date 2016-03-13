/*
Navicat MySQL Data Transfer

Source Server         : huang
Source Server Version : 50155
Source Host           : localhost:3306
Source Database       : campus_security

Target Server Type    : MYSQL
Target Server Version : 50155
File Encoding         : 65001

Date: 2014-05-01 22:31:19
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for user_parent
-- ----------------------------
DROP TABLE IF EXISTS `user_parent`;
CREATE TABLE `user_parent` (
  `user_id` int(11) NOT NULL,
  `parent_id` int(11) NOT NULL,
  PRIMARY KEY (`user_id`,`parent_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user_parent
-- ----------------------------
