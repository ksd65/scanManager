SET SESSION FOREIGN_KEY_CHECKS=0;

/* Drop Triggers */

DROP TRIGGER IF EXISTS TRI_bu_member_info_member_id;



/* Drop Tables */

DROP TABLE IF EXISTS bu_account;
DROP TABLE IF EXISTS bu_account_draw;
DROP TABLE IF EXISTS bu_bank;
DROP TABLE IF EXISTS bu_bank_sub;
DROP TABLE IF EXISTS bu_epay_code;
DROP TABLE IF EXISTS bu_member_bank;
DROP TABLE IF EXISTS bu_member_info;
DROP TABLE IF EXISTS bu_region;
DROP TABLE IF EXISTS bu_register_tmp;
DROP TABLE IF EXISTS bu_routeway;




/* Create Tables */

CREATE TABLE bu_account
(
	id int(11) NOT NULL AUTO_INCREMENT,
	member_id int(11) NOT NULL,
	balance decimal(15,4),
	freeze_money decimal(15,4),
	-- 创建者
	create_by varchar(64) NOT NULL COMMENT '创建者',
	-- 创建时间
	create_date datetime NOT NULL COMMENT '创建时间',
	-- 更新者
	update_by varchar(64) NOT NULL COMMENT '更新者',
	-- 更新时间
	update_date datetime NOT NULL COMMENT '更新时间',
	-- 备注信息
	remarks varchar(255) COMMENT '备注信息',
	-- 删除标记（0：正常；1：删除）
	del_flag char(1) DEFAULT '0' NOT NULL COMMENT '删除标记（0：正常；1：删除）',
	PRIMARY KEY (id),
	UNIQUE (member_id)
);


CREATE TABLE bu_account_draw
(
	id int(11) NOT NULL AUTO_INCREMENT,
	order_code varchar(32) NOT NULL,
	account_id int(11),
	money decimal(15,4),
	status char(1),
	-- 创建者
	create_by varchar(64) NOT NULL COMMENT '创建者',
	-- 创建时间
	create_date datetime NOT NULL COMMENT '创建时间',
	-- 更新者
	update_by varchar(64) NOT NULL COMMENT '更新者',
	-- 更新时间
	update_date datetime NOT NULL COMMENT '更新时间',
	-- 备注信息
	remarks varchar(255) COMMENT '备注信息',
	-- 删除标记（0：正常；1：删除）
	del_flag char(1) DEFAULT '0' NOT NULL COMMENT '删除标记（0：正常；1：删除）',
	PRIMARY KEY (id),
	UNIQUE (order_code)
);


CREATE TABLE bu_bank
(
	-- 银行代码
	id int(11) NOT NULL COMMENT '银行代码',
	-- 银行名称
	name varchar(255) NOT NULL COMMENT '银行名称',
	-- 排序
	sort int(11) COMMENT '排序',
	PRIMARY KEY (id)
);


CREATE TABLE bu_bank_sub
(
	id int(10) NOT NULL AUTO_INCREMENT,
	-- 银行代码
	bank_id int(11) COMMENT '银行代码',
	-- 联行行号
	sub_id varchar(50) COMMENT '联行行号',
	-- 所在城市
	city_id int(11) COMMENT '所在城市',
	-- 参与机构名称
	sub_name varchar(100) COMMENT '参与机构名称',
	-- 联系电话
	tel varchar(30) COMMENT '联系电话',
	PRIMARY KEY (id)
);


CREATE TABLE bu_epay_code
(
	pay_code varchar(15) NOT NULL,
	status varchar(2) NOT NULL,
	office_id varchar(64) NOT NULL,
	-- 绑定会员ID
	member_id int(11) COMMENT '绑定会员ID',
	batch_no varchar(15),
	-- 参考格式：内容1 时间1|内容2 时间2
	flow varchar(1024) COMMENT '参考格式：内容1 时间1|内容2 时间2',
	-- 创建者
	create_by varchar(64) NOT NULL COMMENT '创建者',
	-- 创建时间
	create_date datetime NOT NULL COMMENT '创建时间',
	-- 更新者
	update_by varchar(64) NOT NULL COMMENT '更新者',
	-- 更新时间
	update_date datetime NOT NULL COMMENT '更新时间',
	-- 备注信息
	remarks varchar(255) COMMENT '备注信息',
	-- 删除标记（0：正常；1：删除）
	del_flag char(1) DEFAULT '0' NOT NULL COMMENT '删除标记（0：正常；1：删除）',
	PRIMARY KEY (pay_code)
);


