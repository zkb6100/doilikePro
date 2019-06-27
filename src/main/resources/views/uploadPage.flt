<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>文件上传</title>
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <link rel="stylesheet" href="${req.contextPath }/static/js/layui/css/layui.css" media="all">
    <script src="${req.contextPath }/static/js/layui/layui.js"></script>
</head>
<body>
<button type="button" class="layui-btn" id="addFile">
    <i class="layui-icon">&#xe67c;</i>添加文件
</button>
<button type="button" class="layui-btn" id="upload">上传</button>
<div class="layui-progress layui-progress-big" lay-showpercent="true" lay-filter="uploadProgressBar">
    <div class="layui-progress-bar layui-bg-blue" lay-percent="0%"></div>
</div>
</body>
<script>
    layui.use(["layer","form", "upload","jquery","element"], function () {
        // 注意：为了动态显示进度条，必须加载element组件
        var layer = layui.layer, upload = layui.upload, $ = layui.jquery, element = layui.element;

        upload.render({
            accept : "file",
            elem : "#addFile",
            auto : false,   //关闭文件自动上传
            bindAction : "#upload", //文件上传触发按钮
           // url : "upload",
            url:'/backMusic/uploadWithProgress',
            progress:function(value){//上传进度回调 value进度值
                element.progress('uploadProgressBar', value+'%')//设置页面进度条
            },
            before : function (obj) {
   				
            },
            done : function (res, index, upload) {
            	console.log("success");
            },
            error : function (res) {
                
            }
        });

    });
</script>
</html>