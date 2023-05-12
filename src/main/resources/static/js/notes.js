

$(document).ready(function() {
    // on click of like button
    $('.like-btn').click(function() {
        var noteId = $(this).data('note-id');
        var userId = 'TMP';
        var liked = $(this).find('span').text() === 'Liked';

        // send like/unlike request to server
        $.ajax({
            url: `api/notes/${noteId}/${liked ? 'un-like' : 'like'}`,
            type: 'POST',
            data: {userId: userId},
            success: function() {
                // update like button and count
                var likeBtn = $(`.like-btn[data-note-id="${noteId}"]`);
                var likeCount = $(`.like-count[data-note-id="${noteId}"]`);
                $.get(`api/notes/${noteId}/likes-count`, function(count) {
                    likeCount.text(count);
                });
                likeBtn.find('span').text(liked ? 'Like' : 'Liked');
            }
        });
    });
});



/*
$(document).ready(function() {
    var noteId = "YOUR_NOTE_ID";
    var userId = "YOUR_USER_ID";

    // initial load of likes count
    $.get(`api/notes/${noteId}/likes-count`, function(data) {
        $('.like-count').text(data.count);
    });

    // on click of like button
    $('.like-btn').click(function() {
        if ($(this).hasClass('liked')) {
            // unlike
            $.ajax({
                url: `/notes/${noteId}/un-like`,
                type: 'POST',
                data: {userId: userId},
                success: function(data) {
                    $('.like-btn').removeClass('liked').text('Like');
                    $('.like-count').text(data.count);
                }
            });
        } else {
            // like
            $.ajax({
                url: `/notes/${noteId}/like`,
                type: 'POST',
                data: {userId: userId},
                success: function(data) {
                    $('.like-btn').addClass('liked').text('Liked');
                    $('.like-count').text(data.count);
                }
            });
        }
    });
});
*/
