/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 50549
Source Host           : localhost:3306
Source Database       : db_mis

Target Server Type    : MYSQL
Target Server Version : 50549
File Encoding         : 65001

Date: 2020-06-24 17:45:43
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `gs_book`
-- ----------------------------
DROP TABLE IF EXISTS `gs_book`;
CREATE TABLE `gs_book` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `name` varchar(128) DEFAULT NULL,
  `author` varchar(64) DEFAULT NULL,
  `publish` varchar(64) DEFAULT NULL,
  `time` varchar(64) DEFAULT NULL,
  `price` double(64,2) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1010 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of gs_book
-- ----------------------------
INSERT INTO `gs_book` VALUES ('1001', '1', '1', '简氏出版社', '1', '1.00');
INSERT INTO `gs_book` VALUES ('1002', '2', '2', '2', '2', '2.00');
INSERT INTO `gs_book` VALUES ('1003', '3', '3', '3', '3', '3.00');
INSERT INTO `gs_book` VALUES ('1004', '4', '4', '4', '4', '4.00');
INSERT INTO `gs_book` VALUES ('1005', '5', '5', '5', '5', '5.00');
INSERT INTO `gs_book` VALUES ('1006', '6', '6', '6', '6', '6.00');
INSERT INTO `gs_book` VALUES ('1007', '7', '7', '7', '7', '7.00');
INSERT INTO `gs_book` VALUES ('1008', '8', '8', '8', '8', '8.00');
INSERT INTO `gs_book` VALUES ('1009', '9', '9', '9', '9', '9.00');

