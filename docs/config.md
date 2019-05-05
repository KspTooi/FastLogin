@LineType=GeneralDataCore
@LineVersion=V5
@KeySeparationSymbol==
#
#基本配置
ConfigVersion=0.43-B-RE      -禁止修改
//玩家数据储存类型 可选GeneralDataCore | Mysql
PlayerDataType=GeneralDataCore
//登录超时
LoginTimeOut=60
//玩家密码最大长度
PasswordMaxLength=15
//玩家密码最小长度
PasswordMinLength=5
//玩家名称最小长度
PlayerNameMinLength=3
//登录消息的发送间隔
MessageInterval=5
//登录安全保护时间
LoginSecurityTime=3
//开启安全登录, 此选项开启后玩家进服会被立即传送至初始登录点,并在登录后传送回最后下线的位置. 有效防止卡地狱门
Enable_LoginSecurity=false
//开启用户名严格模式,此选项开启后玩家将只能使用含有A-Z 0-9和下划线的名字进入游戏(在1.7版本关闭此选项可以使用中文ID)
Enable_UserNameStrictmode=true
//名称含有某个关键字的玩家将不能进服  参考格式=name1;name2;name3;name4(如果不开启填 *;)
BanName=*;
//每个IP的最大注册账号数 如不需要 填0关闭它.
MaxRegisterIP=0
//使用自定义的正则表达式来匹配玩家名,不符合要求的玩家将无法进服.
RegexMatchForPlayerName=*
#
#特效及自定义配置
//玩家登录成功以后收到的公告 如不需要,填false (变量:%Player%玩家名 & 颜色代码 #换行符)
//范本:&A&L欢迎来到&C&LKspTooi&A&L服务器#&A&L请在聊天框输入Login登录#&A&L祝你游戏愉快
PlayerLoginedMessage=false
//自定义玩家的加入消息(变量:%Player%玩家名 )
PlayerJoinedMessage=§e%Player% Joined the game.
//自定义玩家的退出消息(变量:%Player%玩家名 )
PlayerQuitMessage=§e%Player% Left the game.
//开启登录后的粒子效果
Enable_PlayerLoginedEffect=true
//开启登录前的失明效果
Enable_PlayerPreLoginEffect=true
#
#安全配置
//启用OP安全 —— OP在未登录时没有权限
Enable_OPSecurity=true
//启用安全警告——有人利用BUG时后台文字提示
Enable_SecurityWarning=true
//启用地狱门安全
Enable_HellGateSecurity=true
//启用创造安全 —— 拥有创造模式的玩家在未登录时没有权限
Enable_CreativeModeSecurity=true
//密码加密方式 如需明文存储密码 填写false
Enable_passwordHash=MD5
//支持旧加密方式, 如果密码错误,会用其他加密方法再尝试 成功后将会将密码转换为新的加密方式.
Enable_SupportOldPassword=false
//开启调试输出(一般不要动)
Enable_DebugPrint=false
#
GeneralDataCore - Mysql数据库配置
//数据库地址
MysqlAddress=127.0.0.1:3306
//数据库名
DataBaseName=fastlogin
//数据库账号
MysqlUser=root
//数据库密码
MysqlPwd=root
//连接时附加的参数
Param=?useSSL=false§characterEncoding=utf8§serverTimezone=UTC§autoReconnect=true
#
FastLogin - Mysql数据库配置 #玩家数据表
//玩家数据表名
PlayerDataTable=playertable
//用于储存玩家名称的数据库字段名
PlayerNameField=playername
//用于储存玩家密码的数据库字段名
PlayerPwdField=playerpwd
//用于储存玩家注册状态的数据库字段名
PlayerRegStatusField=register
//用于储存玩家登录状态的数据库字段名
PlayerLoginStatusField=login
#
FastLogin - Mysql数据库配置 #玩家位置数据表(!不建议修改)
PlayerLocTable=playerloc
PlayerNameField=Foreign Key
PlayerLocworld=locworld
PlayerLocx=locx
PlayerLocy=locy
PlayerLocz=locz
PlayerLocpitch=locpitch
PlayerLocyaw=locyaw
