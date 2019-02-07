<#include "header.ftlh">

<div class="row">
    <div class="col-12" id="main">

        <form method="post" action="login">
            <div class="form-row">
                <div class="col-md-3 m-a mt-1 mb-3 mx-auto">
                    <input type="text" class="form-control" placeholder="${content_email}" name="email" autocomplete="off" pattern="${staticFields.getPatternEmail()}" required>
                </div>
            </div>
            <div class="form-row">
                <div class="col-md-3 mt-1 mb-3 mx-auto">
                    <input type="text" class="form-control" placeholder="${content_password}" name="password" autocomplete="off" required>
                </div>
            </div>
            <div class="form-row">
                <div class="mt-1 mb-1 mx-auto">
                    <button class="btn btn-primary" type="submit" disabled>${content_login}</button>
                </div>
            </div>
            <div class="form-row">
                <div class="mt-1 mb-3 mx-auto">
                    <a class="btn btn-secondary" href="#">${content_signup}</a>
                </div>
            </div>
        </form>

    </div>
</div>

<#include "footer.ftlh">