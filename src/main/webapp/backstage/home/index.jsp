<%--
  Created by IntelliJ IDEA.
  敬老院管理系统后台首页
  User: 陈彩君
  Date: 2017/9/28
  Time: 16:54
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<head lang="en" id="Head1">
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <title>Cc敬老院管理系统</title>
    <meta name="description" content="敬老院管理系统后台首页">
    <meta name="author" content="陈彩君">
    <link rel="stylesheet" type="text/css" href="css/default.css"/>
    <link rel="stylesheet" type="text/css" href="../../easyui/themes/icon.css"/>
    <link rel="stylesheet" type="text/css" href="../../easyui/themes/default/easyui.css"/>
    <script type="text/javascript" src="../../easyui/jquery.min.js"></script>
    <script type="text/javascript" src="../../easyui/jquery.easyui.min.js"></script>
    <script type="text/javascript" src="../../easyui/locale/easyui-lang-zh_CN.js"></script>
    <script type="text/javascript" src="js/outlook2.js"></script>
    <script type="text/javascript">
        var _menus = {
            "menus": [
                {
                    "menuid": "1", "icon": "icon-sys", "menuname": "前端功能",
                    "menus": [
                        {"menuid": "12", "menuname": "服务项目", "icon": "icon-page", "url": "qdgn/fwxm.jsp"},
                        {"menuid": "13", "menuname": "美好风采", "icon": "icon-class", "url": "qdgn/mhfc.jsp"},
                        {"menuid": "14", "menuname": "新闻动态", "icon": "icon-role", "url": "qdgn/xwdt.jsp"},
                        {"menuid": "15", "menuname": "入住费用", "icon": "icon-set", "url": "qdgn/rzfy.jsp"},
                        {"menuid": "16", "menuname": "常见问题", "icon": "icon-log", "url": "qdgn/cjwt.jsp"},
                        {"menuid": "17", "menuname": "联系我们", "icon": "icon-nav", "url": "qdgn/lxwm.jsp"}
                    ]
                },
                {
                    "menuid": "2", "icon": "icon-sys", "menuname": "信息中心",
                    "menus": [
                        {"menuid": "21", "menuname": "信息登记", "icon": "icon-page", "url": "xxzx/xxdj.jsp"},
                        {"menuid": "22", "menuname": "相关病历", "icon": "icon-class", "url": "xxzx/xgbl.jsp"},
                        {"menuid": "23", "menuname": "信息查看", "icon": "icon-role", "url": "xxzx/xxck.jsp"},
                        {"menuid": "24", "menuname": "病例查看", "icon": "icon-nav", "url": "xxzx/blck.jsp"},
                        {"menuid": "25", "menuname": "床位信息", "icon": "icon-set", "url": "xxzx/cwxx.jsp"},
                        {"menuid": "26", "menuname": "特殊服务", "icon": "icon-log", "url": "xxzx/tsfw.jsp"},
                        {"menuid": "27", "menuname": "出入院", "icon": "icon-nav", "url": "xxzx/cry.jsp"}
                    ]
                }, {
                    "menuid": "3", "icon": "icon-sys", "menuname": "体验预约",
                    "menus": [
                        {"menuid": "31", "menuname": "未处理预约", "icon": "icon-page", "url": "tjyy/wclyy.jsp"},
                        {"menuid": "32", "menuname": "已处理预约", "icon": "icon-class", "url": "tjyy/yclyy.jsp"}
                    ]
                }, {
                    "menuid": "4", "icon": "icon-sys", "menuname": "员工中心",
                    "menus": [{"menuid": "41", "menuname": "员工信息", "icon": "icon-page", "url": "ygzx/ygxx.jsp"},
                        {"menuid": "42", "menuname": "排班信息", "icon": "icon-class", "url": "ygzx/pbxx.jsp"},
                        {"menuid": "43", "menuname": "账户列表", "icon": "icon-nav", "url": "ygzx/zhlb.jsp"}
                    ]
                }
            ]
        };

        //设置修改密码窗口
        function openPwd() {
            $('#w').window({
                title: '修改密码',
                width: 300,
                modal: true,
                shadow: true,
                closed: true,
                height: 160,
                resizable: false
            });
        }
        //设置登录窗口
        function openPwd() {
            $('#w').window({
                title: '修改密码',
                width: 300,
                modal: true,
                shadow: true,
                closed: true,
                height: 160,
                resizable: false
            });
        }
        //关闭登录窗口
        function closePwd() {
            $('#w').window('close');
        }


        //修改密码
        function serverLogin() {
            var $newpass = $('#txtNewPass');
            var $rePass = $('#txtRePass');

            if ($newpass.val() == '') {
                msgShow('系统提示', '请输入密码！', 'warning');
                return false;
            }
            if ($rePass.val() == '') {
                msgShow('系统提示', '请在一次输入密码！', 'warning');
                return false;
            }

            if ($newpass.val() != $rePass.val()) {
                msgShow('系统提示', '两次密码不一至！请重新输入', 'warning');
                return false;
            }

            $.post('/ajax/editpassword.ashx?newpass=' + $newpass.val(), function (msg) {
                msgShow('系统提示', '恭喜，密码修改成功！<br>您的新密码为：' + msg, 'info');
                $newpass.val('');
                $rePass.val('');
                close();
            })

        }

        $(function () {

            openPwd();

            $('#editpass').click(function () {
                $('#w').window('open');
            });

            $('#btnEp').click(function () {
                serverLogin();
            });

            $('#btnCancel').click(function () {
                closePwd();
            });

            $('#loginOut').click(function () {
                $.messager.confirm('系统提示', '您确定要退出本次登录吗?', function (r) {

                    if (r) {
                        location.href = '/ajax/loginout.ashx';
                    }
                });
            })
        });


    </script>
