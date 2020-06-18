import http from "../http-common";

class CommentDataService {
    getAllComments(bookId) {
        return http.get(`/books/${bookId}/comments`);
    }

    getComment(bookId, commentId) {
        return http.get(`/books/${bookId}/comments/${commentId}`);
    }

    update(comment) {
        let bookId = comment.bookId;
        let commentId = comment.Id;
        return http.put(`/books/${bookId}/comments/${commentId}`, comment);
    }

    delete(comment) {
        let bookId = comment.bookId;
        let commentId = comment.id;
        return http.delete(`/books/${bookId}/comments/${commentId}`);
    }

    create(comment) {
        let bookId = comment.bookId;
        return http.post(`/books/${bookId}/comments/add`, comment);
    }
}

export default new CommentDataService();