-- ----------------------------
-- Table structure for `gs_button`
-- ----------------------------
DROP TABLE IF EXISTS `gs_button`;
CREATE TABLE `gs_button` (
  `id` int(22) NOT NULL AUTO_INCREMENT,
  `name` varchar(64) NOT NULL,
  `url` varchar(256) NOT NULL,
  `menuId` int(22) DEFAULT NULL,
  `buttonCode` varchar(256) DEFAULT NULL,
  `generateTime` int(22) DEFAULT NULL,
  `updateTime` int(22) DEFAULT NULL,
  `mac` varchar(256) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3004 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of gs_button
-- ----------------------------
INSERT INTO `gs_button` VALUES ('3001', '添加图书', '/book/toAddBook.do', '110101', 'book:toAdd', null, null, null);
INSERT INTO `gs_button` VALUES ('3002', '修改图书', '/book/toAddBook.do', '110101', 'book:toUpdate', null, null, null);
INSERT INTO `gs_button` VALUES ('3003', '批量删除', '/book/deleteBook.do', '110101', 'book:delete', null, null, null);

-- ----------------------------
-- Table structure for `gs_menu`
-- ----------------------------
DROP TABLE IF EXISTS `gs_menu`;
CREATE TABLE `gs_menu` (
  `ID` int(22) NOT NULL AUTO_INCREMENT,
  `NAME` varchar(64) NOT NULL,
  `URL` varchar(256) NOT NULL,
  `PID` int(22) DEFAULT NULL,
  `IMG` varchar(256) DEFAULT NULL,
  `GENERATE_TIME` int(22) DEFAULT NULL,
  `UPDATE_TIME` int(22) DEFAULT NULL,
  `MAC` varchar(256) DEFAULT NULL,
  `MENUCODE` varchar(64) DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=130303 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of gs_menu
-- ----------------------------
INSERT INTO `gs_menu` VALUES ('100000', '用户管理', '#', '-1', 'Hui-iconfont-root\r\nHui-iconfont-root\r\nHui-iconfont-root', null, null, null, null);
INSERT INTO `gs_menu` VALUES ('100100', '管理员管理', '/sysUser/sysUserList.do', '100000', null, null, null, null, null);
INSERT INTO `gs_menu` VALUES ('100101', '管理员管理', '#', '100100', null, null, null, null, null);
INSERT INTO `gs_menu` VALUES ('100200', '角色管理', '/role/roleList.do', '100000', null, null, null, null, null);
INSERT INTO `gs_menu` VALUES ('100201', '角色管理', '#', '100200', null, null, null, null, null);
INSERT INTO `gs_menu` VALUES ('110000', '图书管理', '#', '-1', 'Hui-iconfont-news', null, null, null, null);
INSERT INTO `gs_menu` VALUES ('110100', '图书管理', '/book/bookList.do', '110000', null, null, null, null, null);
INSERT INTO `gs_menu` VALUES ('110101', '图书管理', '#', '110100', null, null, null, null, null);
INSERT INTO `gs_menu` VALUES ('120000', '系统管理', '#', '-1', 'Hui-iconfont-tongji', null, null, null, null);
INSERT INTO `gs_menu` VALUES ('120100', '系统设置', '/system/systemManage.do', '120000', null, null, null, null, null);
INSERT INTO `gs_menu` VALUES ('120101', '系统设置', '#', '120100', null, null, null, null, null);
INSERT INTO `gs_menu` VALUES ('120200', '数据字典', '/system/dictionaryList.do', '120000', null, null, null, null, null);
INSERT INTO `gs_menu` VALUES ('120201', '数据字典', '#', '120200', null, null, null, null, null);
INSERT INTO `gs_menu` VALUES ('130000', '工具管理', '#', '-1', 'Hui-iconfont-manage2', null, null, null, null);
INSERT INTO `gs_menu` VALUES ('130100', '在线二维码', '/barcode/barcodeManage.do', '130000', null, null, null, null, null);
INSERT INTO `gs_menu` VALUES ('130101', '在线二维码', '#', '130100', null, null, null, null, null);
INSERT INTO `gs_menu` VALUES ('130200', '邮件测试', '/email/emailManage.do', '130000', null, null, null, null, null);
INSERT INTO `gs_menu` VALUES ('130201', '邮件测试', '#', '130200', null, null, null, null, null);
INSERT INTO `gs_menu` VALUES ('130300', 'Base64编解码', '/base64/toBase64.do', '130000', null, null, null, null, 'base64:toBase64');
INSERT INTO `gs_menu` VALUES ('130301', 'Base64编码', '/base64/toBase64Encode.do', '130300', null, null, null, null, 'base64:toBase64Encode');
INSERT INTO `gs_menu` VALUES ('130302', 'Base64解码', '/base64/toBase64Decode.do', '130300', null, null, null, null, 'base64:toBase64Decode');

-- ----------------------------
-- Table structure for `gs_role`
-- ----------------------------
DROP TABLE IF EXISTS `gs_role`;
CREATE TABLE `gs_role` (
  `ID` int(22) NOT NULL AUTO_INCREMENT,
  `NAME` varchar(64) NOT NULL,
  `MENU_ID` varchar(2560) DEFAULT NULL,
  `BUTTON_ID` varchar(2560) DEFAULT NULL,
  `GENERATE_TIME` int(22) DEFAULT NULL,
  `UPDATE_TIME` int(22) DEFAULT NULL,
  `MAC` varchar(256) DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=2003 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of gs_role
-- ----------------------------
INSERT INTO `gs_role` VALUES ('2001', '超级管理员', '100000, 100100, 100101, 100200, 100201, 110000, 110100, 110101, 120000, 120100, 120101, 120200, 120201, 130000, 130100, 130101, 130200, 130201, 130300, 130301, 130302', '3001, 3002', '-1', '1668256872', null);
INSERT INTO `gs_role` VALUES ('2002', '图书管理员', '110000, 110100, 110101', '3001, 3002', '-1', '-1467843596', null);

-- ----------------------------
-- Table structure for `gs_user`
-- ----------------------------
DROP TABLE IF EXISTS `gs_user`;
CREATE TABLE `gs_user` (
  `id` int(22) NOT NULL AUTO_INCREMENT,
  `name` varchar(64) NOT NULL,
  `account` varchar(256) NOT NULL,
  `password` varchar(256) NOT NULL,
  `roleId` int(22) DEFAULT NULL,
  `status` int(22) DEFAULT NULL,
  `failedNum` int(22) DEFAULT NULL,
  `changePass` int(22) DEFAULT NULL,
  `companyId` int(22) DEFAULT NULL,
  `generateTime` int(22) DEFAULT NULL,
  `updateTime` int(22) DEFAULT NULL,
  `mac` varchar(256) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1003 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of gs_user
-- ----------------------------
INSERT INTO `gs_user` VALUES ('1001', 'admin', 'admin', 'admin', '2001', null, null, null, null, null, null, null);
INSERT INTO `gs_user` VALUES ('1002', 'zj', 'zj', 'zj', '2002', null, null, null, null, null, null, null);
