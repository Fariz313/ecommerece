$(document).ready(function () {
    $('head').append(`<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">`)
    $('head').append(`<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM" crossorigin="anonymous"></script>`)
    $('head').append(`<script src="https://kit.fontawesome.com/add3d2a7df.js" crossorigin="anonymous"></script>`)
    $.ajax({
        type: 'get',
        url: 'ajax_layout/navbar.html',
        contentType: 'application/json',
        error: function (result) {
            console.log("error");
            console.log(result);
        },
        success: function (result) {
            $('body').prepend(result)
        }
    });
});