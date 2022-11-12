SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for trade_order
-- ----------------------------
DROP TABLE IF EXISTS `trade_order`;
CREATE TABLE `trade_order`  (
  `id` char(19) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '',
  `order_no` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '' COMMENT '订单号',
  `course_id` varchar(19) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '' COMMENT '课程id',
  `course_title` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '课程名称',
  `course_cover` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '课程封面',
  `teacher_name` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '讲师名称',
  `member_id` varchar(19) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '' COMMENT '会员id',
  `nickname` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '会员昵称',
  `mobile` varchar(11) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '会员手机',
  `total_fee` decimal(20, 2) NULL DEFAULT NULL COMMENT '订单金额（分）',
  `pay_type` tinyint(4) NULL DEFAULT NULL COMMENT '支付类型（1：微信 2：支付宝）',
  `status` tinyint(4) NULL DEFAULT NULL COMMENT '订单状态（0：未支付 1：已支付）',
  `is_deleted` tinyint(3) UNSIGNED NOT NULL DEFAULT 0 COMMENT '逻辑删除 1（true）已删除， 0（false）未删除',
  `gmt_create` datetime(0) NOT NULL COMMENT '创建时间',
  `gmt_modified` datetime(0) NOT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `ux_order_no`(`order_no`) USING BTREE,
  INDEX `idx_course_id`(`course_id`) USING BTREE,
  INDEX `idx_member_id`(`member_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '订单' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of trade_order
-- ----------------------------
INSERT INTO `trade_order` VALUES ('1380476424342806529', '20210409190322097', '1379985044570898433', 'java从入门到入土', 'https://arman-guli-edu.oss-cn-beijing.aliyuncs.com/cover/2021/04/08/f85a43fd-9793-44b0-ab15-3c2c31169ea2.jpeg', '周润发', '1379767647461466114', 'testuser', '13333333333', 19.00, 1, 1, 0, '2021-04-09 19:03:22', '2021-04-09 19:03:49');
INSERT INTO `trade_order` VALUES ('1380491388453421058', '20210409200249135', '1380490112076378114', 'Python 机器学习', 'https://arman-guli-edu.oss-cn-beijing.aliyuncs.com/cover/2021/04/09/7eed7cc0-891b-426b-b7f6-3105b74bf7bf.jpg', '刘德华', '1379767647461466114', 'testuser', '13333333333', 249.00, 1, 1, 0, '2021-04-09 20:02:50', '2021-04-09 20:03:24');
INSERT INTO `trade_order` VALUES ('1380495545725882370', '20210409201921779', '1380490112076378114', 'Python 机器学习', 'https://arman-guli-edu.oss-cn-beijing.aliyuncs.com/cover/2021/04/09/7eed7cc0-891b-426b-b7f6-3105b74bf7bf.jpg', '刘德华', '1380495321062199297', 'arman', '15088888888', 249.00, 1, 1, 0, '2021-04-09 20:19:21', '2021-04-09 20:19:44');
INSERT INTO `trade_order` VALUES ('1380496661347831809', '20210409202347421', '1379985044570898433', 'java从入门到入土', 'https://arman-guli-edu.oss-cn-beijing.aliyuncs.com/cover/2021/04/08/f85a43fd-9793-44b0-ab15-3c2c31169ea2.jpeg', '周润发', '1380495321062199297', 'arman', '15522323750', 19.00, 1, 1, 0, '2021-04-09 20:23:47', '2021-04-09 20:24:14');
INSERT INTO `trade_order` VALUES ('1380823816795713537', '20210410180347564', '1380819071246839810', 'Head First HTML', 'https://arman-guli-edu.oss-cn-beijing.aliyuncs.com/cover/2021/04/10/aa108325-6c7e-45aa-a53e-47bfc457083c.jpeg', '姚晨', '1379767647461466114', 'testuser', '13333333333', 99.00, 1, 1, 0, '2021-04-10 18:03:47', '2021-04-10 18:35:36');
INSERT INTO `trade_order` VALUES ('1385416646159544322', '20210423101402478', '1380819071246839810', 'Head First HTML', 'https://arman-guli-edu.oss-cn-beijing.aliyuncs.com/cover/2021/04/10/aa108325-6c7e-45aa-a53e-47bfc457083c.jpeg', '姚晨', '1380495321062199297', 'arman', '15088888888', 99.00, 1, 0, 0, '2021-04-23 10:14:03', '2021-04-23 10:14:03');
INSERT INTO `trade_order` VALUES ('1445273755588329473', '20211005142449715', '1379985044570898433', 'java从入门到入土', 'https://arman-guli-edu.oss-cn-beijing.aliyuncs.com/cover/2021/04/08/f85a43fd-9793-44b0-ab15-3c2c31169ea2.jpeg', '周润发', '1445273546070212610', '王汉远', '15088888888', 19.00, 1, 0, 0, '2021-10-05 14:24:50', '2021-10-05 14:24:50');
INSERT INTO `trade_order` VALUES ('1445274469454675969', '20211005142740070', '1380490112076378114', 'Python 机器学习', 'https://arman-guli-edu.oss-cn-beijing.aliyuncs.com/cover/2021/04/09/7eed7cc0-891b-426b-b7f6-3105b74bf7bf.jpg', '刘德华', '1445273546070212610', '王汉远', '15088888888', 249.00, 1, 0, 0, '2021-10-05 14:27:40', '2021-10-05 14:27:40');

-- ----------------------------
-- Table structure for trade_pay_log
-- ----------------------------
DROP TABLE IF EXISTS `trade_pay_log`;
CREATE TABLE `trade_pay_log`  (
  `id` char(19) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '',
  `order_no` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '' COMMENT '订单号',
  `pay_time` datetime(0) NULL DEFAULT NULL COMMENT '支付完成时间',
  `total_fee` bigint(20) NULL DEFAULT NULL COMMENT '支付金额（分）',
  `transaction_id` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '交易流水号',
  `trade_state` char(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '交易状态',
  `pay_type` tinyint(4) NOT NULL DEFAULT 0 COMMENT '支付类型（1：微信 2：支付宝）',
  `attr` text CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL COMMENT '其他属性',
  `is_deleted` tinyint(3) UNSIGNED NOT NULL DEFAULT 0 COMMENT '逻辑删除 1（true）已删除， 0（false）未删除',
  `gmt_create` datetime(0) NOT NULL COMMENT '创建时间',
  `gmt_modified` datetime(0) NOT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `uk_order_no`(`order_no`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '支付日志表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of trade_pay_log
-- ----------------------------
INSERT INTO `trade_pay_log` VALUES ('1380476535848378370', '20210409190322097', '2021-04-09 19:03:49', 19, '2021040922001475580501398990', 'TRADE_SUCCESS', 2, '{\"gmt_create\":[\"2021-04-09 19:03:38\"],\"charset\":[\"utf-8\"],\"seller_email\":[\"pqbkci2226@sandbox.com\"],\"subject\":[\"java从入门到入土\"],\"sign\":[\"KbK+Q1oZUc0d5O3fcAuZ13eBMm/DGq6xCB5YhRY8MY7CxfzihPzfAh6zF2kb7NGhjqYmIEtpaMJKC6JxVgzjKz+hQOJWt3mQbExrRcyy4Q310LG3nqYlTuoK9Y6vXjDesG/+rgUuZYplfMm0d0BmizZ5YvA9EiKV7krS/QQIzOYs6ot8EL+niubMkoRhRXcs2zoAewBVVfS//IEUZWTIFrORe2wbexg3vHRTZ43HKPBlQjXYRbyr2WxMZS/KSobd3+AFlRQL52sqHw2FeABjOxxG9LYwYg5J+OB6Z5ueiuC/cLtM0NSvEYKj4LxyIsv/JzPQ3VL9a6o84SAUshCQ9w\\u003d\\u003d\"],\"buyer_id\":[\"2088622955675584\"],\"invoice_amount\":[\"19.00\"],\"notify_id\":[\"2021040900222190346075580513832123\"],\"fund_bill_list\":[\"[{\\\"amount\\\":\\\"19.00\\\",\\\"fundChannel\\\":\\\"ALIPAYACCOUNT\\\"}]\"],\"notify_type\":[\"trade_status_sync\"],\"trade_status\":[\"TRADE_SUCCESS\"],\"receipt_amount\":[\"19.00\"],\"buyer_pay_amount\":[\"19.00\"],\"app_id\":[\"2021000117634607\"],\"sign_type\":[\"RSA2\"],\"seller_id\":[\"2088621955516754\"],\"gmt_payment\":[\"2021-04-09 19:03:46\"],\"notify_time\":[\"2021-04-09 19:03:47\"],\"version\":[\"1.0\"],\"out_trade_no\":[\"20210409190322097\"],\"total_amount\":[\"19.00\"],\"trade_no\":[\"2021040922001475580501398990\"],\"auth_app_id\":[\"2021000117634607\"],\"buyer_logon_id\":[\"wrs***@sandbox.com\"],\"point_amount\":[\"0.00\"]}', 0, '2021-04-09 19:03:49', '2021-04-09 19:03:49');
INSERT INTO `trade_pay_log` VALUES ('1380491530728407042', '20210409200249135', '2021-04-09 20:03:24', 249, '2021040922001475580501398693', 'TRADE_SUCCESS', 2, '{\"gmt_create\":[\"2021-04-09 20:03:04\"],\"charset\":[\"utf-8\"],\"seller_email\":[\"pqbkci2226@sandbox.com\"],\"subject\":[\"Python 机器学习\"],\"sign\":[\"Dcx1It1KsawRHLK8jTZCJS66/q+8GP07FuX0nBcAWLQu7GmnsPXApFXN2mbR8bvwbawQ3ZBM/kSAMXJoBWDvdrkqAtp6JwsO+DoelpY+B6tOLyA7PQ9vz6R1CCmLcZt3pX7j8jkDWObDA6OEsp1JhMhptGgxnEpaIYr/VFVPPQL4IAu2Ici0g/vjnUW+TGxVTBE/iny7Ec5VctngnK/2MKPP7VNGFYA9SW9Ku3nz+IMZK8WRSHzbYsF7Qj5dVPela/0EoUOL1uobTK6gAKqoaoiovuKVUWhsc1vRScO9Mz5T+zZjY275cwMG5nRZeXdUjisQCYzMlz1q3P+jGBfXKg\\u003d\\u003d\"],\"buyer_id\":[\"2088622955675584\"],\"invoice_amount\":[\"249.00\"],\"notify_id\":[\"2021040900222200322075580513813598\"],\"fund_bill_list\":[\"[{\\\"amount\\\":\\\"249.00\\\",\\\"fundChannel\\\":\\\"ALIPAYACCOUNT\\\"}]\"],\"notify_type\":[\"trade_status_sync\"],\"trade_status\":[\"TRADE_SUCCESS\"],\"receipt_amount\":[\"249.00\"],\"buyer_pay_amount\":[\"249.00\"],\"app_id\":[\"2021000117634607\"],\"sign_type\":[\"RSA2\"],\"seller_id\":[\"2088621955516754\"],\"gmt_payment\":[\"2021-04-09 20:03:21\"],\"notify_time\":[\"2021-04-09 20:03:22\"],\"version\":[\"1.0\"],\"out_trade_no\":[\"20210409200249135\"],\"total_amount\":[\"249.00\"],\"trade_no\":[\"2021040922001475580501398693\"],\"auth_app_id\":[\"2021000117634607\"],\"buyer_logon_id\":[\"wrs***@sandbox.com\"],\"point_amount\":[\"0.00\"]}', 0, '2021-04-09 20:03:24', '2021-04-09 20:03:24');
INSERT INTO `trade_pay_log` VALUES ('1380495641389568002', '20210409201921779', '2021-04-09 20:19:44', 249, '2021040922001475580501398808', 'TRADE_SUCCESS', 2, '{\"gmt_create\":[\"2021-04-09 20:19:32\"],\"charset\":[\"utf-8\"],\"seller_email\":[\"pqbkci2226@sandbox.com\"],\"subject\":[\"Python 机器学习\"],\"sign\":[\"ANf7cYnlBZkZVDE6+IhTrFCwu2uLmyCLiyfsd2GVUtg3o3ShKrS5BIrGrKXlHwkhzdR4BjqZSaudH4iWy0ZpwpC9qcy23U4cnzr5zMPKLnhpy7nJNJOBYeZOwfjFubJhPvTDlJtMAtiriv6mNWp8jgH2WAV5Qz/UZ+PPGLuhdyJAz91no2P6mVLaijyzm5faFee+5nqpa/ki37j1vTjYwrwRkpeDlN98iiuI/wzy5KUZuXzRffiUxH16sSBVokrobguLii2i3fftnEPDJG6yvShkGz7v/Nw9mV9oj3O3kbNBdRjIAEcWVXuTVcLBeV37c++RWvmIcGIulGKtRF4VMA\\u003d\\u003d\"],\"buyer_id\":[\"2088622955675584\"],\"invoice_amount\":[\"249.00\"],\"notify_id\":[\"2021040900222201942075580513830307\"],\"fund_bill_list\":[\"[{\\\"amount\\\":\\\"249.00\\\",\\\"fundChannel\\\":\\\"ALIPAYACCOUNT\\\"}]\"],\"notify_type\":[\"trade_status_sync\"],\"trade_status\":[\"TRADE_SUCCESS\"],\"receipt_amount\":[\"249.00\"],\"buyer_pay_amount\":[\"249.00\"],\"app_id\":[\"2021000117634607\"],\"sign_type\":[\"RSA2\"],\"seller_id\":[\"2088621955516754\"],\"gmt_payment\":[\"2021-04-09 20:19:41\"],\"notify_time\":[\"2021-04-09 20:19:42\"],\"version\":[\"1.0\"],\"out_trade_no\":[\"20210409201921779\"],\"total_amount\":[\"249.00\"],\"trade_no\":[\"2021040922001475580501398808\"],\"auth_app_id\":[\"2021000117634607\"],\"buyer_logon_id\":[\"wrs***@sandbox.com\"],\"point_amount\":[\"0.00\"]}', 0, '2021-04-09 20:19:44', '2021-04-09 20:19:44');
INSERT INTO `trade_pay_log` VALUES ('1380496775453872130', '20210409202347421', '2021-04-09 20:24:14', 19, '2021040922001475580501398992', 'TRADE_SUCCESS', 2, '{\"gmt_create\":[\"2021-04-09 20:24:04\"],\"charset\":[\"utf-8\"],\"seller_email\":[\"pqbkci2226@sandbox.com\"],\"subject\":[\"java从入门到入土\"],\"sign\":[\"Sb13OmN3GmVEBoTvkhIfQnsvyTVMafnQJFodpeF6xZuI5+dqeAOWncElyUzbK8ZEJSs2/61aT4/fMc3zHALFRlmMFXgdPC8twIlp1qwK5f42lOZdmWL2b2O2gN6wjGqtYdYGwnJgfUsue9SL6wkp4x10sX1DHvkMRupKpQHNi5esAB3Pz/jOLEXLFIa15LV9w7ktSqrTVaXvJX+t7mZmogrIktLignmvwmXDeGH/8o/p4AugOUd8Q8CQ/DnbEaWX82wUw1m9FxeVAo4HjtFwbyW/zHdI/TEfBFEXR55DDj9Fr1j4HKKzorSiL416FlbvXCDhxA4o4yDf4F9wiiETlw\\u003d\\u003d\"],\"buyer_id\":[\"2088622955675584\"],\"invoice_amount\":[\"19.00\"],\"notify_id\":[\"2021040900222202411075580513813599\"],\"fund_bill_list\":[\"[{\\\"amount\\\":\\\"19.00\\\",\\\"fundChannel\\\":\\\"ALIPAYACCOUNT\\\"}]\"],\"notify_type\":[\"trade_status_sync\"],\"trade_status\":[\"TRADE_SUCCESS\"],\"receipt_amount\":[\"19.00\"],\"buyer_pay_amount\":[\"19.00\"],\"app_id\":[\"2021000117634607\"],\"sign_type\":[\"RSA2\"],\"seller_id\":[\"2088621955516754\"],\"gmt_payment\":[\"2021-04-09 20:24:10\"],\"notify_time\":[\"2021-04-09 20:24:11\"],\"version\":[\"1.0\"],\"out_trade_no\":[\"20210409202347421\"],\"total_amount\":[\"19.00\"],\"trade_no\":[\"2021040922001475580501398992\"],\"auth_app_id\":[\"2021000117634607\"],\"buyer_logon_id\":[\"wrs***@sandbox.com\"],\"point_amount\":[\"0.00\"]}', 0, '2021-04-09 20:24:14', '2021-04-09 20:24:14');
INSERT INTO `trade_pay_log` VALUES ('1380831823558471682', '20210410180347564', '2021-04-10 18:35:36', 99, '2021041022001475580501399700', 'TRADE_SUCCESS', 2, '{\"gmt_create\":[\"2021-04-10 18:35:19\"],\"charset\":[\"utf-8\"],\"seller_email\":[\"pqbkci2226@sandbox.com\"],\"subject\":[\"Head First HTML\"],\"sign\":[\"oT35zfqqWTwpZpx7MRtZO2IWwLuAO7RDkx+1dmLZfz4/T9ZIWwC9gP4fytgfiYgc2PH4u4P9kS0GC9gmnOeTsNzau3Ij0Cf/R4BHX7QLuWI3X/vzUXC/FA1IKQsWslls6Ps0pPGe5lnYHz3uuHShGjVJ71CsEUAV1T5I3nRANtA92FzkPGeDvvy+4SnlZ18rZ5/VPDO8HG0hguqomjkKGs17lpUX8CFyiTbBq33fau6bv9wj153hWn8g1SXvZ9VW0CgTEbrw5MO9j9sxepcC3UJ7HFTTDwTXuFHQvmAkShR9iTQiEOSvZJUOy/sg62t7sIc6ztrV1E6YqUx6fkb+MA\\u003d\\u003d\"],\"buyer_id\":[\"2088622955675584\"],\"invoice_amount\":[\"99.00\"],\"notify_id\":[\"2021041000222183533075580513849521\"],\"fund_bill_list\":[\"[{\\\"amount\\\":\\\"99.00\\\",\\\"fundChannel\\\":\\\"ALIPAYACCOUNT\\\"}]\"],\"notify_type\":[\"trade_status_sync\"],\"trade_status\":[\"TRADE_SUCCESS\"],\"receipt_amount\":[\"99.00\"],\"buyer_pay_amount\":[\"99.00\"],\"app_id\":[\"2021000117634607\"],\"sign_type\":[\"RSA2\"],\"seller_id\":[\"2088621955516754\"],\"gmt_payment\":[\"2021-04-10 18:35:32\"],\"notify_time\":[\"2021-04-10 18:35:34\"],\"version\":[\"1.0\"],\"out_trade_no\":[\"20210410180347564\"],\"total_amount\":[\"99.00\"],\"trade_no\":[\"2021041022001475580501399700\"],\"auth_app_id\":[\"2021000117634607\"],\"buyer_logon_id\":[\"wrs***@sandbox.com\"],\"point_amount\":[\"0.00\"]}', 0, '2021-04-10 18:35:36', '2021-04-10 18:35:36');

SET FOREIGN_KEY_CHECKS = 1;
