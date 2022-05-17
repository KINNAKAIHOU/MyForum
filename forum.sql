/*
 Navicat MySQL Data Transfer

 Source Server         : mysql
 Source Server Type    : MySQL
 Source Server Version : 80027
 Source Host           : localhost:3306
 Source Schema         : forum

 Target Server Type    : MySQL
 Target Server Version : 80027
 File Encoding         : 65001

 Date: 17/05/2022 10:56:59
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for t_blog
-- ----------------------------
DROP TABLE IF EXISTS `t_blog`;
CREATE TABLE `t_blog`  (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `title` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `content` text CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL,
  `share_statement` int NULL DEFAULT 0,
  `create_time` datetime NULL DEFAULT NULL,
  `update_time` datetime NULL DEFAULT NULL,
  `views` int NULL DEFAULT 1,
  `love_count` int NULL DEFAULT 0,
  `favorite_count` int NULL DEFAULT 0,
  `see_able` bit(1) NULL DEFAULT NULL,
  `love_able` bit(1) NULL DEFAULT NULL,
  `comment_able` bit(1) NULL DEFAULT NULL,
  `user_id` bigint NULL DEFAULT NULL,
  `is_delete` int NULL DEFAULT 0,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 58 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_blog
-- ----------------------------
INSERT INTO `t_blog` VALUES (1, 'FirstTest', NULL, 1, '2022-05-13 14:47:02', '2022-05-13 14:47:02', 1, NULL, NULL, NULL, NULL, NULL, 3, 1);
INSERT INTO `t_blog` VALUES (2, 'Test2', '第二次测试分享内容...\r\n允许查看，允许点赞，允许留言', 1, '2022-05-13 14:50:14', '2022-05-13 14:50:14', 1, 0, NULL, b'1', b'1', b'1', 3, 1);
INSERT INTO `t_blog` VALUES (39, '我不玩啦！', 'Test1', 1, '2022-05-13 20:47:41', '2022-05-15 20:04:23', 5, 0, 0, b'1', b'0', b'1', 3, 0);
INSERT INTO `t_blog` VALUES (40, '深崎暮人的画', '深崎暮人的画', 1, '2022-05-13 20:50:23', '2022-05-16 21:25:52', 9, 0, 0, b'1', b'0', b'1', 3, 0);
INSERT INTO `t_blog` VALUES (41, '3Able测试', '3Able测试', 1, '2022-05-13 20:51:52', '2022-05-13 20:53:56', 1, 0, 0, NULL, NULL, NULL, 3, 1);
INSERT INTO `t_blog` VALUES (42, 'Sinn来到啦', '我tama莱纳！', 1, '2022-05-13 21:29:56', '2022-05-15 20:04:06', 3, 0, 0, b'1', b'0', b'0', 2, 0);
INSERT INTO `t_blog` VALUES (43, 'DEleteTest', 'aaa', 1, '2022-05-14 13:24:33', '2022-05-14 13:24:39', 1, 0, 0, b'0', b'1', b'1', 3, 1);
INSERT INTO `t_blog` VALUES (44, 'TestDelete', 'asd', 1, '2022-05-14 13:26:12', '2022-05-14 13:26:18', 1, 0, 0, b'1', b'1', b'1', 3, 1);
INSERT INTO `t_blog` VALUES (48, '请求测试', '请求测试 ，不带任何@Request。\r\n二测：查看Vicews为null问题\r\n三测：修改shareStatement为boolean', 1, '2022-05-14 22:20:02', '2022-05-15 20:04:03', 4, 0, 0, b'1', b'1', b'0', 2, 0);
INSERT INTO `t_blog` VALUES (50, 'haah', 'aahahaha', 1, '2022-05-14 22:40:08', '2022-05-15 20:04:00', 5, 1, 0, b'1', b'1', b'1', 2, 0);
INSERT INTO `t_blog` VALUES (51, '图片内容显示测试', 'AAAAA希望出图片', 1, '2022-05-15 16:28:12', '2022-05-15 16:28:38', 1, 1, 0, b'1', b'1', b'1', 2, 1);
INSERT INTO `t_blog` VALUES (52, '路人女主的养成方式', '圣人惠yyds', 1, '2022-05-15 16:50:35', '2022-05-15 20:03:49', 9, 2, 0, b'1', b'1', b'0', 2, 0);
INSERT INTO `t_blog` VALUES (55, '几张图片分享', '啊啊啊啊阿阿\r\n分享一下加藤惠', 1, '2022-05-15 22:49:29', '2022-05-16 22:28:54', 56, 2, 3, b'1', b'1', b'1', 3, 0);
INSERT INTO `t_blog` VALUES (56, '用于评论的测试', '用于评论的测试的分享', 1, '2022-05-16 16:48:16', '2022-05-16 21:24:22', 41, 2, 1, b'1', b'1', b'1', 2, 0);
INSERT INTO `t_blog` VALUES (57, '发几张霞诗子的图片', '发几张霞诗子的图片\r\n——图片来源 冴えない彼女の育てかた 深崎暮人画集 下 Fine.', 1, '2022-05-17 10:37:53', '2022-05-17 10:38:55', 5, 0, 1, b'1', b'1', b'1', 1, 0);
INSERT INTO `t_blog` VALUES (58, '这货谁啊', '这货谁啊？\r\n没见过啊', 1, '2022-05-17 10:53:44', '2022-05-17 10:54:20', 4, 0, 0, b'1', b'1', b'1', 27, 0);

-- ----------------------------
-- Table structure for t_comment
-- ----------------------------
DROP TABLE IF EXISTS `t_comment`;
CREATE TABLE `t_comment`  (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `content` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `create_time` datetime NULL DEFAULT NULL,
  `parent_comment_id` bigint NULL DEFAULT -1,
  `root_comment_id` bigint NULL DEFAULT -1,
  `user_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `blog_id` bigint NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_comment
-- ----------------------------
INSERT INTO `t_comment` VALUES (1, '第一条留言！', '2022-05-16 19:58:37', -1, -1, 'Sinn', 56);
INSERT INTO `t_comment` VALUES (2, '第二条评论!', '2022-05-16 20:05:00', -1, -1, 'Sinn', 56);
INSERT INTO `t_comment` VALUES (3, '第三条评论！', '2022-05-16 20:06:14', -1, -1, 'LKA', 56);
INSERT INTO `t_comment` VALUES (4, '发布一条留言', '2022-05-16 22:31:18', -1, -1, 'LKA', 55);
INSERT INTO `t_comment` VALUES (5, '回复Sinn的第一条评论', '2022-05-17 10:16:10', 2, 2, 'LKA', 56);
INSERT INTO `t_comment` VALUES (6, '管理员回复LKA', '2022-05-17 10:23:26', 5, 2, 'root', 56);
INSERT INTO `t_comment` VALUES (7, '管理员回复Sinn', '2022-05-17 10:23:37', 2, 2, 'root', 56);
INSERT INTO `t_comment` VALUES (8, '不错不错，测试测试', '2022-05-17 10:29:42', 4, 4, 'Sinn', 55);
INSERT INTO `t_comment` VALUES (9, '依我看，英梨梨才是天', '2022-05-17 10:30:07', -1, -1, 'Sinn', 55);
INSERT INTO `t_comment` VALUES (10, '我分享加藤惠，你支持err。想打架是吧？', '2022-05-17 10:30:45', 9, 9, 'LKA', 55);
INSERT INTO `t_comment` VALUES (11, '我觉得还是霞诗子好吧，一会我发几张霞诗子的', '2022-05-17 10:33:13', -1, -1, 'root', 55);
INSERT INTO `t_comment` VALUES (12, '沙发', '2022-05-17 10:39:17', -1, -1, 'root', 57);
INSERT INTO `t_comment` VALUES (13, '出言不逊，封号。', '2022-05-17 10:54:56', -1, -1, 'root', 58);

-- ----------------------------
-- Table structure for t_favorite
-- ----------------------------
DROP TABLE IF EXISTS `t_favorite`;
CREATE TABLE `t_favorite`  (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `user_id` bigint NOT NULL,
  `blog_id` bigint NOT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 4 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_favorite
-- ----------------------------
INSERT INTO `t_favorite` VALUES (1, 3, 48);
INSERT INTO `t_favorite` VALUES (3, 3, 50);
INSERT INTO `t_favorite` VALUES (6, 2, 55);
INSERT INTO `t_favorite` VALUES (7, 2, 56);
INSERT INTO `t_favorite` VALUES (8, 1, 55);
INSERT INTO `t_favorite` VALUES (9, 1, 57);

-- ----------------------------
-- Table structure for t_love
-- ----------------------------
DROP TABLE IF EXISTS `t_love`;
CREATE TABLE `t_love`  (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `user_id` bigint NOT NULL,
  `blog_id` bigint NOT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_love
-- ----------------------------
INSERT INTO `t_love` VALUES (1, 3, 50);
INSERT INTO `t_love` VALUES (2, 3, 51);
INSERT INTO `t_love` VALUES (3, 3, 52);
INSERT INTO `t_love` VALUES (4, 1, 52);
INSERT INTO `t_love` VALUES (8, 2, 55);
INSERT INTO `t_love` VALUES (9, 3, 56);
INSERT INTO `t_love` VALUES (10, 2, 56);
INSERT INTO `t_love` VALUES (11, 3, 55);

-- ----------------------------
-- Table structure for t_picture
-- ----------------------------
DROP TABLE IF EXISTS `t_picture`;
CREATE TABLE `t_picture`  (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `location` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `create_time` datetime NULL DEFAULT NULL,
  `blog_id` bigint NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 42 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_picture
-- ----------------------------
INSERT INTO `t_picture` VALUES (28, 'D35AE192-46A9-4EF0-88CC-621E73E6D644.jpg', '/image/D35AE192-46A9-4EF0-88CC-621E73E6D644.jpg', '2022-05-15 16:39:53', 51);
INSERT INTO `t_picture` VALUES (29, '078B6484-2968-476F-A058-63626C26D57E.jpg', '/image/078B6484-2968-476F-A058-63626C26D57E.jpg', '2022-05-15 16:39:53', 51);
INSERT INTO `t_picture` VALUES (30, '0A2F30E3-F5CE-413D-8D23-C7A774D81BC9.png', '/image/0A2F30E3-F5CE-413D-8D23-C7A774D81BC9.png', '2022-05-15 16:39:53', 51);
INSERT INTO `t_picture` VALUES (31, 'A1C5F33A-E07E-4A95-B519-884CEEEFAB8C.jpg', '/image/A1C5F33A-E07E-4A95-B519-884CEEEFAB8C.jpg', '2022-05-15 16:50:54', 52);
INSERT INTO `t_picture` VALUES (32, 'C653063F-49BC-47BB-B923-C8C707C80F55.jpg', '/image/C653063F-49BC-47BB-B923-C8C707C80F55.jpg', '2022-05-15 16:50:54', 52);
INSERT INTO `t_picture` VALUES (33, '90A8FD4B-A314-422D-AC4F-F05A916A9E85.jpg', '/image/90A8FD4B-A314-422D-AC4F-F05A916A9E85.jpg', '2022-05-15 16:50:54', 52);
INSERT INTO `t_picture` VALUES (34, '9B0DE97B-A94F-436B-BEEB-D74FC9B6FA3B.png', '/image/9B0DE97B-A94F-436B-BEEB-D74FC9B6FA3B.png', '2022-05-15 18:58:15', 40);
INSERT INTO `t_picture` VALUES (35, '93CA9C3F-5DF9-4DC5-9008-BF50D9658BB8.png', '/image/93CA9C3F-5DF9-4DC5-9008-BF50D9658BB8.png', '2022-05-15 22:50:16', 55);
INSERT INTO `t_picture` VALUES (36, 'BB7E8452-BEED-43C8-9205-09E19551D457.png', '/image/BB7E8452-BEED-43C8-9205-09E19551D457.png', '2022-05-15 22:50:16', 55);
INSERT INTO `t_picture` VALUES (37, '1A27CEEC-E9DB-4183-A01B-629B5F4E80BE.png', '/image/1A27CEEC-E9DB-4183-A01B-629B5F4E80BE.png', '2022-05-16 16:48:42', 56);
INSERT INTO `t_picture` VALUES (38, 'AFDBB1AA-AD6A-40AB-BE84-2C509E6C5900.jpg', '/image/AFDBB1AA-AD6A-40AB-BE84-2C509E6C5900.jpg', '2022-05-17 10:38:50', 57);
INSERT INTO `t_picture` VALUES (39, '9267B492-527B-4E8A-88C7-98A0EC09D7BE.jpg', '/image/9267B492-527B-4E8A-88C7-98A0EC09D7BE.jpg', '2022-05-17 10:38:50', 57);
INSERT INTO `t_picture` VALUES (40, '4721D0A7-3888-4E7A-8D0C-36FB298FFB90.jpg', '/image/4721D0A7-3888-4E7A-8D0C-36FB298FFB90.jpg', '2022-05-17 10:38:50', 57);
INSERT INTO `t_picture` VALUES (41, '6DB5AC4C-D94F-4FC7-95C0-96CD831CBD8C.jpg', '/image/6DB5AC4C-D94F-4FC7-95C0-96CD831CBD8C.jpg', '2022-05-17 10:38:50', 57);
INSERT INTO `t_picture` VALUES (42, '5D14017B-07A9-46A4-8FDA-B5CD61A77D78.jpg', '/image/5D14017B-07A9-46A4-8FDA-B5CD61A77D78.jpg', '2022-05-17 10:54:03', 58);

-- ----------------------------
-- Table structure for t_role
-- ----------------------------
DROP TABLE IF EXISTS `t_role`;
CREATE TABLE `t_role`  (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `role_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `note` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 3 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_role
-- ----------------------------
INSERT INTO `t_role` VALUES (1, 'ROLE_ADMIN', NULL);
INSERT INTO `t_role` VALUES (2, 'ROLE_USER', NULL);

-- ----------------------------
-- Table structure for t_user
-- ----------------------------
DROP TABLE IF EXISTS `t_user`;
CREATE TABLE `t_user`  (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `user_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `password` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `create_time` datetime NULL DEFAULT NULL,
  `status` bit(1) NULL DEFAULT b'1',
  `is_ban` int NULL DEFAULT 1,
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `t_user_user_name_uindex`(`user_name` ASC) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 23 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_user
-- ----------------------------
INSERT INTO `t_user` VALUES (1, 'root', '$2a$10$HuWKjuyjQ94qMSRAF8bdb.0LNPdM0sswztplT7hxuEpongdVCqa4K', '2022-05-06 20:28:00', b'1', 1);
INSERT INTO `t_user` VALUES (2, 'Sinn', '$2a$10$eMDC8WHKNVoOHzzoOK.Gp.ZhckTzVRj3fyrZTQR37lYM9.yAlA9pa', '2022-05-12 13:05:32', b'1', 1);
INSERT INTO `t_user` VALUES (3, 'LKA', '$2a$10$HuWKjuyjQ94qMSRAF8bdb.0LNPdM0sswztplT7hxuEpongdVCqa4K', '2022-05-12 19:19:42', b'1', 1);
INSERT INTO `t_user` VALUES (18, 'user111', '$2a$10$eMDC8WHKNVoOHzzoOK.Gp.ZhckTzVRj3fyrZTQR37lYM9.yAlA9pa', '2022-05-12 19:29:32', b'1', 1);
INSERT INTO `t_user` VALUES (19, 'user123', '$2a$10$.cu8oWu27kO58GO9r/tHP.240PGGJwEdfsSPILBE62e46a4tH.Aba', '2022-05-12 19:48:57', b'1', 1);
INSERT INTO `t_user` VALUES (21, 'xuye', '$2a$10$wzB4Vq5J/IeHGcIjCdUAKuZJCLtGpJigBj8ATtmEJ1jlr.oP2MqUa', '2022-05-12 22:56:14', b'1', 1);
INSERT INTO `t_user` VALUES (22, 'SinnAyane', '$2a$10$j5X0RJwPL2HdUrq3PhW2..BW.E3IeGThALfipkNezwvE7ZBKfR31C', '2022-05-13 22:27:51', b'1', 1);
INSERT INTO `t_user` VALUES (23, 'user01', '$2a$10$mhWz4TV/O.wc7jrgIshFZOZP4/Bw4AepFsQ33M6vOFFJGKov2dxya', '2022-05-17 10:48:29', b'1', 1);
INSERT INTO `t_user` VALUES (25, 'user02', '$2a$10$WDp7siXzC.TSOjLNyh9aFOgZoB8VagkfyrYeSA4iEMUzIJBrh6nrm', '2022-05-17 10:49:52', b'1', 1);
INSERT INTO `t_user` VALUES (26, 'user03', '$2a$10$1j.zofZ5y8s4Qt0QfJU6PuoiMb7NjDEWQSZVmoPjXd6QsByrnhy.S', '2022-05-17 10:50:30', b'1', 1);
INSERT INTO `t_user` VALUES (27, 'user100', '$2a$10$bizJJ0.wSmwAfI9LOGArZeBlLx3e9qjDD/wRMut/EYHxl3Z5xHMiS', '2022-05-17 10:53:28', b'0', 1);

-- ----------------------------
-- Table structure for t_user_role
-- ----------------------------
DROP TABLE IF EXISTS `t_user_role`;
CREATE TABLE `t_user_role`  (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `role_id` bigint NULL DEFAULT NULL,
  `user_id` bigint NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 7 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_user_role
-- ----------------------------
INSERT INTO `t_user_role` VALUES (1, 1, 1);
INSERT INTO `t_user_role` VALUES (2, 2, 1);
INSERT INTO `t_user_role` VALUES (3, 2, 2);
INSERT INTO `t_user_role` VALUES (4, 2, 3);
INSERT INTO `t_user_role` VALUES (5, 2, 21);
INSERT INTO `t_user_role` VALUES (6, 2, 22);
INSERT INTO `t_user_role` VALUES (7, 2, 23);
INSERT INTO `t_user_role` VALUES (8, 2, 25);
INSERT INTO `t_user_role` VALUES (9, 2, 26);
INSERT INTO `t_user_role` VALUES (10, 2, 27);

SET FOREIGN_KEY_CHECKS = 1;
