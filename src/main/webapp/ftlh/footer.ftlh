    </div>

    <script src="../js/jquery-3.3.1.min.js"></script>
    <script src="../js/bootstrap-4.2.1.bundle.min.js"></script>
    <script src="../js/bootstrap-datepicker-1.6.4.min.js"></script>
    <script src="../js/locales/bootstrap-datepicker.de.js"></script>
    <script src="../js/locales/bootstrap-datepicker.pl.js"></script>
    <script>
        $('#datepicker').datepicker({
            format: 'yyyy-mm-dd',
            weekStart: 1,
            endDate: '0d',
            todayHighlight: true,
            language: '${currentLanguage}',
            autoclose: true
        });
    </script>

    </body>
</html>
