
$('#break-dropdown').on('change', function () {
    var breakId = $('#break-dropdown').find('option:selected').val();
    getBreakById(breakId);
    emptyBreakComments();
    getBreakComments(breakId);

});

function getBreakById(breakId) {

    $.ajax({
        method: "GET",
        url: 'http://localhost:8080/break/' + breakId,
        success: function (data, status) {
            var breakId = data.id;
            var name = data.name;
            var beach = data.beach;
            var latitude = data.latitude;
            var longitude = data.longitude;
            var blog = data.blog;

            $('#breakName').text(name);
            $('#latitude').text(latitude);
            $('#longitude').text(longitude);

            $('#blogText').text(blog);

        },
        error: function (jqXHR, status, thrownError) {
            alert('failure');
//            $('#messages').val(jqXHR.responseJSON.message);
        }
    })
}

function getBreakComments(breakId) {

    $.ajax({
        method: "GET",
        url: 'http://localhost:8080/breakComments/' + breakId,
        success: function (beachComments) {
            $.each(beachComments, function (index, beachComment) {

                var breakCommentId = beachComment.id;
                var breakCommentUser = beachComment.user.username;
                var breakCommentText = beachComment.commentText;
                
//                $('#id').val(breakCommentId);
//                $('#userName').val(breakCommentUser);
//                $('#break-user-comments').val(breakCommentText);
                

                var div = '<div class ="row-md-12 text-center border border-light>';
                div += '<div class ="col-md-3">'
                div += breakCommentUser
                div += '</div>'
                div += '<div class ="col-md-9">'
                div += breakCommentText
                div += '</div>'
                div += '<div>'
                div += '<button type="button" onclick ="deleteBreakComment(' + breakCommentId + ')" class="btn btn-default">Delete</button>'
                div += '</div>'
                div += '</div>';

                $('#break-user-comments').append(div);
            });
        },
        error: function (jqXHR, status, thrownError) {
            alert('failure');
        }
    })
}

function emptyBreakComments() {
    $('#break-user-comments').empty();
}

function deleteBreakComment(breakCommentId) {

    $.ajax({
        method: "POST",
        url: 'http://localhost:8080/breakComments/delete/' + breakCommentId,
        success: function (data, status) {
            alert('success');
        },
        error: function (jqXHR, status, thrownError) {
            alert('failure');
        }

    })
}

function getAllBreaks() {

    $.ajax({
        method: "GET",
        url: 'http://localhost:8080/break/',
        success: function (data, status) {
            alert('success');

        },
        error: function (jqXHR, status, thrownError) {
            alert('failure');
//            $('#messages').val(jqXHR.responseJSON.message);
        }
    })
}