package com.yunhan.scc.backto.web.model.masterdata;

import com.yunhan.scc.tools.component.module.query.QueryCondition;
import java.lang.Integer;
import java.lang.String;
import java.math.BigDecimal;
import java.util.Date;

/**
 * Masterdata查询条件
 * @author luohoudong
 * @version 2016-7-22 10:02:24
 */
public class MasterdataBacktoCondition extends QueryCondition {
	
	/**
	 * 丛书名 有则必填
页面控制 最大100
注意：如该书有2个或以上的丛书名，则中间用；分隔。  示例：名石鉴赏投资指南；名石鉴赏
	 */
	private String affiliateseries;
	/**
	 * 亚马逊介质
	 */
	private String amazonmedium;
	/**
	 * 亚马逊版本
	 */
	private String amazonversion;
	/**
	 * 注释 有则必填 页面控制100
	 */
	private String annotations;
	/**
	 * 预售到货时间 选填  页面控制10位
格式：定长为8的数字；
示例：2014-08-05
格式：请输入10为数字的日期（YYYYMMDD)，如：2014-08-01；
注意：当没有精确到日时，请用“01"代替，如：2014-08-01。
示例：2014-08-01
	 */
	private String arrivedadvancetime;
	/**
	 * 副标题
	 */
	private String assistbooktitle;
	/**
	 * 读者对象 有则必填 页面控制100位
注意：
（1）请填写图书所适应的读者对象，如少儿、孕产妇、青少年、成人、专业技术人员、中小学生、大中专学生、教育工作者、中老年人、普通大众等等；
（2）少儿类请填写：0-2、3-6、7-10、11-14等
文本录入框
	 */
	private String audience;
	/**
	 * 作者国籍
	 */
	private String authorcitizen;
	/**
	 * 作者 必填
（1）以图书封面描述的作者属性为准（封面未描述的，以版权页的描述为准），分别填列。注：编著放在著者字段，编绘放在绘者字段。
（2）有多个人名的，以逗号隔开；
（3）国外作者的国籍以[]符号放在名字的前面，外文名字以符号放在中文名后面；示例：[美]凯利?麦格尼格尔 (Kelly McGonigal Ph.D.)
（4）作者的朝代以[]符号放在名字的前面；示例：[汉]
    示例：黑柳彻子
页面控制 最大100
	 */
	private String authoreditor;
	/**
	 * 每包册数 选填 页面控制2位整数
 文本录入框
格式：数字
注意：请按照包装情况填写。
示例：40
	 */
	private Integer bagPernum;
	/**
	 * 正文语种 必填 页面控制20
（1）填写图书正文的语种。示例：正文语言：中文。
（2）如果遇到图书正文有多语种的，中间用“；”号隔开。录入格式：例：中文；英文；韩文。
	 */
	private String bodylanguage;
	/**
	 * 附赠
	 */
	private String bonus;
	/**
	 * 附赠数量
	 */
	private String bonusnum;
	/**
	 * 套装册数 有则必填
当【是否套装书】选项为是时，该字段启用为必填 ；且该字段为数字、必填
最大3位
	 */
	private Integer bookletnum;
	/**
	 * 书名 必填  页面 100长度控制
	 */
	private String booktitle;
	/**
	 * 品牌 必填  页面控制200
文本录入框
注意：请填列商品的品牌或制造商。
示例：南海出版公司
	 */
	private String brandname;
	/**
	 * 出版分类（小类） 选填 页面控制 20
文本录入框
示例：外国儿童文学
	 */
	private String childrentpublishercode;
	/**
	 * CIP数据核字 选填 页面控制 50
 版权页所显示的“CIP数据核字
 文本录入框
 示例：第23423号
	 */
	private String cipnum;
	/**
	 * 中图法分类号 必填 页面控制20位
文本录入框
注意：按照版权页上面的信息进行录入。  示例：TP11.1
	 */
	private String classification;
	/**
	 * 封面书名
	 */
	private String coverbooktitle;
	/**
	 * 创建者 数据上传者，页面提交时提供的用户
记录用户表登录名
	 */
	private String creator;
	/**
	 * 学科关键词 选填  页面控制100长度
文本录入框  "注意：
（1）请填写事件关键词是指作品主要涉及到的事件
（2）如有多个关键词，用""；""隔开"
	 */
	private String disciplinekeyword;
	/**
	 * 绘者 有则必填 页面最大控制 100
"注意：
（1）以图书封面描述的作者属性为准（封面未描述的，以版权页的描述为准），分别填列。注：编著放在著者字段，编绘放在绘者字段。
（2）有多个人名的，以逗号隔开；
（3）国外作者的国籍以[]符号放在名字的前面，外文名字以符号放在中文名后面；示例：[美]凯利?麦格尼格尔 (Kelly McGonigal Ph.D.)
（4）作者的朝代以[]符号放在名字的前面；示例：[汉]"  示例：岩崎千弘
	 */
	private String drawing;
	/**
	 * 出版社 必填 页面控制最大50
出版社/版别
（1）有多个出版社的，只填ISBN对应的出版社；
（2）无法从ISBN中识别出版社的，必须填列；
（3）同一家出版社应保持名称一致。"
当出版社号值非空时，系统自动由《出版社及社号对应原则》匹配对；当无法匹配时，人工录入
示例：南海出版公司
	 */
	private String edition;
	/**
	 * 出版日期 控制1：必填；
控制2：值为10位数字；
格式：请输入10位数字的日期（YYYY-MM-DD)，如：2014-08-01；
注意：当没有精确到日时，请用“01"代替，如：2014-08-01。
	 */
	private String editionyearmonth;
	/**
	 * 编者 有则必填 页面控制 最大100
"注意：
（1）以图书封面描述的作者属性为准（封面未描述的，以版权页的描述为准），分别填列。注：编著放在著者字段，编绘放在绘者字段。
（2）有多个人名的，以逗号隔开；
（3）国外作者的国籍以[]符号放在名字的前面，外文名字以符号放在中文名后面；示例：[美]凯利?麦格尼格尔 (Kelly McGonigal Ph.D.)
（4）作者的朝代以[]符号放在名字的前面；示例：[汉]"  示例：陈明俊猿渡静子
	 */
	private String editor;
	/**
	 * 校注 有则必填 页面控制100
	 */
	private String emendation;
	/**
	 * 外文书名 有则必填
首字母大写，所有实词第一个字母大写，其余字母要小写。
可以有特殊字符 ，需要防止注入 如，
页面控制最大 10
	 */
	private String enbookname;
	/**
	 * 事件关键词 选填  页面控制100长度
文本录入框  "注意：
（1）请填写事件关键词是指作品主要涉及到的事件
（2）如有多个关键词，用""；""隔开"
  示例：九一八事件
	 */
	private String eventkeyword;
	/**
	 * 首版日期 格式：定长为10的数字。示例：2014-08-01
	 */
	private String firsteditionyearmonth;
	/**
	 * 印数 控制1：必填 页面控制10位
控制2：值为数字
示例：10000
	 */
	private Integer firstpulish;
	/**
	 * 开本 必填  页面控制20
文本录入框
 示例：32K
	 */
	private String format;
	/**
	 * 商品介质
	 */
	private String goodsmedium;
	/**
	 * 商品种类
	 */
	private String goodsvariety;
	/**
	 * 高 选填  页面5位整数2位小数
文本录框录入  格式：数字  示例：40
	 */
	private BigDecimal high;
	/**
	 * 历史一号多书
	 */
	private Integer historonlybook;
	/**
	 * 历史书名
	 */
	private String historybooktitle;
	/**
	 * 历史ISBN
	 */
	private String historyisbn;
	/**
	 * 历史定价
	 */
	private BigDecimal historyrice;
	/**
	 * 
	 */
	private Long id;
	/**
	 * 印次 必填 页面控制2位
格式：数字；
示例：3
	 */
	private Integer impression;
	/**
	 * 是否预售 下拉选择框 常数
值包括：是、否
0：否 1：是
	 */
	private Integer isadvance;
	/**
	 * ISBN 由于ISBN位数不定，存在多种形态12位 13位 小于12位
主要用于页面展示使用

控制1：必填
控制2：前三位字母为数字；应有“0~9”或“A~Z”或“a~z”组成；最小长度大于等于3位，最大长度小于等于13位；
如果填写了条形码没有填写ISBN号 可以根据条形码转换成ISBN
ISBN不能带特殊符号
	 */
	private String isbn;
	/**
	 * 是否重点品 必填
是否为重点：常数
            0：否，1：是
	 */
	private Integer isimportant;
	/**
	 * 是否一号多书 必填
是否为一号多书： 常数
            0：否，1：是 2：未填写一号多书
	 */
	private Integer isoveronlybook;
	/**
	 * 是否套装书 必填
是否为套装书 常数
             0：否 1：是
	 */
	private Integer issuitbook;
	/**
	 * 主题词 必填  页面控制100长度
文本录入框  注意：严格按照版权页录入，版权页中由罗马数字标示的部分，其中Ⅲ后面所标示的，即为主题词。  示例：传记-画册
	 */
	private String keyword;
	/**
	 * 长 选填  页面5位整数2位小数
文本录框录入  格式：数字  示例：40
	 */
	private BigDecimal length;
	/**
	 * 营销分类 必填 常数
下拉选择框
值包括：社科、文艺、文教、少儿、科技、教材
	 */
	private String marketingcategories;
	/**
	 * 条码号 必填
待分割线的ISBN
如果页面未录入的时候，默认与ISBN一致
最大  25长度
	 */
	private String mastercommoditycode;
	/**
	 * 普通关键词 选填  页面控制100长度
文本录入框  "注意：
（1）请填写事件关键词是指作品主要涉及到的事件
（2）如有多个关键词，用""；""隔开"
	 */
	private String ordinarykeyword;
	/**
	 * 正书名
	 */
	private String origbooktitle;
	/**
	 * 页码 必填 页面控制 10位
（1）格式：数字；
（2）根据图书的实际页码进行录入，仅能填写数字；套装书把每册的页码相加填列。
例：304
	 */
	private Integer pagenumber;
	/**
	 * 用纸 必填 页面控制50
文本录入框
注意：按照书籍正文实际用纸填写。
示例：胶版纸
	 */
	private String paper;
	/**
	 * 装帧 必填 常数
下拉选择框
值包括：平装、精装、简装、线装、盒装、软精装、袋装、盒函装
	 */
	private String paperback;
	/**
	 * 出版分类（大类） 选填 页面控制 20
文本录入框
示例：童书
	 */
	private String parentpublishercode;
	/**
	 * 出版地 选填 页面控制 最大20
填写版权页上CIP数据中标明的地址。如：北京
	 */
	private String placepublished;
	/**
	 * 定价 必填
格式：数值  示例：39.08
最大 1000000 保留2位小数
	 */
	private BigDecimal price;
	/**
	 * 印刷日期 格式：定长为10的数字。示例：2014-08-01
	 */
	private String printyearmonth;
	/**
	 * 出版者国别 有则必填 页面控制最大20
（1）非中国大陆出版机构出版的图书必须填列；
（2）如为空，平台将默认为中国大陆。
	 */
	private String publisherassigneecountry;
	/**
	 * 出版社号 控制1：必填 页面控制 最大20
控制2：值必须为”数字“和”-“组成
控制3：当出版社值非空时，系统自动由《出版社及社号对应原则》匹配对；当无法匹配时，人工录入
从ISBN号中978后的第一、二节数字；
 示例：如：ISBN为978-7-111-47639-9，则版别号为：7-111
	 */
	private String publishercode;
	/**
	 * 上架建议 选填 页面控制 100
文本录入框
示例：少儿文学
	 */
	private String recommendSaleGroup;
	/**
	 * 备注 大字段
	 */
	private String rememo;
	/**
	 * 交替书名
	 */
	private String replacebooktitle;
	/**
	 * 印张 选填
文本录入框
格式：数字
注意：按照版权页上列示的填写
示例：8.323
	 */
	private BigDecimal sheet;
	/**
	 * 占用状态     使用常数定义 ISEMPTY                       未占用     表示当前商品暂未被任何加工或审核占用
INFORMATIONONLY    信息占用 表示当前商品正在进行信息加工
APPENDONLY               附件占用 表示当前商品所关联的附件批次正在进行附件加工
ENTITYONLY                实物占用 表示当前商品正在进行实物加工或实物审核
APPROVED                   审核通过 表示实物的审核通过数据不能被更改
	 */
	private String state;
	/**
	 * 占用方式     使用常数定义 INFORMATION    信息
APPEND              附件
ENTITY                实物
	 */
	private String taken;
	/**
	 * 税率 系统后台自动默认该值为“13%”；
	 */
	private String taxrate;
	/**
	 * 译者 有则必填  页面控制 最大100
"注意：
（1）以图书封面描述的作者属性为准（封面未描述的，以版权页的描述为准），分别填列。注：编著放在著者字段，编绘放在绘者字段。
（2）有多个人名的，以逗号隔开；
（3）国外作者的国籍以[]符号放在名字的前面，外文名字以符号放在中文名后面；示例：[美]凯利?麦格尼格尔 (Kelly McGonigal Ph.D.)
（4）作者的朝代以[]符号放在名字的前面；示例：[汉]"  示例：赵玉皎
	 */
	private String translator;
	/**
	 * 随书附件 有则必填 页面控制100
文本录入框
注意：请写明随书附送的物品及数量。
      示例：附光盘1张
	 */
	private String typeandquantity;
	/**
	 * 上传时间 第一次进入待发表时间
	 */
	private Date uploaddate;
	/**
	 * 版次 必填  下拉选择 常数
格式：第X版。例如：第3版
值包括：第1版…..第30版
	 */
	private String version;
	/**
	 * 版本
	 */
	private String versionnotes;
	/**
	 * 商品重量 选填  页面5位整数2位小数
文本录框录入
格式：数字；单位为克
示例： 399
	 */
	private BigDecimal weight;
	/**
	 * 宽 选填  页面5位整数2位小数
文本录框录入  格式：数字  示例：40
	 */
	private BigDecimal wide;
	/**
	 * 字数 必填 页面控制20位
（1）格式：数字+千字；
（2）按照版权页录入。示例：300千字
	 */
	private String wordnumber;
	/**
	 * 平台ID 平台对商品定义的唯一号
	 */
	private String yunhanId;
	
