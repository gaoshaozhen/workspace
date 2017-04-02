/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 50528
Source Host           : localhost:3306
Source Database       : javashop

Target Server Type    : MYSQL
Target Server Version : 50528
File Encoding         : 65001

Date: 2017-04-02 18:49:45
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `es_brand`
-- ----------------------------
DROP TABLE IF EXISTS `es_brand`;
CREATE TABLE `es_brand` (
  `brand_id` int(8) NOT NULL AUTO_INCREMENT,
  `name` varchar(50) DEFAULT NULL,
  `logo` varchar(255) DEFAULT NULL,
  `keywords` longtext,
  `brief` longtext,
  `url` varchar(255) DEFAULT NULL,
  `disabled` smallint(1) DEFAULT NULL,
  PRIMARY KEY (`brand_id`)
) ENGINE=MyISAM AUTO_INCREMENT=96 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of es_brand
-- ----------------------------
INSERT INTO `es_brand` VALUES ('1', '旺旺', 'http://javashop3.javamall.com.cn/statics/attachment/brand/201202211150124684.jpg', null, '<p>\n	旺旺之业务可追溯至台湾宜兰食品工业股份有限公司。旺旺于1992年正式投资大陆市场，是台湾第一个在大陆注册商标并且拥有最多注册商标的公司，于1994年在湖南设立第一家工厂，旺旺秉持着&ldquo;缘、自信、大团结&rdquo;的经营理念，立志成为&ldquo;综合消费食品王国&rdquo;，向着&ldquo;中国第一，世界第一&rdquo;的目标不断前进。</p>\n', 'http://www.wantwant.com.cn/', '0');
INSERT INTO `es_brand` VALUES ('2', '新农哥', 'http://javashop3.javamall.com.cn/statics/attachment/brand/201202211143461491.jpg', null, '', 'http://http://xinnongge.o-sa.info', '0');
INSERT INTO `es_brand` VALUES ('3', '洽洽', 'http://javashop3.javamall.com.cn/statics/attachment/brand/201202211146563596.jpg', null, '<p>\n	洽洽食品股份有限公司成立于2001年8月9日，位于合肥市国家级经济技术开发区，主要生产坚果炒货类、焙烤类和薯片类等休闲食品。洽洽食品（002557）于2011年3月2日在深圳交易所挂牌上市。2011年6月1日，洽洽食品启动品牌新形象&ldquo;更时尚、更快乐&rdquo;。</p>\n<p>\n	洽洽食品坚持以&ldquo;创造美味产品，传播快乐味道&rdquo;为使命，经过近十多年的发展，产品线日趋丰富，成功推广&ldquo;洽洽香瓜子&rdquo;、洽洽喀吱脆&rdquo;、&ldquo;洽洽小而 香&rdquo;、&ldquo;洽洽怪U味&rdquo;等产品，深受消费者的喜爱，品牌知名度和美誉度不断提升，是中国坚果炒货行业的领军品牌，产品远销国内外30多个国家和地区。</p>\n', 'http://www.qiaqiafood.com', '0');
INSERT INTO `es_brand` VALUES ('4', '百味林', 'http://javashop3.javamall.com.cn/statics/attachment/brand/201202211152387107.gif', null, '<p style=\"color: rgb(102, 102, 102); line-height: 18px; margin-top: 5px; margin-right: 0px; margin-bottom: 5px; margin-left: 0px; padding-top: 0px; padding-right: 0px; padding-bottom: 0px; padding-left: 0px; \">\n	<strong>上海百味林实业有限公司</strong></p>\n<p style=\"color: rgb(102, 102, 102); line-height: 18px; margin: 5px 0px; padding: 0px;\">\n	上海总部地址：上海市嘉定区马陆镇丰功路801号百味林大厦</p>\n<p style=\"color: rgb(102, 102, 102); line-height: 18px; margin: 5px 0px; padding: 0px;\">\n	上海总部电话：021-31273838</p>\n<p style=\"color: rgb(102, 102, 102); line-height: 18px; margin: 5px 0px; padding: 0px;\">\n	电子商务部电话：400-110-3001</p>\n<p style=\"color: rgb(102, 102, 102); line-height: 18px; margin: 5px 0px; padding: 0px;\">\n	传真：021-63739210</p>\n', 'http://www.bwlfood.com/', '0');
INSERT INTO `es_brand` VALUES ('5', '乐事', 'http://javashop3.javamall.com.cn/statics/attachment/brand/201202211154567239.jpg', null, '', 'http://www.lays.com.cn/', '0');
INSERT INTO `es_brand` VALUES ('6', '上好佳', 'http://javashop3.javamall.com.cn/statics/attachment/brand/201202211156220232.gif', null, '<p>\n	上好佳控股有限公司由施恭旗董事长领导，作为一个企业家，他从亚洲地区小企业家们敢做敢为寻求财富的精神中汲取灵感、受到鼓舞。上好佳公司以享有这种精神的亚洲企业而感到自豪。</p>\n', 'http://www.oishi-tm.com/', '0');
INSERT INTO `es_brand` VALUES ('7', '天喔', 'http://javashop3.javamall.com.cn/statics/attachment/brand/201202211200113292.jpg', null, '<p>\n	上海天喔食品（集团）有限公司是一家以蜜饯、炒货、南北货、麦片生产为主，其他休闲食品生产为辅的著名食品生产企业。成立于1999年12月，总投资 11320.75万港元，注册资本8490.75万港元，为沪港合资。公司地处上海市松江区泗泾镇，总占地面积65亩。生产车间完全参照标准药品洁净厂房 要求设计修建。设施先进，环境优美，年均产值过亿元。</p>\n', 'http://www.tenwowfood.com', '0');
INSERT INTO `es_brand` VALUES ('8', '金帝', 'http://javashop3.javamall.com.cn/statics/attachment/brand/201202221015047090.jpg', null, '<p>\n	<font size=\"-1\">源自欧洲，由瑞士<em>巧克力</em>大师亲自调制。多年来，严格坚持欧洲甄选标准,精心挑选上乘&ldquo;黄金可可&rdquo;，造就了至浓可可味的经典<em>巧克力</em></font></p>\n', 'http://www.leconte.com.cn/', '0');
INSERT INTO `es_brand` VALUES ('9', '好时', 'http://javashop3.javamall.com.cn/statics/attachment/brand/201202221018146247.gif', null, '', 'http://www.hersheys.com', '0');
INSERT INTO `es_brand` VALUES ('10', '瑞士莲', 'http://javashop3.javamall.com.cn/statics/attachment/brand/201202221035341549.gif', null, '', 'http://www.lindtusa.com', '0');
INSERT INTO `es_brand` VALUES ('11', '德芙', 'http://javashop3.javamall.com.cn/statics/attachment/brand/201202221036549178.jpg', null, '', 'http://www.dovechocolate.com.cn/', '0');
INSERT INTO `es_brand` VALUES ('12', '可口可乐', 'http://javashop3.javamall.com.cn/statics/attachment/brand/201202221038391096.jpg', null, '', 'http://www.coca-cola.com', '0');
INSERT INTO `es_brand` VALUES ('13', '百事', 'http://javashop3.javamall.com.cn/statics/attachment/brand/201202221040322598.jpg', null, '', 'http://www.pepsi.com', '0');
INSERT INTO `es_brand` VALUES ('14', '农夫山泉', 'http://javashop3.javamall.com.cn/statics/attachment/brand/201202221042275274.jpg', null, '', 'http://www.nfsq.com.cn/app/appOut.action', '0');
INSERT INTO `es_brand` VALUES ('15', '五粮液', 'http://javashop3.javamall.com.cn/statics/attachment/brand/201202221044295459.jpg', null, '<p>\n	五粮液集团有限公司前身是50年代初8家古传酿酒作坊联合组建而成的&ldquo;中国专卖公司四川省宜宾酒厂&rdquo;，1959年正式命名为&ldquo;宜宾五粮液酒厂&rdquo;，1998年改制为&ldquo;四川省宜宾五粮液集团有限公司&rdquo;。</p>\n', 'http://www.wuliangye.com.cn/', '0');
INSERT INTO `es_brand` VALUES ('16', '阿尔卑斯', 'http://javashop3.javamall.com.cn/statics/attachment/brand/201202221654250514.jpg', null, '<p>\n	<font size=\"-1\"><em>阿尔卑斯</em>全球最大的糖果生产企业之一，专业从事各种糖果和胶姆糖的生产和销售。其中比较有代表性的就是<em>阿尔卑斯</em>棒棒糖和<em>阿尔卑斯</em>糖。<em>阿尔卑斯</em>糖表达爱只需一颗<em>阿尔卑斯</em>。</font></p>\n', 'http://www.acandy.cn/', '0');
INSERT INTO `es_brand` VALUES ('17', '大白兔', 'http://javashop3.javamall.com.cn/statics/attachment/brand/201202221658088525.jpg', null, '', 'http://www.guanshengyuan.com.cn/dabaitu/', '0');
INSERT INTO `es_brand` VALUES ('18', '伊利', 'http://javashop3.javamall.com.cn/statics/attachment/brand/201202221659311713.jpg', null, '', 'http://www.yili.com/', '0');
INSERT INTO `es_brand` VALUES ('19', '益达', 'http://javashop3.javamall.com.cn/statics/attachment/brand/201202221702199745.jpg', null, '', 'http://www.extra-city.com/', '0');
INSERT INTO `es_brand` VALUES ('20', '绿箭', 'http://javashop3.javamall.com.cn/statics/attachment/brand/201202221707525542.jpg', null, '', 'http://www.wrigley.com.cn/', '0');
INSERT INTO `es_brand` VALUES ('21', '好丽友', 'http://javashop3.javamall.com.cn/statics/attachment/brand/201202221709247073.jpg', null, '', 'http://www.orion.cn/product/index.aspx', '0');
INSERT INTO `es_brand` VALUES ('22', '康师傅', 'http://javashop3.javamall.com.cn/statics/attachment/brand/201202221727178594.jpg', null, '<p>\n	康师傅主要在中国从事生产和销售方便面饮品及糕饼。2011年连续第三年获得福布斯亚洲50强称号,品牌价值10.66亿美元。</p>\n', 'http://www.masterkong.com.cn/', '0');
INSERT INTO `es_brand` VALUES ('23', '娃哈哈', 'http://javashop3.javamall.com.cn/statics/attachment/brand/201202221728161737.gif', null, '', 'http://www.wahaha.com.cn/', '0');
INSERT INTO `es_brand` VALUES ('24', '统一', 'http://javashop3.javamall.com.cn/statics/attachment/brand/201202221729517242.jpg', null, '', 'http://www.uni-president.com.cn/', '0');
INSERT INTO `es_brand` VALUES ('25', '郎酒', null, null, '<p>\n	四川郎酒集团，是一个以生产销售中国名酒郎酒和房地产开发的大型现代化企业集团。</p>\n<p align=\"left\">\n	郎酒，始于1903年，产自川黔交界有&ldquo;中国美酒河&rdquo;之称的赤水河畔，是目前中国唯一一家采用山泉水酿造的白酒企业。</p>\n<p>\n	郎酒历史可追溯到汉武帝时期，当时宫廷贡酒&ldquo;枸酱酒&rdquo;即是郎酒前身。从&ldquo;絮志酒厂&rdquo;、&ldquo;惠川糟房&rdquo;到&ldquo;集义糟房&rdquo;的&ldquo;回沙郎酒&rdquo;开始，至今已有100多年的悠久历史。</p>\n', 'http://www.langjiu.cn/', '0');
INSERT INTO `es_brand` VALUES ('26', '洋河', 'http://javashop3.javamall.com.cn/statics/attachment/brand/201202221742498256.jpg', null, '', 'http://www.chinayanghe.com/', '0');
INSERT INTO `es_brand` VALUES ('27', '百威', null, null, '', 'http://www.budweiser.com/public/age-gate11.aspx?ReturnUrl=/d', '0');
INSERT INTO `es_brand` VALUES ('28', '哈尔滨', 'http://javashop3.javamall.com.cn/statics/attachment/brand/201202221747410265.jpg', null, '', 'http://www.harbin-beer.cn', '0');
INSERT INTO `es_brand` VALUES ('29', '爱士堡', null, null, '', 'http://www.harbin-beer.cn', '0');
INSERT INTO `es_brand` VALUES ('30', '百利', null, null, '', 'http://www.baileys.com.cn', '0');
INSERT INTO `es_brand` VALUES ('31', '富豪', null, null, '', 'http://www.harbin-beer.cn', '0');
INSERT INTO `es_brand` VALUES ('32', '欧德堡', null, null, '', 'http://www.oldelburg.com/', '0');
INSERT INTO `es_brand` VALUES ('33', '多美鲜', null, null, '', 'http://www.chnimport.com', '0');
INSERT INTO `es_brand` VALUES ('34', '爱尔曼', null, null, '', 'http://#', '0');
INSERT INTO `es_brand` VALUES ('35', '丹麦蓝罐', null, null, '', 'http://www.baidu.com', '0');
INSERT INTO `es_brand` VALUES ('36', '莱家', null, null, '', 'http://www.baidu.com', '0');
INSERT INTO `es_brand` VALUES ('37', '北田', null, null, '', 'http://www.baidu.com', '0');
INSERT INTO `es_brand` VALUES ('38', '玛勃洛可', 'http://javashop3.javamall.com.cn/statics/attachment/brand/201202222100337891.jpg', null, '<p>\n	　　老上海人可能不熟悉斯里兰卡茶，但永远不会忘记锡兰茶，所谓的锡兰茶，就是上世纪初进口到中国的英式红茶。 中国是茶叶的故乡，也是世界上最大的茶叶生产、消费和出口国之一。在漫长的岁月中，中国的茶叶经过海上贸易传遍全球，改变了无数人的生活方式，也推进了世界经</p>\n', 'http://www.mabroc.cn', '0');
INSERT INTO `es_brand` VALUES ('39', 'AKBAR', null, null, '', 'http://www.akbarsilverlake.com/', '0');
INSERT INTO `es_brand` VALUES ('40', '川宁', null, null, '', 'http://www.baidu.com', '0');
INSERT INTO `es_brand` VALUES ('41', '卡司诺', null, null, '', 'http://www.baidu.com', '0');
INSERT INTO `es_brand` VALUES ('42', '益昌老街', null, null, '', 'http://www.baidu.com', '0');
INSERT INTO `es_brand` VALUES ('43', '卡度', null, null, '', 'http://www.baidu.com', '0');
INSERT INTO `es_brand` VALUES ('44', '张君雅', 'http://javashop3.javamall.com.cn/statics/attachment/brand/201202222116468759.jpg', null, '', 'http://www.weilih.com.tw/', '0');
INSERT INTO `es_brand` VALUES ('45', '鲜得味', null, null, '', 'http://www.centurytuna.com/', '0');
INSERT INTO `es_brand` VALUES ('46', '海牌', null, null, '', 'http://www.baidu.com', '0');
INSERT INTO `es_brand` VALUES ('47', 'Olay/玉兰油', null, null, '', 'http://www.olay.com.cn', '0');
INSERT INTO `es_brand` VALUES ('48', '鲜之园', null, null, '', 'http://www.xianzhiyuan.com', '0');
INSERT INTO `es_brand` VALUES ('49', 'Hunts/汉斯', null, null, '', 'http://www.baidu.com', '0');
INSERT INTO `es_brand` VALUES ('50', '可康', null, null, '', 'http://www.kjkk.cc/', '0');
INSERT INTO `es_brand` VALUES ('51', 'PomPotes/梦果鲜', null, null, '', 'http://www.baidu.com', '0');
INSERT INTO `es_brand` VALUES ('52', 'Shiseido/资生堂', 'http://javashop3.javamall.com.cn/statics/attachment/brand/201202222258101961.jpg', null, '', 'http://www.shiseidochina.com/', '0');
INSERT INTO `es_brand` VALUES ('53', '迪丽', null, null, '', 'http://www.baidu.com', '0');
INSERT INTO `es_brand` VALUES ('54', '花间公主', null, null, '', 'http://www.baidu.com/', '0');
INSERT INTO `es_brand` VALUES ('55', '城市生活', null, null, '', 'http://www.baidu.com/', '0');
INSERT INTO `es_brand` VALUES ('56', 'Inoherb/相宜本草', 'http://javashop3.javamall.com.cn/statics/attachment/brand/201202231016206426.jpg', null, '<p>\n	相宜本草&mdash;&mdash;一个诠释&ldquo;本草养肤&rdquo;概念的品牌。悠久灿烂、博大精深的中医文化充满奥妙与智慧。相宜本草深信：天然神奇的中草药对皮肤的改善是安全的、有效的，而且高品质的天然护肤品应该是平易近人的，人人都能拥有。源于这份初衷，2000年&ldquo;相宜本草&rdquo;品牌诞生了。<br />\n	<br />\n	中医强调的&ldquo;标本兼治&rdquo;理念，正是相宜本草养肤之道的来由。 因为，女人的美，从来都是由内而外。 即使外表平凡， 强大的内在力量亦让美丽绽放； 倘若丽质天生，内在力量更令你魅力加分。 肌肤更是如此，强韧的肌肤能量，内在的活力和健康，才是肌肤真正的美丽源泉。</p>\n', 'http://www.inoherb.com/', '0');
INSERT INTO `es_brand` VALUES ('57', 'Mentholatum/曼秀雷敦', 'http://javashop3.javamall.com.cn/statics/attachment/brand/201202231018171140.png', null, '', 'http://www.mentholatum.com.cn/', '0');
INSERT INTO `es_brand` VALUES ('58', 'Herborist/佰草集', 'http://javashop3.javamall.com.cn/statics/attachment/brand/201202231020005541.gif', null, '<p style=\" padding-left:20px;\">\n	受千年本草美颜文化启迪，中国第一套具有完整意义的中草药护理品牌&mdash;&mdash;佰草集于1998年清新诞生。糅合中国美颜经典与现代生物科技，以本草古方配伍为特色，佰草集逐步实现着中国文化中追求&ldquo;自然、平衡&rdquo;的亘美境界，缔造了一个本草养美颜的传奇。</p>\n<p style=\" padding-left:20px;\">\n	采天地灵气，化气韵和谐，佰草集萃取自中国经典草药精华，提出以&ldquo;证&rdquo;、&ldquo;方&rdquo;、&ldquo;效&rdquo;为核心的严谨的理论体系：以中医理论辩肌肤问题之证，以现代科技焕活传世古方，以内在调养之法达到养护肌肤之效，开启中草药养美的全新风尚。</p>\n<p style=\"padding-left: 20px;\">\n	享受自然的绿色馈赠，更将绿色之美还给自然,佰草集自创立以来，一直倡导绿色环保的品牌理念，提出&ldquo;养出地球之美&rdquo;的绿色时尚宣言。现在，佰草集更走出国门，以其独特的中国文化底蕴和全新品牌形象在法国以及其他国际市场中引领中医养美潮流，与全球分享&ldquo;美自根源养有方&rdquo;的佰草集养美之理念！</p>\n', 'http://www.herborist.com.cn/', '0');
INSERT INTO `es_brand` VALUES ('59', '海飞丝', 'http://javashop3.javamall.com.cn/statics/attachment/brand/201202231146497650.jpg', null, '', 'http://scalptrends.ellechina.com/', '0');
INSERT INTO `es_brand` VALUES ('60', '潘婷', 'http://javashop3.javamall.com.cn/statics/attachment/brand/201202231147529126.gif', null, '', 'http://www.pantene.com.cn/', '0');
INSERT INTO `es_brand` VALUES ('61', '飘柔', 'http://javashop3.javamall.com.cn/statics/attachment/brand/201202231149270759.jpg', null, '', 'http://www.pg.com.cn/', '0');
INSERT INTO `es_brand` VALUES ('62', '多美滋', null, null, '', 'http://1000day.dumex.com.cn/', '0');
INSERT INTO `es_brand` VALUES ('63', '美赞臣', 'http://javashop3.javamall.com.cn/statics/attachment/brand/201202231159446345.jpg', null, '', 'http://www.meadjohnson.com.cn/', '0');
INSERT INTO `es_brand` VALUES ('64', '光明', null, null, '', 'http://www.brightdairy.com/', '0');
INSERT INTO `es_brand` VALUES ('65', '雅培', null, null, '', 'http://www.abbottmama.com.cn/', '0');
INSERT INTO `es_brand` VALUES ('66', 'Nestle/雀巢', 'http://javashop3.javamall.com.cn/statics/attachment/brand/201202231204385546.jpg', null, '', 'http://www.nestle.com.cn/', '0');
INSERT INTO `es_brand` VALUES ('67', '妈咪宝贝', null, null, '', 'http://www.unicharm.com.cn/', '0');
INSERT INTO `es_brand` VALUES ('68', '帮宝适', null, null, '', 'http://goldensleep.pampers.com.cn/', '0');
INSERT INTO `es_brand` VALUES ('69', 'Johnson/强生', null, null, '', 'http://www.johnsonsbaby.com.cn/index.html', '0');
INSERT INTO `es_brand` VALUES ('70', '好奇', null, null, '', 'http://www.kimberly-clark.com.cn/kcc_huggies/', '0');
INSERT INTO `es_brand` VALUES ('71', 'Bonyee/邦怡', null, null, '', 'http://www.bonyee.com/', '0');
INSERT INTO `es_brand` VALUES ('72', '良良', null, null, '', 'http://www.llyez.com', '0');
INSERT INTO `es_brand` VALUES ('73', '喜亲宝', null, null, '', 'http://www.tjxqb.com.cn/', '0');
INSERT INTO `es_brand` VALUES ('74', '三月桃花', null, null, '', 'http://www.baidu.com', '0');
INSERT INTO `es_brand` VALUES ('75', '亨氏', 'http://javashop3.javamall.com.cn/statics/attachment/brand/201202232302299961.jpg', null, '', 'http://www.heinz.com.cn/', '0');
INSERT INTO `es_brand` VALUES ('76', '伊威', 'http://javashop3.javamall.com.cn/statics/attachment/brand/201202232303236077.gif', null, '', 'http://www.sh-eastwes.com/index.html', '0');
INSERT INTO `es_brand` VALUES ('77', '欧格尼', 'http://javashop3.javamall.com.cn/statics/attachment/brand/201202232304418701.gif', null, '', 'http://www.ognfood.com/', '0');
INSERT INTO `es_brand` VALUES ('78', 'Pigeon/贝亲', null, null, '', 'http://www.pigeon.cn', '0');
INSERT INTO `es_brand` VALUES ('79', '嗳呵', null, null, '', 'http://www.elsker.com/', '0');
INSERT INTO `es_brand` VALUES ('80', '铃兰', 'http://javashop3.javamall.com.cn/statics/attachment/brand/201202232346471548.jpg', null, '', 'http://www.suzuran.cn/', '0');
INSERT INTO `es_brand` VALUES ('81', '爱得利', 'http://javashop3.javamall.com.cn/statics/attachment/brand/201202232350075147.jpg', null, '', 'http://www.ivorybaby.com/', '0');
INSERT INTO `es_brand` VALUES ('82', '小榕树', null, null, '', 'http://www.xici.net/', '0');
INSERT INTO `es_brand` VALUES ('83', '1号药网', null, null, '', 'http://www.yihaodian.com', '0');
INSERT INTO `es_brand` VALUES ('84', '利其尔', null, null, '', 'http://www.richell.co.jp', '0');
INSERT INTO `es_brand` VALUES ('85', '亲亲我', 'http://javashop3.javamall.com.cn/statics/attachment/brand/201202240003319744.jpg', null, '', 'http://www.kidsme.cn/', '0');
INSERT INTO `es_brand` VALUES ('86', '美美', null, null, '<p>\n	美美</p>\n', 'http://www.baidu.com', '0');
INSERT INTO `es_brand` VALUES ('87', '丹尼熊', null, null, '<p>\n	丹尼熊</p>\n', 'http://www.baidu.com', '0');
INSERT INTO `es_brand` VALUES ('88', '蒂乐', null, null, '', 'http://dele.wumeiwang.com', '0');
INSERT INTO `es_brand` VALUES ('89', '皇家宝贝', null, null, '', 'http://www.royalsonny.com.cn', '0');
INSERT INTO `es_brand` VALUES ('90', 'ASD/爱仕达', 'http://javashop3.javamall.com.cn/statics/attachment/brand/201202242158308119.jpg', null, '', 'http://www.chinaasd.com/wwwroot/index.htm', '0');
INSERT INTO `es_brand` VALUES ('91', '双立人', null, null, '', 'http://www.zwilling.com.cn/index.aspx', '0');
INSERT INTO `es_brand` VALUES ('92', 'ROICHEN', null, null, '', 'http://www.baidu.com', '0');
INSERT INTO `es_brand` VALUES ('93', 'Xingcai/兴财', null, null, '', 'http://www.xingcai.com/', '0');
INSERT INTO `es_brand` VALUES ('94', '张小泉', null, null, '', 'http://www.zhangxiaoquan.cn/', '0');
INSERT INTO `es_brand` VALUES ('95', '金仕顿(Kingstone)', null, null, '', 'http://www.kingstone.com', '0');
