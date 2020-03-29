layui.use(['jquery','layer','form','upload'],function () {
    //创建出内置模块的对象
    var $ = layui.jquery,
        layer = layui.layer,
        form = layui.form,
        upload = layui.upload;

    //初始化房屋信息
    loadAllRooms();

    loadAllRoomType();

    //判断房间号的唯一性
    var roomNumIf = false;

    //点击添加按钮，弹出添加框
    $("#saveRoomsUI").click(function () {
        //1.清空添加弹框的值
        $("form :input").val("");
        $("#roomPicId").val("/img/fm7.jpg")
        $('#demo1').attr('src', "/img/fm7.jpg");
        //1.弹框
        layer.open({
            type:1,
            title:'添加房屋信息界面',
            area:['420px','500px'],
            shade:0.5,
            content:$("#saveRoomsDiv"),
            anim:2
        });
    });

    //普通图片上传
    var uploadInst = upload.render({
        elem: '#test1'  //绑定上传的按钮
        ,url: 'rooms/uploadRoomPic'   //服务器端的访问路径
        //,data:{"path":"E:\\BaiduNetdiskDownload\\imgResources"}  //传回服务器端的文件上传路径额外参数
        ,field:'myFile'  //设定文件域的字段名
        ,before: function(obj){   //上传之前的函数回调
            //预读本地文件示例，不支持ie8
            obj.preview(function(index, file, result){    //底层为jquery的操作
                //result表示的是选中图片的绝对访问路径
                $('#demo1').attr('src', result); //图片链接（base64）
            });
        }
        ,done: function(res){    //res表示上传后返回的Map集合
            //如果上传成功
            if(res.code == 0){
                //将上传后的新文件名字加入添加房屋的from表单的隐藏域中
                $("#roomPicId").val(res.newFileName);
                return layer.msg('上传成功。。');
            }else {//如果上传失败
                return layer.msg('上传失败！！');
            }
        }
        ,error: function(){
            //演示失败状态，并实现重传
            var demoText = $('#demoText');
            demoText.html('<span style="color: #FF5722;">上传失败</span> <a class="layui-btn layui-btn-xs demo-reload">重试</a>');
            demoText.find('.demo-reload').on('click', function(){
                uploadInst.upload();
            });
        }
    });

    //房间号绑定失去焦点事件
    $("#roomNum").blur(function () {
        checkRoomNum($(this).val());  //验证房间号的唯一性
    });

    //房屋添加的提交
    form.on('submit(demo3)', function(data){
        if(roomNumIf){
            var saveJsonRooms = data.field;
            saveJsonRooms['roomStatus'] = "0";
            saveJsonRooms['flag'] = 1;
            console.info(saveJsonRooms)
            saveRooms(saveJsonRooms);  //完成房屋的添加
            layer.closeAll();  //关闭所有弹框
            //$('#demo1').attr('src', "/img/fm7.jpg");
        }else {
            layer.msg("房间号输入有误！！",{icon:2,time:2000,anim:6});
        }
        return false;
    });

    //执行客房的操作
    $("ul").on('click','button',function () {
        var value =  $(this).val();
        var id = $(this).attr("roomid");
        if(value=='del') {
            layer.confirm('真的删除此房间信息么？', function (index) {
                //向服务器端发送ajax请求完成房间显示状态的修改
                var delJsonRooms = {};
                delJsonRooms['id'] = id;
                delJsonRooms['flag'] = 0;
                updRooms(delJsonRooms,value);
                layer.close(index);  //关闭弹框
            });
        }else if(value=='upd') {
            layer.msg("执行修改操作。。");
            var arrRooms = $(this).attr("roomsStr").split(",");
            console.log(arrRooms);
            //1.数据的回显
            //给表单赋值
            form.val("updRoomsForm", { //formTest 即 class="layui-form" 所在元素属性 lay-filter="" 对应的值
                "roomPic": arrRooms[2] // "name": "value"
                ,"roomNum": arrRooms[1]
            });
            $("#roomNum").attr("disabled","disabled");
            $("#demo1").attr("src",arrRooms[2]);
            $("#roomTypeOpt").replaceWith('<option value="'+arrRooms[3]+'" id="roomTypeOpt" selected>'+arrRooms[4]+'</option>');
            form.render("select");
            //2.弹框
            layer.open({
                type:1,
                title:'修改房屋信息界面',
                area:['420px','500px'],
                shade:0.5,
                content:$("#saveRoomsDiv"),
                anim:4
            });
            //3.修改的提交
            form.on('submit(demo3)', function(data){
                var updJsonRooms = data.field;
                delete updJsonRooms['roomNum'];  //删除json数据中的某一个属性值
                updJsonRooms["id"] = arrRooms[0];
                updRooms(updJsonRooms,"edit");
                layer.closeAll();
                return false; //阻止表单跳转。如果需要表单跳转，去掉这段即可。
            });
        }else {
            layer.confirm('确定此房间变为空闲的么？', function(index) {
                //向服务器端发送ajax请求完成房间显示状态的修改
                var updJsonRooms = {};
                updJsonRooms['id'] = id;
                updJsonRooms['roomStatus'] = 0;
                updRooms(updJsonRooms,value);
                layer.close(index);  //关闭弹框
            });
        }
    })

    //加载所有房屋信息
    function loadAllRooms() {
        $.ajax({
            type:"POST",
            url:"rooms/loadAllT",
            dataType:"JSON",
            success:function (data) {
                var roomStatus0 = '';
                var roomStatus1 = '';
                var roomStatus2 = '';
                var arrUl = $("ul");  //标签选择器获取页面所有的ul标签对象
                $.each(data,function (i,item) {
                    if(item.roomStatus=='0'){
                        var roomsStr = item.id+","+item.roomNum+","+item.roomPic+","+item.roomType.id+","+item.roomType.roomTypeName;
                        roomStatus0 += '<li style="background-color: #009688;">';
                        roomStatus0 += '<img class="layui-anim" id="demo1" src="'+item.roomPic+'" width="135px" height="135px"/>';
                        roomStatus0 += '<div class="code">';
                        roomStatus0 += '<span style="display: block;color: #0C0C0C;">'+item.roomNum+'-'+item.roomType.roomTypeName+'-'+item.roomType.roomPrice+'元/天</span>';
                        roomStatus0 += '<button type="button" value="del" roomid="'+item.id+'" class="layui-btn layui-btn-danger layui-btn-xs">删除</button>';
                        roomStatus0 += '<button type="button" value="upd" roomsStr="'+roomsStr+'" class="layui-btn layui-btn-warm layui-btn-xs">修改</button>';
                        roomStatus0 += '</div>';
                        roomStatus0 += '</li>';
                    }else if(item.roomStatus=='1'){
                        roomStatus1 += '<li style="background-color: red;">';
                        roomStatus1 += '<img class="layui-anim" id="demo1" src="'+item.roomPic+'" width="135px" height="135px"/>';
                        roomStatus1 += '<div class="code">';
                        roomStatus1 += '<span style="display: block;color: #0C0C0C;">'+item.roomNum+'-'+item.roomType.roomTypeName+'-'+item.roomType.roomPrice+'元/天</span>';
                        roomStatus1 += '</div>';
                        roomStatus1 += '</li>';
                    }else {
                        roomStatus2 += '<li style="background-color: blueviolet;">';
                        roomStatus2 += '<img class="layui-anim" id="demo1" src="'+item.roomPic+'" width="135px" height="135px"/>';
                        roomStatus2 += '<div class="code">';
                        roomStatus2 += '<span style="display: block;color: #0C0C0C;">'+item.roomNum+'-'+item.roomType.roomTypeName+'-'+item.roomType.roomPrice+'元/天</span>';
                        roomStatus2 += '<button type="button" value="del" roomid="'+item.id+'" class="layui-btn layui-btn-danger layui-btn-xs">删除</button>';
                        roomStatus2 += '<button type="button" value="edit" roomid="'+item.id+'" class="layui-btn layui-btn-xs layui-btn-normal">空闲</button>';
                        roomStatus2 += '</div>';
                        roomStatus2 += '</li>';
                    }
                });
                $(arrUl[0]).html(roomStatus0);
                $(arrUl[1]).html(roomStatus1);
                $(arrUl[2]).html(roomStatus2);
            },
            error:function () {
                layer.msg("服务器异常！！",{icon:3,time:2000,anim:3});
            }
        });
    }

    //加载所有的房间类型
    function loadAllRoomType(){
        $.ajax({
            type: "POST",
            url: "roomType/loadAllT",
            dataType: "JSON",
            success: function (data) {
                var roomTypeStr = '<option value="" id="roomTypeOpt" selected>--请选择房屋类型--</option>';
                $.each(data,function (i,item) {
                    roomTypeStr += '<option value="'+item.id+'">'+item.roomTypeName+'</option>';
                })
                $("#selRoomType").html(roomTypeStr);
                form.render("select");
            },
            error:function () {
                layer.msg("服务器异常！！",{icon:3,time:2000,anim:3});
            }
        });
    }

    //修改房间信息
    function updRooms(JsonRooms,value) {
        $.ajax({
            type:'POST',
            url:'rooms/updByPrimaryKeySelective',
            data:JsonRooms,
            success:function (data) {
                if(data=="success"){
                    if(value=='del'){
                        layer.msg("房屋删除成功。。",{icon:1,time:2000,anim:3});
                    }else if(value=='upd'){
                        layer.msg("房屋空闲修改成功。。",{icon:1,time:2000,anim:4});
                    }else {
                        layer.msg("房屋信息修改成功。。",{icon:1,time:2000,anim:4});
                    }
                    loadAllRooms();  //重新加载房屋信息
                }else {
                    if(value=='del'){
                        layer.msg("房屋删除失败！！",{icon:2,time:2000,anim:3});
                    }else if(value=='upd'){
                        layer.msg("房屋空闲修改失败！！",{icon:1,time:2000,anim:4});
                    }else {
                        layer.msg("房屋信息修改失败！！",{icon:1,time:2000,anim:4});
                    }
                }
            },
            error:function () {
                layer.msg("服务器异常！！",{icon:3,time:2000,anim:3});
            }
        });
    }

    //验证房间号的唯一性
    function checkRoomNum(roomNum) {
        $.ajax({
            type:'POST',
            url:'rooms/getCountByPramas',
            async: false, //允许外部的变量取到ajax里面的数据
            data:{"roomNum":roomNum},
            success:function (data) {
                if(data==1){
                    layer.tips('房间号已被使用！', '#roomNum', {tips: [2,'#fc1505'], time:3000});  //吸附框
                    roomNumIf = false;
                }else {
                    layer.tips('房间号可用。。', '#roomNum', {tips: [2,'green'], time:3000});  //吸附框
                    roomNumIf = true;
                }
            },
            error:function () {
                layer.msg("服务器异常！！",{icon:3,time:2000,anim:3});
            }
        });
    }

    //房屋添加
    function saveRooms(saveJsonRooms) {
        $.ajax({
            type:'POST',
            url:'rooms/saveT',
            data:saveJsonRooms,
            success:function (data) {
                if(data=="success"){
                    layer.msg("房屋添加成功。。",{icon:1,time:2000,anim:3});
                    loadAllRooms();  //重新加载房屋信息
                }else {
                    layer.msg("房屋添加失败！！",{icon:2,time:2000,anim:3});
                }
            },
            error:function () {
                layer.msg("服务器异常！！",{icon:3,time:2000,anim:3});
            }
        });
    }
})