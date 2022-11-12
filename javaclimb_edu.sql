SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for edu_chapter
-- ----------------------------
DROP TABLE IF EXISTS `edu_chapter`;
CREATE TABLE `edu_chapter`  (
  `id` char(19) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '章节ID',
  `course_id` char(19) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '课程ID',
  `title` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '章节名称',
  `sort` int(10) UNSIGNED NOT NULL DEFAULT 0 COMMENT '显示排序',
  `gmt_create` datetime(0) NOT NULL COMMENT '创建时间',
  `gmt_modified` datetime(0) NOT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `idx_course_id`(`course_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '课程' ROW_FORMAT = Compact;

-- ----------------------------
-- Records of edu_chapter
-- ----------------------------
INSERT INTO `edu_chapter` VALUES ('1379985130034036737', '1379985044570898433', '第一张 前沿', 1, '2021-04-08 10:31:09', '2021-04-08 10:31:09');
INSERT INTO `edu_chapter` VALUES ('1379986518969417729', '1379985044570898433', '第二章', 2, '2021-04-08 10:36:40', '2021-04-08 10:36:40');
INSERT INTO `edu_chapter` VALUES ('1380500460737150977', '1380500424011825154', '第一张', 1, '2021-04-09 20:38:53', '2021-04-09 20:38:53');
INSERT INTO `edu_chapter` VALUES ('1380819240176627713', '1380819071246839810', '第一张', 1, '2021-04-10 17:45:36', '2021-04-10 17:45:36');
INSERT INTO `edu_chapter` VALUES ('1380819415632752641', '1380819071246839810', '第二章', 2, '2021-04-10 17:46:18', '2021-04-10 17:46:18');

-- ----------------------------
-- Table structure for edu_comment
-- ----------------------------
DROP TABLE IF EXISTS `edu_comment`;
CREATE TABLE `edu_comment`  (
  `id` char(19) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '讲师ID',
  `course_id` char(19) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '' COMMENT '课程id',
  `teacher_id` char(19) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '' COMMENT '讲师id',
  `member_id` char(19) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '' COMMENT '会员id',
  `nickname` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '会员昵称',
  `avatar` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '会员头像',
  `content` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '评论内容',
  `gmt_create` datetime(0) NOT NULL COMMENT '创建时间',
  `gmt_modified` datetime(0) NOT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `idx_course_id`(`course_id`) USING BTREE,
  INDEX `idx_teacher_id`(`teacher_id`) USING BTREE,
  INDEX `idx_member_id`(`member_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '评论' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of edu_comment
-- ----------------------------

-- ----------------------------
-- Table structure for edu_course
-- ----------------------------
DROP TABLE IF EXISTS `edu_course`;
CREATE TABLE `edu_course`  (
  `id` char(19) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '课程ID',
  `teacher_id` char(19) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '课程讲师ID',
  `subject_id` char(19) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '课程专业ID',
  `subject_parent_id` char(19) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '课程专业父级ID',
  `title` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '课程标题',
  `price` decimal(10, 2) UNSIGNED NOT NULL COMMENT '课程销售价格，设置为0则可免费观看',
  `lesson_num` int(10) UNSIGNED NOT NULL DEFAULT 0 COMMENT '总课时',
  `cover` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '课程封面图片路径',
  `buy_count` bigint(20) UNSIGNED NOT NULL DEFAULT 0 COMMENT '销售数量',
  `view_count` bigint(20) UNSIGNED NOT NULL DEFAULT 0 COMMENT '浏览数量',
  `version` bigint(20) UNSIGNED NOT NULL DEFAULT 1 COMMENT '乐观锁',
  `status` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT 'Draft' COMMENT '课程状态 Draft未发布  Normal已发布',
  `gmt_create` datetime(0) NOT NULL COMMENT '创建时间',
  `gmt_modified` datetime(0) NOT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `idx_title`(`title`) USING BTREE,
  INDEX `idx_subject_id`(`subject_id`) USING BTREE,
  INDEX `idx_teacher_id`(`teacher_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '课程' ROW_FORMAT = Compact;

-- ----------------------------
-- Records of edu_course
-- ----------------------------
INSERT INTO `edu_course` VALUES ('1379985044570898433', '2', '1379771872606007298', '1379771872362737666', 'java从入门到入土', 19.00, 30, 'https://arman-guli-edu.oss-cn-beijing.aliyuncs.com/cover/2021/04/08/f85a43fd-9793-44b0-ab15-3c2c31169ea2.jpeg', 1, 711, 1, 'Normal', '2021-04-08 10:30:48', '2021-10-12 20:48:54');
INSERT INTO `edu_course` VALUES ('1380490112076378114', '1', '1379771872878637057', '1379771872362737666', 'Python 机器学习', 249.90, 4, 'https://arman-guli-edu.oss-cn-beijing.aliyuncs.com/cover/2021/04/09/7eed7cc0-891b-426b-b7f6-3105b74bf7bf.jpg', 2, 19, 1, 'Normal', '2021-04-09 19:57:46', '2021-10-05 14:27:36');
INSERT INTO `edu_course` VALUES ('1380500424011825154', '10', '1379771873486811138', '1379771873063186433', 'Javascript', 0.00, 2, 'https://arman-guli-edu.oss-cn-beijing.aliyuncs.com/cover/2021/04/09/608ca091-e83d-476e-89bb-4656da4905dd.jpg', 0, 36, 1, 'Normal', '2021-04-09 20:38:44', '2021-10-12 20:48:08');
INSERT INTO `edu_course` VALUES ('1380819071246839810', '6', '1379771873243541505', '1379771873063186433', 'Head First HTML', 99.00, 10, 'https://arman-guli-edu.oss-cn-beijing.aliyuncs.com/cover/2021/04/10/aa108325-6c7e-45aa-a53e-47bfc457083c.jpeg', 1, 59, 1, 'Normal', '2021-04-10 17:44:56', '2021-10-05 14:19:52');

-- ----------------------------
-- Table structure for edu_course_collect
-- ----------------------------
DROP TABLE IF EXISTS `edu_course_collect`;
CREATE TABLE `edu_course_collect`  (
  `id` char(19) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '收藏ID',
  `course_id` char(19) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '课程讲师ID',
  `member_id` char(19) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '' COMMENT '课程专业ID',
  `gmt_create` datetime(0) NOT NULL COMMENT '创建时间',
  `gmt_modified` datetime(0) NOT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '课程收藏' ROW_FORMAT = Compact;

-- ----------------------------
-- Records of edu_course_collect
-- ----------------------------
INSERT INTO `edu_course_collect` VALUES ('1380074241252134913', '1379985044570898433', '1379767647461466114', '2021-04-08 16:25:14', '2021-04-08 16:25:14');
INSERT INTO `edu_course_collect` VALUES ('1380495537186291713', '1380490112076378114', '1380495321062199297', '2021-04-09 20:19:19', '2021-04-09 20:19:19');
INSERT INTO `edu_course_collect` VALUES ('1380820052068052994', '1380819071246839810', '1379767647461466114', '2021-04-10 17:48:49', '2021-04-10 17:48:49');

-- ----------------------------
-- Table structure for edu_course_description
-- ----------------------------
DROP TABLE IF EXISTS `edu_course_description`;
CREATE TABLE `edu_course_description`  (
  `id` char(19) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '课程ID',
  `description` text CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL COMMENT '课程简介',
  `gmt_create` datetime(0) NOT NULL COMMENT '创建时间',
  `gmt_modified` datetime(0) NOT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '课程简介' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of edu_course_description
-- ----------------------------
INSERT INTO `edu_course_description` VALUES ('1379985044570898433', '<h2>此课程专门为想学习Java技术的人而准备</h2>\n<p>&nbsp;</p>\n<p>但是粉丝地方</p>\n<p>sdfsdfsdfsdfsdfdsfsdfsdfsdfssdfsdf</p>\n<p>但是粉丝地方水电费水电费史蒂夫史蒂夫史蒂夫史蒂夫史蒂夫史蒂夫豆腐花是</p>', '2021-04-08 10:30:48', '2021-04-09 16:15:36');
INSERT INTO `edu_course_description` VALUES ('1380490112076378114', '<p>一个关于python入门和机器学习入门的课程</p>', '2021-04-09 19:57:46', '2021-04-09 19:57:46');
INSERT INTO `edu_course_description` VALUES ('1380500424011825154', '<p>一起学习JS</p>', '2021-04-09 20:38:44', '2021-04-09 20:38:44');
INSERT INTO `edu_course_description` VALUES ('1380819071246839810', '<p>带你入门前端最好的课程</p>', '2021-04-10 17:44:56', '2021-04-23 10:15:05');

-- ----------------------------
-- Table structure for edu_subject
-- ----------------------------
DROP TABLE IF EXISTS `edu_subject`;
CREATE TABLE `edu_subject`  (
  `id` char(19) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '课程类别ID',
  `title` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '类别名称',
  `parent_id` char(19) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '0' COMMENT '父ID',
  `sort` int(10) UNSIGNED NOT NULL DEFAULT 0 COMMENT '排序字段',
  `gmt_create` datetime(0) NOT NULL COMMENT '创建时间',
  `gmt_modified` datetime(0) NOT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `idx_parent_id`(`parent_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '课程科目' ROW_FORMAT = Compact;

-- ----------------------------
-- Records of edu_subject
-- ----------------------------
INSERT INTO `edu_subject` VALUES ('1379771872362737666', '后端开发', '0', 0, '2021-04-07 20:23:44', '2021-04-07 20:23:44');
INSERT INTO `edu_subject` VALUES ('1379771872606007298', 'Java', '1379771872362737666', 0, '2021-04-07 20:23:44', '2021-04-07 20:23:44');
INSERT INTO `edu_subject` VALUES ('1379771872878637057', 'Python', '1379771872362737666', 0, '2021-04-07 20:23:44', '2021-04-07 20:23:44');
INSERT INTO `edu_subject` VALUES ('1379771873063186433', '前端开发', '0', 0, '2021-04-07 20:23:44', '2021-04-07 20:23:44');
INSERT INTO `edu_subject` VALUES ('1379771873243541505', 'HTML/CSS', '1379771873063186433', 0, '2021-04-07 20:23:44', '2021-04-07 20:23:44');
INSERT INTO `edu_subject` VALUES ('1379771873486811138', 'JavaScript', '1379771873063186433', 0, '2021-04-07 20:23:44', '2021-04-07 20:23:44');
INSERT INTO `edu_subject` VALUES ('1379771873667166210', '编程语言', '0', 0, '2021-04-07 20:23:44', '2021-04-07 20:23:44');
INSERT INTO `edu_subject` VALUES ('1379771873860104194', 'Java', '1379771873667166210', 0, '2021-04-07 20:23:44', '2021-04-07 20:23:44');
INSERT INTO `edu_subject` VALUES ('1379773570154360834', '日记', '0', 0, '2021-04-07 20:30:29', '2021-04-07 20:30:29');
INSERT INTO `edu_subject` VALUES ('1379773570380853250', 'test', '1379773570154360834', 0, '2021-04-07 20:30:29', '2021-04-07 20:30:29');

-- ----------------------------
-- Table structure for edu_teacher
-- ----------------------------
DROP TABLE IF EXISTS `edu_teacher`;
CREATE TABLE `edu_teacher`  (
  `id` char(19) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '讲师ID',
  `name` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '讲师姓名',
  `intro` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '' COMMENT '讲师简介',
  `career` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '讲师资历,一句话说明讲师',
  `level` int(10) UNSIGNED NOT NULL COMMENT '头衔 1高级讲师 2首席讲师',
  `avatar` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '讲师头像',
  `sort` int(10) UNSIGNED NOT NULL DEFAULT 0 COMMENT '排序',
  `join_date` date NULL DEFAULT NULL COMMENT '入驻时间',
  `is_deleted` tinyint(3) UNSIGNED NOT NULL DEFAULT 0 COMMENT '逻辑删除 1（true）已删除， 0（false）未删除',
  `gmt_create` datetime(0) NOT NULL COMMENT '创建时间',
  `gmt_modified` datetime(0) NOT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `uk_name`(`name`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '讲师' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of edu_teacher
-- ----------------------------
INSERT INTO `edu_teacher` VALUES ('1', '刘德华', '毕业于师范大学数学系，热爱教育事业，执教数学思维6年有余', '具备深厚的数学思维功底、丰富的小学教育经验，授课风格生动活泼，擅长用形象生动的比喻帮助理解、简单易懂的语言讲解难题，深受学生喜欢', 2, 'https://arman-guli-edu.oss-cn-beijing.aliyuncs.com/avatar/2021/04/23/7d048fd7-02b3-4faf-b7fe-b8909959e599.jpg', 10, '2019-10-27', 0, '2018-03-30 17:15:57', '2021-04-23 08:58:17');
INSERT INTO `edu_teacher` VALUES ('10', '唐嫣', '北京师范大学法学院副教授', '北京师范大学法学院副教授、清华大学法学博士。自2004年至今已有9年的司法考试培训经验。长期从事司法考试辅导，深知命题规律，了解解题技巧。内容把握准确，授课重点明确，层次分明，调理清晰，将法条法理与案例有机融合，强调综合，深入浅出。', 1, 'https://arman-guli-edu.oss-cn-beijing.aliyuncs.com/avatar/2021/04/23/c9dd13bc-b07e-4a60-83ad-3dc3493fb358.jpg', 20, '2019-10-27', 0, '2018-04-03 14:32:19', '2021-04-08 10:28:45');
INSERT INTO `edu_teacher` VALUES ('2', '周润发', '中国人民大学附属中学数学一级教师', '中国科学院数学与系统科学研究院应用数学专业博士，研究方向为数字图像处理，中国工业与应用数学学会会员。参与全国教育科学“十五”规划重点课题“信息化进程中的教育技术发展研究”的子课题“基与课程改革的资源开发与应用”，以及全国“十五”科研规划全国重点项目“掌上型信息技术产品在教学中的运用和开发研究”的子课题“用技术学数学”。', 2, 'https://arman-guli-edu.oss-cn-beijing.aliyuncs.com/avatar/2021/04/23/abfd4fd8-91ad-45ac-aacd-e1136bd7f7c0.jpg', 1, '2019-10-26', 0, '2018-03-30 18:28:26', '2021-04-23 08:57:53');
INSERT INTO `edu_teacher` VALUES ('3', '钟汉良', '钟汉良钟汉良钟汉良钟汉良', '中教一级职称。讲课极具亲和力。', 1, 'https://arman-guli-edu.oss-cn-beijing.aliyuncs.com/avatar/2021/04/23/5f64458d-3483-4cab-acb3-104ce88ef49a.jpg', 2, '2019-10-27', 0, '2018-03-31 09:20:46', '2021-04-23 08:59:09');
INSERT INTO `edu_teacher` VALUES ('4', '周杰伦', '长期从事考研政治课讲授和考研命题趋势与应试对策研究。考研辅导新锐派的代表。', '政治学博士、管理学博士后，北京师范大学马克思主义学院副教授。多年来总结出了一套行之有效的应试技巧与答题方法，针对性和实用性极强，能帮助考生在轻松中应考，在激励的竞争中取得高分，脱颖而出。', 1, 'https://arman-guli-edu.oss-cn-beijing.aliyuncs.com/avatar/2021/04/23/efb66cbe-f689-4fbf-836e-b1a8b9e24274.jpg', 1, '2019-10-25', 0, '2018-04-03 14:13:51', '2021-04-23 08:58:34');
INSERT INTO `edu_teacher` VALUES ('5', '陈伟霆', '人大附中2009届毕业生', '北京大学数学科学学院2008级本科生，2012年第八届学生五四奖章获得者，在数学领域取得多项国际国内奖项，学术研究成绩突出。曾被两次评为北京大学三好学生、一次评为北京大学三好标兵，获得过北京大学国家奖学金、北京大学廖凯原奖学金、北京大学星光国际一等奖学金、北京大学明德新生奖学金等。', 1, 'https://arman-guli-edu.oss-cn-beijing.aliyuncs.com/avatar/2021/04/23/6e1d92ba-9aa6-4d12-98e2-5144c441c11c.jpg', 1, '2019-10-27', 0, '2018-04-03 14:15:36', '2021-04-23 08:58:52');
INSERT INTO `edu_teacher` VALUES ('6', '姚晨', '华东师范大学数学系硕士生导师，中国数学奥林匹克高级教练', '曾参与北京市及全国多项数学活动的命题和组织工作，多次带领北京队参加高中、初中、小学的各项数学竞赛，均取得优异成绩。教学活而新，能够调动学生的学习兴趣并擅长对学生进行思维点拨，对学生学习习惯的养成和非智力因素培养有独到之处，是一位深受学生喜爱的老师。', 1, 'https://arman-guli-edu.oss-cn-beijing.aliyuncs.com/avatar/2021/04/23/c9dd13bc-b07e-4a60-83ad-3dc3493fb358.jpg', 1, '2019-10-28', 0, '2018-04-01 14:19:28', '2021-04-08 10:26:46');
INSERT INTO `edu_teacher` VALUES ('7', '胡歌', '考研政治辅导实战派专家，全国考研政治命题研究组核心成员。', '法学博士，北京师范大学马克思主义学院副教授，专攻毛泽东思想概论、邓小平理论，长期从事考研辅导。出版著作两部，发表学术论文30余篇，主持国家社会科学基金项目和教育部重大课题子课题各一项，参与中央实施马克思主义理论研究和建设工程项目。', 2, 'https://arman-guli-edu.oss-cn-beijing.aliyuncs.com/avatar/2021/04/23/a430ef65-2d16-409d-a9c2-e6e5445f75bb.jpg', 8, '2019-09-02', 0, '2018-04-03 14:21:03', '2021-04-23 08:59:26');
INSERT INTO `edu_teacher` VALUES ('8', '谢娜', '资深课程设计专家，专注10年AACTP美国培训协会认证导师', '十年课程研发和培训咨询经验，曾任国企人力资源经理、大型外企培训经理，负责企业大学和培训体系搭建；曾任专业培训机构高级顾问、研发部总监，为包括广东移动、东莞移动、深圳移动、南方电网、工商银行、农业银行、民生银行、邮储银行、TCL集团、清华大学继续教育学院、中天路桥、广西扬翔股份等超过200家企业提供过培训与咨询服务，并担任近50个大型项目的总负责人。', 1, 'https://arman-guli-edu.oss-cn-beijing.aliyuncs.com/avatar/2021/04/23/c9dd13bc-b07e-4a60-83ad-3dc3493fb358.jpg', 10, '2019-10-26', 0, '2018-04-03 14:23:33', '2021-04-23 09:01:46');

-- ----------------------------
-- Table structure for edu_video
-- ----------------------------
DROP TABLE IF EXISTS `edu_video`;
CREATE TABLE `edu_video`  (
  `id` char(19) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '视频ID',
  `course_id` char(19) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '课程ID',
  `chapter_id` char(19) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '章节ID',
  `title` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '节点名称',
  `video_source_id` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '云端视频资源',
  `video_original_name` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '原始文件名称',
  `sort` int(10) UNSIGNED NOT NULL DEFAULT 0 COMMENT '排序字段',
  `play_count` bigint(20) UNSIGNED NOT NULL DEFAULT 0 COMMENT '播放次数',
  `is_free` tinyint(3) UNSIGNED NOT NULL DEFAULT 0 COMMENT '是否可以试听：0收费 1免费',
  `duration` float NOT NULL DEFAULT 0 COMMENT '视频时长（秒）',
  `status` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT 'Empty' COMMENT '状态',
  `size` bigint(20) UNSIGNED NOT NULL DEFAULT 0 COMMENT '视频源文件大小（字节）',
  `version` bigint(20) UNSIGNED NOT NULL DEFAULT 1 COMMENT '乐观锁',
  `gmt_create` datetime(0) NOT NULL COMMENT '创建时间',
  `gmt_modified` datetime(0) NOT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `idx_course_id`(`course_id`) USING BTREE,
  INDEX `idx_chapter_id`(`chapter_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '课程视频' ROW_FORMAT = Compact;

-- ----------------------------
-- Records of edu_video
-- ----------------------------
INSERT INTO `edu_video` VALUES ('1379986461729751042', '1379985044570898433', '1379985130034036737', '第一节 读一下', '8304efab66e441cea17f7fc8630a16d3', 'aaa.mp4', 1, 0, 0, 0, 'Empty', 0, 1, '2021-04-08 10:36:26', '2021-04-08 10:36:26');
INSERT INTO `edu_video` VALUES ('1379986607481815041', '1379985044570898433', '1379986518969417729', '第一节 入土', 'd7bdf7f0625841abbfa678a2d4113eeb', 'abc.mp4', 1, 0, 0, 0, 'Empty', 0, 1, '2021-04-08 10:37:01', '2021-04-08 10:37:01');
INSERT INTO `edu_video` VALUES ('1380500540722528258', '1380500424011825154', '1380500460737150977', '第一节', '6df682721a9b44fa949227773f1aa68b', 'abc.mp4', 0, 0, 0, 0, 'Empty', 0, 1, '2021-04-09 20:39:12', '2021-04-09 20:39:12');
INSERT INTO `edu_video` VALUES ('1380819365032669185', '1380819071246839810', '1380819240176627713', '第一街', '99ebde2e4882429fb102afd22987e864', 'aaa.mp4', 1, 0, 1, 0, 'Empty', 0, 1, '2021-04-10 17:46:06', '2021-04-10 17:46:06');
INSERT INTO `edu_video` VALUES ('1380819460683771905', '1380819071246839810', '1380819415632752641', '第一节', '3cea8bd080634cb78eee436817fafe97', 'abc.mp4', 0, 0, 0, 0, 'Empty', 0, 1, '2021-04-10 17:46:28', '2021-04-10 17:46:40');

SET FOREIGN_KEY_CHECKS = 1;