CREATE TABLE bu_member_bank
(
	id int(11) NOT NULL AUTO_INCREMENT,
	member_id int(11) NOT NULL,
	bank_id varchar(15),
	province varchar(15),
	city varchar(15),
	sub_id varchar(30),
	bank_open varchar(30),
	account_name varchar(60),
	account_number varchar(30),
	-- 创建者
	create_by varchar(64) NOT NULL COMMENT '创建者',
	-- 创建时间
	create_date datetime NOT NULL COMMENT '创建时间',
	-- 更新者
	update_by varchar(64) NOT NULL COMMENT '更新者',
	-- 更新时间
	update_date datetime NOT NULL COMMENT '更新时间',
	-- 备注信息
	remarks varchar(255) COMMENT '备注信息',
	-- 删除标记（0：正常；1：删除）
	del_flag char(1) DEFAULT '0' NOT NULL COMMENT '删除标记（0：正常；1：删除）',
	PRIMARY KEY (id)
);


CREATE TABLE bu_member_info
(
	-- 会员ID
	id int(11) NOT NULL AUTO_INCREMENT COMMENT '会员ID',
	-- 商户编号:2位（10）+8位序列（12300000起）
	code varchar(64) NOT NULL COMMENT '商户编号:2位（10）+8位序列（12300000起）',
	-- 商户类型
	type varchar(2) NOT NULL COMMENT '商户类型',
	-- 登陆账号
	login_code varchar(32) COMMENT '登陆账号',
	-- 登陆密码
	login_pass varchar(32) COMMENT '登陆密码',
	-- 上级ID
	parent_id int(11) COMMENT '上级ID',
	-- 电子邮箱
	email varchar(128) COMMENT '电子邮箱',
	-- 手机号码
	mobile_phone varchar(32) COMMENT '手机号码',
	-- 会员名称
	name varchar(40) COMMENT '会员名称',
	-- 联系人
	contact varchar(64) COMMENT '联系人',
	-- 状态
	status varchar(2) COMMENT '状态',
	-- 姓别
	sex_type varchar(2) COMMENT '姓别',
	-- 出生日期
	birthday date COMMENT '出生日期',
	-- 固定电话
	home_phone varchar(40) COMMENT '固定电话',
	-- 身份证号
	cert_nbr varchar(40) COMMENT '身份证号',
	-- 身份证正面图片
	cert_pic1 varchar(60) COMMENT '身份证正面图片',
	-- 身份证反面图片
	cert_pic2 varchar(60) COMMENT '身份证反面图片',
	-- 手持身份证照片
	memCert_pic varchar(60) COMMENT '手持身份证照片',
	-- 认证银行卡卡号
	card_nbr varchar(30) COMMENT '认证银行卡卡号',
	-- 银行卡正面图片
	card_pic1 varchar(60) COMMENT '银行卡正面图片',
	-- 银行卡反面图片
	card_pic2 varchar(60) COMMENT '银行卡反面图片',
	-- 地址
	addr varchar(256) COMMENT '地址',
	-- 省份
	province varchar(11) COMMENT '省份',
	-- 城市
	city varchar(11) COMMENT '城市',
	-- 县乡
	county varchar(11) COMMENT '县乡',
	-- 认证标志
	verify_flag char(1) COMMENT '认证标志',
	-- 微信通道ID
	wx_route_id varchar(5) NOT NULL COMMENT '微信通道ID',
	-- 支付宝通道ID
	zfb_route_id varchar(5) NOT NULL COMMENT '支付宝通道ID',
	-- 创建者
	create_by varchar(64) NOT NULL COMMENT '创建者',
	-- 创建时间
	create_date datetime NOT NULL COMMENT '创建时间',
	-- 更新者
	update_by varchar(64) NOT NULL COMMENT '更新者',
	-- 更新时间
	update_date datetime NOT NULL COMMENT '更新时间',
	-- 备注信息
	remarks varchar(255) COMMENT '备注信息',
	-- 删除标记（0：正常；1：删除）
	del_flag char(1) DEFAULT '0' NOT NULL COMMENT '删除标记（0：正常；1：删除）',
	PRIMARY KEY (id)
);


CREATE TABLE bu_region
(
	-- 地区ID
	id int(11) NOT NULL AUTO_INCREMENT COMMENT '地区ID',
	-- 省份代码
	prov_cd varchar(255) COMMENT '省份代码',
	-- 省份名称
	prov_nm varchar(255) COMMENT '省份名称',
	-- 地区代码
	city_cd varchar(255) COMMENT '地区代码',
	-- 地区名称
	city_nm varchar(255) COMMENT '地区名称',
	-- 行政区号
	area_cd varchar(10) COMMENT '行政区号',
	PRIMARY KEY (id)
);