</head>
<body class="easyui-layout" style="overflow-y: hidden" scroll="no">
<noscript>
    <div style=" position:absolute; z-index:100000; height:2046px;top:0px;left:0px; width:100%; background:white; text-align:center;">
        <img src="images/noscript.gif" alt='抱歉，请开启脚本支持！'/></div>
</noscript>
<div region="north" split="true" border="false" style="overflow: hidden; height: 30px;
        background: url(images/layout-browser-hd-bg.gif) #7f99be repeat-x center 50%;
        line-height: 20px;color: #fff; font-family: Verdana, 微软雅黑,黑体"><span style="float:right; padding-right:20px;"
                                                                            class="head">欢迎 管理员 <a href="#"
                                                                                                   id="editpass">修改密码</a> <a
        href="#" id="loginOut">安全退出</a></span> <span style="padding-left:10px; font-size: 16px; "><img
        src="images/blocks.gif" width="20" height="20" align="absmiddle"/>C<sup>c</sup>敬老院管理系统</span></div>
<div region="south" split="true" style="height: 30px; background: #D2E0F2; ">
    <div class="footer">版权归属于C<sup>c</sup>敬老院所有，翻版必究</div>
</div>
<div region="west" hide="true" split="true" title="导航菜单" style="width:180px;" id="west">
    <div id="nav" class="easyui-accordion" fit="true" border="false">
        <!--  导航内容 -->
    </div>
</div>
<div id="mainPanle" region="center" style="background: #eee; overflow-y:hidden">
    <div id="tabs" class="easyui-tabs" fit="true" border="false">
        <div title="欢迎使用" style="padding:20px;overflow:hidden; color:red; ">
            <h1 style="font-size:24px;">欢迎使用C<sup>c</sup>敬老院管理系统</h1>
        </div>
    </div>
</div>

<!--修改密码窗口-->
<div id="w" class="easyui-window" title="修改密码" collapsible="false" minimizable="false"
     maximizable="false" icon="icon-save" style="width: 300px; height: 150px; padding: 5px;
        background: #fafafa;">
    <div class="easyui-layout" fit="true">
        <div region="center" border="false" style="padding: 10px; background: #fff; border: 1px solid #ccc;">
            <table cellpadding=3>
                <tr>
                    <td>新密码：</td>
                    <td><input id="txtNewPass" type="Password" class="txt01"/></td>
                </tr>
                <tr>
                    <td>确认密码：</td>
                    <td><input id="txtRePass" type="Password" class="txt01"/></td>
                </tr>
            </table>
        </div>
        <div region="south" border="false" style="text-align: right; height: 30px; line-height: 30px;"><a id="btnEp"
                                                                                                          class="easyui-linkbutton"
                                                                                                          icon="icon-ok"
                                                                                                          href="javascript:void(0)">
            确定</a> <a id="btnCancel" class="easyui-linkbutton" icon="icon-cancel" href="javascript:void(0)">取消</a></div>
    </div>
</div>
<div id="mm" class="easyui-menu" style="width:150px;">
    <div id="mm-tabupdate">刷新</div>
    <div class="menu-sep"></div>
    <div id="mm-tabclose">关闭</div>
    <div id="mm-tabcloseall">全部关闭</div>
    <div id="mm-tabcloseother">除此之外全部关闭</div>
    <div class="menu-sep"></div>
    <div id="mm-tabcloseright">当前页右侧全部关闭</div>
    <div id="mm-tabcloseleft">当前页左侧全部关闭</div>
    <div class="menu-sep"></div>
    <div id="mm-exit">退出</div>
</div>
</body>
</html>