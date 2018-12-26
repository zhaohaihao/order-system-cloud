<html>
<#include "../common/header.ftl">

<body>
<div id="wrapper" class="toggled">

    <#--边栏sidebar-->
    <#include "../common/nav.ftl">

    <#--主要内容content-->
    <div id="page-content-wrapper">
        <div class="container-fluid">
            <div class="row clearfix">
                <div class="col-md-12 column">
                    <form role="form" method="post" action="/hilox-order/seller/category/save">
                        <div class="form-group">
                            <label>名字</label>
                            <input name="name" type="text" class="form-control" value="${(productCategory.name)!''}"/>
                        </div>
                        <div class="form-group">
                            <label>type</label>
                            <input name="code" type="number" class="form-control" value="${(productCategory.code)!''}"/>
                        </div>
                        <input hidden type="text" name="id" value="${(productCategory.id)!''}">
                        <button type="submit" class="btn btn-default">提交</button>
                    </form>
                </div>
            </div>
        </div>
    </div>

</div>
</body>
</html>