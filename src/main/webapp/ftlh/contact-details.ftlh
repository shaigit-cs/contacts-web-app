<#include "header.ftlh">

<div class="row">
    <div class="col-12" id="main">

        <#if inputStatus?has_content>
            <form method="post" action="details">
                <input type="hidden" name="id" value="${contactDetails.id}"/>
                <input type="hidden" name="edit"/>
        <#else>
            <form method="get" action="handler">
                <input type="hidden" name="id" value="${contactDetails.id}"/>
        </#if>
                <div class="form-row">
                    <div class="col-md-3 mt-1 mb-3">
                        <input type="text" class="form-control" value="${contactDetails.name}" name="name" required autocomplete="off" ${inputStatus}>
                    </div>
                    <div class="col-md-3 mt-1 mb-3">
                        <input type="text" class="form-control" value="${contactDetails.surname}" name="surname" required autocomplete="off" ${inputStatus}>
                    </div>
                </div>
        <#if inputStatus?has_content>
                <a class="btn btn-secondary" href="list">${content_close}</a>
                <button class="btn btn-secondary" type="submit">${content_edit}</button>
        <#else>
                <a class="btn btn-secondary" href="list">${content_close}</a>
                <button class="btn btn-secondary" type="submit" name="action" value="update">${content_update}</button>
                <button class="btn btn-danger" type="submit" name="action" value="delete">${content_delete}</button>
        </#if>
            </form>

    </div>
</div>

<#include "footer.ftlh">