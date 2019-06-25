/*
 Navicat Premium Data Transfer

 Source Server         : webstore
 Source Server Type    : MySQL
 Source Server Version : 80015
 Source Host           : localhost:3306
 Source Schema         : open_account

 Target Server Type    : MySQL
 Target Server Version : 80015
 File Encoding         : 65001

 Date: 24/06/2019 15:31:56
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for account_info
-- ----------------------------
DROP TABLE IF EXISTS `account_info`;
CREATE TABLE `account_info`  (
  `account_info_id` int(10) UNSIGNED ZEROFILL NOT NULL AUTO_INCREMENT,
  `user_id` int(10) NOT NULL,
  `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
  `id_type` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
  `id_number` varchar(18) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
  `id_address_id` int(10) DEFAULT NULL,
  `id_issuance_date` datetime(0) DEFAULT NULL,
  `id_overdue_date` datetime(0) DEFAULT NULL,
  `id_licensing_authority` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
  `contact_address_id` int(10) DEFAULT NULL,
  `postal_address_id` int(10) DEFAULT NULL,
  `trans_password` varchar(12) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
  `fund_password` varchar(12) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
  `n_security_id` int(10) DEFAULT NULL,
  `s_security_id` int(10) DEFAULT NULL,
  `deposit_bank` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
  `deposit_account` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
  `deposit_password` varchar(12) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
  `status` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
  `profession` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
  `education` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
  `email` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
  `id_picture` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
  `id_card_inverse_side` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
  `heashot` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
  `risk_assessment_mark` int(3) DEFAULT NULL,
  PRIMARY KEY (`account_info_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 26 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of account_info
-- ----------------------------
INSERT INTO `account_info` VALUES (0000000002, 7000, 'lastgg', ' 40', ' 2', NULL, '2018-01-01 00:00:00', '2018-01-01 00:00:00', '2', NULL, NULL, ' 2', '2', NULL, NULL, '2', '2', '2 ', '2', '2', '2', '2', '2', '2', NULL, 90);
INSERT INTO `account_info` VALUES (0000000003, 8000, ' fdsnk', ' 40', ' 2', 82, '2018-01-01 05:11:12', '2018-01-01 05:11:14', '2', 83, 84, '2', '2', 2, 2, '2', '2', '2 ', '2', ' 2', '2', '2', '2', '2', NULL, NULL);
INSERT INTO `account_info` VALUES (0000000004, 9000, ' fdsnk', ' 40', ' 2', 85, '2018-01-01 05:11:12', '2018-01-01 05:11:14', '2', 86, 87, '2', '2', 2, 2, '2', '2', '2 ', '2', ' 2', '2', '2', '2', '2', NULL, NULL);
INSERT INTO `account_info` VALUES (0000000025, 2000, 'fdsnk', 'lowercase_id', '2', NULL, '2018-01-01 05:11:12', '2018-01-01 05:11:14', '2', NULL, NULL, '2', '2', 2, 2, '2', '2', '2', '2', '2', '2', '2', '2', '2', NULL, 22);

-- ----------------------------
-- Table structure for address
-- ----------------------------
DROP TABLE IF EXISTS `address`;
CREATE TABLE `address`  (
  `aid` int(10) NOT NULL AUTO_INCREMENT,
  `province` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `city` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `street` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  PRIMARY KEY (`aid`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 16 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of address
-- ----------------------------
INSERT INTO `address` VALUES (1, 'gz', 'gz', 'py');
INSERT INTO `address` VALUES (2, 'gd', 'gz', 'py');
INSERT INTO `address` VALUES (3, 'gd', 'gz', 'py');
INSERT INTO `address` VALUES (4, 'gz', 'gz', 'py');
INSERT INTO `address` VALUES (5, 'gd', 'gz', 'py');
INSERT INTO `address` VALUES (6, 'gd', 'gz', 'py');
INSERT INTO `address` VALUES (7, NULL, NULL, NULL);
INSERT INTO `address` VALUES (8, 'gd', 'gz', 'py');
INSERT INTO `address` VALUES (9, 'gd', 'gz', 'py');
INSERT INTO `address` VALUES (10, 'gz', 'gz', 'py');
INSERT INTO `address` VALUES (11, 'gd', 'gz', 'py');
INSERT INTO `address` VALUES (12, 'gd', 'gz', 'py');
INSERT INTO `address` VALUES (13, 'gz', 'gz', 'py');
INSERT INTO `address` VALUES (14, 'gd', 'gz', 'py');
INSERT INTO `address` VALUES (15, 'gd', 'gz', 'py');

-- ----------------------------
-- Table structure for admin_manager
-- ----------------------------
DROP TABLE IF EXISTS `admin_manager`;
CREATE TABLE `admin_manager`  (
  `security_id` int(5) NOT NULL,
  `admin_id` int(10) NOT NULL,
  PRIMARY KEY (`security_id`, `admin_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for auditor_manager
-- ----------------------------
DROP TABLE IF EXISTS `auditor_manager`;
CREATE TABLE `auditor_manager`  (
  `security_id` int(5) NOT NULL,
  `auditor_id` int(10) NOT NULL,
  PRIMARY KEY (`security_id`, `auditor_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of auditor_manager
-- ----------------------------
INSERT INTO `auditor_manager` VALUES (1, 9527);

-- ----------------------------
-- Table structure for currency
-- ----------------------------
DROP TABLE IF EXISTS `currency`;
CREATE TABLE `currency`  (
  `Fund_id` int(10) NOT NULL,
  `currency_type` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `balance` decimal(10, 2) NOT NULL,
  PRIMARY KEY (`Fund_id`, `currency_type`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for customer_account
-- ----------------------------
DROP TABLE IF EXISTS `customer_account`;
CREATE TABLE `customer_account`  (
  `customer_id` varchar(12) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `user_id` int(10) DEFAULT NULL,
  `open_date` datetime(0) DEFAULT CURRENT_TIMESTAMP,
  `insurance_date` datetime(0) DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP(0),
  `auditor_id` int(10) DEFAULT NULL,
  PRIMARY KEY (`customer_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of customer_account
-- ----------------------------
INSERT INTO `customer_account` VALUES ('11', 111, '2019-06-24 15:19:31', '2019-06-24 15:19:31', 9527);

-- ----------------------------
-- Table structure for employee
-- ----------------------------
DROP TABLE IF EXISTS `employee`;
CREATE TABLE `employee`  (
  `employee_id` int(10) NOT NULL,
  `employee_account` varchar(12) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `employee_password` varchar(12) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `Employee_type` char(1) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  PRIMARY KEY (`employee_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for evaluation_grade
-- ----------------------------
DROP TABLE IF EXISTS `evaluation_grade`;
CREATE TABLE `evaluation_grade`  (
  `grade` char(3) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `mini_mark` decimal(65, 0) DEFAULT NULL,
  `max_mark` decimal(65, 0) DEFAULT NULL,
  PRIMARY KEY (`grade`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of evaluation_grade
-- ----------------------------
INSERT INTO `evaluation_grade` VALUES ('保守型', 0, 19);
INSERT INTO `evaluation_grade` VALUES ('激进型', 83, 100);
INSERT INTO `evaluation_grade` VALUES ('积极型', 54, 82);
INSERT INTO `evaluation_grade` VALUES ('稳健型', 37, 53);
INSERT INTO `evaluation_grade` VALUES ('谨慎型', 20, 36);

-- ----------------------------
-- Table structure for fund_account
-- ----------------------------
DROP TABLE IF EXISTS `fund_account`;
CREATE TABLE `fund_account`  (
  `customer_id` varchar(12) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `fund_id` varchar(12) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `bank_account` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `bank` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `type` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  PRIMARY KEY (`fund_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for risk_evaluation
-- ----------------------------
DROP TABLE IF EXISTS `risk_evaluation`;
CREATE TABLE `risk_evaluation`  (
  `RE_id` int(12) NOT NULL AUTO_INCREMENT,
  `content` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `option_1` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `mark_1` decimal(65, 0) DEFAULT NULL,
  `mark_2` decimal(65, 0) DEFAULT NULL,
  `mark_3` decimal(65, 0) DEFAULT NULL,
  `mark_4` decimal(65, 0) DEFAULT NULL,
  `mark_5` decimal(65, 0) DEFAULT NULL,
  `option_2` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `option_3` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `option_4` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `option_5` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `is_radio` tinyint(1) DEFAULT NULL,
  `type` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  PRIMARY KEY (`RE_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 21 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of risk_evaluation
-- ----------------------------
INSERT INTO `risk_evaluation` VALUES (1, '1.您的主要收入来源是：( )', 'A. 工资、劳务报酬', 3, 2, 1, 1, 0, 'B. 生产经营所得', 'C. 利息、股息、转让证券等金融性资产收入', 'D. 出租、出售房地产等非金融性资产收入', 'E. 无固定收入，或者个人或家庭人均收入低于当地城乡居民最低生活保障标准', 1, '财务状况');
INSERT INTO `risk_evaluation` VALUES (2, '2.最近您家庭预计进行证券投资的资金占家庭现有总资产(不含自住、自用房产及汽车等固定资产)的比例是：( )', 'A．70%以上', 1, 3, 4, 5, 6, 'B．50%-70% ', 'C．30%－50%', 'D．10%－30%', 'E．10%以下', 1, '财务状况');
INSERT INTO `risk_evaluation` VALUES (3, '3.您是否有尚未清偿的数额较大的债务，如有，其性质是：（ ）', 'A. 没有', 3, 2, 1, 0, NULL, 'B. 有，住房抵押贷款等长期定额债务', 'C. 有，信用卡欠款、消费信贷等短期信用债务', 'D. 有，亲朋之间借款', NULL, 1, '财务状况');
INSERT INTO `risk_evaluation` VALUES (4, '4.您可用于投资的资产数额（包括金融资产和不动产）为：（ ）', ' A. 不超过50万元人民币', 0, 1, 2, 3, NULL, 'B. 50万-300万元（不含）人民币', 'C. 300万-1000万元（不含）人民币', 'D. 1000万元人民币以上', NULL, 1, '财务状况');
INSERT INTO `risk_evaluation` VALUES (5, '5.以下描述中何种符合您的实际情况：( )', 'A. 现在或此前曾从事金融、经济或财会等与金融产品投资相关的工作超过两年', 5, 5, 5, 0, NULL, 'B. 已取得金融、经济或财会等与金融产品投资相关专业学士以上学位', 'C. 取得证券从业资格、期货从业资格、注册会计师证书（CPA）或注册金融分析师证书（CFA）中的一项及以上', 'D. 我不符合以上任何一项描述', NULL, 1, '投资知识');
INSERT INTO `risk_evaluation` VALUES (6, '6.您的投资经验可以被概括为：( )', 'A．有限：除银行活期账户和定期存款外，我基本没有其他投资经验或者证券期货投资知识', 1, 2, 3, 5, NULL, 'B．一般：除银行活期账户和定期存款外，我购买过基金、保险等理财产品，但还需要进一步的指导', 'C．丰富：我是一位有经验的投资者，参与过股票、基金等产品的交易，并倾向于自己做出投资决策', 'D．非常丰富：我是一位非常有经验的投资者，参与过权证、期货或创业板等高风险产品的交易', NULL, 1, '投资经验');
INSERT INTO `risk_evaluation` VALUES (7, '7.有一位投资者一个月内做了15笔交易（同一品种买卖各一次算一笔），您认为这样的交易频率：( )', 'A. 太高了', 1, 2, 3, 4, NULL, 'B. 偏高', 'C. 正常', 'D. 偏低', NULL, 1, '投资经验');
INSERT INTO `risk_evaluation` VALUES (8, '8.过去一年时间内，您购买的不同产品或接受的不同服务（含同一类型的不同产品或服务）的数量是：（ ）', 'A. 5个以下', 1, 3, 4, 6, NULL, 'B. 6至10个', 'C. 11至15个', 'D. 16个以上', NULL, 1, '投资经验');
INSERT INTO `risk_evaluation` VALUES (9, '9.以下金融产品或服务，您投资经验在两年以上的有：（ ）', 'A. 银行存款等', 0, 1, 2, 4, 6, 'B. 债券、货币市场基金、债券型基金或其它固定收益类产品等', 'C. 股票、混合型基金、偏股型基金、股票型基金等权益类投资品种等', 'D. 期货、融资融券', 'E. 复杂金融产品、其他产品或服务', 0, '投资经验');
INSERT INTO `risk_evaluation` VALUES (10, '10.如果您曾经从事过金融市场投资，在交易较为活跃的月份，平均月交易额大概是多少：（ ）', 'A. 10万元以内', 1, 2, 3, 4, 0, 'B. 10万元-30万元', 'C. 30万元-100万元', 'D. 100万元以上', 'E. 从未从事过金融市场投资', 1, '投资经验');
INSERT INTO `risk_evaluation` VALUES (11, '11.您用于证券投资的大部分资金不会用作其它用途的时间段为：（ ）', 'A．0到1年', 1, 3, 5, NULL, NULL, 'B．0到5年', 'C．无特别要求', NULL, NULL, 1, '投资目标');
INSERT INTO `risk_evaluation` VALUES (12, '12.您打算重点投资于哪些种类的投资品种？（ ）', 'A.  债券、货币市场基金、债券基金等固定收益类投资品种', 2, 4, 5, 6, 6, 'B.  股票、混合型基金、偏股型基金、股票型基金等权益类投资品种', 'C.  期货、融资融券等', 'D.  复杂或高风险金融产品或服务', 'E.  其他产品或服务', 0, '投资目标');
INSERT INTO `risk_evaluation` VALUES (13, '13.假设有两种不同的投资：投资A预期获得5%的收益，有可能承担非常小的损失；投资B预期获得20%的收益，但有可能面临25%甚至更高的亏损。您将您的投资资产分配为： （ ）', 'A．全部投资于A', 0, 1, 3, 5, 6, 'B．大部分投资于A', 'C．两种投资各一半', 'D．大部分投资于B', 'E．全部投资于B', 1, '投资目标');
INSERT INTO `risk_evaluation` VALUES (14, '14.当您进行投资时，您的首要目标是：（ ）', 'A．尽可能保证本金安全，不在乎收益率比较低', 0, 2, 3, 4, NULL, 'B．产生一定的收益，可以承担一定的投资风险', 'C．产生较多的收益，可以承担较大的投资风险', 'D．实现资产大幅增长，愿意承担很大的投资风险', NULL, 1, '风险偏好');
INSERT INTO `risk_evaluation` VALUES (15, '15.您认为自己能承受的最大投资损失是多少？（ ）', 'A. 尽可能保证本金安全，没有风险容忍度或者不能承受任何损失', 0, 2, 4, 6, NULL, 'B. 一定的投资损失', 'C. 较大的投资损失', 'D. 损失可能超过本金', NULL, 1, '风险偏好');
INSERT INTO `risk_evaluation` VALUES (16, '16.您打算将自己的投资回报主要用于：（ ）', 'A. 改善生活', 7, 5, 4, 3, 1, 'B. 个体生产经营或证券投资以外的投资行为', 'C. 履行扶养、抚养或赡养义务', 'D. 本人养老或医疗', 'E. 偿付债务', 1, '风险偏好');
INSERT INTO `risk_evaluation` VALUES (17, '17.您的年龄是：（ ）', 'A. 18-30岁', 5, 7, 3, 2, 0, 'B. 31-40岁', 'C. 41-50岁', 'D 51-60岁', 'E. 超过60岁', 1, '其他信息');
INSERT INTO `risk_evaluation` VALUES (18, '18.今后五年时间内，您的父母、配偶以及未成年子女等需负法定抚养、扶养和赡养义务的人数为：（ ）', 'A.1-2人', 5, 3, 1, NULL, NULL, 'B.3-4人', 'C.5人以上', NULL, NULL, 1, '其他信息');
INSERT INTO `risk_evaluation` VALUES (19, '19.您的最高学历是：（ ）', 'A. 高中或以下', 1, 2, 4, 5, NULL, 'B. 大学专科', 'C. 大学本科', 'D. 硕士及以上', NULL, 1, '其他信息');
INSERT INTO `risk_evaluation` VALUES (20, '20.您家庭的就业状况是：（ ）', 'A. 您与配偶均有稳定收入的工作', 4, 3, 2, 1, 0, 'B. 您与配偶其中一人有稳定收入的工作', 'C. 您与配偶均没有稳定收入的工作或者已退休', 'D. 未婚，但有稳定收入的工作', 'E. 未婚，目前暂无稳定收入的工作', 1, '其他信息');

-- ----------------------------
-- Table structure for security
-- ----------------------------
DROP TABLE IF EXISTS `security`;
CREATE TABLE `security`  (
  `security_id` int(5) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `province` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `city` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `type` int(1) DEFAULT NULL,
  PRIMARY KEY (`security_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 234 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of security
-- ----------------------------
INSERT INTO `security` VALUES (1, '爱建证券有限责任公司', '上海', '上海', 0);
INSERT INTO `security` VALUES (2, '安信证券股份有限公司', '广东', '深圳', 0);
INSERT INTO `security` VALUES (3, '北京高华证券有限责任公司', '北京', '北京', 0);
INSERT INTO `security` VALUES (4, '渤海证券股份有限公司', '天津', '天津', 0);
INSERT INTO `security` VALUES (5, '财达证券股份有限公司', '河北', '石家庄', 0);
INSERT INTO `security` VALUES (6, '财富证券有限责任公司', '湖南', '长沙', 0);
INSERT INTO `security` VALUES (7, '财通证券股份有限公司', '浙江', '杭州', 0);
INSERT INTO `security` VALUES (8, '长城国瑞证券有限公司', '福建', '厦门', 0);
INSERT INTO `security` VALUES (9, '长城证券股份有限公司', '广东', '深圳', 0);
INSERT INTO `security` VALUES (10, '长江证券承销保荐有限公司', '上海', '上海', 0);
INSERT INTO `security` VALUES (11, '长江证券股份有限公司', '湖北', '武汉', 0);
INSERT INTO `security` VALUES (12, '川财证券有限责任公司', '四川', '成都', 0);
INSERT INTO `security` VALUES (13, '大通证券股份有限公司', '辽宁', '大连', 0);
INSERT INTO `security` VALUES (14, '大同证券有限责任公司', '山西', '太原', 0);
INSERT INTO `security` VALUES (15, '德邦证券股份有限公司', '上海', '上海', 0);
INSERT INTO `security` VALUES (16, '第一创业证券承销保荐有限责任公司', '北京', '北京', 0);
INSERT INTO `security` VALUES (17, '第一创业证券股份有限公司', '广东', '深圳', 0);
INSERT INTO `security` VALUES (18, '东北证券股份有限公司', '吉林', '长春', 0);
INSERT INTO `security` VALUES (19, '东方花旗证券有限公司', '上海', '上海', 0);
INSERT INTO `security` VALUES (20, '东方证券股份有限公司', '上海', '上海', 0);
INSERT INTO `security` VALUES (21, '东海证券股份有限公司', '江苏', '常州', 0);
INSERT INTO `security` VALUES (22, '东吴证券股份有限公司', '江苏', '苏州', 0);
INSERT INTO `security` VALUES (23, '东兴证券股份有限公司', '北京', '北京', 0);
INSERT INTO `security` VALUES (24, '东亚前海证券有限责任公司', '广东', '深圳', 0);
INSERT INTO `security` VALUES (25, '东莞证券股份有限公司', '广东', '东莞', 0);
INSERT INTO `security` VALUES (26, '方正证券股份有限公司', '湖南', '长沙', 0);
INSERT INTO `security` VALUES (27, '高盛高华证券有限责任公司', '北京', '北京', 0);
INSERT INTO `security` VALUES (28, '光大证券股份有限公司', '上海', '上海', 0);
INSERT INTO `security` VALUES (29, '广发证券股份有限公司', '广东', '广州', 0);
INSERT INTO `security` VALUES (30, '广州证券股份有限公司', '广东', '广州', 0);
INSERT INTO `security` VALUES (31, '国都证券股份有限公司', '北京', '北京', 0);
INSERT INTO `security` VALUES (32, '国海证券股份有限公司', '广西', '桂林', 0);
INSERT INTO `security` VALUES (33, '国金证券股份有限公司', '四川', '成都', 0);
INSERT INTO `security` VALUES (34, '国开证券股份有限公司', '北京', '北京', 0);
INSERT INTO `security` VALUES (35, '国联证券股份有限公司', '江苏', '无锡', 0);
INSERT INTO `security` VALUES (36, '国融证券股份有限公司', '内蒙古', '呼和浩特', 0);
INSERT INTO `security` VALUES (37, '国盛证券有限责任公司', '江西', '南昌', 0);
INSERT INTO `security` VALUES (38, '国泰君安证券股份有限公司', '上海', '上海', 0);
INSERT INTO `security` VALUES (39, '国信证券股份有限公司', '广东', '深圳', 0);
INSERT INTO `security` VALUES (40, '国元证券股份有限公司', '安徽', '合肥', 0);
INSERT INTO `security` VALUES (41, '海通证券股份有限公司', '上海', '上海', 0);
INSERT INTO `security` VALUES (42, '恒泰长财证券有限责任公司', '吉林', '长春', 0);
INSERT INTO `security` VALUES (43, '恒泰证券股份有限公司', '内蒙古', '呼和浩特', 0);
INSERT INTO `security` VALUES (44, '宏信证券有限责任公司', '四川', '成都', 0);
INSERT INTO `security` VALUES (45, '红塔证券股份有限公司', '云南', '昆明', 0);
INSERT INTO `security` VALUES (46, '华安证券股份有限公司', '安徽', '合肥', 0);
INSERT INTO `security` VALUES (47, '华宝证券有限责任公司', '上海', '上海', 0);
INSERT INTO `security` VALUES (48, '华创证券有限责任公司', '贵州', '贵阳', 0);
INSERT INTO `security` VALUES (49, '华福证券有限责任公司', '福建', '福州', 0);
INSERT INTO `security` VALUES (50, '华金证券股份有限公司', '上海', '上海', 0);
INSERT INTO `security` VALUES (51, '华林证券股份有限公司', '广东', '深圳', 0);
INSERT INTO `security` VALUES (52, '华龙证券股份有限公司', '甘肃', '兰州', 0);
INSERT INTO `security` VALUES (53, '华融证券股份有限公司', '北京', '北京', 0);
INSERT INTO `security` VALUES (54, '华泰联合证券有限责任公司', '广东', '深圳', 0);
INSERT INTO `security` VALUES (55, '华泰证券股份有限公司', '江苏', '南京', 0);
INSERT INTO `security` VALUES (56, '华西证券股份有限公司', '四川', '成都', 0);
INSERT INTO `security` VALUES (57, '华英证券有限责任公司', '江苏', '无锡', 0);
INSERT INTO `security` VALUES (58, '华菁证券有限公司', '上海', '上海', 0);
INSERT INTO `security` VALUES (59, '华鑫证券有限责任公司', '广东', '深圳', 0);
INSERT INTO `security` VALUES (60, '汇丰前海证券有限责任公司', '广东', '深圳', 0);
INSERT INTO `security` VALUES (61, '江海证券有限公司', '黑龙江', '哈尔滨', 0);
INSERT INTO `security` VALUES (62, '金通证券有限责任公司', '浙江', '杭州', 0);
INSERT INTO `security` VALUES (63, '金元证券股份有限公司', '广东', '深圳', 0);
INSERT INTO `security` VALUES (64, '九州证券股份有限公司', '北京', '北京', 0);
INSERT INTO `security` VALUES (65, '开源证券股份有限公司', '陕西', '西安', 0);
INSERT INTO `security` VALUES (66, '联储证券有限责任公司', '广东', '深圳', 0);
INSERT INTO `security` VALUES (67, '联讯证券股份有限公司', '广东', '惠州', 0);
INSERT INTO `security` VALUES (68, '民生证券股份有限公司', '北京', '北京', 0);
INSERT INTO `security` VALUES (69, '摩根士丹利华鑫证券有限责任公司', '上海', '上海', 0);
INSERT INTO `security` VALUES (70, '南京证券股份有限公司', '江苏', '南京', 0);
INSERT INTO `security` VALUES (71, '平安证券股份有限公司', '广东', '深圳', 0);
INSERT INTO `security` VALUES (72, '瑞信方正证券有限责任公司', '北京', '北京', 0);
INSERT INTO `security` VALUES (73, '瑞银证券有限责任公司', '北京', '北京', 0);
INSERT INTO `security` VALUES (74, '山西证券股份有限公司', '山西', '太原', 0);
INSERT INTO `security` VALUES (75, '上海华信证券有限责任公司', '上海', '上海', 0);
INSERT INTO `security` VALUES (76, '上海证券有限责任公司', '上海', '上海', 0);
INSERT INTO `security` VALUES (77, '申港证券股份有限公司', '上海', '上海', 0);
INSERT INTO `security` VALUES (78, '申万宏源西部证券有限公司', '新疆', '乌鲁木齐', 0);
INSERT INTO `security` VALUES (79, '申万宏源证券承销保荐有限责任公司', '新疆', '乌鲁木齐', 0);
INSERT INTO `security` VALUES (80, '申万宏源证券有限公司', '上海', '上海', 0);
INSERT INTO `security` VALUES (81, '世纪证券有限责任公司', '广东', '深圳', 0);
INSERT INTO `security` VALUES (82, '首创证券有限责任公司', '北京', '北京', 0);
INSERT INTO `security` VALUES (83, '太平洋证券股份有限公司', '云南', '昆明', 0);
INSERT INTO `security` VALUES (84, '天风证券股份有限公司', '湖北', '武汉', 0);
INSERT INTO `security` VALUES (85, '万和证券股份有限公司', '广东', '深圳', 0);
INSERT INTO `security` VALUES (86, '万联证券股份有限公司', '广东', '广州', 0);
INSERT INTO `security` VALUES (87, '网信证券有限责任公司', '辽宁', '沈阳', 0);
INSERT INTO `security` VALUES (88, '五矿证券有限公司', '广东', '深圳', 0);
INSERT INTO `security` VALUES (89, '西部证券股份有限公司', '陕西', '西安', 0);
INSERT INTO `security` VALUES (90, '西藏东方财富证券股份有限公司', '上海', '上海', 0);
INSERT INTO `security` VALUES (91, '西南证券股份有限公司', '重庆', '重庆', 0);
INSERT INTO `security` VALUES (92, '湘财证券股份有限公司', '湖南', '长沙', 0);
INSERT INTO `security` VALUES (93, '新时代证券股份有限公司', '北京', '北京', 0);
INSERT INTO `security` VALUES (94, '信达证券股份有限公司', '北京', '北京', 0);
INSERT INTO `security` VALUES (95, '兴业证券股份有限公司', '福建', '福州', 0);
INSERT INTO `security` VALUES (96, '银泰证券有限责任公司', '广东', '深圳', 0);
INSERT INTO `security` VALUES (97, '英大证券有限责任公司', '广东', '深圳', 0);
INSERT INTO `security` VALUES (98, '招商证券股份有限公司', '广东', '深圳', 0);
INSERT INTO `security` VALUES (99, '浙商证券股份有限公司', '浙江', '杭州', 0);
INSERT INTO `security` VALUES (100, '中德证券有限责任公司', '北京', '北京', 0);
INSERT INTO `security` VALUES (101, '中国国际金融股份有限公司', '北京', '北京', 0);
INSERT INTO `security` VALUES (102, '中国民族证券有限责任公司', '北京', '北京', 0);
INSERT INTO `security` VALUES (103, '中国银河证券股份有限公司', '北京', '北京', 0);
INSERT INTO `security` VALUES (104, '中国证券金融股份有限公司', '北京', '北京', 0);
INSERT INTO `security` VALUES (105, '中国中投证券有限责任公司', '广东', '深圳', 0);
INSERT INTO `security` VALUES (106, '中航证券有限公司', '江西', '南昌', 0);
INSERT INTO `security` VALUES (107, '中山证券有限责任公司', '广东', '深圳', 0);
INSERT INTO `security` VALUES (108, '中泰证券股份有限公司', '山东', '济南', 0);
INSERT INTO `security` VALUES (109, '中天国富证券有限公司', '贵州', '贵阳', 0);
INSERT INTO `security` VALUES (110, '中天证券股份有限公司', '辽宁', '沈阳', 0);
INSERT INTO `security` VALUES (111, '中信建投证券股份有限公司', '北京', '北京', 0);
INSERT INTO `security` VALUES (112, '中信证券(山东)有限责任公司', '山东', '青岛', 0);
INSERT INTO `security` VALUES (113, '中信证券股份有限公司', '北京', '北京', 0);
INSERT INTO `security` VALUES (114, '中银国际证券股份有限公司', '上海', '上海', 0);
INSERT INTO `security` VALUES (115, '中邮证券有限责任公司', '北京', '北京', 0);
INSERT INTO `security` VALUES (116, '中原证券股份有限公司', '河南', '郑州', 0);
INSERT INTO `security` VALUES (118, '国信证券股份有限公司', '广东', '深圳', 1);
INSERT INTO `security` VALUES (119, '招商证券股份有限公司', '广东', '深圳', 1);
INSERT INTO `security` VALUES (120, '平安证券股份有限公司', '广东', '深圳', 1);
INSERT INTO `security` VALUES (121, '长城证券股份有限公司', '广东', '深圳', 1);
INSERT INTO `security` VALUES (122, '光大证券股份有限公司', '上海', '上海', 1);
INSERT INTO `security` VALUES (123, '方正证券股份有限公司', '湖南', '长沙', 1);
INSERT INTO `security` VALUES (124, '华泰证券股份有限公司', '江苏', '南京', 1);
INSERT INTO `security` VALUES (125, '广发证券股份有限公司', '广东', '广州', 1);
INSERT INTO `security` VALUES (126, '广州证券股份有限公司', '广东', '广州', 1);
INSERT INTO `security` VALUES (127, '世纪证券有限责任公司', '广东', '深圳', 1);
INSERT INTO `security` VALUES (128, '华西证券股份有限公司', '四川', '成都', 1);
INSERT INTO `security` VALUES (129, '国海证券股份有限公司', '广西', '南宁', 1);
INSERT INTO `security` VALUES (130, '联讯证券股份有限公司', '广东', '惠州', 1);
INSERT INTO `security` VALUES (131, '南京证券股份有限公司', '江苏', '南京', 1);
INSERT INTO `security` VALUES (132, '华鑫证券有限责任公司', '广东', '深圳', 1);
INSERT INTO `security` VALUES (133, '华林证券股份有限公司', '西藏', '拉萨', 1);
INSERT INTO `security` VALUES (134, '长城国瑞证券有限公司', '福建', '厦门', 1);
INSERT INTO `security` VALUES (135, '中国民族证券有限责任公司', '北京', '北京', 1);
INSERT INTO `security` VALUES (136, '申万宏源证券有限公司', '上海', '上海', 1);
INSERT INTO `security` VALUES (137, '中信证券（山东）有限责任公司', '山东', '青岛', 1);
INSERT INTO `security` VALUES (138, '海通证券股份有限公司', '上海', '上海', 1);
INSERT INTO `security` VALUES (139, '山西证券股份有限公司', '山西', '太原', 1);
INSERT INTO `security` VALUES (140, '民生证券股份有限公司', '北京', '北京', 1);
INSERT INTO `security` VALUES (141, '兴业证券股份有限公司', '福建', '福州', 1);
INSERT INTO `security` VALUES (142, '华福证券有限责任公司', '福建', '福州', 1);
INSERT INTO `security` VALUES (143, '长江证券股份有限公司', '湖北', '武汉', 1);
INSERT INTO `security` VALUES (144, '东莞证券股份有限公司', '广东', '东莞', 1);
INSERT INTO `security` VALUES (145, '国联证券股份有限公司', '江苏', '无锡', 1);
INSERT INTO `security` VALUES (146, '国金证券股份有限公司', '四川', '成都', 1);
INSERT INTO `security` VALUES (147, '东北证券股份有限公司', '吉林', '长春', 1);
INSERT INTO `security` VALUES (148, '恒泰证券股份有限公司', '内蒙古', '呼和浩特', 1);
INSERT INTO `security` VALUES (149, '华安证券股份有限公司', '安徽', '合肥', 1);
INSERT INTO `security` VALUES (150, '东海证券股份有限公司', '江苏', '常州', 1);
INSERT INTO `security` VALUES (151, '中信证券股份有限公司', '广东', '深圳', 1);
INSERT INTO `security` VALUES (152, '东吴证券股份有限公司', '江苏', '苏州', 1);
INSERT INTO `security` VALUES (153, '中山证券有限责任公司', '广东', '深圳', 1);
INSERT INTO `security` VALUES (154, '第一创业证券股份有限公司', '广东', '深圳', 1);
INSERT INTO `security` VALUES (155, '大同证券有限责任公司', '山西', '太原', 1);
INSERT INTO `security` VALUES (156, '申万宏源西部证券有限公司', '新疆', '乌鲁木齐', 1);
INSERT INTO `security` VALUES (157, '西藏东方财富证券股份有限公司', '西藏', '拉萨', 1);
INSERT INTO `security` VALUES (158, '英大证券有限责任公司', '广东', '深圳', 1);
INSERT INTO `security` VALUES (159, '湘财证券股份有限公司', '湖南', '长沙', 1);
INSERT INTO `security` VALUES (160, '国开证券股份有限公司', '北京', '北京', 1);
INSERT INTO `security` VALUES (161, '中国国际金融股份有限公司', '北京', '北京', 1);
INSERT INTO `security` VALUES (162, '华泰联合证券有限责任公司', '广东', '深圳', 1);
INSERT INTO `security` VALUES (163, '东方证券股份有限公司', '上海', '上海', 1);
INSERT INTO `security` VALUES (164, '国泰君安证券股份有限公司', '上海', '上海', 1);
INSERT INTO `security` VALUES (165, '首创证券有限责任公司', '北京', '北京', 1);
INSERT INTO `security` VALUES (166, '西南证券股份有限公司', '重庆', '重庆', 1);
INSERT INTO `security` VALUES (167, '天风证券股份有限公司', '湖北', '武汉', 1);
INSERT INTO `security` VALUES (168, '五矿证券有限公司', '广东', '深圳', 1);
INSERT INTO `security` VALUES (169, '华金证券股份有限公司', '上海', '上海', 1);
INSERT INTO `security` VALUES (170, '中国银河证券股份有限公司', '北京', '北京', 1);
INSERT INTO `security` VALUES (171, '西部证券股份有限公司', '陕西', '西安', 1);
INSERT INTO `security` VALUES (172, '渤海证券股份有限公司', '天津', '天津', 1);
INSERT INTO `security` VALUES (173, '上海证券有限责任公司', '上海', '上海', 1);
INSERT INTO `security` VALUES (174, '开源证券股份有限公司', '陕西', '西安', 1);
INSERT INTO `security` VALUES (175, '华龙证券股份有限公司', '甘肃', '兰州', 1);
INSERT INTO `security` VALUES (176, '大通证券股份有限公司', '辽宁', '大连', 1);
INSERT INTO `security` VALUES (177, '万联证券股份有限公司', '广东', '广州', 1);
INSERT INTO `security` VALUES (178, '中泰证券股份有限公司', '山东', '济南', 1);
INSERT INTO `security` VALUES (179, '国元证券股份有限公司', '安徽', '合肥', 1);
INSERT INTO `security` VALUES (180, '联储证券有限责任公司', '广东', '深圳', 1);
INSERT INTO `security` VALUES (181, '国都证券股份有限公司', '北京', '北京', 1);
INSERT INTO `security` VALUES (182, '中银国际证券股份有限公司', '上海', '上海', 1);
INSERT INTO `security` VALUES (183, '华创证券有限责任公司', '贵州', '贵阳', 1);
INSERT INTO `security` VALUES (184, '华宝证券有限责任公司', '上海', '上海', 1);
INSERT INTO `security` VALUES (185, '红塔证券股份有限公司', '云南', '昆明', 1);
INSERT INTO `security` VALUES (186, '万和证券股份有限公司', '海南', '海口', 1);
INSERT INTO `security` VALUES (187, '国融证券股份有限公司', '内蒙古', '呼和浩特', 1);
INSERT INTO `security` VALUES (188, '川财证券有限责任公司', '四川', '成都', 1);
INSERT INTO `security` VALUES (189, '恒泰长财证券有限责任公司', '吉林', '长春', 1);
INSERT INTO `security` VALUES (190, '财达证券股份有限公司', '河北', '石家庄', 1);
INSERT INTO `security` VALUES (191, '浙商证券股份有限公司', '浙江', '杭州', 1);
INSERT INTO `security` VALUES (192, '爱建证券有限责任公司', '上海', '上海', 1);
INSERT INTO `security` VALUES (193, '宏信证券有限责任公司', '四川', '成都', 1);
INSERT INTO `security` VALUES (194, '中航证券有限公司', '江西', '南昌', 1);
INSERT INTO `security` VALUES (195, '财富证券有限责任公司', '湖南', '长沙', 1);
INSERT INTO `security` VALUES (196, '金元证券股份有限公司', '海南', '海口', 1);
INSERT INTO `security` VALUES (197, '中邮证券有限责任公司', '陕西', '西安', 1);
INSERT INTO `security` VALUES (198, '九州证券股份有限公司', '青海', '西宁', 1);
INSERT INTO `security` VALUES (199, '国盛证券有限责任公司', '江西', '南昌', 1);
INSERT INTO `security` VALUES (200, '中原证券股份有限公司', '河南', '郑州', 1);
INSERT INTO `security` VALUES (201, '德邦证券股份有限公司', '上海', '上海', 1);
INSERT INTO `security` VALUES (202, '财通证券股份有限公司', '浙江', '杭州', 1);
INSERT INTO `security` VALUES (203, '新时代证券股份有限公司', '北京', '北京', 1);
INSERT INTO `security` VALUES (204, '上海华信证券有限责任公司', '上海', '上海', 1);
INSERT INTO `security` VALUES (205, '网信证券有限责任公司', '辽宁', '沈阳', 1);
INSERT INTO `security` VALUES (206, '太平洋证券股份有限公司', '云南', '昆明', 1);
INSERT INTO `security` VALUES (207, '长江证券承销保荐有限公司', '上海', '上海', 1);
INSERT INTO `security` VALUES (208, '江海证券有限公司', '黑龙江', '哈尔滨', 1);
INSERT INTO `security` VALUES (209, '中天证券股份有限公司', '辽宁', '沈阳', 1);
INSERT INTO `security` VALUES (210, '北京高华证券有限责任公司', '北京', '北京', 1);
INSERT INTO `security` VALUES (211, '中天国富证券有限公司', '贵州', '贵阳', 1);
INSERT INTO `security` VALUES (212, '高盛高华证券有限责任公司', '北京', '北京', 1);
INSERT INTO `security` VALUES (213, '中国中投证券有限责任公司', '广东', '深圳', 1);
INSERT INTO `security` VALUES (214, '中信建投证券股份有限公司', '北京', '北京', 1);
INSERT INTO `security` VALUES (215, '安信证券股份有限公司', '广东', '深圳', 1);
INSERT INTO `security` VALUES (216, '银泰证券有限责任公司', '广东', '深圳', 1);
INSERT INTO `security` VALUES (217, '瑞银证券有限责任公司', '北京', '北京', 1);
INSERT INTO `security` VALUES (218, '信达证券股份有限公司', '北京', '北京', 1);
INSERT INTO `security` VALUES (219, '华融证券股份有限公司', '北京', '北京', 1);
INSERT INTO `security` VALUES (220, '东兴证券股份有限公司', '北京', '北京', 1);
INSERT INTO `security` VALUES (221, '瑞信方正证券有限责任公司', '北京', '北京', 1);
INSERT INTO `security` VALUES (222, '中德证券有限责任公司', '北京', '北京', 1);
INSERT INTO `security` VALUES (223, '华英证券有限责任公司', '江苏', '无锡', 1);
INSERT INTO `security` VALUES (224, '第一创业证券承销保荐有限责任公司', '北京', '北京', 1);
INSERT INTO `security` VALUES (225, '摩根士丹利华鑫证券有限责任公司', '上海', '上海', 1);
INSERT INTO `security` VALUES (226, '东方花旗证券有限公司', '上海', '上海', 1);
INSERT INTO `security` VALUES (227, '金通证券有限责任公司', '浙江', '杭州', 1);
INSERT INTO `security` VALUES (228, '申万宏源证券承销保荐有限责任公司', '新疆', '乌鲁木齐', 1);
INSERT INTO `security` VALUES (229, '申港证券股份有限公司', '上海', '上海', 1);
INSERT INTO `security` VALUES (230, '华菁证券有限公司', '上海', '上海', 1);
INSERT INTO `security` VALUES (231, '汇丰前海证券有限责任公司', '广东', '深圳', 1);
INSERT INTO `security` VALUES (232, '东亚前海证券有限责任公司', '广东', '深圳', 1);
INSERT INTO `security` VALUES (233, '中国证券金融股份有限公司', '北京', '北京', 1);

-- ----------------------------
-- Table structure for trade_account
-- ----------------------------
DROP TABLE IF EXISTS `trade_account`;
CREATE TABLE `trade_account`  (
  `customer_id` varchar(12) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `trade_id` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `security_id` int(5) NOT NULL,
  PRIMARY KEY (`customer_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for transactions
-- ----------------------------
DROP TABLE IF EXISTS `transactions`;
CREATE TABLE `transactions`  (
  `transaction_id` int(10) UNSIGNED ZEROFILL NOT NULL AUTO_INCREMENT,
  `customer_id` varchar(12) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `value` decimal(10, 2) DEFAULT NULL,
  `message` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `createtime` datetime(0) DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`transaction_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user`  (
  `user_id` int(12) UNSIGNED ZEROFILL NOT NULL AUTO_INCREMENT,
  `phone` varchar(11) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `password` varchar(12) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `status` char(1) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `create_time` datetime(0) DEFAULT CURRENT_TIMESTAMP,
  `update_time` datetime(0) DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP(0),
  PRIMARY KEY (`user_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 4 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES (000000000004, '15920139771', '123456', '5', '2019-06-24 15:20:22', '2019-06-24 15:20:44');
INSERT INTO `user` VALUES (000000000005, '15920139700', '123456', '5', '2019-06-24 15:20:30', '2019-06-24 15:20:46');
INSERT INTO `user` VALUES (000000000006, '15920149700', '123456', '5', '2019-06-24 15:20:36', '2019-06-24 15:20:47');
INSERT INTO `user` VALUES (000000000007, '14920149700', '123456', '6', '2019-06-24 15:20:38', '2019-06-24 15:24:16');

SET FOREIGN_KEY_CHECKS = 1;
