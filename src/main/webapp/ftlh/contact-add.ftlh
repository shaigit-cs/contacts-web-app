<#include "header.ftlh">

<div class="row">
    <div class="col-12" id="main">

        <form method="get" action="handler">
            <input type="hidden" name="action" value="add"/>
            <div class="form-row">
                <div class="col-md-3 mt-1 mb-3">
                    <input type="text" class="form-control" placeholder="${content_name}" name="name" required autocomplete="off">
                </div>
                <div class="col-md-3 mt-1 mb-3">
                    <input type="text" class="form-control" placeholder="${content_surname}" name="surname" required autocomplete="off">
                </div>
            </div>
            <a class="btn btn-secondary" href="list">${content_close}</a>
            <button class="btn btn-secondary" type="submit">${content_save}</button>
        </form>

    </div>
</div>

<#include "footer.ftlh">