CREATE TABLE bu_register_tmp
(
	-- 绑定会员ID
	member_id int(11) NOT NULL AUTO_INCREMENT COMMENT '绑定会员ID',
	-- 商户编号:2位（10）+8位序列（12300000起）
	code varchar(64) NOT NULL COMMENT '商户编号:2位（10）+8位序列（12300000起）',
	-- 商户类型
	type varchar(2) NOT NULL COMMENT '商户类型',
	-- 登陆账号
	login_code varchar(32) COMMENT '登陆账号',
	-- 登陆密码
	login_pass varchar(32) COMMENT '登陆密码',
	-- 上级ID
	parent_id int(11) COMMENT '上级ID',
	-- 电子邮箱
	email varchar(128) COMMENT '电子邮箱',
	-- 手机号码
	mobile_phone varchar(32) COMMENT '手机号码',
	-- 会员名称
	name varchar(40) COMMENT '会员名称',
	-- 联系人
	contact varchar(64) COMMENT '联系人',
	-- 身份证号
	cert_nbr varchar(40) COMMENT '身份证号',
	-- 身份证正面图片
	cert_pic1 varchar(60) COMMENT '身份证正面图片',
	-- 身份证反面图片
	cert_pic2 varchar(60) COMMENT '身份证反面图片',
	-- 手持身份证照片
	memCert_pic varchar(60) COMMENT '手持身份证照片',
	-- 认证银行卡卡号
	card_nbr varchar(30) COMMENT '认证银行卡卡号',
	-- 银行卡正面图片
	card_pic1 varchar(60) COMMENT '银行卡正面图片',
	-- 银行卡反面图片
	card_pic2 varchar(60) COMMENT '银行卡反面图片',
	-- 地址
	addr varbinary(256) COMMENT '地址',
	-- 省份
	province varchar(11) COMMENT '省份',
	-- 城市
	city varchar(11) COMMENT '城市',
	-- 县乡
	county varchar(11) COMMENT '县乡',
	-- 银行代码
	bank_id varchar(15) COMMENT '银行代码',
	-- 联行行号
	sub_id varchar(30) COMMENT '联行行号',
	-- 开户行名称
	bank_open varchar(30) COMMENT '开户行名称',
	-- 账户名称
	account_name varchar(60) COMMENT '账户名称',
	-- 账号
	account_number varchar(30) COMMENT '账号',
	-- E码付编号
	pay_code varchar(64) COMMENT 'E码付编号',
	-- 创建者
	create_by varchar(64) NOT NULL COMMENT '创建者',
	-- 创建时间
	create_date datetime NOT NULL COMMENT '创建时间',
	-- 更新者
	update_by varchar(64) NOT NULL COMMENT '更新者',
	-- 更新时间
	update_date datetime NOT NULL COMMENT '更新时间',
	-- 备注信息
	remarks varchar(255) COMMENT '备注信息',
	-- 删除标记（0：正常；1：删除）
	del_flag char(1) DEFAULT '0' NOT NULL COMMENT '删除标记（0：正常；1：删除）',
	PRIMARY KEY (member_id)
);


CREATE TABLE bu_routeway
(
	route_id varchar(5) NOT NULL,
	-- 交易类型
	txn_type varchar(2) NOT NULL COMMENT '交易类型',
	txn_cost varchar(8),
	is_usable char(1),
	contact varchar(40),
	contact_tel varchar(30),
	contact_addr varchar(60),
	-- 创建者
	create_by varchar(64) NOT NULL COMMENT '创建者',
	-- 创建时间
	create_date datetime NOT NULL COMMENT '创建时间',
	-- 更新者
	update_by varchar(64) NOT NULL COMMENT '更新者',
	-- 更新时间
	update_date datetime NOT NULL COMMENT '更新时间',
	-- 备注信息
	remarks varchar(255) COMMENT '备注信息',
	-- 删除标记（0：正常；1：删除）
	del_flag char(1) DEFAULT '0' NOT NULL COMMENT '删除标记（0：正常；1：删除）'
);



/* Create Triggers */

CREATE TRIGGER TRI_bu_member_info_member_id BEFORE INSERT ON bu_member_info
FOR EACH ROW
BEGIN
	SELECT SEQ_bu_member_info_member_id.nextval
	INTO :new.member_id
	FROM dual;
END;