	/**
	 * 折扣
	 */
	private BigDecimal discountrate;
	/**
	 * 采购商id
	 */
	private String purchaserid;
	/**
	 * 供应商id
	 */
	private String supplierid;
	/**
	 * keyId
	 */
	private String keyId;
	/**
	 * 丛书名 有则必填
页面控制 最大100
注意：如该书有2个或以上的丛书名，则中间用；分隔。  示例：名石鉴赏投资指南；名石鉴赏
	 * @param affiliateseries
	 */
	
	public void setAffiliateseries(String affiliateseries){
		this.affiliateseries=affiliateseries;
	}
	/**
	 * 丛书名 有则必填
页面控制 最大100
注意：如该书有2个或以上的丛书名，则中间用；分隔。  示例：名石鉴赏投资指南；名石鉴赏
	 * @return
	 */
	public String getAffiliateseries(){
		return this.affiliateseries;
	}
		
	/**
	 * 亚马逊介质
	 * @param amazonmedium
	 */
	
	public void setAmazonmedium(String amazonmedium){
		this.amazonmedium=amazonmedium;
	}
	/**
	 * 亚马逊介质
	 * @return
	 */
	public String getAmazonmedium(){
		return this.amazonmedium;
	}
		
	/**
	 * 亚马逊版本
	 * @param amazonversion
	 */
	
