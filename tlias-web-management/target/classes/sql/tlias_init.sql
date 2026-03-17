
-- ============================================
-- Tlias 学生管理系统数据库初始化脚本
-- 数据库：MySQL
-- 创建时间：2026-03-12
-- ============================================

-- 创建数据库
CREATE DATABASE IF NOT EXISTS `tlias` DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;

USE `tlias`;

-- ============================================
-- 部门表 (dept)
-- ============================================
DROP TABLE IF EXISTS `dept`;
CREATE TABLE `dept` (
                        `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'ID,主键',
                        `name` varchar(50) NOT NULL COMMENT '部门名称',
                        `create_time` datetime DEFAULT NULL COMMENT '创建时间',
                        `update_time` datetime DEFAULT NULL COMMENT '修改时间',
                        PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='部门表';

-- ============================================
-- 员工表 (emp)
-- ============================================
DROP TABLE IF EXISTS `emp`;
CREATE TABLE `emp` (
                       `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'ID,主键',
                       `username` varchar(50) NOT NULL COMMENT '用户名',
                       `password` varchar(50) NOT NULL COMMENT '密码',
                       `name` varchar(50) NOT NULL COMMENT '姓名',
                       `gender` tinyint(4) DEFAULT NULL COMMENT '性别，1:男，2:女',
                       `phone` varchar(20) DEFAULT NULL COMMENT '手机号',
                       `job` tinyint(4) DEFAULT NULL COMMENT '职位，1:班主任，2:讲师，3:学工主管，4:教研主管，5:咨询师',
                       `salary` decimal(10,2) DEFAULT NULL COMMENT '薪资',
                       `image` varchar(255) DEFAULT NULL COMMENT '头像',
                       `entry_date` date DEFAULT NULL COMMENT '入职日期',
                       `dept_id` int(11) DEFAULT NULL COMMENT '关联的部门 ID',
                       `create_time` datetime DEFAULT NULL COMMENT '创建时间',
                       `update_time` datetime DEFAULT NULL COMMENT '修改时间',
                       PRIMARY KEY (`id`),
                       KEY `dept_id` (`dept_id`),
                       CONSTRAINT `emp_ibfk_1` FOREIGN KEY (`dept_id`) REFERENCES `dept` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='员工表';

-- ============================================
-- 员工经历表 (emp_expr)
-- ============================================
DROP TABLE IF EXISTS `emp_expr`;
CREATE TABLE `emp_expr` (
                            `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'ID,主键',
                            `emp_id` int(11) NOT NULL COMMENT '员工 ID',
                            `begin` date NOT NULL COMMENT '开始时间',
                            `end` date NOT NULL COMMENT '结束时间',
                            `company` varchar(100) DEFAULT NULL COMMENT '公司名称',
                            `job` varchar(50) DEFAULT NULL COMMENT '职位',
                            PRIMARY KEY (`id`),
                            KEY `emp_id` (`emp_id`),
                            CONSTRAINT `emp_expr_ibfk_1` FOREIGN KEY (`emp_id`) REFERENCES `emp` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='员工经历表';

-- ============================================
-- 初始化数据 - 部门表
-- ============================================
INSERT INTO `dept` (`id`, `name`, `create_time`, `update_time`) VALUES
                                                                    (1, '学工部', NOW(), NOW()),
                                                                    (2, '教研部', NOW(), NOW()),
                                                                    (3, '咨询部', NOW(), NOW()),
                                                                    (4, '教学部', NOW(), NOW());

-- ============================================
-- 初始化数据 - 员工表
-- ============================================
INSERT INTO `emp` (`id`, `username`, `password`, `name`, `gender`, `phone`, `job`, `salary`, `image`, `entry_date`, `dept_id`, `create_time`, `update_time`) VALUES
                                                                                                                                                                 (1, 'zhangsan', '123456', '张三', 1, '13800138001', 2, 8000, '/images/1.jpg', '2020-01-01', 1, NOW(), NOW()),
                                                                                                                                                                 (2, 'lisi', '123456', '李四', 2, '13800138002', 1, 9000, '/images/2.jpg', '2019-06-15', 2, NOW(), NOW()),
                                                                                                                                                                 (3, 'wangwu', '123456', '王五', 1, '13800138003', 3, 10000, '/images/3.jpg', '2018-03-20', 3, NOW(), NOW()),
                                                                                                                                                                 (4, 'zhaoliu', '123456', '赵六', 2, '13800138004', 4, 9500, '/images/4.jpg', '2021-07-10', 4, NOW(), NOW()),
                                                                                                                                                                 (5, 'sunqi', '123456', '孙七', 1, '13800138005', 5, 8500, '/images/5.jpg', '2022-02-28', 1, NOW(), NOW());

-- ============================================
-- 初始化数据 - 员工经历表
-- ============================================
INSERT INTO `emp_expr` (`id`, `emp_id`, `begin`, `end`, `company`, `job`) VALUES
                                                                              (1, 1, '2018-01-01', '2019-12-31', '某某科技公司', 'Java 开发工程师'),
                                                                              (2, 2, '2017-06-01', '2019-05-31', '某某网络公司', '前端开发工程师'),
                                                                              (3, 3, '2016-03-01', '2018-02-28', '某某信息公司', '产品经理');
