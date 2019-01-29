<#include "header.ftlh">

        <div class="row">
            <div class="col-12" id="main">

                <#assign nullLetter = "">
                <#list contactList?sort_by("surname") as contact>

                <form method="post" action="details">

                    <#if nullLetter != contact.surname?substring(0, 1)>
                        <p><b>${contact.surname?substring(0, 1)}</b></p>
                    </#if>

                    <#assign nullLetter = contact.surname?substring(0, 1)>
                        <input type="hidden" name="id" value="${contact.id}"/>
                        <button class="btn" type="submit">${contact.name} <b>${contact.surname}</button>


                    <#if contact_has_next>
                        <hr class="my-4">
                    </#if>

                </form>

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