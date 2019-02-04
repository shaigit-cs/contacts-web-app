<#include "header.ftlh">

<div class="row">
    <div class="col-12" id="main">

        <form method="get" action="handler">
            <input type="hidden" name="action" value="add"/>
            <div class="form-row">
                <div class="col-md-3 mt-1 mb-3">
                    <input type="text" class="form-control" placeholder="${content_name}" name="name" autocomplete="off" pattern="[a-zA-ZąćęłóśżźĄĆĘŁÓŚŻŹäöüäöüÄÖÜß]+" required>
                </div>
                <div class="col-md-3 mt-1 mb-3">
                    <input type="text" class="form-control" placeholder="${content_surname}" name="surname" autocomplete="off" pattern="[a-zA-ZąćęłóśżźĄĆĘŁÓŚŻŹäöüäöüÄÖÜß]+" required>
                </div>
            </div>
            <div class="form-row">
                <div class="col-md-6 mt-1 mb-3">
                    <input type="text" class="form-control" placeholder="${content_email}" name="email" autocomplete="off" pattern="\b[a-zA-Z0-9]+@[a-zA-Z0-9]+\.[a-zA-Z]{2,}\b" required>
                </div>
            </div>
            <div class="form-row">
                <div class="col-md-1 mt-1 mb-3">
                    <select class="form-control" name="code" required>
                        <option value="" selected disabled hidden>${content_phone_code}</option>
                        <option>+44</option>
                        <option>+48</option>
                        <option>+49</option>
                    </select>
                </div>
                <div class="col-md-3 mt-1 mb-3">
                    <input type="text" class="form-control" placeholder="${content_phone}" name="phone" autocomplete="off" pattern="[\d]+" required>
                </div>
                <div class="col-md-2 mt-1 mb-3">
                    <div class="input-group">
                        <input type="text" class="form-control" id="datepicker" placeholder="${content_birthdate}" name="birthdate" autocomplete="off" pattern="^\d{4}-(0[1-9]|1[012])-(0[1-9]|[12][0-9]|3[01])$" required>
                        <div class="input-group-append">
                            <span class="input-group-text">
                                <i class="fas fa-calendar-alt"></i>
                            </span>
                        </div>
                    </div>
                </div>
            </div>
            <a class="btn btn-secondary" href="list">${content_close}</a>
            <button class="btn btn-secondary" type="submit">${content_save}</button>
        </form>

    </div>
</div>

<#include "footer.ftlh">