	public void setAmazonversion(String amazonversion){
		this.amazonversion=amazonversion;
	}
	/**
	 * 亚马逊版本
	 * @return
	 */
	public String getAmazonversion(){
		return this.amazonversion;
	}
		
	/**
	 * 注释 有则必填 页面控制100
	 * @param annotations
	 */
	
	public void setAnnotations(String annotations){
		this.annotations=annotations;
	}
	/**
	 * 注释 有则必填 页面控制100
	 * @return
	 */
	public String getAnnotations(){
		return this.annotations;
	}
		
	/**
	 * 预售到货时间 选填  页面控制10位
格式：定长为8的数字；
示例：2014-08-05
格式：请输入10为数字的日期（YYYYMMDD)，如：2014-08-01；
注意：当没有精确到日时，请用“01"代替，如：2014-08-01。
示例：2014-08-01
	 * @param arrivedadvancetime
	 */
	
	public void setArrivedadvancetime(String arrivedadvancetime){
		this.arrivedadvancetime=arrivedadvancetime;
	}
	/**
	 * 预售到货时间 选填  页面控制10位
格式：定长为8的数字；
示例：2014-08-05
格式：请输入10为数字的日期（YYYYMMDD)，如：2014-08-01；
注意：当没有精确到日时，请用“01"代替，如：2014-08-01。
示例：2014-08-01
	 * @return
	 */
	public String getArrivedadvancetime(){
		return this.arrivedadvancetime;
	}
		
	/**
	 * 副标题
	 * @param assistbooktitle
	 */
	
	public void setAssistbooktitle(String assistbooktitle){
		this.assistbooktitle=assistbooktitle;
	}
	/**
	 * 副标题
	 * @return
	 */
	public String getAssistbooktitle(){
		return this.assistbooktitle;
	}
		
	/**
	 * 读者对象 有则必填 页面控制100位
注意：
（1）请填写图书所适应的读者对象，如少儿、孕产妇、青少年、成人、专业技术人员、中小学生、大中专学生、教育工作者、中老年人、普通大众等等；
（2）少儿类请填写：0-2、3-6、7-10、11-14等
文本录入框
	 * @param audience
	 */
	
	public void setAudience(String audience){
		this.audience=audience;
	}
	/**
	 * 读者对象 有则必填 页面控制100位
注意：
（1）请填写图书所适应的读者对象，如少儿、孕产妇、青少年、成人、专业技术人员、中小学生、大中专学生、教育工作者、中老年人、普通大众等等；
（2）少儿类请填写：0-2、3-6、7-10、11-14等
文本录入框
	 * @return
	 */
	public String getAudience(){
		return this.audience;
	}
		
	/**
	 * 作者国籍
	 * @param authorcitizen
	 */
	
	public void setAuthorcitizen(String authorcitizen){
		this.authorcitizen=authorcitizen;
	}
	/**
	 * 作者国籍
	 * @return
	 */
	public String getAuthorcitizen(){
		return this.authorcitizen;
	}
		
	/**
	 * 作者 必填
（1）以图书封面描述的作者属性为准（封面未描述的，以版权页的描述为准），分别填列。注：编著放在著者字段，编绘放在绘者字段。
（2）有多个人名的，以逗号隔开；
（3）国外作者的国籍以[]符号放在名字的前面，外文名字以符号放在中文名后面；示例：[美]凯利?麦格尼格尔 (Kelly McGonigal Ph.D.)
（4）作者的朝代以[]符号放在名字的前面；示例：[汉]
    示例：黑柳彻子
页面控制 最大100
	 * @param authoreditor
	 */
	
	public void setAuthoreditor(String authoreditor){
		this.authoreditor=authoreditor;
	}
	/**
	 * 作者 必填
（1）以图书封面描述的作者属性为准（封面未描述的，以版权页的描述为准），分别填列。注：编著放在著者字段，编绘放在绘者字段。
（2）有多个人名的，以逗号隔开；
（3）国外作者的国籍以[]符号放在名字的前面，外文名字以符号放在中文名后面；示例：[美]凯利?麦格尼格尔 (Kelly McGonigal Ph.D.)
（4）作者的朝代以[]符号放在名字的前面；示例：[汉]
    示例：黑柳彻子
页面控制 最大100
	 * @return
	 */
	public String getAuthoreditor(){
		return this.authoreditor;
	}
		
	/**
	 * 每包册数 选填 页面控制2位整数
 文本录入框
格式：数字
注意：请按照包装情况填写。
示例：40
	 * @param bagPernum
	 */
	
	public void setBagPernum(Integer bagPernum){
		this.bagPernum=bagPernum;
	}
	/**
	 * 每包册数 选填 页面控制2位整数
 文本录入框
格式：数字
注意：请按照包装情况填写。
示例：40
	 * @return
	 */
	public Integer getBagPernum(){
		return this.bagPernum;
	}
		
	/**
	 * 正文语种 必填 页面控制20
（1）填写图书正文的语种。示例：正文语言：中文。
（2）如果遇到图书正文有多语种的，中间用“；”号隔开。录入格式：例：中文；英文；韩文。
	 * @param bodylanguage
	 */
	
