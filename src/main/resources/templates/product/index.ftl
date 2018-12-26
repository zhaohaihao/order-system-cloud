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
                    <form role="form" method="post" action="/hilox-order/seller/product/save">
                        <div class="form-group">
                            <label>名称</label>
                            <input name="name" type="text" class="form-control" value="${(product.name)!''}"/>
                        </div>
                        <div class="form-group">
                            <label>价格</label>
                            <input name="price" type="text" class="form-control" value="${(product.price)!''}"/>
                        </div>
                        <div class="form-group">
                            <label>库存</label>
                            <input name="stock" type="number" class="form-control" value="${(product.stock)!''}"/>
                        </div>
                        <div class="form-group">
                            <label>描述</label>
                            <input name="description" type="text" class="form-control" value="${(product.description)!''}"/>
                        </div>
                        <div class="form-group">
                            <label>图片</label>
                            <img height="100" width="100" src="${(product.icon)!''}" alt="">
                            <input name="icon" type="text" class="form-control" value="${(product.icon)!''}"/>
                        </div>
                        <div class="form-group">
                            <label>类目</label>
                            <select name="categoryType" class="form-control">
                            <#list productCategoryList as category>
                                <option value="${category.code}"
                                    <#if (product.categoryCode)?? && product.categoryCode == category.code>
                                        selected
                                    </#if>
                                >${category.name}
                                </option>
                            </#list>
                            </select>
                        </div>
                        <input hidden type="text" name="id" value="${(product.id)!''}">
                        <button type="submit" class="btn btn-default">提交</button>
                    </form>
                </div>
            </div>
        </div>
    </div>

</div>
</body>
</html>