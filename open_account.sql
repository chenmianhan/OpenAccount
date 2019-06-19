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

 Date: 19/06/2019 14:17:03
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for account_info
-- ----------------------------
DROP TABLE IF EXISTS `account_info`;
CREATE TABLE `account_info`  (
  `account_info_id` int(12) UNSIGNED ZEROFILL NOT NULL AUTO_INCREMENT,
  `user_id` int(12) NOT NULL,
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `ID_type` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `ID_number` varchar(18) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `ID_issuance_date` datetime(0) DEFAULT NULL,
  `ID_overdue_date` datetime(0) DEFAULT NULL,
  `ID_licensing_authority` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `contact_address_id` int(11) DEFAULT NULL,
  `postal_address_id` int(11) DEFAULT NULL,
  `trans_password` varchar(12) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `Fund_password` varchar(12) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `n_security_id` varchar(12) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `s_security_id` varchar(12) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `deposit_bank` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `deposit_account` varchar(19) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `deposit_password` varchar(12) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `status` varchar(2) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `profession` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `education` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `email` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `ID_picture` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `ID_card_inverse_side` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `risk_assessment_mark` decimal(65, 0) DEFAULT NULL,
  PRIMARY KEY (`account_info_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for address
-- ----------------------------
DROP TABLE IF EXISTS `address`;
CREATE TABLE `address`  (
  `aid` int(11) NOT NULL AUTO_INCREMENT,
  `province` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `city` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `street` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  PRIMARY KEY (`aid`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for admin_manager
-- ----------------------------
DROP TABLE IF EXISTS `admin_manager`;
CREATE TABLE `admin_manager`  (
  `security_id` varchar(12) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `admin_id` varchar(12) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  PRIMARY KEY (`security_id`, `admin_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for auditor_manager
-- ----------------------------
DROP TABLE IF EXISTS `auditor_manager`;
CREATE TABLE `auditor_manager`  (
  `security_id` varchar(12) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `auditor_id` varchar(12) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  PRIMARY KEY (`security_id`, `auditor_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for currency
-- ----------------------------
DROP TABLE IF EXISTS `currency`;
CREATE TABLE `currency`  (
  `Fund_id` varchar(12) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `currency_type` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `balance` decimal(65, 0) NOT NULL,
  PRIMARY KEY (`Fund_id`, `currency_type`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for customer_account
-- ----------------------------
DROP TABLE IF EXISTS `customer_account`;
CREATE TABLE `customer_account`  (
  `customer_id` varchar(12) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `account_info_id` varchar(12) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `open_date` datetime(0) DEFAULT CURRENT_TIMESTAMP,
  `insurance_date` datetime(0) DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP(0),
  PRIMARY KEY (`customer_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for employee
-- ----------------------------
DROP TABLE IF EXISTS `employee`;
CREATE TABLE `employee`  (
  `employee_id` varchar(12) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
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
  `grade` char(1) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `mini_mark` decimal(65, 0) DEFAULT NULL,
  `max_mark` decimal(65, 0) DEFAULT NULL,
  PRIMARY KEY (`grade`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of evaluation_grade
-- ----------------------------
INSERT INTO `evaluation_grade` VALUES ('A', 83, 100);
INSERT INTO `evaluation_grade` VALUES ('B', 54, 82);
INSERT INTO `evaluation_grade` VALUES ('C', 37, 53);
INSERT INTO `evaluation_grade` VALUES ('D', 20, 36);
INSERT INTO `evaluation_grade` VALUES ('E', 0, 19);

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
  PRIMARY KEY (`customer_id`) USING BTREE
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
  `security_id` int(12) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `address` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `type` int(1) DEFAULT NULL,
  PRIMARY KEY (`security_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for trade_account
-- ----------------------------
DROP TABLE IF EXISTS `trade_account`;
CREATE TABLE `trade_account`  (
  `customer_id` varchar(12) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `trade_id` varchar(12) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `network-dot` varchar(12) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  PRIMARY KEY (`customer_id`) USING BTREE
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
  `customer_id` varchar(12) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  PRIMARY KEY (`user_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 4 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

SET FOREIGN_KEY_CHECKS = 1;
