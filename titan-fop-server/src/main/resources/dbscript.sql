DROP TABLE IF EXISTS `t_tfs_reqsession`;
CREATE TABLE `t_tfs_reqsession` (
  `sessid` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `appkey` varchar(50) NOT NULL COMMENT '应用key',
  `appsecret` varchar(500) NOT NULL COMMENT '应用secret',
  `session` varchar(50) DEFAULT NULL COMMENT '生成的会话字串',
  `createtime` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `isactive` tinyint(4) NOT NULL DEFAULT '1' COMMENT '状态:0-停用 ,1－有效',
  PRIMARY KEY (`sessid`)
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=utf8 COMMENT='请求session列表';

insert into configpara values (config.seq_configpara.nextval,0,'3333','pay.appSecret','DC368712-18A4-4290-9A58-FF995DC161DC','金融付款secret','Y');
insert into configpara values (config.seq_configpara.nextval,0,'3333','pay.serviceUrl','http://192.168.0.14:8090/titan-fop-server/fopapi.shtml','金融付款服务地址','Y');
