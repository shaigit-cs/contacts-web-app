<#include "header.ftlh">

<div class="row">
    <div class="col-12" id="main">
        <ul class="nav flex-column" id="error">
            <li class="nav-item">
                <p class="font-weight-light">${error_info}</p>
                <a class="nav-link font-weight-light" href="list"><i class="fas fa-chevron-left"></i> ${error_return}</a>
            </li>
        </ul>
    </div>
</div>

<#include "footer.ftlh">