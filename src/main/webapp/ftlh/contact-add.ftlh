<#include "header.ftlh">

<div class="row">
    <div class="col-12" id="main">

        <form method="get" action="handler">
            <input type="hidden" name="action" value="add"/>
            <div class="form-row">
                <div class="col-md-3 mt-1 mb-3">
                    <input type="text" class="form-control" placeholder="Imię" name="name" required autocomplete="off">
                </div>
                <div class="col-md-3 mt-1 mb-3">
                    <input type="text" class="form-control" placeholder="Nazwisko" name="surname" required autocomplete="off">
                </div>
            </div>
            <button class="btn btn-secondary btn-sm" type="submit">Zapisz</button>
        </form>

    </div>
</div>

<#include "footer.ftlh">