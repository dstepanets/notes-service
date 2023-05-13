
$(document).ready(function() {
    // on click of like button
    $('.like-btn').click(function() {
        const noteId = $(this).data('note-id');
        const likeBtn = $(`.like-btn[data-note-id="${noteId}"]`);
        const likeCount = $(`.like-count[data-note-id="${noteId}"]`);
        const liked = likeBtn.text() === ' Liked';

        // send like/unlike request to server
        $.ajax({
            url: `api/notes/${noteId}/${liked ? 'un-like' : 'like'}`,
            type: 'POST',
            data: {},
            xhrFields: {
                withCredentials: true
            },
            success: function() {
                // update like button and count

                $.get(`api/notes/${noteId}/likes-count`, function(count) {
                    likeCount.text(count);
                });
                likeBtn.firstChild;
                if (liked) {
                    likeBtn.text(' Like');
                    likeBtn.removeClass('liked');
                } else {
                    likeBtn.text(' Liked');
                    likeBtn.addClass('liked');
                }

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
