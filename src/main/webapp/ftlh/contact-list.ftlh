<#include "header.ftlh">

        <div class="row">
            <div class="col-12" id="main">

                <#assign lastLetter = "o">
                <#list contactList?sort_by("surname") as contact>

                    <#if lastLetter != contact.surname?substring(0, 1)>
                        <p><b>${contact.surname?substring(0, 1)}</b></p>
                    </#if>

                    <#assign lastLetter = contact.surname?substring(0, 1)>
                        <p>${contact.name} <b>${contact.surname}</b></p>

                    <#if contact_has_next>
                        <hr class="my-4">
                    </#if>

                <#else>
                    <p id="empty_list">${list_empty}</p>
                </#list>

                <br>
                <br>
                <br>
                <br>
                <br>
                <br>
                <br>
                <br>
                <br>
                <br>
                <br>
                <br>
                <br>
                <br>
                <br>
                <br>
                <br>
                <br>
                <br>
                <br>
                <br>
                <br>
                <br>
                <br>
                <br>
                <br>
                <br>
                <br>
                <br>
                <br>
                <br>
                <br>
                <br>
                <br>
                <br>
                <br>
                <br>
                <br>
                <br>
                <br>
                <br>
                <br>
                <br>
                <br>
                <br>
                <br>
                <br>
                <br>
                <br>
                <br>
                <br>
                <br>
                <br>
                <a href="test.ftlh">click</a>

            </div>
        </div>

<#include "footer.ftlh">