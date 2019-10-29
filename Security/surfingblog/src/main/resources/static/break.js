
$('#break-dropdown').on('change', function () {
    refreshBreak();
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


                var div = '<div class ="row-md-12 border>';
                div += '<div class ="col-md-9">'
                div += breakCommentText
                div += '<button type="button" onclick ="deleteBreakComment(' + breakCommentId + ')" class="btn btn-default">Delete</button>'
                div += '</div>'
                div += '</div>';

                var div2 = '<div class ="row-md-12 text-center border>';
                div2 += '<div class ="col-md-9">'
                div2 += breakCommentUser
                div2 += '</div>'
                div2 += '</div>';

                var div3 = '<div class ="row-md-12 text-center border>';
                div3 += '<div class ="col-md-9">'
                div3 += breakCommentId
                div3 += '</div>'
                div3 += '</div>';

                $('#userId').append(div3);
                $('#userName').append(div2);
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
    $('#userName').empty();
    $('#userId').empty();
}

function deleteBreakComment(breakCommentId) {

    $.ajax({
        method: "PUT",
        url: 'http://localhost:8080/breakComments/delete/' + breakCommentId,
        success: function (response) {
            alert('success');
            refreshBreak();
        },
        error: function (jqXHR, status, thrownError) {
            alert('failure');
        }

    })
}

function refreshBreak() {
    var breakId = $('#break-dropdown').find('option:selected').val();
    getBreakById(breakId);
    emptyBreakComments();
    getBreakComments(breakId);
    $('#commentBreakId').val(breakId);
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
//
//                div += '<div class ="col-md-3">'
//                div += breakCommentUser
//                div += '</div>'