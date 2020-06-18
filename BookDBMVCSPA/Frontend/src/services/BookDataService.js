import http from "../http-common";

class BookDataService {
    getAll() {
        return http.get("/books");
    }

    get(id) {
        return http.get(`/books/${id}`);
    }

    update(book) {
        return http.put(`/books`, book);
    }

    delete(id) {
        return http.delete(`/books/${id}`);
    }

    create(data) {
        return http.post("/books", data);
    }
}

export default new BookDataService();