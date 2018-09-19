/*
Navicat MySQL Data Transfer

Source Server         : dev
Source Server Version : 50623
Source Host           : 192.168.5.117:3307
Source Database       : xiaochen

Target Server Type    : MYSQL
Target Server Version : 50623
File Encoding         : 65001

Date: 2018-08-22 10:30:53
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- create database
-- ----------------------------
DROP DATABASE IF EXISTS `xiaochen`;
CREATE DATABASE `xiaochen`;

use `xiaochen`;

-- ----------------------------
-- Table structure for `t_user_info`
-- ----------------------------
DROP TABLE IF EXISTS `t_user_info`;
CREATE TABLE `t_user_info` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(20) NOT NULL DEFAULT '',
  `age` int(11) NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_user_info
-- ----------------------------
INSERT INTO `t_user_info` VALUES ('1', 'xiaochen', '18');
INSERT INTO `t_user_info` VALUES ('2', '小臣', '20');
