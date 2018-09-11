/*
 Navicat Premium Data Transfer

 Source Server         : mysql
 Source Server Type    : MySQL
 Source Server Version : 50720
 Source Host           : localhost:3306
 Source Schema         : zshop

 Target Server Type    : MySQL
 Target Server Version : 50720
 File Encoding         : 65001

 Date: 11/09/2018 20:43:22
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for t_cart
-- ----------------------------
DROP TABLE IF EXISTS `t_cart`;
CREATE TABLE `t_cart`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `productId` int(11) DEFAULT NULL,
  `userId` int(11) DEFAULT NULL,
  `productSum` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for t_customer
-- ----------------------------
DROP TABLE IF EXISTS `t_customer`;
CREATE TABLE `t_customer`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `login_name` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `password` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `phone` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `address` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `is_valid` int(11) DEFAULT NULL,
  `regist_date` datetime(0) DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `login_name`(`login_name`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 14 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_customer
-- ----------------------------
INSERT INTO `t_customer` VALUES (2, 'xuzilou', 'admin', 'admin', '15873133706', '上海', 1, '2018-08-13 11:14:17');
INSERT INTO `t_customer` VALUES (3, 'zhangsan', '123', '123', '1888191202', '长沙', 0, '2018-09-09 00:00:00');
INSERT INTO `t_customer` VALUES (5, 'lisi', '1233', '123', '1288191202', '长沙', 0, '2018-09-09 00:00:00');
INSERT INTO `t_customer` VALUES (6, '梅西', '111', '123', '13077038533', '长沙', 1, '2018-09-09 00:00:00');
INSERT INTO `t_customer` VALUES (7, 'C罗', '222', '123', '13077038533', '长沙', 1, '2018-09-09 00:00:00');
INSERT INTO `t_customer` VALUES (8, '内马尔', '3', '123', '1288191202', '长沙', 1, '2018-09-09 00:00:00');
INSERT INTO `t_customer` VALUES (9, '马塞洛', '4', '123', '1288191202', '长沙', 1, '2018-09-09 00:00:00');
INSERT INTO `t_customer` VALUES (10, '罗纳尔多', '5', '123', '1288191202', '长沙', 1, '2018-09-09 00:00:00');
INSERT INTO `t_customer` VALUES (11, '罗纳尔迪尼奥', '6', '123', '1288191202', '长沙', 1, '2018-09-09 00:00:00');
INSERT INTO `t_customer` VALUES (12, '贝克汉姆', '7', '123', '1288191202', '长沙', 1, '2018-09-09 00:00:00');
INSERT INTO `t_customer` VALUES (13, '卡卡', '888', '123', '15873122061', '攸县', 1, '2018-09-09 00:00:00');

-- ----------------------------
-- Table structure for t_order
-- ----------------------------
DROP TABLE IF EXISTS `t_order`;
CREATE TABLE `t_order`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `no` varchar(300) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `customer_id` int(11) DEFAULT NULL,
  `prcie` float DEFAULT NULL,
  `create_date` datetime(0) DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for t_order_item
-- ----------------------------
DROP TABLE IF EXISTS `t_order_item`;
CREATE TABLE `t_order_item`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `product_id` int(11) DEFAULT NULL,
  `num` int(11) DEFAULT NULL,
  `price` float DEFAULT NULL,
  `order_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for t_product
-- ----------------------------
DROP TABLE IF EXISTS `t_product`;
CREATE TABLE `t_product`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `price` float DEFAULT NULL,
  `info` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `image` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `product_type_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `name`(`name`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 17 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_product
-- ----------------------------
INSERT INTO `t_product` VALUES (3, '橘子', 20, NULL, 'E:/javaweb/项目实战/在线商城系统/zshop/zshop_bakend_web/src/main/webapp/uploda/d82c32db-2be7-4bcb-9f1a-d699c3e7563a.jpg', 25);
INSERT INTO `t_product` VALUES (4, '苹果', 20, NULL, 'E:/javaweb/项目实战/在线商城系统/zshop/zshop_bakend_web/src/main/webapp/uploda/80a61c95-f35d-4f78-a8b6-447817c60876.jpg', 25);
INSERT INTO `t_product` VALUES (5, '梨子', 11, NULL, 'E:/javaweb/项目实战/在线商城系统/zshop/zshop_bakend_web/src/main/webapp/uploda/d49eb1ae-c671-4417-9083-bcd71a015e90.jpg', 25);
INSERT INTO `t_product` VALUES (6, '西瓜', 999, NULL, 'E:/javaweb/项目实战/在线商城系统/zshop/zshop_bakend_web/src/main/webapp/uploda/43a25a24-7bcd-4fed-b756-da3e09c23757.jpg', 25);
INSERT INTO `t_product` VALUES (7, '桃子', 123, NULL, 'E:/javaweb/项目实战/在线商城系统/zshop/zshop_bakend_web/src/main/webapp/uploda/a434b1b2-b0f3-48fc-b69f-09dfd7b473dc.jpg', 25);
INSERT INTO `t_product` VALUES (8, '白菜', 1, NULL, 'E:/javaweb/项目实战/在线商城系统/zshop/zshop_bakend_web/src/main/webapp/uploda/1b27263c-acc6-4eb6-b871-60e4f7cb2123.jpg', 25);
INSERT INTO `t_product` VALUES (9, '123', 123, NULL, 'E:/javaweb/项目实战/在线商城系统/zshop/zshop_bakend_web/src/main/webapp/uploda/ba4c266e-bbef-4c55-a9d0-c896d2b0eae7.jpg', 6);
INSERT INTO `t_product` VALUES (10, '1231', 123, NULL, 'E:/javaweb/项目实战/在线商城系统/zshop/zshop_bakend_web/src/main/webapp/uploda/5647ef8f-3d5c-489e-a5a3-ef19341c6d0a.jpg', 6);
INSERT INTO `t_product` VALUES (11, '1', 1, NULL, 'E:/javaweb/项目实战/在线商城系统/zshop/zshop_bakend_web/src/main/webapp/uploda/bef08de2-7cab-4d13-beda-65692d0c64e2.jpg', 3);
INSERT INTO `t_product` VALUES (12, '2', 2, NULL, 'E:/javaweb/项目实战/在线商城系统/zshop/zshop_bakend_web/src/main/webapp/uploda/730d8f07-11b2-4af2-9eb5-eb408bc46b91.jpg', 6);
INSERT INTO `t_product` VALUES (15, '11111', 123, NULL, 'E:/javaweb/项目实战/在线商城系统/zshop/zshop_bakend_web/src/main/webapp/uploda/71059047-9494-4c0c-9097-05cbbbb791e9.jpg', 25);
INSERT INTO `t_product` VALUES (16, '12311', 123, NULL, 'E:/javaweb/项目实战/在线商城系统/zshop/zshop_bakend_web/src/main/webapp/uploda/5f207323-0330-4f30-a1fc-5c1f748e637b.jpg', 3);

-- ----------------------------
-- Table structure for t_product_type
-- ----------------------------
DROP TABLE IF EXISTS `t_product_type`;
CREATE TABLE `t_product_type`  (
  `type_id` int(11) NOT NULL AUTO_INCREMENT,
  `type_name` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `type_status` int(11) DEFAULT NULL,
  PRIMARY KEY (`type_id`) USING BTREE,
  UNIQUE INDEX `type_name`(`type_name`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 26 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_product_type
-- ----------------------------
INSERT INTO `t_product_type` VALUES (1, '食品', 0);
INSERT INTO `t_product_type` VALUES (2, '衣服', 1);
INSERT INTO `t_product_type` VALUES (3, '数码', 1);
INSERT INTO `t_product_type` VALUES (4, '生活用品', 0);
INSERT INTO `t_product_type` VALUES (5, '家装', 0);
INSERT INTO `t_product_type` VALUES (6, '旅游', 1);
INSERT INTO `t_product_type` VALUES (7, '运动', 1);
INSERT INTO `t_product_type` VALUES (8, '电器', 1);
INSERT INTO `t_product_type` VALUES (9, '家具', 0);
INSERT INTO `t_product_type` VALUES (10, '配饰', 1);
INSERT INTO `t_product_type` VALUES (11, '裤子', 1);
INSERT INTO `t_product_type` VALUES (12, '包包', 1);
INSERT INTO `t_product_type` VALUES (13, '鞋子', 0);
INSERT INTO `t_product_type` VALUES (14, '内衣', 1);
INSERT INTO `t_product_type` VALUES (15, '箱子', 1);
INSERT INTO `t_product_type` VALUES (16, '电脑', 1);
INSERT INTO `t_product_type` VALUES (18, '12', 1);
INSERT INTO `t_product_type` VALUES (19, '1', 1);
INSERT INTO `t_product_type` VALUES (25, '水果', 1);

-- ----------------------------
-- Table structure for t_role
-- ----------------------------
DROP TABLE IF EXISTS `t_role`;
CREATE TABLE `t_role`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `role_name` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 4 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_role
-- ----------------------------
INSERT INTO `t_role` VALUES (1, '商品专员');
INSERT INTO `t_role` VALUES (2, '营销经理');
INSERT INTO `t_role` VALUES (3, '超级管理员');

-- ----------------------------
-- Table structure for t_sysuser
-- ----------------------------
DROP TABLE IF EXISTS `t_sysuser`;
CREATE TABLE `t_sysuser`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `login_name` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `password` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `phone` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `email` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `is_valid` int(11) DEFAULT NULL,
  `create_date` datetime(0) DEFAULT NULL,
  `role_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `login_name`(`login_name`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 29 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_sysuser
-- ----------------------------
INSERT INTO `t_sysuser` VALUES (26, '123', '465', 'b3934fd927689fd25bb850c1c34ba344', '15877332061', '121585088@qq.com', 1, '2018-09-05 23:51:14', 3);
INSERT INTO `t_sysuser` VALUES (27, '123', 'zhangsan', '9419e260c4cc9565ac99e88855b97983', '15873133706', '1215855088@qq.com', 1, '2018-09-06 08:02:23', 1);
INSERT INTO `t_sysuser` VALUES (28, '123', 'xuzilou', '022c7e41149ef2e32aacb2f19b5b5fc8', '15873133706', '1215855088@qq.com', 1, '2018-09-06 08:05:36', 1);

SET FOREIGN_KEY_CHECKS = 1;
