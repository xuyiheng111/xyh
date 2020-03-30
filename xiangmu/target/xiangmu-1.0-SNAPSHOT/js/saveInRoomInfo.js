layui.use(['jquery','layer','form','laydate'],function () {
    var $ = layui.jquery,
        layer = layui.layer,
        form = layui.form,
        laydate = layui.laydate;

    //初始化可入住的房屋
    loadRoomsByPramas();

    //执行一个laydate实例
    laydate.render({
        elem: '#create_date', //指定元素
        format: 'yyyy/MM/dd HH:mm:ss',  //日期的字符串格式
        value:new Date(),  //显示当前时间
        type:'datetime',  //选则格式（精确到秒）
        min:0  //只能选中当前时间之后的时间
    });

    //监听是否会员选择单选框
    form.on('radio(isVip)', function(data){
        if(data.value=="1"){
            //除开输入会员卡号的输入框可以输入之外，其他的个人信息输入框均不可输入
            $("#vip_num").removeAttr("disabled");
            $("#customerName").attr("disabled","disabled");
            $("input:radio:gt(1)").attr("disabled","disabled");
            $("#idcard").attr("disabled","disabled");
            $("#phone").attr("disabled","disabled");
        }else {
            //除开输入会员卡号的输入框不可输入之外，其他的个人信息输入框均可输入
            $("#vip_num").attr("disabled","disabled");
            $("#customerName").removeAttr("disabled");
            // $("input:radio").removeAttr("disabled");
            $("#genderDiv").replaceWith('<div class="layui-input-block" id="genderDiv">\n' +
                '                <input type="radio" name="gender" value="1" title="男" checked="checked"/>\n' +
                '                <input type="radio" name="gender" value="0" title="女"/>\n' +
                '            </div>');
            form.render("radio");  //渲染表单的单选框
            $("#idcard").removeAttr("disabled");
            $("#phone").removeAttr("disabled");
            $("form").eq(0).find("input:gt(1)").val("");
        }
    });

    //查询会员信息
    $("#vip_num").blur(function () {
        var vipNum = $(this).val();
        if(vipNum.length==16){
            //根据会员卡号查询会员信息
            loadVipByPramas(vipNum);
        }else {
            layer.tips('卡号格式输入有误！', '#vip_num', {tips: [2,'#fc1505'], time:3000});  //吸附框
        }
    });

    form.verify({
        money: function (value, item) { //value：表单的值、item：表单的DOM对象
            if (value < 0) {
                return '押金不能小于0';
            }
        }
    })

    //添加提交
    form.on('submit(demo1)', function(data){
        var saveJsonINI = data.field;
        saveJsonINI['status'] = "1";
        saveJsonINI['outRoomStatus'] = "0";
        saveINI(saveJsonINI);  //添加入住信息
        return false;  //阻止页面跳转
    });

    //添加入住信息
    function saveINI(saveJsonINI){
        $.ajax({
            type:'POST',
            url:'inRoomInfo/saveT',
            data:saveJsonINI,
            success:function (data) {
                if(data=="success"){
                    layer.msg("入住信息添加成功。。",{icon:1,time:2000,anim:4});
                    setTimeout('window.location = "model/toShowInRoomInfo"',2000);
                }else {
                    layer.msg("入住信息添加失败！！",{icon:2,time:2000,anim:5});
                }
            },
            error:function () {
                layer.msg("服务器异常！！",{icon:3,time:2000,anim:3});
            }
        });
    }

    //查询可以入住的房间信息
    function loadRoomsByPramas() {
        $.ajax({
            type:'POST',
            url:'rooms/loadManyTByPramas',
            data:{"roomStatus":"0"},
            success:function (data) {
                var roomStr = '<option value="">--请选择房间--</option>';
                $.each(data,function (i,rooms) {
                    roomStr += '<option value="'+rooms.id+'">'+rooms.roomNum+'-'+rooms.roomType.roomTypeName+'-'+rooms.roomType.roomPrice+'</option>';
                })
                $("#selRoomNumId").html(roomStr);
                form.render("select");  //渲染表单的下拉框
            },
            error:function () {
                layer.msg("服务器异常！！",{icon:3,time:2000,anim:3});
            }
        });
    }

    //根据会员卡号查询会员信息
    function loadVipByPramas(vipNum){
        $.ajax({
            type:'POST',
            url:'vip/loadTByPramas',
            data:{"vipNum":vipNum},
            success:function (data) {
                if(data!=""){
                    layer.tips('已查到该会员信息。。', '#vip_num', {tips: [2,'green'], time:3000});  //吸附框
                    //给表单赋值
                    form.val("example", { //formTest 即 class="layui-form" 所在元素属性 lay-filter="" 对应的值
                        "customerName": data.customerName
                        ,"idcard": data.idcard
                        ,"phone": data.phone
                        ,"gender": data.gender
                    });
                }else {
                    layer.tips('没有查到该会员信息！！', '#vip_num', {tips: [2,'red'], time:3000});  //吸附框
                    $("form").eq(0).find("input:gt(1)").val("");
                }
            },
            error:function () {
                layer.msg("服务器异常！！",{icon:3,time:2000,anim:3});
            }
        });
    }
})