	public void setBodylanguage(String bodylanguage){
		this.bodylanguage=bodylanguage;
	}
	/**
	 * 正文语种 必填 页面控制20
（1）填写图书正文的语种。示例：正文语言：中文。
（2）如果遇到图书正文有多语种的，中间用“；”号隔开。录入格式：例：中文；英文；韩文。
	 * @return
	 */
	public String getBodylanguage(){
		return this.bodylanguage;
	}
		
	/**
	 * 附赠
	 * @param bonus
	 */
	
	public void setBonus(String bonus){
		this.bonus=bonus;
	}
	/**
	 * 附赠
	 * @return
	 */
	public String getBonus(){
		return this.bonus;
	}
		
	/**
	 * 附赠数量
	 * @param bonusnum
	 */
	
	public void setBonusnum(String bonusnum){
		this.bonusnum=bonusnum;
	}
	/**
	 * 附赠数量
	 * @return
	 */
	public String getBonusnum(){
		return this.bonusnum;
	}
		
	/**
	 * 套装册数 有则必填
当【是否套装书】选项为是时，该字段启用为必填 ；且该字段为数字、必填
最大3位
	 * @param bookletnum
	 */
	
	public void setBookletnum(Integer bookletnum){
		this.bookletnum=bookletnum;
	}
	/**
	 * 套装册数 有则必填
当【是否套装书】选项为是时，该字段启用为必填 ；且该字段为数字、必填
最大3位
	 * @return
	 */
	public Integer getBookletnum(){
		return this.bookletnum;
	}
		
	/**
	 * 书名 必填  页面 100长度控制
	 * @param booktitle
	 */
	
	public void setBooktitle(String booktitle){
		this.booktitle=booktitle;
	}
	/**
	 * 书名 必填  页面 100长度控制
	 * @return
	 */
	public String getBooktitle(){
		return this.booktitle;
	}
		
	/**
	 * 品牌 必填  页面控制200
文本录入框
注意：请填列商品的品牌或制造商。
示例：南海出版公司
	 * @param brandname
	 */
	
	public void setBrandname(String brandname){
		this.brandname=brandname;
	}
	/**
	 * 品牌 必填  页面控制200
文本录入框
注意：请填列商品的品牌或制造商。
示例：南海出版公司
	 * @return
	 */
	public String getBrandname(){
		return this.brandname;
	}
		
	/**
	 * 出版分类（小类） 选填 页面控制 20
文本录入框
示例：外国儿童文学
	 * @param childrentpublishercode
	 */
	
	public void setChildrentpublishercode(String childrentpublishercode){
		this.childrentpublishercode=childrentpublishercode;
	}
	/**
	 * 出版分类（小类） 选填 页面控制 20
文本录入框
示例：外国儿童文学
	 * @return
	 */
	public String getChildrentpublishercode(){
		return this.childrentpublishercode;
	}
		
	/**
	 * CIP数据核字 选填 页面控制 50
 版权页所显示的“CIP数据核字
 文本录入框
 示例：第23423号
	 * @param cipnum
	 */
	
	public void setCipnum(String cipnum){
		this.cipnum=cipnum;
	}
	/**
	 * CIP数据核字 选填 页面控制 50
 版权页所显示的“CIP数据核字
 文本录入框
 示例：第23423号
	 * @return
	 */
	public String getCipnum(){
		return this.cipnum;
	}
		
	/**
	 * 中图法分类号 必填 页面控制20位
文本录入框
注意：按照版权页上面的信息进行录入。  示例：TP11.1
	 * @param classification
	 */
	
	public void setClassification(String classification){
		this.classification=classification;
	}
	/**
	 * 中图法分类号 必填 页面控制20位
文本录入框
注意：按照版权页上面的信息进行录入。  示例：TP11.1
	 * @return
	 */
	public String getClassification(){
		return this.classification;
	}
		
	/**
	 * 封面书名
	 * @param coverbooktitle
	 */
	
	public void setCoverbooktitle(String coverbooktitle){
		this.coverbooktitle=coverbooktitle;
	}
	/**
	 * 封面书名
	 * @return
	 */
	public String getCoverbooktitle(){
		return this.coverbooktitle;
	}
		
	/**
	 * 创建者 数据上传者，页面提交时提供的用户
记录用户表登录名
	 * @param creator
	 */
	
	public void setCreator(String creator){
		this.creator=creator;
	}
	/**
	 * 创建者 数据上传者，页面提交时提供的用户
记录用户表登录名
	 * @return
	 */
	public String getCreator(){
		return this.creator;
	}
		
	/**
	 * 学科关键词 选填  页面控制100长度
文本录入框  "注意：
（1）请填写事件关键词是指作品主要涉及到的事件
（2）如有多个关键词，用""；""隔开"
	 * @param disciplinekeyword
	 */
	
	public void setDisciplinekeyword(String disciplinekeyword){
		this.disciplinekeyword=disciplinekeyword;
	}
	/**
	 * 学科关键词 选填  页面控制100长度
文本录入框  "注意：
（1）请填写事件关键词是指作品主要涉及到的事件
（2）如有多个关键词，用""；""隔开"
	 * @return
	 */
	public String getDisciplinekeyword(){
		return this.disciplinekeyword;
	}
		
	/**
	 * 绘者 有则必填 页面最大控制 100
"注意：
（1）以图书封面描述的作者属性为准（封面未描述的，以版权页的描述为准），分别填列。注：编著放在著者字段，编绘放在绘者字段。
（2）有多个人名的，以逗号隔开；
（3）国外作者的国籍以[]符号放在名字的前面，外文名字以符号放在中文名后面；示例：[美]凯利?麦格尼格尔 (Kelly McGonigal Ph.D.)
（4）作者的朝代以[]符号放在名字的前面；示例：[汉]"  示例：岩崎千弘
	 * @param drawing
	 */
	
	public void setDrawing(String drawing){
		this.drawing=drawing;
	}
	/**
	 * 绘者 有则必填 页面最大控制 100
"注意：
（1）以图书封面描述的作者属性为准（封面未描述的，以版权页的描述为准），分别填列。注：编著放在著者字段，编绘放在绘者字段。
（2）有多个人名的，以逗号隔开；
（3）国外作者的国籍以[]符号放在名字的前面，外文名字以符号放在中文名后面；示例：[美]凯利?麦格尼格尔 (Kelly McGonigal Ph.D.)
（4）作者的朝代以[]符号放在名字的前面；示例：[汉]"  示例：岩崎千弘
	 * @return
	 */
	public String getDrawing(){
		return this.drawing;
	}
		
	/**
	 * 出版社 必填 页面控制最大50
出版社/版别
（1）有多个出版社的，只填ISBN对应的出版社；
（2）无法从ISBN中识别出版社的，必须填列；
（3）同一家出版社应保持名称一致。"
当出版社号值非空时，系统自动由《出版社及社号对应原则》匹配对；当无法匹配时，人工录入
示例：南海出版公司
	 * @param edition
	 */
	
