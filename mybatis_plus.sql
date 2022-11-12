/*
 Navicat Premium Data Transfer

 Source Server         : localhost
 Source Server Type    : MySQL
 Source Server Version : 50722
 Source Host           : localhost:3306
 Source Schema         : mybatis_plus

 Target Server Type    : MySQL
 Target Server Version : 50722
 File Encoding         : 65001

 Date: 21/10/2021 20:03:54
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `name` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '姓名',
  `age` int(11) NULL DEFAULT NULL COMMENT '年龄',
  `email` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '邮箱',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
  `version` int(11) NULL DEFAULT NULL COMMENT '版本号',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1449689775719071752 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES (1, 'Jone', 18, 'test1@baomidou.com', NULL, NULL, NULL);
INSERT INTO `user` VALUES (2, 'Jack', 20, 'test2@baomidou.com', NULL, NULL, NULL);
INSERT INTO `user` VALUES (3, 'Tom', 28, 'test3@baomidou.com', NULL, NULL, NULL);
INSERT INTO `user` VALUES (4, 'Sandy', 21, 'test4@baomidou.com', NULL, NULL, NULL);
INSERT INTO `user` VALUES (5, 'Billie', 24, 'test5@baomidou.com', NULL, NULL, NULL);
INSERT INTO `user` VALUES (1449689775719071747, 'sunwukong', 20, '781505696@qq.com', NULL, NULL, NULL);
INSERT INTO `user` VALUES (1449689775719071751, 'zhubajie', 10, '123123@qq.com', NULL, '2021-10-18 07:39:04', NULL);

SET FOREIGN_KEY_CHECKS = 1;
