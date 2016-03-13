/*
Navicat MySQL Data Transfer

Source Server         : huang
Source Server Version : 50155
Source Host           : localhost:3306
Source Database       : campus_security

Target Server Type    : MYSQL
Target Server Version : 50155
File Encoding         : 65001

Date: 2014-05-01 22:30:41
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for privilege
-- ----------------------------
DROP TABLE IF EXISTS `privilege`;
CREATE TABLE `privilege` (
  `privilege_id` int(10) unsigned zerofill NOT NULL AUTO_INCREMENT,
  `privilege_type` varchar(20) DEFAULT NULL,
  `privilege_descr` varchar(255) DEFAULT NULL,
  `action_url` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`privilege_id`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of privilege
-- ----------------------------
INSERT INTO `privilege` VALUES ('0000000005', '增加权限2', '增加权限2', '/permissions.htm');
INSERT INTO `privilege` VALUES ('0000000006', '增加权限3', '增加权限3', '/roles.htm');
INSERT INTO `privilege` VALUES ('0000000007', '增加权限4', '增加权限4', '/role.htm');
INSERT INTO `privilege` VALUES ('0000000008', '增加权限5', '增加权限5', '/roleEdit.htm');
INSERT INTO `privilege` VALUES ('0000000011', '用户管理', '用户管理权限', '/users.htm');