	public void setEdition(String edition){
		this.edition=edition;
	}
	/**
	 * 出版社 必填 页面控制最大50
出版社/版别
（1）有多个出版社的，只填ISBN对应的出版社；
（2）无法从ISBN中识别出版社的，必须填列；
（3）同一家出版社应保持名称一致。"
当出版社号值非空时，系统自动由《出版社及社号对应原则》匹配对；当无法匹配时，人工录入
示例：南海出版公司
	 * @return
	 */
	public String getEdition(){
		return this.edition;
	}
		
	/**
	 * 出版日期 控制1：必填；
控制2：值为10位数字；
格式：请输入10位数字的日期（YYYY-MM-DD)，如：2014-08-01；
注意：当没有精确到日时，请用“01"代替，如：2014-08-01。
	 * @param editionyearmonth
	 */
	
	public void setEditionyearmonth(String editionyearmonth){
		this.editionyearmonth=editionyearmonth;
	}
	/**
	 * 出版日期 控制1：必填；
控制2：值为10位数字；
格式：请输入10位数字的日期（YYYY-MM-DD)，如：2014-08-01；
注意：当没有精确到日时，请用“01"代替，如：2014-08-01。
	 * @return
	 */
	public String getEditionyearmonth(){
		return this.editionyearmonth;
	}
		
	/**
	 * 编者 有则必填 页面控制 最大100
"注意：
（1）以图书封面描述的作者属性为准（封面未描述的，以版权页的描述为准），分别填列。注：编著放在著者字段，编绘放在绘者字段。
（2）有多个人名的，以逗号隔开；
（3）国外作者的国籍以[]符号放在名字的前面，外文名字以符号放在中文名后面；示例：[美]凯利?麦格尼格尔 (Kelly McGonigal Ph.D.)
（4）作者的朝代以[]符号放在名字的前面；示例：[汉]"  示例：陈明俊猿渡静子
	 * @param editor
	 */
	
	public void setEditor(String editor){
		this.editor=editor;
	}
	/**
	 * 编者 有则必填 页面控制 最大100
"注意：
（1）以图书封面描述的作者属性为准（封面未描述的，以版权页的描述为准），分别填列。注：编著放在著者字段，编绘放在绘者字段。
（2）有多个人名的，以逗号隔开；
（3）国外作者的国籍以[]符号放在名字的前面，外文名字以符号放在中文名后面；示例：[美]凯利?麦格尼格尔 (Kelly McGonigal Ph.D.)
（4）作者的朝代以[]符号放在名字的前面；示例：[汉]"  示例：陈明俊猿渡静子
	 * @return
	 */
	public String getEditor(){
		return this.editor;
	}
		
	/**
	 * 校注 有则必填 页面控制100
	 * @param emendation
	 */
	
	public void setEmendation(String emendation){
		this.emendation=emendation;
	}
	/**
	 * 校注 有则必填 页面控制100
	 * @return
	 */
	public String getEmendation(){
		return this.emendation;
	}
		
	/**
	 * 外文书名 有则必填
首字母大写，所有实词第一个字母大写，其余字母要小写。
可以有特殊字符 ，需要防止注入 如，
页面控制最大 10
	 * @param enbookname
	 */
	
	public void setEnbookname(String enbookname){
		this.enbookname=enbookname;
	}
	/**
	 * 外文书名 有则必填
首字母大写，所有实词第一个字母大写，其余字母要小写。
可以有特殊字符 ，需要防止注入 如，
页面控制最大 10
	 * @return
	 */
	public String getEnbookname(){
		return this.enbookname;
	}
		
	/**
	 * 事件关键词 选填  页面控制100长度
文本录入框  "注意：
（1）请填写事件关键词是指作品主要涉及到的事件
（2）如有多个关键词，用""；""隔开"
  示例：九一八事件
	 * @param eventkeyword
	 */
	
	public void setEventkeyword(String eventkeyword){
		this.eventkeyword=eventkeyword;
	}
	/**
	 * 事件关键词 选填  页面控制100长度
文本录入框  "注意：
（1）请填写事件关键词是指作品主要涉及到的事件
（2）如有多个关键词，用""；""隔开"
  示例：九一八事件
	 * @return
	 */
	public String getEventkeyword(){
		return this.eventkeyword;
	}
		
	/**
	 * 首版日期 格式：定长为10的数字。示例：2014-08-01
	 * @param firsteditionyearmonth
	 */
	
	public void setFirsteditionyearmonth(String firsteditionyearmonth){
		this.firsteditionyearmonth=firsteditionyearmonth;
	}
	/**
	 * 首版日期 格式：定长为10的数字。示例：2014-08-01
	 * @return
	 */
	public String getFirsteditionyearmonth(){
		return this.firsteditionyearmonth;
	}
		
	/**
	 * 印数 控制1：必填 页面控制10位
控制2：值为数字
示例：10000
	 * @param firstpulish
	 */
	
	public void setFirstpulish(Integer firstpulish){
		this.firstpulish=firstpulish;
	}
	/**
	 * 印数 控制1：必填 页面控制10位
控制2：值为数字
示例：10000
	 * @return
	 */
	public Integer getFirstpulish(){
		return this.firstpulish;
	}
		
	/**
	 * 开本 必填  页面控制20
文本录入框
 示例：32K
	 * @param format
	 */
	
	public void setFormat(String format){
		this.format=format;
	}
	/**
	 * 开本 必填  页面控制20
文本录入框
 示例：32K
	 * @return
	 */
	public String getFormat(){
		return this.format;
	}
		
	/**
	 * 商品介质
	 * @param goodsmedium
	 */
	
	public void setGoodsmedium(String goodsmedium){
		this.goodsmedium=goodsmedium;
	}
	/**
	 * 商品介质
	 * @return
	 */
	public String getGoodsmedium(){
		return this.goodsmedium;
	}
		
	/**
	 * 商品种类
	 * @param goodsvariety
	 */
	
	public void setGoodsvariety(String goodsvariety){
		this.goodsvariety=goodsvariety;
	}
	/**
	 * 商品种类
	 * @return
	 */
	public String getGoodsvariety(){
		return this.goodsvariety;
	}
		
	/**
	 * 高 选填  页面5位整数2位小数
文本录框录入  格式：数字  示例：40
	 * @param high
	 */
	
	public void setHigh(BigDecimal high){
		this.high=high;
	}
	/**
	 * 高 选填  页面5位整数2位小数
文本录框录入  格式：数字  示例：40
	 * @return
	 */
	public BigDecimal getHigh(){
		return this.high;
	}
		
	/**
	 * 历史一号多书
	 * @param historonlybook
	 */
	
	public void setHistoronlybook(Integer historonlybook){
		this.historonlybook=historonlybook;
	}
	/**
	 * 历史一号多书
	 * @return
	 */
	public Integer getHistoronlybook(){
		return this.historonlybook;
	}
		
	/**
	 * 历史书名
	 * @param historybooktitle
	 */
	
	public void setHistorybooktitle(String historybooktitle){
		this.historybooktitle=historybooktitle;
	}
	/**
	 * 历史书名
	 * @return
	 */
	public String getHistorybooktitle(){
		return this.historybooktitle;
	}
		
