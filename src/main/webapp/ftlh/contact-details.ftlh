<#include "header.ftlh">

<div class="row">
    <div class="col-12" id="main">

        <#if editStatus == false>
            <form method="post" action="details">
                <input type="hidden" name="id" value="${contactDetails.id}"/>
                <input type="hidden" name="edit"/>
                <table class="table">
                    <thead>
                    <tr class="d-flex">
                        <th scope="col" class="col-12" style="border-top: none">${contactDetails.name} ${contactDetails.surname}</th>
                    </tr>
                    </thead>
                    <tbody class="font-weight-light">
                    <tr class="d-flex">
                        <td class="col-4"><b>${content_email}:</b></td>
                        <td class="col-8">${contactDetails.email}</td>

                    </tr>
                    <tr class="d-flex">
                        <td class="col-4"><b>${content_phone}:</b></td>
                        <td class="col-8">${contactDetails.phoneCode} ${contactDetails.phone}</td>
                    </tr>
                    <tr class="d-flex">
                        <td class="col-4"><b>${content_birthdate}:</b></td>
                        <td class="col-8">${contactDetails.birthdate?date}</td>
                    </tr>
                    </tbody>
                </table>
                <a class="btn btn-secondary" href="list">${content_close}</a>
                <button class="btn btn-secondary" type="submit">${content_edit}</button>
        <#else>
            <form method="get" action="handler">
                <input type="hidden" name="id" value="${contactDetails.id}"/>
                <div class="form-row">
                    <div class="col-md-3 mt-1 mb-3">
                        <input type="text" class="form-control" value="${contactDetails.name}" placeholder="${content_name}" name="name" autocomplete="off" pattern="[a-zA-ZąćęłóśżźĄĆĘŁÓŚŻŹäöüäöüÄÖÜß]+" required>
                    </div>
                    <div class="col-md-3 mt-1 mb-3">
                        <input type="text" class="form-control" value="${contactDetails.surname}" placeholder="${content_surname}" name="surname" autocomplete="off" pattern="[a-zA-ZąćęłóśżźĄĆĘŁÓŚŻŹäöüäöüÄÖÜß]+" required>
                    </div>
                </div>
                <div class="form-row">
                    <div class="col-md-6 mt-1 mb-3">
                        <input type="text" class="form-control" value="${contactDetails.email}" placeholder="${content_email}" name="email" autocomplete="off" pattern="\b[a-zA-Z0-9]+@[a-zA-Z0-9]+\.[a-zA-Z]{2,}\b" required>
                    </div>
                </div>
                <div class="form-row">
                    <div class="col-md-1 mt-1 mb-3">
                        <select class="form-control" name="code" required>
                            <option value="${contactDetails.phoneCode}" selected disabled hidden>${contactDetails.phoneCode}</option>
                            <option>+44</option>
                            <option>+48</option>
                            <option>+49</option>
                        </select>
                    </div>
                    <div class="col-md-3 mt-1 mb-3">
                        <input type="text" class="form-control" value="${contactDetails.phone}" placeholder="${content_phone}" name="phone" autocomplete="off" pattern="[\d]+" required>
                    </div>
                    <div class="col-md-2 mt-1 mb-3">
                        <div class="input-group">
                            <input type="text" class="form-control" id="datepicker" value="${contactDetails.birthdate?string["yyyy-MM-dd"]}" placeholder="${content_birthdate}" name="birthdate" autocomplete="off" pattern="^\d{4}-(0[1-9]|1[012])-(0[1-9]|[12][0-9]|3[01])$" required>
                            <div class="input-group-append">
                            <span class="input-group-text">
                                <i class="fas fa-calendar-alt"></i>
                            </span>
                            </div>
                        </div>
                    </div>
                </div>
                <a class="btn btn-secondary" href="list">${content_close}</a>
                <button class="btn btn-secondary" type="submit" name="action" value="update">${content_update}</button>
                <button class="btn btn-danger" type="submit" name="action" value="delete">${content_delete}</button>
        </#if>
            </form>

    </div>
</div>

<#include "footer.ftlh">