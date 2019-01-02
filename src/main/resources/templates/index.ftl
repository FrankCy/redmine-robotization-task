<#include "indexDep.ftl">
<@html title="" lang="zh-CN">
    <!--头部 导航栏-->
    <header>
    </header>
    <body>
        <div class="container-fluid" style="max-width: 500px" >
            <form method="POST" enctype="multipart/form-data" action="/redmine/upload">
                <p>
                    文件：<input type="file" name="file" />
                </p>
                <p>
                    <input type="submit" value="上传" />
                </p>
            </form>
            <!--
            <form method="POST" enctype="multipart/form-data" action="/redmine/upload">
                <div class="form-group">
                    <label for="exampleInputEmail1">请上传您的任务清单</label>
                    <input type="file" class="form-control" id="exampleInputEmail1" placeholder="Email">
                </div>
                <button type="submit" class="btn btn-default">提交</button>
            </form>
            -->
        </div>
    </body>
</@html>