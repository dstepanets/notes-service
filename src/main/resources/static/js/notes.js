$(document).ready(function () {
    // on click of like button
    $('.like-btn').click(function () {
        const likedClass = 'liked';
        const noteId = $(this).data('note-id');
        const likeBtn = $(`.like-btn[data-note-id="${noteId}"]`);
        const likeCount = $(`.like-count[data-note-id="${noteId}"]`);
        const isLiked = likeBtn.hasClass(likedClass);

        // send like/unlike request to server
        $.ajax({
            url: `api/notes/${noteId}/${isLiked ? 'un-like' : 'like'}`,
            type: 'POST',
            data: {},
            xhrFields: {
                withCredentials: true
            },
            success: function (response) {
                // update like button and count
                likeCount.text(response.likesCount)
                if (response.liked === true) {
                    likeBtn.children('span').eq(0).text(' Liked');
                    likeBtn.addClass(likedClass);
                } else {
                    likeBtn.children('span').eq(0).text(' Like');
                    likeBtn.removeClass(likedClass);
                }
            }
        });
    });
});
