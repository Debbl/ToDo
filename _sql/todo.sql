/*
 Navicat Premium Data Transfer

 Source Server         : centos
 Source Server Type    : MySQL
 Source Server Version : 50731
 Source Host           : 192.168.31.59:3306
 Source Schema         : todo

 Target Server Type    : MySQL
 Target Server Version : 50731
 File Encoding         : 65001

 Date: 07/08/2020 19:27:31
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for todo_tasks
-- ----------------------------
DROP TABLE IF EXISTS `todo_tasks`;
CREATE TABLE `todo_tasks`  (
  `count` int(11) NOT NULL AUTO_INCREMENT,
  `task` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `tuid` int(11) NOT NULL,
  PRIMARY KEY (`count`) USING BTREE,
  INDEX `tuid`(`tuid`) USING BTREE,
  CONSTRAINT `tuid` FOREIGN KEY (`tuid`) REFERENCES `todo_user` (`uid`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 6 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of todo_tasks
-- ----------------------------
INSERT INTO `todo_tasks` VALUES (1, '任务一', 1);
INSERT INTO `todo_tasks` VALUES (2, '任务二', 1);
INSERT INTO `todo_tasks` VALUES (3, '任务三', 1);
INSERT INTO `todo_tasks` VALUES (4, '二二二', 2);
INSERT INTO `todo_tasks` VALUES (5, '三三三', 3);

-- ----------------------------
-- Table structure for todo_user
-- ----------------------------
DROP TABLE IF EXISTS `todo_user`;
CREATE TABLE `todo_user`  (
  `uid` int(11) NOT NULL AUTO_INCREMENT,
  `uname` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`uid`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 4 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of todo_user
-- ----------------------------
INSERT INTO `todo_user` VALUES (1, '用户一');
INSERT INTO `todo_user` VALUES (2, '用户二');
INSERT INTO `todo_user` VALUES (3, '用户三');

SET FOREIGN_KEY_CHECKS = 1;