	/**
	 * 历史ISBN
	 * @param historyisbn
	 */
	
	public void setHistoryisbn(String historyisbn){
		this.historyisbn=historyisbn;
	}
	/**
	 * 历史ISBN
	 * @return
	 */
	public String getHistoryisbn(){
		return this.historyisbn;
	}
		
	/**
	 * 历史定价
	 * @param historyrice
	 */
	
	public void setHistoryrice(BigDecimal historyrice){
		this.historyrice=historyrice;
	}
	/**
	 * 历史定价
	 * @return
	 */
	public BigDecimal getHistoryrice(){
		return this.historyrice;
	}
		
	/**
	 * 
	 * @param id
	 */
	
	public void setId(Long id){
		this.id=id;
	}
	/**
	 * 
	 * @return
	 */
	public Long getId(){
		return this.id;
	}
		
	/**
	 * 印次 必填 页面控制2位
格式：数字；
示例：3
	 * @param impression
	 */
	
	public void setImpression(Integer impression){
		this.impression=impression;
	}
	/**
	 * 印次 必填 页面控制2位
格式：数字；
示例：3
	 * @return
	 */
	public Integer getImpression(){
		return this.impression;
	}
		
	/**
	 * 是否预售 下拉选择框 常数
值包括：是、否
0：否 1：是
	 * @param isadvance
	 */
	
	public void setIsadvance(Integer isadvance){
		this.isadvance=isadvance;
	}
	/**
	 * 是否预售 下拉选择框 常数
值包括：是、否
0：否 1：是
	 * @return
	 */
	public Integer getIsadvance(){
		return this.isadvance;
	}
		
	/**
	 * ISBN 由于ISBN位数不定，存在多种形态12位 13位 小于12位
主要用于页面展示使用

控制1：必填
控制2：前三位字母为数字；应有“0~9”或“A~Z”或“a~z”组成；最小长度大于等于3位，最大长度小于等于13位；
如果填写了条形码没有填写ISBN号 可以根据条形码转换成ISBN
ISBN不能带特殊符号
	 * @param isbn
	 */
	
	public void setIsbn(String isbn){
		this.isbn=isbn;
	}
	/**
	 * ISBN 由于ISBN位数不定，存在多种形态12位 13位 小于12位
主要用于页面展示使用

控制1：必填
控制2：前三位字母为数字；应有“0~9”或“A~Z”或“a~z”组成；最小长度大于等于3位，最大长度小于等于13位；
如果填写了条形码没有填写ISBN号 可以根据条形码转换成ISBN
ISBN不能带特殊符号
	 * @return
	 */
	public String getIsbn(){
		return this.isbn;
	}
		
	/**
	 * 是否重点品 必填
是否为重点：常数
            0：否，1：是
	 * @param isimportant
	 */
	
	public void setIsimportant(Integer isimportant){
		this.isimportant=isimportant;
	}
	/**
	 * 是否重点品 必填
是否为重点：常数
            0：否，1：是
	 * @return
	 */
	public Integer getIsimportant(){
		return this.isimportant;
	}
		
	/**
	 * 是否一号多书 必填
是否为一号多书： 常数
            0：否，1：是 2：未填写一号多书
	 * @param isoveronlybook
	 */
	
	public void setIsoveronlybook(Integer isoveronlybook){
		this.isoveronlybook=isoveronlybook;
	}
	/**
	 * 是否一号多书 必填
是否为一号多书： 常数
            0：否，1：是 2：未填写一号多书
	 * @return
	 */
	public Integer getIsoveronlybook(){
		return this.isoveronlybook;
	}
		
	/**
	 * 是否套装书 必填
是否为套装书 常数
             0：否 1：是
	 * @param issuitbook
	 */
	
	public void setIssuitbook(Integer issuitbook){
		this.issuitbook=issuitbook;
	}
	/**
	 * 是否套装书 必填
是否为套装书 常数
             0：否 1：是
	 * @return
	 */
	public Integer getIssuitbook(){
		return this.issuitbook;
	}
		
	/**
	 * 主题词 必填  页面控制100长度
文本录入框  注意：严格按照版权页录入，版权页中由罗马数字标示的部分，其中Ⅲ后面所标示的，即为主题词。  示例：传记-画册
	 * @param keyword
	 */
	
	public void setKeyword(String keyword){
		this.keyword=keyword;
	}
	/**
	 * 主题词 必填  页面控制100长度
文本录入框  注意：严格按照版权页录入，版权页中由罗马数字标示的部分，其中Ⅲ后面所标示的，即为主题词。  示例：传记-画册
	 * @return
	 */
	public String getKeyword(){
		return this.keyword;
	}
		
	/**
	 * 长 选填  页面5位整数2位小数
文本录框录入  格式：数字  示例：40
	 * @param length
	 */
	
	public void setLength(BigDecimal length){
		this.length=length;
	}
	/**
	 * 长 选填  页面5位整数2位小数
文本录框录入  格式：数字  示例：40
	 * @return
	 */
	public BigDecimal getLength(){
		return this.length;
	}
		
	/**
	 * 营销分类 必填 常数
下拉选择框
值包括：社科、文艺、文教、少儿、科技、教材
	 * @param marketingcategories
	 */
	
	public void setMarketingcategories(String marketingcategories){
		this.marketingcategories=marketingcategories;
	}
	/**
	 * 营销分类 必填 常数
下拉选择框
值包括：社科、文艺、文教、少儿、科技、教材
	 * @return
	 */
	public String getMarketingcategories(){
		return this.marketingcategories;
	}
		
	/**
	 * 条码号 必填
待分割线的ISBN
如果页面未录入的时候，默认与ISBN一致
最大  25长度
	 * @param mastercommoditycode
	 */
	
	public void setMastercommoditycode(String mastercommoditycode){
		this.mastercommoditycode=mastercommoditycode;
	}
	/**
	 * 条码号 必填
待分割线的ISBN
如果页面未录入的时候，默认与ISBN一致
最大  25长度
	 * @return
	 */
	public String getMastercommoditycode(){
		return this.mastercommoditycode;
	}
		
	/**
	 * 普通关键词 选填  页面控制100长度
文本录入框  "注意：
（1）请填写事件关键词是指作品主要涉及到的事件
（2）如有多个关键词，用""；""隔开"
	 * @param ordinarykeyword
	 */
	
	public void setOrdinarykeyword(String ordinarykeyword){
		this.ordinarykeyword=ordinarykeyword;
	}
	/**
	 * 普通关键词 选填  页面控制100长度
文本录入框  "注意：
（1）请填写事件关键词是指作品主要涉及到的事件
（2）如有多个关键词，用""；""隔开"
	 * @return
	 */
	public String getOrdinarykeyword(){
		return this.ordinarykeyword;
	}
		
	/**
	 * 正书名
	 * @param origbooktitle
	 */
	
	public void setOrigbooktitle(String origbooktitle){
		this.origbooktitle=origbooktitle;
	}
	/**
	 * 正书名
	 * @return
	 */
	public String getOrigbooktitle(){
		return this.origbooktitle;
	}
		
