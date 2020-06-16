import http from "../http-common";

class AuthorDataService {
    getAll() {
        return http.get("/authors");
    }

    get(id) {
        return http.get(`/authors/${id}`);
    }

    update(author) {
        return http.put(`/authors`, author);
    }

    delete(id) {
        return http.delete(`/authors/${id}`);
    }

    create(data) {
        return http.post("authors", data);
    }
}

export default new AuthorDataService();