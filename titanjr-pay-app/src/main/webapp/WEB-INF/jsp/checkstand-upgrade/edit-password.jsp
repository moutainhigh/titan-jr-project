<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<html>
<head lang="en">
    <meta charset="UTF-8">
    <title></title>
    <link rel="stylesheet" href="./font/iconfont.css"/>
    <link rel="stylesheet" href="./css/reset.css"/>
    <link rel="stylesheet" href="./css/cashier.css"/>
</head>
<body>
<header>
    <b></b>
    <span>修改支付密码</span>
</header>
<div class="forgot-password-top w">
    <div class="content content-1">
        <div class="top">
            <div class="border"></div>
            <div class="password-one">1</div>
            <div class="password-two">2</div>
            <div class="password-three">3</div>
        </div>
        <div class="bottom">
            <div class="text-one">短信验证</div>
            <div class="text-two">修改密码</div>
            <div class="text-three">完成</div>
        </div>
    </div>
    <div class="content content-2 isShow">
        <div class="top">
            <div class="border"></div>
            <svg class="icon" aria-hidden="true"><use xlink:href="#icon-konggou"></use></svg>
            <div class="password-two">2</div>
            <div class="password-three">3</div>
        </div>
        <div class="bottom">
            <div class="text-one">短信验证</div>
            <div class="text-two">修改密码</div>
            <div class="text-three">完成</div>
        </div>
    </div>
    <div class="content content-3 isShow">
        <div class="top">
            <div class="border"></div>
            <svg class="icon svg-1" aria-hidden="true"><use xlink:href="#icon-konggou"></use></svg>
            <svg class="icon svg-2" aria-hidden="true"><use xlink:href="#icon-konggou"></use></svg>
            <div class="password-three">3</div>
        </div>
        <div class="bottom">
            <div class="text-one">短信验证</div>
            <div class="text-two">修改密码</div>
            <div class="text-three">完成</div>
        </div>
    </div>
</div>
<div class="agreement w" style="height: 72%; min-height: 400px; padding-top: 30px;">
    <!-- 第一步-->
    <div class="payment-verification forgot-password-1 " style="position: static;box-shadow:  0 0 0 #fff; transform: none;margin: 0 auto;background-color: transparent;">
        <div class="payment-verification-content">
            <div class="phone clearfix">
                <div class="left fl">用户名</div>
                <div class="right fl">158 3388 3388</div>
            </div>
            <div class="phone message clearfix">
                <div class="left fl">短信验证</div>
                <div class="right fl"><input type="text" placeholder="请输入验证码" onkeyup="value=value.replace(/[^\d]/g,'')"/><i class="iconfont icon-sc forgot-password-empty isShow"></i><button type="button" class="obtain-btn">获取验证码</button></div>
                <span class="verification-sentence isShow">验证码不能为空</span>
            </div>
            <div class="phone confirm clearfix" style="margin-top: 30px;">
                <div class="left fl"></div>
                <div class="right fl"><button class="next-step-btn">下一步</button></div>
            </div>
        </div>
    </div>
    <!-- 第二步-->
    <div class="payment-verification forgot-password-2 isShow" style="position: static;box-shadow:  0 0 0 #fff; transform: none;margin: 0 auto;background-color: transparent;">
        <div class="payment-verification-content">
            <div class="password">
                <div class="setpassword">
                    <ul class="TFSpassw_set">
                        <li class="clearfix">
                            <span class="Passname fl">新密码</span>
                            <div class="sixDigitPassword fl" id="passwordbox-1">
                                <i><b></b></i>
                                <i><b></b></i>
                                <i><b></b></i>
                                <i><b></b></i>
                                <i><b></b></i>
                                <i><b></b></i>
                                <span></span>
                            </div>
                        </li>
                        <li class="clearfix">
                            <span class="Passname fl">确认密码</span>
                            <div class="sixDigitPassword fl" id="passwordbox1-2">
                                <i><b></b></i>
                                <i><b></b></i>
                                <i><b></b></i>
                                <i><b></b></i>
                                <i><b></b></i>
                                <i><b></b></i>
                                <span></span>
                            </div>
                        </li>
                    </ul>
                    <span class="password-prompt isShow">两次密码不一致</span>
                </div>
            </div>
            <div class="phone confirm clearfix" style="margin-top: 30px;">
                <div class="left fl"></div>
                <div class="right fl"><button type="button" id="btn" class="password-confirm-btn">确认</button></div>
            </div>
        </div>
    </div>
    <!-- 第三步-->
    <div class="payment-verification forgot-password-3 isShow" style="position: static;box-shadow:  0 0 0 #fff; transform: none;margin: 0 auto;background-color: transparent;">
        <div class="payment-verification-content ">
            <img src="./images/20170826201840.png" alt=""/>
            <p>您的付款密码已设置成功</p>
            <a class="close-forgot-password" href="收银台.html">关闭</a>
        </div>
    </div>

</div>
<footer style="position: fixed; bottom: 0px">
    <p>Copyright 2012-2016，fangcang.com. All Rights Reserved 泰坦云 版权所有 粤ICP备13046150号 <a href="javascript:void(0);"></a>工商网监 电子标识</p>
</footer>
</body>
<script src="./font/iconfont.js"></script>
<script src="./js/jquery-1.8.3.min.js"></script>
<script src="./js/password.js"></script>
<script src="./js/cashier.js"></script>
<script>
    $(function(){
        // 密码输入
        var PasswordStr=new sixDigitPassword("passwordbox-1");
        var PasswordStr1=new sixDigitPassword("passwordbox1-2");
        // 获取两次密码框中的值
        $("#btn").on('click',function(){
            alert (PasswordStr.returnStr()+"+"+PasswordStr1.returnStr())
        })

        // 第一步
        // 验证码60s倒计时
        var btn = true;
        $(".obtain-btn").click(function(){
            if(btn){
                btn = false;
                var num = 60;
                var _this = this;
                setInterval(function(){
                    num--;
                    if(num < 0){
                        btn = true;
                        $(_this).text("获取验证码").css("color","#222");
                        return;
                    }
                    $(_this).text(num + "s").css("color","#bbb");
                },1000)
            }else{
                return;
            }
        })
        $(".next-step-btn").click(function(){
            if($(".payment-verification input").val() != ""){
                $(".content-1").addClass("isShow");
                $(".forgot-password-1").addClass("isShow");
                $(".content-2").removeClass("isShow");
                $(".forgot-password-2").removeClass("isShow");
            }else{
                $(".verification-sentence").removeClass("isShow");
                $(".payment-verification input").css("borderColor","#d71319");
            }

        });
        // 第二步
        $(".password-confirm-btn").click(function(){
            if(PasswordStr.returnStr() == "" || PasswordStr1.returnStr() == ""){
                $(".password-prompt").removeClass("isShow").text("密码不能为空");
            }else{
                if(PasswordStr.returnStr().length == 6 && PasswordStr1.returnStr().length == 6){
                    if(PasswordStr.returnStr() == PasswordStr1.returnStr()){
                        $(".content-2").addClass("isShow");
                        $(".forgot-password-2").addClass("isShow");
                        $(".content-3").removeClass("isShow");
                        $(".forgot-password-3").removeClass("isShow");
                    }else{
                        $(".password-prompt").removeClass("isShow").text("两次输入不一致");
                    }
                }else{
                    $(".password-prompt").removeClass("isShow").text("密码不能为空");
                }
            }
        })
        // 第三步
//        $(".close-forgot-password").click(function(){
//
//        })

    })

</script>
</html>