	/**
	 * 页码 必填 页面控制 10位
（1）格式：数字；
（2）根据图书的实际页码进行录入，仅能填写数字；套装书把每册的页码相加填列。
例：304
	 * @param pagenumber
	 */
	
	public void setPagenumber(Integer pagenumber){
		this.pagenumber=pagenumber;
	}
	/**
	 * 页码 必填 页面控制 10位
（1）格式：数字；
（2）根据图书的实际页码进行录入，仅能填写数字；套装书把每册的页码相加填列。
例：304
	 * @return
	 */
	public Integer getPagenumber(){
		return this.pagenumber;
	}
		
	/**
	 * 用纸 必填 页面控制50
文本录入框
注意：按照书籍正文实际用纸填写。
示例：胶版纸
	 * @param paper
	 */
	
	public void setPaper(String paper){
		this.paper=paper;
	}
	/**
	 * 用纸 必填 页面控制50
文本录入框
注意：按照书籍正文实际用纸填写。
示例：胶版纸
	 * @return
	 */
	public String getPaper(){
		return this.paper;
	}
		
	/**
	 * 装帧 必填 常数
下拉选择框
值包括：平装、精装、简装、线装、盒装、软精装、袋装、盒函装
	 * @param paperback
	 */
	
	public void setPaperback(String paperback){
		this.paperback=paperback;
	}
	/**
	 * 装帧 必填 常数
下拉选择框
值包括：平装、精装、简装、线装、盒装、软精装、袋装、盒函装
	 * @return
	 */
	public String getPaperback(){
		return this.paperback;
	}
		
	/**
	 * 出版分类（大类） 选填 页面控制 20
文本录入框
示例：童书
	 * @param parentpublishercode
	 */
	
	public void setParentpublishercode(String parentpublishercode){
		this.parentpublishercode=parentpublishercode;
	}
	/**
	 * 出版分类（大类） 选填 页面控制 20
文本录入框
示例：童书
	 * @return
	 */
	public String getParentpublishercode(){
		return this.parentpublishercode;
	}
		
	/**
	 * 出版地 选填 页面控制 最大20
填写版权页上CIP数据中标明的地址。如：北京
	 * @param placepublished
	 */
	
	public void setPlacepublished(String placepublished){
		this.placepublished=placepublished;
	}
	/**
	 * 出版地 选填 页面控制 最大20
填写版权页上CIP数据中标明的地址。如：北京
	 * @return
	 */
	public String getPlacepublished(){
		return this.placepublished;
	}
		
	/**
	 * 定价 必填
格式：数值  示例：39.08
最大 1000000 保留2位小数
	 * @param price
	 */
	
	public void setPrice(BigDecimal price){
		this.price=price;
	}
	/**
	 * 定价 必填
格式：数值  示例：39.08
最大 1000000 保留2位小数
	 * @return
	 */
	public BigDecimal getPrice(){
		return this.price;
	}
		
	/**
	 * 印刷日期 格式：定长为10的数字。示例：2014-08-01
	 * @param printyearmonth
	 */
	
	public void setPrintyearmonth(String printyearmonth){
		this.printyearmonth=printyearmonth;
	}
	/**
	 * 印刷日期 格式：定长为10的数字。示例：2014-08-01
	 * @return
	 */
	public String getPrintyearmonth(){
		return this.printyearmonth;
	}
		
	/**
	 * 出版者国别 有则必填 页面控制最大20
（1）非中国大陆出版机构出版的图书必须填列；
（2）如为空，平台将默认为中国大陆。
	 * @param publisherassigneecountry
	 */
	
	public void setPublisherassigneecountry(String publisherassigneecountry){
		this.publisherassigneecountry=publisherassigneecountry;
	}
	/**
	 * 出版者国别 有则必填 页面控制最大20
（1）非中国大陆出版机构出版的图书必须填列；
（2）如为空，平台将默认为中国大陆。
	 * @return
	 */
	public String getPublisherassigneecountry(){
		return this.publisherassigneecountry;
	}
		
	/**
	 * 出版社号 控制1：必填 页面控制 最大20
控制2：值必须为”数字“和”-“组成
控制3：当出版社值非空时，系统自动由《出版社及社号对应原则》匹配对；当无法匹配时，人工录入
从ISBN号中978后的第一、二节数字；
 示例：如：ISBN为978-7-111-47639-9，则版别号为：7-111
	 * @param publishercode
	 */
	
	public void setPublishercode(String publishercode){
		this.publishercode=publishercode;
	}
	/**
	 * 出版社号 控制1：必填 页面控制 最大20
控制2：值必须为”数字“和”-“组成
控制3：当出版社值非空时，系统自动由《出版社及社号对应原则》匹配对；当无法匹配时，人工录入
从ISBN号中978后的第一、二节数字；
 示例：如：ISBN为978-7-111-47639-9，则版别号为：7-111
	 * @return
	 */
	public String getPublishercode(){
		return this.publishercode;
	}
		
	/**
	 * 上架建议 选填 页面控制 100
文本录入框
示例：少儿文学
	 * @param recommendSaleGroup
	 */
	
	public void setRecommendSaleGroup(String recommendSaleGroup){
		this.recommendSaleGroup=recommendSaleGroup;
	}
	/**
	 * 上架建议 选填 页面控制 100
文本录入框
示例：少儿文学
	 * @return
	 */
	public String getRecommendSaleGroup(){
		return this.recommendSaleGroup;
	}
		
	/**
	 * 备注 大字段
	 * @param rememo
	 */
	
	public void setRememo(String rememo){
		this.rememo=rememo;
	}
	/**
	 * 备注 大字段
	 * @return
	 */
	public String getRememo(){
		return this.rememo;
	}
		
	/**
	 * 交替书名
	 * @param replacebooktitle
	 */
	
	public void setReplacebooktitle(String replacebooktitle){
		this.replacebooktitle=replacebooktitle;
	}
	/**
	 * 交替书名
	 * @return
	 */
	public String getReplacebooktitle(){
		return this.replacebooktitle;
	}
		
	/**
	 * 印张 选填
文本录入框
格式：数字
注意：按照版权页上列示的填写
示例：8.323
	 * @param sheet
	 */
	
	public void setSheet(BigDecimal sheet){
		this.sheet=sheet;
	}
	/**
	 * 印张 选填
文本录入框
格式：数字
注意：按照版权页上列示的填写
示例：8.323
	 * @return
	 */
	public BigDecimal getSheet(){
		return this.sheet;
	}
		
	/**
	 * 占用状态     使用常数定义 ISEMPTY                       未占用     表示当前商品暂未被任何加工或审核占用
INFORMATIONONLY    信息占用 表示当前商品正在进行信息加工
APPENDONLY               附件占用 表示当前商品所关联的附件批次正在进行附件加工
ENTITYONLY                实物占用 表示当前商品正在进行实物加工或实物审核
APPROVED                   审核通过 表示实物的审核通过数据不能被更改
	 * @param state
	 */
	
	public void setState(String state){
		this.state=state;
	}
	/**
	 * 占用状态     使用常数定义 ISEMPTY                       未占用     表示当前商品暂未被任何加工或审核占用
INFORMATIONONLY    信息占用 表示当前商品正在进行信息加工
APPENDONLY               附件占用 表示当前商品所关联的附件批次正在进行附件加工
ENTITYONLY                实物占用 表示当前商品正在进行实物加工或实物审核
APPROVED                   审核通过 表示实物的审核通过数据不能被更改
	 * @return
	 */
	public String getState(){
		return this.state;
	}
		
	/**
	 * 占用方式     使用常数定义 INFORMATION    信息
APPEND              附件
ENTITY                实物
	 * @param taken
	 */
	
	public void setTaken(String taken){
		this.taken=taken;
	}
	/**
	 * 占用方式     使用常数定义 INFORMATION    信息
APPEND              附件
ENTITY                实物
	 * @return
	 */
	public String getTaken(){
		return this.taken;
	}
		
	/**
	 * 税率 系统后台自动默认该值为“13%”；
	 * @param taxrate
	 */
	
	public void setTaxrate(String taxrate){
		this.taxrate=taxrate;
	}
	/**
	 * 税率 系统后台自动默认该值为“13%”；
	 * @return
	 */
	public String getTaxrate(){
		return this.taxrate;
	}
		
	/**
	 * 译者 有则必填  页面控制 最大100
"注意：
（1）以图书封面描述的作者属性为准（封面未描述的，以版权页的描述为准），分别填列。注：编著放在著者字段，编绘放在绘者字段。
（2）有多个人名的，以逗号隔开；
（3）国外作者的国籍以[]符号放在名字的前面，外文名字以符号放在中文名后面；示例：[美]凯利?麦格尼格尔 (Kelly McGonigal Ph.D.)
（4）作者的朝代以[]符号放在名字的前面；示例：[汉]"  示例：赵玉皎
	 * @param translator
	 */
	
	public void setTranslator(String translator){
		this.translator=translator;
	}
	/**
	 * 译者 有则必填  页面控制 最大100
"注意：
（1）以图书封面描述的作者属性为准（封面未描述的，以版权页的描述为准），分别填列。注：编著放在著者字段，编绘放在绘者字段。
（2）有多个人名的，以逗号隔开；
（3）国外作者的国籍以[]符号放在名字的前面，外文名字以符号放在中文名后面；示例：[美]凯利?麦格尼格尔 (Kelly McGonigal Ph.D.)
（4）作者的朝代以[]符号放在名字的前面；示例：[汉]"  示例：赵玉皎
	 * @return
	 */
	public String getTranslator(){
		return this.translator;
	}
		
	/**
	 * 随书附件 有则必填 页面控制100
文本录入框
注意：请写明随书附送的物品及数量。
      示例：附光盘1张
	 * @param typeandquantity
	 */
	
	public void setTypeandquantity(String typeandquantity){
		this.typeandquantity=typeandquantity;
	}
	/**
	 * 随书附件 有则必填 页面控制100
文本录入框
注意：请写明随书附送的物品及数量。
      示例：附光盘1张
	 * @return
	 */
	public String getTypeandquantity(){
		return this.typeandquantity;
	}
		
	/**
	 * 上传时间 第一次进入待发表时间
	 * @param uploaddate
	 */
	
	public void setUploaddate(Date uploaddate){
		this.uploaddate=uploaddate;
	}
	/**
	 * 上传时间 第一次进入待发表时间
	 * @return
	 */
	public Date getUploaddate(){
		return this.uploaddate;
	}
		
	/**
	 * 版次 必填  下拉选择 常数
格式：第X版。例如：第3版
值包括：第1版…..第30版
	 * @param version
	 */
	
	public void setVersion(String version){
		this.version=version;
	}
	/**
	 * 版次 必填  下拉选择 常数
格式：第X版。例如：第3版
值包括：第1版…..第30版
	 * @return
	 */
	public String getVersion(){
		return this.version;
	}
		
	/**
	 * 版本
	 * @param versionnotes
	 */
	
	public void setVersionnotes(String versionnotes){
		this.versionnotes=versionnotes;
	}
	/**
	 * 版本
	 * @return
	 */
	public String getVersionnotes(){
		return this.versionnotes;
	}
		
	/**
	 * 商品重量 选填  页面5位整数2位小数
文本录框录入
格式：数字；单位为克
示例： 399
	 * @param weight
	 */
	
	public void setWeight(BigDecimal weight){
		this.weight=weight;
	}
	/**
	 * 商品重量 选填  页面5位整数2位小数
文本录框录入
格式：数字；单位为克
示例： 399
	 * @return
	 */
	public BigDecimal getWeight(){
		return this.weight;
	}
		
	/**
	 * 宽 选填  页面5位整数2位小数
文本录框录入  格式：数字  示例：40
	 * @param wide
	 */
	
	public void setWide(BigDecimal wide){
		this.wide=wide;
	}
	/**
	 * 宽 选填  页面5位整数2位小数
文本录框录入  格式：数字  示例：40
	 * @return
	 */
	public BigDecimal getWide(){
		return this.wide;
	}
		
	/**
	 * 字数 必填 页面控制20位
（1）格式：数字+千字；
（2）按照版权页录入。示例：300千字
	 * @param wordnumber
	 */
	
	public void setWordnumber(String wordnumber){
		this.wordnumber=wordnumber;
	}
	/**
	 * 字数 必填 页面控制20位
（1）格式：数字+千字；
（2）按照版权页录入。示例：300千字
	 * @return
	 */
	public String getWordnumber(){
		return this.wordnumber;
	}
		
	/**
	 * 平台ID 平台对商品定义的唯一号
	 * @param yunhanId
	 */
	
	public void setYunhanId(String yunhanId){
		this.yunhanId=yunhanId;
	}
	/**
	 * 平台ID 平台对商品定义的唯一号
	 * @return
	 */
	public String getYunhanId(){
		return this.yunhanId;
	}
	/**
	 * @return the discountrate
	 */
	public BigDecimal getDiscountrate() {
		return discountrate;
	}
	/**
	 * @param discountrate the discountrate to set
	 */
	public void setDiscountrate(BigDecimal discountrate) {
		this.discountrate = discountrate;
	}
	/**
	 * @return the purchaserid
	 */
	public String getPurchaserid() {
		return purchaserid;
	}
	/**
	 * @param purchaserid the purchaserid to set
	 */
	public void setPurchaserid(String purchaserid) {
		this.purchaserid = purchaserid;
	}
	/**
	 * @return the supplierid
	 */
	public String getSupplierid() {
		return supplierid;
	}
	/**
	 * @param supplierid the supplierid to set
	 */
	public void setSupplierid(String supplierid) {
		this.supplierid = supplierid;
	}
	/**
	 * @return the keyId
	 */
	public String getKeyId() {
		return keyId;
	}
	/**
	 * @param keyId the keyId to set
	 */
	public void setKeyId(String keyId) {
		this.keyId = keyId;
	}
	
	
		
}
